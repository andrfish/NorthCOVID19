package andrfish.main.stocks;

import andrfish.main.flows.InfectedRecoveredFlow;
import andrfish.main.flows.InfectedWardIntermediateRecoveredFlow;

public class InfectedWardRecoveredIntermediate {
	public double value = 0.0;
	
	private InfectedRecoveredFlow infectedRecoveredFlow;
	private InfectedWardIntermediateRecoveredFlow infectedWardIntermediateRecoveredFlow;

	public InfectedWardRecoveredIntermediate(InfectedRecoveredFlow infectedRecoveredFlow,
											 InfectedWardIntermediateRecoveredFlow infectedWardIntermediateRecoveredFlow) 
	{
		this.infectedRecoveredFlow = infectedRecoveredFlow;
		this.infectedWardIntermediateRecoveredFlow = infectedWardIntermediateRecoveredFlow;	
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = infectedRecoveredFlow.value - infectedWardIntermediateRecoveredFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
