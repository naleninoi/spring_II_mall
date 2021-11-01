package ru.gb.java1154.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cart_positions")
@Data
public class CartPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public static CartPosition create(Product product) {
        CartPosition cartPosition = new CartPosition();
        cartPosition.setProduct(product);
        cartPosition.setQuantity(1);
        return cartPosition;
    }
}
