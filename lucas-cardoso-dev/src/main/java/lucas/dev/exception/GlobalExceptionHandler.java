package lucas.dev.exception;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	

	/*
	 * Se qualquer controller lançar exceção em tempo de execução: throw new
	 * RuntimeException("Erro interno");
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntime (RuntimeException ex){
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		
	}
	
	
	/* Sempre deixe um handler final para demais exceções inesperadas de camadas diferentes: */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException (Exception ex){
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Erro no sistema: " + ex.getMessage());
		
	}
	
	
	@ExceptionHandler({MsgApiException.class}) /*Captura a exceção*/
	public ResponseEntity<ErrorResponse> handleApiException(MsgApiException ex, 
			HttpServletRequest request){
		
		
		ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),
				                         ex.getStatus().value(), 
				                         ex.getStatus().getReasonPhrase(), 
				                         ex.getMessage(), 
				                         request.getRequestURI());
		
		/*Objeto completo com erro e detalhes*/
		return ResponseEntity.status(ex.getStatus()).body(errorResponse);
		
	}
	
	

}
