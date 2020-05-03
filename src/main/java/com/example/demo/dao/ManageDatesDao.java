package com.example.demo.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.app.ManageDatesForm;
import com.example.demo.entity.ManageDates;

public interface ManageDatesDao {
	
//	int count();
	
	List<ManageDates> findAll();
	
//    Optional<ManageDates> findOne(String id);
	
	ManageDates findOne(String id);
	
	void insert(ManageDates manageDates);
	
	void update(ManageDates manageDates);
	
	int delete(String id);

	
	
}
