package com.ProductApi.main.productTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ProductApi.main.DTO.ProductPage;
import com.ProductApi.main.entity.ProductItem;
import com.ProductApi.main.repositories.ProductItemRepository;
import com.ProductApi.main.services.ProductService;

@SpringBootTest
@Transactional
public class ProductServiceTest {
	
	@Autowired	
	ProductService service;
	
	@Autowired
	ProductItemRepository repository;
	
	@Autowired
	ProductPage productPage;
	Logger log;
	@Test
	void createProductTest(){
		ProductItem product = service.createProduct("Lampada", "220V");
		assertEquals("Lampada", product.getName());
		assertEquals("220V", product.getDescription());
	}
	
	@Test
	void getAllProductsTeste(){	
		service.saveProduct(service.createProduct("Lampada", "220V"));
		service.saveProduct(service.createProduct("Celular", "Apple"));
		service.saveProduct(service.createProduct("Tablet", "Galaxy"));
		service.saveProduct(service.createProduct("Tv", "Semp toshiba"));
		
		productPage = service.getAllProducts();
		assertEquals("Lampada", productPage.getData().get(0).getName());
		assertEquals("220V", productPage.getData().get(0).getDescription());
		assertEquals("Celular", productPage.getData().get(1).getName());
		assertEquals("Apple", productPage.getData().get(1).getDescription());
		assertEquals("Tablet", productPage.getData().get(2).getName());
		assertEquals("Galaxy", productPage.getData().get(2).getDescription());
		assertEquals("Tv", productPage.getData().get(3).getName());
		assertEquals("Semp toshiba", productPage.getData().get(3).getDescription());
	}
	
	@Test
	void saveProductTest(){
		service.saveProduct(service.createProduct("Lampada", "220v"));
		service.saveProduct(service.createProduct("Lanterna", "pilha AAA"));
		service.saveProduct(service.createProduct("Radio", "Am e Fm"));
		
		ProductItem product = repository.findByName("Lampada");
		assertEquals("Lampada", product.getName());
		assertEquals("220v", product.getDescription());
		
		product = repository.findByName("Lanterna");
		assertEquals("Lanterna", product.getName());
		assertEquals("pilha AAA", product.getDescription()); 
		
		product = repository.findByName("Radio");
		assertEquals("Radio", product.getName());
		assertEquals("Am e Fm", product.getDescription());
	}
}
