package andrfish.main.flows;

public class InfectedWardIntermediateRecoveredFlow {

	public double value = 0.0;

	public double calculate(double InfectedWardRecoveredIntermediate, double HospitalStayDuration)
	{
		double result = 0.0;
		
		result = InfectedWardRecoveredIntermediate / HospitalStayDuration;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
