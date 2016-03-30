package com.epam.pdp.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_lines")
public class OrderLine {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "item")
    private String item;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "quantity")
    private Integer quantity;
}
