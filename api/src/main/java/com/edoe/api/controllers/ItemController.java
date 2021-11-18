package com.edoe.api.controllers;

import com.edoe.api.dto.ItemDTO;
import com.edoe.api.enums.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.edoe.api.models.Item;
import com.edoe.api.services.ItemService;

import javax.servlet.ServletException;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
	@Autowired
	private ItemService itemServ;
	
	@PostMapping("/donations")
	public ResponseEntity<ItemDTO> createItemDonation(@RequestBody Item item, @RequestHeader("Authorization") String token) throws ServletException {
		return new ResponseEntity <>(itemServ.createItem(ItemType.DOACAO, item, token).toDTO(), HttpStatus.OK);
	}

	@PatchMapping("/donations/{id}")
	public ResponseEntity<ItemDTO> updateItemDonation(@RequestBody Item item, @RequestHeader("Authorization") String token, @PathVariable Long id) throws ServletException {
		return new ResponseEntity<>(itemServ.updateItem(ItemType.DOACAO, item, id, token), HttpStatus.OK);
	}

	@DeleteMapping("/donations/{id}")
	public ResponseEntity removeItemDonation(@PathVariable Long id, @RequestHeader("Authorization") String token) throws ServletException {
		itemServ.removeItem(ItemType.DOACAO, id, token);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<ItemDTO>> getAllItemsDoacao() {
		return new ResponseEntity<>(itemServ.getAllItemsDTO(), HttpStatus.OK);
	}

	@GetMapping("/donations/descriptor")
	public ResponseEntity<List<ItemDTO>> getItemByDescriptor(@RequestParam Long id) {
		return new ResponseEntity<>(itemServ.getItemsByDescriptor(ItemType.DOACAO, id), HttpStatus.OK);
	}

	@GetMapping("/donations")
	public ResponseEntity<List<ItemDTO>> searchItem(@RequestParam String search) {
		return new ResponseEntity<>(itemServ.searchItems(ItemType.DOACAO, search), HttpStatus.OK);
	}
	
	@GetMapping("/donations/top10")
	public ResponseEntity<List<ItemDTO>> getItemsTopTen() {
		return new ResponseEntity<>(itemServ.topTenDonation(ItemType.DOACAO), HttpStatus.OK);
	}

	@PostMapping("/required")
	public ResponseEntity<ItemDTO> createItemRequired(@RequestBody Item item, @RequestHeader("Authorization") String token) throws ServletException {
		return new ResponseEntity <>(itemServ.createItem(ItemType.NECESSARIO, item, token).toDTO(), HttpStatus.OK);
	}

	@PatchMapping("/required/{id}")
	public ResponseEntity<ItemDTO> updateItemRequired(@RequestBody Item item, @RequestHeader("Authorization") String token, @PathVariable Long id) throws ServletException {
		return new ResponseEntity<>(itemServ.updateItem(ItemType.NECESSARIO, item, id, token), HttpStatus.OK);
	}

	@DeleteMapping("/required/{id}")
	public ResponseEntity removeItemRequired(@PathVariable Long id, @RequestHeader("Authorization") String token) throws ServletException {
		itemServ.removeItem(ItemType.NECESSARIO, id, token);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/required/descriptor")
	public ResponseEntity<List<ItemDTO>> getItemRequiredByDescriptor(@RequestParam Long id) {
		return new ResponseEntity<>(itemServ.getItemsByDescriptor(ItemType.NECESSARIO, id), HttpStatus.OK);
	}

	@GetMapping("/required/top10")
	public ResponseEntity<List<ItemDTO>> getItemsTopTenRequired() {
		return new ResponseEntity<>(itemServ.topTenDonation(ItemType.NECESSARIO), HttpStatus.OK);
	}

	@GetMapping("/matches")
	public ResponseEntity<List<ItemDTO>> matchesItems(@RequestParam Long id, @RequestHeader("Authorization") String token) throws ServletException {
		return new ResponseEntity<>(itemServ.itemsMatches(id, token), HttpStatus.OK);
	}

	@GetMapping("/required")
	public ResponseEntity<List<ItemDTO>> searchItemRequired(@RequestParam String search) {
		return new ResponseEntity<>(itemServ.searchItems(ItemType.NECESSARIO, search), HttpStatus.OK);
	}
}
