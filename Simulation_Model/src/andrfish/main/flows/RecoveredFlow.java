package andrfish.main.flows;

public class RecoveredFlow {

	public double value = 0.0;

	public double calculate(double Infectious, double HospitalizationFlow)
	{
		double result = 0.0;
		
		result = Infectious - HospitalizationFlow;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
