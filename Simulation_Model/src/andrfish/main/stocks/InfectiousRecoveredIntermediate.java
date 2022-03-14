package andrfish.main.stocks;

import andrfish.main.flows.InfectionIntermediateRecoveredFlow;
import andrfish.main.flows.RecoveredFlow;

public class InfectiousRecoveredIntermediate {
	public double value = 0.0;

	private RecoveredFlow recoveredFlow;
	private InfectionIntermediateRecoveredFlow infectionIntermediateRecoveredFlow;

	public InfectiousRecoveredIntermediate(RecoveredFlow recoveredFlow,
										   InfectionIntermediateRecoveredFlow infectionIntermediateRecoveredFlow) 
	{
		this.recoveredFlow = recoveredFlow;
		this.infectionIntermediateRecoveredFlow = infectionIntermediateRecoveredFlow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = recoveredFlow.value - infectionIntermediateRecoveredFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
