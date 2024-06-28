package com.yilan.awesome.rest;

import com.yilan.awesome.base.ResponseResult;
import com.yilan.awesome.domain.vo.UserDTO;
import com.yilan.awesome.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传和下载 控制器
 *
 * @author： yilan0916
 * @date: 2024/6/27
 */
@Api(tags = "demo应用：FileController")
@Slf4j
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @ApiOperation("上传文件接口")
    @PostMapping("/upload")
    public ResponseResult<String> upload(@RequestPart("file") MultipartFile file) {
        String content = String.format("name = %s,originName = %s,size = %d",
                file.getName(), file.getOriginalFilename(), file.getSize());
        log.info(content);
        return ResponseResult.success(content);
    }

    @ApiOperation("上传文件并携带json数据接口")
    @PostMapping("/uploadWithJson")
    public ResponseResult<String> uploadWithJson(@RequestPart("file") MultipartFile file,
                                                 @RequestPart("user") UserDTO user) {
        String content = String.format("name = %s,originName = %s,size = %d",
                file.getName(), file.getOriginalFilename(), file.getSize());
        log.info(content);

        log.info("user = {}", user);
        return ResponseResult.success(content + user);
    }

    @ApiOperation("下载文件接口")
    @GetMapping("/download/{fileName:.+}")
    public void download(HttpServletResponse response, @PathVariable String fileName) {
        fileService.download(response, fileName);
    }
}