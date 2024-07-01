package com.yilan.awesome.utils;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @author： yilan0916
 * @date: 2024/7/1
 */
@Slf4j
public class JschUtils {
    private static final int TIMEOUT = 5 * 60 * 1000;

    /**
     * 获取session
     *
     * @param host     ip
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return Session
     */
    public static Session getSession(String host, int port, String username, String password) {
        JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(TIMEOUT);

        } catch (JSchException e) {
            e.printStackTrace();
        }
        return session;
    }

    /**
     * 开启exec通道
     *
     * @param session Session
     * @return ChanelExec
     */
    public static ChannelExec openChannelExec(Session session) {
        ChannelExec channelExec = null;
        try {
            channelExec = (ChannelExec) session.openChannel("exec");
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return channelExec;
    }

    /**
     * 开启sftp通道
     *
     * @param session Session
     * @return ChannelSftp
     */
    public static ChannelSftp openChannelSftp(Session session) {
        ChannelSftp channelSftp = null;
        try {
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return channelSftp;
    }

    public static void close(Channel channel) {
        if (channel != null) {
            channel.disconnect();
        }
    }

    public static void close(Session session) {
        if (session != null) {
            session.disconnect();
        }
    }

    /**
     * 异步执行,不需要结果
     *
     * @param session Session
     * @param cmd     命令
     */
    public static void execCommand(Session session, String cmd) {
        ChannelExec channelExec = openChannelExec(session);
        channelExec.setCommand(cmd);
        try {
            channelExec.connect();
        } catch (JSchException e) {
            e.printStackTrace();
        }
        close(channelExec);
    }

    /**
     * 同步执行,需要获取执行完的结果
     *
     * @param session Session
     * @param cmd     命令
     * @param charset 字符格式
     * @return 结果
     */
    public static String execCommand(Session session, String cmd, String charset) {
        ChannelExec channelExec = openChannelExec(session);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        channelExec.setCommand(cmd);
        channelExec.setOutputStream(out);
        String result = null;
        try {
            channelExec.connect();
            Thread.sleep(2000);
            result = out.toString(charset);
            out.close();
            // 关闭通道
            close(channelExec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void uploadFile(Session session, String dst, File file) {

        try (FileInputStream fis = new FileInputStream(file)) {
            uploadFile(session, dst, fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void uploadFile(Session session, String dst, InputStream inputStream) {
        try {
            // 打开channelSftp
            ChannelSftp sftp = openChannelSftp(session);
            // 远程连接
            sftp.connect();
            sftp.put(inputStream, dst, ChannelSftp.OVERWRITE);
            sftp.disconnect();
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断有无路径
     *
     * @param path 路径
     * @return true or false
     */
    public static boolean hasPath(String path, ChannelSftp sftp) {
        try {
            sftp.lstat(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}