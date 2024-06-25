package com.ProductApi.main.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import com.ProductApi.main.entity.ProductItem;
import com.ProductApi.main.exception.customExceptionsClass.EmptyDatabaseException;
import com.ProductApi.main.exception.customExceptionsClass.ProductNotFoundException;
import com.ProductApi.main.repositories.ProductItemRepository;

@Service
public class ProductService {

    private static ProductItemRepository productRepository;

    ProductService(ProductItemRepository productRepository){
    	ProductService.productRepository = productRepository;
    }

    public List<ProductItem> getProducts() {
    	if(productRepository.findAll().isEmpty()) {
          	throw new EmptyDatabaseException("Banco de dados vazio");
        }
    	List<ProductItem> listProductItem = productRepository.findAll();
        return listProductItem;
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
	    		throw new ProductNotFoundException("Produto não encontrado");
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
