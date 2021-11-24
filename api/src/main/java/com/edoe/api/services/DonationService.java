package com.edoe.api.services;

import com.edoe.api.dto.ItemDTO;
import com.edoe.api.enums.Role;
import com.edoe.api.exceptions.BadRequestException;
import com.edoe.api.models.Donation;
import com.edoe.api.models.Item;
import com.edoe.api.models.User;
import com.edoe.api.repositories.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.ServletException;
import java.util.Date;


@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    public Donation donate(String token, Long idItemRequired, Long idItemDonation, int quantity) throws ServletException {
        User user = userService.getUserByToken(token);
        if(!(user.getRole().equals(Role.DOADOR_RECEPTOR) || user.getRole().equals(Role.APENAS_RECEPTOR)))
            throw new BadRequestException("User cannot request a donation","Only recipient users can request a donation.");
        Item itemDonation = itemService.getItemById(idItemDonation);
        Item itemRequired = itemService.getItemById(idItemRequired);
        if(!itemDonation.getDescriptor().equals(itemRequired.getDescriptor()))
            throw new BadRequestException("Different descriptors","Descriptors must be the same.");
        if(itemDonation.getQuantity() < quantity)
            throw new BadRequestException("Insufficient items","Quantity of items requested cannot be less than items for donation.");
        itemDonation.setQuantity(itemDonation.getQuantity() - quantity);
        itemRequired.setQuantity(itemRequired.getQuantity() - quantity);
        if(itemDonation.getQuantity() <= 0) itemService.removeItemWithoutToken(itemDonation);
        if(itemRequired.getQuantity() <= 0) itemService.removeItemWithoutToken(itemRequired);
        Donation donation = new Donation(new Date(), itemRequired.getUser(), itemDonation.getUser(), itemDonation.getDescription(), quantity);
        return donationRepository.save(donation);
    }

    public List<DonationWithoutReceptorDTO> findAllDonationsWithoutReceptor() {
        List<DonationWithoutReceptorDTO> donations = new ArrayList<>();
        for(Donation d: donationRepository.findAll()) {
            donations.add(d.toDTOWithoutReceptor());
        }
        Collections.sort(donations);
        return donations;
    }
}
