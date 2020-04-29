package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.ManageDates;

public interface ManageDatesService {
	
	List<ManageDates> findAll();
	
	List<LocalDate> search(String input);
	
    Optional<ManageDates> findOne(String id);
	
	void insert(ManageDates manageDates);
	
	void update(ManageDates manageDates);
	
	void delete(String id);
	
}
