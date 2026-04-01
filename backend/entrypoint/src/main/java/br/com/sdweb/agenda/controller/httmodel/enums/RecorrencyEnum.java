package br.com.sdweb.agenda.controller.httmodel.enums;

import lombok.Getter;

@Getter
public enum RecorrencyEnum {

    ONCE("ONCE"),
    DAILY("DAILY"),
    WEEKLY("WEKKLY"),
    MONTHLY("MONTHLY"),
    YEARLY("YEARLY");

    private String value;

    RecorrencyEnum(String value) {
        this.value = value;
    }

    public static RecorrencyEnum findByValue(String value) {
        for (RecorrencyEnum recorrencyEnum : values()) {
            if (recorrencyEnum.getValue().equals(value)) {
                return recorrencyEnum;
            }
        }

        // TODO AJUSTE DE RETORNO: deve retorno uma exception
        return null;
    }

}
