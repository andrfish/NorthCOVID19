package andrfish.main.flows;

public class HospitalizedDeathFlow {

	public double value = 0.0;

	public double calculate(double InfectedHospitalized, double HospitalizedDeathRate)
	{
		double result = 0.0;
		
		result = InfectedHospitalized * HospitalizedDeathRate;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
