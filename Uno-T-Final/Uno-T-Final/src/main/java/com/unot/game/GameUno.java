package com.unot.game;

import com.unot.cartas.Carta;
import com.unot.cartas.Cor;
import com.unot.cartas.Pilha;
import com.unot.cartas.Tipo;
import com.unot.lista.DuplaEncadeada;
import com.unot.lista.No;
import com.unot.players.Humano;
import com.unot.players.Player;
import com.unot.players.Robot;

import javax.swing.*;
import java.util.Scanner;

public class GameUno {

    private final Scanner sc = new Scanner(System.in);
    private final DuplaEncadeada<Carta> lista;
    private final Player jogador;
    private final Player robot;
    private final Pilha pilha;
    private Carta jogouJogador;
    private Carta jogouRobot;
    private Carta cartaAtual;
    private String vezDo;

    public GameUno() {
        this.pilha = new Pilha();
        this.jogador = new Humano(pilha);
        this.robot = new Robot(pilha);
        this.vezDo = "ROBOT";
        this.cartaAtual = new Carta(Tipo.CARTA_COMUM, "1", Cor.VERMELHO);
        this.cartaAtual = this.pilha.removerCarta();
        while (this.cartaAtual.getTipo() != Tipo.CARTA_COMUM) {
            this.pilha.adicionar(this.cartaAtual);
            this.cartaAtual = this.pilha.removerCarta();
        }
        this.lista = new DuplaEncadeada<>();
        this.lista.adicionar(this.cartaAtual);
    }

    private void imprimirJogo() {
        System.out.println("\nCARTAS: " + robot.toString() + " ÚLTIMA JOGADA: " + this.jogouRobot);
        System.out.println("\nCARTAS PARA COMPRAR: " + pilha.getCartas().size());
        System.out.println("CARTA ATUAL: " + this.cartaAtual);
        System.out.println("\nCARTAS: " + jogador.getMao() + " ÚLTIMA JOGADA: " + this.jogouJogador);
    }

    public void jogo() throws Exception {
        while (!this.jogador.getMao().isEmpty() && !this.robot.getMao().isEmpty()) {
            acaoEspecial(this.cartaAtual);
            trocarVez();
            imprimirJogo();
            Player player = this.vezDo.equals("JOGADOR") ? this.jogador : this.robot;
            if (this.cartaAtual.getTipo() == Tipo.CARTA_COMPRAR && !player.verificarPodeJogar(player, this.cartaAtual)) {
                comprar();
            } else if (!player.verificarPodeJogar(player, this.cartaAtual)) {
                comprar();
                if (player.verificarPodeJogar(player, this.cartaAtual)) {
                    imprimirJogo();
                    System.out.print(this.vezDo + " INDICE: ");
                    if(player == this.jogador) {
                        Carta cartaAhJogar = player.encontrarCarta(sc.nextInt(), player);
                        if(player.verificarCarta(cartaAhJogar, this.cartaAtual)) {
                            realizarJogada(cartaAhJogar);
                        } else {
                            trocarVez();
                            throw new Exception("JOGADA INVÁLIDA!");
                        }
                    } else {
                        realizarJogada(player.encontrarCarta(player.realizarJogadaRobo(this.robot, this.cartaAtual), player));
                    }
                }
            } else if (player.verificarPodeJogar(player, this.cartaAtual)) {
                System.out.print(this.vezDo + " INDICE: ");
                if(player == this.jogador) {
                    Carta cartaAhJogar = player.encontrarCarta(sc.nextInt(), player);
                    if(player.verificarCarta(cartaAhJogar, this.cartaAtual)) {
                        realizarJogada(cartaAhJogar);
                    } else {
                        trocarVez();
                        throw new Exception("\n\nJOGADA INVÁLIDA!");
                    }
                } else {
                    realizarJogada(player.encontrarCarta(player.realizarJogadaRobo(this.robot, this.cartaAtual), player));
                }
            }
            if (deveMudarCor()) {
                System.out.print(this.vezDo + " COR: ");
                if(player == this.jogador) {
                    sc.nextLine();
                    mudarCor(sc.nextLine());
                } else {
                    mudarCor(player.escolherCorRobo(player));
                }
            }
        }
        imprimirJogo();
        System.out.println("PARABÉNS " + this.vezDo);
    }

    private boolean deveMudarCor() {
        return this.cartaAtual.getCor() == Cor.BLACK;
    }

    private void realizarJogada(Carta carta) {
        this.lista.adicionar(carta);
        if (this.vezDo.equals("JOGADOR")) {
            this.jogador.remover(carta);
            this.jogouJogador = carta;
        } else {
            this.robot.remover(carta);
            this.jogouRobot = carta;
        }
        this.cartaAtual = carta;
    }

    private void comprar() {
        if (this.lista.getFim().getElemento().getTipo() == Tipo.CARTA_COMPRAR) {
            No<Carta> noVaiComprar = lista.getFim();
            int total = 0;
            while (noVaiComprar.getElemento().getTipo() == Tipo.CARTA_COMPRAR) {
                total += Character.getNumericValue(noVaiComprar.getElemento().getSimbolo().charAt(1));
                noVaiComprar = noVaiComprar.getAnterior();
            }
            for (int i = 0; i < total; i++) {
                Carta comprada = this.pilha.removerCarta();
                if (this.vezDo.equals("JOGADOR")) {
                    this.jogador.adicionar(comprada);
                } else {
                    this.robot.adicionar(comprada);
                }
                JOptionPane.showMessageDialog(null, this.vezDo + " COMPROU", "COMPRA", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            Carta comprada = this.pilha.removerCarta();
            if (this.vezDo.equals("JOGADOR")) {
                this.jogador.adicionar(comprada);
            } else {
                this.robot.adicionar(comprada);
            }
            JOptionPane.showMessageDialog(null, this.vezDo + " COMPROU", "COMPRA", JOptionPane.INFORMATION_MESSAGE);
        }
        this.cartaAtual.setTipo(Tipo.CARTA_COMUM);
    }

    private void acaoEspecial(Carta carta) {
        if (!carta.getSimbolo().equals("C") && carta.getTipo() == Tipo.CARTA_ESPECIAL) {
            trocarVez();
        }
    }

    private void mudarCor(String cor) {
        if (cor.equalsIgnoreCase("vermelho")) {
            cartaAtual.setCor(Cor.VERMELHO);
        }
        if (cor.equalsIgnoreCase("verde")) {
            cartaAtual.setCor(Cor.VERDE);
        }
        if (cor.equalsIgnoreCase("azul")) {
            cartaAtual.setCor(Cor.AZUL);
        }
        if (cor.equalsIgnoreCase("amarelo")) {
            cartaAtual.setCor(Cor.AMARELO);
        }
        imprimirJogo();
    }

    private void trocarVez() {
        if (this.vezDo.equals("JOGADOR")) {
            this.vezDo = "ROBOT";
        } else {
            this.vezDo = "JOGADOR";
        }
    }

}
