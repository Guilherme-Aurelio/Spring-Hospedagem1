package AV.AV.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import AV.AV.model.Hospede;
import AV.AV.model.Reserva;
import AV.AV.repository.HospedeRepository;
import AV.AV.repository.ReservaRepository;
import AV.AV.service.ReservaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("reserva")
public class ReservaController {
    
    @Autowired
    private ReservaRepository repository;

    @Autowired
    private HospedeRepository Hrepository;

    @Autowired
    private ReservaService reservaService;


    private final int limiteMaximoReservas = 3;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Reserva reserva,
                                            UriComponentsBuilder uriBuilder) {
        Hospede hospede = Hrepository.findById(reserva.getHospede().getId()).orElse(null);

        if (hospede == null) {
            return ResponseEntity.badRequest().body("Hóspede não encontrado");
        }

        if (reservaService.verificarLimiteReservas(hospede, limiteMaximoReservas)) {
            reservaService.realizarReserva(hospede, reserva, limiteMaximoReservas);

            var uri = uriBuilder.path("/reservas/{id}").buildAndExpand(reserva.getId()).toUri();
            return ResponseEntity.created(uri).build();
        } else {
            return ResponseEntity.badRequest().body("O hóspede atingiu o limite de reservas permitidas.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalhar(@PathVariable Long id) {
        var reserva = repository.getReferenceById(id);
        return ResponseEntity.ok(reserva);
    }

    @GetMapping
    public ResponseEntity<Page<Reserva>> listar(@PageableDefault(size = 4, sort = { "id" }) Pageable paginacao) {
        var reservas = repository.findAll(paginacao);
        return ResponseEntity.ok(reservas);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        var reserva = repository.getReferenceById(id);
        repository.delete(reserva);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Reserva> atualizar(@PathVariable Long id, @RequestBody @Valid Reserva reserva) {
        Optional<Reserva> optionalReserva = repository.findById(id);

        if (optionalReserva.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
    
        Reserva reservaLocal = optionalReserva.get();
        
        // Atualize apenas os campos que devem ser modificados
        reservaLocal.setDataInicio(reserva.getDataInicio());
        reservaLocal.setDataFim(reserva.getDataFim());
        reservaLocal.setAcomodacao(reserva.getAcomodacao());
        reservaLocal.setHospede(reserva.getHospede());
    
        Reserva reservaAtualizada = repository.save(reservaLocal);
        return ResponseEntity.ok(reservaAtualizada);
    }
}
