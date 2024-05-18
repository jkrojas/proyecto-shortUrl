import React, { useState, useEffect } from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import UrlForm from './components/urlForm';
import UrlDisplay from './components/urlDisplay';
import {initGA, logPageView} from './components/analytics';

function App() {
  const [shortenedUrl, setShortenedUrl] = useState('');
  useEffect(() => {
    initGA();
    logPageView();
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <h1>Acortador de URL</h1>
        <UrlForm onShortenUrl={data => setShortenedUrl(data.shortUrl)} />
        <UrlDisplay shortenedUrl={shortenedUrl} />
      </header>
    </div>
  );
}

export default App;
