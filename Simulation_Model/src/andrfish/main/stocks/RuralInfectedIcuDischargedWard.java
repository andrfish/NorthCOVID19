package andrfish.main.stocks;

import andrfish.main.flows.RuralInfectedIcuDischargedRecoveredFlow;
import andrfish.main.flows.RuralInfectedIcuWardIntermediateFlow;

public class RuralInfectedIcuDischargedWard {
	public double value = 0.0;

	private RuralInfectedIcuWardIntermediateFlow ruralInfectedIcuWardIntermediateFlow;
	private RuralInfectedIcuDischargedRecoveredFlow ruralInfectedIcuDischargedRecoveredFlow;
	

	public RuralInfectedIcuDischargedWard(RuralInfectedIcuWardIntermediateFlow ruralInfectedIcuWardIntermediateFlow,
									 	  RuralInfectedIcuDischargedRecoveredFlow ruralInfectedIcuDischargedRecoveredFlow) 
	{
		this.ruralInfectedIcuWardIntermediateFlow = ruralInfectedIcuWardIntermediateFlow;
		this.ruralInfectedIcuDischargedRecoveredFlow = ruralInfectedIcuDischargedRecoveredFlow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = ruralInfectedIcuWardIntermediateFlow.splitValue[0] + ruralInfectedIcuWardIntermediateFlow.splitValue[1] - ruralInfectedIcuDischargedRecoveredFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
