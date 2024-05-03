package com.unot.players;

import com.unot.cartas.Carta;
import com.unot.cartas.Cor;
import com.unot.cartas.Pilha;
import com.unot.cartas.Tipo;

import java.util.ArrayList;

public class Player {

    private ArrayList<Carta> mao;

    public Player(Pilha pilha) {
        this.mao = new ArrayList<>();
        while (this.mao.size() < 7) {
            this.adicionar(pilha.removerCarta());
        }
    }

    public ArrayList<Carta> getMao() {
        return mao;
    }

    public void adicionar(Carta carta) {
        this.mao.add(carta);
    }

    public void remover(Carta carta) {
        this.mao.remove(carta);
    }

    public boolean verificarCarta(Carta carta, Carta cartaAtual) {
        if (cartaAtual.getTipo() == Tipo.CARTA_COMPRAR && carta.getTipo() != Tipo.CARTA_COMPRAR) {
            return false;
        }
        return carta.getCor() == Cor.BLACK || carta.getCor() == cartaAtual.getCor() || carta.getSimbolo().equals(cartaAtual.getSimbolo());
    }

    public Carta encontrarCarta(int posicao, Player player) {
        return player.getMao().get(posicao - 1);
    }

    public boolean verificarPodeJogar(Player player, Carta cartaAtual) {
        for (int i = 0; i < player.getMao().size(); i++) {
            if (verificarCarta(player.getMao().get(i), cartaAtual)) {
                return true;
            }
        }
        return false;
    }

    public int realizarJogadaRobo(Player player, Carta cartaAtual) {
        return 0;
    }

    public String escolherCorRobo(Player player) {
        return null;
    }

    @Override
    public String toString() {
        return mao.toString();
    }
}
