package br.com.sdweb.agenda;

import br.com.sdweb.agenda.boundary.out.RelatorioOut;
import br.com.sdweb.agenda.domain.RelatorioLivroAutor;
import exceptions.Errors;
import exceptions.OutException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RelatorioDataProvider implements RelatorioOut {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<RelatorioLivroAutor> listarRelatorio() {
        try {
            String sql = "SELECT livro_id, titulo, editora, edicao, ano_publicacao, autor_nome, assunto_descricao FROM vw_relatorio_livros_autores";
            
            return jdbcTemplate.query(sql, (rs, rowNum) -> RelatorioLivroAutor.builder()
                    .livroId(rs.getString("livro_id"))
                    .titulo(rs.getString("titulo"))
                    .editora(rs.getString("editora"))
                    .edicao(rs.getInt("edicao"))
                    .anoPublicacao(rs.getString("ano_publicacao"))
                    .autorNome(rs.getString("autor_nome"))
                    .assuntoDescricao(rs.getString("assunto_descricao"))
                    .build());
        } catch (Exception e) {
            throw new OutException("Falha de comunicacao ao tentar listar", e, Errors.ERROR0006);
        }
    }
}
