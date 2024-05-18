import axios from 'axios';
import { logEvent } from './analytics';

const API_BASE_URL = 'https://api.compumundohipermegared.cl/url';

/*Aqui es donde esta la llamada a los microservicios*/
export const getOriginalUrl = async (shortUrl) => {
  try {
    const response = await axios.get(`${API_BASE_URL}/redirect`, { params: { url: shortUrl } });
    logEvent('[API][Redirect] Redireccion de URL');
    return response.data;
  } catch (error) {
    console.error('Error redirigiendo a la URL original:', error);
    logEvent('[API][Redirect][ERROR] Error al redireccionar la url', error);
    throw error;
  }
};

export const createShortUrl = async (originalUrl) => {
  try {
    const response = await axios.post(`${API_BASE_URL}/originalUrl`, { originalUrl });
    logEvent('[API][OriginalURL] Generación de URL acortada');
    return response.data;
  } catch (error) {
    console.error('Error al crear una url acortada:', error);
    logEvent('[API][OriginalURL][ERROR] Error al generar la url acortada', error);
    throw error;
  }
};