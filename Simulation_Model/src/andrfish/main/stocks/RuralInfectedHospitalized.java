package andrfish.main.stocks;

import andrfish.main.flows.RuralHospitalizationFlow;
import andrfish.main.flows.RuralHospitalizedDeathFlow;
import andrfish.main.flows.RuralInfectedIcuFlow;
import andrfish.main.flows.RuralInfectedWardFlow;

public class RuralInfectedHospitalized {
	public double value = 0.0;
	
	private RuralHospitalizationFlow ruralHospitalizationFlow;
	private RuralInfectedWardFlow ruralInfectedWardFlow;
	private RuralInfectedIcuFlow ruralInfectedIcuFlow;
	private RuralHospitalizedDeathFlow ruralHospitalizedDeathFlow;

	public RuralInfectedHospitalized(	RuralHospitalizationFlow ruralHospitalizationFlow,
										RuralInfectedWardFlow ruralInfectedWardFlow,
										RuralInfectedIcuFlow ruralInfectedIcuFlow,
										RuralHospitalizedDeathFlow ruralHospitalizedDeathFlow) 
	{
		this.ruralHospitalizationFlow = ruralHospitalizationFlow;
		this.ruralInfectedWardFlow = ruralInfectedWardFlow;
		this.ruralInfectedIcuFlow = ruralInfectedIcuFlow;
		this.ruralHospitalizedDeathFlow = ruralHospitalizedDeathFlow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		//																		To the rural ICU population			 To the urban ICU population
		result = ruralHospitalizationFlow.value - ruralInfectedWardFlow.value - ruralInfectedIcuFlow.splitValue[0] - ruralInfectedIcuFlow.splitValue[1] - ruralHospitalizedDeathFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
