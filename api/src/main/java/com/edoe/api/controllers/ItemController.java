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
	
	@PostMapping
	public ResponseEntity<ItemDTO> createItem (@RequestBody Item item, @RequestHeader("Authorization") String token) throws ServletException {
		return new ResponseEntity <>(itemServ.createItem(item, token).toDTO(), HttpStatus.OK);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<Item> updateItem(@RequestBody Item item, @RequestHeader("Authorization") String token) throws ServletException {
		return new ResponseEntity<>(itemServ.updateItem(item, token), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Item>> getAllItemsDoacao() {
		return new ResponseEntity<>(itemServ.getAllItems(), HttpStatus.OK);
	}
}
