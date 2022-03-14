package andrfish.main.flows;

public class RuralRecoveredFlow {

	public double value = 0.0;

	public double calculate(double RuralInfectious, double RuralHospitalizationFlow)
	{
		double result = 0.0;
		
		result = RuralInfectious - RuralHospitalizationFlow;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
