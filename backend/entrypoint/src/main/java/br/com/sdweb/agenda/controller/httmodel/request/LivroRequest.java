package br.com.sdweb.agenda.controller.httmodel.request;

import java.math.BigDecimal;
import java.util.List;

public record LivroRequest(
    String titulo,
    String editora,
    Integer edicao,
    String anoPublicacao,
    BigDecimal valor,
    List<String> autores,
    List<String> assuntos
) {
}
