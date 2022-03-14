package andrfish.main.flows;

public class InfectedRecoveredFlow {

	public double value = 0.0;

	public double calculate(double InfectedWard, double InfectedWardIcuFlow)
	{
		double result = 0.0;
		
		result = InfectedWard - InfectedWardIcuFlow;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
