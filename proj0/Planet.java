public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        return Math.sqrt(dx*dx + dy*dy);
    }

    public double calcForceExertedBy(Planet p) {
        double m1 = this.mass;
        double m2 = p.mass;
        double r = this.calcDistance(p);

        return G*m1*m2/(r*r);
    }

    public double calcForceExertedByX(Planet p) {
        double F = this.calcForceExertedBy(p);
        double dx = p.xxPos - this.xxPos;
        double r = this.calcDistance(p);

        return F*dx/r;
    }

    public double calcForceExertedByY(Planet p) {
        double F = this.calcForceExertedBy(p);
        double dy = p.yyPos - this.yyPos;
        double r = this.calcDistance(p);

        return F*dy/r;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceX = 0;

        for(Planet p : allPlanets) {
            if(!this.equals(p)) {
                netForceX += this.calcForceExertedByX(p);
            }
        }

        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceY = 0;

        for(Planet p : allPlanets) {
            if(!this.equals(p)) {
                netForceY += this.calcForceExertedByY(p);
            }
        }

        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX/this.mass;
        double aY = fY/this.mass;

        this.xxVel = this.xxVel + dt*aX;
        this.yyVel = this.yyVel + dt*aY;

        this.xxPos = this.xxPos + dt*xxVel;
        this.yyPos = this.yyPos + dt*yyVel;
    }

    public void draw() {
        String planetImgFile = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, planetImgFile);
        StdDraw.show();
    }
}