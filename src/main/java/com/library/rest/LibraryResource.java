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

    public LibraryResource() {
        this.bookDAO = new ObjectifyBookDao();
    }

    @GET
    @Path("/{textSearch}")
    @ApiOperation("list books objects")
    public Response listBooks(@PathParam("textSearch") String text ) {
    	Response listado = null;
        try {
        	listado = Response.ok(this.bookDAO.listBooks(text)).build();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
        return listado;
    }

    @GET
    @Path("/{id}")
    @ApiOperation("get detail book object")
    public Response readBook(@PathParam("id") Long id) {
        Book bean = null;
		try {
			bean = this.bookDAO.readBook(id);
			if (bean == null) {
	            return Response.status(Response.Status.NOT_FOUND).build();
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
        
        return Response.ok(bean).build();
    }

    @POST
    @Consumes("application/json;charset=utf-8")
    @ApiOperation("save book object")
    public Response createBook(Book bean) {
        try {
			this.bookDAO.createBook(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation("delete book object")
    public Response delete(@PathParam("id") Long id) {
        Book bean;
		try {
			bean = this.bookDAO.readBook(id);
			if (bean == null) {
	            return Response.status(Response.Status.NOT_FOUND).build();
	        }
	        this.bookDAO.deleteBook(bean.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
        
        
        
        return Response.ok().build();
    }
}
