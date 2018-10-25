package nbody;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class SolarSystem {

    public SolarSystem(double duration, double increment, String filename) {
        simulate(duration, increment, filename);
    }

    public static void simulate(double duration, double increment, String filename) {
        double clock = 0.0;
        double GRAVITATIONAL_CONSTANT = 6.67e-11;
        In in = new In(filename);
        int bodies = in.readInt();
        double screenSize = in.readDouble();
        StdDraw.setScale(-screenSize, screenSize);
        StdDraw.enableDoubleBuffering();
        double[] coordinateX = new double[bodies];
        double[] coordinateY = new double[bodies];
        double[] velocityX = new double[bodies];
        double[] velocityY = new double[bodies];
        double[] mass = new double[bodies];
        double[] radius = new double[bodies];
        String[] image = new String[bodies];
        double maxRadius = 0.0;
        for (int i = 0; i < bodies; i++) {
            coordinateX[i] = in.readDouble();
            coordinateY[i] = in.readDouble();
            velocityX[i] = in.readDouble();
            velocityY[i] = in.readDouble();
            mass[i] = in.readDouble();
            radius[i] = in.readDouble();
            image[i] = in.readString();
            if (radius[i] > maxRadius) {
                maxRadius = radius[i];
            }
        }
        /* while (clock < duration) {
            double[] forceX = new double[bodies];
            double[] forceY = new double[bodies];
            for (int i = 0; i < bodies; i++) {
                for (int j = 0; j < bodies; j++) {
                    if (i == j) {
                        continue;
                    }
                    double rSquared = Math.pow(coordinateX[i] - coordinateX[j], 2) + Math.pow(coordinateY[i] - coordinateY[j], 2);
                    double deltaX = 0.0;
                    double deltaY = 0.0;
                    if (mass[i] > mass[j]) {
                        deltaX = coordinateX[i] - coordinateX[j];
                        deltaY = coordinateY[i] - coordinateY[j];
                    } else {
                        deltaX = coordinateX[j] - coordinateX[i];
                        deltaY = coordinateY[j] - coordinateY[i];
                    }
                    double sin = deltaY / Math.sqrt(rSquared);
                    double cos = deltaX / Math.sqrt(rSquared);
                    double force = GRAVITATIONAL_CONSTANT * mass[i] * mass[j] / rSquared;
                    double fX = force * cos;
                    double fY = force * sin;
                    forceX[i] += fX;
                    forceY[i] += fY;
                }
            }
            for (int i = 0; i < bodies; i++) {
                double accelerationX = forceX[i] / mass[i];
                double accelerationY = forceY[i] / mass[i];
                velocityX[i] += increment * accelerationX;
                velocityY[i] += increment * accelerationY;
                coordinateX[i] += increment * velocityX[i];
                coordinateY[i] += increment * velocityY[i];
            }
            StdDraw.clear();
            StdDraw.picture(0.0, 0.0, "_data/starfield.jpg");
            for (int i = 0; i < bodies; i++) {
                StdDraw.picture(coordinateX[i], coordinateY[i], "_data/" + image[i]);
            }
            StdDraw.show();
            StdDraw.pause(20);
            clock += increment;
        } */
        StdOut.printf("SIMULATION OVER!!!\n%d\n%.2e\n", bodies, screenSize);
        for (int i = 0; i < bodies; i++) {
            System.out.printf(" %.4e %.4e %.4e %.4e %.4e %.4e %s\n", coordinateX[i], coordinateY[i], velocityX[i], velocityY[i], mass[i], radius[i], image[i]);
        }
    }

    public static void main(String[] args) {
        new SolarSystem(157788000.0, 25000.0, "_data/solar_system.txt");
    }
}
