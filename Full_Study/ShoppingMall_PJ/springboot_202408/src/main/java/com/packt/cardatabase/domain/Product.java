package com.packt.cardatabase.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
    @Id
    private String id;

    private String name;

    private String explanation;

    private Long price;

    private String thumbnail;

    @Column(nullable = true)
    private Long discount;
}
