package com.mercadolibre.shorturl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.mercadolibre.shorturl.service.UrlShortService;
import com.mercadolibre.shorturl.service.to.CreateShortUrlResponse;
import com.mercadolibre.shorturl.service.to.CreateShortUrlRequest;
import com.mercadolibre.shorturl.util.Constants;
import com.mercadolibre.shorturl.util.Util;

import lombok.extern.log4j.Log4j2;

/**
 * Controller de acortador de URL
 *
 * @author Jeimmy Rojas.
 */
@Log4j2
@RestController
@RequestMapping("/url")
public class UrlShortController {
	
	/**
	 * Servicio que se encarga de acortar y almacenar la URL
	 */
	@Autowired
	UrlShortService service;

	/**
	 * Metodo que se encarga de crear una url acortada.
	 * 
	 * @param request Request: Objeto que obtiene la url orginal
	 * @return CreateShortUrlResponse response: Retorna con la url modificada.
	 * @throws NullPointerException 
	 */
	 @PostMapping(value = "/originalUrl")
	 public ResponseEntity<CreateShortUrlResponse> createShortUrl(@RequestBody CreateShortUrlRequest request) throws Exception {
		 log.info("[createShortUrl] Inicio.");
		 CreateShortUrlResponse response = new CreateShortUrlResponse();
		 if(request != null && Util.notNullOrEmpty(request.getUrl())) {
			 response = service.setOriginalUrl(request);
		 }else {
			 log.error("[createShortUrl] Error en el servicio");
			 throw new NullPointerException(Constants.MENSAJE_ERROR_NULL_POINTER);
		 }
		 log.info("[createShortUrl] Fin.");
		 return new ResponseEntity<>(response, HttpStatus.CREATED);
	 }
	 
		/**
		 * Metodo que se encarga de redireccionar a la url original
		 * 
		 * @param String shortUrl: Cadena con la url acortada.
		 * @return CreateShortUrlResponse response: Retorna con la url modificada.
		 * @throws NullPointerException 
		 */
	 @GetMapping(value = "/{shortUrl}")
	 public RedirectView redirectUrl(@PathVariable String shortUrl) {
		 log.info("[createShortUrl] Inicio.");
		 RedirectView redirect = service.redirectUrl(shortUrl);
		 log.info("[createShortUrl] Fin.");
		 return redirect;
	 }
}
