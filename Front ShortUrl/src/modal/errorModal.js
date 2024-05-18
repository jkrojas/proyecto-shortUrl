import React from 'react';
import { Modal, Button } from 'react-bootstrap';

/* Modal de error mostrada cuando los servicios retornan una excepción*/
function ErrorModal({ show, handleClose, errorMessage }) {
  return (
<Modal show={show} onHide={handleClose} dialogClassName="modal-dialog" contentClassName="custom-modal">
      <Modal.Header closeButton>
        <Modal.Title>Error</Modal.Title>
      </Modal.Header>
      <Modal.Body>{errorMessage}</Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Cerrar
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default ErrorModal;