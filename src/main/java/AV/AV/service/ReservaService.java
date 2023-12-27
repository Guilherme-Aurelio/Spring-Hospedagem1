package AV.AV.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AV.AV.model.Hospede;
import AV.AV.model.Reserva;

import AV.AV.repository.ReservaRepository;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public boolean verificarLimiteReservas(Hospede hospede, int limiteMaximo) {
        List<Reserva> reservas = reservaRepository.findByHospede(hospede);
        return reservas.size() < limiteMaximo;
    }

    public boolean realizarReserva(Hospede hospede, Reserva reserva, int limiteMaximo) {
        if (verificarLimiteReservas(hospede, limiteMaximo)) {
            reserva.setHospede(hospede);
            reservaRepository.save(reserva);
            return true;
        }
        return false;
    }
}
