package pl.edu.agh.fis.anistratenko_team_project;

import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

class Body {
    double x;
    double y;
    double r;
    double m;
    double ax, ay, vx, vy;
    boolean active;

    //    int id; // id is used for ignoring body on resultant force calculating
    public double getX() {
        return x;
    }

    public double getR() {
        return r;
    }

    public double getY() {
        return y;
    }

    public double getM() {
        return m;
    }
//    public int getId() {return id;}

    public Body(int newX, int newY, int newR, int newM) {
        this.x = newX;
        this.y = newY;
        this.r = newR;
        this.m = newM;
        this.ax = this.ay = this.vx = this.vy = 0;
        this.active = true;
    }

    boolean beyondTheCanvas() {
        return Math.abs(this.getX()) > 1000 || Math.abs(this.getY()) > 1000;
    }

    void updatePosition() {
        this.x += this.vx;
        this.y += this.vy;
        this.vx += this.ax;
        this.vy += this.ay;
    }

}

public class Gravity {
    private ArrayList<Body> bodies = new ArrayList<>();
    private double G = 8e-1;
    private double real_t = 0.;
    private double t = 0.;
    private Random rnd = new Random();
    public Body getBody(int index) {
        return bodies.get(index);
    }

    public int getNumOfBodies() {
        return bodies.size();
    }

    public void setG(double g) {
        G = g;
    }

    public Gravity(int numOfBodies) {
//        Random rnd = new Random();
        for (int i = 0; i < numOfBodies; i++) {
            bodies.add(new Body(rnd.nextInt(300),
                    rnd.nextInt(300), 10, 10));
        }
    }

    public void setParams(int numOfBodies) {
        real_t = 0.;
        t = 0.;
        bodies.clear();
        for (int i = 0; i < numOfBodies; i++) {
            bodies.add(new Body(rnd.nextInt(300),
                    rnd.nextInt(300), 10, 10));
        }
    }

    public void simulate(double time) {
        double dt = 0.008;
        real_t += time;
//        System.out.println("time: " + real_t);
        while (t < real_t) {
            calculateAccelerations();
            for (Body i : bodies) {
//                System.out.println(bodies.size());
                i.updatePosition();
            }

            t += dt;
        }
    }


    private void calculateAccelerations() {
        for (int i = 0; i < bodies.size(); i++) {
            bodies.get(i).ax = 0;
            bodies.get(i).ay = 0;
        }

        for (int cur = 0; cur < bodies.size(); cur++) {// for each body ...

            if (bodies.get(cur).active) {
                for (int i = 0; i < bodies.size(); i++) { // ... calculate resulting acceleration
               /* HERE TODO avoiding situation in which current body was deleted*/
//               System.out.println(bodies.size());
                    if (cur >= bodies.size()) continue;
                    if (bodies.get(cur).beyondTheCanvas()) {
//                    bodies.remove(cur);
                        bodies.get(cur).active = false;
                        bodies.get(cur).r = 0;
                        bodies.get(cur).m = 0;
                        System.out.println("removed beyond " + cur);
                        continue;
                    }

                    if (i != cur && bodies.get(i).active) {  // ignore the current body
//                    System.out.println(cur + " " +  i + " " + bodies.size());
                        double dist = calcDistance(cur, i);

                        if (dist > bodies.get(cur).r) {
                            calcNewAcceleration(cur, i, dist); // calculate new acceleration
                        } else {
                            removeBodyOnCollision(cur, i);    // removes body with i index (!) if collision detected
                        }
                    }
                }
            }
        }
    }

    double calcDistance(int cur, int i) {
        double first = (bodies.get(i).x - bodies.get(cur).x) * (bodies.get(i).x - bodies.get(cur).x);
        double second = (bodies.get(i).y - bodies.get(cur).y) * (bodies.get(i).y - bodies.get(cur).y);
        return Math.sqrt(first + second);
    }

    void calcNewAcceleration(int cur, int i, double dist) {
        bodies.get(cur).ax += G * bodies.get(i).m * (bodies.get(i).x - bodies.get(cur).x) / Math.pow(dist, 3);
        bodies.get(cur).ay += G * bodies.get(i).m * (bodies.get(i).y - bodies.get(cur).y) / Math.pow(dist, 3);
    }

    void removeBodyOnCollision(int cur, int i) {
        bodies.get(cur).m += bodies.get(i).m;
        if (bodies.get(cur).r < 70 && bodies.get(i).r < 70) { // just for usability. prevents unlimited bodies' growth
            if (bodies.get(cur).r > bodies.get(i).r)
                bodies.get(cur).r += Math.pow(bodies.get(i).r, 1 / 3);
            else
                bodies.get(cur).r = bodies.get(i).r + Math.pow(bodies.get(cur).r, 1 / 3);
        }

//        bodies.get(cur).ax = 0.0;
//        bodies.get(cur).ay = 0.0; // probably the momentum preserving law will be added.
        bodies.get(cur).vx = 0.0;
        bodies.get(cur).vy = 0.0;
        bodies.get(i).active = false;
        bodies.get(i).r = 0;
        bodies.get(i).m = 0;
//        bodies.remove(i);
        System.out.println("removed " + i);

    }


}
