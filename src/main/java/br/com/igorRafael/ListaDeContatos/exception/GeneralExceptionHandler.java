package br.com.igorRafael.ListaDeContatos.exception;

import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	private ResponseEntity<String> handlerBadRequest(BadRequestException exception){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
	
	@ExceptionHandler(NotFoundException.class)
	private ResponseEntity<String> handlerNotFound(NotFoundException exception){
		return ResponseEntity.status(HttpStatus.OK).body(exception.getMessage());
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	private ResponseEntity<String> handleMessageNotReadable(HttpMessageNotReadableException exception){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("check if the name and number fields are filled in correctly");
	}
	
	
   @ExceptionHandler(OptimisticEntityLockException.class)	
   private ResponseEntity<String> handleOptimisticEntityLockException(OptimisticEntityLockException exception){
	   return ResponseEntity.status(HttpStatus.CONFLICT).body("A competition problem has occurred.");
   }
   
   @ExceptionHandler(MethodArgumentNotValidException.class)	
   private ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
	   return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Confirm that you are putting an existing name on the list to be updated and that the numeric and email data are valid.");
			   }

}
