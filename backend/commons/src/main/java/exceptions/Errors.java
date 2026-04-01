package exceptions;

import lombok.Getter;

@Getter
public enum Errors {

    ERROR0001("0001", "Valor monetario incorreto ou negativo"),
    ERROR0002("0002", "Falha de comunicacao ao tentar persistir"),
    ERROR0003("0003", "Falha de comunicacao ao tentar buscar"),
    ERROR0004("0004", "Falha de comunicacao ao tentar deletar"),
    ERROR0005("0005", "Falha de comunicacao ao tentar atualizar"),
    ERROR0006("0006", "Falha de comunicacao ao tentar listar"),
    ERROR0007("0007", "Falha de comunicacao ao tentar buscar por id"),
    ERROR0008("0008", "Assunto invalido"),
    ERROR0009("0009", "Autor invalido");


    private String code;
    private String message;

    private Errors(String code, String message){
        this.code = code;
        this.message = message;
    }
}
