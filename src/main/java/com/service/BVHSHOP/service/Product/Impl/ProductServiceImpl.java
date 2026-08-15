package com.service.BVHSHOP.service.Product.Impl;

import com.service.BVHSHOP.dto.ProductDTO;
import com.service.BVHSHOP.exception.ApiException;
import com.service.BVHSHOP.model.*;
import com.service.BVHSHOP.repository.FileRepository;
import com.service.BVHSHOP.repository.ProductRepository;
import com.service.BVHSHOP.request.Product.ProductFilter;
import com.service.BVHSHOP.request.Product.ProductReq;
import com.service.BVHSHOP.service.Category.CategoryService;
import com.service.BVHSHOP.service.DataRef.DataRefService;
import com.service.BVHSHOP.service.Impl.BaseInternalActivateServiceImpl;
import com.service.BVHSHOP.service.Product.ProductPriceService;
import com.service.BVHSHOP.service.Product.ProductService;
import com.service.BVHSHOP.service.ProductType.ProductTypeService;
import com.service.BVHSHOP.service.file.UploadService;
import com.service.BVHSHOP.specification.ProductSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
class ProductServiceImpl extends BaseInternalActivateServiceImpl<Product, Long> implements ProductService {

    private final ProductRepository productRepository;
    @Autowired
    ProductTypeService productTypeService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    ProductPriceService productPriceService;

    @Autowired
    DataRefService dataRefService;

    @Autowired
    UploadService uploadService;

    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository, Product.class);
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDTO> index(ProductFilter filter) {
        Pageable page = PageRequest.of(filter.getPage(), filter.getSize());
        Specification<Product> spec = new ProductSpecification(filter);
        Page<Product> result = findAllSpePageFetch(spec, page, filter.getFetch());

        List<Long> productIds = result.getContent().stream().map(Product::getId).toList();
        List<File> allFiles = productIds.isEmpty()
                ? Collections.emptyList()
                : fileRepository.findByRefIdIn(productIds);

        Map<Long, List<String>> imagesByRefId = allFiles.stream()
                .collect(Collectors.groupingBy(
                        File::getRefId,
                        Collectors.mapping(File::getSecureURL, Collectors.toList())
                ));

        return result.map(pro -> new ProductDTO(pro, imagesByRefId.getOrDefault(pro.getId(), Collections.emptyList())));
    }

    @Override
    @Transactional
    public String create(ProductReq req, List<MultipartFile> files) {
        if (checkCode(req.getCode())) {
            throw new ApiException("code already existed!");
        }

        if (req.getPrices().isEmpty()) {
            throw new ApiException("Please enter price to each items");
        }
        DataRef dataRef = dataRefService.findByCodeThrow(req.getCurrencyCode());
        Category cate = categoryService.findOneThrow(req.getCategoryId());

        Product pro = new Product();
        pro.setProductType(productTypeService.findById(req.getProductTypeId()).orElse(null));
        pro.setName(req.getName());
        pro.setCurrency(dataRef);
        pro.setEnglishName(req.getEnglishName());
        pro.setCode(req.getCode());
        pro.setCategory(cate);
        saveData(pro);

        try {
            uploadService.saveMultiFile(files, cate.getEnglishName().toLowerCase(), pro.getId(), cate.getEnglishName().toUpperCase());
        } catch (IOException ex) {
            throw new ApiException(ex.getMessage());
        }

        // save product Item
        productPriceService.saveAllPrice(req.getPrices(), pro);
        return "create success";
    }

    @Override
    @Transactional
    public String update(Long id, ProductReq req) {
        Category cate = categoryService.findOneThrow(req.getCategoryId());
        DataRef dataRef = dataRefService.findByCodeThrow(req.getCurrencyCode());

        Product pro = findThrowById(id);

        Long oldProductTypeId = Optional.ofNullable(pro.getProductType())
                .map(ProductType::getId)
                .orElse(null);

        Long newProductTypeId = req.getProductTypeId();

        if (!Objects.equals(newProductTypeId, oldProductTypeId)) {
            pro.getProductPrice().clear();
        }

        pro.setName(req.getName());
        pro.setCurrency(dataRef);
        pro.setEnglishName(req.getEnglishName());
        pro.setCategory(cate);
        pro.setProductType(Optional.ofNullable(req.getProductTypeId()).map(productTypeService::findThrowById).orElse(null));
        saveData(pro);

        productPriceService.saveAllPrice(req.getPrices(), pro);

        return "update success";
    }

    @Override
    public boolean checkCode(String code) {
        return productRepository.existsByCodeAndIsActivateTrue(code);
    }

    @Override
    public String delete(Long id) {
        Product pro = findThrowById(id);
        pro.setIsActivate(Boolean.FALSE);
        saveData(pro);
        return "delete successfully!";
    }
}
