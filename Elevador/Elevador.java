package Elevador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Elevador extends JFrame {
    private JButton[] botoesAndar;
    private JTextArea display;
    private ElevadorPanel[] elevadores;

    private int[] posicaoElevadores;
    private int[] direcaoElevadores;

    public Elevador() {
        super("Sistema de Elevadores");

        // Inicializando variáveis
        botoesAndar = new JButton[7]; // Adicionando um botão extra para o subsolo
        display = new JTextArea();
        elevadores = new ElevadorPanel[2];
        posicaoElevadores = new int[]{0, 0}; // Posição inicial dos elevadores (Térreo)
        direcaoElevadores = new int[]{0, 0}; // 0 para parado, 1 para subindo, -1 para descendo

        // Configurando a interface gráfica
        JPanel panel = new JPanel(new BorderLayout());

        JPanel botoesPanel = new JPanel(new GridLayout(7, 1)); // Ajustando o número de linhas para 7
        for (int i = 6; i >= 0; i--) {  // Invertendo a ordem dos botões
            final int andar = i;
            if (i == 0) {
                botoesAndar[i] = new JButton("T");
            } else {
                botoesAndar[i] = new JButton(i == 6 ? "S1" : String.valueOf(i)); // Adicionando o botão "S1"
            }
            botoesAndar[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (andar == 6) {
                        // Lógica para lidar com o subsolo (S1)
                        int elevadorMaisProximo = obterElevadorMaisProximo(andar);
                        moverElevador(elevadorMaisProximo, andar);
                    } else {
                        int andarSolicitado = andar == 0 ? 0 : Integer.parseInt(((JButton) e.getSource()).getText());
                        int elevadorMaisProximo = obterElevadorMaisProximo(andarSolicitado);
                        moverElevador(elevadorMaisProximo, andarSolicitado);
                    }
                }
            });
            botoesPanel.add(botoesAndar[i]);
        }

        JPanel elevadoresPanel = new JPanel(new GridLayout(1, 2));
        for (int i = 0; i < 2; i++) {
            elevadores[i] = new ElevadorPanel(this);
            elevadoresPanel.add(elevadores[i]);
        }

        panel.add(botoesPanel, BorderLayout.WEST);
        panel.add(elevadoresPanel, BorderLayout.CENTER);
        panel.add(new JScrollPane(display), BorderLayout.SOUTH);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setVisible(true);
    }

    // Retorna o índice do elevador mais próximo do andar especificado
    private int obterElevadorMaisProximo(int andar) {
        int elevadorMaisProximo = 0;
        int distanciaMinima = Math.abs(posicaoElevadores[0] - andar);
        for (int i = 1; i < posicaoElevadores.length; i++) {
            int distancia = Math.abs(posicaoElevadores[i] - andar);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                elevadorMaisProximo = i;
            }
        }
        return elevadorMaisProximo;
    }

    // Move o elevador para o andar especificado
    private void moverElevador(final int indiceElevador, final int andar) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int andarAtual = posicaoElevadores[indiceElevador];
                int andarDestino = andar;
                direcaoElevadores[indiceElevador] = andarAtual < andarDestino ? 1 : -1;

                display.append("Elevador " + (indiceElevador + 1) + " está se movendo...\n");
                while (andarAtual != andarDestino) {
                    try {
                        Thread.sleep(2000); // Delay de 2 segundos por andar
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    andarAtual += direcaoElevadores[indiceElevador];
                    posicaoElevadores[indiceElevador] = andarAtual;
                    elevadores[indiceElevador].setAndarAtual(andarAtual);
                    display.setText(display.getText() + "Elevador " + (indiceElevador + 1) + " chegou ao " + (andarAtual >= 0 ? "andar " + andarAtual : "subsolo") + "\n");
                }
                display.append("Bem-vindo! Por favor, entre no Elevador " + (indiceElevador + 1) + "\n");
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Elevador();
            }
        });
    }
}

class ElevadorPanel extends JPanel {
    private Elevador elevador;
    private int andarAtual;
    private int numeroElevador;

    public ElevadorPanel(Elevador elevador) {
        this.elevador = elevador;
        setPreferredSize(new Dimension(100, 300));
        setBackground(Color.LIGHT_GRAY);
    }

    public void setAndarAtual(int andarAtual) {
        this.andarAtual = andarAtual;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(20, 50, 60, 200); // Elevador
        g.setColor(Color.BLACK);
        g.drawRect(20, 50, 60, 200); // Contorno do Elevador
        g.drawString("Elevador " + numeroElevador, 10, 30); // Texto "Elevador"
        g.drawString("Andar: " + andarAtual, 10, 270); // Texto "Andar"
    }
}
