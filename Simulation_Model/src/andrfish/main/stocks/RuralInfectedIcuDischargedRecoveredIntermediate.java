package andrfish.main.stocks;

import andrfish.main.flows.RuralInfectedIcuDischargedIntermediateRecoveredFlow;
import andrfish.main.flows.RuralInfectedIcuDischargedRecoveredFlow;

public class RuralInfectedIcuDischargedRecoveredIntermediate {
	public double value = 0.0;
	
	private RuralInfectedIcuDischargedRecoveredFlow ruralInfectedIcuDischargedRecoveredFlow;
	private RuralInfectedIcuDischargedIntermediateRecoveredFlow ruralInfectedIcuDischargedIntermediateRecoveredFlow;

	public RuralInfectedIcuDischargedRecoveredIntermediate(RuralInfectedIcuDischargedRecoveredFlow ruralInfectedIcuDischargedRecoveredFlow,
											  		  	   RuralInfectedIcuDischargedIntermediateRecoveredFlow ruralInfectedIcuDischargedIntermediateRecoveredFlow) 
	{
		this.ruralInfectedIcuDischargedRecoveredFlow = ruralInfectedIcuDischargedRecoveredFlow;
		this.ruralInfectedIcuDischargedIntermediateRecoveredFlow = ruralInfectedIcuDischargedIntermediateRecoveredFlow;	
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = ruralInfectedIcuDischargedRecoveredFlow.value - ruralInfectedIcuDischargedIntermediateRecoveredFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
