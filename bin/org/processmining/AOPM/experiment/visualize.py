from bokeh.plotting import figure, output_file, show,save
import pandas as pd

def get_constraint_log(wo_action=False):
	if(wo_action==False):
		constraint_df = pd.read_csv("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/logs/constraint-log-2020-05-11.csv",names=["order_names", "times","outcome"])
	else:
		constraint_df = pd.read_csv("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/logs/wo-constraint-log-2020-05-11.csv",names=["order_names", "times","outcome"])
	exclude = ["o"+str(x) for x in range(101,1000,1)]

	constraint_df = constraint_df.loc[~constraint_df["order_names"].isin(exclude)]

	constraint_df = constraint_df.sort_values(by=["times", "order_names"])

	return constraint_df


if __name__=="__main__":
	# Real
	# w_constraintLog = pd.read_csv("./real-w-constraint-log.csv")
	# wo_constraintLog = pd.read_csv("./real-wo-constraint-log.csv")

	# Artificial
	w_constraintLog = get_constraint_log()
	wo_constraintLog = get_constraint_log(wo_action=True)
	max_time = max(set(wo_constraintLog["times"]))

	times = [24*x for x in range(0,int(max_time/24)+2,1)]
	evaluation = ["non-violated", "violated"]

	numTotalList = []
	num_w_NOKList = []
	num_wo_NOKList = []
	for x in times:
		totalList = wo_constraintLog.loc[(wo_constraintLog['times']==x)]
		numTotal = len(totalList)
		numTotalList.append(numTotal)

		w_NOKList = w_constraintLog.loc[(w_constraintLog['times']==x) & (w_constraintLog['outcome']=='nok')]
		wo_NOKList = wo_constraintLog.loc[(wo_constraintLog['times']==x) & (wo_constraintLog['outcome']=='nok')]

		num_w_NOK = len(w_NOKList)
		num_wo_NOK = len(wo_NOKList)
		num_w_NOKList.append(num_w_NOK)
		num_wo_NOKList.append(num_wo_NOK)

	xaxis = [x for x in times]


	data = dict(xaxis=xaxis,numTotalList=numTotalList, num_w_NOKList=num_w_NOKList, num_wo_NOKList=num_wo_NOKList)
	print(data)

	p = figure(title='Number of instances (total, violated (before action), violated (after action)', x_axis_label='times',
	                  y_axis_label='Number of ongoing instances', sizing_mode="stretch_both")
	p.line(xaxis, numTotalList, line_width=2, line_color='orange', legend='Total')
	p.line(xaxis, num_w_NOKList, line_width=2, line_color='green', legend='Violated (with action)')
	p.line(xaxis, num_wo_NOKList, line_width=2, line_color='red', legend='Violated (without action)')
	# output_file("/Users/GYUNAM/Downloads/test.html")
	show(p)


	"""
	from bokeh.plotting import figure, output_file, show,save
	if __name__ == '__main__':
	    year = [1960, 1970, 1980, 1990, 2000, 2010]
	    pop_pakistan = [44.91, 58.09, 78.07, 107.7, 138.5, 170.6]
	    pop_india = [449.48, 553.57, 696.783, 870.133, 1000.4, 1309.1]
	output_file('line.html', mode='inline')
	    p = figure(title='Population Graph of India and Pakistan', x_axis_label='Year',
	                  y_axis_label='Population in million')
	    p.line(year, pop_pakistan, line_width=2, line_color='green', legend='Pakistan')
	    p.line(year, pop_india, line_width=2, line_color='orange', legend='India')
	    show(p)
	"""