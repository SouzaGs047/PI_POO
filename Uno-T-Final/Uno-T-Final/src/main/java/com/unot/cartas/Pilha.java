package com.unot.cartas;

import java.util.ArrayList;
import java.util.Random;

public class Pilha {

    private ArrayList<Carta> cartas;

    public Pilha() {
        this.cartas = new ArrayList<>();
        preencherPilha();
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    private void preencherPilha() {
        cartas.add(new Carta(Tipo.CARTA_COMUM, "0", Cor.VERMELHO));
        cartas.add(new Carta(Tipo.CARTA_COMUM, "0", Cor.VERDE));
        cartas.add(new Carta(Tipo.CARTA_COMUM, "0", Cor.AZUL));
        cartas.add(new Carta(Tipo.CARTA_COMUM, "0", Cor.AMARELO));
        cartas.add(new Carta(Tipo.CARTA_COMPRAR, "+4", Cor.BLACK));
        cartas.add(new Carta(Tipo.CARTA_COMPRAR, "+4", Cor.BLACK));
        cartas.add(new Carta(Tipo.CARTA_COMPRAR, "+4", Cor.BLACK));
        cartas.add(new Carta(Tipo.CARTA_COMPRAR, "+4", Cor.BLACK));
        cartas.add(new Carta(Tipo.CARTA_ESPECIAL, "C", Cor.BLACK));
        cartas.add(new Carta(Tipo.CARTA_ESPECIAL, "C", Cor.BLACK));
        cartas.add(new Carta(Tipo.CARTA_ESPECIAL, "C", Cor.BLACK));
        cartas.add(new Carta(Tipo.CARTA_ESPECIAL, "C", Cor.BLACK));
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j <= 9; j++) {
                cartas.add(new Carta(Tipo.CARTA_COMUM, String.valueOf(j), Cor.VERMELHO));
                cartas.add(new Carta(Tipo.CARTA_COMUM, String.valueOf(j), Cor.VERDE));
                cartas.add(new Carta(Tipo.CARTA_COMUM, String.valueOf(j), Cor.AZUL));
                cartas.add(new Carta(Tipo.CARTA_COMUM, String.valueOf(j), Cor.AMARELO));
            }
            cartas.add(new Carta(Tipo.CARTA_COMPRAR, "+2", Cor.VERMELHO));
            cartas.add(new Carta(Tipo.CARTA_COMPRAR, "+2", Cor.VERDE));
            cartas.add(new Carta(Tipo.CARTA_COMPRAR, "+2", Cor.AZUL));
            cartas.add(new Carta(Tipo.CARTA_COMPRAR, "+2", Cor.AMARELO));
            cartas.add(new Carta(Tipo.CARTA_ESPECIAL, "B", Cor.VERMELHO));
            cartas.add(new Carta(Tipo.CARTA_ESPECIAL, "B", Cor.VERDE));
            cartas.add(new Carta(Tipo.CARTA_ESPECIAL, "B", Cor.AZUL));
            cartas.add(new Carta(Tipo.CARTA_ESPECIAL, "B", Cor.AMARELO));
            cartas.add(new Carta(Tipo.CARTA_ESPECIAL, "R", Cor.VERMELHO));
            cartas.add(new Carta(Tipo.CARTA_ESPECIAL, "R", Cor.VERDE));
            cartas.add(new Carta(Tipo.CARTA_ESPECIAL, "R", Cor.AZUL));
            cartas.add(new Carta(Tipo.CARTA_ESPECIAL, "R", Cor.AMARELO));
        }
    }

    public void adicionar(Carta carta) {
        this.cartas.add(carta);
    }

    public Carta removerCarta() {
        Random rdm = new Random();
        int a = rdm.nextInt(cartas.size());
        while (cartas.get(a) == null) {
            a = rdm.nextInt(cartas.size());
        }
        Carta cartaRemover = cartas.get(a);
        cartas.remove(cartaRemover);
        return cartaRemover;
    }

}
