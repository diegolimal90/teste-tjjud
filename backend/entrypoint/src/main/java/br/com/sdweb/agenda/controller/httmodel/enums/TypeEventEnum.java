package br.com.sdweb.agenda.controller.httmodel.enums;

import lombok.Getter;

@Getter
public enum TypeEventEnum {

    ATENDIMENTO_AO_CLIENTE("Atendimento ao Clinte"),
    SOLICITACAO_ORCAMENTO("Solicitação de Orçamento"),
    MANUTENCAO_PREVENTIVA("Manutenção Preventiva"),
    MANUTENCAO_PREDITIVA("Manutenção Preditiva");

    private String value;

    TypeEventEnum(String value) {
        this.value = value;
    }

    public static TypeEventEnum findByValue(String value) {
        for (TypeEventEnum typeEventEnum : values()) {
            if (typeEventEnum.getValue().equals(value)) {
                return typeEventEnum;
            }
        }

        // TODO AJUSTE DE RETORNO: deve retorno uma exception
        return null;
    }

}
