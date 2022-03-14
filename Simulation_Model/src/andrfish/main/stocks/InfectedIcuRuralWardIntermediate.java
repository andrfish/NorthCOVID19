package andrfish.main.stocks;

import andrfish.main.flows.RuralInfectedIcuWardFlow;
import andrfish.main.flows.RuralInfectedIcuWardIntermediateFlow;

public class InfectedIcuRuralWardIntermediate {
	public double value = 0.0;

	private RuralInfectedIcuWardFlow ruralInfectedIcuWardFlow;
	private RuralInfectedIcuWardIntermediateFlow ruralInfectedIcuWardIntermediateFlow;

	public InfectedIcuRuralWardIntermediate(RuralInfectedIcuWardFlow ruralInfectedIcuWardFlow,
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
		
		//From the rural population in the urban ICU
		result = ruralInfectedIcuWardFlow.splitValue[1] - ruralInfectedIcuWardIntermediateFlow.splitValue[1];
		
		value += result * dt;
		
		return result;
	}
}