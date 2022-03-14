package andrfish.main.stocks;

import andrfish.main.flows.InfectionFlow;

public class Susceptible {
	public double value = 0.0;
	
	private InfectionFlow infectionFlow;

	public Susceptible(double initialValue, InfectionFlow infectionFlow) 
	{
		value = initialValue;
		this.infectionFlow = infectionFlow;
	}
	
	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = -infectionFlow.value;
		
		value += result * dt;
		
		return result;
	}

}
