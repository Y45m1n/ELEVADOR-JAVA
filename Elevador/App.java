// package Elevador;

// import java.util.Scanner;

// public class App {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);

//         // Criar o elevador
//         Elevador elevador1 = new Elevador(10);

//         // Inicializar
//         elevador1.inicializa();
//         boolean ligado = true;

//         // Iniciar a interface gráfica
//         ElevadorPanel elevadorPanel = new ElevadorPanel(elevador1);

//         // Loop do elevador
//         while (ligado) {
//             // Aguardar pressionar um botão no painel
//             int andarDesejado = elevadorPanel.obterAndarDesejado();

//             // Mover o elevador para o andar desejado
//             elevador1.moverParaAndar(andarDesejado);
//         }
//     }
// }
