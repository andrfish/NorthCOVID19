package andrfish.main.stocks;

import andrfish.main.flows.RuralInfectedDeathFlow;
import andrfish.main.flows.RuralInfectedIcuDeathOverflow;
import andrfish.main.flows.RuralInfectedIcuFlow;
import andrfish.main.flows.RuralInfectedIcuRecoveryOverflow;
import andrfish.main.flows.RuralInfectedIcuWardFlow;
import andrfish.main.flows.RuralInfectedWardIcuFlow;

public class RuralInfectedIcu {
	public double value = 0.0;
	
	private RuralInfectedIcuFlow ruralInfectedIcuFlow;
	private RuralInfectedWardIcuFlow ruralInfectedWardIcuFlow;
	private RuralInfectedIcuWardFlow ruralInfectedIcuWardFlow;
	private RuralInfectedDeathFlow ruralInfectedDeathFlow;
	private RuralInfectedIcuRecoveryOverflow ruralInfectedIcuRecoveryOverflow;
	private RuralInfectedIcuDeathOverflow ruralInfectedIcuDeathOverflow;
	
	
	public RuralInfectedIcu(RuralInfectedIcuFlow ruralInfectedIcuFlow,
							RuralInfectedWardIcuFlow ruralInfectedWardIcuFlow,
							RuralInfectedIcuWardFlow ruralInfectedIcuWardFlow,
							RuralInfectedDeathFlow ruralInfectedDeathFlow,
							RuralInfectedIcuRecoveryOverflow ruralInfectedIcuRecoveryOverflow,
							RuralInfectedIcuDeathOverflow ruralInfectedIcuDeathOverflow) 
	{
		this.ruralInfectedIcuFlow = ruralInfectedIcuFlow;
		this.ruralInfectedWardIcuFlow = ruralInfectedWardIcuFlow;
		this.ruralInfectedIcuWardFlow = ruralInfectedIcuWardFlow;
		this.ruralInfectedDeathFlow = ruralInfectedDeathFlow;
		this.ruralInfectedIcuRecoveryOverflow = ruralInfectedIcuRecoveryOverflow;
		this.ruralInfectedIcuDeathOverflow = ruralInfectedIcuDeathOverflow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = ruralInfectedIcuFlow.splitValue[0] + ruralInfectedWardIcuFlow.splitValue[0] 
				- ruralInfectedIcuWardFlow.splitValue[0] - ruralInfectedDeathFlow.splitValue[0] 
				- ruralInfectedIcuRecoveryOverflow.value - ruralInfectedIcuDeathOverflow.value;
		
		value += result * dt;
		
		//Rounding issues when ICU > Death path is disabled
		if(value < 0) value = 0;
		
		return result;
	}
}
