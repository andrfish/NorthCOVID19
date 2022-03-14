package andrfish.main.stocks;

import andrfish.main.flows.RuralInfectedIcuDischargedIntermediateRecoveredFlow;
import andrfish.main.flows.RuralInfectedWardIntermediateRecoveredFlow;
import andrfish.main.flows.RuralInfectionIntermediateRecoveredFlow;

public class RuralRecovered {
	public double value = 0.0;
	
	private RuralInfectionIntermediateRecoveredFlow ruralInfectionIntermediateRecoveredFlow;
	private RuralInfectedWardIntermediateRecoveredFlow ruralInfectedWardIntermediateRecoveredFlow;
	private RuralInfectedIcuDischargedIntermediateRecoveredFlow ruralInfectedIcuDischargedIntermediateRecoveredFlow;
	
	public RuralRecovered(RuralInfectionIntermediateRecoveredFlow ruralInfectionIntermediateRecoveredFlow,
					 	  RuralInfectedWardIntermediateRecoveredFlow ruralInfectedWardIntermediateRecoveredFlow,
					 	  RuralInfectedIcuDischargedIntermediateRecoveredFlow ruralInfectedIcuDischargedIntermediateRecoveredFlow)
	{
		this.ruralInfectionIntermediateRecoveredFlow = ruralInfectionIntermediateRecoveredFlow;
		this.ruralInfectedWardIntermediateRecoveredFlow = ruralInfectedWardIntermediateRecoveredFlow;
		this.ruralInfectedIcuDischargedIntermediateRecoveredFlow = ruralInfectedIcuDischargedIntermediateRecoveredFlow;
	}
	
	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = ruralInfectionIntermediateRecoveredFlow.value + ruralInfectedWardIntermediateRecoveredFlow.value + ruralInfectedIcuDischargedIntermediateRecoveredFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
