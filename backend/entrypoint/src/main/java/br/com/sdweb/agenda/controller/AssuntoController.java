package br.com.sdweb.agenda.controller;

import br.com.sdweb.agenda.boundary.in.SalvarAssuntoIn;
import br.com.sdweb.agenda.boundary.in.BuscarAssuntoPorIdIn;
import br.com.sdweb.agenda.boundary.in.ListarAssuntosIn;
import br.com.sdweb.agenda.boundary.in.EditarAssuntoIn;
import br.com.sdweb.agenda.boundary.in.ExcluirAssuntoIn;
import br.com.sdweb.agenda.domain.Assunto;
import br.com.sdweb.agenda.controller.httmodel.request.AssuntoRequest;
import br.com.sdweb.agenda.controller.httmodel.response.AssuntoResponse;
import br.com.sdweb.agenda.mapper.AssuntoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/assuntos")
public class AssuntoController {

    private final SalvarAssuntoIn salvarAssuntoIn;
    private final BuscarAssuntoPorIdIn buscarAssuntoPorIdIn;
    private final ListarAssuntosIn listarAssuntosIn;
    private final EditarAssuntoIn editarAssuntoIn;
    private final ExcluirAssuntoIn excluirAssuntoIn;

    public AssuntoController(SalvarAssuntoIn salvarAssuntoIn, BuscarAssuntoPorIdIn buscarAssuntoPorIdIn, ListarAssuntosIn listarAssuntosIn, EditarAssuntoIn editarAssuntoIn, ExcluirAssuntoIn excluirAssuntoIn) {
        this.salvarAssuntoIn = salvarAssuntoIn;
        this.buscarAssuntoPorIdIn = buscarAssuntoPorIdIn;
        this.listarAssuntosIn = listarAssuntosIn;
        this.editarAssuntoIn = editarAssuntoIn;
        this.excluirAssuntoIn = excluirAssuntoIn;
    }

    @PostMapping
    public ResponseEntity<AssuntoResponse> salvar(@RequestBody AssuntoRequest request) {
        Assunto assuntoSalvo = salvarAssuntoIn.salvar(AssuntoMapper.toDomain(request));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(assuntoSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(AssuntoMapper.toResponse(assuntoSalvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssuntoResponse> buscarPorId(@PathVariable String id) {
        Assunto assunto = buscarAssuntoPorIdIn.buscarPorId(id);
        return ResponseEntity.ok(AssuntoMapper.toResponse(assunto));
    }

    @GetMapping
    public ResponseEntity<List<AssuntoResponse>> listarTodos() {
        List<Assunto> assuntos = listarAssuntosIn.listar();
        return ResponseEntity.ok(assuntos.stream().map(AssuntoMapper::toResponse).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssuntoResponse> atualizar(@PathVariable String id, @RequestBody AssuntoRequest request) {
        Assunto assunto = AssuntoMapper.toDomain(request);
        assunto.setId(id);
        Assunto atualizado = editarAssuntoIn.editar(assunto);
        return ResponseEntity.ok(AssuntoMapper.toResponse(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id) {
        excluirAssuntoIn.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
