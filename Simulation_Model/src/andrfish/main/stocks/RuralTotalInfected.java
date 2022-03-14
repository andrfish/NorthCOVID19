package andrfish.main.stocks;

import andrfish.main.flows.RuralInfectionFlow;

public class RuralTotalInfected {
	public double value = 0.0;
	
	private RuralInfectionFlow ruralInfectionFlow;
	
	public RuralTotalInfected(double initialValue, RuralInfectionFlow ruralInfectionFlow) 
	{
		value = initialValue;
		this.ruralInfectionFlow = ruralInfectionFlow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = ruralInfectionFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
