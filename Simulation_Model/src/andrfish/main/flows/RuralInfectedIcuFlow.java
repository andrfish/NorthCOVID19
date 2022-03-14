package andrfish.main.flows;

public class RuralInfectedIcuFlow {
	//ZERO IS THE FLOW TO RuralInfectedIcu
	//ONE IS THE FLOW TO  InfectedIcuRuralUrban
	public double[] splitValue = new double[2];

	public double[] calculate(double RuralInfectedHospitalized, double RuralIcuRate,
							  double InfectedIcu, double InfectedIcuRuralUrban, double InfectedIcuFlow,
							  double InfectedWardIcuFlow, double InfectedDeathFlow, double InfectedIcuWardFlow, 
							  double MaxIcuCapacity, double InfectedIcuWardIntermediate, double InfectedIcuRuralWardIntermediate,
							  double RuralInfectedWardIcuFlow1)
	{
		splitValue[0] = 0.0;
		splitValue[1] = 0.0;
		
		double toIcu = RuralInfectedHospitalized * RuralIcuRate;
		
		//Check if the urban ICU has availability
		double currentIcu = InfectedIcu + InfectedIcuRuralUrban + InfectedIcuFlow + InfectedWardIcuFlow - InfectedDeathFlow
				- InfectedIcuWardFlow + RuralInfectedWardIcuFlow1;
		
		MaxIcuCapacity -= (InfectedIcuWardIntermediate + InfectedIcuRuralWardIntermediate);
		if((currentIcu + toIcu) <= MaxIcuCapacity)
		{
			splitValue[1] = toIcu;
		}
		else
		{
			//Try to add as many as possible to the urban ICU first
			splitValue[1] = MaxIcuCapacity - currentIcu;
			if (splitValue[1] < 0 || splitValue[1] > toIcu) splitValue[1] = 0;
			
			splitValue[0] = toIcu - splitValue[1];
		}
		
		//Need to check if this is negative due to Java's precision
		if (splitValue[0] < 0) splitValue[0] = 0;
		if (splitValue[1] < 0) splitValue[1] = 0;

		return splitValue;
	}
}
