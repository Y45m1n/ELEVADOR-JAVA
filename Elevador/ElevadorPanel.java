package Elevador;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class ElevadorPanel extends JFrame {

    // Construtor(GUI-JPanel)
    public ElevadorPanel() {
        super("Janela Principal");
        JPanel painelPrincipal = new JPanel();
        // set do frame
        this.add(painelPrincipal);
        this.pack();
        this.setBounds(100, 100, 600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        // Crie dois JPanel para os dois grids
        JPanel painelBotao1 = new JPanel();
        JPanel painelBotao2 = new JPanel();

        // Configurar layout do painelPrincipal
        GridLayout gridPrincipal = new GridLayout(1, 2);
        painelPrincipal.setLayout(gridPrincipal);

        // Configurar layout do primeiro grid
        GridLayout grid1 = new GridLayout(9, 1);
        painelBotao1.setLayout(grid1);

        // Adicionar componentes ao primeiro grid
        painelBotao1.add(new JButton("6"));
        painelBotao1.add(new JButton("5"));
        painelBotao1.add(new JButton("4"));
        painelBotao1.add(new JButton("3"));
        painelBotao1.add(new JButton("2"));
        painelBotao1.add(new JButton("1"));
        painelBotao1.add(new JButton("T"));
        painelBotao1.add(new JButton("S1"));
        painelBotao1.add(new JButton("S2"));

        // Configurar layout do segundo grid
        GridLayout grid2 = new GridLayout(2, 1);
        painelBotao2.setLayout(grid2);

        // Adicionar componentes ao segundo grid
        painelBotao2.add(new JButton("Elevador 1"));
        painelBotao2.add(new JButton("Elevador 2"));

        // Adicionar os dois painéis ao painel principal
        painelPrincipal.add(painelBotao1);
        painelPrincipal.add(painelBotao2);
    }

    public static void main(String[] args) {
        new ElevadorPanel();
    }
}
