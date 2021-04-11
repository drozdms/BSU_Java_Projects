

public class SeriesSum {

	// the series converges with every x
	public static void main(String[] args) {
		double x;
		double e;
		try
		{
			if (args.length != 2)
				throw new WrongNumberOfArgumentsException("Number of arguments must be 2");
			 x=  Double.parseDouble(args[0]);
			 e = Double.parseDouble(args[1]);
			 System.out.print(sum(x,e));
                         
                         
		}
		
		catch (WrongNumberOfArgumentsException | NumberFormatException exc)
		{
			System.out.println(exc.getMessage());
		}
		
		
		//Scanner in = new Scanner(System.in);
		//System.out.print("Enter x: ");
		//double x = in.nextDouble();
		//System.out.print("Enter allowable error: ");
		//double e = in.nextDouble();
	      
	}
	
	static double sum(double x, double e)
	{
		double prevValue = (Math.pow(x, 2)/(1*2*3))*Math.pow((double)4/3, 6);
		double value;
		double factor;
		int k=1;
		double sum=prevValue;
		final double factorConst=-Math.pow((4.0/3), 4);
		boolean checkIfAccuracyIsAchieved=false;
		while (!checkIfAccuracyIsAchieved)
		{
			factor = factorConst/((2*k+2)*(2*k+3));
			value = prevValue*factor;
			prevValue=value;
			sum+=value;
			k++;
			if (Math.abs(value)<Math.abs(e))
				checkIfAccuracyIsAchieved=true;
		}
		
		
		return sum;
	}
	

}


class WrongNumberOfArgumentsException extends Exception
{
	public WrongNumberOfArgumentsException() {}
	public WrongNumberOfArgumentsException(String s) 
	{
		super(s);
	}
}
