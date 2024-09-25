package com.ProductApi.main.entity;

import java.io.Serializable;

import com.ProductApi.main.services.ProductService;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class ProductItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = true)
	private String description;

    @Column(nullable = true)
    @Setter(AccessLevel.NONE)
    private String createdAt;

    @Column(nullable = true)
    @Setter(AccessLevel.NONE)
    private String lastUpdate;

    @Column(nullable = true)
    private String createdBy;

    @Column(nullable = true)
    private String lastUpdatedBy;

    public void setLastUpdate(String lastUpdate) {
 		this.lastUpdate = ProductService.getCurrentDateTime();
    }

    public void setCreatedAt(String createAt) {
    	this.createdAt = ProductService.getCurrentDateTime();
    }

	public ProductItem(@NotBlank String name, String description) {
		this.name = name;
		this.description = description;
		this.createdAt = ProductService.getCurrentDateTime();
	}

}



