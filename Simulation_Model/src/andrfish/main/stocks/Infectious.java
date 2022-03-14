package andrfish.main.stocks;

import andrfish.main.flows.HospitalizationFlow;
import andrfish.main.flows.InfectionFlow;
import andrfish.main.flows.RecoveredFlow;

public class Infectious {
	public double value = 0.0;
	
	private InfectionFlow infectionFlow;
	private HospitalizationFlow hospitalizationFlow;
	private RecoveredFlow recoveredFlow;

	public Infectious(	double initialValue,
						InfectionFlow infectionFlow,
						HospitalizationFlow hospitalizationFlow,
						RecoveredFlow recoveredFlow) 
	{
		value = initialValue;
		this.infectionFlow = infectionFlow;
		this.hospitalizationFlow = hospitalizationFlow;
		this.recoveredFlow = recoveredFlow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = infectionFlow.value - hospitalizationFlow.value - recoveredFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
