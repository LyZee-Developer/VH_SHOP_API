package com.service.BVHSHOP.repository;

import com.service.BVHSHOP.model.File;

import java.util.List;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/12/2026 6:30 AM
 */
public interface FileRepository extends BaseInternalActivateRepository<File, Long> {
    List<File> findByRefId(Long refId);
    List<File> findByRefIdIn(List<Long> refId);
}
