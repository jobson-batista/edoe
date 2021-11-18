package com.edoe.api.dto;

import com.edoe.api.enums.ItemType;
import com.edoe.api.models.Descriptor;
import lombok.Data;

@Data
public class ItemDTO {

    private Long id;

    private ItemType type;

    private int quantity;

    private String description;

    private Descriptor descriptor;

    private UserDTO user;
}
