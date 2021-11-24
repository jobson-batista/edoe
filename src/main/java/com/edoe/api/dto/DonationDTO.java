package com.edoe.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DonationDTO {

    private Long id;
    private Date date;
    private UserDTO userReceptor;
    private UserDTO userDonator;
    private String description;
    private int quantity;
}
