package com.mercadolibre.shorturl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mercadolibre.shorturl.model.UrlShortEntity;

/**
 * Representancion del repositorio.
 * 
 * @author Jeimmy Rojas.
 */
public interface UrlShortRepository extends MongoRepository<UrlShortEntity, Integer>{

	/**
	 * Encuentra la informacion por la url de entrada.
	 */
	UrlShortEntity findByShortUrl(String url);
	
	/**
	 * Valida si la cadena de la url corta existe.
	 */
	boolean existsByShortUrl(String shortKey);

}