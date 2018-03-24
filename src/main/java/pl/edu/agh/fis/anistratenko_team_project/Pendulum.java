package pl.edu.agh.fis.anistratenko_team_project;
public class Pendulum
{
    private final double PI = 3.14159265359;
    //variables for simulation
    private double phi = PI/2;
    private double theta = 0.;
    private double d_phi = 0.;
    private double d_theta = 0.;
    private double d2_phi = 0.;
    private double d2_theta = 0.;
    private double l1 = 0.;
    private double l2 = 0.;
    private double m1 = 0.;
    private double m2 = 0.;
    private double g = 9.81;
    private double real_t = 0.;
    private double t = 0.;

    //flag for double/single pendulum distinction
    private boolean DP;

    //public variables x1, y1 - coordinates of 1st pendulum end, x2, y2 - 2nd pendulum or x2==x1, y2==y1 if DP equals false
    //coordinate system origin is located in pendulum pivot
    //modification of these values won't affect simulate function
    public double x1;
    public double y1;
    public double x2;
    public double y2;

    public void setXY(double X1, double Y1, double X2, double Y2)
    {
        l1 = Math.sqrt(X1*X1 + Y1*Y1);
        phi = Math.acos(X1/l1);
        if (Y1 >= 0){ phi += PI/2.; }
        else 		{ phi =  PI/2. - phi; }

        if (DP)
        {
            l2 = Math.sqrt((X2-X1)*(X2-X1) + (Y2-Y1)*(Y2-Y1));
            theta = Math.acos(X1/l1);
            if (Y2 >= Y1){ theta += PI/2.; }
            else 		 { theta =  PI/2. - theta; }
            d_theta = 0;
            d2_theta = 0;
        }

        d_phi = 0;
        d2_phi = 0;
        real_t = 0.;
        t = 0.;
    }

    public void setG(double G)
    {
        g = G;
    }

    //constructor for single pendulum
    public Pendulum(double l_1, double m_1)
    {
        this(l_1, m_1, 10, 0);
        DP = false;
    }

    //constructor for double pendulum
    public Pendulum(double l_1, double m_1, double l_2, double m_2)
    {
        DP = true;
        l1 = l_1;
        m1 = m_1;
        l2 = l_2;
        m2 = m_2;
    }

    //function for standard simulation, time >= 0
    public void simulate(double time)
    {
        simulate(time, 0);
    }

    //function for simulation with additional horizontal force, works only for single pendulum, time >= 0, F [m/s^2] in (-inf;inf)
    public void simulate(double time, double F)
    {
        //internal time-step for computations
        double dt = 5e-4;
        //time measuring
        real_t += time;
        if (DP)
        {
            while (t < real_t)
            {
                d2_phi = 	(
                        - m2*l1*d_theta*d_theta*Math.sin(phi-theta)*Math.cos(phi-theta)
                                + g*m2*Math.sin(theta)*Math.cos(phi-theta)
                                - m2*l2*d_theta*d_theta*Math.sin(phi-theta)
                                - (m1+m2)*g*Math.sin(phi)
                )
                        /
                        (
                                l1*(m1+m2) - m2*l1*Math.cos(phi-theta)*Math.cos(phi-theta)
                        );

                d2_theta =	(  m2*l2*d_theta*d_theta*Math.sin(phi-theta)*Math.cos(phi-theta)
                        + g*Math.sin(phi)*Math.cos(phi-theta)*(m1+m2)
                        + l1*d_phi*d_phi*Math.sin(phi-theta)*(m1+m2)
                        - g*Math.sin(theta)*(m1+m2)
                )
                        /
                        (
                                l2*(m1+m2) - m2*l2*Math.cos(phi-theta)*Math.cos(phi-theta)
                        );

                d_phi += d2_phi*dt;
                d_theta += d2_theta*dt;
                phi += d_phi*dt;
                theta += d_theta*dt;
                t += dt;
            }
        }
        else
        {
            while (t < time)
            {
                //System.out.println(phi + " " + theta);

                d2_phi 	=  (-g * Math.sin(phi) + F*Math.cos(phi))/l1;
                d_phi 	+= d2_phi*dt;
                phi 	+= d_phi*dt;
                t 		+= dt;
            }
        }
        definePositions();
    }

    private void definePositions()
    {
        x1 = l1 * Math.sin(phi);
        y1 = -l1 * Math.cos(phi);
        if (DP)
        {
            x2 = x1 + l2*Math.sin(theta);
            y2 = y1 - l2*Math.cos(theta);
        }
        else
        {
            x2 = x1;
            y2 = y1;
        }
    }

    public static void main(String[] args) {
        Pendulum p = new Pendulum(0.25, 1, 0.3,2);

        Long start = System.nanoTime();
        for (int i = 0; i < 600; i++) p.simulate(1./60.);

        System.out.println( "Computation time per 1 second simulated: " + ((System.nanoTime() - start)*1e-9/10) );
        System.out.println( "Computation time of 1/60 sec: 			  " + ((System.nanoTime() - start)*1e-9/600));
    }
}