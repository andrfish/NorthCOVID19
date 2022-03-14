package andrfish.main.flows;

public class RuralHospitalizationFlow {

	public double value = 0.0;

	public double calculate(double RuralInfectionFlow, double RuralHospitalizationRate, double RuralInfectious)
	{
		double result = 0.0;
		
		result = RuralInfectionFlow * RuralHospitalizationRate + (RuralInfectious * 0);
	
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
