package com.mercadolibre.shorturl.util;


public final class Constants {
    
	/**
	 * Mensaje OK
	 */
    public static final String MENSAJE_OK = "Solicitud creada con exito.";
    
	/**
	 * Mensaje de error para excepcion NullPointer.
	 */
    public static final String MENSAJE_ERROR_NULL_POINTER = "La URL ingresada es nula.";
    
	/**
	 * Mensaje de error para excepcion UrlNotFound.
	 */
    public static final String MENSAJE_ERROR_URL_NOT_FOUND= "Problemas al generar la URL, ingrese una URL valida.";
    
	/**
	 * Mensaje de error al persistir en la base de datos.
	 */
    public static final String MENSAJE_ERROR_DATABASE = "Problemas al persistir informacion en la base de datos.";
    
	/**
	 * Mensaje de url no encontrada.
	 */
    public static final String MENSAJE_URL_NO_ENCONTRADA = "URL no encontrada.";
    
    protected Constants() {
    }
}