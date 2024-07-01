package com.yilan.awesome.utils;

import com.yilan.awesome.base.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author： yilan0916
 * @date : 2024/1/7
 */
@Slf4j
public class HttpClientUtils {

    private static final String ENCODING = "UTF-8";
    private static final int CONNECT_TIMEOUT = 60000;
    private static final int SOCKET_TIMEOUT = 60000;

    private static final CloseableHttpClient HTTP_CLIENT_DEFAULT;
    private static CloseableHttpClient HTTP_CLIENT;

    private static CloseableHttpResponse HTTP_RESPONSE;
    private static final RequestConfig REQUEST_CONFIG;

    static {
        //连接池
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(1280);
        cm.setDefaultMaxPerRoute(1280);
        HTTP_CLIENT_DEFAULT = HttpClients.custom()
                .setConnectionManager(cm)
                .setConnectionManagerShared(true)
                .build();

        REQUEST_CONFIG = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();
    }

    private static void basicAuth(String username, String password) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));

        HTTP_CLIENT = HttpClients.custom()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();
    }

    public static String doGet(String url, Map<String, String> headers, Map<String, String> params) {
        return executeGet(url, headers, params, false);
    }

    public static String doGet(String url, Map<String, String> headers, Map<String, String> params, String username, String password) {
        basicAuth(username, password);
        return executeGet(url, headers, params, true);
    }

    private static String executeGet(String url, Map<String, String> headers, Map<String, String> params, boolean isBasicAuth) {
        //HttpGet不能封装params参数
        HttpGet httpGet;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            if (params != null) {
                Set<Map.Entry<String, String>> entrySet = params.entrySet();
                for (Map.Entry<String, String> entry : entrySet) {
                    uriBuilder.setParameter(entry.getKey(), entry.getValue());
                }
            }
            httpGet = new HttpGet(uriBuilder.build());
            httpGet.setConfig(REQUEST_CONFIG);
            // 设置请求头
            settingHeaders(httpGet, headers);
            // 执行请求并获得响应结果
            return getHttpClientResult(httpGet, isBasicAuth);
        } catch (URISyntaxException e) {
            log.info("URIBuilder出错");
        } finally {
            // 释放资源
            release();
        }
        return null;
    }


    public static String doPostJson(String url, Map<String, String> headers, String json) {
        return executePost(url, headers, null, true, json, false);
    }

    public static String doPostForm(String url, Map<String, String> headers, Map<String, String> params) {
        return executePost(url, headers, params, false, null, false);
    }

    public static String doPostJson(String url, Map<String, String> headers, String json, String username, String password) {
        basicAuth(username, password);
        return executePost(url, headers, null, true, json, true);
    }

    public static String doPostForm(String url, Map<String, String> headers, Map<String, String> params, String username, String password) {
        basicAuth(username, password);
        return executePost(url, headers, params, false, null, true);
    }

    private static String executePost(String url, Map<String, String> headers, Map<String, String> params, Boolean isStringEntity, String json, boolean isBasicAuth) {
        //https://www.cnblogs.com/shouyaya/p/14202656.html
        HttpPost httpPost;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            httpPost = new HttpPost(uriBuilder.build());
            httpPost.setConfig(REQUEST_CONFIG);
            settingHeaders(httpPost, headers);
            if (isStringEntity) {
                settingStringEntity(httpPost, json);
            } else {
                settingParams(httpPost, params);
            }
            return getHttpClientResult(httpPost, isBasicAuth);
        } catch (URISyntaxException e) {
            log.error("URIBuilder出错");
        } finally {
            // 释放资源
            release();
        }
        return null;
    }

    public static String doPutJson(String url, Map<String, String> headers, String json) {
        return executePut(url, headers, null, true, json, false);
    }

    public static String doPutForm(String url, Map<String, String> headers, Map<String, String> params) {
        return executePut(url, headers, params, false, null, false);
    }

    public static String doPutJson(String url, Map<String, String> headers, String json, String username, String password) {
        basicAuth(username, password);
        return executePut(url, headers, null, true, json, true);
    }

    public static String doPutForm(String url, Map<String, String> headers, Map<String, String> params, String username, String password) {
        basicAuth(username, password);
        return executePut(url, headers, params, false, null, true);
    }

    private static String executePut(String url, Map<String, String> headers, Map<String, String> params, Boolean isStringEntity, String json, boolean isBasicAuth) {
        HttpPut httpPut;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            httpPut = new HttpPut(uriBuilder.build());
            httpPut.setConfig(REQUEST_CONFIG);
            settingHeaders(httpPut, headers);
            if (isStringEntity) {
                settingStringEntity(httpPut, json);
            } else {
                settingParams(httpPut, params);
            }
            return getHttpClientResult(httpPut, isBasicAuth);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void settingHeaders(HttpRequestBase httpMethod, Map<String, String> headers) {
        // 封装请求头
        if (headers != null) {
            Set<Map.Entry<String, String>> entrySet = headers.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                // 设置到请求头到HttpRequestBase对象中
                httpMethod.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    private static void settingParams(HttpEntityEnclosingRequestBase httpMethod, Map<String, String> params) {
        // 封装请求参数
        if (params != null) {
            List<NameValuePair> nvps = new ArrayList<>();
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            // 设置到请求的http对象中
            try {
                httpMethod.setEntity(new UrlEncodedFormEntity(nvps, ENCODING));
            } catch (UnsupportedEncodingException e) {
                log.error("封装请求参数出错");
            }
        }
    }

    private static void settingStringEntity(HttpEntityEnclosingRequestBase httpMethod, String str) {
        //为application/xml、json等封装数据
        if (str != null) {
            httpMethod.setEntity(new StringEntity(str, ENCODING));
        }
    }

    private static String getHttpClientResult(HttpRequestBase httpMethod, boolean basicAuth) {
        CloseableHttpClient httpClient;
        if (basicAuth) {
            httpClient = HTTP_CLIENT;
        } else {
            httpClient = HTTP_CLIENT_DEFAULT;
        }
        try {
            // 执行请求
            HTTP_RESPONSE = httpClient.execute(httpMethod);
            // 获取返回结果
            if (HTTP_RESPONSE != null && HTTP_RESPONSE.getStatusLine() != null) {
                String content = "";
                if (HTTP_RESPONSE.getEntity() != null) {
                    content = EntityUtils.toString(HTTP_RESPONSE.getEntity(), ENCODING);
//                    log.info("HttpClient请求内容" + content);
                }
                return content;
            }
        } catch (IOException e) {
            log.error("getHttpClientResult执行错误");
        }
        return null;
    }

    private static void release() {
        // 释放资源
        try {
            if (HTTP_CLIENT != null) {
                HTTP_CLIENT.close();
            }
            if (HTTP_CLIENT_DEFAULT != null) {
                HTTP_CLIENT_DEFAULT.close();
            }
            if (HTTP_RESPONSE != null) {
                HTTP_RESPONSE.close();
            }
        } catch (IOException e) {
            log.error("release失败");
        }
    }
}