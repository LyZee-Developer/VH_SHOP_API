package com.service.BVHSHOP.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/12/2026 6:25 AM
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class File extends ActivateEntityModel {
    @Column(nullable = false)
    private Integer bytes;

    @Column(nullable = false)
    private String originName;

    private String secureURL;
    private String publicId;

    @Column(nullable = false)
    private String fileType;

    @Column(nullable = false)
    private String feature;

    @Column(nullable = false)
    private String assetId;

    @Column(nullable = false)
    private Long refId;

    @Column(nullable = false)
    private Integer width;

    @Column(nullable = false)
    private Integer height;
}
