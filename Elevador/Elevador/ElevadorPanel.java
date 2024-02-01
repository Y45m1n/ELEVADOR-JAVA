// package Elevador;

// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import java.awt.GridLayout;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class ElevadorPanel extends JFrame {

//     private Elevador elevador;

//     public ElevadorPanel(Elevador elevador) {
//         super("Elevador Panel");
//         this.elevador = elevador;

//         JPanel painelPrincipal = new JPanel();
//         this.add(painelPrincipal);
//         this.pack();
//         this.setBounds(100, 100, 600, 600);
//         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         this.setVisible(true);
//         this.setLocationRelativeTo(null);

//         // Crie dois JPanel para os dois grids
//         JPanel painelBotao = new JPanel();
//         JPanel textJPanel = new JPanel();

//         // Configurar layout do painelPrincipal
//         GridLayout gridPrincipal = new GridLayout(1, 2);
//         painelPrincipal.setLayout(gridPrincipal);

//         // Configurar layout do primeiro grid
//         GridLayout grid1 = new GridLayout(9, 1);
//         painelBotao.setLayout(grid1);

//         // Adicionar componentes ao primeiro grid
//         JButton button6 = new JButton("6");
//         JButton button5 = new JButton("5");
//         JButton button4 = new JButton("4");
//         JButton button3 = new JButton("3");
//         JButton button2 = new JButton("2");
//         JButton button1 = new JButton("1");
//         JButton buttonT = new JButton("T");
//         JButton buttonS1 = new JButton("S1");
//         JButton buttonS2 = new JButton("S2");

//         // Adicionar ActionListener aos botões
//         button6.addActionListener(new BotaoListener(6));
//         button5.addActionListener(new BotaoListener(5));
//         button4.addActionListener(new BotaoListener(4));
//         button3.addActionListener(new BotaoListener(3));
//         button2.addActionListener(new BotaoListener(2));
//         button1.addActionListener(new BotaoListener(1));
//         buttonT.addActionListener(new BotaoListener(0)); // Presumo que T seja o térreo (andar 0)
//         buttonS1.addActionListener(new BotaoListener(-1)); // Presumo que S1 seja o subsolo 1
//         buttonS2.addActionListener(new BotaoListener(-2)); // Presumo que S2 seja o subsolo 2

//         painelBotao.add(button6);
//         painelBotao.add(button5);
//         painelBotao.add(button4);
//         painelBotao.add(button3);
//         painelBotao.add(button2);
//         painelBotao.add(button1);
//         painelBotao.add(buttonT);
//         painelBotao.add(buttonS1);
//         painelBotao.add(buttonS2);

//         // Configurar layout do segundo grid
//         GridLayout grid2 = new GridLayout(2, 1);
//         textJPanel.setLayout(grid2);

//         // Adicionar componentes ao segundo grid
//         textJPanel.add(new JLabel("<html><font color='blue'>Elevador 1</font></html>"));
//         textJPanel.add(new JLabel("<html><font color='red'>Elevador 2</font></html>"));

//         // Adicionar os dois painéis ao painel principal
//         painelPrincipal.add(textJPanel);
//         painelPrincipal.add(painelBotao);
//     }

//     private class BotaoListener implements ActionListener {
//         private int andarDesejado;

//         public BotaoListener(int andarDesejado) {
//             this.andarDesejado = andarDesejado;
//         }

//         @Override
//         public void actionPerformed(ActionEvent e) {
//             // Mover o elevador para o andar desejado
//             elevador.moverParaAndar(andarDesejado);
//         }
//     }

//     public static void main(String[] args) {
//         Elevador elevador = new Elevador(9);
//         new ElevadorPanel(elevador);
//     }

//     public void inicializa() {
//         // Lógica de inicialização do elevador
//     }

//     public int obterAndarDesejado() {
//         return 0;
//     }
// }
