package br.com.sdweb.agenda.controller.httmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseHttpModel<T> {
    private String status;
    private Integer code;
    private Long timestamp;
    private String message;
    private List<T> data;
}
