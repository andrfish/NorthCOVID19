package andrfish.main.flows;

public class InfectedIcuWardFlow {

	public double value = 0.0;

	public double calculate(double InfectedIcu, double InfectedIcuWardIntermediate, double MaxIcuCapacity,
							double InfectedDeathFlow, double InfectedIcuFlow, double DeathRate)
	{
		double result = 0.0;
		
		double toWard = InfectedIcu + InfectedIcuFlow - InfectedDeathFlow;

		if(InfectedDeathFlow > 0 || DeathRate == 0)
			result = toWard;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
