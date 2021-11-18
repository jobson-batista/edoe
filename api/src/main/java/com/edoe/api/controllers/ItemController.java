package com.edoe.api.controllers;

import com.edoe.api.dto.ItemDTO;
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
	public ResponseEntity<ItemDTO> createItem (@RequestBody Item item, @RequestHeader("Authorization") String token) throws ServletException {
		return new ResponseEntity <>(itemServ.createItem(item, token).toDTO(), HttpStatus.OK);
	}

	@PatchMapping("/donations/{id}")
	public ResponseEntity<ItemDTO> updateItem(@RequestBody Item item, @RequestHeader("Authorization") String token, @PathVariable Long id) throws ServletException {
		return new ResponseEntity<>(itemServ.updateItem(item, id, token), HttpStatus.OK);
	}

	@DeleteMapping("/donations/{id}")
	public ResponseEntity removeItem(@PathVariable Long id, @RequestHeader("Authorization") String token) throws ServletException {
		itemServ.removeItem(id, token);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/donations")
	public ResponseEntity<List<ItemDTO>> getAllItemsDoacao() {
		return new ResponseEntity<>(itemServ.getAllItemsDTO(), HttpStatus.OK);
	}

	@GetMapping("/donations/descriptor")
	public ResponseEntity<List<ItemDTO>> getItemByDescriptor(@RequestParam Long id) {
		return new ResponseEntity<>(itemServ.getItemsByDescriptor(id), HttpStatus.OK);
	}
	
	@GetMapping("/donations/top10")
	public ResponseEntity<List<ItemDTO>> getItemsTopTen() {
		return new ResponseEntity<>(itemServ.topTenDonation(), HttpStatus.OK);
	}
}
