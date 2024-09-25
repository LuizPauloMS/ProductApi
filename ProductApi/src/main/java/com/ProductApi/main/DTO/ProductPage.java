package com.ProductApi.main.DTO;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ProductApi.main.entity.ProductItem;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ProductPage {

    @Column(nullable = false)
	private boolean hasNext = true;

    @Column(nullable = false)
	private List<ProductItem> data;
}
