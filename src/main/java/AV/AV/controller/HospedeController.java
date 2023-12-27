package AV.AV.controller;


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
import AV.AV.repository.HospedeRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("hospede")
public class HospedeController {
    
    @Autowired
    private HospedeRepository repository;

    @PostMapping
    @Transactional
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Hospede hospede,
            UriComponentsBuilder uriBuilder) {
        Hospede hospedeLocal = repository.save(hospede);
        var uri = uriBuilder.path("/hospedes/{id}").buildAndExpand(hospedeLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalhar(@PathVariable Long id) {
        var hospede = repository.getReferenceById(id);
        return ResponseEntity.ok(hospede);
    }

    @GetMapping
    public ResponseEntity<Page<Hospede>> listar(@PageableDefault(size = 4, sort = { "nome" }) Pageable paginacao) {
        var hospedes = repository.findAll(paginacao);
        return ResponseEntity.ok(hospedes);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        var hospede = repository.getReferenceById(id);
        repository.delete(hospede);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Hospede> atualizar(@RequestBody @Valid Hospede hospede) {
        Hospede hospedeLocal = repository.findById(hospede.getId()).get();
        hospedeLocal.setNome(hospede.getNome());
        return ResponseEntity.ok(hospedeLocal);
    }
}