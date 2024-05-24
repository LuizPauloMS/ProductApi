package com.ProductApi.main.entity;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class ProductItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	
    @Column(nullable = false)
	private String name;
	
    @Column(nullable = true)
    private String description;
	
    @Column(nullable = true)
    private String createdAt;
	
    @Column(nullable = true)
    @Setter(AccessLevel.NONE)
    private String lastUpdate;

    @Column(nullable = true)
    private String createdBy;

    @Column(nullable = true)
    private String lastUpdatedBy;
    
    public void setLastUpdate(String lastUpdate) {
    	LocalDateTime currentDateTime = LocalDateTime.now();
 		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
 		this.lastUpdate = currentDateTime.format(formatter);
    }
}

