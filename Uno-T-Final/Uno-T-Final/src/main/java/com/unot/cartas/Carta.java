package com.unot.cartas;

public class Carta {

    private static final String RESET = "\u001B[0m";
    private static final String BLUE = "\u001B[34m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String PURPLE = "\u001B[35m";

    private final String simbolo;
    private Tipo tipo;
    private Cor cor;

    public Carta(Tipo tipo, String simbolo, Cor cor) {
        this.tipo = tipo;
        this.simbolo = simbolo;
        this.cor = cor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        if(this.cor == Cor.AMARELO) {
            return YELLOW + simbolo + RESET;
        } else if(this.cor == Cor.AZUL) {
            return BLUE + simbolo + RESET;
        } else if(this.cor == Cor.VERMELHO) {
            return RED + simbolo + RESET;
        }else if(this.cor == Cor.VERDE) {
            return GREEN + simbolo + RESET;
        } else {
            return PURPLE + simbolo + RESET;
        }
    }

}
