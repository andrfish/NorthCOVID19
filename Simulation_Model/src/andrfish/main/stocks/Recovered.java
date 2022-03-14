package andrfish.main.stocks;

import andrfish.main.flows.InfectedIcuDischargedIntermediateRecoveredFlow;
import andrfish.main.flows.InfectedWardIntermediateRecoveredFlow;
import andrfish.main.flows.InfectionIntermediateRecoveredFlow;

public class Recovered {
	public double value = 0.0;
	
	private InfectionIntermediateRecoveredFlow infectionIntermediateRecoveredFlow;
	private InfectedWardIntermediateRecoveredFlow infectedWardIntermediateRecoveredFlow;
	private InfectedIcuDischargedIntermediateRecoveredFlow infectedIcuDischargedIntermediateRecoveredFlow;
	
	public Recovered(InfectionIntermediateRecoveredFlow infectionIntermediateRecoveredFlow,
					 InfectedWardIntermediateRecoveredFlow infectedWardIntermediateRecoveredFlow,
					 InfectedIcuDischargedIntermediateRecoveredFlow infectedIcuDischargedIntermediateRecoveredFlow)
	{
		this.infectionIntermediateRecoveredFlow = infectionIntermediateRecoveredFlow;
		this.infectedWardIntermediateRecoveredFlow = infectedWardIntermediateRecoveredFlow;
		this.infectedIcuDischargedIntermediateRecoveredFlow = infectedIcuDischargedIntermediateRecoveredFlow;
	}
	
	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = infectionIntermediateRecoveredFlow.value + infectedWardIntermediateRecoveredFlow.value + infectedIcuDischargedIntermediateRecoveredFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
