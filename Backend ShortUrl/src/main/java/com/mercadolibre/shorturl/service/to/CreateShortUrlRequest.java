package com.mercadolibre.shorturl.service.to;

import lombok.Data;

@Data
public class CreateShortUrlRequest {
    
	/**
	 * Url original
	 */
	private String url;

}
