package andrfish.main.flows;

public class RuralInfectedWardFlow {

	public double value = 0.0;

	public double calculate(double RuralInfectedHospitalized, double RuralWardRate)
	{
		double result = 0.0;
		
		result = RuralInfectedHospitalized * RuralWardRate;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
