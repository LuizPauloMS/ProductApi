package com.ProductApi.main.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ProductApi.main.entity.ProductItem;
import com.ProductApi.main.repositories.ProductItemRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	ProductItemRepository productRepository;
	
	public ProductController(ProductItemRepository productRepository) {
		this.productRepository = productRepository;
	}

	@PostMapping
	@ResponseBody
    public ResponseEntity<ProductItem> createProduct(@RequestBody ProductItem productItem) {
		
		try {
			
    	   productRepository.save(productItem);
    	   return ResponseEntity.ok(productItem);
       }catch(Exception e) {
    	   return ResponseEntity.noContent().build();
       }
	   
    }
	
	@GetMapping()
    public ResponseEntity<List<ProductItem>> productList() {
		try {
			List<ProductItem> listProducts = productRepository.findAll();
			return ResponseEntity.ok(listProducts);
		} catch(Exception e){
			return ResponseEntity.noContent().build();		}
		
    }

	@PutMapping("{productId}")
	public ResponseEntity<ProductItem> editProduct(@PathVariable String productId, @RequestBody @Valid ProductItem productItem) {
	    Optional<ProductItem> idProduct = productRepository.findById(productId);
	    
	   
	    if (idProduct.isPresent()) {
	    
	    	LocalDateTime currentDateTime = LocalDateTime.now();
	 		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
	 	
	        ProductItem product = idProduct.get();

	        product.setName(productItem.getName()); 
	        product.setDescription(productItem.getDescription());
	        product.setCreatedAt(productItem.getCreatedAt());
	        product.setCreatedBy(productItem.getCreatedBy());
	        product.setLastUpdate(currentDateTime.format(formatter));
	        product.setLastUpdatedBy(productItem.getLastUpdatedBy());
	        productRepository.save(product); 
	        return ResponseEntity.noContent().build();
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
