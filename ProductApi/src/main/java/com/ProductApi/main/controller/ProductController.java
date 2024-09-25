package com.ProductApi.main.controller;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ProductApi.main.DTO.ProductPage;
import com.ProductApi.main.entity.ProductItem;
import com.ProductApi.main.exception.customExceptionsClass.EmptyDatabaseException;
import com.ProductApi.main.repositories.ProductItemRepository;
import com.ProductApi.main.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
		
	ProductService productService;
	ProductItemRepository productRepository;
    ProductItem product;
		
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<ProductItem> createProduct(@RequestBody @Valid ProductItem product){
		   productService.saveProduct(
				   productService.createProduct(product.getName(), product.getDescription()));
		   return new ResponseEntity<>(product,HttpStatus.CREATED);		        
	}
			
	@GetMapping()
	public ResponseEntity<ProductPage> productList(){
		if(productService.getAllProducts().getData().isEmpty()) throw new EmptyDatabaseException();
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);	    
	}

	@PutMapping("{productId}")
	public ResponseEntity<ProductItem> editProduct(@PathVariable int productId, @RequestBody @Valid ProductItem product) throws NotFoundException {
		productService.makeUpdate(productId, product);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);		
	}
}
