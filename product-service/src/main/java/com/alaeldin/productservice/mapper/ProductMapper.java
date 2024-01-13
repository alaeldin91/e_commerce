package com.alaeldin.productservice.mapper;

import com.alaeldin.productservice.dto.ProductDto;
import com.alaeldin.productservice.model.Product;

public class ProductMapper {

    public static Product mapToProduct(ProductDto productDto){

        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        return product;

    }

    public static ProductDto mapToProductDto(Product product){

        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());

        return productDto;
    }
}
