package com.ProductApi.main.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.ProductApi.main.DTO.ProductItemDto;
import com.ProductApi.main.controllers.ProductController;
import com.ProductApi.main.entity.ProductItem;
import com.ProductApi.main.repositories.ProductItemRepository;

@Service
public class ProductService {
	
    private static ProductItemRepository productRepository;
    private static final Logger log = Logger.getLogger(ProductController.class.getName());	
	
    ProductService(ProductItemRepository productRepository){
    	ProductService.productRepository = productRepository;
    }
    
    public List<ProductItemDto> findProducts() {
    	List<ProductItem> listProductItem = productRepository.findAll();
		List<ProductItemDto> listProductItemDto = new ArrayList<ProductItemDto>();
    	try {
    		for(ProductItem product : listProductItem) {
    			ProductItemDto productDto = new ProductItemDto();
    			productDto.setName(product.getName());
    			productDto.setDescription(product.getDescription());
    			productDto.setCreatedBy(product.getCreatedBy());
    			productDto.setId(product.getId());
    			productDto.setLastUpdate(product.getLastUpdate());
    			listProductItemDto.add(productDto);
    		}
    		return listProductItemDto;
    	}catch(Exception e) {
    		if(listProductItem.isEmpty()) {
    			log.info("lista vazia");
    		}
    		log.info("Erro find Products");
    		return null;
    	}
	}
	
	public static void createProduct(ProductItemDto productItemDto) {
		try{
			ProductItem product = new ProductItem();
			product.setName(productItemDto.getName());
			product.setDescription(productItemDto.getDescription());
			product.setCreatedBy(productItemDto.getCreatedBy());
			product.setCreatedAt(getCurrentDateTime());
			productRepository.save(product);
		}catch(Exception e) {
			log.info("Erro creatProduct");
		}
	}
	
	public static void makeUpdate( String productId, ProductItemDto productItemDto) {
	    try {
	    	Optional<ProductItem> idProduct = productRepository.findById(productId);
	    	if (idProduct.isPresent()) {   	
	    	
	    		ProductItem productItem = new ProductItem();
	    		productItem.setName(productItemDto.getName());
	    		productItem.setDescription(productItemDto.getDescription());;
	    		productItem.setCreatedBy(productItemDto.getCreatedBy());
	    		productItem.setLastUpdate(getCurrentDateTime());
	    		
	    		ProductItem product = idProduct.get();	
	    		product.setName(productItem.getName()); 
	    		product.setDescription(productItem.getDescription());
	    		product.setLastUpdate(getCurrentDateTime());
	    		product.setCreatedBy(productItem.getCreatedBy());
	    
	    		productRepository.save(product); 
	    	}
	    }catch(Exception e) {
	    	log.info("Erro make Update");
	    }
	}
	
	public static String getCurrentDateTime() {
		LocalDateTime currentDateTime = LocalDateTime.now();
 		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
 		return currentDateTime.format(formatter);
	}
	
}	
	