package andrfish.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

import andrfish.main.flows.HospitalizationFlow;
import andrfish.main.flows.HospitalizedDeathFlow;
import andrfish.main.flows.HospitalizedDeathIntermediateFlow;
import andrfish.main.flows.InfectedDeathFlow;
import andrfish.main.flows.InfectedIcuDeathOverflow;
import andrfish.main.flows.InfectedIcuDischargedIntermediateRecoveredFlow;
import andrfish.main.flows.InfectedIcuDischargedRecoveredFlow;
import andrfish.main.flows.InfectedIcuFlow;
import andrfish.main.flows.InfectedIcuIntermediateRecoveryOverflow;
import andrfish.main.flows.InfectedIcuRecoveryOverflow;
import andrfish.main.flows.InfectedIcuWardFlow;
import andrfish.main.flows.InfectedIcuWardIntermediateFlow;
import andrfish.main.flows.InfectedRecoveredFlow;
import andrfish.main.flows.InfectedWardFlow;
import andrfish.main.flows.InfectedWardIcuFlow;
import andrfish.main.flows.InfectedWardIntermediateRecoveredFlow;
import andrfish.main.flows.InfectionFlow;
import andrfish.main.flows.InfectionIntermediateRecoveredFlow;
import andrfish.main.flows.RecoveredFlow;
import andrfish.main.flows.RuralHospitalizationFlow;
import andrfish.main.flows.RuralHospitalizedDeathFlow;
import andrfish.main.flows.RuralHospitalizedDeathIntermediateFlow;
import andrfish.main.flows.RuralInfectedDeathFlow;
import andrfish.main.flows.RuralInfectedIcuDeathOverflow;
import andrfish.main.flows.RuralInfectedIcuDischargedIntermediateRecoveredFlow;
import andrfish.main.flows.RuralInfectedIcuDischargedRecoveredFlow;
import andrfish.main.flows.RuralInfectedIcuFlow;
import andrfish.main.flows.RuralInfectedIcuIntermediateRecoveryOverflow;
import andrfish.main.flows.RuralInfectedIcuRecoveryOverflow;
import andrfish.main.flows.RuralInfectedIcuWardFlow;
import andrfish.main.flows.RuralInfectedIcuWardIntermediateFlow;
import andrfish.main.flows.RuralInfectedRecoveredFlow;
import andrfish.main.flows.RuralInfectedWardFlow;
import andrfish.main.flows.RuralInfectedWardIcuFlow;
import andrfish.main.flows.RuralInfectedWardIntermediateRecoveredFlow;
import andrfish.main.flows.RuralInfectionFlow;
import andrfish.main.flows.RuralInfectionIntermediateRecoveredFlow;
import andrfish.main.flows.RuralRecoveredFlow;
import andrfish.main.stocks.InfectedDeath;
import andrfish.main.stocks.InfectedHospitalized;
import andrfish.main.stocks.InfectedHospitalizedDeathIntermediate;
import andrfish.main.stocks.InfectedIcu;
import andrfish.main.stocks.InfectedIcuDischargedRecoveredIntermediate;
import andrfish.main.stocks.InfectedIcuDischargedWard;
import andrfish.main.stocks.InfectedIcuOverflowIntermediateRecovery;
import andrfish.main.stocks.InfectedIcuRuralUrban;
import andrfish.main.stocks.InfectedIcuRuralWardIntermediate;
import andrfish.main.stocks.InfectedIcuWardIntermediate;
import andrfish.main.stocks.InfectedWard;
import andrfish.main.stocks.InfectedWardRecoveredIntermediate;
import andrfish.main.stocks.Infectious;
import andrfish.main.stocks.InfectiousRecoveredIntermediate;
import andrfish.main.stocks.Recovered;
import andrfish.main.stocks.RuralInfectedDeath;
import andrfish.main.stocks.RuralInfectedHospitalized;
import andrfish.main.stocks.RuralInfectedHospitalizedDeathIntermediate;
import andrfish.main.stocks.RuralInfectedIcu;
import andrfish.main.stocks.RuralInfectedIcuDischargedRecoveredIntermediate;
import andrfish.main.stocks.RuralInfectedIcuDischargedWard;
import andrfish.main.stocks.RuralInfectedIcuOverflowIntermediateRecovery;
import andrfish.main.stocks.RuralInfectedIcuWardIntermediate;
import andrfish.main.stocks.RuralInfectedWard;
import andrfish.main.stocks.RuralInfectedWardRecoveredIntermediate;
import andrfish.main.stocks.RuralInfectious;
import andrfish.main.stocks.RuralInfectiousRecoveredIntermediate;
import andrfish.main.stocks.RuralRecovered;
import andrfish.main.stocks.RuralSusceptible;
import andrfish.main.stocks.RuralTotalInfected;
import andrfish.main.stocks.Susceptible;
import andrfish.main.stocks.TotalInfected;

public class CovidFlow {
	
	/**
	 * ------------------------------------------------------------------
	 * ALL DEBUGGING TOOLS
	 * ------------------------------------------------------------------
	 */
	
	/**
	 * Used to pause the console when a check condition is met
	 */
	private Scanner scan = new Scanner(System.in);
	
	/**
	 * Keeps track of how many checks have been triggered
	 */
	private int stopCount = 0;
	
	/**
	 * Stores the number of checks in place
	 */
	private final int checkCount = 6;
	
	/**
	 * Sets a limit for simulation to run in case the parameters allow for
	 * a seemingly infinite lasting scenario
	 */
	private final int maxDays = 730;
	
	/**
	 * Flag for making the simulation pause when it hits a check
	 */
	private final boolean checkPause = false;
	
	/**
	 * ------------------------------------------------------------------
	 * ALL PARAMETERS FOR URBAN
	 * ------------------------------------------------------------------
	 */
	
	/**
	 * The ratio of the TotalPopulation that can become infected
	 * alpha_1
	 */
	private double MaxInfectedRatio = 0.2;
	
	/**
	 * The number of individuals in the total population
	 * N
	 */
	private double TotalPopulation = 12000000;
	
	/**
	 * The infectivity of the infection
	 * tau
	 */
	private double Infectivity = 0.075;
	
	/**
	 * The contact rate of the infection
	 * overline{c}
	 */
	private double ContactRate = 2.2;
	
	/**
	 * The number of initially infected individuals
	 * N_i
	 */
	private double InitialInfected = 250;
	
	/**
	 * The maximum capacity of the ICU
	 * N_c
	 */
	private double MaxIcuCapacity = 513;
	
	/**
	 * The percent of infected individuals that need to be hospitalized
	 * alpha_2
	 */
	private double HospitalizationRate = 0.19;
	
	/**
	 * The average duration of the illness in days
	 * nu
	 */
	private double AverageIllnessDuration = 20;
	
	/**
	 * The percent of individuals that needed to go the ICU when it was full who will recover
	 * alpha_7
	 */
	private double IcuOverflowRecoveryRate = 0.2;
	
	/**
	 * The number of additional days an overflowed ICU patient will need to recover on top of AverageIllnessDuration
	 * alpha_8
	 */
	private double IcuOverflowRecoveryOffsetDuration = 20;
	
	/**
	 * The percent of hospitalized individuals that need to go to the ICU
	 * alpha_4
	 */
	private double IcuRate = 0.26;
	
	/**
	 * The percent of hospitalized individuals that need to go to the ward
	 * alpha_3
	 */
	private double WardRate = 0.74;
	
	/**
	 * The percent of individuals in the ICU that will move to the death state
	 * alpha_6
	 */
	private double DeathRate = 0.026;
	
	/**
	 * The number of days that an individual in the ICU will need to stay there
	 * alpha_5
	 */
	private double IcuStayRate = 8;
	
	/**
	 * The percent of ward individuals that need to go to the ICU
	 * alpha_9
	 */
	private double WardToIcuRate = 0.1;
	
	/**
	 * The number of days a ward patient will take to recover
	 * alpha_10
	 */
	private double HospitalStayDuration = 10;
	
	/**
	 * The number of days a discharged ICU patient will take to recover in the ward
	 * alpha_11
	 */
	private double DischargeStayDuration = 7;
	
	/**
	 * The percentage of hospitalized individuals that will move to the death state
	 * alpha_12
	 */
	private double HospitalizedDeathRate = 0.20;
	
	/**
	 * The number of days a hospitalized individual moving to the death state will survive
	 * alpha_13
	 */
	private double AverageHospitalizationDeathDuration = 7;
	
	/**
	 * ------------------------------------------------------------------
	 * ALL PARAMETERS FOR RURAL
	 * ------------------------------------------------------------------
	 */
	
	/**
	 * The ratio of the TotalPopulation that can become infected
	 * alpha_1
	 */
	private double RuralMaxInfectedRatio = 0.2;
	
	/**
	 * The number of individuals in the total population
	 * N
	 */
	private double RuralTotalPopulation = 1400000;
	
	/**
	 * The infectivity of the infection
	 * tau
	 */
	private double RuralInfectivity = 0.075;
	
	/**
	 * The contact rate of the infection
	 * overline{c}
	 */
	private double RuralContactRate = 2.2;
	
