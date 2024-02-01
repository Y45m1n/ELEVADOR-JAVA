import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ElevadorFX extends Application {
    private Button[] botoesAndar;
    private Button botaoDescerSubsolo1;
    private Button botaoDescerSubsolo2;
    private TextArea display;
    private ElevadorFXPanel[] elevadores;

    private int[] posicaoElevadores;
    private int[] direcaoElevadores;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Elevadores");

        botoesAndar = new Button[7];
        botaoDescerSubsolo1 = new Button("S1");
        botaoDescerSubsolo2 = new Button("S2");
        display = new TextArea();
        elevadores = new ElevadorFXPanel[2];
        posicaoElevadores = new int[]{0, 0};
        direcaoElevadores = new int[]{0, 0};
        elevadores[0] = new ElevadorFXPanel(1, this);
        elevadores[1] = new ElevadorFXPanel(2, this);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        for (int i = 6; i >= 0; i--) {
            final int andar = i;
            String buttonText = (i == 0) ? "T" : String.valueOf(i);
            botoesAndar[i] = new Button(buttonText);
            botoesAndar[i].setPrefSize(100, 50);
            botoesAndar[i].setOnAction(e -> {
                int andarSolicitado = (andar == 0) ? 0 : Integer.parseInt(((Button) e.getSource()).getText());
                int elevadorMaisProximo = obterElevadorMaisProximo(andarSolicitado);
                moverElevador(elevadorMaisProximo, andarSolicitado);
            });
            grid.add(botoesAndar[i], 0, 6 - i);
        }

        botaoDescerSubsolo1.setOnAction(e -> {
            int elevadorMaisProximo = obterElevadorMaisProximo(-1);
            moverElevador(elevadorMaisProximo, -1);
        });

        botaoDescerSubsolo2.setOnAction(e -> {
            int elevadorMaisProximo = obterElevadorMaisProximo(-2);
            moverElevador(elevadorMaisProximo, -2);
        });

        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(botaoDescerSubsolo1, botaoDescerSubsolo2);
        grid.add(hbox, 0, 8);

        for (int i = 0; i < elevadores.length; i++) {
            grid.add(elevadores[i], i + 1, 0);
        }

        StackPane root = new StackPane();
        root.getChildren().add(grid);

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
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
        new Thread(() -> {
            int andarAtual = posicaoElevadores[indiceElevador];
            int andarDestino = andar;
            direcaoElevadores[indiceElevador] = andarAtual < andarDestino ? 1 : -1;

            display.appendText("Elevador " + (indiceElevador + 1) + " está se movendo...\n");
            while (andarAtual != andarDestino) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                andarAtual += direcaoElevadores[indiceElevador];
                posicaoElevadores[indiceElevador] = andarAtual;
                elevadores[indiceElevador].setAndarAtual(andarAtual);
                display.appendText((andarAtual >= 0 ? "andar " + andarAtual : "subsolo") + "\n");
            }
            display.appendText("Bem-vindo! Por favor, entre no Elevador " + (indiceElevador + 1) + "\n");
        }).start();
    }
}

class ElevadorFXPanel extends StackPane {
    private Label elevadorLabel;
    private int andarAtual;

    public ElevadorFXPanel(int numeroElevador, ElevadorFX elevadorFX) {
        this.elevadorLabel = new Label("Elevador " + numeroElevador);
        this.andarAtual = 0;

        Rectangle elevadorRectangle = new Rectangle(100, 200, Color.LIGHTGRAY);
        this.getChildren().addAll(elevadorRectangle, elevadorLabel);

        this.setAlignment(Pos.BOTTOM_CENTER);
    }

    public void setAndarAtual(int andarAtual) {
        this.andarAtual = andarAtual;
        elevadorLabel.setText("Andar " + andarAtual);
    }
}
