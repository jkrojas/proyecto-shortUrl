package com.mercadolibre.shorturl.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representancion de la tabla.
 * 
 * @author Jeimmy Rojas.
 */

@Document
public class UrlShortEntity {
	
    /**
     * Url corta
     */
	private String shortUrl;
	
    /**
     * Url original
     */
	private String originalUrl;
	
    /**
     * Disponibilidad de la url
     */
	private int enabled;
	
    /**
     * Fecha de ingreso
     */
	private Date fecha;
	
	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
