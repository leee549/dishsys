package cn.lhx.dishsys.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author lee549
 * @date 2020/6/7 13:06
 */
public interface FileUploadService {
    /**
     * 上传图片
     * @param file
     * @param id
     * @return
     * @throws IOException
     */
    void uploadImage(MultipartFile file,Integer id) throws IOException;

}
