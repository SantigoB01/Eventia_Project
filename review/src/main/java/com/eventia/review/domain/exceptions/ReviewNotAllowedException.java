package com.eventia.review.domain.exceptions;


public class ReviewNotAllowedException extends RuntimeException {
    public ReviewNotAllowedException(String msg) {
        super(msg);
    }
}