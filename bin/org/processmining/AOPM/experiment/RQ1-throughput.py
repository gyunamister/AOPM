import numpy as np
import pandas as pd

from bokeh.plotting import figure, output_file, show
from bokeh.sampledata.les_mis import data

def get_constraint_log():
  constraint_df = pd.read_csv("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/logs/constraint-log-2020-05-11.csv",names=["order_names", "times","outcome"])
  exclude = ["o"+str(x) for x in range(41,1000,1)]

  constraint_df = constraint_df.loc[~constraint_df["order_names"].isin(exclude)]

  constraint_df = constraint_df.sort_values(by=["times", "order_names"])

  constraint_df = constraint_df.loc[constraint_df["outcome"]=="nok"]
  constraint_df["colors"] = "#FF0000"

  del constraint_df["outcome"]

  return constraint_df

def get_action_log():
  action_df = pd.read_csv("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/logs/action-log-2020-05-11.csv",names=["operations","order_names", "times"])
  action_df = action_df.sort_values(by=["times", "order_names"])
  priority_df = action_df.loc[action_df["operations"]=="Set higher priority"];
  priority_df["colors"] = "#3498DB";
  email_df = action_df.loc[action_df["operations"]=="Send an email to the case manager"];
  email_df["colors"] = "#1A237E";
  return priority_df, email_df

def get_order_history():
  eventlog = pd.read_csv("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/IS_OHP/eventlog.csv",index_col=None,names=["event-id", "proc", "act", "res", "completeTimestamp", "Order", "Item", "Package", "Route"])
  eventlog = eventlog[["Order","act","completeTimestamp"]]

  exclude = ["o"+str(x) for x in range(41,1000,1)]
  eventlog = eventlog.loc[~eventlog["Order"].isin(exclude)]

  ft_eventlog = eventlog.loc[(eventlog["act"]=="place_order") | (eventlog["act"]=="deliver_package")]
  order_names = set(ft_eventlog["Order"])

  history_set = []
  for oi in order_names:
    temp = ft_eventlog.loc[ft_eventlog["Order"]==oi]
    if len(temp)==2:
      startTime = temp.iloc[0]["completeTimestamp"]
      completeTime = temp.iloc[1]["completeTimestamp"]
      history = [oi,startTime,completeTime]
      history_set.append(history)
  return history_set

def gen_history_log(history_set, max_time):
  data = []
  agg_size=1
  for x in range(1,int(max_time/agg_size)+1,1):
    for h in history_set:
      if h[1] < x*agg_size < h[2]:
        row = [h[0],x*agg_size,"#ABB2B9"]
        data.append(row)
  return pd.DataFrame(data, columns=["order_names", "times","colors"])



if __name__=="__main__":
  constraint_df = get_constraint_log()
  min_time = min(set(constraint_df["times"]))
  max_time = max(set(constraint_df["times"]))

  history_set = get_order_history()
  event_df = gen_history_log(history_set,max_time)

  agg_size=1
  x_range = [str(x) for x in range(0,max_time,agg_size)]
  # x_range = [x_range[i] + "-" + x_range[i+1] for i in range(0,len(x_range)-1,1)]

  y_range = [int(x[1:]) for x in event_df["order_names"]]
  y_range = ["o"+str(x) for x in sorted(list(set(y_range)))]

  x2_range = [str(x) for x in range(0,max_time,24)]

  y2_range = [int(x[1:]) for x in constraint_df["order_names"]]
  y2_range = ["o"+str(x) for x in sorted(list(set(y2_range)))]

  priority_df, email_df = get_action_log()

  #Since the range is String,
  priority_df["times"] = priority_df["times"].apply(str)
  email_df["times"] = email_df["times"].apply(str)
  event_df["times"] = event_df["times"].apply(str)
  constraint_df["times"] = constraint_df["times"].apply(str)

  p = figure(title='RQ1: ',
    x_axis_location="below", tools="hover,save,pan,wheel_zoom,box_zoom,reset",
             x_range=x_range, y_range=y_range,
             tooltips = [('names', '@order_names, @times'), ('count', '@count')], sizing_mode="stretch_both")

  # p.plot_width = 800
  # p.plot_height = 800
  p.grid.grid_line_color = None
  p.axis.axis_line_color = None
  p.axis.major_tick_line_color = None
  p.axis.major_label_text_font_size = "7px"
  p.axis.major_label_standoff = 0
  p.xaxis.major_label_orientation = np.pi/3

  p.rect('times', 'order_names', 0.9, 0.9, source=event_df, color='colors', alpha=0.5, line_color=None, hover_line_color='black')
  p.rect('times', 'order_names', 0.9, 0.9, source=constraint_df, color='colors', alpha=1, line_color=None, hover_line_color='black')

  p2 = figure(title="RQ2: ",
    x_axis_location="below", tools="hover,save,pan,wheel_zoom,box_zoom,reset",
             x_range=x_range, y_range=y_range,
             tooltips = [('names', '@order_names, @times'), ('count', '@count')], sizing_mode="stretch_both")

  # p2.plot_width = 800
  # p2.plot_height = 800
  p2.grid.grid_line_color = None
  p2.axis.axis_line_color = None
  p2.axis.major_tick_line_color = None
  p2.axis.major_label_text_font_size = "7px"
  p2.axis.major_label_standoff = 0
  p2.xaxis.major_label_orientation = np.pi/3

  # p2.rect('times', 'order_names', 0.9, 0.9, source=constraint_df,
  #        color='colors', alpha='alphas', line_color=None,
  #        hover_line_color='black', hover_color='colors')
  p2.rect('times', 'order_names', 0.9, 0.9, source=event_df, color='colors', alpha=0.5, line_color=None, hover_line_color='black')
  p2.rect('times', 'order_names', 0.9, 0.9, source=constraint_df, color='colors', line_color=None,
         hover_line_color='black')
  p2.circle('times', 'order_names', size=20, fill_color=None, source=priority_df, color='colors', hover_line_color='black')
  p2.diamond('times', 'order_names', size=20, fill_color=None, source=email_df, color='colors', hover_line_color='black')
  # output_file("les_mis.html", title="les_mis.py example")

  show(p) # show the plot
  show(p2) # show the plot