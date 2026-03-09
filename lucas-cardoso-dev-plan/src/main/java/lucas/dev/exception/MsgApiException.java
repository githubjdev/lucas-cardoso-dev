package lucas.dev.exception;

import org.springframework.http.HttpStatus;

/*Crie uma exceção base para a aplicação.*/
/*Agora você pode lançar exceções com status HTTP personalizado.*/
public class MsgApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final HttpStatus status;

	public MsgApiException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
