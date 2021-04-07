package sprites;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Globo {

    private Image image;
    private double posX, posY, velX, velY, width, height;
    private int dirX, dirY;
    private final String color;

    public Globo(Image image, String color) {
        this.posX = Math.random()*1300;
        this.color = color;
        this.posY = 750;
        this.velX = 1.0f;
        this.velY = 1.0f;
        this.dirX = 1;
        this.dirY = 1;
        setImage(image);
    }

    /**
     * El moviment de la pilota és gestionat per la mateixa pilota
     * En aquest exemple només cal generalitzar les mides per on es
     * pot moure. En aquest cas en una finestra de 1200x800
     */
    public void move() {
        this.posY--;
    }

    public String getColor() {
        return color;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, posX, posY);
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }
    public void setImage(Image i) {
        image = i;
        width = image.getWidth();
        height = image.getHeight();
    }

    /**
     * Netejar la zona que ocupa l'objecte dins del graphicsContext
     * Al clearRect li passem la posició i les mides de la imatge.
     *
     * @param gc
     */
    public void clear(GraphicsContext gc) {
        gc.clearRect(posX,posY, width, height);
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(posX,posY,width,height);
    }

    public boolean isClicked(Point2D p) {
        if(getBoundary().contains(p)) return true;
        else return false;
    }

    public void changeDir() {
        double t = Math.random();
        if(0.33 > t) dirX = dirX*(-1);
        if(0.33 < t && 0.66 > t) dirY = dirY*(-1);
        if(0.66 < t) {
            dirY = dirY*(-1);
            dirX = dirX*(-1);
        }

    }

    public void setDirection(String direction) {
        switch (direction) {
            case "RIGHT":
                dirX = 1;
                break;
            case "LEFT":
                dirX = -1;
                break;
            case "DOWN":
                dirY = 1;
                break;
            case "UP":
                dirY = -1;
                break;
        }
    }



}
