package com.ProductApi.main.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ProductApi.main.DTO.ProductItemDto;
import com.ProductApi.main.repositories.ProductItemRepository;
import com.ProductApi.main.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	
	ProductService productService;
	ProductItemRepository productRepository;
	ProductItemDto product;
    private static final Logger log = Logger.getLogger(ProductController.class.getName());	

    
    
	public ProductController(ProductItemDto product, ProductService productService) {
		this.product = product;
		this.productService = productService;
	}

	
	
	@PostMapping
	@ResponseBody
    public ResponseEntity<ProductItemDto> createProduct(@RequestBody ProductItemDto product) {
		try {
		ProductService.createProduct(product);
    	   return ResponseEntity.ok(product);
		}catch(Exception e) {
    	   log.info("Erro ao criar produto");
    	   return ResponseEntity.notFound().build();      }
    }
	
	
	
	@GetMapping()
    public ResponseEntity<List<ProductItemDto>> productList()  {
		List<ProductItemDto> list = productService.findProducts();
		if(list.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(list);
    }

	
	
	@PutMapping("{productId}")
	public ResponseEntity<ProductItemDto> editProduct(@PathVariable String productId, @RequestBody @Valid ProductItemDto product) {
	    try {
	   	   	ProductService.makeUpdate(productId, product);
	        return ResponseEntity.noContent().build();
	    }catch(Exception e) {
	    	log.info("Produto não encontrado");
	        return ResponseEntity.notFound().build();
	    }
	}
}
