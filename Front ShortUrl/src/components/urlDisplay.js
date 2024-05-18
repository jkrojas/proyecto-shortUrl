import React, { useEffect } from 'react';
import { logEvent } from './analytics';

/* Esta funcion retorna la URL acortada*/
function UrlDisplay({ shortenedUrl }) {
  const fullShortUrl = `https://api.compumundohipermegared.cl/url/${shortenedUrl}`;
  
  useEffect(() => {
    if (shortenedUrl) {
      logEvent('[URLRedirect] URL Generada correctamente', shortenedUrl);
    }
  }, [shortenedUrl]);

  return (
    <div style={{ marginTop: '20px', textAlign: 'center', display: 'flex'}}>
      {shortenedUrl ? (
        <p>
          URL Generada: <a href={fullShortUrl} target="_blank" rel="noopener noreferrer">{fullShortUrl}</a>
        </p>
      ) : (
        <p></p>
      )}
    </div>
  );
}

export default UrlDisplay;