package andrfish.main.flows;

public class InfectedIcuRecoveryOverflow {

	public double value = 0.0;

	public double calculate(double InfectedIcu, double InfectedIcuWardIntermediate, double MaxIcuCapacity,
						    double InfectedIcuFlow, double InfectedWardIcuFlow, double InfectedDeathFlow, 
						    double InfectedIcuWardFlow,
						    double InfectedIcuDeathOverflow, double InfectedIcuRuralUrban,
						    double InfectedIcuRuralWardIntermediate,
						    double RuralInfectedDeathFlow1,
						    double RuralInfectedIcuWardFlow1,
						    double RuralInfectedWardIcuFlow1)
	{
		double result = 0.0;
		
		double currentIcu = InfectedIcu + InfectedIcuRuralUrban + InfectedIcuFlow + InfectedWardIcuFlow - InfectedDeathFlow
				- InfectedIcuWardFlow - RuralInfectedDeathFlow1 - RuralInfectedIcuWardFlow1 + RuralInfectedWardIcuFlow1;
		
		MaxIcuCapacity -= (InfectedIcuWardIntermediate + InfectedIcuRuralWardIntermediate);
		result = currentIcu >= MaxIcuCapacity
				? (currentIcu - MaxIcuCapacity + 0.5) - InfectedIcuDeathOverflow
				: 0;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
				
		value = result;
		return result;
	}
}
