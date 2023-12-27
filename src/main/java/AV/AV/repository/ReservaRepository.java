package AV.AV.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import AV.AV.model.Hospede;
import AV.AV.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{
     List<Reserva> findByHospede(Hospede hospede);
}
