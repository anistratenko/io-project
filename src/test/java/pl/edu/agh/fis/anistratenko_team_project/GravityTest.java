package pl.edu.agh.fis.anistratenko_team_project;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GravityTest {

    @Test
    public void setParams() {
        Gravity gravity = new Gravity(12);
        gravity.setParams(Integer.MIN_VALUE);
        assertEquals("Integer.MINVALUE", 0, gravity.getNumOfBodies());

        gravity.setParams(-1);
        assertEquals("-1", 0, gravity.getNumOfBodies());

        gravity.setParams(0);
        assertEquals("0", 0, gravity.getNumOfBodies());

        gravity.setParams(1);
        assertEquals("1", 1, gravity.getNumOfBodies());

        gravity.setParams(10);
        assertEquals("10", 10, gravity.getNumOfBodies());
    }

    @Test
    public void simulate() {
        Gravity gravity = new Gravity(2);
        gravity.getBody(0).x = 0;
        gravity.getBody(0).y = 0;
        gravity.getBody(1).x = 0;
        gravity.getBody(1).y = 50;

        gravity.simulate(GravityView.FRAMERATE);

        assertEquals(0, gravity.getBody(0).x, 1e-15);
        assertEquals(0, gravity.getBody(1).x, 1e-15);
        assertTrue(gravity.getBody(0).y > 0);
        assertTrue(gravity.getBody(1).y < 50);

        gravity = new Gravity(2);
        gravity.getBody(0).x = 0;
        gravity.getBody(0).y = 50;
        gravity.getBody(1).x = 50;
        gravity.getBody(1).y = 50;

        gravity.simulate(GravityView.FRAMERATE);

        assertEquals(50, gravity.getBody(0).y, 1e-15);
        assertEquals(50, gravity.getBody(1).y, 1e-15);
        assertTrue(gravity.getBody(0).x > 0);
        assertTrue(gravity.getBody(1).x < 50);
    }

    @Test
    public void calcDistance() {
        Gravity gravity = new Gravity(2);
        gravity.getBody(0).x = 0;
        gravity.getBody(0).y = 0;
        gravity.getBody(1).x = 50;
        gravity.getBody(1).y = 50;
        assertEquals(50 * Math.sqrt(2), gravity.calcDistance(0, 1), 1e-15);
    }

    @Test
    public void removeBodyOnCollision() {
        int startX = 300;
        int startY = 300;
        Gravity gravity = new Gravity(12);
        for (int i = 0; i < 12; i++) {
            gravity.getBody(i).x = startX + (int) (Math.random() * 50 - 25);
            gravity.getBody(i).y = startY + (int) (Math.random() * 50 - 25);
        }

        gravity.simulate(10);

        assertEquals("12 bodies should merge to 1 after 10s", 1, gravity.getNumOfBodies());
    }
}