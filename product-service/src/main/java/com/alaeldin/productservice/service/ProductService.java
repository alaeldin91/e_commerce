package com.alaeldin.productservice.service;

import com.alaeldin.productservice.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


public interface ProductService {

    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(ProductDto productDto);
    void deleteProduct(String productId);
    Page<ProductDto> getAllProducts(int pageNumber, int pageSize);
    ProductDto getProductById(String productId);
    ProductDto getProductByName(String name);

}
