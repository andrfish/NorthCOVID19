package andrfish.main.stocks;

import andrfish.main.flows.RuralHospitalizedDeathIntermediateFlow;
import andrfish.main.flows.RuralInfectedDeathFlow;
import andrfish.main.flows.RuralInfectedIcuDeathOverflow;

public class RuralInfectedDeath {
	public double value = 0.0;
	
	private RuralInfectedDeathFlow ruralInfectedDeathFlow;
	private RuralInfectedIcuDeathOverflow ruralInfectedIcuDeathOverflow;
	private RuralHospitalizedDeathIntermediateFlow ruralHospitalizedDeathIntermediateFlow;

	public RuralInfectedDeath(RuralInfectedDeathFlow ruralInfectedDeathFlow,
							  RuralInfectedIcuDeathOverflow ruralInfectedIcuDeathOverflow,
							  RuralHospitalizedDeathIntermediateFlow ruralHospitalizedDeathIntermediateFlow) 
	{
		this.ruralInfectedDeathFlow = ruralInfectedDeathFlow;
		this.ruralInfectedIcuDeathOverflow = ruralInfectedIcuDeathOverflow;
		this.ruralHospitalizedDeathIntermediateFlow = ruralHospitalizedDeathIntermediateFlow;
	}
	
	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		//		 From the rural ICU population			From the urban rural ICU population
		result = ruralInfectedDeathFlow.splitValue[0] + ruralInfectedDeathFlow.splitValue[1] + ruralInfectedIcuDeathOverflow.value + ruralHospitalizedDeathIntermediateFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
