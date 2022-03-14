package andrfish.main.stocks;

import andrfish.main.flows.RuralHospitalizationFlow;
import andrfish.main.flows.RuralInfectionFlow;
import andrfish.main.flows.RuralRecoveredFlow;

public class RuralInfectious {
	public double value = 0.0;
	
	private RuralInfectionFlow ruralInfectionFlow;
	private RuralHospitalizationFlow ruralHospitalizationFlow;
	private RuralRecoveredFlow ruralRecoveredFlow;

	public RuralInfectious(	double initialValue, 
							RuralInfectionFlow ruralInfectionFlow,
							RuralHospitalizationFlow ruralHospitalizationFlow,
							RuralRecoveredFlow ruralRecoveredFlow) 
	{
		value = initialValue;
		this.ruralInfectionFlow = ruralInfectionFlow;
		this.ruralHospitalizationFlow = ruralHospitalizationFlow;
		this.ruralRecoveredFlow = ruralRecoveredFlow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = ruralInfectionFlow.value - ruralHospitalizationFlow.value - ruralRecoveredFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
