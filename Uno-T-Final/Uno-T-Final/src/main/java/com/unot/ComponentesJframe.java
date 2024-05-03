package com.unot;

import javax.swing.*;
import java.awt.*;

public class ComponentesJframe extends JFrame {

    public static JLabel label( int largura, int altura, String texto){
        JLabel label = new JLabel(texto);
        label.setPreferredSize(new Dimension(largura,altura));
        Font font = label.getFont();
        label.setFont(new Font(font.getName(), Font.BOLD, 30));
        label.setForeground(Color.WHITE);
        return label;
    }

    public static JTextField campoTexto(int largura, int altura){
        JTextField campo = new JTextField();
        campo.setPreferredSize(new Dimension(largura,altura));
        Font font = campo.getFont();
        campo.setFont(new Font(font.getName(), Font.PLAIN, 20));
        return campo;
    }

    public static JPasswordField campoSenha(int largura, int altura){
        JPasswordField senha = new JPasswordField();
        senha.setPreferredSize(new Dimension(largura,altura));
        Font font = senha.getFont();
        senha.setFont(new Font(font.getName(), Font.PLAIN, 20));
        return senha;
    }

    public static JButton botao(int largura, int altura, String texto){
        JButton bt = new JButton(texto);
        bt.setPreferredSize(new Dimension(largura,altura));

        Font font = bt.getFont();
        bt.setFont(new Font(font.getName(), Font.BOLD, 18));
        bt.setBackground(new Color(229, 229, 229, 204)); // Cor de fundo
        return bt;
    }

}
