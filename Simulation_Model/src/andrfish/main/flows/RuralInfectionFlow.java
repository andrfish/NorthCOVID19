package andrfish.main.flows;

public class RuralInfectionFlow {
	
	public double value = 0.0;

	public double calculate(double RuralTotalInfected, double RuralTotalPopulation, double RuralMaxInfectedRatio,
			   					   double RuralInfectious, double RuralInfectiousRecoveredIntermediate, double RuralContactRate, double RuralSusceptible,
			   					   double RuralInfectivity)
	{
		double result = 0.0;
		
		result = (RuralTotalInfected / RuralTotalPopulation) >= RuralMaxInfectedRatio
				? 0
				: ((RuralInfectious + RuralInfectiousRecoveredIntermediate) * RuralContactRate * RuralSusceptible * RuralInfectivity)
						/ RuralTotalPopulation;
		
		//Need to check if this is negative due to Java's precision
		if (result < 0) result = 0;
		
		value = result;
		return result;
	}
}
