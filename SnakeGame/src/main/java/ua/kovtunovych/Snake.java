package ua.kovtunovych;

public class Snake {

    public Direction direction = Direction.RIGHT;
    public int length = 2;
    public int snakeX[] = new int[SnakeGame.WIDTH * SnakeGame.HEIGHT];
    public int snakeY[] = new int[SnakeGame.WIDTH * SnakeGame.HEIGHT];

    public Snake(int x0, int y0, int x1, int y1) {
        snakeX[0] = x0;
        snakeY[0] = y0;
        snakeX[1] = x1;
        snakeY[1] = y1;
    }

    public void move() {

        for(int d = length; d > 0; d--) {
            snakeX[d] = snakeX[d-1];
            snakeY[d] = snakeY[d-1];
        }

        if(direction == Direction.RIGHT) {
            snakeX[0]++;
        }
        if(direction == Direction.DOWN) {
            snakeY[0]++;
        }
        if(direction == Direction.LEFT) {
            snakeX[0]--;
        }
        if(direction == Direction.UP) {
            snakeY[0]--;
        }

        for(int d = length-1; d > 0; d--) {
            if( (snakeX[0] == snakeX[d]) & (snakeY[0] == snakeY[d])) {
                length = d-2;
            }
        }

        if(snakeX[0] > SnakeGame.WIDTH-1) {
            snakeX[0] = 0;
        }

        if(snakeX[0] < 0) {
            snakeX[0] = SnakeGame.WIDTH;
        }

        if(snakeY[0] > SnakeGame.HEIGHT-2) {
            snakeY[0] = 0;
        }

        if(snakeY[0] < 0) {
            snakeY[0] = SnakeGame.HEIGHT-1;
        }

        if( length < 2 ) {
            length = 2;
        }
    }

}
