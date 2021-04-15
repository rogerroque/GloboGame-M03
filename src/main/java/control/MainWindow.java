package control;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import sprites.Globo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    private Scene scene;
    private GraphicsContext gc;
    private Image fons;
    double cuantos = 0.005;
    private int contadorGlobosReventados;
    private Random random = new Random();
    private int numRandom;
    private double FPS = 0.005;
    private int nivel = 1;
    private int contadorAmarillos, contadorMorados, contadorRojos, contadorVerdes;
    private String colorAReventar;
    int numeroRandonXD;
    private boolean alreadyExecuted1 = false;
    private boolean alreadyExecuted2 = false;
    private boolean alreadyExecuted3 = false;
    private boolean alreadyExecuted4 = false;
    private boolean alreadyExecuted5 = false;
    private boolean alreadyExecuted6 = false;
    private boolean alreadyExecuted7 = false;
    private boolean alreadyExecuted8 = false;
    boolean ff;

    List<String> colorDeGloboActual = new ArrayList<>();
    String rojo = "rojo"; String verde = "verde"; String morado = "morado"; String amarillo = "amarillo";
    List<String> tipoDeGlobo = new ArrayList<>();
    private String stringGlobosRojos = "rojo";
    private String stringGlobosAmarillos = "amarillo";
    private String stringGlobosVerdes = "verde";
    private String stringGlobosMorados = "morado";

    ArrayList<Globo> globos = new ArrayList<>();

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(FPS), new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {

            tipoDeGlobo.add(stringGlobosAmarillos); tipoDeGlobo.add(stringGlobosMorados); tipoDeGlobo.add(stringGlobosRojos); tipoDeGlobo.add(stringGlobosVerdes);
            colorDeGloboActual.add(amarillo); colorDeGloboActual.add(morado); colorDeGloboActual.add(rojo); colorDeGloboActual.add(verde);

            numRandom = random.nextInt(4) + 1;
            if (Math.random() < cuantos)  {

                if (numRandom == 1) {
                    Globo globo = new Globo(new Image("images/globo.png", 100, 100, false, false), "morado");
                    globos.add(globo);
                } else if (numRandom == 2){
                    Globo globo = new Globo(new Image("images/globo2.png", 100, 100, false, false), "amarillo");
                    globos.add(globo);
                } else if (numRandom == 3){
                    Globo globo = new Globo(new Image("images/globo3.png", 100, 100, false, false), "verde");
                    globos.add(globo);
                } else {
                    Globo globo = new Globo(new Image("images/globo4.png", 100, 100, false, false), "rojo");
                    globos.add(globo);
                }
            }

            gc.drawImage(fons, 0,0,1400,800);
            for (int i = 0; i < globos.size(); i++) {
                globos.get(i).move();
                globos.get(i).render(gc);
            }

            contadores();
            prints();

        }
    })
    );

    @FXML
    Label lblInfo;
    @FXML
    Canvas mainCanvas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(url);
        System.out.println(resourceBundle.getString("key2"));

        /*globo = new Globo(new Image("images/globo.png"));*/
        fons = new Image("images/city.jpg");
        gc = mainCanvas.getGraphicsContext2D();
        //gc.drawImage(fons, 1200,800);

        // Opció 1
        //animationTimer.start();
        // Opció 2
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    public void setScene(Scene sc) {
        scene = sc;

        scene.setOnMouseClicked(mouseEvent -> {
            Point2D point = new Point2D(mouseEvent.getX(),mouseEvent.getY());

            for (int i = 0; i < globos.size() ; i++) {

                if (globos.get(i).isClicked(point)) {

                    /*if(!ff) {
                        numeroRandonXD = random.nextInt(4) + 1;
                        colorAReventar = tipoDeGlobo.get(numeroRandonXD);

                        ff = true;
                    }*/

                    if (!globos.get(i).getColor().equals(colorAReventar)) {
                        System.out.print("Has perdido");
                    } else {
                        if (colorAReventar.equals("morado")) {
                            contadorMorados++;
                            contadorGlobosReventados++;
                        } else if (colorAReventar.equals("verde")) {
                            contadorVerdes++;
                            contadorGlobosReventados++;
                        } else if (colorAReventar.equals("rojo")) {
                            contadorRojos++;
                            contadorGlobosReventados++;
                        } else if (colorAReventar.equals("amarillo")) {
                            contadorAmarillos++;
                            contadorGlobosReventados++;
                        }
                    }
                }
            }
            globos.removeIf(globo -> globo.isClicked(point));
        });
    }

    private void contadores() {

        if (contadorGlobosReventados == 0) {
            cuantos = 0.005;
            nivel = 1;

            if (!alreadyExecuted1) {
                numeroRandonXD = random.nextInt(4) + 1;
                colorAReventar = tipoDeGlobo.get(numeroRandonXD);

                alreadyExecuted1 = true;
            }

        } else if (contadorGlobosReventados == 10) {
            cuantos = 0.010;
            nivel = 2;

            if (!alreadyExecuted2) {
                numeroRandonXD = random.nextInt(4) + 1;
                colorAReventar = tipoDeGlobo.get(numeroRandonXD);
                System.out.println("AAAAAAAAAAAAAAAA");

                alreadyExecuted2 = true;
            }

        } else if (contadorGlobosReventados == 20) {
            cuantos = 0.020;
            nivel = 3;

            if (!alreadyExecuted3) {
                numeroRandonXD = random.nextInt(4) + 1;
                colorAReventar = tipoDeGlobo.get(numeroRandonXD);

                alreadyExecuted3 = true;
            }

        } else if (contadorGlobosReventados == 30) {
            cuantos = 0.030;
            nivel = 4;

            if (!alreadyExecuted4) {
                numeroRandonXD = random.nextInt(4) + 1;
                colorAReventar = tipoDeGlobo.get(numeroRandonXD);

                alreadyExecuted4 = true;
            }

        } else if (contadorGlobosReventados == 40) {
            cuantos = 0.040;
            nivel = 5;

            if (!alreadyExecuted5) {
                numeroRandonXD = random.nextInt(4) + 1;
                colorAReventar = tipoDeGlobo.get(numeroRandonXD);

                alreadyExecuted5 = true;
            }

        } else if (contadorGlobosReventados == 50) {
            cuantos = 0.050;
            nivel = 6;

            if (!alreadyExecuted6) {
                numeroRandonXD = random.nextInt(4) + 1;
                colorAReventar = tipoDeGlobo.get(numeroRandonXD);

                alreadyExecuted6 = true;
            }

        } else if (contadorGlobosReventados == 60) {
            cuantos = 0.060;
            nivel = 7;

            if (!alreadyExecuted7) {
                numeroRandonXD = random.nextInt(4) + 1;
                colorAReventar = tipoDeGlobo.get(numeroRandonXD);

                alreadyExecuted7 = true;
            }

        } else if (contadorGlobosReventados == 70) {
            cuantos = 0.070;
            nivel = 8;

            if (!alreadyExecuted8) {
                numeroRandonXD = random.nextInt(4) + 1;
                colorAReventar = tipoDeGlobo.get(numeroRandonXD);

                alreadyExecuted8 = true;
            }

        }
    }

    private void prints() {
        //Morado
        gc.setFill(Color.PURPLE);
        gc.setFont(new Font("Arial", 50));
        gc.fillText(String.valueOf(contadorMorados), 1320, 80);

        //Amarillo
        gc.setFill(Color.YELLOW);
        gc.setFont(new Font("Arial", 50));
        gc.fillText(String.valueOf(contadorAmarillos), 1320, 130);

        //Rojo
        gc.setFill(Color.RED);
        gc.setFont(new Font("Arial", 50));
        gc.fillText(String.valueOf(contadorRojos), 1320, 180);

        //Verde
        gc.setFill(Color.GREEN);
        gc.setFont(new Font("Arial", 50));
        gc.fillText(String.valueOf(contadorVerdes), 1320, 230);
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("Arial", 50));
        gc.fillText(String.valueOf(contadorGlobosReventados), 1320, 280);

        gc.setFill(Color.BLUE);
        gc.setFont(new Font("Arial", 50));
        gc.fillText("Nivel: " + nivel, 60, 80);

        gc.setFill(Color.ORANGE);
        gc.setFont(new Font("Arial", 50));
        gc.fillText(colorAReventar, 400, 80);

    }

}
