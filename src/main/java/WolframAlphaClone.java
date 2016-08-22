import java.math.*;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class WolframAlphaClone{
	public static void main(String args[])
	{
		//System.out.println("simplefinal test : " + compoundfinal(1000.0, 0.08,5.0, 1.0 ));
		//System.out.println("simpleinitial test : "+ simpleinitial(1400.0, 0.08, 5.0));
	}
	
	static String compoundfinal(int P, int n)
	{
		double F;
		double t = 12;
		double [] interests = new double[]{.0205,0.0211,0.0198,0.0423};
		int i = new Random().nextInt(interests.length);
		F = P* Math.pow(1+interests[i]/n, (n*t));
		System.out.println("Interest is "+i + "and " + n +"Years");
		return "<p>Given the amount "+  P + "Rands</p> <p> Your total amount will be</p> <p>" +  new DecimalFormat("#.##").format(F) + " Rands " +"</p>"+ "<p>"+"Which is " + new DecimalFormat("#.##").format(F-P) + " Rands More than the initial amount you had invested"+"</p> ";
	}
	static String compoundfinal2(int P, int n)
	{
		double F;
		double t = 12;
		double [] interests = new double[]{.0205,0.0211,0.0198,0.0423};
		int i = new Random().nextInt(interests.length);
		F = P* Math.pow(1+interests[i]/n, (n*t));
		System.out.println("Interest is "+i + "and " + n +"Years");
		return "<p> The Second Option i have found </p><p>Given the amount "+  P + " Rands</p> <p> if you invest the amount into a Fixed Deposit Investment Account Your total amount will be</p> <p>" +  new DecimalFormat("#.##").format(F) + " Rands " +"</p>"+ "<p>"+"Which is " + new DecimalFormat("#.##").format(F-P) + " Rands More than the initial amount you had invested"+"</p>";
	}
	static String compoundfinal3(int P, int n)
	{
		double F;
		double t = 12;
		double [] interests = new double[]{.0205,0.0211,0.0198,0.0423};
		int i = new Random().nextInt(interests.length);
		F = P* Math.pow(1+interests[i]/n, (n*t));
		System.out.println("Interest is "+i + "and " + n +"Years");
		return "<p>The third Option</p><p> Given the amount "+  P + " Rands</p> <p>if you invest the amount into a Call Deposit investment Account Your total amount will be </p><p>" +  new DecimalFormat("#.##").format(F) + " Rands " +"</p>"+ "<p>"+"Which is " + new DecimalFormat("#.##").format(F-P) + " Rands More than the initial amount you had invested"+"</p>";
	}
	
	static double compoundinitial(double F,double i,double n,double t)
	{
		double P;
		P = F/Math.pow((1+i/n),(n*t));
		return P;
	}
	
	static double compoundyears(double F, double P,double i,double n)
	{
		double t;
		t = (Math.log(Math.pow((F/P),(1/n))))/(Math.log(1+(i/n)));
		return t;
	}
	
	static double compoundrate(double F,double P,double n,double t)
	{
		double i;
		i = n*(Math.pow((F/P),(1/n*t))-1);
		return i;
	}
	
		static double simplefinal(double P, double i, double t)
	{
		double F;
		F = P*(1+(i*t));
		return F;
	}
	
	static double simpleinitial(double F, double i, double t)
	{
		double P;
		P = F/(1+(i*t));
		return P;
	}
	
	static double simpleyears(double F, double P,double i)
	{
		double t;
		t = (F/(P*i))-(1/i);
		return t;
	}
	
	static double simplerate(double F,double P, double t)
	{
		double i;
		i = (F/(P*t))-(1/t);
		return i;
	}
}