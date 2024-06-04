package com.ProductApi.main.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

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
    
    public List<ProductItem> findProducts() {
        List<ProductItem> listProductItem = productRepository.findAll();
        if (listProductItem.isEmpty()) {
            log.info("Lista de produtos está vazia");
        }
        return listProductItem;
    }
    
	public void createProduct(ProductItem productItem) {
		try{
			ProductItem product = new ProductItem();
			product.setName(productItem.getName());
			product.setDescription(productItem.getDescription());
			product.setCreatedBy("default");
			product.setCreatedAt(getCurrentDateTime());
			productRepository.save(product);
		}catch(Exception e) {
			log.info("Erro ao criar produto");
		}
	}
	
	public void makeUpdate( String productId, ProductItem product) throws NotFoundException {
	    	Optional<ProductItem> idProduct = productRepository.findById(productId);
	    	if (idProduct.isPresent()) {   	
	    		ProductItem productItem = idProduct.get();
	    		productItem.setName(product.getName());
	    		productItem.setDescription(product.getDescription());
	    		productItem.setLastUpdate(getCurrentDateTime());
	    		productRepository.save(productItem);
	    	} else{
	    		log.info("Produto não encontrado");
	    		throw new NotFoundException();
	    }
	}
	
	public static String getCurrentDateTime() {
		LocalDateTime currentDateTime = LocalDateTime.now();
 		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
 		return currentDateTime.format(formatter);
	}
	
}	
	