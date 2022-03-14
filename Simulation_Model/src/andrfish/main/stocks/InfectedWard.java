package andrfish.main.stocks;

import andrfish.main.flows.InfectedIcuIntermediateRecoveryOverflow;
import andrfish.main.flows.InfectedRecoveredFlow;
import andrfish.main.flows.InfectedWardFlow;
import andrfish.main.flows.InfectedWardIcuFlow;

public class InfectedWard {
	public double value = 0.0;
	
	private InfectedIcuIntermediateRecoveryOverflow infectedIcuIntermediateRecoveryOverflow;
	private InfectedWardFlow infectedWardFlow;
	private InfectedRecoveredFlow infectedRecoveredFlow;
	private InfectedWardIcuFlow infectedWardIcuFlow;

	public InfectedWard(InfectedIcuIntermediateRecoveryOverflow infectedIcuIntermediateRecoveryOverflow,
						InfectedWardFlow infectedWardFlow,
						InfectedRecoveredFlow infectedRecoveredFlow,
						InfectedWardIcuFlow infectedWardIcuFlow) 
	{
		this.infectedIcuIntermediateRecoveryOverflow = infectedIcuIntermediateRecoveryOverflow;
		this.infectedWardFlow = infectedWardFlow;
		this.infectedRecoveredFlow = infectedRecoveredFlow;
		this.infectedWardIcuFlow = infectedWardIcuFlow;	
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = infectedIcuIntermediateRecoveryOverflow.value + infectedWardFlow.value - infectedRecoveredFlow.value - infectedWardIcuFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
