package com.example.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ManageDatesDao;
import com.example.demo.entity.ManageDates;

@Service
public class ManageDatesServiceImpl implements ManageDatesService {

	public final ManageDatesDao dao; 
//	デフォルトコンストラクタへの代入
	@Autowired ManageDatesServiceImpl(ManageDatesDao dao) {
		this.dao = dao;
	}
	

	@Override
	public List<ManageDates> findAll() {
		return dao.findAll();
	}
	@Override
	//全体検索結果に計算を行う	
		public List<LocalDate> search(String input) {
//		入力値をlocalDateに変換
			DateTimeFormatter formatter = new DateTimeFormatterBuilder()
					.appendValue(ChronoField.YEAR, 4)
					.appendValue(ChronoField.MONTH_OF_YEAR, 2)
					.appendValue(ChronoField.DAY_OF_MONTH, 2)
					.toFormatter();
			LocalDate inputDate = LocalDate.parse(input, formatter); 
			
			List<ManageDates> manageDates = dao.findAll();
			
			List<LocalDate> sumDate = new ArrayList<LocalDate>();
//			各レコードから計算用の年月日を取得。入力値と合算させる。
			for(ManageDates n : manageDates) {
				int dbYear = n.getYear();
				int dbMonth = n.getMonth();
				int dbDate = n.getDate();
				LocalDate sum = inputDate.plusYears(dbYear).plusMonths(dbMonth).plusDays(dbDate);
//			listに計算結果を格納
				sumDate.add(sum);
			}
			return sumDate;
		}

		@Override
		public Optional<ManageDates> findOne(String id) {
//			値がなければ例外発生
//			チェック例外を非チェック例外に直して、一括で管理するようにする（議論あり）
			try {
				return dao.findOne(id);
			} catch (EmptyResultDataAccessException e) {
				throw new ManageDatesNotFoundException("指定された値が存在しません");
			}
		}

		@Override
		public void insert(ManageDates manageDates) {
			dao.insert(manageDates);
			
		}
		
		@Override
		public boolean update(ManageDates manageDates) {
//			一件更新
			int num = dao.update(manageDates);
			
			boolean result = false;
			
			if(num > 0) {
				result = true;
			} 
				return result;
			}
		

		@Override
		public boolean delete(String id) {
			int num = dao.delete(id);
			
			boolean result = false;
			
			if(num > 0) {
				result = true;
			} 
				return result;
			}
		}
		
