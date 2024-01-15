package com.alaeldin.productservice;

import com.alaeldin.productservice.dto.ProductDto;
import com.alaeldin.productservice.mapper.ProductMapper;
import com.alaeldin.productservice.model.Product;
import com.alaeldin.productservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = ProductServiceApplication.class)
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0");

	@Autowired
	public   MockMvc mockMvc;
	@Autowired
	public ObjectMapper objectMapper;
	@Autowired
	public   ProductRepository productRepository;


	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldCreateProduct() throws Exception {
		ProductDto productDto = getProduct();
		String productRequestString = objectMapper.writeValueAsString(productDto);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString)
		).andExpect(status().isCreated());

		assertEquals(1, productRepository.findAll().size());
	}
	@Test
	void shouldGetAllTest() throws Exception {
		List <ProductDto> products = getAllProduct();
		System.out.println("products"+"  "+products);
		String productString = objectMapper.writeValueAsString(products);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/products?pageNumber=0&pageSize=6")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productString)).andExpect(status().isOk());
		assertEquals(2,products.size());
		System.out.println("products"+"  "+productString);
	}
	@Test
	void shouldGetProductById() throws Exception {
   ProductDto saveProduct = saveProduct();
		String stringProduct = objectMapper.writeValueAsString(saveProduct);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/by_id?id={id}",saveProduct.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(stringProduct))
				.andExpect(status().isOk());
		System.out.println("Save Product"+"  "+saveProduct);

	}

	@Test
	void shouldDeleteProduct() throws Exception {
		ProductDto saveProduct = saveProduct();
		String stringSaveProduct = objectMapper.writeValueAsString(saveProduct);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/delete/{id}",saveProduct.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(stringSaveProduct)).andExpect(status().isOk());
		assertEquals(0,productRepository.findAll().size());
	}
	@Test
	void shouldUpdateProduct() throws Exception {
	ProductDto saveProduct = saveProduct();
	String saveProductString = objectMapper.writeValueAsString(saveProduct);
	mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product/update_product/{id}",saveProduct.getId())
			.contentType(MediaType.APPLICATION_JSON)
			.content(saveProductString)).andExpect(status().isOk());
	}
	private ProductDto saveProduct(){
		ProductDto productDto = getProduct();
		Product product = ProductMapper.mapToProduct(productDto);
		Product saveProduct = productRepository.save(product);
        return ProductMapper.mapToProductDto(saveProduct);
	}
	private ProductDto getProduct() {
		return ProductDto.builder()
				.name("Telephone Nokia 301")
				.description("This is a beautiful telephone, iPhone")
				.price(BigDecimal.valueOf(12000))
				.build();
	}
	private List<ProductDto> getAllProduct(){
		List<ProductDto> products = new ArrayList<>();
		products.add(ProductDto.builder().name("Iphone")
				.description("this is Iphone Phone")
				.price(BigDecimal.valueOf(1000))
				.build());
		products.add(ProductDto.builder()
				.name("Samsung").description("this phone is good")
				.price(BigDecimal.valueOf(6000)).build());
	System.out.println("Number of products: " + products.size());

	return products;
	}

}
