package com.ProductApi.main.controllers;

import java.util.logging.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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
import com.ProductApi.main.repositories.ProductItemRepository;
import com.ProductApi.main.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	ProductService productService;
    ProductItemRepository productRepository;
	ProductItem product;
    private static final Logger log = Logger.getLogger(ProductController.class.getName());	    
    
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<ProductItem> createProduct(@RequestBody ProductItem product) {
	    try {
	        productService.createProduct(product);
	        return ResponseEntity.ok(product);
	    } catch (DataAccessException e) {
	        log.info("Erro ao criar produto: {}");
	        return ResponseEntity.notFound().build();
	    } catch (ServiceException e) {
	        log.info("Erro ao criar produto");
	        return ResponseEntity.internalServerError().build();
	    }
	}
		
	@GetMapping()
    public ResponseEntity<ProductPage> productList()  {
		ProductPage productPage = new ProductPage();
		productPage.setData(productService.findProducts() );
		if(productPage.getData().isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(productPage);
    }

	@PutMapping("{productId}")
	public ResponseEntity<ProductItem> editProduct(@PathVariable String productId, @RequestBody @Valid ProductItem product) throws NotFoundException {
		productService.makeUpdate(productId, product);
		return ResponseEntity.noContent().build();
	}
}
