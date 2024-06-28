package com.yilan.awesome.service.impl;

import com.yilan.awesome.service.FileService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author： yilan0916
 * @date: 2024/6/27
 */
@Service
public class FileServiceImpl implements FileService {
    @Override
    public void download(HttpServletResponse response, String fileName) {

    }
}
