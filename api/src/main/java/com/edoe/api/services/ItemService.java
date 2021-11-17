package com.edoe.api.services;

import com.edoe.api.enums.ItemType;
import com.edoe.api.enums.Role;
import com.edoe.api.exceptions.ForbiddenException;
import com.edoe.api.exceptions.NotFoundException;
import com.edoe.api.exceptions.BadRequestException;
import com.edoe.api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edoe.api.models.Item;
import com.edoe.api.repositories.ItemRepository;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepo;

	@Autowired
	private UserService  userService;

	@PostConstruct
	public void main() {
		Item i = new Item();
		//User u = new User("jobson@dcx.ufpb.br","Jobson", Role.ADMIN, "123");
		i.setQuantity(2);
		i.setType(ItemType.DOACAO);
		i.setDescription("Exemplo de descrição");
		//i.setUser(u);
		itemRepo.save(i);
	}

	public Item createItem(Item item, String token) {
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

	public Item updateItem(Item item, String token) throws ServletException {
		if(!userService.isOwnerItem(token, item)) {
			throw new ForbiddenException();
		}
		return itemRepo.save(item);
	}

	public Item getItemById(Long id) {
		Optional<Item> item = itemRepo.findById(id);
		if(!item.isPresent()) {
			throw new NotFoundException();
		}
		return item.get();
	}

	public List<Item> getAllItems() {
		return itemRepo.findAll();
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

