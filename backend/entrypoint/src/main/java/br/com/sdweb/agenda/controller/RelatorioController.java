package br.com.sdweb.agenda.controller;

import br.com.sdweb.agenda.boundary.in.GerarRelatorioIn;
import br.com.sdweb.agenda.domain.RelatorioLivroAutor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/relatorios")
public class RelatorioController {

    private final GerarRelatorioIn gerarRelatorioIn;

    public RelatorioController(GerarRelatorioIn gerarRelatorioIn) {
        this.gerarRelatorioIn = gerarRelatorioIn;
    }

    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> gerar() {
        try {
            List<RelatorioLivroAutor> relatorio = gerarRelatorioIn.gerarRelatorio();

            InputStream relatorioStream = getClass().getResourceAsStream("/reports/relatorio_livros_jasper.jrxml");
            if (relatorioStream == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(relatorioStream);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(relatorio);

            Map<String, Object> parameters = new HashMap<>();
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "relatorio_livros.pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            
        } catch (JRException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
