package andrfish.main.stocks;

import andrfish.main.flows.RuralInfectedIcuWardFlow;
import andrfish.main.flows.RuralInfectedIcuWardIntermediateFlow;

public class RuralInfectedIcuWardIntermediate {
	public double value = 0.0;

	private RuralInfectedIcuWardFlow ruralInfectedIcuWardFlow;
	private RuralInfectedIcuWardIntermediateFlow ruralInfectedIcuWardIntermediateFlow;

	public RuralInfectedIcuWardIntermediate(RuralInfectedIcuWardFlow ruralInfectedIcuWardFlow,
											RuralInfectedIcuWardIntermediateFlow ruralInfectedIcuWardIntermediateFlow) 
	{
		this.ruralInfectedIcuWardFlow = ruralInfectedIcuWardFlow;
		this.ruralInfectedIcuWardIntermediateFlow = ruralInfectedIcuWardIntermediateFlow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		//From the rural ICU population
		result = ruralInfectedIcuWardFlow.splitValue[0] - ruralInfectedIcuWardIntermediateFlow.splitValue[0];
		
		value += result * dt;
		
		return result;
	}
}
