package com.service.BVHSHOP.service.Cloudinary;

import com.service.BVHSHOP.request.Cloudinary.CloudinaryReq;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/15/2026 9:11 AM
 */
public interface CloudinaryService {
    void saveImageToCloud(List<MultipartFile> files, String folderName, Long refId, String feature, Consumer<CloudinaryReq> fn);
    String deleteImageByPublicId(String publicId) throws IOException;
}
