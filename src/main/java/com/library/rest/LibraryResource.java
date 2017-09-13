package com.library.rest;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.library.dao.ObjectifyBookDao;
import com.library.data.Book;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;


@Path("/library")
@Produces("application/json;charset=utf-8")
@Api(value = "library", description = "library service")
public class LibraryResource {

    private ObjectifyBookDao bookDAO;
//    private DataStoreBookDao dsBookDao;
    
    public LibraryResource() {
        this.bookDAO = new ObjectifyBookDao();
//        this.dsBookDao = new DataStoreBookDao();
    }

    @GET
    @ApiOperation("list books objects")
    public Response listBooks() {
       	return Response.ok(this.bookDAO.listBooks()).build();
        
    }

    @GET
    @Path("/{id}")
    @ApiOperation("get detail book object")
    public Response readBook(@PathParam("id") Long id) {
        Book bean = null;
		bean = this.bookDAO.readBook(id);
		if (bean == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }        
        return Response.ok(bean).build();
    }

    @POST
    @Consumes("application/json;charset=utf-8")
    @ApiOperation("save book object")
    public Response createBook(Book bean) {
		this.bookDAO.createBook(bean);
        return Response.ok().build();
    }
//
    @DELETE
    @Path("/delete/{bookId}")
    @ApiOperation("delete book object")
    public Response delete(@PathParam("bookId") Long bookId) {
        Book bean;
		bean = this.bookDAO.readBook(bookId);
		if (bean == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        this.bookDAO.deleteBook(bookId);
        return Response.ok().build();
    }
}
