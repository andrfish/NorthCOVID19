package andrfish.main.flows;

public class RuralInfectedWardIcuFlow {

	//ZERO IS THE FLOW TO RuralInfectedIcu
	//ONE IS THE FLOW TO  InfectedIcuRuralUrban
	public double[] splitValue = new double[2];

	public double[] calculate(double RuralInfectedWard, double RuralWardToIcuRate,
							double InfectedIcu, double InfectedIcuRuralUrban, double InfectedIcuFlow,
							double InfectedWardIcuFlow, double InfectedDeathFlow, double InfectedIcuWardFlow, 
							double MaxIcuCapacity, double InfectedIcuWardIntermediate, double InfectedIcuRuralWardIntermediate,
							double RuralInfectedDeathFlow1, double RuralInfectedIcuWardFlow1)
	{
		splitValue[0] = 0.0;
		splitValue[1] = 0.0;
		
		double toIcu = RuralInfectedWard * RuralWardToIcuRate;
		
		//Check if the urban ICU has availability
		double currentIcu = InfectedIcu + InfectedIcuRuralUrban + InfectedIcuFlow + InfectedWardIcuFlow
							- InfectedDeathFlow - InfectedIcuWardFlow - RuralInfectedDeathFlow1 - RuralInfectedIcuWardFlow1;

		MaxIcuCapacity -= (InfectedIcuWardIntermediate + InfectedIcuRuralWardIntermediate);
		if((currentIcu + toIcu) < MaxIcuCapacity)
		{
			splitValue[1] = toIcu;
		}
		else
		{
			splitValue[0] = toIcu;
		}
		
		//Need to check if this is negative due to Java's precision
		if (splitValue[0] < 0) splitValue[0] = 0;
		if (splitValue[1] < 0) splitValue[1] = 0;

		return splitValue;
	}
}
