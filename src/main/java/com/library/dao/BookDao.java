package com.library.dao;

import java.util.List;

import com.library.data.Book;
import com.library.data.Result;

public interface BookDao {
	Long createBook(Book book);

	Book readBook(Long bookId) ;

	void updateBook(Book book);

	void deleteBook(Long bookId);

	Result<Book> listBooks(String startCursor);
	
	List<Book> listBooks();
}
