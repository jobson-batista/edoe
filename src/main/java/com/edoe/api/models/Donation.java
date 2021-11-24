package com.edoe.api.models;

import com.edoe.api.dto.DonationDTO;
import com.edoe.api.dto.DonationWithoutReceptorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    @OneToOne
    @JoinColumn(name = "user_receptor_email")
    private User userReceptor;

    @OneToOne
    @JoinColumn(name = "user_donator_email")
    private User userDonator;

    @Column(name = "descriptrion_item")
    private String descriptrionItem;

    private int quantity;

    public Donation(Date date, User userReceptor, User userDonator, String descriptrionItem, int quantity) {
        this.date = date;
        this.userReceptor = userReceptor;
        this.userDonator = userDonator;
        this.descriptrionItem = descriptrionItem;
        this.quantity = quantity;
    }

    public DonationDTO toDTO() {
        DonationDTO donationDTO = new DonationDTO();
        donationDTO.setDate(this.date);
        donationDTO.setId(this.id);
        donationDTO.setUserDonator(this.userDonator.toDTO());
        donationDTO.setUserReceptor(this.userReceptor.toDTO());
        donationDTO.setQuantity(this.quantity);
        donationDTO.setDescription(this.descriptrionItem);
        return donationDTO;
    }

    public DonationWithoutReceptorDTO toDTOWithoutReceptor() {
        DonationWithoutReceptorDTO dto = new DonationWithoutReceptorDTO();
        dto.setId(this.id);
        dto.setDate(this.date);
        dto.setUserDonator(this.userDonator.toDTO());
        dto.setDescription(this.descriptrionItem);
        dto.setQuantity(this.quantity);
        return dto;
    }
}
