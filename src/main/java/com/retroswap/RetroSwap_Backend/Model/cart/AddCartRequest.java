package com.retroswap.RetroSwap_Backend.Model.cart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCartRequest {

    private Long productId;
    private int quantity;
}
