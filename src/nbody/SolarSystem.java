
package nbody;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class SolarSystem {
    public SolarSystem(double duration, double increment, boolean displaySun) {
        simulate(duration, increment, displaySun);
    }

    public static void simulate(double duration, double increment, boolean displaySun) {
        double clock = 0.0;
        double GRAVITATIONAL_CONSTANT = 6.67e-11;
        In in = new In("_data/solar_system.txt");
        int bodies = in.readInt();
        double screenScale = in.readDouble();
        int canvasSize = 1366;
        StdDraw.setCanvasSize(canvasSize, canvasSize);
        StdDraw.setScale(-screenScale, screenScale);
        StdDraw.enableDoubleBuffering();
        double[] coordinateX = new double[bodies];
        double[] coordinateY = new double[bodies];
        double[] velocityX = new double[bodies];
        double[] velocityY = new double[bodies];
        double[] mass = new double[bodies];
        double[] radius = new double[bodies];
        String[] image = new String[bodies];
        double maxRadius = 0.0;
        double factorScreen = (displaySun) ? 125.0 : 12500.0;
        for (int i = 0; i < bodies; i++) {
            coordinateX[i] = in.readDouble();
            coordinateY[i] = in.readDouble();
            velocityX[i] = in.readDouble();
            velocityY[i] = in.readDouble();
            mass[i] = in.readDouble();
            radius[i] = factorScreen * in.readDouble();
            image[i] = in.readString();
            if (radius[i] > maxRadius) {
                maxRadius = radius[i];
            }
        }
        // double size = 1.0e+12;
        while (clock < duration) {
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
            // StdDraw.picture(0.0, 0.0, "_data/space.jpg");
            for (int i = (displaySun) ? 0 : 1; i < bodies; i++) {
                if (i == 6) {
                    StdDraw.picture(coordinateX[i], coordinateY[i], "_data/" + image[i], 2 * radius[i], radius[i]);
                    continue;
                }
                // StdDraw.picture(coordinateX[i], coordinateY[i], "_data/" + image[i], size * radius[i] / maxRadius, size * radius[i] / maxRadius);
                StdDraw.picture(coordinateX[i], coordinateY[i], "_data/" + image[i], radius[i], radius[i]);
            }
            StdDraw.show();
            StdDraw.pause(20);
            clock += increment;
        }
        StdOut.printf("SIMULATION OVER!!!\n%d\n%.2e\n", bodies, screenScale);
        for (int i = 0; i < bodies; i++) {
            System.out.printf(" %.4e %.4e %.4e %.4e %.4e %.4e %s\n", coordinateX[i], coordinateY[i], velocityX[i], velocityY[i], mass[i], radius[i], image[i]);
        }
    }

    public static void main(String[] args) {
        new SolarSystem(157788000.0, 250000.0, false);
    }
}
