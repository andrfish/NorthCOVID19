package andrfish.main.stocks;

import andrfish.main.flows.InfectedIcuDischargedRecoveredFlow;
import andrfish.main.flows.InfectedIcuWardIntermediateFlow;

public class InfectedIcuDischargedWard {
	public double value = 0.0;

	private InfectedIcuWardIntermediateFlow infectedIcuWardIntermediateFlow;
	private InfectedIcuDischargedRecoveredFlow infectedIcuDischargedRecoveredFlow;
	

	public InfectedIcuDischargedWard(InfectedIcuWardIntermediateFlow infectedIcuWardIntermediateFlow,
									 InfectedIcuDischargedRecoveredFlow infectedIcuDischargedRecoveredFlow) 
	{
		this.infectedIcuWardIntermediateFlow = infectedIcuWardIntermediateFlow;
		this.infectedIcuDischargedRecoveredFlow = infectedIcuDischargedRecoveredFlow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = infectedIcuWardIntermediateFlow.value - infectedIcuDischargedRecoveredFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
