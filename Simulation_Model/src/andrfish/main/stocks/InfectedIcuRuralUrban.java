package andrfish.main.stocks;

import andrfish.main.flows.RuralInfectedDeathFlow;
import andrfish.main.flows.RuralInfectedIcuFlow;
import andrfish.main.flows.RuralInfectedIcuWardFlow;
import andrfish.main.flows.RuralInfectedWardIcuFlow;

public class InfectedIcuRuralUrban {
	public double value = 0.0;

	private RuralInfectedIcuFlow ruralInfectedIcuFlow;
	private RuralInfectedWardIcuFlow ruralInfectedWardIcuFlow;
	
	private RuralInfectedIcuWardFlow ruralInfectedIcuWardFlow;
	private RuralInfectedDeathFlow ruralInfectedDeathFlow;

	public InfectedIcuRuralUrban(RuralInfectedIcuFlow ruralInfectedIcuFlow,
								 RuralInfectedWardIcuFlow ruralInfectedWardIcuFlow,
								 RuralInfectedIcuWardFlow ruralInfectedIcuWardFlow,
								 RuralInfectedDeathFlow ruralInfectedDeathFlow) 
	{
		this.ruralInfectedIcuFlow = ruralInfectedIcuFlow;
		this.ruralInfectedWardIcuFlow = ruralInfectedWardIcuFlow;
		
		this.ruralInfectedIcuWardFlow = ruralInfectedIcuWardFlow;
		this.ruralInfectedDeathFlow = ruralInfectedDeathFlow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = ruralInfectedIcuFlow.splitValue[1]  + ruralInfectedWardIcuFlow.splitValue[1] - ruralInfectedIcuWardFlow.splitValue[1] - ruralInfectedDeathFlow.splitValue[1];
		
		value += result * dt;
		
		return result;
	}
}
