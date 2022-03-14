package andrfish.main.flows;

public class RuralInfectedIcuWardIntermediateFlow {

	//ZERO IS FROM RuralInfectedIcuWardIntermediate
	//ONE IS FROM  InfectedIcuRuralWardIntermediate
	public double[] splitValue = new double[2];

	public double[] calculate(double RuralInfectedIcuWardIntermediate, double InfectedIcuRuralWardIntermediate,
							  double RuralIcuStayRate)
	{
		splitValue[0] = 0.0;
		splitValue[1] = 0.0;
		
		splitValue[0] = RuralInfectedIcuWardIntermediate / RuralIcuStayRate;
		splitValue[1] = InfectedIcuRuralWardIntermediate / RuralIcuStayRate;
		
		//Need to check if this is negative due to Java's precision
		if (splitValue[0] < 0) splitValue[0] = 0;
		if (splitValue[1] < 0) splitValue[1] = 0;

		return splitValue;
	}
}
