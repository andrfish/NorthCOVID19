package andrfish.main.stocks;

import andrfish.main.flows.InfectedIcuDischargedIntermediateRecoveredFlow;
import andrfish.main.flows.InfectedIcuDischargedRecoveredFlow;

public class InfectedIcuDischargedRecoveredIntermediate {
	public double value = 0.0;
	
	private InfectedIcuDischargedRecoveredFlow infectedIcuDischargedRecoveredFlow;
	private InfectedIcuDischargedIntermediateRecoveredFlow infectedIcuDischargedIntermediateRecoveredFlow;

	public InfectedIcuDischargedRecoveredIntermediate(InfectedIcuDischargedRecoveredFlow infectedIcuDischargedRecoveredFlow,
											  		  InfectedIcuDischargedIntermediateRecoveredFlow infectedIcuDischargedIntermediateRecoveredFlow) 
	{
		this.infectedIcuDischargedRecoveredFlow = infectedIcuDischargedRecoveredFlow;
		this.infectedIcuDischargedIntermediateRecoveredFlow = infectedIcuDischargedIntermediateRecoveredFlow;	
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = infectedIcuDischargedRecoveredFlow.value - infectedIcuDischargedIntermediateRecoveredFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
