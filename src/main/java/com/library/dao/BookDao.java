package com.library.dao;

import java.sql.SQLException;

import com.library.data.Book;
import com.library.data.Result;

public interface BookDao {
	Long createBook(Book book) throws SQLException;

	Book readBook(Long bookId) throws SQLException;

	void updateBook(Book book) throws SQLException;

	void deleteBook(Long bookId) throws SQLException;

	Result<Book> listBooks(String startCursor) throws SQLException;
}
