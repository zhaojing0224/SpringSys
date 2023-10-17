package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.BookInfo;
import com.example.demo.service.BookInfoService;

@Controller
public class BookInfoController {

	@Autowired
	BookInfoService bookInfoService;

	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 *本情報一覧
	 * @param model
	 * @return
	 */
	@GetMapping("/bookInfo")
	public String boookInfo (Model model) {
		
		model.addAttribute("bookInfo", "BookInfoList");
		
		//本情報取得
		List<BookInfo> bookInfoList = bookInfoService.findAllBookInfo();
		model.addAttribute("bookInfoList", bookInfoList);
		
		return "bookInfo";
	}

	/**
	 * idで本情報一覧検索する
	 * @param searchId
	 * @param model
	 * @return
	 */
	@GetMapping("/searchBookInfo")
	public String getBookInfoById(@RequestParam(name = "searchId") Integer searchId, Model model) {
		
		List<BookInfo> bookInfoList = bookInfoService.findBookInfoById(searchId);
		model.addAttribute("bookInfoList", bookInfoList);
		
		return "bookInfo";
		
	}
	
	/**
	 *  idで本情報一覧戻る
	 * @return
	 */
	@GetMapping("/goBackToBookInfo")
	public String goBackToBookInfo() {
		return "redirect:/BookInfo";
	}
	
	/**
	 * idで本情報編集画面を表示
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/editBookInfo/{id}")
	public String showEditForm(@PathVariable Integer id, Model model) {
		return "redirect:/BookInfo";
	}
	
	
	
	
}