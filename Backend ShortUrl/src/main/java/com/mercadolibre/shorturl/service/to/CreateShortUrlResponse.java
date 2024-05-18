package com.mercadolibre.shorturl.service.to;

import lombok.Data;

@Data
public class CreateShortUrlResponse {
    
    /**
     * Mensaje de respuesta.
     */
	private String message;
    
    /**
     * Url acortada.
     */
    private String shortUrl;

}
