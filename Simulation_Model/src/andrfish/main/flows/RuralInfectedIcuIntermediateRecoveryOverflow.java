package andrfish.main.flows;

public class RuralInfectedIcuIntermediateRecoveryOverflow {

	public double value = 0.0;

	public double calculate(double RuralInfectedIcuOverflowIntermediateRecovery, double RuralInfectedIcuRecoveryOverflow, double RuralIcuOverflowRecoveryOffsetDuration)
	{
		double result = 0.0;
		
		result = (RuralInfectedIcuOverflowIntermediateRecovery + RuralInfectedIcuRecoveryOverflow) / RuralIcuOverflowRecoveryOffsetDuration;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
				
		value = result;
		return result;
	}
}
