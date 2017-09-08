package com.library.dao;

import java.util.List;

import com.library.data.Book;

public interface BookDao {
	Long createBook(Book book);

	Book readBook(Long bookId) ;

	void updateBook(Book book);

	void deleteBook(Long bookId);

	List<Book> listBooks(String startCursor);
}
