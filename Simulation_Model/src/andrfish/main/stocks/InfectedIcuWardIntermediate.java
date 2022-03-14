package andrfish.main.stocks;

import andrfish.main.flows.InfectedIcuWardFlow;
import andrfish.main.flows.InfectedIcuWardIntermediateFlow;

public class InfectedIcuWardIntermediate {
	public double value = 0.0;

	private InfectedIcuWardFlow infectedIcuWardFlow;
	private InfectedIcuWardIntermediateFlow infectedIcuWardIntermediateFlow;

	public InfectedIcuWardIntermediate(InfectedIcuWardFlow infectedIcuWardFlow,
									   InfectedIcuWardIntermediateFlow infectedIcuWardIntermediateFlow) 
	{
		this.infectedIcuWardFlow = infectedIcuWardFlow;
		this.infectedIcuWardIntermediateFlow = infectedIcuWardIntermediateFlow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = infectedIcuWardFlow.value - infectedIcuWardIntermediateFlow.value;
		
		value += result * dt;
		
		return result;
	}
}