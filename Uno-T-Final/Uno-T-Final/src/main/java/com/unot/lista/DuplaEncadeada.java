package com.unot.lista;

import com.unot.cartas.Carta;

public class DuplaEncadeada<T extends Carta> {

    private No<Carta> inicio;
    private No<Carta> fim;

    public No<Carta> getFim() {
        return fim;
    }

    public void adicionar(Carta novaCarta) {
        No<Carta> celula = new No<>(novaCarta);
        if (this.inicio == null && this.fim == null) {
            this.inicio = celula;
        } else {
            this.fim.setProximo(celula);
            celula.setAnterior(this.fim);
        }
        this.fim = celula;
    }

}