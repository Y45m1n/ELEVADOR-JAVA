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

        botoesAndar = new JButton[7];
        display = new JTextArea();
        elevadores = new ElevadorPanel[2];
        posicaoElevadores = new int[]{0, 0}; // Posição inicial dos elevadores (Térreo)
        direcaoElevadores = new int[]{0, 0}; // 0 para parado, 1 para subindo, -1 para descendo
        elevadores[0] = new ElevadorPanel(this); // Adiciona esta linha
        elevadores[1] = new ElevadorPanel(this); // Adiciona esta linha
        JPanel textJPanel = new JPanel();

        // Configurando a interface gráfica
        JPanel panel = new JPanel(new BorderLayout());
        

        JPanel botoesPanel = new JPanel(new GridLayout(7, 1));
        for (int i = 6; i >= 0; i--) {  // Invertendo a ordem dos botões
            final int andar = i;
            if (i == 0) {
                botoesAndar[i] = new JButton("T");
            } else {
                botoesAndar[i] = new JButton(String.valueOf(i));
            }

            // Definindo tamanho preferencial para os botões
            botoesAndar[i].setPreferredSize(new Dimension(100, 50));

            botoesAndar[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int andarSolicitado = andar == 0 ? 0 : Integer.parseInt(((JButton) e.getSource()).getText());
                    int elevadorMaisProximo = obterElevadorMaisProximo(andarSolicitado);
                    moverElevador(elevadorMaisProximo, andarSolicitado);
                }
            });
            botoesPanel.add(botoesAndar[i]);
        }

        // Configurar layout do segundo grid
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 10, 10); // Ajuste conforme necessário
        textJPanel.setLayout(flowLayout);

        // Adicionar componentes ao segundo grid
        JLabel elevadorLabel1 = new JLabel("<html><font color='blue'>Elevador 1</font></html>");
        JLabel elevadorLabel2 = new JLabel("<html><font color='red'>Elevador 2</font></html>");
       

        elevadorLabel1.setPreferredSize(new Dimension(100, 30)); // Ajuste conforme necessário
        elevadorLabel2.setPreferredSize(new Dimension(100, 30)); // Ajuste conforme necessário

        textJPanel.add(elevadorLabel1);
        textJPanel.add(elevadorLabel2);

        // Adicionar os dois painéis ao painel principal
        panel.add(textJPanel, BorderLayout.CENTER);
        panel.add(botoesPanel, BorderLayout.WEST);
        panel.add(new JScrollPane(display), BorderLayout.SOUTH);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
        setLocationRelativeTo(null);
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
                    display.setText(display.getText()   + (andarAtual >= 0 ? "andar " + andarAtual : "subsolo") + "\n");
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
}