	/**
	 * The number of initially infected individuals
	 * N_i
	 */
	private double RuralInitialInfected = 26;
	
	/**
	 * The maximum capacity of the ICU
	 * N_c
	 */
	private double RuralMaxIcuCapacity = 53;
	
	/**
	 * The percent of infected individuals that need to be hospitalized
	 * alpha_2
	 */
	private double RuralHospitalizationRate = 0.19;
	
	/**
	 * The average duration of the illness in days
	 * nu
	 */
	private double RuralAverageIllnessDuration = 20;
	
	/**
	 * The percent of individuals that needed to go the ICU when it was full who will recover
	 * alpha_7
	 */
	private double RuralIcuOverflowRecoveryRate = 0.2;
	
	/**
	 * The number of additional days an overflowed ICU patient will need to recover on top of AverageIllnessDuration
	 * alpha_8
	 */
	private double RuralIcuOverflowRecoveryOffsetDuration = 20;
	
	/**
	 * The percent of hospitalized individuals that need to go to the ICU
	 * alpha_4
	 */
	private double RuralIcuRate = 0.26;
	
	/**
	 * The percent of hospitalized individuals that need to go to the ward
	 * alpha_3
	 */
	private double RuralWardRate = 0.74;
	
	/**
	 * The percent of individuals in the ICU that will move to the death state
	 * alpha_6
	 */
	private double RuralDeathRate = 0.026;
	
	/**
	 * The number of days that an individual in the ICU will need to stay there
	 * alpha_5
	 */
	private double RuralIcuStayRate = 8;
	
	/**
	 * The percent of ward individuals that need to go to the ICU
	 * alpha_9
	 */
	private double RuralWardToIcuRate = 0.1;
	
	/**
	 * The number of days a ward patient will take to recover
	 * alpha_10
	 */
	private double RuralHospitalStayDuration = 10;
	
	/**
	 * The number of days a discharged ICU patient will take to recover in the ward
	 * alpha_11
	 */
	private double RuralDischargeStayDuration = 7;
	
	/**
	 * The percentage of hospitalized individuals that will move to the death state
	 * alpha_12
	 */
	private double RuralHospitalizedDeathRate = 0.20;
	
	/**
	 * The number of days a hospitalized individual moving to the death state will survive
	 * alpha_13
	 */
	private double RuralAverageHospitalizationDeathDuration = 7;
	
	/**
	 * ------------------------------------------------------------------
	 * ALL URBAN STOCKS
	 * ------------------------------------------------------------------
	 */
	
	/**
	 * The susceptible population
	 * s
	 */
	private Susceptible susceptible;
	
	/**
	 * The total infected population
	 * N_t
	 */
	private TotalInfected totalInfected;
	
	/**
	 * The infectious population
	 * i
	 */
	private Infectious infectious;
	
	/**
	 * The intermediate state for infectious patients going to the recovered state
	 * i_rw
	 */
	private InfectiousRecoveredIntermediate infectiousRecoveredIntermediate;
	
	/**
	 * The infectious hospitalized population
	 * i_h
	 */
	private InfectedHospitalized infectedHospitalized;
	
	/**
	 * The infectious hospitalized to death intermediate population
	 * i_ihx
	 */
	private InfectedHospitalizedDeathIntermediate infectedHospitalizedDeathIntermediate;
	
	/**
	 * The infected ICU population
	 * i_c
	 */
	private InfectedIcu infectedIcu;
	
	/**
	 * The overflow infected ICU intermediate recovery population
	 * i_r
	 */
	private InfectedIcuOverflowIntermediateRecovery infectedIcuOverflowIntermediateRecovery;
	
	/**
	 * The intermediate state for ICU patients recovering to the ward
	 * i_ir
	 */
	private InfectedIcuWardIntermediate infectedIcuWardIntermediate;
	
	/**
	 * The ward state for discharged ICU patients
	 * i_wi
	 */
	private InfectedIcuDischargedWard infectedIcuDischargedWard;
	
	/**
	 * The intermediate recovery state for discharged ICU patients in the ward
	 * i_iwi
	 */
	private InfectedIcuDischargedRecoveredIntermediate infectedIcuDischargedRecoveredIntermediate;
	
	/**
	 * The infected ward population
	 * i_w
	 */
	private InfectedWard infectedWard;
	
	/**
	 * The intermediate state for ward patients going to the recovered state
	 * i_wr
	 */
	private InfectedWardRecoveredIntermediate infectedWardRecoveredIntermediate;
	
	/**
	 * The infected death population
	 * x
	 */
	private InfectedDeath infectedDeath;
	
	/**
	 * The infected recovered population
	 * r
	 */
	private Recovered recovered;
	
	/**
	 * ------------------------------------------------------------------
	 * ALL RURAL STOCKS
	 * ------------------------------------------------------------------
	 */
	
	/**
	 * The susceptible population
	 * s
	 */
	private RuralSusceptible ruralSusceptible;
	
	/**
	 * The total infected population
	 * N_t
	 */
	private RuralTotalInfected ruralTotalInfected;
	
	/**
	 * The infectious population
	 * i
	 */
	private RuralInfectious ruralInfectious;
	
	/**
	 * The intermediate state for infectious patients going to the recovered state
	 * i_ir
	 */
	private RuralInfectiousRecoveredIntermediate ruralInfectiousRecoveredIntermediate;
	
	/**
	 * The infectious hospitalized population
	 * i_h
	 */
	private RuralInfectedHospitalized ruralInfectedHospitalized;
	
	/**
	 * The infectious hospitalized to death intermediate population
	 * i_ihx
	 */
	private RuralInfectedHospitalizedDeathIntermediate ruralInfectedHospitalizedDeathIntermediate;
	
	/**
	 * The infected ICU population
	 * i_c
	 */
	private RuralInfectedIcu ruralInfectedIcu;
	
	/**
	 * The rural infected ICU population in the urban population
	 * i_rc
	 */
	private InfectedIcuRuralUrban infectedIcuRuralUrban;
	
	/**
	 * The intermediate state for rural ICU patients in the urban population recovering to the rural ward
	 * i_rcw
	 */
	private InfectedIcuRuralWardIntermediate infectedIcuRuralWardIntermediate;
	
	/**
	 * The overflow infected ICU intermediate recovery population
	 * i_r
	 */
	private RuralInfectedIcuOverflowIntermediateRecovery ruralInfectedIcuOverflowIntermediateRecovery;
	
	/**
	 * The intermediate state for ICU patients recovering to the ward
	 * i_cw
	 */
	private RuralInfectedIcuWardIntermediate ruralInfectedIcuWardIntermediate;
	
	/**
	 * The ward state for discharged ICU patients
	 * i_wi
	 */
	private RuralInfectedIcuDischargedWard ruralInfectedIcuDischargedWard;
	
	/**
	 * The intermediate recovery state for discharged ICU patients in the ward
	 * i_iwi
	 */
	private RuralInfectedIcuDischargedRecoveredIntermediate ruralInfectedIcuDischargedRecoveredIntermediate;
	
	/**
	 * The infected ward population
	 * i_w
	 */
	private RuralInfectedWard ruralInfectedWard;
	
	/**
	 * The intermediate state for ward patients going to the recovered state
	 * i_wr
	 */
	private RuralInfectedWardRecoveredIntermediate ruralInfectedWardRecoveredIntermediate;
	
	
	/**
	 * The infected death population
	 * x
	 */
	private RuralInfectedDeath ruralInfectedDeath;
	
	/**
	 * The infected recovered population
	 * r
	 */
	private RuralRecovered ruralRecovered;
	
	/**
	 * ------------------------------------------------------------------
	 */

	/**
	 * Used to give the output files a unique ID
	 */
	private int UniqueId = -1;
	
	/**
	 * Changes the dt and number of outputs for the API's output
	 */
	private boolean ApiFlag = false;
	
	/**
	 * Cause the maximum ICU capacity to be ignored to see what the maximum load would be 
	 */
	private boolean IcuRealityFlag = false;
	
	/**
	 * ------------------------------------------------------------------
	 */
	
	/**
	 * Initializes a new CovidFlow object
	 */
	public CovidFlow() {}
	
	/**
	 * ------------------------------------------------------------------
	 */
	
