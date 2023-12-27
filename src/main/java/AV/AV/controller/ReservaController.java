package AV.AV.controller;


import java.util.List;

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
import AV.AV.repository.ReservaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("reserva")
public class ReservaController {
    
    @Autowired
    private ReservaRepository repository;


    @PostMapping
    @Transactional
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Reserva reserva,
            UriComponentsBuilder uriBuilder) {
        Reserva reservaLocal = repository.save(reserva);
        var uri = uriBuilder.path("/reservas/{id}").buildAndExpand(reservaLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalhar(@PathVariable Long id) {
        var reserva = repository.getReferenceById(id);
        return ResponseEntity.ok(reserva);
    }

    @GetMapping
    public ResponseEntity<Page<Reserva>> listar(@PageableDefault(size = 4, sort = { "nome" }) Pageable paginacao) {
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
    public ResponseEntity<Reserva> atualizar(@RequestBody @Valid Reserva reserva) {
        Reserva reservaLocal = repository.findById(reserva.getId()).get();
        reservaLocal.setNome(reserva.getNome());
        return ResponseEntity.ok(reservaLocal);
    }
}
