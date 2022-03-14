package andrfish.main.stocks;

import andrfish.main.flows.RuralInfectionIntermediateRecoveredFlow;
import andrfish.main.flows.RuralRecoveredFlow;

public class RuralInfectiousRecoveredIntermediate {
	public double value = 0.0;

	private RuralRecoveredFlow ruralRecoveredFlow;
	private RuralInfectionIntermediateRecoveredFlow ruralInfectionIntermediateRecoveredFlow;

	public RuralInfectiousRecoveredIntermediate(RuralRecoveredFlow ruralRecoveredFlow,
										   		RuralInfectionIntermediateRecoveredFlow ruralInfectionIntermediateRecoveredFlow) 
	{
		this.ruralRecoveredFlow = ruralRecoveredFlow;
		this.ruralInfectionIntermediateRecoveredFlow = ruralInfectionIntermediateRecoveredFlow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = ruralRecoveredFlow.value - ruralInfectionIntermediateRecoveredFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
