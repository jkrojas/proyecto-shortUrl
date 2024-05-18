package com.mercadolibre.shorturl.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.view.RedirectView;

import com.mercadolibre.shorturl.service.UrlShortService;
import com.mercadolibre.shorturl.service.to.CreateShortUrlResponse;
import com.mercadolibre.shorturl.service.to.CreateShortUrlRequest;

@SpringBootTest
class UrlShortControllerTest {

	@Test
	void contextLoads() {
	}
	
	@InjectMocks
    private UrlShortController controller;
	
	@Mock
    private UrlShortService service;
	
	public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void createShortUrlTest() throws Exception {
        CreateShortUrlRequest request = new CreateShortUrlRequest();
        request.setUrl("http://dominio-ejemplo.com");
        
        CreateShortUrlResponse response = new CreateShortUrlResponse();
        response.setShortUrl("https://www.meli.com/2d7338");

        when(service.setOriginalUrl(any(CreateShortUrlRequest.class))).thenReturn(response);
        
        assertNotNull(controller.createShortUrl(request));
    }
	
	@Test
	public void redirectUrlTest() throws Exception {
        String shortUrl = "https://www.meli.com/2d7338";
        RedirectView redirectView = new RedirectView();
		redirectView.setUrl(shortUrl);
		redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);

        when(service.redirectUrl(shortUrl)).thenReturn(redirectView);

        assertNotNull(controller.redirectUrl(shortUrl));
    }
	
	@Test(expected = Exception.class)
	public void createShortUrlBadRequestExceptionTest() throws Exception {
        CreateShortUrlRequest request = new CreateShortUrlRequest();
        request.setUrl(null);
        when(service.setOriginalUrl(any(CreateShortUrlRequest.class)));
    }

}
