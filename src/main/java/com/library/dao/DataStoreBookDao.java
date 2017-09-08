package com.library.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.cloud.datastore.Cursor;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;

import com.library.data.Book;
import com.library.data.Result;

public class DataStoreBookDao implements BookDao {

	private Datastore datastore;
	private KeyFactory keyFactory;

	public DataStoreBookDao() {
		datastore = DatastoreOptions.getDefaultInstance().getService(); // Authorized Datastore service
		keyFactory = datastore.newKeyFactory().setKind("Book"); // Is used for creating keys later
	}

	public Book entityToBook(Entity entity) {

		Book book = new Book();
		book.setAuthor(entity.getString(Book.AUTHOR));
		book.setDescription(entity.getString(Book.DESCRIPTION));
		book.setId(entity.getKey().getId());
		book.setPublishedDate(entity.getString(Book.PUBLISHED_DATE));
		book.setTitle(entity.getString(Book.TITLE));

		return book;
	}

	@Override
	public Long createBook(Book book) {
		IncompleteKey key = keyFactory.newKey(); // Key will be assigned once written
		FullEntity<IncompleteKey> incBookEntity = Entity.newBuilder(key) // Create the Entity
				.set(Book.AUTHOR, book.getAuthor()) // Add Property ("author", book.getAuthor())
				.set(Book.DESCRIPTION, book.getDescription()).set(Book.PUBLISHED_DATE, book.getPublishedDate())
				.set(Book.TITLE, book.getTitle()).build();
		Entity bookEntity = datastore.add(incBookEntity); // Save the Entity
		return bookEntity.getKey().getId(); // The ID of the Key
	}

	@Override
	public Book readBook(Long bookId) {
		Entity bookEntity = datastore.get(keyFactory.newKey(bookId)); // Load an Entity for Key(id)
		return entityToBook(bookEntity);
	}

	@Override
	public void updateBook(Book book) {
		Key key = keyFactory.newKey(book.getId()); // From a book, create a Key
		Entity entity = Entity.newBuilder(key) // Convert Book to an Entity
				.set(Book.AUTHOR, book.getAuthor()).set(Book.DESCRIPTION, book.getDescription())
				.set(Book.PUBLISHED_DATE, book.getPublishedDate()).set(Book.TITLE, book.getTitle()).build();
		datastore.update(entity); // Update the Entity
	}

	@Override
	public void deleteBook(Long bookId) {
		Key key = keyFactory.newKey(bookId); // Create the Key
		datastore.delete(key); // Delete the Entity
	}

	public List<Book> entitiesToBooks(QueryResults<Entity> resultList) {
		List<Book> resultBooks = new ArrayList<>();
		while (resultList.hasNext()) { // We still have data
			resultBooks.add(entityToBook(resultList.next())); // Add the Book to the List
		}
		return resultBooks;
	}

	@Override
	public Result<Book> listBooks(String startCursorString) {
		Cursor startCursor = null;
		if (startCursorString != null && !startCursorString.equals("")) {
			startCursor = Cursor.fromUrlSafe(startCursorString); // Where we left off
		}
		Query<Entity> query = Query.newEntityQueryBuilder() // Build the Query
				.setKind("Book") // We only care about Books
				.setLimit(10) // Only show 10 at a time
				.setStartCursor(startCursor) // Where we left off
				.setOrderBy(OrderBy.asc(Book.TITLE)) // Use default Index "title"
				.build();
		QueryResults<Entity> resultList = datastore.run(query); // Run the query
		List<Book> resultBooks = entitiesToBooks(resultList); // Retrieve and convert Entities
		Cursor cursor = resultList.getCursorAfter(); // Where to start next time
		if (cursor != null && resultBooks.size() == 10) { // Are we paging? Save Cursor
			String cursorString = cursor.toUrlSafe(); // Cursors are WebSafe
			return new Result<>(resultBooks, cursorString);
		} else {
			return new Result<>(resultBooks);
		}
	}

	@Override
	public List<Book> listBooks() {

		Query<Entity> query = Query.newEntityQueryBuilder() // Build the Query
				.setKind("Book") // We only care about Books
				.setOrderBy(OrderBy.asc(Book.TITLE)) // Use default Index "title"
				.build();
		QueryResults<Entity> resultList = datastore.run(query); // Run the query
		List<Book> resultBooks = entitiesToBooks(resultList);

		return resultBooks;
	}

}
