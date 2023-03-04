package br.com.cwi.api.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Visualizacao {
    PRIVADO(Tipos.PRIVADO),
    PUBLICO(Tipos.PUBLICO);

    public static class Tipos {
        public static String PRIVADO = "PRIVADO";
        public static String  PUBLICO = "PUBLICO";
    }

    private final String tipo;
}
