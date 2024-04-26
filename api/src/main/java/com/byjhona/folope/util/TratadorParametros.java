package com.byjhona.folope.util;

public class TratadorParametros {
    public static String tratar(String sortear, String genero) {
        String parametros = "?";

        if (sortear != null) {
            parametros += "sort_by=" + sortear + "&";
        }
        if (genero != null) {
            parametros += "with_genres=" + genero + "&";
        }

        parametros += "language=pt-BR";

        parametros = parametros.substring(0, parametros.length() -1);
        return parametros;

    }
}
