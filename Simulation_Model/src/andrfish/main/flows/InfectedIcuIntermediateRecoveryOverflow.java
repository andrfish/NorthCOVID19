package andrfish.main.flows;

public class InfectedIcuIntermediateRecoveryOverflow {

	public double value = 0.0;

	public double calculate(double InfectedIcuOverflowIntermediateRecovery, double InfectedIcuRecoveryOverflow, double IcuOverflowRecoveryOffsetDuration)
	{
		double result = 0.0;
		
		result = (InfectedIcuOverflowIntermediateRecovery + InfectedIcuRecoveryOverflow) / IcuOverflowRecoveryOffsetDuration;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
				
		value = result;
		return result;
	}
}
