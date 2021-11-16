package com.edoe.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edoe.api.models.Item;

public interface ItemRepository extends JpaRepository<Item,Long>{

}
