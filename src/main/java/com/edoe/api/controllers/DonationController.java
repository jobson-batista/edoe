package com.edoe.api.controllers;

import com.edoe.api.dto.DonationDTO;
import com.edoe.api.dto.DonationWithoutReceptorDTO;
import com.edoe.api.services.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.List;

@RestController
@RequestMapping("/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @GetMapping
    public ResponseEntity<DonationDTO> donate(@RequestParam Long idItemRequired, @RequestParam Long idItemDonation, @RequestParam int quantity, @RequestHeader("Authorization") String token) throws ServletException {
        return new ResponseEntity(donationService.donate(token, idItemRequired,idItemDonation,quantity).toDTO(), HttpStatus.OK);
    }

    @GetMapping("/history")
    public ResponseEntity<List<DonationWithoutReceptorDTO>> findAllDonationsWithoutReceptor() {
        return new ResponseEntity(donationService.findAllDonationsWithoutReceptor(), HttpStatus.OK);
    }
}
