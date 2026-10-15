package controle;

import nucleo.Config;

public final class ValidadorEntrada {

    private static final int RAIO_MAXIMO = 1000;

    private ValidadorEntrada() {
    }

    public static int lerInteiro(String texto, String campo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("O campo " + campo + " está vazio.");
        }
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O campo " + campo + " deve ser um número inteiro (valor digitado: \"" + texto.trim() + "\").");
        }
    }

    public static int lerX(String texto, String campo) {
        int valor = lerInteiro(texto, campo);
        if (valor < -Config.LIMITE_PLANO || valor > Config.LIMITE_PLANO) {
            throw new IllegalArgumentException("O campo " + campo + " deve estar entre " + (-Config.LIMITE_PLANO) + " e " + Config.LIMITE_PLANO + ".");
        }
        return valor;
    }

    public static int lerY(String texto, String campo) {
        int valor = lerInteiro(texto, campo);
        if (valor < -Config.LIMITE_PLANO || valor > Config.LIMITE_PLANO) {
            throw new IllegalArgumentException("O campo " + campo + " deve estar entre " + (-Config.LIMITE_PLANO) + " e " + Config.LIMITE_PLANO + ".");
        }
        return valor;
    }

    public static int lerRaio(String texto, String campo) {
        int valor = lerInteiro(texto, campo);
        if (valor < 1 || valor > RAIO_MAXIMO) {
            throw new IllegalArgumentException("O campo " + campo + " deve estar entre 1 e " + RAIO_MAXIMO + ".");
        }
        return valor;
    }
}