package com.edoe.api.controllers;

import com.edoe.api.dto.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.edoe.api.models.Item;
import com.edoe.api.services.ItemService;

import javax.servlet.ServletException;

@RestController
@RequestMapping("/items")
public class ItemController {
	@Autowired
	private ItemService itemServ;
	
	@PostMapping
	public ResponseEntity<ItemDTO> createItem (@RequestBody Item item, @RequestHeader("Authorization") String token) throws ServletException {
		return new ResponseEntity <>(itemServ.createItem(item, token).toDTO(), HttpStatus.OK);
	}
}