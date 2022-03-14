package andrfish.main.flows;

public class InfectionFlow {
	
	public double value = 0.0;

	public double calculate(double TotalInfected, double TotalPopulation, double MaxInfectedRatio,
			   					   double Infectious, double InfectiousRecoveredIntermediate, double ContactRate, double Susceptible,
			   					   double Infectivity)
	{
		double result = 0.0;
		
		result = (TotalInfected / TotalPopulation) >= MaxInfectedRatio
				? 0
				: ((Infectious + InfectiousRecoveredIntermediate) * ContactRate * Susceptible * Infectivity)
						/ TotalPopulation;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
