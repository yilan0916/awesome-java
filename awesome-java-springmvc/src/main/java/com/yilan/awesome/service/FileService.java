package com.yilan.awesome.service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author： yilan0916
 * @date: 2024/6/27
 */
public interface FileService {
    void download(HttpServletResponse response, String fileName);
}
