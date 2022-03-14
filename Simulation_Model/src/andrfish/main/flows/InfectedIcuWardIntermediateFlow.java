package andrfish.main.flows;

public class InfectedIcuWardIntermediateFlow {

	public double value = 0.0;

	public double calculate(double InfectedIcuWardIntermediate, double IcuStayRate)
	{
		double result = 0.0;
		
		result = InfectedIcuWardIntermediate / IcuStayRate;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
