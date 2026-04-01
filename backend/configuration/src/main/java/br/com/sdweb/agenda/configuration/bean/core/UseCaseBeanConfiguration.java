package br.com.sdweb.agenda.configuration.bean.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.sdweb.agenda.AutorDataProvider;
import br.com.sdweb.agenda.usecase.EditarAutorUseCase;
import br.com.sdweb.agenda.usecase.ExcluirAutorUseCase;
import br.com.sdweb.agenda.usecase.GerarRelatorioUseCase;
import br.com.sdweb.agenda.usecase.SalvarAssuntoUseCase;
import br.com.sdweb.agenda.usecase.SalvarAutorUseCase;
import br.com.sdweb.agenda.usecase.SalvarLivroUseCase;
import br.com.sdweb.agenda.usecase.BuscarAutorPorIdUseCase;
import br.com.sdweb.agenda.usecase.ListarAutoresUseCase;
import br.com.sdweb.agenda.usecase.BuscarAssuntoPorIdUseCase;
import br.com.sdweb.agenda.usecase.ListarAssuntosUseCase;
import br.com.sdweb.agenda.usecase.EditarAssuntoUseCase;
import br.com.sdweb.agenda.usecase.ExcluirAssuntoUseCase;
import br.com.sdweb.agenda.usecase.BuscarLivroPorIdUseCase;
import br.com.sdweb.agenda.usecase.ListarLivrosUseCase;
import br.com.sdweb.agenda.usecase.EditarLivroUseCase;
import br.com.sdweb.agenda.usecase.ExcluirLivroUseCase;

@Configuration
public class UseCaseBeanConfiguration {


    @Bean
    public SalvarAutorUseCase salvarAutorUseCase(AutorDataProvider autorDataProvider){
        return new SalvarAutorUseCase(autorDataProvider);
    }

    @Bean
    public EditarAutorUseCase editarAutorUseCase(AutorDataProvider autorDataProvider){
        return new EditarAutorUseCase(autorDataProvider);
    }

    @Bean
    public ExcluirAutorUseCase excluirAutorUseCase(AutorDataProvider autorDataProvider){
        return new ExcluirAutorUseCase(autorDataProvider);
    }

    @Bean
    public BuscarAutorPorIdUseCase buscarAutorPorIdUseCase(AutorDataProvider autorDataProvider){
        return new BuscarAutorPorIdUseCase(autorDataProvider);
    }

    @Bean
    public ListarAutoresUseCase listarAutoresUseCase(AutorDataProvider autorDataProvider){
        return new ListarAutoresUseCase(autorDataProvider);
    }

    @Bean
    public SalvarAssuntoUseCase salvarAssuntoUseCase(br.com.sdweb.agenda.AssuntoDataProvider assuntoDataProvider){
        return new SalvarAssuntoUseCase(assuntoDataProvider);
    }

    @Bean
    public BuscarAssuntoPorIdUseCase buscarAssuntoPorIdUseCase(br.com.sdweb.agenda.AssuntoDataProvider assuntoDataProvider){
        return new BuscarAssuntoPorIdUseCase(assuntoDataProvider);
    }

    @Bean
    public ListarAssuntosUseCase listarAssuntosUseCase(br.com.sdweb.agenda.AssuntoDataProvider assuntoDataProvider){
        return new ListarAssuntosUseCase(assuntoDataProvider);
    }

    @Bean
    public EditarAssuntoUseCase editarAssuntoUseCase(br.com.sdweb.agenda.AssuntoDataProvider assuntoDataProvider){
        return new EditarAssuntoUseCase(assuntoDataProvider);
    }

    @Bean
    public ExcluirAssuntoUseCase excluirAssuntoUseCase(br.com.sdweb.agenda.AssuntoDataProvider assuntoDataProvider){
        return new ExcluirAssuntoUseCase(assuntoDataProvider);
    }

    @Bean
    public SalvarLivroUseCase salvarLivroUseCase(br.com.sdweb.agenda.LivroDataProvider livroDataProvider){
        return new SalvarLivroUseCase(livroDataProvider);
    }

    @Bean
    public BuscarLivroPorIdUseCase buscarLivroPorIdUseCase(br.com.sdweb.agenda.LivroDataProvider livroDataProvider){
        return new BuscarLivroPorIdUseCase(livroDataProvider);
    }

    @Bean
    public ListarLivrosUseCase listarLivrosUseCase(br.com.sdweb.agenda.LivroDataProvider livroDataProvider){
        return new ListarLivrosUseCase(livroDataProvider);
    }

    @Bean
    public EditarLivroUseCase editarLivroUseCase(br.com.sdweb.agenda.LivroDataProvider livroDataProvider){
        return new EditarLivroUseCase(livroDataProvider);
    }

    @Bean
    public ExcluirLivroUseCase excluirLivroUseCase(br.com.sdweb.agenda.LivroDataProvider livroDataProvider){
        return new ExcluirLivroUseCase(livroDataProvider);
    }

    @Bean
    public GerarRelatorioUseCase gerarRelatorioUseCase(br.com.sdweb.agenda.RelatorioDataProvider relatorioDataProvider){
        return new GerarRelatorioUseCase(relatorioDataProvider);
    }
}
