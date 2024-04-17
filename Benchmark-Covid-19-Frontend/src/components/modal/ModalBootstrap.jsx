import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

function Example({ closeModal, openSecondModal }) {
  const handleButtonClick = (placeType) => {

    closeModal();
    openSecondModal(placeType);
  };

  return (
    <>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton style={{ textAlign: 'center' }}>
          <Modal.Title>Obter Benchmark</Modal.Title>
        </Modal.Header>
        <Modal.Body style={{ textAlign: 'center' }}>Escolha uma categoria</Modal.Body>
        <Modal.Footer style={{ justifyContent: 'center' }}>
          <div style={{ display: 'flex', justifyContent: 'center' }}>
            <Button variant="secondary" onClick={handleButtonClick} style={{ marginRight: '10px' }}>
              País
            </Button>
            <Button variant="secondary" onClick={handleButtonClick} style={{ marginRight: '10px' }}>
              Estado
            </Button>
            <Button variant="secondary" onClick={handleButtonClick}>
              Cidade
            </Button>
          </div>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default Example;
