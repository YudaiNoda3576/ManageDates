package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.ManageDatesDao;
import com.example.demo.entity.ManageDates;

public interface ManageDatesService {
	
	
	
	List<ManageDates> findAll();
	
	List<LocalDate> search(String input);
	
    Optional<ManageDates> findOne(String id);
	
	void insert(ManageDates manageDates);
	
	boolean update(ManageDates manageDates);
	
	boolean delete(int id);
	
}
