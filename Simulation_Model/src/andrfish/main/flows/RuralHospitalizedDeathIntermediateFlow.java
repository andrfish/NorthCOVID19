package andrfish.main.flows;

public class RuralHospitalizedDeathIntermediateFlow {

	public double value = 0.0;

	public double calculate(double RuralInfectedHospitalizedDeathIntermediate, double RuralAverageHospitalizationDeathDuration)
	{
		double result = 0.0;
		
		result = RuralInfectedHospitalizedDeathIntermediate / RuralAverageHospitalizationDeathDuration;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
