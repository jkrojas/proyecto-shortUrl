package com.mercadolibre.shorturl.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.view.RedirectView;

import com.mercadolibre.shorturl.model.UrlShortEntity;
import com.mercadolibre.shorturl.repository.UrlShortRepository;
import com.mercadolibre.shorturl.service.to.CreateShortUrlResponse;
import com.mercadolibre.shorturl.service.to.CreateShortUrlRequest;

@SpringBootTest
class UrlShortServiceTest {

	@Test
	void contextLoads() {
	}
	
	@Mock
    private UrlShortRepository repository;

    @InjectMocks
    private UrlShortService service;
	
	@Before
    void setUp() {
        repository = mock(UrlShortRepository.class);
        service = new UrlShortService();
    }
	
	
	@Test
    public void setOriginalUrlTest() throws Exception {
        CreateShortUrlRequest request = new CreateShortUrlRequest();
        request.setUrl("http://dominio-ejemplo.com");
        when(repository.existsByShortUrl(anyString())).thenReturn(false);
        when(repository.save(any(UrlShortEntity.class))).thenReturn(new UrlShortEntity());

        CreateShortUrlResponse response = service.setOriginalUrl(request);

        assertNotNull(response);
        assertEquals(6, response.getShortUrl().length());
    }

    @Test
    public void redirectUrlTest() {
        String shortUrl = "http://dominio-ejemplo.com";
        UrlShortEntity entity = new UrlShortEntity();
        entity.setOriginalUrl("http://dominio-original.com");
        entity.setEnabled(1);
        when(repository.findByShortUrl(shortUrl)).thenReturn(entity);

        RedirectView redirectView = service.redirectUrl(shortUrl);

        assertEquals("http://dominio-ejemplo.com", redirectView.getUrl());
    }
}

