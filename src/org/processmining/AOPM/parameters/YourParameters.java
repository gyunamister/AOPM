package org.processmining.AOPM.parameters;

import org.processmining.AOPM.models.AEConfig;
import org.processmining.AOPM.models.CMConfig;
import org.processmining.AOPM.models.YourFirstInput;
import org.processmining.AOPM.models.YourSecondInput;
import org.processmining.EIS.Simulation.ProcessSimulation;
import org.processmining.basicutils.parameters.impl.PluginParametersImpl;

public class YourParameters extends PluginParametersImpl {

	private boolean yourBoolean;
	private int yourInteger;
	private String yourString;
	public AEConfig aeConfig;
	public CMConfig cmConfig;
	public YourParameters(YourFirstInput input1, YourSecondInput input2) {
		super();
		setYourBoolean(input1.equals(input2));
		setYourInteger(input1.toString().length() - input2.toString().length());
		setYourString(input1.toString() + input2.toString());
	}
	
	public YourParameters(ProcessSimulation input1) {
		super();
//		setYourBoolean(input1.equals(input2));
//		setYourInteger(input1.toString().length() - input2.toString().length());
//		setYourString(input1.toString() + input2.toString());
	}
	
	public YourParameters() {
		super();
	}

	public YourParameters(YourParameters parameters) {
		super(parameters);
		setYourBoolean(parameters.isYourBoolean());
		setYourInteger(parameters.getYourInteger());
		setYourString(parameters.getYourString());
	}

	public boolean equals(Object object) {
		if (object instanceof YourParameters) {
			YourParameters parameters = (YourParameters) object;
			return super.equals(parameters) &&
					isYourBoolean() == parameters.isYourBoolean() &&
					getYourInteger() == parameters.getYourInteger() &&
					getYourString().equals(parameters.getYourString());
		}
		return false;
	}

	public void setYourBoolean(boolean yourBoolean) {
		this.yourBoolean = yourBoolean;
	}

	public boolean isYourBoolean() {
		return yourBoolean;
	}

	public void setYourInteger(int yourInteger) {
		this.yourInteger = yourInteger;
	}

	public int getYourInteger() {
		return yourInteger;
	}

	public void setYourString(String yourString) {
		this.yourString = yourString;
	}

	public String getYourString() {
		return yourString;
	}

	public String toString() {
		return "(" + getYourString() + "," + getYourInteger() + "," + isYourBoolean() + ")";
	}
	
	public void updateAEConf(AEConfig aeConfig) {
		this.aeConfig = aeConfig;
	}
	
	public void updateCMConf(CMConfig cmConfig) {
		this.cmConfig = cmConfig;
	}
}
