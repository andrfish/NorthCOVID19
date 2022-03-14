package andrfish.main.flows;

public class RuralInfectedIcuDischargedIntermediateRecoveredFlow {

	public double value = 0.0;

	public double calculate(double RuralInfectedIcuDischargedRecoveredIntermediate, double RuralDischargeStayDuration)
	{
		double result = 0.0;
		
		result = RuralInfectedIcuDischargedRecoveredIntermediate / RuralDischargeStayDuration;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
