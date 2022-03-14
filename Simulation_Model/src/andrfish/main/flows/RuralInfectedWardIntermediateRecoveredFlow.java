package andrfish.main.flows;

public class RuralInfectedWardIntermediateRecoveredFlow {

	public double value = 0.0;

	public double calculate(double RuralInfectedWardRecoveredIntermediate, double RuralHospitalStayDuration)
	{
		double result = 0.0;
		
		result = RuralInfectedWardRecoveredIntermediate / RuralHospitalStayDuration;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
