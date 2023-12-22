public class NBody {
    public static double readRadius(String txtFileName) {
        In in = new In(txtFileName);

        int N = in.readInt();
		double R = in.readDouble();
        return R;
    }

    public static Planet[] readPlanets(String txtFileName) {
        In in = new In(txtFileName);

        int N = in.readInt();
		double R = in.readDouble();

        Planet[] planets = new Planet[N];
        int i=0;

        while(!in.isEmpty() && i<N) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();

            Planet p = new Planet(xP, yP, xV, yV, m, img);

            planets[i] = p;
            i++;
		}

        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double universeRadius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);

        StdDraw.enableDoubleBuffering();

        double time = 0.0;
        while(time < T) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for(int i=0; i<planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByY(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for(int i=0; i<planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            String bgImg = "images/starfield.jpg";
            StdDraw.setScale(-universeRadius, universeRadius);
            StdDraw.clear();
            StdDraw.picture(0, 0, bgImg);
            StdDraw.show();

            for(Planet p : planets) {
                p.draw();
            }

            StdDraw.pause(10);
            time += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", universeRadius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}