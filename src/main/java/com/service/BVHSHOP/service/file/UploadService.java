package com.service.BVHSHOP.service.file;

import com.service.BVHSHOP.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/12/2026 9:24 PM
 */
public interface UploadService {
    String saveMultiFile(List<MultipartFile> file, String folderName,Long id, String feature) throws IOException;
    List<File> getImageByObjectId(Long id);
    String deleteImage(Long id);
}
