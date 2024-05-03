package com.unot;

import javax.swing.*;
import java.awt.*;
import java.sql.*;


public class LoginFrame extends JFrame {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/uno";
    private static final String userMySQL = "root";
    private static final String senhaSQL = "P@$$W0RD";

    private JPanel mainPanel;
    private JPanel loginPanel;
    private JPanel cadastroPanel;

    private JTextField usuarioNome;
    private JTextField usuarioUser;
    private JTextField usuarioEmail;
    private JPasswordField usuarioSenha;

    private JButton loginUserBotao;
    private JButton cadastrarUserBotao;

    private JButton telaLoginBotao;
    private JButton telaCadastroBotao;

    public LoginFrame() {
        configurarJanela();
        criarPanelPrincipal();
    }
    private void configurarJanela() {
        setTitle("Login");
        setMinimumSize(new Dimension(1280,720));
        setSize(1920,1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private void criarPanelPrincipal() {
        // Create a custom JPanel to paint the background image
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the image icon
                ImageIcon imageIcon = new ImageIcon("C:\\Users\\Gssms\\Downloads\\Uno-T-Final\\Uno-T-Final\\src\\main\\java\\com\\unot\\foto\\fundo_uno.jpeg");
                // Draw the image as the background
                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        mainPanel.setLayout(new GridBagLayout());

        loginPanel = criarLoginPanel();
        cadastroPanel = criarCadastroPanel();
        cadastroPanel.setVisible(false);

        mainPanel.add(loginPanel);
        mainPanel.add(cadastroPanel);

        add(mainPanel);
    }

    private JPanel criarLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension( 600,400));
        loginPanel.setBackground(new Color(0, 0, 0, 204));
        loginPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel usuarioLabel = ComponentesJframe.label(250,50, "Usuário:");
        usuarioUser = ComponentesJframe.campoTexto(500,50);

        JLabel senhaLabel = ComponentesJframe.label(230,50, "Senha:");
        usuarioSenha = ComponentesJframe.campoSenha(500,50);

        loginUserBotao = ComponentesJframe.botao(145,50, "Login");
        loginUserBotao.addActionListener(e -> loginUser());

        telaCadastroBotao = ComponentesJframe.botao(145,50, "Cadastrar-se");
        telaCadastroBotao.addActionListener(e -> mostrarCadastroPanel());

        adicionarComponente(loginPanel, gbc, usuarioLabel, 2, 0, 1, GridBagConstraints.CENTER);
        adicionarComponente(loginPanel, gbc, usuarioUser, 1, 1, 2, GridBagConstraints.CENTER);

        adicionarComponente(loginPanel, gbc, senhaLabel, 2, 2, 1, GridBagConstraints.CENTER);
        adicionarComponente(loginPanel, gbc, usuarioSenha, 1, 3, 2, GridBagConstraints.CENTER);

        adicionarComponente(loginPanel, gbc, loginUserBotao, 1, 4, 1, GridBagConstraints.LINE_START);
        adicionarComponente(loginPanel, gbc, telaCadastroBotao, 2, 4, 1, GridBagConstraints.LINE_END);

        return loginPanel;
    }
    private JPanel criarCadastroPanel() {
        cadastroPanel = new JPanel();
        cadastroPanel.setPreferredSize(new Dimension( 800,600));
        cadastroPanel.setBackground(new Color(0, 0, 0, 204));
        cadastroPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel nomeLabel = ComponentesJframe.label(250,50, "Nome:");
        usuarioNome = ComponentesJframe.campoTexto(500,50);

        JLabel usuarioLabel = ComponentesJframe.label(270,50, "Usuário:");
        usuarioUser = ComponentesJframe.campoTexto(500,50);

        JLabel emailLabel = ComponentesJframe.label(250,50, "E-mail:");
        usuarioEmail = ComponentesJframe.campoTexto(500,50);

        JLabel senhaLabel = ComponentesJframe.label(250,50, "Senha:");
        usuarioSenha = ComponentesJframe.campoSenha(500,50);

        telaLoginBotao = ComponentesJframe.botao(145,50, "Voltar");
        telaLoginBotao.addActionListener(e -> voltarLoginPanel());

        cadastrarUserBotao = ComponentesJframe.botao(145,50, "Cadastrar");
        cadastrarUserBotao.addActionListener(e -> cadastrarUsuario());


        adicionarComponente(cadastroPanel, gbc, nomeLabel, 2, 0, 1, GridBagConstraints.CENTER);
        adicionarComponente(cadastroPanel, gbc, usuarioNome, 1, 1, 2, GridBagConstraints.CENTER);

        adicionarComponente(cadastroPanel, gbc, usuarioLabel, 2, 2, 1, GridBagConstraints.CENTER);
        adicionarComponente(cadastroPanel, gbc, usuarioUser, 1, 3, 2, GridBagConstraints.CENTER);

        adicionarComponente(cadastroPanel, gbc, emailLabel, 2, 4, 1, GridBagConstraints.CENTER);
        adicionarComponente(cadastroPanel, gbc, usuarioEmail, 1, 5, 2, GridBagConstraints.CENTER);

        adicionarComponente(cadastroPanel, gbc, senhaLabel, 2, 6, 1, GridBagConstraints.CENTER);
        adicionarComponente(cadastroPanel, gbc, usuarioSenha, 1, 7, 2, GridBagConstraints.CENTER);


        adicionarComponente(cadastroPanel, gbc, telaLoginBotao, 1, 8, 1, GridBagConstraints.LINE_START);
        adicionarComponente(cadastroPanel, gbc, cadastrarUserBotao, 2, 8, 1, GridBagConstraints.LINE_END);

        return cadastroPanel;
    }

