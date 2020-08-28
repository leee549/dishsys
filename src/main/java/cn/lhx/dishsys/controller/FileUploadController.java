package cn.lhx.dishsys.controller;

import cn.lhx.dishsys.core.base.JsonResult;
import cn.lhx.dishsys.core.enmus.ResultCode;
import cn.lhx.dishsys.service.FileUploadService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author lee549
 * @date 2020/6/7 13:04
 */
@RestController
public class FileUploadController {
    @Resource
    FileUploadService fileUploadService;

    @RequestMapping("/upload")
    public JsonResult<Object> upload(MultipartFile file ,Integer id) throws IOException {
        fileUploadService.uploadImage(file,id);
        return JsonResult.success(null,ResultCode.SUCCESS.val);
    }
}
