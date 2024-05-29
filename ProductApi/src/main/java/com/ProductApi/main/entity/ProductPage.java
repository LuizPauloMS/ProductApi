package com.ProductApi.main.entity;

import java.util.List;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPage {

    @Column(nullable = false)
	private boolean hasNext;
    
    @Column(nullable = false)
	private List<ProductItem> data;
}