    private void loginUser() {
        try {
            String usuario = usuarioUser.getText();
            String senha = new String(usuarioSenha.getPassword());

            Connection connection = DriverManager.getConnection(JDBC_URL, userMySQL, senhaSQL);
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM usuarios WHERE usuario='" + usuario + "' AND senha='" + senha + "'";
            ResultSet resultSet = statement.executeQuery(query);

            //O ".next" retorna true caso o comando retorne alguma linha válida
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
                openMainWindow();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.");
            }

            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    private void cadastrarUsuario() {
        try {
            String nome = usuarioNome.getText();
            String usuario = usuarioUser.getText();
            String email = usuarioEmail.getText();
            String senha = new String(usuarioSenha.getPassword());

            Connection connection = DriverManager.getConnection(JDBC_URL, userMySQL, senhaSQL);
            String query = "INSERT INTO usuarios (nome, usuario, email, senha) VALUES ('" + nome + "', '" + usuario + "', '" + email + "', '" + senha + "')";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();

            voltarLoginPanel();

            JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar usuário: " + ex.getMessage());
        }
    }

    private void voltarLoginPanel() {
        usuarioUser.setText("");
        usuarioSenha.setText("");

        cadastroPanel.setVisible(false);
        loginPanel.setVisible(true);
        revalidate();
        repaint();
    }
    private void mostrarCadastroPanel() {
        usuarioNome.setText("");
        usuarioUser.setText("");
        usuarioEmail.setText("");
        usuarioSenha.setText("");

        cadastroPanel.setVisible(true);
        loginPanel.setVisible(false);
        revalidate();
        repaint();
    }

    private void adicionarComponente(JPanel panel, GridBagConstraints gbc, Component component, int gridx, int gridy, int gridwidth, int anchor) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.anchor = anchor;
        panel.add(component, gbc);
    }

    private void openMainWindow() {
        // Crie a nova janela principal
        JFrame mainWindow = new JFrame("Tela Principal");
        mainWindow.setSize(400, 300);
        mainWindow.setLocationRelativeTo(null); // Center the window

        JLabel welcomeLabel = new JLabel("Bem-vindo!");
        mainWindow.add(welcomeLabel);

        mainWindow.setVisible(true);

        // Fecha a janela de login
        dispose();
    }

    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}

/*create database uno;

use uno;
CREATE TABLE usuarios (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    nome  VARCHAR(50) NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(50) NOT NULL
);

insert into usuarios(usuario, senha) values("Gustavo","123");

CREATE TABLE jogos (
    id_jogo INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL,
    data_jogo DATETIME NOT NULL
);

insert into jogos(usuario, data_jogo) values("gu","2024-04-30 15:25:10");

select * from usuarios;
select * from jogos;*/





