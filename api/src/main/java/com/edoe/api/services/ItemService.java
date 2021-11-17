package com.edoe.api.services;

import com.edoe.api.enums.ItemType;
import com.edoe.api.enums.Role;
import com.edoe.api.exceptions.ForbiddenException;
import com.edoe.api.exceptions.NotFoundException;
import com.edoe.api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edoe.api.models.Item;
import com.edoe.api.repositories.ItemRepository;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import java.util.List;
import java.util.Optional;

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
	
}

