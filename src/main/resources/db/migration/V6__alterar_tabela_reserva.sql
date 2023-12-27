     ALTER TABLE reserva ADD CONSTRAINT fk_acomodacao FOREIGN KEY (acomodacao_id) REFERENCES acomodacao(id);
     ALTER TABLE reserva ADD CONSTRAINT fk_hospede FOREIGN KEY (hospede_id) REFERENCES hospede(id);
