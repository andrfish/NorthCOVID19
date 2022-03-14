package andrfish.main.flows;

public class RuralInfectedIcuWardFlow {
	//ZERO IS THE FLOW FROM RuralInfectedIcu
	//ONE IS THE FLOW FROM  InfectedIcuRuralUrban
	public double[] splitValue = new double[2];

	public double[] calculate(double RuralInfectedIcu,
							  double InfectedIcuRuralUrban,
							  double[] RuralInfectedDeathFlow,
							  double[] RuralInfectedIcuFlow,
							  double RuralDeathRate)
	{
		splitValue[0] = 0;
		splitValue[1] = 0;
		
		double toWard = RuralInfectedIcu + RuralInfectedIcuFlow[0] - RuralInfectedDeathFlow[0];
		if(RuralInfectedDeathFlow[0] > 0 || RuralDeathRate == 0)
			splitValue[0] = toWard;
		
		toWard = InfectedIcuRuralUrban + RuralInfectedIcuFlow[1] - RuralInfectedDeathFlow[1];
		if(RuralInfectedDeathFlow[1] > 0 || RuralDeathRate == 0)
			splitValue[1] = toWard;
		
		//Need to check if this is negative due to Java's precision
		if (splitValue[0] < 0) splitValue[0] = 0;
		if (splitValue[1] < 0) splitValue[1] = 0;

		return splitValue;
	}
}
