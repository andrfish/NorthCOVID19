package andrfish.main.flows;

public class RuralInfectionIntermediateRecoveredFlow {

	public double value = 0.0;

	public double calculate(double RuralInfectiousRecoveredIntermediate, double RuralAverageIllnessDuration)
	{
		double result = 0.0;
		
		result = RuralInfectiousRecoveredIntermediate / RuralAverageIllnessDuration;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
