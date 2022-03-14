package andrfish.main.stocks;

import andrfish.main.flows.HospitalizedDeathIntermediateFlow;
import andrfish.main.flows.InfectedDeathFlow;
import andrfish.main.flows.InfectedIcuDeathOverflow;

public class InfectedDeath {
	public double value = 0.0;
	
	private InfectedDeathFlow infectedDeathFlow;
	private InfectedIcuDeathOverflow infectedIcuDeathOverflow;
	private HospitalizedDeathIntermediateFlow hospitalizedDeathIntermediateFlow;

	public InfectedDeath(InfectedDeathFlow infectedDeathFlow, 
						 InfectedIcuDeathOverflow infectedIcuDeathOverflow,
						 HospitalizedDeathIntermediateFlow hospitalizedDeathIntermediateFlow) 
	{
		this.infectedDeathFlow = infectedDeathFlow;
		this.infectedIcuDeathOverflow = infectedIcuDeathOverflow;
		this.hospitalizedDeathIntermediateFlow = hospitalizedDeathIntermediateFlow;
	}
	
	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = infectedDeathFlow.value + infectedIcuDeathOverflow.value + hospitalizedDeathIntermediateFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
