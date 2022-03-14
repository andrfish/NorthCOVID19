package andrfish.main.stocks;

import andrfish.main.flows.RuralInfectedIcuIntermediateRecoveryOverflow;
import andrfish.main.flows.RuralInfectedRecoveredFlow;
import andrfish.main.flows.RuralInfectedWardFlow;
import andrfish.main.flows.RuralInfectedWardIcuFlow;

public class RuralInfectedWard {
	public double value = 0.0;
	
	private RuralInfectedIcuIntermediateRecoveryOverflow ruralInfectedIcuIntermediateRecoveryOverflow;
	private RuralInfectedWardFlow ruralInfectedWardFlow;
	private RuralInfectedRecoveredFlow ruralInfectedRecoveredFlow;
	private RuralInfectedWardIcuFlow ruralInfectedWardIcuFlow;

	public RuralInfectedWard(	RuralInfectedIcuIntermediateRecoveryOverflow ruralInfectedIcuIntermediateRecoveryOverflow,
								RuralInfectedWardFlow ruralInfectedWardFlow,
								RuralInfectedRecoveredFlow ruralInfectedRecoveredFlow,
								RuralInfectedWardIcuFlow ruralInfectedWardIcuFlow) 
	{
		this.ruralInfectedIcuIntermediateRecoveryOverflow = ruralInfectedIcuIntermediateRecoveryOverflow;
		this.ruralInfectedWardFlow = ruralInfectedWardFlow;
		this.ruralInfectedRecoveredFlow = ruralInfectedRecoveredFlow;
		this.ruralInfectedWardIcuFlow = ruralInfectedWardIcuFlow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = ruralInfectedIcuIntermediateRecoveryOverflow.value
				+ ruralInfectedWardFlow.value - ruralInfectedRecoveredFlow.value 
				- ruralInfectedWardIcuFlow.splitValue[0] - ruralInfectedWardIcuFlow.splitValue[1];
		//		  To the rural ICU population			   To the urban ICU population
		
		value += result * dt;
		
		return result;
	}
}
