package andrfish.main.flows;

public class InfectedWardIcuFlow {

	public double value = 0.0;

	public double calculate(double InfectedWard, double WardToIcuRate)
	{
		double result = 0.0;
		
		result = InfectedWard * WardToIcuRate;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
