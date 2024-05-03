package com.unot.lista;

public class No<Carta> {

    private No<Carta> anterior;
    private Carta carta;
    private No<Carta> proximo;

    public No(Carta carta) {
        this.carta = carta;
    }

    public No<Carta> getAnterior() {
        return anterior;
    }

    public void setAnterior(No<Carta> anterior) {
        this.anterior = anterior;
    }

    public Carta getElemento() {
        return this.carta;
    }
    
    public void setProximo(No<Carta> proximo) {
        this.proximo = proximo;
    }

    @Override
    public String toString() {
        return"-> " + this.carta;
    }

}