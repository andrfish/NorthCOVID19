package andrfish.main.flows;

public class HospitalizationFlow {

	public double value = 0.0;

	public double calculate(double InfectionFlow, double HospitalizationRate, double Infectious)
	{
		double result = 0.0;
		
		result = InfectionFlow * HospitalizationRate + (Infectious * 0);
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
