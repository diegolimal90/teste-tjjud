package br.com.sdweb.agenda.controller;

import br.com.sdweb.agenda.boundary.in.EditarAutorIn;
import br.com.sdweb.agenda.boundary.in.ExcluirAutorIn;
import br.com.sdweb.agenda.boundary.in.SalvarAutorIn;
import br.com.sdweb.agenda.boundary.in.BuscarAutorPorIdIn;
import br.com.sdweb.agenda.boundary.in.ListarAutoresIn;
import br.com.sdweb.agenda.domain.Autor;
import br.com.sdweb.agenda.controller.httmodel.request.AutorRequest;
import br.com.sdweb.agenda.controller.httmodel.response.AutorResponse;
import br.com.sdweb.agenda.mapper.AutorMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/autores")
public class AutorController {
    private final SalvarAutorIn salvarAutorIn;
    private final EditarAutorIn editarAutorIn;
    private final ExcluirAutorIn excluirAutorIn;
    private final BuscarAutorPorIdIn buscarAutorPorIdIn;
    private final ListarAutoresIn listarAutoresIn;

    public AutorController(SalvarAutorIn salvarAutorIn, EditarAutorIn editarAutorIn, ExcluirAutorIn excluirAutorIn, BuscarAutorPorIdIn buscarAutorPorIdIn, ListarAutoresIn listarAutoresIn) {
        this.salvarAutorIn = salvarAutorIn;
        this.editarAutorIn = editarAutorIn;
        this.excluirAutorIn = excluirAutorIn;
        this.buscarAutorPorIdIn = buscarAutorPorIdIn;
        this.listarAutoresIn = listarAutoresIn;
    }

    @PostMapping
    public ResponseEntity<AutorResponse> salvar(@RequestBody AutorRequest request) {
        Autor autorSalvo = salvarAutorIn.salvar(AutorMapper.to(request));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponse> buscarPorId(@PathVariable String id) {
        Autor autorEncontrado = buscarAutorPorIdIn.buscarPorId(id);
        return ResponseEntity.ok(AutorMapper.toResponse(autorEncontrado));
    }

    @GetMapping
    public ResponseEntity<List<AutorResponse>> listarTodos() {
        List<Autor> autores = listarAutoresIn.listar();
        List<AutorResponse> responseList = autores.stream()
                .map(AutorMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorResponse> atualizar(@PathVariable String id, @RequestBody AutorRequest request) {
        Autor autorParaAtualizar = AutorMapper.to(request);
        autorParaAtualizar.setId(id);
        
        editarAutorIn.editar(autorParaAtualizar);
        
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id) {
        excluirAutorIn.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
