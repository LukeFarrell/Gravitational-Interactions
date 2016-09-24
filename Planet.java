
public class Planet {
	double myXPos;
	double myYPos;
	double myXVel;
	double myYVel;
	double myMass;
	String myFileName;
	
	public Planet(double xP, double yP, double xV,
            double yV, double m, String img){
		myXPos = xP;
		myYPos = yP;
		myXVel = xV;
		myYVel = yV;
		myMass = m;
		myFileName = img;
	}
	
	public Planet(Planet p) {
		myXPos = p.myXPos;
		myYPos = p.myYPos;
		myXVel = p.myXVel;
		myYVel = p.myYVel;
		myMass = p.myMass;
		myFileName = p.myFileName;
	}
	
	public double calcDistance(Planet otherPlanet){
		double X1 = myXPos;
		double Y1 = myYPos;
		double X2 = otherPlanet.myXPos;
		double Y2 = otherPlanet.myYPos;
		double distance = Math.pow((((X2-X1)*(X2-X1))+((Y2-Y1)*(Y2-Y1))),.5);
		
		return distance;
	}
	
	public double  calcForceExertedBy(Planet otherPlanet){ 
		double G = 6.67 * Math.pow(10, -11);
		double r = calcDistance(otherPlanet);
		double r2 = r*r;
		double m1 = myMass;
		double m2 = otherPlanet.myMass;
		double force = (G * m1 * m2) / r2;
		return force;
	}
	
	public double  calcForceExertedByX(Planet otherPlanet){
		double r = calcDistance(otherPlanet);
		double F = calcForceExertedBy(otherPlanet);
		double X1 = myXPos;
		double X2 = otherPlanet.myXPos;
		double dx = X2-X1;			
		double forceX = F * dx / r;
		return forceX;
	}
	
	public double  calcForceExertedByY(Planet otherPlanet){
		double r = calcDistance(otherPlanet);
		double F = calcForceExertedBy(otherPlanet);
		double Y1 = myYPos;
		double Y2 = otherPlanet.myYPos;
		double dy = Y2-Y1;			
		double forceX = F * dy / r;
		return forceX;
	}
	
	public double calcNetForceExertedByX(Planet [] allPlanets){
		double netForceX = 0;
		for(int k=0; k < allPlanets.length; k++){
			if (! allPlanets[k].equals(this)){
				netForceX += calcForceExertedByX(allPlanets[k]);
			}
		}
		return netForceX;
	}
	
	public double calcNetForceExertedByY(Planet [] allPlanets){
		double netForceY = 0;
		for(int k=0; k < allPlanets.length; k++){
			if (! allPlanets[k].equals(this)){
				netForceY += calcForceExertedByY(allPlanets[k]);
			}
		}
		return netForceY;
	}
	public void update(double seconds, double xForce, double yForce){
		double aX = (xForce / myMass); 
		double aY = (yForce / myMass);
		this.myXVel = myXVel+(seconds*aX);
		this.myYVel = myYVel+(seconds*aY);
		this.myXPos = myXPos+(myXVel*seconds);
		this.myYPos = myYPos+(myYVel*seconds);
	}
	public void draw(){
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
}
