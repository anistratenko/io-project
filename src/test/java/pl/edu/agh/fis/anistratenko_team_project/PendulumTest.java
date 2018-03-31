package pl.edu.agh.fis.anistratenko_team_project;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class PendulumTest {

    @Test
    public void setXY_singlePendulum() {
        Pendulum singlePendulum = new Pendulum(1,1);

        // y1 << 0
        singlePendulum.setXY(1,-1 * Double.MAX_VALUE);
        double phi = Math.acos(1 / Double.MAX_VALUE);
        assertTrue("Y1 is minimum value of its type",Math.PI/2. - phi - singlePendulum.getPhi() < 1e-15);

        // y1 >> 0
        singlePendulum.setXY(1,Double.MAX_VALUE);
        assertTrue("Y1 is maximum value of its type", Math.PI/2. + phi - singlePendulum.getPhi() < 1e-15);

        singlePendulum.setXY(1,Double.MIN_VALUE);
        phi = Math.acos(1/Math.sqrt(1+Double.MIN_VALUE * Double.MIN_VALUE));

        // y1 > 0
        assertTrue("Y1 is minimal positive value of its type", Math.PI/2. + phi - singlePendulum.getPhi() < 1e-15);

        // y1  < 0
        singlePendulum.setXY(1,-1*Double.MIN_VALUE);
        assertTrue("Y1 is maximal negative value ( close to 0) of its type",Math.PI/2. - phi - singlePendulum.getPhi() < 1e-15);

        // y1 = 0
        singlePendulum.setXY(1,0);
        phi = Math.acos(1);
        assertTrue("Y1 is equal to zero ", Math.PI/2. + phi - singlePendulum.getPhi() < 1e-15);

        //x1 = y1 = max
        singlePendulum.setXY(Double.MAX_VALUE, Double.MAX_VALUE);
        phi = Math.acos(1);
        assertTrue("X1 = Y1 = maximum value of data type, expected =" + Double.toString(phi + Math.PI/2.) + " actual = " + singlePendulum.getPhi(),
                singlePendulum.getPhi() - phi + Math.PI/2. < 1e-15);
    }

    @Test
    public void setXY_DoublePendulum(){
        Pendulum doublePendulum = new Pendulum(1,1,1,1);
        //Y2 << Y1
        double l2 = Math.sqrt(0+Math.pow(Double.MAX_VALUE - 0, 2));
        double theta = Math.acos(1/l2);
        doublePendulum.setXY(1,0, 1, -1 * Double.MAX_VALUE);
        assertTrue("Y2 is minimum value of its type, Y1 is zero",
                doublePendulum.getTheta()- Math.PI -theta < 1e-15);

        //Y2 >> Y1
        doublePendulum.setXY(1, 0, 1, Double.MAX_VALUE);
        assertTrue("Y2 is maximum value of its type, Y1 is zero",
                doublePendulum.getTheta()- Math.PI + theta < 1e-15);

        //Y2 < Y1
        doublePendulum.setXY(1, 0, 2, Double.MIN_VALUE );
        theta = Math.acos(1);
        assertTrue("Y2 is minimum positive value of its type, Y1 is zero",
                doublePendulum.getTheta()- Math.PI + theta < 1e-15);

        //Y2 > Y1
        doublePendulum.setXY(1, 0, 2, -1 * Double.MIN_VALUE );
        assertTrue("Y2 is maximum negative value ( close to 0 ) of its type, Y1 is zero",
                doublePendulum.getTheta()- Math.PI + theta < 1e-15);


        //Y2 = Y1
        double rand = (Math.random()-0.5)* Double.MAX_VALUE;
        doublePendulum.setXY(1,rand,2,rand);
        theta = Math.acos(1);
        assertTrue("Y2 = Y1 and it is equal to"+ Double.toString(rand)+" expected result is "
                + Double.toString(theta + Math.PI /2.) + " actual result is " + doublePendulum.getTheta(),
                doublePendulum.getTheta() - theta + Math.PI /2. < 1e-15);
    }

    @Test(expected = java.lang.ArithmeticException.class)
    public void setXY_DoublePendulumSqrtOfZero() {
        Pendulum doublePendulum = new Pendulum(1,1,1,1);
        doublePendulum.setXY(1,Double.MAX_VALUE, 1,-1*Double.MAX_VALUE);
    }

    @Test(expected=java.lang.ArithmeticException.class)
    public void setXY_SinglePendulumSqrtOfZero() {
        Pendulum singlePendulum = new Pendulum(1,1);
        singlePendulum.setXY(0,0);
    }

    @Test
    public void setG() {
        Pendulum p1 = new Pendulum(1,1);

        p1.setG(-1 * Double.MAX_VALUE);
        assertTrue("Minimal value set ", p1.getG() - (-1* Double.MAX_VALUE) < 1e-15 );

        p1.setG(Double.MAX_VALUE);
        assertTrue("maximal value set ", Double.MAX_VALUE - p1.getG() < 1e-15 );

        Double randomValue = (Math.random() - 0.5) * Double.MAX_VALUE;
        p1.setG(randomValue);
        assertTrue("Random value set, value = " + randomValue.toString() + " ", randomValue-p1.getG() < 1e-15);
    }

    @Test @Ignore
    public void simulate() {

    }

    @Test @Ignore
    public void simulate1() {

    }

}