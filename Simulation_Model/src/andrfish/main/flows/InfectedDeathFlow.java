package andrfish.main.flows;

public class InfectedDeathFlow {

	public double value = 0.0;

	public double calculate(double InfectedIcu, double InfectedIcuWardIntermediate, double MaxIcuCapacity, 
							double InfectedIcuFlow, double DeathRate, double InfectedWardIcuFlow, 
							double InfectedIcuRuralUrban, double InfectedIcuRuralWardIntermediate,
							double RuralInfectedWardIcuFlow1)
	{
		double result = 0.0;
		
		double toDeath = (InfectedIcu + InfectedIcuFlow) * DeathRate;
		
		double currentIcu = InfectedIcu + InfectedIcuRuralUrban + InfectedIcuFlow + InfectedWardIcuFlow 
							- toDeath + RuralInfectedWardIcuFlow1;

		if(currentIcu < MaxIcuCapacity - InfectedIcuWardIntermediate - InfectedIcuRuralWardIntermediate)
			result = toDeath;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
