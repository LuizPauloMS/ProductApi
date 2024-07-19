package com.ProductApi.main.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import com.ProductApi.main.DTO.ProductPage;
import com.ProductApi.main.entity.ProductItem;
import com.ProductApi.main.exception.customExceptionsClass.EmptyDatabaseException;
import com.ProductApi.main.exception.customExceptionsClass.ProductNotFoundException;
import com.ProductApi.main.repositories.ProductItemRepository;

@Service
public class ProductService {

    private ProductItemRepository productRepository;
   
    ProductService(ProductItemRepository productRepository){
    	this.productRepository = productRepository;
    }

    public ProductPage getProducts() {
    	if(productRepository.findAll().isEmpty()) throw new EmptyDatabaseException();
        ProductPage productPage = new ProductPage();
    	productPage.setData(productRepository.findAll());
        return productPage;
    }
   

	public void createProduct(ProductItem productItem) {
			ProductItem product = new ProductItem();
			product.setName(productItem.getName());
			product.setDescription(productItem.getDescription());
			product.setCreatedAt(getCurrentDateTime());
			productRepository.save(product);
	}

	public void makeUpdate( String productId, ProductItem product) throws NotFoundException {
	    	Optional<ProductItem> idProduct = productRepository.findById(productId);

	    	if (!idProduct.isPresent()) {
	    		throw new ProductNotFoundException();
	    	}

	    		ProductItem productItem = idProduct.get();
	    		productItem.setName(product.getName());
	    		productItem.setDescription(product.getDescription());
	    		productItem.setLastUpdate(getCurrentDateTime());
	    		productRepository.save(productItem);
	}

	public static String getCurrentDateTime() {
		LocalDateTime currentDateTime = LocalDateTime.now();
 		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
 		return currentDateTime.format(formatter);
	}
}
