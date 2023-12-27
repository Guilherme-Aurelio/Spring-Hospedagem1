CREATE TABLE reserva( 
    /*ID, data de início, data de fim, acomodação reservada, hóspede que
realizou a reserva.*/
    id INT AUTO_INCREMENT PRIMARY KEY,
    data_inicio VARCHAR(100),
    data_fim VARCHAR(100),
    acomodacao_id INT, 
    hospede_id INT

);