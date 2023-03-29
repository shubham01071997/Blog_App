package com.BlogApplication.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.BlogApplication.dto.AprResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<AprResponse> ResourceNotFoundException (ResourceNotFoundException ex){
		String message=ex.getMessage();
		AprResponse AprResponse=new AprResponse(message,false);
		return new ResponseEntity<AprResponse>(AprResponse,HttpStatus.NOT_FOUND);	
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError) error).getField();
			String meassge=error.getDefaultMessage();
			resp.put(fieldName,meassge);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);		
	}
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<AprResponse> handleApiException (ApiException ex){
		String message=ex.getMessage();
		AprResponse AprResponse=new AprResponse(message,false);
		return new ResponseEntity<AprResponse>(AprResponse,HttpStatus.BAD_REQUEST);	
	}
}