	/**
	 * The master method for running a simulation given the arguments
	 * @param args
	 */
	public void main(String[] args) {
		//Parse the arguments
		if (args.length > 0)
		{
			if (args[0].toUpperCase().equals("-H"))
			{
				System.out.println("List of arguments for the CovidFlow simulation\n");
				System.out.println("-----------------------------------------------------------------------------------------------------");
				System.out.println("NOTE: To set the rural parameters, simply preceed the desired argument with 'r' (i.e., -a1 would be -ra1). This exludes '-a10' and '-i'.");
				System.out.println("-----------------------------------------------------------------------------------------------------");
				System.out.println("Variable                            | Arg | Description");
				System.out.println("-----------------------------------------------------------------------------------------------------");
				System.out.println("MaxInfectedRatio                    | -a1 | The ratio of the TotalPopulation that can become infected\n");
				System.out.println("TotalPopulation                     | -n  | The number of individuals in the total population\n");
				System.out.println("Infectivity                         | -t  | The infectivity of the infection\n");
				System.out.println("ContactRate                         | -c  | The contact rate of the infection\n");
				System.out.println("InitialInfected                     | -ni | The number of initially infected individuals\n");
				System.out.println("MaxIcuCapacity                      | -nc | The maximum capacity of the ICU\n");
				System.out.println("HospitalizationRate                 | -a2 | The percent of infected individuals that need to be hospitalized\n");
				System.out.println("AverageIllnessDuration              | -nu | The average duration of the illness in days\n");
				System.out.println("IcuOverflowRecoveryRate             | -a7 | The percent of individuals that needed to go the ICU when it was full who will recover\n");
				System.out.println("IcuOverflowRecoveryOffsetDuration   | -a8 | The number of additional days an overflowed ICU patient will need to recover on top of AverageIllnessDuration\n");
				System.out.println("IcuRate                             | -a4 | The percent of hospitalized individuals that need to go to the ICU\n");
				System.out.println("WardRate                            | -a3 | The percent of hospitalized individuals that need to go to the ward\n");
				System.out.println("DeathRate                           | -a6 | The percent of individuals in the ICU that will move to the death state\n");
				System.out.println("IcuStayRate                         | -a5 | The number of days that an individual in the ICU will need to stay there\n");
				System.out.println("WardToIcuRate                       | -a9 | The percent of ward individuals that need to go to the ICU\n");
				System.out.println("HospitalStayDuration                | -a10| The number of days a ward patient will take to recover\n");
				System.out.println("DischargeStayDuration               | -a11| The number of days a discharged ICU patient will take to recover in the ward\n");
				System.out.println("HospitalizedDeathRate               | -a12| The percentage of hospitalized individuals that will move to the death state\n");
				System.out.println("AverageHospitalizationDeathDuration | -a13| The number of days a hospitalized individual moving to the death state will survive\n");
				System.out.println("ID                                  | -i  | Uses the passed ID in the filenames when outputting to avoid overwriting previous results\n");
				return;
			}
			else
			{
				for(int i = 0; i < args.length; i += 2)
				{
					String argument = args[i].toUpperCase();
					double value = -1;
					
					try
					{
						value = Double.parseDouble(args[i + 1]);
					}
					catch (Exception e)
					{
						System.out.println("Value \"" + args[i + 1] + "\" for argument \"" + argument + "\" is not a valid double");
						return;
					}
		
					if (argument.equals("-A1"))
					{
						MaxInfectedRatio = value;
					}
					else if (argument.equals("-N"))
					{
						TotalPopulation = value;
					}
					else if (argument.equals("-T"))
					{
						Infectivity = value;
					}
					else if (argument.equals("-C"))
					{
						ContactRate = value;
					}
					else if (argument.equals("-NI"))
					{
						InitialInfected = value;
					}
					else if (argument.equals("-NC"))
					{
						MaxIcuCapacity = value;
					}
					else if (argument.equals("-A2"))
					{
						HospitalizationRate = value;
					}
					else if (argument.equals("-NU"))
					{
						AverageIllnessDuration = value;
					}
					else if (argument.equals("-A7"))
					{
						IcuOverflowRecoveryRate = value;
					}
					else if (argument.equals("-A8"))
					{
						IcuOverflowRecoveryOffsetDuration = value;
					}
					else if (argument.equals("-A4"))
					{
						IcuRate = value;
					}
					else if (argument.equals("-A3"))
					{
						WardRate = value;
					}
					else if (argument.equals("-A6"))
					{
						DeathRate = value;
					}
					else if (argument.equals("-A5"))
					{
						IcuStayRate = value;
					}
					else if (argument.equals("-A9"))
					{
						WardToIcuRate = value;
					}
					else if (argument.equals("-A10"))
					{
						HospitalStayDuration = value;
					}
					else if (argument.equals("-A11"))
					{
						DischargeStayDuration = value;
					}
					else if (argument.equals("-A12"))
					{
						HospitalizedDeathRate = value;
					}
					else if (argument.equals("-A13"))
					{
						AverageHospitalizationDeathDuration = value;
					}
					else if (argument.equals("-RA1"))
					{
						RuralMaxInfectedRatio = value;
					}
					else if (argument.equals("-RN"))
					{
						RuralTotalPopulation = value;
					}
					else if (argument.equals("-RT"))
					{
						RuralInfectivity = value;
					}
					else if (argument.equals("-RC"))
					{
						RuralContactRate = value;
					}
					else if (argument.equals("-RNI"))
					{
						RuralInitialInfected = value;
					}
					else if (argument.equals("-RNC"))
					{
						RuralMaxIcuCapacity = value;
					}
					else if (argument.equals("-RA2"))
					{
						RuralHospitalizationRate = value;
					}
					else if (argument.equals("-RNU"))
					{
						RuralAverageIllnessDuration = value;
					}
					else if (argument.equals("-RA7"))
					{
						RuralIcuOverflowRecoveryRate = value;
					}
					else if (argument.equals("-RA8"))
					{
						RuralIcuOverflowRecoveryOffsetDuration = value;
					}
					else if (argument.equals("-RA4"))
					{
						RuralIcuRate = value;
					}
					else if (argument.equals("-RA3"))
					{
						RuralWardRate = value;
					}
					else if (argument.equals("-RA6"))
					{
						RuralDeathRate = value;
					}
					else if (argument.equals("-RA5"))
					{
						RuralIcuStayRate = value;
					}
					else if (argument.equals("-RA9"))
					{
						RuralWardToIcuRate = value;
					}
					else if (argument.equals("-RA10"))
					{
						RuralHospitalStayDuration = value;
					}
					else if (argument.equals("-RA11"))
					{
						RuralDischargeStayDuration = value;
					}
					else if (argument.equals("-RA12"))
					{
						RuralHospitalizedDeathRate = value;
					}
					else if (argument.equals("-RA13"))
					{
						RuralAverageHospitalizationDeathDuration = value;
					}
					else if (argument.equals("-I"))
					{
						UniqueId = (int)value;
					}
					else if (argument.equals("-O"))
					{
						ApiFlag = true;
					}
					else if (argument.equals("-IRF"))
					{
						IcuRealityFlag = true;
					}
					else
					{
						System.out.println("Unknown argument \"" + argument + "\" passed. Please see the valid arguments with -h");
						return;
					}
				}
			}
		}
		
		//Update the total population based on A1
		TotalPopulation = TotalPopulation * MaxInfectedRatio;
		RuralTotalPopulation = RuralTotalPopulation * RuralMaxInfectedRatio;
		
		MaxInfectedRatio = 1.00;
		RuralMaxInfectedRatio = 1.00;
		
		//Initialize the simulation flows
		InfectionFlow infectionFlow = new InfectionFlow();
		RuralInfectionFlow ruralInfectionFlow = new RuralInfectionFlow();
		
		HospitalizationFlow hospitalizationFlow = new HospitalizationFlow();
		RuralHospitalizationFlow ruralHospitalizationFlow = new RuralHospitalizationFlow();
		
		RecoveredFlow recoveredFlow = new RecoveredFlow();
		RuralRecoveredFlow ruralRecoveredFlow = new RuralRecoveredFlow();
		
		HospitalizedDeathFlow hospitalizedDeathFlow = new HospitalizedDeathFlow();
		HospitalizedDeathIntermediateFlow hospitalizedDeathIntermediateFlow = new HospitalizedDeathIntermediateFlow();
		
		InfectedIcuFlow infectedIcuFlow = new InfectedIcuFlow();
		
		RuralHospitalizedDeathFlow ruralHospitalizedDeathFlow = new RuralHospitalizedDeathFlow();
		RuralHospitalizedDeathIntermediateFlow ruralHospitalizedDeathIntermediateFlow = new RuralHospitalizedDeathIntermediateFlow();
		
		RuralInfectedIcuFlow ruralInfectedIcuFlow = new RuralInfectedIcuFlow();
		
		InfectedWardFlow infectedWardFlow = new InfectedWardFlow();
		RuralInfectedWardFlow ruralInfectedWardFlow = new RuralInfectedWardFlow();
		
		InfectedWardIcuFlow infectedWardIcuFlow = new InfectedWardIcuFlow();
		RuralInfectedWardIcuFlow ruralInfectedWardIcuFlow = new RuralInfectedWardIcuFlow();
		
		RuralInfectedDeathFlow ruralInfectedDeathFlow = new RuralInfectedDeathFlow();
		
		RuralInfectedIcuWardFlow ruralInfectedIcuWardFlow = new RuralInfectedIcuWardFlow();
		RuralInfectedIcuWardIntermediateFlow ruralInfectedIcuWardIntermediateFlow = new RuralInfectedIcuWardIntermediateFlow();
		
		InfectedDeathFlow infectedDeathFlow = new InfectedDeathFlow();

		InfectedIcuWardFlow infectedIcuWardFlow = new InfectedIcuWardFlow();
		InfectedIcuWardIntermediateFlow infectedIcuWardIntermediateFlow = new InfectedIcuWardIntermediateFlow();
		
		InfectedIcuDeathOverflow infectedIcuDeathOverflow = new InfectedIcuDeathOverflow();
		RuralInfectedIcuDeathOverflow ruralInfectedIcuDeathOverflow = new RuralInfectedIcuDeathOverflow();
		
		InfectedIcuRecoveryOverflow infectedIcuRecoveryOverflow = new InfectedIcuRecoveryOverflow();
		RuralInfectedIcuRecoveryOverflow ruralInfectedIcuRecoveryOverflow = new RuralInfectedIcuRecoveryOverflow();
		
		InfectedIcuIntermediateRecoveryOverflow infectedIcuIntermediateRecoveryOverflow = new InfectedIcuIntermediateRecoveryOverflow();
		RuralInfectedIcuIntermediateRecoveryOverflow ruralInfectedIcuIntermediateRecoveryOverflow = new RuralInfectedIcuIntermediateRecoveryOverflow();
		
		InfectedRecoveredFlow infectedRecoveredFlow = new InfectedRecoveredFlow();
		RuralInfectedRecoveredFlow ruralInfectedRecoveredFlow = new RuralInfectedRecoveredFlow();
		
		InfectedIcuDischargedRecoveredFlow infectedIcuDischargedRecoveredFlow = new InfectedIcuDischargedRecoveredFlow();
		RuralInfectedIcuDischargedRecoveredFlow ruralInfectedIcuDischargedRecoveredFlow = new RuralInfectedIcuDischargedRecoveredFlow();
		
		InfectedWardIntermediateRecoveredFlow infectedWardIntermediateRecoveredFlow = new InfectedWardIntermediateRecoveredFlow();
		RuralInfectedWardIntermediateRecoveredFlow ruralInfectedWardIntermediateRecoveredFlow = new RuralInfectedWardIntermediateRecoveredFlow();
		
		InfectedIcuDischargedIntermediateRecoveredFlow infectedIcuDischargedIntermediateRecoveredFlow = new InfectedIcuDischargedIntermediateRecoveredFlow();
		RuralInfectedIcuDischargedIntermediateRecoveredFlow ruralInfectedIcuDischargedIntermediateRecoveredFlow = new RuralInfectedIcuDischargedIntermediateRecoveredFlow();
		
		InfectionIntermediateRecoveredFlow infectionIntermediateRecoveredFlow = new InfectionIntermediateRecoveredFlow();
		RuralInfectionIntermediateRecoveredFlow ruralInfectionIntermediateRecoveredFlow = new RuralInfectionIntermediateRecoveredFlow();
		
		//Initialize the simulation stocks
		susceptible = new Susceptible(TotalPopulation - InitialInfected, infectionFlow);
		totalInfected = new TotalInfected(InitialInfected, infectionFlow);
		infectious = new Infectious(InitialInfected, infectionFlow, hospitalizationFlow, recoveredFlow);
		infectiousRecoveredIntermediate = new InfectiousRecoveredIntermediate(recoveredFlow, infectionIntermediateRecoveredFlow);
		infectedHospitalized = new InfectedHospitalized(hospitalizationFlow, infectedWardFlow, infectedIcuFlow, hospitalizedDeathFlow);
		infectedHospitalizedDeathIntermediate = new InfectedHospitalizedDeathIntermediate(hospitalizedDeathFlow, hospitalizedDeathIntermediateFlow);
		infectedIcu = new InfectedIcu(infectedIcuFlow, infectedWardIcuFlow, infectedIcuWardFlow, infectedDeathFlow, infectedIcuRecoveryOverflow, infectedIcuDeathOverflow);
		infectedIcuRuralUrban = new InfectedIcuRuralUrban(ruralInfectedIcuFlow, ruralInfectedWardIcuFlow, ruralInfectedIcuWardFlow, ruralInfectedDeathFlow);
		infectedIcuRuralWardIntermediate = new InfectedIcuRuralWardIntermediate(ruralInfectedIcuWardFlow, ruralInfectedIcuWardIntermediateFlow);
		infectedIcuOverflowIntermediateRecovery = new InfectedIcuOverflowIntermediateRecovery(infectedIcuRecoveryOverflow, infectedIcuIntermediateRecoveryOverflow);
		infectedIcuWardIntermediate = new InfectedIcuWardIntermediate(infectedIcuWardFlow, infectedIcuWardIntermediateFlow);
		infectedIcuDischargedWard = new InfectedIcuDischargedWard(infectedIcuWardIntermediateFlow, infectedIcuDischargedRecoveredFlow);
		infectedIcuDischargedRecoveredIntermediate = new InfectedIcuDischargedRecoveredIntermediate(infectedIcuDischargedRecoveredFlow, infectedIcuDischargedIntermediateRecoveredFlow);
		infectedWard = new InfectedWard(infectedIcuIntermediateRecoveryOverflow, infectedWardFlow, infectedRecoveredFlow, infectedWardIcuFlow);
		infectedWardRecoveredIntermediate = new InfectedWardRecoveredIntermediate(infectedRecoveredFlow, infectedWardIntermediateRecoveredFlow);
		infectedDeath = new InfectedDeath(infectedDeathFlow, infectedIcuDeathOverflow, hospitalizedDeathIntermediateFlow);
		recovered = new Recovered(infectionIntermediateRecoveredFlow, infectedWardIntermediateRecoveredFlow, infectedIcuDischargedIntermediateRecoveredFlow);
		
		ruralSusceptible = new RuralSusceptible(RuralTotalPopulation - RuralInitialInfected, ruralInfectionFlow);
		ruralTotalInfected = new RuralTotalInfected(RuralInitialInfected, ruralInfectionFlow);
		ruralInfectious = new RuralInfectious(RuralInitialInfected, ruralInfectionFlow, ruralHospitalizationFlow, ruralRecoveredFlow);
		ruralInfectiousRecoveredIntermediate = new RuralInfectiousRecoveredIntermediate(ruralRecoveredFlow, ruralInfectionIntermediateRecoveredFlow);
		ruralInfectedHospitalized = new RuralInfectedHospitalized(ruralHospitalizationFlow, ruralInfectedWardFlow, ruralInfectedIcuFlow, ruralHospitalizedDeathFlow);
		ruralInfectedHospitalizedDeathIntermediate = new RuralInfectedHospitalizedDeathIntermediate(ruralHospitalizedDeathFlow, ruralHospitalizedDeathIntermediateFlow);
		ruralInfectedIcu = new RuralInfectedIcu(ruralInfectedIcuFlow, ruralInfectedWardIcuFlow, ruralInfectedIcuWardFlow, ruralInfectedDeathFlow, ruralInfectedIcuRecoveryOverflow, ruralInfectedIcuDeathOverflow);
		ruralInfectedIcuOverflowIntermediateRecovery = new RuralInfectedIcuOverflowIntermediateRecovery(ruralInfectedIcuRecoveryOverflow, ruralInfectedIcuIntermediateRecoveryOverflow);
		ruralInfectedIcuWardIntermediate = new RuralInfectedIcuWardIntermediate(ruralInfectedIcuWardFlow, ruralInfectedIcuWardIntermediateFlow);
		ruralInfectedIcuDischargedWard = new RuralInfectedIcuDischargedWard(ruralInfectedIcuWardIntermediateFlow, ruralInfectedIcuDischargedRecoveredFlow);
		ruralInfectedIcuDischargedRecoveredIntermediate = new RuralInfectedIcuDischargedRecoveredIntermediate(ruralInfectedIcuDischargedRecoveredFlow,  ruralInfectedIcuDischargedIntermediateRecoveredFlow);
		ruralInfectedWard = new RuralInfectedWard(ruralInfectedIcuIntermediateRecoveryOverflow, ruralInfectedWardFlow, ruralInfectedRecoveredFlow, ruralInfectedWardIcuFlow);
		ruralInfectedWardRecoveredIntermediate = new RuralInfectedWardRecoveredIntermediate(ruralInfectedRecoveredFlow, ruralInfectedWardIntermediateRecoveredFlow);
		ruralInfectedDeath = new RuralInfectedDeath(ruralInfectedDeathFlow, ruralInfectedIcuDeathOverflow, ruralHospitalizedDeathIntermediateFlow);
		ruralRecovered = new RuralRecovered(ruralInfectionIntermediateRecoveredFlow, ruralInfectedWardIntermediateRecoveredFlow, ruralInfectedIcuDischargedIntermediateRecoveredFlow);
		
		//Prompt the user to start the simulation
		if(checkPause)
		{
			System.out.print("Press any key to start the simulation . . . ");
			scan.nextLine();
		}
		else
		{
			System.out.println("Starting the simulation" + (IcuRealityFlag ? " with IRF enabled" : "") +  " . . .");
		}
		
		//Create StringBuilder objects for the output CSV file
		StringBuilder dInfectedOutput = new StringBuilder();
		StringBuilder dRecoveredOutput = new StringBuilder();
		StringBuilder dSusceptibleOutput = new StringBuilder();
		
		StringBuilder infectedOutput = new StringBuilder();
		StringBuilder deadOutput = new StringBuilder();
		StringBuilder recoveredOutput = new StringBuilder();
		StringBuilder icuOutput = new StringBuilder();
		
		StringBuilder statesOutput = new StringBuilder();
		
		//Initialize the CSV files
		dInfectedOutput.append("Day,dInfected");
		dInfectedOutput.append(System.getProperty("line.separator"));
		dInfectedOutput.append("0," + InitialInfected);
		dInfectedOutput.append(System.getProperty("line.separator"));
		
		dRecoveredOutput.append("Day,dRecovered");
		dRecoveredOutput.append(System.getProperty("line.separator"));
		dRecoveredOutput.append("0,0");
		dRecoveredOutput.append(System.getProperty("line.separator"));
		
		dSusceptibleOutput.append("Day,dSusceptible");
		dSusceptibleOutput.append(System.getProperty("line.separator"));
		dSusceptibleOutput.append("0," + (TotalPopulation - InitialInfected));
		dSusceptibleOutput.append(System.getProperty("line.separator"));
		
		infectedOutput.append("Day,Infected");
		infectedOutput.append(System.getProperty("line.separator"));
		infectedOutput.append("0," + InitialInfected);
		infectedOutput.append(System.getProperty("line.separator"));
		
		deadOutput.append("Day,Dead");
		deadOutput.append(System.getProperty("line.separator"));
		deadOutput.append("0,0");
		deadOutput.append(System.getProperty("line.separator"));
		
		recoveredOutput.append("Day,Recovered");
		recoveredOutput.append(System.getProperty("line.separator"));
		recoveredOutput.append("0,0");
		recoveredOutput.append(System.getProperty("line.separator"));
		
		icuOutput.append("Day,ICU");
		icuOutput.append(System.getProperty("line.separator"));
		icuOutput.append("0,0");
		icuOutput.append(System.getProperty("line.separator"));
		
		statesOutput.append("Day,Susceptible,Infectious,Hospitalized,ICU,Ward,Recovered,Death");
		statesOutput.append(System.getProperty("line.separator"));
		statesOutput.append("0," + (TotalPopulation - InitialInfected) + "," + InitialInfected + ",0,0,0,0,0");
		statesOutput.append(System.getProperty("line.separator"));
		
		//Create StringBuilder objects for the output CSV file
		StringBuilder dRuralInfectedOutput = new StringBuilder();
		StringBuilder dRuralRecoveredOutput = new StringBuilder();
		StringBuilder dRuralSusceptibleOutput = new StringBuilder();
		
		StringBuilder ruralInfectedOutput = new StringBuilder();
		StringBuilder ruralDeadOutput = new StringBuilder();
		StringBuilder ruralRecoveredOutput = new StringBuilder();
		StringBuilder ruralIcuOutput = new StringBuilder();
		
		StringBuilder ruralStatesOutput = new StringBuilder();
		
		//Initialize the rural CSV files
		dRuralInfectedOutput.append("Day,dInfected");
		dRuralInfectedOutput.append(System.getProperty("line.separator"));
		dRuralInfectedOutput.append("0," + RuralInitialInfected);
		dRuralInfectedOutput.append(System.getProperty("line.separator"));
		
		dRuralRecoveredOutput.append("Day,dRecovered");
		dRuralRecoveredOutput.append(System.getProperty("line.separator"));
		dRuralRecoveredOutput.append("0,0");
		dRuralRecoveredOutput.append(System.getProperty("line.separator"));
		
		dRuralSusceptibleOutput.append("Day,dSusceptible");
		dRuralSusceptibleOutput.append(System.getProperty("line.separator"));
		dRuralSusceptibleOutput.append("0," + (RuralTotalPopulation - RuralInitialInfected));
		dRuralSusceptibleOutput.append(System.getProperty("line.separator"));
		
		ruralInfectedOutput.append("Day,Infected");
		ruralInfectedOutput.append(System.getProperty("line.separator"));
		ruralInfectedOutput.append("0," + RuralInitialInfected);
		ruralInfectedOutput.append(System.getProperty("line.separator"));
		
		ruralDeadOutput.append("Day,Dead");
		ruralDeadOutput.append(System.getProperty("line.separator"));
		ruralDeadOutput.append("0,0");
		ruralDeadOutput.append(System.getProperty("line.separator"));
		
		ruralRecoveredOutput.append("Day,Recovered");
		ruralRecoveredOutput.append(System.getProperty("line.separator"));
		ruralRecoveredOutput.append("0,0");
		ruralRecoveredOutput.append(System.getProperty("line.separator"));
		
		ruralIcuOutput.append("Day,ICU");
		ruralIcuOutput.append(System.getProperty("line.separator"));
		ruralIcuOutput.append("0,0");
		ruralIcuOutput.append(System.getProperty("line.separator"));
		
		ruralStatesOutput.append("Day,Susceptible,Infectious,Hospitalized,ICU,Ward,Recovered,Death");
		ruralStatesOutput.append(System.getProperty("line.separator"));
		ruralStatesOutput.append("0," + (RuralTotalPopulation - RuralInitialInfected) + "," + RuralInitialInfected + ",0,0,0,0,0");
		ruralStatesOutput.append(System.getProperty("line.separator"));

		//Run the simulation until all checks have been triggered
		boolean icuFull = false;
		boolean maxInfected = false;
		boolean infectionEnd = false;
		
		boolean ruralIcuFull = false;
		boolean ruralMaxInfected = false;
		boolean ruralInfectionEnd = false;
		
		double currentDay = 0;
		double dt = ApiFlag ? 0.1 : 0.01;
		
		double icuFullDays = -1;
		double maxInfectedDays = -1;
		double infectionEndDays = -1;
		
		double ruralIcuFullDays = -1;
		double ruralMaxInfectedDays = -1;
		double ruralInfectionEndDays = -1;
		
		if(IcuRealityFlag)
		{
			MaxIcuCapacity = Double.MAX_VALUE;
			RuralMaxIcuCapacity = Double.MAX_VALUE;
		}
		
		DecimalFormat df = new DecimalFormat("0.00");
		while(stopCount < checkCount && currentDay < maxDays)
		{		
			//Update the flows
			infectionFlow.calculate(totalInfected.value, TotalPopulation, MaxInfectedRatio, 
									infectious.value, infectiousRecoveredIntermediate.value, ContactRate, susceptible.value, Infectivity);		
			
			hospitalizationFlow.calculate(infectionFlow.value, HospitalizationRate, infectious.value);
			
			recoveredFlow.calculate(infectious.value, hospitalizationFlow.value);
			
			hospitalizedDeathFlow.calculate(infectedHospitalized.value, HospitalizedDeathRate);
			
			infectedIcuFlow.calculate(infectedHospitalized.value, IcuRate);
			
			infectedWardFlow.calculate(infectedHospitalized.value, WardRate);
			
			infectedWardIcuFlow.calculate(infectedWard.value, WardToIcuRate);
			
			
			
			ruralInfectionFlow.calculate(ruralTotalInfected.value, RuralTotalPopulation, RuralMaxInfectedRatio, 
					ruralInfectious.value, ruralInfectiousRecoveredIntermediate.value, RuralContactRate, ruralSusceptible.value, RuralInfectivity);
			
			ruralHospitalizationFlow.calculate(ruralInfectionFlow.value, RuralHospitalizationRate, ruralInfectious.value);
			
			ruralRecoveredFlow.calculate(ruralInfectious.value, ruralHospitalizationFlow.value);
			
			ruralHospitalizedDeathFlow.calculate(ruralInfectedHospitalized.value, RuralHospitalizedDeathRate);
			
			ruralInfectedIcuFlow.calculate(ruralInfectedHospitalized.value, RuralIcuRate,
										   infectedIcu.value, infectedIcuRuralUrban.value, infectedIcuFlow.value,
										   infectedWardIcuFlow.value, infectedDeathFlow.value, infectedIcuWardFlow.value, 
										   MaxIcuCapacity, infectedIcuWardIntermediate.value, infectedIcuRuralWardIntermediate.value,
										   ruralInfectedWardIcuFlow.splitValue[1]);
			
			ruralInfectedWardFlow.calculate(ruralInfectedHospitalized.value, RuralWardRate);
			
			ruralInfectedWardIcuFlow.calculate(ruralInfectedWard.value, RuralWardToIcuRate,
											   infectedIcu.value, infectedIcuRuralUrban.value, infectedIcuFlow.value,
											   infectedWardIcuFlow.value, infectedDeathFlow.value, infectedIcuWardFlow.value, 
											   MaxIcuCapacity, infectedIcuWardIntermediate.value, infectedIcuRuralWardIntermediate.value,
											   ruralInfectedDeathFlow.splitValue[1], ruralInfectedIcuWardFlow.splitValue[1]);

			ruralInfectedDeathFlow.calculate(ruralInfectedIcu.value, ruralInfectedIcuWardIntermediate.value, RuralMaxIcuCapacity, 
											 ruralInfectedIcuFlow.splitValue, RuralDeathRate, ruralInfectedWardIcuFlow.splitValue[0],
											 infectedIcuRuralUrban.value);
			
			ruralInfectedIcuWardFlow.calculate(ruralInfectedIcu.value,
					   infectedIcuRuralUrban.value,
					   ruralInfectedDeathFlow.splitValue,
					   ruralInfectedIcuFlow.splitValue, RuralDeathRate);

			infectedDeathFlow.calculate(infectedIcu.value, infectedIcuWardIntermediate.value, MaxIcuCapacity, 
										infectedIcuFlow.value, DeathRate, infectedWardIcuFlow.value, 
										infectedIcuRuralUrban.value, infectedIcuRuralWardIntermediate.value,
										ruralInfectedWardIcuFlow.splitValue[1]);
			
			infectedIcuWardFlow.calculate(infectedIcu.value, infectedIcuWardIntermediate.value, MaxIcuCapacity, infectedDeathFlow.value, infectedIcuFlow.value, DeathRate);
			
			
			infectedIcuWardIntermediateFlow.calculate(infectedIcuWardIntermediate.value, IcuStayRate);
			
			infectedIcuDischargedRecoveredFlow.calculate(infectedIcuDischargedWard.value);
		
			infectedIcuDeathOverflow.calculate(infectedIcu.value, infectedIcuWardIntermediate.value, MaxIcuCapacity, 
											   infectedIcuFlow.value, infectedWardIcuFlow.value, infectedDeathFlow.value,
											   infectedIcuWardFlow.value, IcuOverflowRecoveryRate, 
											   infectedIcuRuralUrban.value, infectedIcuRuralWardIntermediate.value,
											   ruralInfectedDeathFlow.splitValue[1], ruralInfectedIcuWardFlow.splitValue[1],
											   ruralInfectedWardIcuFlow.splitValue[1]);
			
			infectedIcuRecoveryOverflow.calculate(infectedIcu.value, infectedIcuWardIntermediate.value, MaxIcuCapacity,
												  infectedIcuFlow.value, infectedWardIcuFlow.value, infectedDeathFlow.value, 
												  infectedIcuWardFlow.value,
												  infectedIcuDeathOverflow.value, infectedIcuRuralUrban.value,
												  infectedIcuRuralWardIntermediate.value,
												  ruralInfectedDeathFlow.splitValue[1], ruralInfectedIcuWardFlow.splitValue[1],
												  ruralInfectedWardIcuFlow.splitValue[1]);	
			
			//Update the ICU to ward flow
			infectedIcuWardFlow.value -= (infectedIcuDeathOverflow.value + infectedIcuRecoveryOverflow.value);
			if(infectedIcuWardFlow.value < 0) infectedIcuWardFlow.value = 0;
			
			//If overflow > 0 ensure that difference between in and out of itermediate is <=0
			if((infectedIcuDeathOverflow.value + infectedIcuRecoveryOverflow.value) > 0)
			{
				if(infectedIcuWardFlow.value - infectedIcuWardIntermediateFlow.value > 0 &&
				   infectedIcuWardFlow.value + infectedIcu.value + infectedIcuWardIntermediate.value - infectedIcuWardIntermediateFlow.value
				   > MaxIcuCapacity)
				{
					//Add the difference proportionally
					//IcuOverflowRecoveryRate
					double diff = infectedIcuWardFlow.value + infectedIcu.value + infectedIcuWardIntermediate.value - infectedIcuWardIntermediateFlow.value - MaxIcuCapacity;
					
					infectedIcuDeathOverflow.value += diff * (1 - IcuOverflowRecoveryRate) + 0.5;
					infectedIcuRecoveryOverflow.value += diff * IcuOverflowRecoveryRate + 0.5;
					
					infectedIcuWardFlow.value -= diff;
				}
			}
			
			
			
			infectedIcuIntermediateRecoveryOverflow.calculate(infectedIcuOverflowIntermediateRecovery.value, infectedIcuRecoveryOverflow.value, IcuOverflowRecoveryOffsetDuration);
			
			
			ruralInfectedIcuWardIntermediateFlow.calculate(ruralInfectedIcuWardIntermediate.value, infectedIcuRuralWardIntermediate.value, RuralIcuStayRate);
			
			ruralInfectedIcuDischargedRecoveredFlow.calculate(ruralInfectedIcuDischargedWard.value);
			
			ruralInfectedIcuDeathOverflow.calculate(ruralInfectedIcu.value, ruralInfectedIcuWardIntermediate.value, RuralMaxIcuCapacity, 
				    ruralInfectedIcuFlow.splitValue[0], ruralInfectedWardIcuFlow.splitValue[0], ruralInfectedDeathFlow.splitValue[0],
				    ruralInfectedIcuWardFlow.splitValue[0], RuralIcuOverflowRecoveryRate);
			ruralInfectedIcuRecoveryOverflow.calculate(ruralInfectedIcu.value, ruralInfectedIcuWardIntermediate.value, RuralMaxIcuCapacity,
								    ruralInfectedIcuFlow.splitValue[0], ruralInfectedWardIcuFlow.splitValue[0], ruralInfectedDeathFlow.splitValue[0], 
								    ruralInfectedIcuWardFlow.splitValue[0],
								    ruralInfectedIcuDeathOverflow.value);
			
			//Update the ICU to ward flow
			ruralInfectedIcuWardFlow.splitValue[0] -= (ruralInfectedIcuDeathOverflow.value + ruralInfectedIcuRecoveryOverflow.value);
			if(ruralInfectedIcuWardFlow.splitValue[0] < 0) ruralInfectedIcuWardFlow.splitValue[0] = 0;
			
			//If overflow > 0 ensure that difference between in and out of itermediate is <=0
			if((ruralInfectedIcuDeathOverflow.value + ruralInfectedIcuRecoveryOverflow.value) > 0)
			{
				if(ruralInfectedIcuWardFlow.splitValue[0] - ruralInfectedIcuWardIntermediateFlow.splitValue[0] > 0 &&
				   ruralInfectedIcuWardFlow.splitValue[0] + ruralInfectedIcu.value + ruralInfectedIcuWardIntermediate.value - ruralInfectedIcuWardIntermediateFlow.splitValue[0]
				   > RuralMaxIcuCapacity)
				{
					//Add the difference proportionally
					//IcuOverflowRecoveryRate
					double diff = ruralInfectedIcuWardFlow.splitValue[0] + ruralInfectedIcu.value + ruralInfectedIcuWardIntermediate.value - ruralInfectedIcuWardIntermediateFlow.splitValue[0] - RuralMaxIcuCapacity;
					
					ruralInfectedIcuDeathOverflow.value += diff * (1 - RuralIcuOverflowRecoveryRate);
					ruralInfectedIcuRecoveryOverflow.value += diff * RuralIcuOverflowRecoveryRate;
					
					ruralInfectedIcuWardFlow.splitValue[0] -= diff;
				}
			}
			
			
			ruralInfectedIcuIntermediateRecoveryOverflow.calculate(ruralInfectedIcuOverflowIntermediateRecovery.value, ruralInfectedIcuRecoveryOverflow.value, RuralIcuOverflowRecoveryOffsetDuration);
				
			
			infectedWardIntermediateRecoveredFlow.calculate(infectedWardRecoveredIntermediate.value, HospitalStayDuration);
			ruralInfectedWardIntermediateRecoveredFlow.calculate(ruralInfectedWardRecoveredIntermediate.value, RuralHospitalStayDuration);
			
			infectedIcuDischargedIntermediateRecoveredFlow.calculate(infectedIcuDischargedRecoveredIntermediate.value, DischargeStayDuration);
			ruralInfectedIcuDischargedIntermediateRecoveredFlow.calculate(ruralInfectedIcuDischargedRecoveredIntermediate.value, RuralDischargeStayDuration);
			
			infectionIntermediateRecoveredFlow.calculate(infectiousRecoveredIntermediate.value, AverageIllnessDuration);
			ruralInfectionIntermediateRecoveredFlow.calculate(ruralInfectiousRecoveredIntermediate.value, RuralAverageIllnessDuration);
			
			hospitalizedDeathIntermediateFlow.calculate(infectedHospitalizedDeathIntermediate.value, AverageHospitalizationDeathDuration);
			ruralHospitalizedDeathIntermediateFlow.calculate(ruralInfectedHospitalizedDeathIntermediate.value, RuralAverageHospitalizationDeathDuration);
			
			infectedRecoveredFlow.calculate(infectedWard.value, infectedWardIcuFlow.value);
			ruralInfectedRecoveredFlow.calculate(ruralInfectedWard.value, ruralInfectedWardIcuFlow.splitValue);
			
			//Update the stocks
			susceptible.step(dt);
			totalInfected.step(dt);
			infectious.step(dt);
			
			ruralSusceptible.step(dt);
			ruralTotalInfected.step(dt);
			ruralInfectious.step(dt);
			
			infectedHospitalized.step(dt);
			ruralInfectedHospitalized.step(dt);	
			
			infectedIcu.step(dt);
			infectedIcuWardIntermediate.step(dt);
			infectedWard.step(dt);
			infectedIcuDischargedWard.step(dt);
			
			infectedIcuRuralUrban.step(dt);
			infectedIcuRuralWardIntermediate.step(dt);
			
			ruralInfectedIcu.step(dt);
			ruralInfectedIcuWardIntermediate.step(dt);
			ruralInfectedWard.step(dt);
			ruralInfectedIcuDischargedWard.step(dt);
			
			if(ruralInfectedIcu.value + ruralInfectedIcuWardIntermediate.value > RuralMaxIcuCapacity + 0.5)
			{
				System.out.println("ABOVE RURAL ICU CAPACITY!");
			}
			
			double dDeath = infectedDeath.step(dt) * dt;
			recovered.step(dt);
		
			double dRuralDeath = ruralInfectedDeath.step(dt) * dt;
			ruralRecovered.step(dt);
			
			infectedIcuOverflowIntermediateRecovery.step(dt);
			ruralInfectedIcuOverflowIntermediateRecovery.step(dt);
			
			infectiousRecoveredIntermediate.step(dt);
			ruralInfectiousRecoveredIntermediate.step(dt);
			
			infectedHospitalizedDeathIntermediate.step(dt);
			ruralInfectedHospitalizedDeathIntermediate.step(dt);
			
			infectedWardRecoveredIntermediate.step(dt);
			ruralInfectedWardRecoveredIntermediate.step(dt);
			
			infectedIcuDischargedRecoveredIntermediate.step(dt);
			ruralInfectedIcuDischargedRecoveredIntermediate.step(dt);
			
			//Update the day counter
			currentDay += dt;
			
			//Update the files
			String strDay = df.format(currentDay);

			dInfectedOutput.append(strDay + "," + (infectious.value + infectiousRecoveredIntermediate.value));
			dInfectedOutput.append(System.getProperty("line.separator"));
			
			dRecoveredOutput.append(strDay + "," + recovered.value);
			dRecoveredOutput.append(System.getProperty("line.separator"));
			
			dSusceptibleOutput.append(strDay + "," + susceptible.value);
			dSusceptibleOutput.append(System.getProperty("line.separator"));
			
			infectedOutput.append(strDay + "," + totalInfected.value);
			infectedOutput.append(System.getProperty("line.separator"));
			
			deadOutput.append(strDay + "," + dDeath);
			deadOutput.append(System.getProperty("line.separator"));
			
			recoveredOutput.append(strDay + "," + recovered.value);
			recoveredOutput.append(System.getProperty("line.separator"));
			
			icuOutput.append(strDay + "," + (infectedIcu.value + infectedIcuWardIntermediate.value + infectedIcuRuralUrban.value + infectedIcuRuralWardIntermediate.value));
			icuOutput.append(System.getProperty("line.separator"));
			
			statesOutput.append(strDay + "," + susceptible.value + "," + (infectious.value + infectiousRecoveredIntermediate.value) + "," + infectedHospitalized.value + "," + (infectedIcu.value + infectedIcuWardIntermediate.value + infectedIcuRuralUrban.value + infectedIcuRuralWardIntermediate.value) + "," + (infectedIcuOverflowIntermediateRecovery.value + infectedWardRecoveredIntermediate.value + infectedIcuDischargedWard.value + infectedIcuDischargedRecoveredIntermediate.value + infectedWard.value) + "," + recovered.value + "," + infectedDeath.value);
			statesOutput.append(System.getProperty("line.separator"));
			
			
			
			dRuralInfectedOutput.append(strDay + "," + (ruralInfectious.value + ruralInfectiousRecoveredIntermediate.value));
			dRuralInfectedOutput.append(System.getProperty("line.separator"));
			
			dRuralRecoveredOutput.append(strDay + "," + ruralRecovered.value);
			dRuralRecoveredOutput.append(System.getProperty("line.separator"));
			
			dRuralSusceptibleOutput.append(strDay + "," + ruralSusceptible.value);
			dRuralSusceptibleOutput.append(System.getProperty("line.separator"));
			
			ruralInfectedOutput.append(strDay + "," + ruralTotalInfected.value);
			ruralInfectedOutput.append(System.getProperty("line.separator"));
			
			ruralDeadOutput.append(strDay + "," + dRuralDeath);
			ruralDeadOutput.append(System.getProperty("line.separator"));
			
			ruralRecoveredOutput.append(strDay + "," + ruralRecovered.value);
			ruralRecoveredOutput.append(System.getProperty("line.separator"));
			
			ruralIcuOutput.append(strDay + "," + (ruralInfectedIcu.value + ruralInfectedIcuWardIntermediate.value));
			ruralIcuOutput.append(System.getProperty("line.separator"));
			
			ruralStatesOutput.append(strDay + "," + ruralSusceptible.value + "," + (ruralInfectious.value + ruralInfectiousRecoveredIntermediate.value) + "," + ruralInfectedHospitalized.value + "," + (ruralInfectedIcu.value + ruralInfectedIcuWardIntermediate.value) + "," + (ruralInfectedIcuOverflowIntermediateRecovery.value + ruralInfectedWardRecoveredIntermediate.value + ruralInfectedIcuDischargedWard.value + ruralInfectedIcuDischargedRecoveredIntermediate.value + ruralInfectedWard.value) + "," + ruralRecovered.value + "," + ruralInfectedDeath.value);
			ruralStatesOutput.append(System.getProperty("line.separator"));

			//Check the triggers
			if(IcuFullCheck(infectedIcu.value + infectedIcuWardIntermediate.value + infectedIcuRuralUrban.value + infectedIcuRuralWardIntermediate.value, MaxIcuCapacity) && !icuFull)
			{
				stopCount += 1;
				icuFull = true;
				
				//Write current results to file
				if(!ApiFlag)
				{
					WriteOutput(dInfectedOutput, "dInfected_IcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRecoveredOutput, "dRecovered_IcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dSusceptibleOutput, "dSusceptible_IcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(infectedOutput, "infected_IcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(deadOutput, "dead_IcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(recoveredOutput, "recovered_IcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(statesOutput, "states_IcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					
					WriteOutput(dRuralInfectedOutput, "dRuralInfected_IcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRuralRecoveredOutput, "dRuralRecovered_IcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRuralSusceptibleOutput, "dRuralSusceptible_IcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralInfectedOutput, "ruralInfected_IcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralDeadOutput, "ruralDead_IcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralRecoveredOutput, "ruralRecovered_IcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralStatesOutput, "ruralStates_IcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
				}
				
				if(checkPause)
				{
					System.out.print("The ICU has filled on day " + currentDay + "! Press any key to continue . . . ");
					scan.nextLine();
				}
				else
				{
					System.out.println("The ICU has filled on day " + currentDay + "!");
				}
				icuFullDays = currentDay;
			}

			if(IcuFullCheck(ruralInfectedIcu.value + ruralInfectedIcuWardIntermediate.value, RuralMaxIcuCapacity) && !ruralIcuFull)
			{
				stopCount += 1;
				ruralIcuFull = true;
				
				//Write current results to file
				if(!ApiFlag)
				{
					WriteOutput(dInfectedOutput, "dInfected_RuralIcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRecoveredOutput, "dRecovered_RuralIcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dSusceptibleOutput, "dSusceptible_RuralIcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(infectedOutput, "infected_RuralIcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(deadOutput, "dead_RuralIcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(recoveredOutput, "recovered_RuralIcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(statesOutput, "states_RuralIcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					
					WriteOutput(dRuralInfectedOutput, "dRuralInfected_RuralIcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRuralRecoveredOutput, "dRuralRecovered_RuralIcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRuralSusceptibleOutput, "dRuralSusceptible_RuralIcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralInfectedOutput, "ruralInfected_RuralIcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralDeadOutput, "ruralDead_RuralIcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralRecoveredOutput, "ruralRecovered_RuralIcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralStatesOutput, "ruralStates_RuralIcuFull" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
				}
				
				if(checkPause)
				{
					System.out.print("The rural ICU has filled on day " + currentDay + "! Press any key to continue . . . ");
					scan.nextLine();
				}
				else
				{
					System.out.println("The rural ICU has filled on day " + currentDay + "!");
				}
				ruralIcuFullDays = currentDay;
			}
			
			if(MaxInfectedCheck(totalInfected.value, TotalPopulation, MaxInfectedRatio) && !maxInfected)
			{
				stopCount += 1;
				maxInfected = true;
				
				//Write current results to file
				if(!ApiFlag && !IcuRealityFlag)
				{
					WriteOutput(dInfectedOutput, "dInfected_MaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRecoveredOutput, "dRecovered_MaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dSusceptibleOutput, "dSusceptible_MaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(infectedOutput, "infected_MaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(deadOutput, "dead_MaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(recoveredOutput, "recovered_MaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(statesOutput, "states_MaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					
					WriteOutput(dRuralInfectedOutput, "dRuralInfected_MaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRuralRecoveredOutput, "dRuralRecovered_MaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRuralSusceptibleOutput, "dRuralSusceptible_MaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralInfectedOutput, "ruralInfected_MaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralDeadOutput, "ruralDead_MaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralRecoveredOutput, "ruralRecovered_MaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralStatesOutput, "ruralStates_MaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
				}
				
				if(checkPause)
				{
					System.out.print("The max infected has been reached on day " + currentDay + "! Press any key to continue . . . ");
					scan.nextLine();
				}
				else
				{
					System.out.println("The max infected has been reached on day " + currentDay + "!");
				}
				maxInfectedDays = currentDay;
			}
			
			if(MaxInfectedCheck(ruralTotalInfected.value, RuralTotalPopulation, RuralMaxInfectedRatio) && !ruralMaxInfected)
			{
				stopCount += 1;
				ruralMaxInfected = true;
				
				//Write current results to file
				if(!ApiFlag && !IcuRealityFlag)
				{
					WriteOutput(dInfectedOutput, "dInfected_RuralMaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRecoveredOutput, "dRecovered_RuralMaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dSusceptibleOutput, "dSusceptible_RuralMaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(infectedOutput, "infected_RuralMaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(deadOutput, "dead_RuralMaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(recoveredOutput, "recovered_RuralMaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(statesOutput, "states_RuralMaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					
					WriteOutput(dRuralInfectedOutput, "dRuralInfected_RuralMaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRuralRecoveredOutput, "dRuralRecovered_RuralMaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRuralSusceptibleOutput, "dRuralSusceptible_RuralMaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralInfectedOutput, "ruralInfected_RuralMaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralDeadOutput, "ruralDead_RuralMaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralRecoveredOutput, "ruralRecovered_RuralMaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralStatesOutput, "ruralStates_RuralMaxInfected" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
				}
				
				if(checkPause)
				{
					System.out.print("The rural max infected has been reached on day " + currentDay + "! Press any key to continue . . . ");
					scan.nextLine();
				}
				else
				{
					System.out.println("The rural max infected has been reached on day " + currentDay + "!");
				}
				ruralMaxInfectedDays = currentDay;
			}
			
			if(InfectionEndCheck(infectious.value + infectiousRecoveredIntermediate.value) && !infectionEnd)
			{
				stopCount += 1;
				infectionEnd = true;
				
				//Write current results to file
				if(!ApiFlag && !IcuRealityFlag)
				{
					WriteOutput(dInfectedOutput, "dInfected_InfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRecoveredOutput, "dRecovered_InfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dSusceptibleOutput, "dSusceptible_InfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(infectedOutput, "infected_InfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(deadOutput, "dead_InfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(recoveredOutput, "recovered_InfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(statesOutput, "states_InfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					
					WriteOutput(dRuralInfectedOutput, "dRuralInfected_InfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRuralRecoveredOutput, "dRuralRecovered_InfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRuralSusceptibleOutput, "dRuralSusceptible_InfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralInfectedOutput, "ruralInfected_InfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralDeadOutput, "ruralDead_InfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralRecoveredOutput, "ruralRecovered_InfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralStatesOutput, "ruralStates_InfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
				}
				else if (!ApiFlag)
				{
					WriteOutput(icuOutput, "IcuOutput_InfectionEnd_IRF" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralIcuOutput, "RuralIcuOutput_InfectionEnd_IRF" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
				}
				
				if(checkPause)
				{
					System.out.print("The infection has ended on day " + currentDay + "! Press any key to continue . . . ");
					scan.nextLine();
				}
				else
				{
					System.out.println("The infection has ended on day " + currentDay + "!");
				}
				infectionEndDays = currentDay;
			}
			
			if(InfectionEndCheck(ruralInfectious.value + ruralInfectiousRecoveredIntermediate.value) && !ruralInfectionEnd)
			{
				stopCount += 1;
				ruralInfectionEnd = true;
				
				//Write current results to file
				if(!ApiFlag && !IcuRealityFlag)
				{
					WriteOutput(dInfectedOutput, "dInfected_RuralInfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRecoveredOutput, "dRecovered_RuralInfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dSusceptibleOutput, "dSusceptible_RuralInfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(infectedOutput, "infected_RuralInfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(deadOutput, "dead_RuralInfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(recoveredOutput, "recovered_RuralInfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(statesOutput, "states_RuralInfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					
					WriteOutput(dRuralInfectedOutput, "dRuralInfected_RuralInfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRuralRecoveredOutput, "dRuralRecovered_RuralInfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(dRuralSusceptibleOutput, "dRuralSusceptible_RuralInfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralInfectedOutput, "ruralInfected_RuralInfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralDeadOutput, "ruralDead_RuralInfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralRecoveredOutput, "ruralRecovered_RuralInfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralStatesOutput, "ruralStates_RuralInfectionEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
				}
				else if (!ApiFlag)
				{
					WriteOutput(icuOutput, "IcuOutput_RuralInfectionEnd_IRF" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
					WriteOutput(ruralIcuOutput, "RuralIcuOutput_RuralInfectionEnd_IRF" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
				}
				
				if(checkPause)
				{
					System.out.print("The rural infection has ended on day " + currentDay + "! Press any key to continue . . . ");
					scan.nextLine();
				}
				else
				{
					System.out.println("The rural infection has ended on day " + currentDay + "!");
				}
				ruralInfectionEndDays = currentDay;
			}
		}
			
		if(currentDay < maxDays)
		{
			System.out.println("The simulation has completed!");
		}
		else
		{
			System.out.println("The simulation has ran for the maximum of " + maxDays + " days! Outputting the results and stopping the simulation.");
		}
		
		//Write current results to file
		if(!IcuRealityFlag)
		{
			WriteOutput(dInfectedOutput, "dInfected_SimulationEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
			WriteOutput(dRecoveredOutput, "dRecovered_SimulationEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
			WriteOutput(dSusceptibleOutput, "dSusceptible_SimulationEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
			WriteOutput(infectedOutput, "infected_SimulationEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
			WriteOutput(deadOutput, "dead_SimulationEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
			WriteOutput(recoveredOutput, "recovered_SimulationEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
			WriteOutput(icuOutput, "IcuOutput_SimulationEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
			WriteOutput(statesOutput, "states_SimulationEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
			
			WriteOutput(dRuralInfectedOutput, "dRuralInfected_SimulationEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
			WriteOutput(dRuralRecoveredOutput, "dRuralRecovered_SimulationEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
			WriteOutput(dRuralSusceptibleOutput, "dRuralSusceptible_SimulationEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
			WriteOutput(ruralInfectedOutput, "ruralInfected_SimulationEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
			WriteOutput(ruralDeadOutput, "ruralDead_SimulationEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
			WriteOutput(ruralRecoveredOutput, "ruralRecovered_SimulationEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
			WriteOutput(ruralIcuOutput, "RuralIcuOutput_SimulationEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
			WriteOutput(ruralStatesOutput, "ruralStates_SimulationEnd" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
			
			//Write the days the checks were reached and peaked values to file
			PrintWriter writer;
			try {
				writer = new PrintWriter(new File("results" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".json"));
				writer.write("{\n");
				writer.write("\t\"icuFullDays\": " + df.format(icuFullDays) + ",\n");
				writer.write("\t\"maxInfectedDays\": " + df.format(maxInfectedDays) + ",\n");
				writer.write("\t\"infectionEndDays\": " + df.format(infectionEndDays) + ",\n");
				
				writer.write("\t\"totalInfected\": " + df.format(totalInfected.value) + ",\n");
				writer.write("\t\"totalDead\": " + df.format(infectedDeath.value) + ",\n");
				
				writer.write("\t\"ruralIcuFullDays\": " + df.format(ruralIcuFullDays) + ",\n");
				writer.write("\t\"ruralMaxInfectedDays\": " + df.format(ruralMaxInfectedDays) + ",\n");
				writer.write("\t\"ruralInfectionEndDays\": " + df.format(ruralInfectionEndDays) + ",\n");
				
				writer.write("\t\"ruralTotalInfected\": " + df.format(ruralTotalInfected.value) + ",\n");
				writer.write("\t\"ruralTotalDead\": " + df.format(ruralInfectedDeath.value) + "\n");
				writer.write("}");
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("An I/O error ocurred when writing the checks output");
				return;
			}
		}
		else
		{
			WriteOutput(icuOutput, "IcuOutput_SimulationEnd_IRF" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
			WriteOutput(ruralIcuOutput, "RuralIcuOutput_SimulationEnd_IRF" + (UniqueId == -1 ? "" : "_" + UniqueId) + ".csv");
		}
		
		//Required for the API since this instance is 
		stopCount = 0;
		IcuRealityFlag = false;
		ApiFlag = false;
	}
	
	/**
	 * Checks if the ICU's maximum capacity has been reached
	 * @param InfectedIcu
	 * @return boolean
	 */
	private boolean IcuFullCheck(double InfectedIcu, double MaxIcuCapacity)
	{
		return Math.round(InfectedIcu) >= MaxIcuCapacity;
	}
	
	/**
	 * Checks if the maximum number of individuals that can be infected has been reached
	 * @param TotalInfected
	 * @return boolean
	 */
	private boolean MaxInfectedCheck(double TotalInfected, double TotalPopulation, double MaxInfectedRatio)
	{
		return (TotalInfected / TotalPopulation) >= MaxInfectedRatio;
	}
	
	/**
	 * Checks if the infection has ended (i.e., infectious population < 1)
	 * @param Infectious
	 * @return boolean
	 */
	private boolean InfectionEndCheck(double Infectious)
	{
		return Infectious < 1;
	}
	
	/**
	 * Writes the output to filename
	 * @param output
	 * @param filename
	 */
	private void WriteOutput(StringBuilder output, String filename)
	{
		PrintWriter writer;
		try {
			writer = new PrintWriter(new File(filename));
			writer.write(output.toString());
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("An I/O error ocurred when writing the output");
			return;
		}
	}
}
