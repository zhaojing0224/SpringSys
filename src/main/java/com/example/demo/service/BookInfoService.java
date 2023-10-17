package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BookInfo;
import com.example.demo.repository.BookInfoRepository;

@Service
public class BookInfoService {

	@Autowired
	BookInfoRepository bookInfoRepositroy;

	/**
	 * レコードを全件取得する。
	 * @return BookInfoリスト
	 */
	public List<BookInfo> findAllBookInfo() {

		return bookInfoRepositroy.findAll();

	}

	/**
	 * 指定したidのレコードを取得する。
	 * @param id
	 * @return BookInfoリスト
	 */
	public List<BookInfo> findBookInfoById(Integer id) {

		return bookInfoRepositroy.findBookInfoById(id);

	}
	
	/**
	 * 指定したtitleのレコードを取得する。
	 * @param title
	 * @return BookInfoリスト
	 */
	public List<BookInfo> findBookInfoByTitle(String title) {

		return bookInfoRepositroy.findByTitle(title);

	}
	

	
}
