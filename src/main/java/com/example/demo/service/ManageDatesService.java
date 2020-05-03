package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.app.ManageDatesForm;
import com.example.demo.entity.ManageDates;

public interface ManageDatesService {
	
//	int count();
	
	List<ManageDates> findAll();
	
	List<LocalDate> search(String input);
	
//  Optional<ManageDates> findOne(String id);
    
	ManageDates findOne(String id);
    
	void insert(ManageDates manageDates);
	
	boolean update(ManageDates manageDates);
	
	boolean delete(String id);
	
}
