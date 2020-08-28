package cn.lhx.dishsys.service.impl;

import cn.lhx.dishsys.dao.UserDao;
import cn.lhx.dishsys.entity.UserInfo;
import cn.lhx.dishsys.service.FileUploadService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author lee549
 * @date 2020/6/7 13:06
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Resource
    UserDao userDao;
    @Value("${windows.path}")
    private String windowsPath;

    @Value("${linux.path}")
    private String linuxPath;

    @Value("${file.path}")
    private String filePath;
    @Override
    public void uploadImage(MultipartFile file, Integer id) throws IOException {
        // 设置随机前缀名
        String fileName = UUID.randomUUID().toString();
        // 获取源文件名
        String originalFilename = file.getOriginalFilename();
        // 获取后缀
        assert originalFilename != null;
        String extension = originalFilename.substring(originalFilename.indexOf("."));
        // 拼接文件名
        String uniqueFileName = fileName + extension;

        File savePathFile = new File(windowsPath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        // 2.文件上传类型
        String contentType = file.getContentType();

        if ("image/jpeg".equals(contentType)
                || "image/png".equals(contentType)
                || "image/jpg".equals(contentType)) {
            // 查询当前用户,设置新路径
            UserInfo user = userDao.selectById(id);
            user.setFaceImg(filePath+uniqueFileName);
            // 更新路径
            QueryWrapper<UserInfo> qw = new QueryWrapper<>();
            qw.lambda().eq(UserInfo::getUserId, id);
            userDao.update(user, qw);
            //获取操作系统名
            String osName = System.getProperty("os.name");
            // 上传
            if (osName.toLowerCase().startsWith("win")) {
                file.transferTo(new File(windowsPath + uniqueFileName));
            } else {
                file.transferTo(new File(linuxPath + uniqueFileName));
            }
            System.out.println(windowsPath + uniqueFileName);
        }
    }
}
