package br.com.sdweb.agenda.controller.httmodel.response;

import java.math.BigDecimal;

public record LivroResponse(
    String id,
    String titulo,
    String editora,
    Integer edicao,
    String anoPublicacao,
    BigDecimal valor
) {
}
