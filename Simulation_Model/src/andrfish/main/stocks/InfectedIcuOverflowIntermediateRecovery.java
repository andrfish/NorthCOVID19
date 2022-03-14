package andrfish.main.stocks;

import andrfish.main.flows.InfectedIcuIntermediateRecoveryOverflow;
import andrfish.main.flows.InfectedIcuRecoveryOverflow;

public class InfectedIcuOverflowIntermediateRecovery {
	public double value = 0.0;

	private InfectedIcuRecoveryOverflow infectedIcuRecoveryOverflow;
	private InfectedIcuIntermediateRecoveryOverflow infectedIcuIntermediateRecoveryOverflow;

	public InfectedIcuOverflowIntermediateRecovery(InfectedIcuRecoveryOverflow infectedIcuRecoveryOverflow,
												   InfectedIcuIntermediateRecoveryOverflow infectedIcuIntermediateRecoveryOverflow) 
	{
		this.infectedIcuRecoveryOverflow = infectedIcuRecoveryOverflow;
		this.infectedIcuIntermediateRecoveryOverflow = infectedIcuIntermediateRecoveryOverflow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = infectedIcuRecoveryOverflow.value - infectedIcuIntermediateRecoveryOverflow.value;
		
		value += result * dt;
		
		return result;
	}
}
