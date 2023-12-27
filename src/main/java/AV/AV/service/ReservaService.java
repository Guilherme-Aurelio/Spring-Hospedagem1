package AV.AV.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AV.AV.infra.exceptions.LimiteReservasException;
import AV.AV.model.Acomodacao;
import AV.AV.model.Hospede;
import AV.AV.model.Reserva;
import AV.AV.repository.AcomodacaoRepository;
import AV.AV.repository.HospedeRepository;
import AV.AV.repository.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private HospedeRepository hospedeRepository;

    @Autowired
    private AcomodacaoRepository acomodacaoRepository;

    public Reserva cadastrar(Long hospedeId, Long acomodacaoId) {
        Hospede hospede = hospedeRepository.findById(hospedeId)
        .orElseThrow(() -> new EntityNotFoundException(
            "Hóspede nao encontrado"
        ));

        if (hospede.getReservas().size() >= hospede.getLimiteReservas()) {
            throw new LimiteReservasException("Limite de reservas atingido");
        }

        Acomodacao acomodacao = AcomodacaoRepository.findById(acomodacaoId)
            .orElseThrow(() -> new EntityNotFoundException("Acomodação não encontrada"));

        Reserva reserva = new Reserva();
        reserva.setHospede(hospede);
        reserva.setAcomodacao(acomodacao);

        reserva = reservaRepository.save(reserva);

        return reserva;
    }
}
