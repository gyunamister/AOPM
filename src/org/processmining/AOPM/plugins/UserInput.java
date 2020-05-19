package org.processmining.AOPM.plugins;

import java.util.Collection;

import org.deckfour.uitopia.api.event.TaskListener.InteractionResult;
import org.processmining.AOPM.algorithms.YourAlgorithm;
import org.processmining.AOPM.connections.YourConnection;
import org.processmining.AOPM.dialogs.InputDialog;
import org.processmining.AOPM.models.YourFirstInput;
import org.processmining.AOPM.models.YourSecondInput;
import org.processmining.AOPM.parameters.YourParameters;
import org.processmining.AOPM.simulation.Simulator;
import org.processmining.EIS.Simulation.ProcessSimulation;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.contexts.uitopia.annotations.UITopiaVariant;
import org.processmining.framework.connections.ConnectionCannotBeObtained;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.plugin.annotations.Plugin;
import org.processmining.framework.plugin.annotations.PluginVariant;

@Plugin(name = "AOPM", parameterLabels = { "Artificial Information System", "Not specified", "Simulator settings" },
	    returnLabels = { "AOPM Simulator" }, returnTypes = { Simulator.class })
public class UserInput extends YourAlgorithm {

	/**
	 * The plug-in variant that runs in any context and requires a parameters.
	 *
	 * @param context The context to run in.
	 * @param input1 The first input.
	 * @param input2 The second input.
	 * @param parameters The parameters to use.
	 * @return The output.
	 */
	@UITopiaVariant(affiliation = "Your affiliation", author = "Your name", email = "Your e-mail address")
	@PluginVariant(variantLabel = "Your plug-in name, parameters", requiredParameterLabels = { 0, 1, 2 })
	public Simulator run(PluginContext context, YourFirstInput input1, YourSecondInput input2, YourParameters parameters) {
		// Apply the algorithm depending on whether a connection already exists.
	    return runConnections(context, input1, input2, parameters);
	}

	/**
	 * The plug-in variant that runs in any context and uses the default parameters.
	 *
	 * @param context The context to run in.
	 * @param input1 The first input.
	 * @param input2 The second input.
	 * @return The output.
	 */
	@UITopiaVariant(affiliation = "PADS", author = "Gyunam Park", email = "gnpark@pads.rwth-aachen.de")
	@PluginVariant(variantLabel = "Your plug-in name, parameters", requiredParameterLabels = { 0, 1 })
	public Simulator runDefault(PluginContext context, YourFirstInput input1, YourSecondInput input2) {
		// Get the default parameters.
	    YourParameters parameters = new YourParameters(input1, input2);
		// Apply the algorithm depending on whether a connection already exists.
	    return runConnections(context, input1, input2, parameters);
	}

	/**
	 * The plug-in variant that runs in a UI context and uses a dialog to get the parameters.
	 *
	 * @param context The context to run in.
	 * @param input1 The first input.
	 * @param input2 The second input.
	 * @return The output.
	 */
	@UITopiaVariant(affiliation = "PADS", author = "Gyunam Park", email = "gnpark@pads.rwth-aachen.de")
	@PluginVariant(variantLabel = "with the connected information system", requiredParameterLabels = {0})
	public Simulator runUI(UIPluginContext context, ProcessSimulation input1) {
		// Get the default parameters.
	    YourParameters parameters = new YourParameters();
	    // Get a dialog for this parameters.
	    InputDialog dialog = new InputDialog(context, parameters);
	    // Show the dialog. User can now change the parameters.
	    InteractionResult result = context.showWizard("Your dialog title", true, true, dialog);
	    // User has close the dialog.
	    if (result == InteractionResult.FINISHED) {
			// Apply the algorithm depending on whether a connection already exists.
	    	System.out.println("It works: " + input1.resourceList);
	    	return runConnections(context, input1, parameters);
	    }
	    // Dialog got canceled.
	    return null;
	}

