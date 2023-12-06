package GameArkanoid;

public class Ball extends BaseObject {
    // скорость
    private double speed;
    // направление  (в градусах от 0 до 360)
    private double direction;

    // текущее значение вектора движения (dx,dy)
    private double dx;
    private double dy;

    // заморожен ли объект или может двигаться
    private boolean isFrozen;

    public Ball(double x, double y, double speed, double direction) {
        super(x, y, 1);

        this.direction = direction;
        this.speed = speed;

        this.isFrozen = true;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDirection() {
        return direction;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    /**
     * We are setting a new direction of movement.
     * We immediately calculate a new vector.
     */
    void setDirection(double direction) {
        this.direction = direction;

        double angle = Math.toRadians(direction);
        dx = Math.cos(angle) * speed;
        dy = -Math.sin(angle) * speed;
    }

    @Override
    void draw(Canvas canvas) {
        canvas.setPoint(x, y, 'O');
    }

    public void move() {
        if (isFrozen) return;

        x += dx;
        y += dy;

        checkRebound(1, Arkanoid.game.getWidth(), 1, Arkanoid.game.getHeight() + 5);
    }

    /**
     * We check if the ball has gone over the wall.
     * If yes, we reflect it.
     */
    void checkRebound(int minx, int maxx, int miny, int maxy) {
        if (x < minx) {
            x = minx + (minx - x);
            dx = -dx;
        }

        if (x > maxx) {
            x = maxx - (x - maxx);
            dx = -dx;
        }

        if (y < miny) {
            y = miny + (miny - y);
            dy = -dy;
        }

        if (y > maxy) {
            y = maxy - (y - maxy);
            dy = -dy;
        }
    }

    void start() {
        this.setDirection(direction);
        this.isFrozen = false;
    }
}

