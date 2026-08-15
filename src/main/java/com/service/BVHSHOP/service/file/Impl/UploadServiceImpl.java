package com.service.BVHSHOP.service.file.Impl;

import com.service.BVHSHOP.exception.ApiException;
import com.service.BVHSHOP.model.File;
import com.service.BVHSHOP.repository.FileRepository;
import com.service.BVHSHOP.service.Cloudinary.Impl.CloudinaryServiceImpl;
import com.service.BVHSHOP.service.file.UploadService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/12/2026 9:24 PM
 */
@Service
class UploadServiceImpl extends CloudinaryServiceImpl implements UploadService {
    @Autowired
    FileRepository fileRepository;

    @Override
    public String deleteImage(Long id) {
        File file = fileRepository.findById(id).orElseThrow(() -> new ApiException("Image not fround"));
        String result = deleteImageByPublicId(file.getPublicId());
        fileRepository.delete(file);
        return result;
    }

    @Override
    @Transactional
    public String saveMultiFile(List<MultipartFile> files, String folderName, Long refId, String feature) {
        if (files != null) {
            saveImageToCloud(files, folderName, refId, feature, cloudinaryReq -> {
                File data = File.builder()
                        .bytes(cloudinaryReq.getBytes())
                        .width(cloudinaryReq.getWidth())
                        .height(cloudinaryReq.getHeight())
                        .originName(cloudinaryReq.getOriginName())
                        .assetId(cloudinaryReq.getAssetId())
                        .refId(cloudinaryReq.getRefId())
                        .secureURL(cloudinaryReq.getSecureUrl())
                        .publicId(cloudinaryReq.getPublicId())
                        .feature(cloudinaryReq.getFeature().toUpperCase())
                        .fileType(cloudinaryReq.getFormat())
                        .build();
                fileRepository.save(data);
            });
        }
        return "Ok";
    }

    @Override
    public List<File> getImageByObjectId(Long id) {
        return fileRepository.findByRefId(id);
    }
}