	/**
	 * The plug-in variant that allows one to test the dialog to get the parameters.
	 *
	 * @param context The context to run in.
	 * @return The output.
	 */
	@UITopiaVariant(affiliation = "PADS", author = "Gyunam Park", email = "gnpark@pads.rwth-aachen.de")
	@PluginVariant(variantLabel = "AOPM without the connected information system", requiredParameterLabels = { })
	public Simulator testUI(UIPluginContext context) {
		// Create default inputs.
		// Get the default parameters.
	    YourParameters parameters = new YourParameters();
	    // Get a dialog for this parameters.
	    InputDialog dialog = new InputDialog(context, parameters);
	    // Show the dialog. User can now change the parameters.
	    InteractionResult result = context.showWizard("AOPM - Simulator settings", true, true, dialog);
	    // User has close the dialog.
	    if (result == InteractionResult.FINISHED) {
			// Apply the algorithm depending on whether a connection already exists.
	    	return runConnections(context, parameters);
	    }
	    // Dialog got canceled. 
	    return null;
	}

	/**
	 * Apply the algorithm depending on whether a connection already exists.
	 *
	 * @param context The context to run in.
	 * @param input1 The first input.
	 * @param input2 The second input.
	 * @return The output.
	 */
	private Simulator runConnections(PluginContext context, YourFirstInput input1, YourSecondInput input2, YourParameters parameters) {
		if (parameters.isTryConnections()) {
			// Try to found a connection that matches the inputs and the parameters.
			Collection<YourConnection> connections;
			try {
				connections = context.getConnectionManager().getConnections(
						YourConnection.class, context, input1, input2);
				for (YourConnection connection : connections) {
					if (connection.getObjectWithRole(YourConnection.FIRSTINPUT)
							.equals(input1) && connection.getObjectWithRole(YourConnection.SECONDINPUT)
							.equals(input2) && connection.getParameters().equals(parameters)) {
						// Found a match. Return the associated output as result of the algorithm.
						return connection
								.getObjectWithRole(YourConnection.OUTPUT);
					}
				}
			} catch (ConnectionCannotBeObtained e) {
			}
		}
		// No connection found. Apply the algorithm to compute a fresh output result.
		Simulator output = apply(context, input1, input2, parameters);
		if (parameters.isTryConnections()) {
			// Store a connection containing the inputs, output, and parameters.
			context.getConnectionManager().addConnection(
					new YourConnection(input1, input2, output, parameters));
		}
		// Return the output.
		return output;
	}

	private Simulator runConnections(PluginContext context, ProcessSimulation input1, YourParameters parameters) {
		if (parameters.isTryConnections()) {
			// Try to found a connection that matches the inputs and the parameters.
			Collection<YourConnection> connections;
			try {
				connections = context.getConnectionManager().getConnections(
						YourConnection.class, context, input1);
				for (YourConnection connection : connections) {
					if (connection.getObjectWithRole(YourConnection.FIRSTINPUT)
							.equals(input1) && connection.getParameters().equals(parameters)) {
						// Found a match. Return the associated output as result of the algorithm.
						return connection
								.getObjectWithRole(YourConnection.OUTPUT);
					}
				}
			} catch (ConnectionCannotBeObtained e) {
			}
		}
		// No connection found. Apply the algorithm to compute a fresh output result.
		Simulator output = apply(context, input1, parameters);
		if (parameters.isTryConnections()) {
			// Store a connection containing the inputs, output, and parameters.
			context.getConnectionManager().addConnection(
					new YourConnection(input1, output, parameters));
		}
		// Return the output.
		return output;
	}

	private Simulator runConnections(PluginContext context, YourParameters parameters) {
		if (parameters.isTryConnections()) {
			// Try to found a connection that matches the inputs and the parameters.
			Collection<YourConnection> connections;
			try {
				connections = context.getConnectionManager().getConnections(
						YourConnection.class, context);
				for (YourConnection connection : connections) {
					if (connection.getParameters().equals(parameters)) {
						// Found a match. Return the associated output as result of the algorithm.
						return connection
								.getObjectWithRole(YourConnection.OUTPUT);
					}
				}
			} catch (ConnectionCannotBeObtained e) {
			}
		}
		// No connection found. Apply the algorithm to compute a fresh output result.
		Simulator output = apply(context, parameters);
		if (parameters.isTryConnections()) {
			// Store a connection containing the inputs, output, and parameters.
			context.getConnectionManager().addConnection(
					new YourConnection(output, parameters));
		}
		// Return the output.
		return output;
	}

}
