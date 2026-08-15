package com.retroswap.RetroSwap_Backend.Model.cart;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCart {
    private int cartItemId;
    private int quantity;
}
