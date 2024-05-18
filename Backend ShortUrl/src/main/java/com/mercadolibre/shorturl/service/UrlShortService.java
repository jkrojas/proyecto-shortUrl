package com.mercadolibre.shorturl.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import com.mercadolibre.shorturl.exception.DatabaseException;
import com.mercadolibre.shorturl.exception.UrlNotFoundException;
import com.mercadolibre.shorturl.model.UrlShortEntity;
import com.mercadolibre.shorturl.repository.UrlShortRepository;
import com.mercadolibre.shorturl.service.to.CreateShortUrlResponse;
import com.mercadolibre.shorturl.service.to.CreateShortUrlRequest;
import com.mercadolibre.shorturl.util.Constants;

import lombok.extern.log4j.Log4j2;

/**
 * Clase que permite generar una url corta a partir de una url original.
 *
 * @author Jeimmy Rojas.
 */
@Log4j2
@Service
public class UrlShortService {
	
    /**
     * Instancia del repositorio.
     */
	@Autowired
	UrlShortRepository repository;

	/**
	 * Metodo que genera una nueva url corta, verifica que
	 * la nueva url no exista en la base de datos y hace una cantidad x de
	 * reintentos en caso de existir, persiste la informacion en la base de datos.
	 * 
	 * @param String originalUrl: Url original.
	 * @return CreateShortUrlResponse: Retorna la informacion con la url modificada.
	 * @throws Exception 
	 */
	public CreateShortUrlResponse setOriginalUrl(CreateShortUrlRequest request) throws Exception {
		log.info("[setOriginalUrl] Inicio.");
		String originalUrl = request.getUrl();
		String shortKey = generateShortKey(originalUrl);
		try {
			boolean keyExists = repository.existsByShortUrl(shortKey);
			if (keyExists) {
				return setInformation(shortKey);
			}
			
			if(!isUrlValid(originalUrl)) {
				throw new UrlNotFoundException();
			}
			databasePersistence(originalUrl, shortKey);
			
		}catch (UrlNotFoundException e) {
			log.error("Metodo [setOriginalUrl] con error.");
			throw new UrlNotFoundException(Constants.MENSAJE_ERROR_URL_NOT_FOUND);
		}catch (DatabaseException e) {
			log.error("Metodo [setOriginalUrl] con error.");
			throw new DatabaseException(Constants.MENSAJE_ERROR_DATABASE);
		}
		log.info("[setOriginalUrl] Fin.");
		return setInformation(shortKey);
	}

	/**
	 * Metodo que entrega la informacion al objeto de respuesta
	 * 
	 * @param String shortKey: Url corta.
	 * @return CreateShortUrlResponse response: Objeto de respuesta.
	 */
	private CreateShortUrlResponse setInformation(String shortKey) {
		log.info("[setInformation] Inicio.");
		CreateShortUrlResponse response = new CreateShortUrlResponse();
		response.setShortUrl(shortKey);
		response.setMessage(Constants.MENSAJE_OK);
		log.info("[setInformation] Fin.");
		return response;
	}
	
	/**
	 * Metodo que valida si la url es valida
	 * 
	 * @param String url: Url.
	 * @return boolean result: Si es valido devuelve true.
	 */
    private boolean isUrlValid(String url) {
        UrlValidator urlValidator = new UrlValidator(
               new String[]{"http","https"}
        );
        return urlValidator.isValid(url);
    }
	
	/**
	 * Metodo que persiste la informacion en la base de datos.
	 * 
	 * @param String originalUrl: Url original.
	 * @param String shortKey: Url modificada.
	 * @throws Exception 
	 */
	private void databasePersistence(String originalUrl, String shortKey) throws Exception {
		log.info("[databasePersistence] Inicio.");
		UrlShortEntity entity = new UrlShortEntity();
		entity.setOriginalUrl(originalUrl);
		entity.setShortUrl(shortKey);
		entity.setEnabled(1);
		entity.setFecha(new Date());
		try {
			repository.save(entity);
			log.info("[databasePersistence] Informacion almacenada correctamente.");
		} catch (Exception e) {
			log.error("Metodo [databasePersistence] con errores.");
			throw new DatabaseException();
		}
		log.info("[databasePersistence] Fin.");
	}
	
	/**
	 * Metodo que genera un hash random, se obtendra los primeros 6 digitos y se concatenaran al dominio.
	 * 
	 * @param String originalUrl: Url original.
	 * @param String hex: Devuelve los primeros 6 digitos del hash.
	 */
	private String generateShortKey(String originalUrl) throws NoSuchAlgorithmException {
		log.info("[generateShortKey] Inicio.");
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    byte[] hash = digest.digest(originalUrl.getBytes(StandardCharsets.UTF_8));
	    String hex = String.format("%064x", new BigInteger(1, hash));
	    log.info("[generateShortKey] Fin.");
	    return hex.substring(0, 6);
	}

	/**
	 * Metodo que se encarga de buscar en la base de datos la url consultada.
	 * 
	 * @param String shortUrl: Url corta.
	 * @param RedirectView redirectUrl: Devuelve la redireccion a la url original
	 */
	public RedirectView redirectUrl(String shortUrl) {
		log.info("[redirectUrl] Inicio.");
		UrlShortEntity entity = repository.findByShortUrl(shortUrl);
		if (entity != null && entity.getEnabled() == 1) {
			RedirectView redirectView = new RedirectView();
			redirectView.setUrl(entity.getOriginalUrl());
			redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
			log.info("[redirectUrl] Fin.");
			return redirectView;
		} else {
			log.error("Metodo [redirectUrl] con errores.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constants.MENSAJE_URL_NO_ENCONTRADA);
		}
	}
	
}
