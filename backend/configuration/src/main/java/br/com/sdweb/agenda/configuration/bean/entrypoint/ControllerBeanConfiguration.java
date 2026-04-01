package br.com.sdweb.agenda.configuration.bean.entrypoint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.sdweb.agenda.controller.AutorController;
import br.com.sdweb.agenda.usecase.EditarAutorUseCase;
import br.com.sdweb.agenda.usecase.ExcluirAutorUseCase;
import br.com.sdweb.agenda.usecase.SalvarAutorUseCase;
import br.com.sdweb.agenda.usecase.BuscarAutorPorIdUseCase;
import br.com.sdweb.agenda.usecase.ListarAutoresUseCase;

@Configuration
public class ControllerBeanConfiguration {

    @Bean
    public AutorController autorController(SalvarAutorUseCase salvarAutorUseCase, 
                                           EditarAutorUseCase editarAutorUseCase,
                                           ExcluirAutorUseCase excluirAutorUseCase,
                                           BuscarAutorPorIdUseCase buscarAutorPorIdUseCase,
                                           ListarAutoresUseCase listarAutoresUseCase) {
        return new AutorController(salvarAutorUseCase, editarAutorUseCase, excluirAutorUseCase, buscarAutorPorIdUseCase, listarAutoresUseCase);
    }

    @Bean
    public br.com.sdweb.agenda.controller.AssuntoController assuntoController(
            br.com.sdweb.agenda.usecase.SalvarAssuntoUseCase salvarAssuntoUseCase,
            br.com.sdweb.agenda.usecase.BuscarAssuntoPorIdUseCase buscarAssuntoPorIdUseCase,
            br.com.sdweb.agenda.usecase.ListarAssuntosUseCase listarAssuntosUseCase,
            br.com.sdweb.agenda.usecase.EditarAssuntoUseCase editarAssuntoUseCase,
            br.com.sdweb.agenda.usecase.ExcluirAssuntoUseCase excluirAssuntoUseCase
    ) {
        return new br.com.sdweb.agenda.controller.AssuntoController(
                salvarAssuntoUseCase, buscarAssuntoPorIdUseCase, listarAssuntosUseCase, editarAssuntoUseCase, excluirAssuntoUseCase
        );
    }

    @Bean
    public br.com.sdweb.agenda.controller.LivroController livroController(
            br.com.sdweb.agenda.usecase.SalvarLivroUseCase salvarLivroUseCase,
            br.com.sdweb.agenda.usecase.BuscarLivroPorIdUseCase buscarLivroPorIdUseCase,
            br.com.sdweb.agenda.usecase.ListarLivrosUseCase listarLivrosUseCase,
            br.com.sdweb.agenda.usecase.EditarLivroUseCase editarLivroUseCase,
            br.com.sdweb.agenda.usecase.ExcluirLivroUseCase excluirLivroUseCase
    ) {
        return new br.com.sdweb.agenda.controller.LivroController(
                salvarLivroUseCase, buscarLivroPorIdUseCase, listarLivrosUseCase, editarLivroUseCase, excluirLivroUseCase
        );
    }

    @Bean
    public br.com.sdweb.agenda.controller.RelatorioController relatorioController(br.com.sdweb.agenda.usecase.GerarRelatorioUseCase gerarRelatorioUseCase) {
        return new br.com.sdweb.agenda.controller.RelatorioController(gerarRelatorioUseCase);
    }
}
