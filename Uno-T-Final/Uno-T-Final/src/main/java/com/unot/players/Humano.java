package com.unot.players;

import com.unot.cartas.Carta;
import com.unot.cartas.Pilha;

public class Humano extends Player{

    public Humano(Pilha pilha) {
        super(pilha);
    }

    @Override
    public boolean verificarCarta(Carta carta, Carta cartaAtual) {
        return super.verificarCarta(carta, cartaAtual);
    }

    @Override
    public Carta encontrarCarta(int posicao, Player player) {
        return super.encontrarCarta(posicao, player);
    }

    @Override
    public boolean verificarPodeJogar(Player player, Carta cartaAtual) {
        return super.verificarPodeJogar(player, cartaAtual);
    }

}
