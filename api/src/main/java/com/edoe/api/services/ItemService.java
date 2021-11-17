package com.edoe.api.services;

import com.edoe.api.exceptions.BadRequestException;
import com.edoe.api.exceptions.ForbiddenException;
import com.edoe.api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edoe.api.models.Item;
import com.edoe.api.repositories.ItemRepository;

import javax.servlet.ServletException;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepo;

	@Autowired
	private UserService user;
	
	
	public Item createItem(Item item, String token) throws ServletException {

		if(!user.isDonor(token) && !user.isAdmin(token)){
			throw new ForbiddenException();
		}

		if(itemExists(item)) {
			throw new BadRequestException("Item already registered", "Repeated items are not allowed.");
		}

		User u = user.getUserByToken(token);
		item.setUser(u);

		return itemRepo.save(item);
	}

	private boolean itemExists(Item item) {
		for(Item i : itemRepo.findAll()) {
			if(item.getDescription().equals(i.getDescription()) || item.getDescriptor().getDescriptor().equals(i.getDescriptor().getDescriptor())) {
				return true;
			}
		}
		return false;
	}
}

