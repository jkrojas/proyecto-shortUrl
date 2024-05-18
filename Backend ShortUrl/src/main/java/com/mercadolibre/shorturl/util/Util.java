package com.mercadolibre.shorturl.util;


public final class Util {

    protected Util() {
    }
    
    /**
     * Metodo que valida si la cadena es nula o vacia
     * 
     * @param string cadena a validar
     * @return true o false.
     */
    public static boolean notNullOrEmpty(String string) {

        return string != null && !string.trim().isEmpty();
    }
    
    /**
     * Method que valida si un objeto es nulo.
     * 
     * @param Objetc objeto a validar.
     * @return true o false.
     */
    public static boolean notNull(Object objeto) {

        return objeto != null;
    }
}