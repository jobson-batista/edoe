package com.edoe.api.models;

import javax.persistence.*;

import com.edoe.api.enums.ItemType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private ItemType type;
	
	private int quantity;
	
	private String description;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Descriptor descriptor;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
}