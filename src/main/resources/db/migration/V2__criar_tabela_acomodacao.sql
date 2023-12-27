CREATE TABLE acomodacao (
    /*Atributos: ID, nome, localização, número de registro, quantidade de quartos
disponíveis, anfitrião.*/
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(200),
  localizao VARCHAR(700),
  numero_registro INT,
  quantidade_quartos_disponiveis INT, 
  anfitriao_id INT

)