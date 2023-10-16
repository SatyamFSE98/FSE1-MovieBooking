package com.cognizant.moviebookingapp.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cognizant.moviebookingapp.model.ErrorResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(code = HttpStatus.CONFLICT)
	@ExceptionHandler({ InvalidInputException.class })
	public ErrorResponseDTO InvalidWalletBalanceException(Exception exception, HttpServletRequest request) {
		return new ErrorResponseDTO(new Date(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(),
				exception.getMessage(), request.getRequestURI());
	}
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	@ExceptionHandler({ AuthorizationException.class })
	public ErrorResponseDTO AuthorizationException(Exception exception, HttpServletRequest request) {
		return new ErrorResponseDTO(new Date(), HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(),
				exception.getMessage(), request.getRequestURI());
	}

}