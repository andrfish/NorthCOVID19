package andrfish.main.flows;

public class RuralInfectedRecoveredFlow {

	public double value = 0.0;

	public double calculate(double RuralInfectedWard, double[] InfectedWardIcuFlow)
	{
		double result = 0.0;
		
		result = RuralInfectedWard - InfectedWardIcuFlow[0] - InfectedWardIcuFlow[1];
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
