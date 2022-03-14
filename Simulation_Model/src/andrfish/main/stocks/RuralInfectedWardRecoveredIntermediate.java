package andrfish.main.stocks;

import andrfish.main.flows.RuralInfectedRecoveredFlow;
import andrfish.main.flows.RuralInfectedWardIntermediateRecoveredFlow;

public class RuralInfectedWardRecoveredIntermediate {
	public double value = 0.0;
	
	private RuralInfectedRecoveredFlow ruralInfectedRecoveredFlow;
	private RuralInfectedWardIntermediateRecoveredFlow ruralInfectedWardIntermediateRecoveredFlow;

	public RuralInfectedWardRecoveredIntermediate(RuralInfectedRecoveredFlow ruralInfectedRecoveredFlow,
											 	  RuralInfectedWardIntermediateRecoveredFlow ruralInfectedWardIntermediateRecoveredFlow) 
	{
		this.ruralInfectedRecoveredFlow = ruralInfectedRecoveredFlow;
		this.ruralInfectedWardIntermediateRecoveredFlow = ruralInfectedWardIntermediateRecoveredFlow;	
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = ruralInfectedRecoveredFlow.value - ruralInfectedWardIntermediateRecoveredFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
