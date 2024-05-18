import ReactGA from 'react-ga';

const TRACKING_ID = "G-D7FKBYE01R";

/*Componente que se encarga de registrar los eventos*/

export const initGA = () => {
   ReactGA.initialize(TRACKING_ID);
};

export const logPageView = () => {
   ReactGA.set({ page: window.location.pathname });
   ReactGA.pageview(window.location.pathname);
};

export const logEvent = (category = '', action = '') => {
   if (category && action) {
     ReactGA.event({ category, action });
   }
};

export const logException = (description = '', fatal = false) => {
   if (description) {
     ReactGA.exception({ description, fatal });
   }
};