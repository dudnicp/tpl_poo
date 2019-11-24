package tests;
import java.awt.Point;

import balls.Balls;

public class TestBalls {
      public static void main(String[] args) {
            Point[] pos = new Point[3];
            pos[0] = new Point(1,1);
            pos[1] = new Point(2,2);
            pos[2] = new Point(3,3);
            Balls balls = new Balls(pos);
            System.out.println(balls.toString());
            balls.translate(2,2);
            System.out.println(balls.toString());
            balls.reInit();
            System.out.println(balls.toString());
      }
}
