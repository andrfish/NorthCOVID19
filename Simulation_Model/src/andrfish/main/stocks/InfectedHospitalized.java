package andrfish.main.stocks;

import andrfish.main.flows.HospitalizationFlow;
import andrfish.main.flows.HospitalizedDeathFlow;
import andrfish.main.flows.InfectedIcuFlow;
import andrfish.main.flows.InfectedWardFlow;

public class InfectedHospitalized {
	public double value = 0.0;
	
	private HospitalizationFlow hospitalizationFlow;
	private InfectedWardFlow infectedWardFlow;
	private InfectedIcuFlow infectedIcuFlow;
	private HospitalizedDeathFlow hospitalizedDeathFlow;

	public InfectedHospitalized(HospitalizationFlow hospitalizationFlow,
								InfectedWardFlow infectedWardFlow,
								InfectedIcuFlow infectedIcuFlow,
								HospitalizedDeathFlow hospitalizedDeathFlow) 
	{
		this.hospitalizationFlow = hospitalizationFlow;
		this.infectedWardFlow = infectedWardFlow;
		this.infectedIcuFlow = infectedIcuFlow;
		this.hospitalizedDeathFlow = hospitalizedDeathFlow;
	}

	public double step()
	{
		return step(0.01);
	}
	
	public double step(double dt)
	{
		double result = 0.0;
		
		result = hospitalizationFlow.value - infectedWardFlow.value - infectedIcuFlow.value - hospitalizedDeathFlow.value;
		
		value += result * dt;
		
		return result;
	}
}
