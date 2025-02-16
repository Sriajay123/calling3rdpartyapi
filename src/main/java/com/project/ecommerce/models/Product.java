package com.project.ecommerce.models;


import jakarta.persistence.*;
import lombok.Data;




@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private double price;
    private String description;
    private String image;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;


}
