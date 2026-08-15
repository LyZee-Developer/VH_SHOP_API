package com.service.BVHSHOP.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/12/2026 9:20 PM
 */
@Getter
@Setter
public abstract class BaseUploadFile {
    private List<MultipartFile> files;
}
