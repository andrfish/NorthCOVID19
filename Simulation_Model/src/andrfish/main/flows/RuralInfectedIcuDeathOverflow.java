package andrfish.main.flows;

public class RuralInfectedIcuDeathOverflow {

	public double value = 0.0;

	public double calculate(double RuralInfectedIcu, double RuralInfectedIcuWardIntermediate, double RuralMaxIcuCapacity, 
						    double RuralInfectedIcuFlow0, double RuralInfectedWardIcuFlow0, double RuralInfectedDeathFlow0,
						    double RuralInfectedIcuWardFlow0, double RuralIcuOverflowRecoveryRate)
	{
		double result = 0.0;
		
		double currentIcu = RuralInfectedIcu + RuralInfectedIcuFlow0 + RuralInfectedWardIcuFlow0 - RuralInfectedDeathFlow0
							- RuralInfectedIcuWardFlow0;
		
		RuralMaxIcuCapacity -= (RuralInfectedIcuWardIntermediate);
		result = currentIcu >= RuralMaxIcuCapacity
				? (currentIcu - RuralMaxIcuCapacity + 0.5) * (1 - RuralIcuOverflowRecoveryRate)
				: 0;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
