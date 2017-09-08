'use strict';

angular.module('library')
    .service('library', function ($http) {
        return {
        	listBooks: function (success) {
                return $http.get("/rest/library").then(success);
            },
            createBook: function (library, success) {
                return $http.post("/rest/library", library).then(success);
            },
            deleteBook: function (bookId, success) {
            	
            	$http({
            	    method: 'DELETE',
            	    url: '/rest/library/delete/' + bookId,
            	    data: {
            	        id: bookId
            	    },
            	    headers: {
            	        'Content-type': 'application/json;charset=utf-8'
            	    }
            	})
            	.then(function(response) {
            	    console.log(response.data);
            	}, function(rejection) {
            	    console.log(rejection.data);
            	});
            	
                return $http.delete("/rest/library", library).then(success);
            }
        };
    });
