package ua.kovtunovych;

public class Apple {

    public int posX;
    public int posY;

    public Apple(int startX, int startY) {
        posX = startX;
        posY = startY;
    }

    public void setRandomPosition() {
        posX = (int) (Math.random()*(SnakeGame.WIDTH-1));
        posY = (int) (Math.random()*(SnakeGame.HEIGHT-2));
    }

}
