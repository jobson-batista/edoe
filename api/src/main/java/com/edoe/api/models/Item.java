package com.edoe.api.models;

import javax.persistence.*;

import com.edoe.api.dto.ItemDTO;
import com.edoe.api.enums.ItemType;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private ItemType type;
	
	private int quantity;
	
	private String description;
	
	@OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Descriptor descriptor;

	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "user_id")
	private User user;

	public ItemDTO toDTO(){
		ItemDTO item = new ItemDTO();
		item.setId(this.id);
		item.setType(this.type);
		item.setQuantity(this.quantity);
		item.setDescription(this.description);
		item.setDescriptor(this.descriptor);
		item.setUser(this.user.toDTO());
		return item;
	}
}