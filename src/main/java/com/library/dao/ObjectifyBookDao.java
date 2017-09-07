package com.library.dao;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.googlecode.objectify.ObjectifyService;
import com.library.data.Book;
import com.library.data.Result;

/**
 * @author Jorge Iglesias
 * descripton Implementacion DAO para ObjectifyService
 */
public class ObjectifyBookDao  implements BookDao {

    private static final Logger LOGGER = Logger.getLogger(ObjectifyBookDao.class.getName());


	@Override
	public Long createBook(Book book) throws SQLException {

		return null;
	}

	@Override
	public Book readBook(Long bookId) throws SQLException {
		LOGGER.info("Retrieving bean " + bookId);
        return ObjectifyService.ofy().load().type(Book.class).id(bookId).now();
	}

	@Override
	public void updateBook(Book book) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBook(Long bookId) throws SQLException {
//		if (bean == null) {
//            throw new IllegalArgumentException("null test object");
//        }
//        LOGGER.info("Deleting bean " + bookId);
//        ObjectifyService.ofy().delete().entity(bean);
		
	}

	@Override
	public Result<Book> listBooks(String startCursor) throws SQLException {
		LOGGER.info("Retrieving list of beans");
		Result<Book> listBooks = new Result<Book>(ObjectifyService.ofy().load().type(Book.class).list()) ;
        return listBooks;
	}
}
