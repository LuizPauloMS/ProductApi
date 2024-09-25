package com.ProductApi.main.productTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.ProductApi.main.entity.ProductItem;
import com.ProductApi.main.repositories.ProductItemRepository;
import com.ProductApi.main.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductControllerTest {

	    @Autowired
	    private MockMvc mockMvc;

	    @Autowired
	    private ProductItemRepository productRepository;

	    @Autowired
	    private ProductService productService;

	    @Autowired
	    private ObjectMapper objectMapper;
	   	   
	     @Test
	     public void postProductTest() throws Exception {
	    	 
	         ProductItem productItem_1 = new ProductItem("Geladeira", "Brastemp");
	         ProductItem productItem_2 = new ProductItem("Notebook", "Samsung i3");
	         ProductItem productItem_3 = new ProductItem("TV", "Panasonic 50 Pol");

	         String productJson = objectMapper.writeValueAsString(productItem_1);

	         MvcResult result = mockMvc.perform(post("/products")
	                 .contentType(MediaType.APPLICATION_JSON)
	                 .content(productJson))
	                 .andExpect(status()
	                 .isCreated()).andReturn();
	         
	         String responseJson = result.getResponse().getContentAsString();
	         ProductItem createProduct = objectMapper.readValue(responseJson, ProductItem.class);
	         assertEquals(productItem_1.getName(), createProduct.getName());
	         assertEquals(productItem_1.getDescription(), createProduct.getDescription());
	         
	         productJson = objectMapper.writeValueAsString(productItem_2);

	         result = mockMvc.perform(post("/products")
	                 .contentType(MediaType.APPLICATION_JSON)
	                 .content(productJson))
	                 .andExpect(status()
	                 .isCreated()).andReturn();
	         
	         responseJson = result.getResponse().getContentAsString();
	         createProduct = objectMapper.readValue(responseJson, ProductItem.class);
	         assertEquals(productItem_2.getName(), createProduct.getName());
	         assertEquals(productItem_2.getDescription(), createProduct.getDescription());
	         
	         productJson = objectMapper.writeValueAsString(productItem_3);

	         result = mockMvc.perform(post("/products")
	                 .contentType(MediaType.APPLICATION_JSON)
	                 .content(productJson))
	                 .andExpect(status()
	                 .isCreated()).andReturn();
	         
	         responseJson = result.getResponse().getContentAsString();
	         createProduct = objectMapper.readValue(responseJson, ProductItem.class);
	         assertEquals(productItem_3.getName(), createProduct.getName());
	         assertEquals(productItem_3.getDescription(), createProduct.getDescription());
	     }
	     
	  
	     @Test
	     public void getProductTest() throws Exception {
	   	   
	         productService.saveProduct(productService.createProduct("Geladeira", "Brastemp"));
	         productService.saveProduct(productService.createProduct("Notebook", "Samsung i3"));
	         productService.saveProduct(productService.createProduct("TV", "Panasonic 50 Pol"));

	         mockMvc.perform(get("/products"))
		 	 .andDo(print())
	    	 .andExpect(status().isOk())
	    	 .andExpect(jsonPath("$.data.length()").value(3))
	    	 .andExpect(jsonPath("$.data[0].name").value("Geladeira"))
	    	 .andExpect(jsonPath("$.data[0].description").value("Brastemp"))
	    	 .andExpect(jsonPath("$.data[1].name").value("Notebook"))
	    	 .andExpect(jsonPath("$.data[1].description").value("Samsung i3"))
	    	 .andExpect(jsonPath("$.data[2].name").value("TV"))
	    	 .andExpect(jsonPath("$.data[2].description").value("Panasonic 50 Pol"));
	     
	     }
	     
	   
	     @Test
	     public void putProductTest() throws Exception {
	    	 productService.saveProduct(productService.createProduct("Geladeira", "Brastemp"));
	         productService.saveProduct(productService.createProduct("Notebook", "Samsung i3"));
	         productService.saveProduct(productService.createProduct("TV", "Panasonic 50 Pol"));

	         
	    	    String productJson = "{\"name\":\"Sofa\", \"description\":\"Marrom\"}";
	    	    mockMvc.perform(get("/products"))
	            .andDo(print()) 
	            .andExpect(status().isOk());
	    	    ProductItem product = productRepository.findByName("Geladeira");
	    	    mockMvc.perform(put("/products/{id}", product.getId())
	    	            .contentType(MediaType.APPLICATION_JSON)
	    	            .content(productJson))
	    	    .andExpect(status().isNoContent());
	    	    mockMvc.perform(get("/products"))
			 	 .andDo(print())
		    	 .andExpect(status().isOk())
		    	 .andExpect(jsonPath("$.data.length()").value(3))
		    	 .andExpect(jsonPath("$.data[0].name").value("Sofa"))
		    	 .andExpect(jsonPath("$.data[0].description").value("Marrom"))
		    	 .andExpect(jsonPath("$.data[1].name").value("Notebook"))
		    	 .andExpect(jsonPath("$.data[1].description").value("Samsung i3"))
		    	 .andExpect(jsonPath("$.data[2].name").value("TV"))
		    	 .andExpect(jsonPath("$.data[2].description").value("Panasonic 50 Pol"));
	     }  
}
