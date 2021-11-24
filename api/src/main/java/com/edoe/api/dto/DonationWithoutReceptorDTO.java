package com.edoe.api.dto;

import lombok.Data;
import java.util.Date;

@Data
public class DonationWithoutReceptorDTO implements Comparable<DonationWithoutReceptorDTO> {

    private Long id;
    private Date date;
    private UserDTO userDonator;
    private String description;
    private int quantity;

    @Override
    public int compareTo(DonationWithoutReceptorDTO donation) {
        if(this.date.after(donation.getDate())) {
            return 1;
        }
        if(this.date.before(donation.getDate())) {
            return -1;
        }
        return 0;
    }
}