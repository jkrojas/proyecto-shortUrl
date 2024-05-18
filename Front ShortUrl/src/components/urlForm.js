import React, { useState } from 'react';
import axios from 'axios';
import ErrorModal from '../modal/errorModal';
import { logEvent } from './analytics';

/* Esta funcion contruye una interfaz para ingresar la URL que se desea acortar y se lanzara un error cuando este falle.*/
function UrlForm({ onShortenUrl }) {
  const [url, setUrl] = useState('');
  const [error, setError] = useState('');
  const [showModal, setShowModal] = useState(false);

  const handleSubmit = async (event) => {
    event.preventDefault();
    if (!url.trim()) {
      setError('Por favor ingresa una URL válida.');
      setShowModal(true);
      logEvent('[OriginalURL] Intento fallido de creación de URL sin datos');
      return;
    }
    
    try {
      const response = await axios.post('https://api.compumundohipermegared.cl/url/originalUrl', { url });
      onShortenUrl(response.data);
      logEvent('[OriginalURL] URL Creada correctamente', url);
    } catch (error) {
      setError(error.response && error.response.data ? error.response.data.message : error.message);
      setShowModal(true);
      onShortenUrl('');
      logEvent('[OriginalURL] No se pudo crear la URL:', url);
    }
  };

  return (
    <div style={{ marginTop: '20px' }}>
      <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
        <input
          type="text"
          value={url}
          onChange={e => setUrl(e.target.value)}
          placeholder="Ingresa tu URL aquí"
          style={{ width: '300px', height: '40px', marginBottom: '10px', display: 'flex' }}
        />
        <button type="submit" style={{ width: '150px', height: '40px', borderRadius: '5px', backgroundColor: '#3483fa', color: 'white', border: 'none', cursor: 'pointer' }}>
          Acortar URL
        </button>
        <ErrorModal 
        show={showModal} 
        handleClose={() => setShowModal(false)} 
        errorMessage={error} 
      />
      </form>
    </div>
  );
}

export default UrlForm;