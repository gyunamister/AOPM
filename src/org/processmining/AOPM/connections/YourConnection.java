package org.processmining.AOPM.connections;

import org.processmining.AOPM.models.YourFirstInput;
import org.processmining.AOPM.models.YourSecondInput;
import org.processmining.AOPM.parameters.YourParameters;
import org.processmining.AOPM.simulation.Simulator;
import org.processmining.EIS.Simulation.ProcessSimulation;
import org.processmining.framework.connections.impl.AbstractConnection;

public class YourConnection extends AbstractConnection {

	/**
	 * Label for first input.
	 */
	public final static String FIRSTINPUT = "Artificial Information System";

	/**
	 * Label for second input.
	 */
	public final static String SECONDINPUT = "Second Input";

	/**
	 * Label for output.
	 */
	public final static String OUTPUT = "Output";

	/**
	 * Private copy of parameters.
	 */
	private YourParameters parameters;

	/**
	 * Create a connection.
	 * @param input1 First input.
	 * @param input2 Second input.
	 * @param output Output.
	 * @param parameters Parameters.
	 */
	public YourConnection(YourFirstInput input1, YourSecondInput input2, Simulator output, YourParameters parameters) {
		super("Your Connection");
		put(FIRSTINPUT, input1);
		put(SECONDINPUT, input2);
		put(OUTPUT, output);
		this.parameters = new YourParameters(parameters);
	}
	
	public YourConnection(ProcessSimulation input1, Simulator output, YourParameters parameters) {
		super("Your Connection");
		put(FIRSTINPUT, input1);
		put(OUTPUT, output);
		this.parameters = new YourParameters(parameters);
	}
	
	public YourConnection(Simulator output, YourParameters parameters) {
		super("Your Connection");
		put(OUTPUT, output);
		this.parameters = new YourParameters(parameters);
	}

	/**
	 *
	 * @return The parameters stored in the connection.
	 */
	public YourParameters getParameters() {
		return parameters;
	}
}
