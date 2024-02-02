package Elevador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Elevador extends JFrame {
    private JButton[] botoesAndar;
    private JButton botaoDescerSubsolo1;
    private JButton botaoDescerSubsolo2;
    private JTextArea display;
    private ElevadorPanel[] elevadores;
    private int[] posicaoElevadores;
    private int[] direcaoElevadores;

    public Elevador() {
        super("Sistema de Elevadores");

        botoesAndar = new JButton[7];
        botaoDescerSubsolo1 = new JButton("S1");
        botaoDescerSubsolo2 = new JButton("S2");
        display = new JTextArea();
        elevadores = new ElevadorPanel[2];
        posicaoElevadores = new int[] { 0, 0 };
        direcaoElevadores = new int[] { 0, 0 };
        elevadores[0] = new ElevadorPanel(this);
        elevadores[1] = new ElevadorPanel(this);
        JPanel textJPanel = new JPanel();

        // Configurando o layout do painel principal para BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // Configurando o layout do painel de botões para GridLayout
        JPanel botoesPanel = new JPanel(new GridLayout(9, 1));

        // Configurando botões para andares
        for (int i = 6; i >= 0; i--) {
            final int andar = i;
            if (i == 0) {
                botoesAndar[i] = new JButton("T");
            } else {
                botoesAndar[i] = new JButton(String.valueOf(i));
            }

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

        // Adicionando ação aos botões de descer aos subsolos
        botaoDescerSubsolo1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int elevadorMaisProximo = obterElevadorMaisProximo(-1);
                moverElevador(elevadorMaisProximo, -1);
            }
        });
        botoesPanel.add(botaoDescerSubsolo1);

        botaoDescerSubsolo2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int elevadorMaisProximo = obterElevadorMaisProximo(-2);
                moverElevador(elevadorMaisProximo, -2);
            }
        });
        botoesPanel.add(botaoDescerSubsolo2);

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 10, 10);
        textJPanel.setLayout(flowLayout);

        JLabel elevadorLabel1 = new JLabel("<html><font color='blue'>Elevador 1</font></html>");
        JLabel elevadorLabel2 = new JLabel("<html><font color='red'>Elevador 2</font></html>");

        elevadorLabel1.setPreferredSize(new Dimension(100, 30));
        elevadorLabel2.setPreferredSize(new Dimension(100, 30));

        textJPanel.add(elevadorLabel1);
        textJPanel.add(elevadorLabel2);

        // Adicionando a label ao centro do painel principal
        panel.add(textJPanel, BorderLayout.CENTER);

        // Adicionando o painel de botões à esquerda do painel principal
        panel.add(botoesPanel, BorderLayout.WEST);

        // Adicionando o JScrollPane (display) abaixo da label, também centralizado
        panel.add(new JScrollPane(display), BorderLayout.SOUTH);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
        setLocationRelativeTo(null);
    }

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

    private void moverElevador(final int indiceElevador, final int andar) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int andarAtual = posicaoElevadores[indiceElevador];
                int andarDestino = andar;
                direcaoElevadores[indiceElevador] = andarAtual < andarDestino ? 1 : -1;

                display.append("Elevador " + (indiceElevador + 1) + "\n");
                while (andarAtual != andarDestino) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    andarAtual += direcaoElevadores[indiceElevador];
                    posicaoElevadores[indiceElevador] = andarAtual;
                    elevadores[indiceElevador].setAndarAtual(andarAtual);
                    display.setText(display.getText() + (andarAtual >= 0 ? "andar " + andarAtual : "subsolo") + "\n");
                }
                display.append("Entre no Elevador " + (indiceElevador + 1) + "\n");
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
