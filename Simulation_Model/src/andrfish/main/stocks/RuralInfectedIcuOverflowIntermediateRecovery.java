package andrfish.main.stocks;

import andrfish.main.flows.RuralInfectedIcuIntermediateRecoveryOverflow;
import andrfish.main.flows.RuralInfectedIcuRecoveryOverflow;

public class RuralInfectedIcuOverflowIntermediateRecovery {
	public double value = 0.0;

	private RuralInfectedIcuRecoveryOverflow ruralInfectedIcuRecoveryOverflow;
	private RuralInfectedIcuIntermediateRecoveryOverflow ruralInfectedIcuIntermediateRecoveryOverflow;

	public RuralInfectedIcuOverflowIntermediateRecovery(RuralInfectedIcuRecoveryOverflow ruralInfectedIcuRecoveryOverflow,
												   		RuralInfectedIcuIntermediateRecoveryOverflow ruralInfectedIcuIntermediateRecoveryOverflow) 
	{
		this.ruralInfectedIcuRecoveryOverflow = ruralInfectedIcuRecoveryOverflow;
		this.ruralInfectedIcuIntermediateRecoveryOverflow = ruralInfectedIcuIntermediateRecoveryOverflow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = ruralInfectedIcuRecoveryOverflow.value - ruralInfectedIcuIntermediateRecoveryOverflow.value;
		
		value += result * dt;
		
		return result;
	}
}
