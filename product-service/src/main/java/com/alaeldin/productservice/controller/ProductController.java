package com.alaeldin.productservice.controller;

import com.alaeldin.productservice.dto.ProductDto;
import com.alaeldin.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController()
@RequestMapping("/api/v1/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){

        ProductDto productSaveDto = productService.createProduct(productDto);

       return new ResponseEntity<>(productSaveDto,HttpStatus.CREATED);

    }
   @GetMapping(value = "/products",params = {"pageNumber","pageSize"})
   public ResponseEntity<Page<ProductDto>> getAllProducts(@RequestParam("pageSize")int pageSize,
   @RequestParam("pageNumber") int pageNumber){

        Page<ProductDto> productDtoPage = productService.getAllProducts(pageNumber,pageSize);

        return new ResponseEntity<>(productDtoPage,HttpStatus.OK);
   }

   @GetMapping(value = "by_id",params = {"id"})
   public  ResponseEntity<ProductDto>getProductById(@RequestParam("id") String productId){

        ProductDto productDto = productService.getProductById(productId);

        return new ResponseEntity<>(productDto,HttpStatus.OK);
   }

   @GetMapping(value = "by_name",params = {"name"})
    public  ResponseEntity<ProductDto> getProductName(@RequestParam("name")String name){

        ProductDto productDto = productService.getProductByName(name);

        return new ResponseEntity<>(productDto,HttpStatus.OK);
   }


   @PostMapping("update_product/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id")String id
           ,@RequestBody ProductDto productDto){
        productDto.setId(id);
        ProductDto saveProduct = productService.updateProduct(productDto);
        return new ResponseEntity<>(saveProduct,HttpStatus.OK);

   }

   @GetMapping("delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id")String id){

        productService.deleteProduct(id);

        return new ResponseEntity<>("Successfully Delete Product",HttpStatus.OK);
   }
}
