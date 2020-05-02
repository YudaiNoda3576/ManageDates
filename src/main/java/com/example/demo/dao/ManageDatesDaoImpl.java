package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ManageDates;
//データべースを操作するクラスであることを示す
 @Repository
public class ManageDatesDaoImpl implements ManageDatesDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public ManageDatesDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
//	全件検索
	@Override
	public List<ManageDates> findAll() {
		String sql = "SELECT id, name, year, month, date FROM manage_dates";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		List<ManageDates>list = new ArrayList<ManageDates>();
		for(Map<String, Object> result : resultList) {
			ManageDates manageDates = new ManageDates();
//			オブジェクト指向、manageDatesという物を扱うという考え方。値を詰め直す必要がある。
			manageDates.setId((String)result.get("id"));
			manageDates.setName((String)result.get("name"));
			manageDates.setYear((int)result.get("year"));
			manageDates.setMonth((int)result.get("month"));
			manageDates.setDate((int)result.get("date"));
			list.add(manageDates);
		}
		return list;
	}
	
	@Override
//	Optionalはnullに対処する方法
	public Optional<ManageDates> findOne(String id) {
		String sql = "SELECT id, name, year, month, date"
//				
				+ "WHERE id = ?";
//		一件取得
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
		
		ManageDates manageDates = new ManageDates();
		
		manageDates.setId((String)result.get("id"));
		manageDates.setName((String)result.get("name"));
		manageDates.setYear((int)result.get("year"));
		manageDates.setMonth((int)result.get("month"));
		manageDates.setDate((int)result.get("date"));
//		Optionalでラップする
		Optional<ManageDates> manageDatesOpt = Optional.ofNullable(manageDates);
		
		return manageDatesOpt;
	}
//	新規登録
//	新規登録・更新・削除にupdateメソッドを使う
	@Override
	public void insert(ManageDates manageDates) {
		 jdbcTemplate.update("INSERT INTO manage_dates(id, name, year, month, date) VALUES (?, ?, ?, ? ,?)",
				manageDates.getId(), manageDates.getName(), manageDates.getYear(), manageDates.getMonth(), manageDates.getDate());
		
	}
	@Override
	public int update(ManageDates manageDates) {
//		return jdbcTemplate.update("UPDATE manage_dates SET id = ?, name = ?, year = ?, month = ?, date = ? WHERE id = ?",
//			manageDates.getId(), manageDates.getName(), manageDates.getYear(), manageDates.getMonth(), manageDates.getDate());
		 int result = jdbcTemplate.update("UPDATE manage_dates SET id = ?, name = ?, year = ?, month = ?, date = ? WHERE id = ?",
			manageDates.getId(), manageDates.getName(), manageDates.getYear(), manageDates.getMonth(), manageDates.getDate()); 
		 
			 return result;
	}

	@Override
	public int delete(String id) {
		 int result = jdbcTemplate.update("DELETE FROM manage_dates WHERE id = ?", id);
		 
		 return result;
	}
}
