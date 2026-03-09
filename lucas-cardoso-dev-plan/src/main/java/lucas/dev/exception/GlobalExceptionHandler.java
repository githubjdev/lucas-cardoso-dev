package lucas.dev.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice /*Caso o projeto seja 100% em Rest API*/
public class GlobalExceptionHandler {

	/*
	 * Se qualquer controller lançar exceção em tempo de execução: throw new
	 * RuntimeException("Erro interno");
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntime(RuntimeException ex) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				            .body(ex.getMessage());
	}

	/* Sempre deixe um handler final para demais exceções inesperadas de camadas diferentes: */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception ex) {

		return ResponseEntity.status(500).body("Erro interno: " + ex.getMessage());
	}

	/*Msg com exceção customizada em nosso projeto e resposta em Json*/
	@ExceptionHandler(MsgApiException.class)
	public ResponseEntity<ErrorResponse> handleApiException(MsgApiException ex, 
			                                               HttpServletRequest request) {

		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), 
									ex.getStatus().value(),
									ex.getStatus().getReasonPhrase(), 
									ex.getMessage(), 
									request.getRequestURI());

		return ResponseEntity.status(ex.getStatus())
				              .body(error);
	}

}
