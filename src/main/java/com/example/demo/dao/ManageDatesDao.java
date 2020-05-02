package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.ManageDates;

public interface ManageDatesDao {
	
//	void insertManageDates(ManageDates manageDates);
	int count();
	
	List<ManageDates> findAll();
	
    Optional<ManageDates> findOne(String id);
	
	void insert(ManageDates manageDates);
	
	int update(ManageDates manageDates);
	
	int delete(int id);
	
	
}
