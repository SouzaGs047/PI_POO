package com.unot.players;

import com.unot.cartas.Carta;
import com.unot.cartas.Cor;
import com.unot.cartas.Pilha;

public class Robot extends Player{

    public Robot(Pilha pilha) {
        super(pilha);
    }

    @Override
    public boolean verificarCarta(Carta carta, Carta cartaAtual) {
        return super.verificarCarta(carta, cartaAtual);
    }

    @Override
    public boolean verificarPodeJogar(Player player, Carta cartaAtual) {
        return super.verificarPodeJogar(player, cartaAtual);
    }

    @Override
    public Carta encontrarCarta(int posicao, Player player) {
        return super.encontrarCarta(posicao, player);
    }

    @Override
    public int realizarJogadaRobo(Player player, Carta cartaAtual) {
        for (int i = 0; i < player.getMao().size(); i++) {
            if(verificarCarta(player.getMao().get(i), cartaAtual)) {
                return i + 1;
            }
        }
        return -1;
    }

    @Override
    public String escolherCorRobo(Player player) {
        for (int i = 0; i < player.getMao().size(); i++) {
            if(player.getMao().get(i).getCor() != Cor.BLACK) {
                if(player.getMao().get(i).getCor() == Cor.VERMELHO) return "vermelho";
                else if(player.getMao().get(i).getCor() == Cor.VERDE) return "verde";
                else if(player.getMao().get(i).getCor() == Cor.AZUL) return "azul";
                else return "amarelo";
            }
        }
        return "";
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < getMao().size(); i++) {
            if(i < getMao().size() - 1) {
                result.append("*, ");
            } else {
                result.append("*");
            }
        }
        result.append("]");
        return result.toString();
    }
}
