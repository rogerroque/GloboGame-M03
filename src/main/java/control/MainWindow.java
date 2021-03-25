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
import java.util.*;

public class MainWindow implements Initializable {
    private Scene scene;
    private GraphicsContext gc;
    private Image fons;
    double cuantos = 0.005;
    private int contadorGlobosReventados;
    private Random random = new Random();
    private int numRandom;
    private double FPS = 0.006;
    private int nivel = 1;

    ArrayList<Globo> globos = new ArrayList<>();

    /**
     * Opció 2: TimeLine
     * Controlem la velocitat de refresc amb KeyFrame.
     * Aquesta opció és molt més flexible que l'AnimationTimer
     */
    /*Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.0057), new EventHandler<>() {*/
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(FPS), new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {

            numRandom = random.nextInt(4) + 1;
            if (Math.random() < cuantos)  {

                if (numRandom == 1) {
                    Globo globo = new Globo(new Image("images/globo.png", 100, 100, false, false));
                    globos.add(globo);
                } else if (numRandom == 2){
                    Globo globo = new Globo(new Image("images/globo2.png", 100, 100, false, false));
                    globos.add(globo);
                } else if (numRandom == 3){
                    Globo globo = new Globo(new Image("images/globo2.png", 100, 100, false, false));
                    globos.add(globo);
                } else {
                    Globo globo = new Globo(new Image("images/globo4.png", 100, 100, false, false));
                    globos.add(globo);
                }

            }

            gc.drawImage(fons, 0,0,1200,800);;
            for (int i = 0; i < globos.size(); i++) {
                globos.get(i).move();
                globos.get(i).render(gc);
            }

            gc.setFill(Color.BLUE);
            gc.setFont(new Font("Arial", 50));
            gc.fillText(String.valueOf(contadorGlobosReventados), 1120, 80);

            gc.setFill(Color.BLUE);
            gc.setFont(new Font("Arial", 50));
            gc.fillText("Nivel: " + nivel, 60, 80);

            if (contadorGlobosReventados == 10) {
                cuantos = 0.008;
                nivel = 2;
            } else if (contadorGlobosReventados == 20) {
                cuantos = 0.0010;
                nivel = 3;
            } else if (contadorGlobosReventados == 30) {
                cuantos = 0.012;
                nivel = 4;
            } else if (contadorGlobosReventados == 40) {
                cuantos = 0.014;
                nivel = 5;
            } else if (contadorGlobosReventados == 50) {
                cuantos = 0.016;
                nivel = 6;
            } else if (contadorGlobosReventados == 60) {
                cuantos = 0.016;
                nivel = 7;
            } else if (contadorGlobosReventados == 70) {
                cuantos = 0.016;
                nivel = 8;
            }

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
                if(globos.get(i).isClicked(point)) {
                    contadorGlobosReventados++;

                    // Cambiar imagen del globo por una explsion
                    globos.get(i).setImage(new Image("images/explosion.png", 100, 100, false, false));

                }
            }
            globos.removeIf(globo -> globo.isClicked(point));
        });
    }


}
