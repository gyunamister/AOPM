import pandas as pd;

def preprocess():
	eventlog = pd.read_csv("./modi-BPI_Challenge_2019.csv")
	filteredLog = eventlog[['Case ID', 'Activity', 'Resource', 'Complete Timestamp']]
	filteredLog.rename(columns={'Case ID': 'CaseID', 'Complete Timestamp': 'completeTimestamp'}, inplace=True)
	caseid = ''
	validEvents = list()
	done = False
	for row in filteredLog.itertuples():
		if(caseid == row.CaseID):
			if(done==False):
				validEvents.append(row.Index)
				if(row.Activity=="Record Goods Receipt"):
					done=True
		else:
			done=False
			caseid = row.CaseID
			validEvents.append(row.Index)
	filteredLog['completeTimestamp'] = pd.to_datetime(filteredLog['completeTimestamp'])
	filteredLog = filteredLog.loc[filteredLog.index.isin(validEvents)]
	filteredLog['completeTimestamp'] = filteredLog['completeTimestamp'] - min(filteredLog['completeTimestamp'])
	filteredLog['completeTimestamp'] = filteredLog['completeTimestamp'].apply(days_to_hours)
	return filteredLog

def days_hours_minutes(td):
    return td.days, td.seconds//3600, (td.seconds//60)%60

def days_to_hours(x):
	days, hours, minutes = days_hours_minutes(x)
	return days*24 + hours

if __name__ == '__main__':
	filteredLog = preprocess()
	print(filteredLog)
	filteredLog.to_csv("./final-BPI_Challenge_2019.csv",index=False)