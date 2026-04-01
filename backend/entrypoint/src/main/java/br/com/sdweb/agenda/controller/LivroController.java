package br.com.sdweb.agenda.controller;

import br.com.sdweb.agenda.boundary.in.SalvarLivroIn;
import br.com.sdweb.agenda.boundary.in.BuscarLivroPorIdIn;
import br.com.sdweb.agenda.boundary.in.ListarLivrosIn;
import br.com.sdweb.agenda.boundary.in.EditarLivroIn;
import br.com.sdweb.agenda.boundary.in.ExcluirLivroIn;
import br.com.sdweb.agenda.domain.Livro;
import br.com.sdweb.agenda.controller.httmodel.request.LivroRequest;
import br.com.sdweb.agenda.controller.httmodel.response.LivroResponse;
import br.com.sdweb.agenda.mapper.LivroMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/livros")
public class LivroController {

    private final SalvarLivroIn salvarLivroIn;
    private final BuscarLivroPorIdIn buscarLivroPorIdIn;
    private final ListarLivrosIn listarLivrosIn;
    private final EditarLivroIn editarLivroIn;
    private final ExcluirLivroIn excluirLivroIn;

    public LivroController(SalvarLivroIn salvarLivroIn, BuscarLivroPorIdIn buscarLivroPorIdIn, ListarLivrosIn listarLivrosIn, EditarLivroIn editarLivroIn, ExcluirLivroIn excluirLivroIn) {
        this.salvarLivroIn = salvarLivroIn;
        this.buscarLivroPorIdIn = buscarLivroPorIdIn;
        this.listarLivrosIn = listarLivrosIn;
        this.editarLivroIn = editarLivroIn;
        this.excluirLivroIn = excluirLivroIn;
    }

    @PostMapping
    public ResponseEntity<LivroResponse> salvar(@RequestBody LivroRequest request) {
        Livro livroSalvo = salvarLivroIn.salvar(LivroMapper.toDomain(request));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(livroSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(LivroMapper.toResponse(livroSalvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponse> buscarPorId(@PathVariable String id) {
        Livro livro = buscarLivroPorIdIn.buscarPorId(id);
        return ResponseEntity.ok(LivroMapper.toResponse(livro));
    }

    @GetMapping
    public ResponseEntity<List<LivroResponse>> listarTodos() {
        List<Livro> livros = listarLivrosIn.listar();
        return ResponseEntity.ok(livros.stream().map(LivroMapper::toResponse).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponse> atualizar(@PathVariable String id, @RequestBody LivroRequest request) {
        Livro livro = LivroMapper.toDomain(request);
        livro.setId(id);
        Livro atualizado = editarLivroIn.editar(livro);
        return ResponseEntity.ok(LivroMapper.toResponse(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id) {
        excluirLivroIn.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
