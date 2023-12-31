package com.ua.glebkorobov.todolist.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Optional;

public class ResponseUtils {
    /* GET */

    public static <T> ResponseEntity<T> getResponse(Optional<T> result) {
        return result.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(result.get(), HttpStatus.OK);
    }


    public static <T> ResponseEntity<Collection<T>> getResponse(Collection<T> result) {
        return result.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(result, HttpStatus.OK);
    }


    /* POST */


    public static <T> ResponseEntity<T> postResponse(Optional<T> result) {
        return result.isEmpty() ? new ResponseEntity<>(HttpStatus.CONFLICT)
                : new ResponseEntity<>(result.get(), HttpStatus.CREATED);
    }


    /* PUT */
    /* PATCH */


    public static <T> ResponseEntity<T> updateResponse(Optional<T> result) {
        return result.isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST)
                : new ResponseEntity<>(result.get(), HttpStatus.OK);
    }


    /* DELETE */


    public static <T> ResponseEntity<T> deleteResponse(Optional<T> result) {
        return result.isPresent() ? new ResponseEntity<>(result.get(), HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    public static <T> ResponseEntity<Collection<T>> deleteResponse(Collection<T> result) {
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }


    /* OTHERS */


    public static <T> ResponseEntity<T> notAcceptable() {
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }


    public static <T> ResponseEntity<T> forbidden() {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
