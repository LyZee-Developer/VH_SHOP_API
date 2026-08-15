package com.service.BVHSHOP.service.Product;

import com.service.BVHSHOP.dto.ProductDTO;
import com.service.BVHSHOP.model.Product;
import com.service.BVHSHOP.request.Product.ProductFilter;
import com.service.BVHSHOP.request.Product.ProductReq;
import com.service.BVHSHOP.service.BaseInternalService;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService extends BaseInternalService<Product, Long> {
    Page<ProductDTO> index(ProductFilter filter);
    String create(ProductReq req, List<MultipartFile> files);
    String update(Long id, ProductReq req);
    boolean checkCode(String code);
    String delete(Long id);
}
