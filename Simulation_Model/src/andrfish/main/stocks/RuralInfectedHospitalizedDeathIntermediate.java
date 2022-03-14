package andrfish.main.stocks;

import andrfish.main.flows.RuralHospitalizedDeathIntermediateFlow;
import andrfish.main.flows.RuralHospitalizedDeathFlow;

public class RuralInfectedHospitalizedDeathIntermediate {
	public double value = 0.0;

	private RuralHospitalizedDeathFlow ruralHospitalizedDeathFlow;
	private RuralHospitalizedDeathIntermediateFlow ruralHospitalizedDeathIntermediateFlow;

	public RuralInfectedHospitalizedDeathIntermediate(RuralHospitalizedDeathFlow ruralHospitalizedDeathFlow,
													  RuralHospitalizedDeathIntermediateFlow ruralHospitalizedDeathIntermediateFlow) 
	{
		this.ruralHospitalizedDeathFlow = ruralHospitalizedDeathFlow;
		this.ruralHospitalizedDeathIntermediateFlow = ruralHospitalizedDeathIntermediateFlow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = ruralHospitalizedDeathFlow.value - ruralHospitalizedDeathIntermediateFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
