package br.com.sdweb.agenda.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({
        "br.com.sdweb.agenda.configuration",
        "br.com.sdweb.agenda.entrypoint",
        "br.com.sdweb.agenda.controller.api",
        "br.com.sdweb.agenda.repository",
        "br.com.sdweb.agenda"
})
@EntityScan(basePackages = {"br.com.sdweb.agenda.entities"})
@EnableJpaRepositories(basePackages = {"br.com.sdweb.agenda.repository"})
public class TesteApplication {

    public static void main(String[] args) {
        SpringApplication.run(TesteApplication.class, args);
    }

}
