import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class NBody {
	
	private static Scanner sc;
	public static void main(String[] args){
		double T = 157788000.0;
		double dt = 25000.0;
		String pfile = "data/planets.txt";
		if (args.length > 2) {
			T = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		}	
		Planet[] planets = readPlanets(pfile);
		double radius = readRadius(pfile); 
		
		for(double time=0; time<T; time+=dt){
			double [] xForces = new double [planets.length];
			double [] yForces = new double [planets.length];
			for(int i=0; i<planets.length;i++){
				Planet p = planets[i];
				xForces[i] = p.calcNetForceExertedByX(planets);
				yForces[i] = p.calcNetForceExertedByY(planets);
			}
			for(int i=0; i<planets.length;i++){
				Planet p =  planets[i];
				p.update(dt, xForces[i], yForces[i]);
			}
		    StdDraw.setScale(-radius, radius);
		    StdDraw.picture(0, 0,"images/starfield.jpg");
		    for(int k=0; k < planets.length; k++){
		    	planets[k].draw();
		    StdDraw.show(2);
		    }
		}
		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              planets[i].myXPos, planets[i].myYPos, 
		                      planets[i].myXVel, planets[i].myYVel, 
		                      planets[i].myMass, planets[i].myFileName);
		}
	}
	public static double readRadius(String fname){
		double radius = 0;
		File f = new File(fname);
		Scanner sc;
		try {
			sc = new Scanner(f);
			for(int k = 0; k<2; k++){
				radius = sc.nextDouble();
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	return radius;
	}
	
	public static Planet[] readPlanets(String fname){
		Planet[] pArray = null; 
		File f = new File(fname);
		Scanner sc;
		try{
			sc = new Scanner(f);
			int Nbodies = sc.nextInt();
			pArray = new Planet[Nbodies];
			double radius = sc.nextDouble();
			for(int k = 0 ; k < Nbodies; k++){
				double xPos = sc.nextDouble();
				double yPos = sc.nextDouble();
				double xVel = sc.nextDouble();
				double yVel = sc.nextDouble();
				double Mass = sc.nextDouble();
				String img = sc.next();
				Planet p = new Planet(xPos, yPos, xVel, yVel, Mass, img);
				pArray[k] = p;
				}
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	return pArray;
	}
}
