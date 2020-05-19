package org.processmining.AOPM.models;

public class EvalResult {
	public Context ctx;
	public String outc;
	
	public EvalResult(Context ctx, String outc) {
		this.ctx = ctx;
		this.outc=outc;
	}
	
	public Context getContext() {
		return this.ctx;
	}
	
	public String getOutcome() {
		return this.outc;
	}
}
