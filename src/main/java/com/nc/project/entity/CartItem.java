package com.nc.project.entity;

//import com.nc.project.dto.CartItemDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nc.project.dto.CartItemDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

//@IdClass(CartItemId.class)
public class CartItem {

    // 키 값 (pk)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private long cartItemId;

    // cart와 ManyToOne 관계 (fk)
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    // item과 ManyToOne 관계 (fk)
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "cart_item_cnt")
    private int cartItemCnt;

    public CartItemDTO toDTO() {
        return CartItemDTO.builder()
                .cartItemId(this.cartItemId)
                .cartId(this.cart.getCartId())
                .itemId(this.item.getItemId())
                .cartItemCnt(this.cartItemCnt)
                .build();
    }
    // 장바구니에 담을 상품 엔티티를 생성하는 메소드
    public static CartItem createCartItem (Cart cart, Item item, int cartItemCnt) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCartItemCnt(cartItemCnt);
        return cartItem;
    }


    // 기존 담겨있는 상품을 추가로 담으면 기존수량에 + 됨
    public void addCount(int cartItemCnt) {
        this.cartItemCnt += cartItemCnt;
    }


}
