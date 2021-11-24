package com.edoe.api.services;

import com.edoe.api.dto.ItemDTO;
import com.edoe.api.enums.ItemType;
import com.edoe.api.enums.Role;
import com.edoe.api.exceptions.ForbiddenException;
import com.edoe.api.exceptions.NotFoundException;
import com.edoe.api.exceptions.BadRequestException;
import com.edoe.api.models.Descriptor;
import com.edoe.api.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edoe.api.models.Item;
import com.edoe.api.repositories.ItemRepository;

import javax.servlet.ServletException;
import java.util.*;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepo;

	@Autowired
	private UserService  userSerivce;

	@Autowired
	private DescriptorService descriptorService;

	public Item createItem(ItemType type, Item item, String token) throws ServletException {

		if(itemExists(item)) {
			throw new BadRequestException("Item already registered", "Repeated items are not allowed.");
		}

		if(!item.getType().equals(type)) {
			throw new BadRequestException("Item type not supported","Item type is not compatible with route, check route.");
		}

		for(Descriptor d: descriptorService.getAllDescriptors()) {
			if(d.getDescriptor().equals(item.getDescriptor().getDescriptor())) {
				item.setDescriptor(d);
			}
		}

		User u = userSerivce.getUserByToken(token);

		if(((u.getRole().equals(Role.APENAS_DOADOR) || u.getRole().equals(Role.DOADOR_RECEPTOR)) && type.equals(ItemType.DOACAO)) ||
				(u.getRole().equals(Role.DOADOR_RECEPTOR) || u.getRole().equals(Role.APENAS_RECEPTOR)) && type.equals(ItemType.NECESSARIO) ) {
			item.setUser(u);
			return itemRepo.save(item);
		}
		throw new ForbiddenException();
	}

	public ItemDTO updateItem(ItemType type, Item item, Long id, String token) throws ServletException {
		Optional<Item> itemOld = itemRepo.findById(id);

		if(!itemOld.isPresent()) {
			throw new NotFoundException();
		}
		if(!userSerivce.isOwnerItem(token, itemOld.get())) {
			throw new ForbiddenException();
		}
		if(itemOld.get().getQuantity() != item.getQuantity()) {
			itemOld.get().setQuantity(item.getQuantity());
		}
		if(item.getDescription() != null && itemOld.get().getDescription() != item.getDescription()) {
			itemOld.get().setDescription(item.getDescription());
		}
		if(!itemOld.get().getType().equals(type)) throw new BadRequestException("Item type not supported","Item type is not compatible with route, check route.");
		return itemRepo.save(itemOld.get()).toDTO();
	}

	public void removeItem(ItemType type, Long id, String token) throws ServletException {
		Optional<Item> item = itemRepo.findById(id);
		if(!item.get().getType().equals(type)) throw new BadRequestException("Item type not supported","Item type is not compatible with route, check route.");
		if(!item.isPresent()) {
			throw new NotFoundException();
		}
		if(!userSerivce.isOwnerItem(token, item.get())) {
			throw new ForbiddenException();
		}
		itemRepo.delete(item.get());
	}

	public void removeItemWithoutToken(Item i) {
		itemRepo.delete(i);
	}

	public Item getItemById(Long id) {
		Optional<Item> item = itemRepo.findById(id);
		if(!item.isPresent()) {
			throw new NotFoundException();
		}
		return item.get();
	}

	public List<ItemDTO> getAllItemsDTO() {
		List<ItemDTO> itemsDto = new ArrayList<>();
		for(Item i: itemRepo.findAll()) {
			itemsDto.add(i.toDTO());
		}
		return itemsDto;
	}
	
	private boolean itemExists(Item item) {
		for(Item i : itemRepo.findAll()) {
			if(item.getDescription().equals(i.getDescription()) && item.getType().equals(item.getType())) return true;
		}
		return false;
	}

	public List<ItemDTO> getItemsByDescriptor(ItemType type, Long id) {
		if(id == null) throw new NotFoundException();
		Descriptor d = descriptorService.findDescriptorById(id);
		List<ItemDTO> items = new ArrayList<>();
		for(Item i: itemRepo.findAll()) {
			if (i.getType().equals(type) && i.getDescriptor().getDescriptor().equals(d.getDescriptor())) {
				items.add(i.toDTO());
			}
		}
		return items;
	}

	public List<ItemDTO> searchItems(ItemType type, String term){
		List<ItemDTO> items = new ArrayList<>();
		for(Item i: itemRepo.findAll()){
			if(i.getType().equals(type) && i.getDescriptor().getDescriptor().toLowerCase().contains(term.toLowerCase())){
				items.add(i.toDTO());
			}
		}
		return items;
	}

	public List<ItemDTO> topTenDonation(ItemType type) {
		List<ItemDTO> items = new ArrayList<>();
		for(Item i: itemRepo.findAll()){
			if(i.getType().equals(type)) items.add(i.toDTO());
		}
		Collections.sort(items);
		if(items.size() > 10) {
			items = items.subList(0,10);
		}
		return items;
	}

	public List<ItemDTO> itemsMatches(Long idItem, String token) throws ServletException {
		List<ItemDTO> items = new ArrayList<>();
		Optional<Item> item = itemRepo.findById(idItem);
		if(!(userSerivce.getUserByToken(token).getRole().equals(Role.APENAS_RECEPTOR) ||
				userSerivce.getUserByToken(token).getRole().equals(Role.APENAS_RECEPTOR))) {
			throw new ForbiddenException();
		}
		if(!item.get().getType().equals(ItemType.NECESSARIO)) throw new BadRequestException("Item type not supported","Item type is not compatible with route, check route.");
		for(Item i: itemRepo.findAll()) {
			if(i.getType().equals(ItemType.DOACAO) && i.getDescriptor().equals(item.get().getDescriptor())) {
				items.add(i.toDTO());
			}
		}
		return items;
	}
}

