package Elevador;


import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // criar o elevador
        Elevador elevador1 = new Elevador(10);
        // iniciar
        elevador1.inicializa();
        boolean ligado = true;
        // loop do elevador
        while (ligado) {
            System.out.println("Digite a ação desejada"
                    + "\n1-Subir"
                    + "\n2-Descer"
                    + "\n3-Desligar");
            int acao = sc.nextInt();
            switch (acao) { 
                case 1:
                    System.out.println("Quantos Andares Deseja Subir");
                    System.out.println("Andar Atual: " + elevador1.subir(sc.nextInt()));
                    break;
                case 2:
                    System.out.println("Quantos Andares Deseja Descer");
                    System.out.println("Andar Atual: " + elevador1.descer(sc.nextInt()));
                    break;
                case 3:
                    ligado = false;
                default: 
                    System.out.println("Digite um ação válida");
                    break;
            }
        }
    }
}