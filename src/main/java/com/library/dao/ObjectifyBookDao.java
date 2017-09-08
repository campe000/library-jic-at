package com.library.dao;

import java.util.List;
import java.util.logging.Logger;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.library.data.Book;

/**
 * @author Jorge Iglesias
 * descripton Implementacion DAO para ObjectifyService
 */
public class ObjectifyBookDao {

    private static final Logger LOGGER = Logger.getLogger(ObjectifyBookDao.class.getName());


	public Long createBook(Book book) {
		if (book == null) {
            throw new IllegalArgumentException("null test object");
        }
        LOGGER.info("Saving bean " + book.getId());
        Key<Book> recordSave = ObjectifyService.ofy().save().entity(book).now();
		return recordSave.getId();
	}

	public Book readBook(Long bookId) {
		LOGGER.info("Retrieving bean " + bookId);
        return ObjectifyService.ofy().load().type(Book.class).id(bookId).now();
	}

	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	public void deleteBook(Long bookId) {
//		if (bean == null) {
//            throw new IllegalArgumentException("null test object");
//        }
//        LOGGER.info("Deleting bean " + bookId);
//        ObjectifyService.ofy().delete().entity(bean);
		
	}

	public List<Book> listBooks() {
		List<Book> listado = null;
		listado = ObjectifyService.ofy().load().type(Book.class).list();
		LOGGER.info("Retrieving list of beans");
        return  listado;
	}
}
