package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String boookInfo(Model model) {

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
	public String getBookInfoById(@RequestParam(name = "searchId", required = false) Integer searchId, Model model) {

		List<BookInfo> bookInfoList = null;
		if (searchId != null) {

			bookInfoList = bookInfoService.findBookInfoById(searchId);
		}
		
		model.addAttribute("bookInfoList", bookInfoList);

		return "bookInfo";

	}

	/**
	 * 本情報一覧戻る
	 * @return
	 */
	@GetMapping("/goBackToBookInfo")
	public String goBackToBookInfo() {

		return "redirect:/bookInfo";
	}

	/**
	 * 本情報編集画面を表示
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/editBookInfo/{id}")
	public String showEditForm(@PathVariable Integer id, Model model) {

		Optional<BookInfo> optionalBookInfo = bookInfoService.findBookById(id);
		BookInfo bookInfo = optionalBookInfo.orElseThrow(() -> new RuntimeException("Weather not found"));

		model.addAttribute("editBookInfo", bookInfo);
		return "editBookInfo";
	}

	/**
	 * 本情報編集処理
	 * @param bookInfo
	 * @return
	 */
	@PostMapping("/editBookInfo")
	public String editBookInfo(@ModelAttribute BookInfo bookInfo) {

		LocalDateTime currentTime = LocalDateTime.now();

		bookInfo.setCreateDate(currentTime);
		bookInfo.setUpdateDate(currentTime);

		bookInfoService.saveBookInfo(bookInfo);

		return "redirect:/bookInfo";
	}

	/**
	 * 本情報編集を削除する
	 * @param bookInfo
	 * @return
	 */
	@GetMapping("delBookInfo/{id}")
	public String delBookInfo(@PathVariable Integer id) {

		bookInfoService.deleteBookInfo(id);

		return "redirect:/bookInfo";

	}

	/**
	 * 本情報新規登録画面を表示
	 * @param bookInfo
	 * @return
	 */
	@GetMapping("/addBookInfo")
	public String addBookInfo(Model model) {

		BookInfo bookInfo = new BookInfo();
		model.addAttribute("addBookInfo", bookInfo);

		return "addBookInfo";
	}

	/**
	 * 本情報新規登録処理
	 * @param bookInfo
	 * @return
	 */
	@PostMapping("/addBookInfo")
	public String addBookInfo(@ModelAttribute BookInfo bookInfo) {

		LocalDateTime currentTime = LocalDateTime.now();

		bookInfo.setCreateDate(currentTime);
		bookInfo.setUpdateDate(currentTime);

		bookInfoService.saveBookInfo(bookInfo);
		return "redirect:/bookInfo";

	}

}