package andrfish.main.stocks;

import andrfish.main.flows.HospitalizedDeathIntermediateFlow;
import andrfish.main.flows.HospitalizedDeathFlow;

public class InfectedHospitalizedDeathIntermediate {
	public double value = 0.0;

	private HospitalizedDeathFlow hospitalizationDeathFlow;
	private HospitalizedDeathIntermediateFlow hospitalizedDeathIntermediateFlow;

	public InfectedHospitalizedDeathIntermediate(HospitalizedDeathFlow hospitalizationDeathFlow,
												 HospitalizedDeathIntermediateFlow hospitalizedDeathIntermediateFlow) 
	{
		this.hospitalizationDeathFlow = hospitalizationDeathFlow;
		this.hospitalizedDeathIntermediateFlow = hospitalizedDeathIntermediateFlow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = hospitalizationDeathFlow.value - hospitalizedDeathIntermediateFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
