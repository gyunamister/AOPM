import pandas as pd;

def splitLog(interval):
	"""
	Split the log into sublogs
	Each sublog contains the events from ongoing instances
	"""
	eventlog = pd.read_csv("../w-eventlog.csv",index_col=None,names=["event-identifier", "activity", "resource", "startTimestamp", "completeTimestamp", "Order", "Item", "Package", "Route"])
	orderLifespan = inferOrderLifespan(eventlog)
	ongoingOrderbyTime = inferOngoingOrder(eventlog,orderLifespan)
	for i in range(max(eventlog["completeTimestamp"])):
		if(i%interval==0):
			sublog = eventlog.loc[(eventlog["Order"].isin(ongoingOrderbyTime[i])) & (eventlog["completeTimestamp"] < i)]
			sublog.to_csv("./w-sublogs/sublog-{}.csv".format(i))

def inferOngoingOrder(eventlog, orderLifespan):
	ongoingOrderbyTime = dict()
	for i in range(max(eventlog["completeTimestamp"])):
		ongoingOrderbyTime[i] = list()
		for key in orderLifespan.keys():
			if orderLifespan[key][0] < i < orderLifespan[key][1]:
				ongoingOrderbyTime[i] += [key]
	return ongoingOrderbyTime

def inferOrderLifespan(eventlog):
	orderLifespan = dict()
	maxCompleteTime = max(eventlog["completeTimestamp"])
	for row in eventlog.itertuples():
		if(row.activity=="place_order"):
			orderLifespan[row.Order] = [row.startTimestamp,maxCompleteTime]
		elif(row.activity=="deliver_package"):
			orderLifespan[row.Order][1] = row.completeTimestamp
	return orderLifespan


if __name__ == '__main__':
	interval = 24;
	splitLog(interval)