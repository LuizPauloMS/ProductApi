package com.ProductApi.main.DTO;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Component
@NoArgsConstructor
public class ProductItemDto  {
    
    private int id;
	private String name;
    private String description;
	private String createdBy;
    private String lastUpdate;

	
}
