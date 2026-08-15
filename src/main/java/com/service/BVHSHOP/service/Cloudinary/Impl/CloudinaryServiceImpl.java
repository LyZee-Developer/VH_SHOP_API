package com.service.BVHSHOP.service.Cloudinary.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.service.BVHSHOP.exception.ApiException;
import com.service.BVHSHOP.request.Cloudinary.CloudinaryReq;
import com.service.BVHSHOP.service.Cloudinary.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/15/2026 9:11 AM
 */
public class CloudinaryServiceImpl implements CloudinaryService {

    @Autowired
    Cloudinary cloudinary;

    @Override
    public void saveImageToCloud(List<MultipartFile> files, String folderName, Long refId, String feature, Consumer<CloudinaryReq> fn) {
        if (files != null) {
            for (MultipartFile file : files) {
                if (file.isEmpty()) continue;
                try {
                    Map data = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                            "folder",
                            folderName.toLowerCase(),
                            "resource_type",
                            "auto"
                    ));
//
                    System.out.println(data);
                    CloudinaryReq req = CloudinaryReq.builder()
                            .secureUrl((String) data.get("secure_url"))
                            .width((Integer) data.get("width"))
                            .height((Integer) data.get("height"))
                            .assetId((String) data.get("asset_id"))
                            .publicId((String) data.get("public_id"))
                            .format((String) data.get("format"))
                            .originName(file.getOriginalFilename())
                            .feature(feature)
                            .refId(refId)
                            .bytes((Integer) data.get("bytes"))
                            .build();
                    fn.accept(req);
                } catch (Exception e) {
                    throw new ApiException(e.getMessage());
                }
            }
        }
    }

    @Override
    public String deleteImageByPublicId(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId,ObjectUtils.emptyMap());
        }catch (IOException e){
            throw  new ApiException(e.getMessage());
        }
        return "delete image successfully!";
    }
}
