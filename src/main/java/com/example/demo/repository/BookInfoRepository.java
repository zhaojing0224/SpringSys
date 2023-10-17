package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BookInfo;

@Repository
public interface BookInfoRepository extends JpaRepository<BookInfo, Integer> {

	List<BookInfo> findByTitle(String title);
	
	List<BookInfo> findBookInfoById(Integer id);
	
}
