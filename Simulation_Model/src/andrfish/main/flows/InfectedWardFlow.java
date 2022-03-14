package andrfish.main.flows;

public class InfectedWardFlow {

	public double value = 0.0;

	public double calculate(double InfectedHospitalized, double WardRate)
	{
		double result = 0.0;
		
		result = InfectedHospitalized * WardRate;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
