package com.service.BVHSHOP.request.Cloudinary;

import lombok.Builder;
import lombok.Data;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/15/2026 9:24 AM
 */
@Builder
@Data
public class CloudinaryReq {
    private String secureUrl;
    private String format;
    private String assetId;
    private String originName;
    private String publicId;
    private Integer bytes;
    private Long refId;
    private String feature;
    private Integer width;
    private Integer height;
}
