package ar.edu.unicen.ringo.mockapp.ui;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_MODIFIED)
public class AlreadyInRequestedState extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5764982487393875888L;

}
