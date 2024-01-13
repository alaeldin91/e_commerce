package com.alaeldin.productservice.service.productImpl;

import com.alaeldin.productservice.exception.resourcenotfound.ResourceNotFoundException;
import com.alaeldin.productservice.mapper.ProductMapper;
import com.alaeldin.productservice.dto.ProductDto;
import com.alaeldin.productservice.exception.existdata.ProductAlreadyExistsException;
import com.alaeldin.productservice.model.Product;
import com.alaeldin.productservice.repository.ProductRepository;
import com.alaeldin.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = ProductMapper.mapToProduct(productDto);
        Optional<Product> optionalProduct = productRepository.findByName(productDto.getName());
        if (optionalProduct.isPresent()){

            throw new ProductAlreadyExistsException("Product Already Exist");
        }
        Product productSave = productRepository.save(product);
        return ProductMapper.mapToProductDto(productSave);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product existProduct = productRepository.findById(productDto.getId()).orElseThrow(
                ()->new ResourceNotFoundException("product","id",productDto.getId()));
        existProduct.setId(productDto.getId());
        existProduct.setName(productDto.getName());
        existProduct.setDescription(productDto.getDescription());
        existProduct.setPrice(productDto.getPrice());
        Product updateProduct = productRepository.save(existProduct);
        return ProductMapper.mapToProductDto(updateProduct);
    }

    @Override
    public void deleteProduct(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(()->
                new ResourceNotFoundException("product","product Id",productId));
        productRepository.delete(product);
    }

    @Override
    public Page<ProductDto> getAllProducts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage.map(ProductMapper::mapToProductDto);
    }

    @Override
    public ProductDto getProductById(String productId) {
       Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()){

            throw new ResourceNotFoundException("product", "productId", productId);
        }

        return ProductMapper.mapToProductDto(product.get());
    }

    @Override
    public ProductDto getProductByName(String name) {

        Product product = productRepository.findByName(name).orElseThrow(()
                ->new ResourceNotFoundException(
                "product","productName",name
        ));

        return ProductMapper.mapToProductDto(product);
    }
}
