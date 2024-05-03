package com.unot;

import com.unot.game.GameUno;

import javax.swing.*;

public class UnoT {

    static GameUno gameUno = new GameUno();

    public static void main(String[] args) {
        try {
            gameUno.jogo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            main(null);
        }
    }
}