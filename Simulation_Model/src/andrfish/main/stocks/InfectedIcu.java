package andrfish.main.stocks;

import andrfish.main.flows.InfectedDeathFlow;
import andrfish.main.flows.InfectedIcuDeathOverflow;
import andrfish.main.flows.InfectedIcuFlow;
import andrfish.main.flows.InfectedIcuRecoveryOverflow;
import andrfish.main.flows.InfectedIcuWardFlow;
import andrfish.main.flows.InfectedWardIcuFlow;

public class InfectedIcu {
	public double value = 0.0;
	
	private InfectedIcuFlow infectedIcuFlow;
	private InfectedWardIcuFlow infectedWardIcuFlow;
	private InfectedIcuWardFlow infectedIcuWardFlow;
	private InfectedDeathFlow infectedDeathFlow;
	private InfectedIcuRecoveryOverflow infectedIcuRecoveryOverflow;
	private InfectedIcuDeathOverflow infectedIcuDeathOverflow;

	public InfectedIcu( InfectedIcuFlow infectedIcuFlow,
						InfectedWardIcuFlow infectedWardIcuFlow,
						InfectedIcuWardFlow infectedIcuWardFlow,
						InfectedDeathFlow infectedDeathFlow,
						InfectedIcuRecoveryOverflow infectedIcuRecoveryOverflow,
						InfectedIcuDeathOverflow infectedIcuDeathOverflow) 
	{
		this.infectedIcuFlow = infectedIcuFlow;
		this.infectedWardIcuFlow = infectedWardIcuFlow;
		this.infectedIcuWardFlow = infectedIcuWardFlow;
		this.infectedDeathFlow = infectedDeathFlow;
		this.infectedIcuRecoveryOverflow = infectedIcuRecoveryOverflow;
		this.infectedIcuDeathOverflow = infectedIcuDeathOverflow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = infectedIcuFlow.value + infectedWardIcuFlow.value 
				- infectedIcuWardFlow.value - infectedDeathFlow.value
				- infectedIcuRecoveryOverflow.value - infectedIcuDeathOverflow.value;
		
		value += result * dt;
		
		//Rounding issues when ICU > Death path is disabled
		if(value < 0) value = 0;
		
		return result;
	}
}
