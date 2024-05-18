package com.mercadolibre.shorturl.exception;

/**
 * Clase para manejar una exception personalizada para un error de URL
 *
 * @author Jeimmy Rojas.
 */
public class UrlNotFoundException extends Exception{
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;

	public UrlNotFoundException(String message) {
        super(message);
    }

	public UrlNotFoundException() {
	}
}
