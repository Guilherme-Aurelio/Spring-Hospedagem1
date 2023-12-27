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

import AV.AV.model.Anfitriao;
import AV.AV.repository.AnfitriaoRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("anfitriao")

public class AnfitriaoController {
    
    @Autowired
    private AnfitriaoRepository repository;

    @PostMapping
    @Transactional
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Anfitriao anfitriao,
            UriComponentsBuilder uriBuilder) {
        Anfitriao anfitriaoLocal = repository.save(anfitriao);
        var uri = uriBuilder.path("/anfitriaos/{id}").buildAndExpand(anfitriaoLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalhar(@PathVariable Long id) {
        var anfitriao = repository.getReferenceById(id);
        return ResponseEntity.ok(anfitriao);
    }

    @GetMapping
    public ResponseEntity<Page<Anfitriao>> listar(@PageableDefault(size = 4, sort = { "nome" }) Pageable paginacao) {
        var anfitriaos = repository.findAll(paginacao);
        return ResponseEntity.ok(anfitriaos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id) {
        var anfitriao = repository.getReferenceById(id);
        repository.delete(anfitriao);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Anfitriao> atualizar(@RequestBody @Valid Anfitriao anfitriao) {
        Anfitriao anfitriaoLocal = repository.findById(anfitriao.getId()).get();
        anfitriaoLocal.setNome(anfitriao.getNome());
        return ResponseEntity.ok(anfitriaoLocal);
    }
}
