package br.com.igorRafael.ListaDeContatos.exception;

import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	private ResponseEntity<String> handlerBadRequest(BadRequestException exception){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
	
	@ExceptionHandler(NotExist.class)
	private ResponseEntity<String> handlerNotExist(NotExist exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}
	
   @ExceptionHandler(OptimisticEntityLockException.class)	
   private ResponseEntity<String> handleOptimisticEntityLockException(OptimisticEntityLockException exception){
	   return ResponseEntity.status(HttpStatus.CONFLICT).body("A competition problem has occurred.");
   }

}
