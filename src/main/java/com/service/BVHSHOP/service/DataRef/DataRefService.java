package com.service.BVHSHOP.service.DataRef;

import com.service.BVHSHOP.model.DataRef;

import java.util.List;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/10/2026 6:24 AM
 */
public interface DataRefService {
    DataRef findByCodeThrow(String code);
    List<DataRef> getListByParentCode(String code);
}
