package control;

import javafx.animation.AnimationTimer;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import sprites.Globo;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    private Scene scene;
    private GraphicsContext gc;
    private Globo globo;
    private Image fons;

    /**
     * Opció 1: Animationtimer
     * Controlar la velocitat de moviment no és tant fàcil
     */
    private AnimationTimer animationTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            globo.clear(gc);
            globo.move();
            globo.render(gc);
        }
    };

    /**
     * Opció 2: TimeLine
     * Controlem la velocitat de refresc amb KeyFrame.
     * Aquesta opció és molt més flexible que l'AnimationTimer
     */
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.0057), new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event) {


            globo.clear(gc);
            gc.drawImage(fons, 0,0,600,400);
            globo.move();
            globo.render(gc);



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

        globo = new Globo(new Image("images/pilota.png"));
        globo = new Globo(new Image("images/pilota.png"));
        fons = new Image("images/pista.jpeg");
        gc = mainCanvas.getGraphicsContext2D();
        //gc.drawImage(fons, 600,400);

        // Opció 1
        //animationTimer.start();

        // Opció 2
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    public void setScene(Scene sc) {
        scene = sc;
        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Point2D point = new Point2D(mouseEvent.getX(),mouseEvent.getY());
                if(globo.isClicked(point)) globo.changeDir();
                System.out.println("click");
            }
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println(keyEvent.getCode().toString());
                globo.setDirection(keyEvent.getCode().toString());

            }
        });
    }
}
