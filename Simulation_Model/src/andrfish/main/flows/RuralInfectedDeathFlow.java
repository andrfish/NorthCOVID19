package andrfish.main.flows;

public class RuralInfectedDeathFlow {
	//ZERO IS THE FLOW FROM RuralInfectedIcu
	//ONE IS THE FLOW FROM  InfectedIcuRuralUrban
	public double[] splitValue = new double[2];

	public double[] calculate(double RuralInfectedIcu, double RuralInfectedIcuWardIntermediate, double RuralMaxIcuCapacity, 
							  double[] RuralInfectedIcuFlow, double RuralDeathRate, double RuralInfectedWardIcuFlow0,
							  double InfectedIcuRuralUrban)
	{
		splitValue[0] = 0.0;
		splitValue[1] = 0.0;
		
		double toDeath = (RuralInfectedIcu + RuralInfectedIcuFlow[0]) * RuralDeathRate;
		
		double currentIcu = RuralInfectedIcu + RuralInfectedIcuFlow[0] + RuralInfectedWardIcuFlow0 - toDeath;

		if(currentIcu < RuralMaxIcuCapacity - RuralInfectedIcuWardIntermediate)
			splitValue[0] = toDeath;
		
		splitValue[1] = (InfectedIcuRuralUrban + RuralInfectedIcuFlow[1]) * RuralDeathRate;
		
		//Need to check if this is negative due to Java's precision
		if (splitValue[0] < 0) splitValue[0] = 0;
		if (splitValue[1] < 0) splitValue[1] = 0;

		return splitValue;
	}
}
