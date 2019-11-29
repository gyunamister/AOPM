package org.processmining.newpackage.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.framework.util.ui.widgets.ProMComboBox;
import org.processmining.framework.util.ui.widgets.ProMTable;
import org.processmining.framework.util.ui.widgets.ProMTextField;
import org.processmining.newpackage.models.YourFirstInput;
import org.processmining.newpackage.models.YourSecondInput;
import org.processmining.newpackage.parameters.YourParameters;
import org.processmining.newpackage.plugins.RelativeLayout;

import com.fluxicon.slickerbox.factory.SlickerFactory;

import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstants;

public class YourDialog extends JPanel {
	private int set_number = 1;
//	public ConstraintPanel const_panel;
	public String constraint;
	public String action;
	public ConditionPanel cond_panel;
//	public ActionPanel act_panel;
	/**
	 *
	 */
	private static final long serialVersionUID = -60087716353524468L;

	/**
	 * The JPanel that allows the user to set (a subset of) the parameters.
	 */
	public YourDialog(UIPluginContext context, YourFirstInput input1, YourSecondInput input2,
			final YourParameters parameters) {
		double size[][] = { { TableLayoutConstants.FILL }, { 30, 30, 30, 30, TableLayoutConstants.FILL } };
		setLayout(new TableLayout(size));
//		Set<String> values = new HashSet<String>();
//		values.add("Option 1");
//		values.add("A case should be completed in 10 days");
//		values.add("Option 3");
//		values.add("Option 4");
//		
//		
//		
//		DefaultListModel<String> listModel = new DefaultListModel<String>();
//		for (String value: values) {
//			listModel.addElement(value);
//		}
//		final ProMList<String> list = new ProMList<String>("Select option", listModel);
//		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		final String defaultValue = "Option 1";
//		list.setSelection(defaultValue);
//		list.addListSelectionListener(new ListSelectionListener() {
//			public void valueChanged(ListSelectionEvent e) {
//				List<String> selected = list.getSelectedValuesList();
//				if (selected.size() == 1) {
//					parameters.setYourString(selected.get(0));
//				} else {
//					/*
//					 * Nothing selected. Revert to selection of default classifier.
//					 */
//					list.setSelection(defaultValue);
//					parameters.setYourString(defaultValue);
//				}
//			}
//		});
//		list.setPreferredSize(new Dimension(30, 100));
//		//add(list, "0, 0");
		
		//const_panel = new ConstraintPanel(context);
		String constraint_list[] = {
				"Select constraint", 
				"c1: An order must be delivered in 200", 
				"c2: An availability of item must be checked before picking",
				"c3: An order must be delivered in 100",
				"cp1: Maximum number of ongoing orders is 50", 
				"cp2: There is no resource involved in more than 5 activities" 
			}; 
		final ProMComboBox<String> constraintComboBox = new ProMComboBox<String>(constraint_list);
		constraintComboBox.setSelectedIndex(0);
		constraintComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(c_constraint.getSelectedItem());
				constraint = constraintComboBox.getSelectedItem().toString();
			}
		});
		constraintComboBox.setPreferredSize(new Dimension(30, 100));
		add(constraintComboBox, "0, 0");
		
		cond_panel = new ConditionPanel(context);
		cond_panel.setPreferredSize(new Dimension(30, 100));
		add(cond_panel, "0, 1");
		
		/*
		act_panel = new ActionPanel(context);
		add(act_panel, "0, 2");
		*/
		String action_list[] = {
				"Select action", 
				"a1: alert case manager", 
				"a2: alert operation director", 
				"a3: alert managing director",
				"a4: alert case manager with suggestion",
				"a5: alert operation director with suggestion",
				"a6: alert managing director with suggestion",
				"a7: add resource" 
			};
		final ProMComboBox<String> actionComboBox = new ProMComboBox<String>(action_list);
		actionComboBox.setOpaque(false);
		actionComboBox.setSelectedIndex(0);
		actionComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(c_action.getSelectedItem());
				action = actionComboBox.getSelectedItem().toString();
			}
		});
		actionComboBox.setPreferredSize(new Dimension(30, 100));
//		add(actionComboBox, new Float(20));
		add(actionComboBox, "0, 2");
		
		
        TableModel t = new DefaultTableModel() { 
        	String[] column_names = {"Action set", "Constraint", "Action condition", "Action"}; 

            @Override 
            public int getColumnCount() { 
                return column_names.length; 
            } 

            @Override 
            public String getColumnName(int index) { 
                return column_names[index]; 
            } 
        };

		final ProMTable table = new ProMTable(t);
		//table.getTable().addColumn(new TableColumn(3));
		//table.column
		table.setPreferredSize(new Dimension(50, 100));
		add(table, "0, 4");
		
		
		JButton add_button = SlickerFactory.instance().createButton("add");
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(constraint + " and " + action);
				DefaultTableModel model = (DefaultTableModel) table.getTable().getModel();
				String action_set_name = "as" + set_number;
				List<String> action_set = new ArrayList<String>();
				action_set.add(constraint);
				action_set.add(cond_panel.condition);
				action_set.add(action);
				parameters.addActionSet(action_set, action_set_name);
				
				List<String> row = new ArrayList<String>();
				row.add(action_set_name);
				row.add(constraint);
				row.add(cond_panel.condition);
				row.add(action);
				model.addRow(row.toArray());
				
				//reset selections
				constraintComboBox.setSelectedIndex(0);
				if(!cond_panel.checkBox.isSelected()) {
					cond_panel.checkBox.doClick(); 
				}
				cond_panel.aggComboBox.setSelectedIndex(0);
				cond_panel.comparatorComboBox.setSelectedIndex(0);
				cond_panel.valueTextField.setText("insert threshold");
				actionComboBox.setSelectedIndex(0);
				
				set_number+=1;
			}
		});
		add_button.setPreferredSize(new Dimension(30, 100));
		add(add_button, "0, 3");
		
		/*
		final NiceSlider integerSilder = SlickerFactory.instance().createNiceIntegerSlider("Select number ", -10,
				10, parameters.getYourInteger(), Orientation.HORIZONTAL);
		integerSilder.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				parameters.setYourInteger(integerSilder.getSlider().getValue());
			}
		});
		add(integerSilder, "0, 3");
		*/
		//

		/*
		final JCheckBox checkBox = SlickerFactory.instance().createCheckBox("Select Yes/No", false);
		checkBox.setSelected(parameters.isYourBoolean());
		checkBox.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				parameters.setYourBoolean(checkBox.isSelected());
			}

		});
		checkBox.setOpaque(false);
		checkBox.setPreferredSize(new Dimension(100, 30));
		add(checkBox, "0, 2");
		*/
	}
	
	class ConstraintPanel extends JPanel{
		PluginContext context;
		public String constraint = "";
		
		public ConstraintPanel(PluginContext ctx) {
			context = ctx;
			RelativeLayout rl = new RelativeLayout(RelativeLayout.X_AXIS);
			this.setLayout(rl);
//			this.addLabel();
			this.addConstraintComboBox();
		}
		
		public void addLabel() {
			JLabel constraintLabel = SlickerFactory.instance().createLabel("Constraint");
			add(constraintLabel, new Float(20));
		}
		
		public void addConstraintComboBox() {
			String constraint_list[] = {
					"Select constraint", 
					"c1: An order must be delivered in 200", 
					"c2: An availability of item must be checked before picking",
					"c3: An order must be delivered in 100",
					"cp1: Maximum number of ongoing orders is 50", 
					"cp2: There is no resource involved in more than 5 activities" 
				}; 
			final ProMComboBox<String> constraintComboBox = new ProMComboBox<String>(constraint_list);
			constraintComboBox.setSelectedIndex(0);
			constraintComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//System.out.println(c_constraint.getSelectedItem());
					constraint = constraintComboBox.getSelectedItem().toString();
				}
			});
			add(constraintComboBox, new Float(20));
		}
		
	}
	
	class ConditionPanel extends JPanel{
		PluginContext context;
		public String action_condition = "";
		public String cond_aggregator;
		public String cond_comparator;
		public String cond_value;
		public String condition;
		public JCheckBox checkBox;
		public ProMComboBox<String> aggComboBox;
		public ProMComboBox<String> comparatorComboBox;
		public ProMTextField valueTextField;
//		ProMComboBox<String> aggComboBox;
		public ConditionPanel(PluginContext ctx) {
			context = ctx;
			Color prom_color = new Color(238, 238, 238);
			this.setOpaque(false);
			this.setBackground(prom_color);
			this.setForeground(prom_color);
			RelativeLayout rl = new RelativeLayout(RelativeLayout.X_AXIS);
			this.setLayout(rl);
			this.addCheckBox();
//			this.addLabel();
			this.addAggComboBox();
			this.addComparatorComboBox();
			this.addValueTextField();
		}
		
		public void addLabel() {
			JLabel conditionLabel = SlickerFactory.instance().createLabel("Action condition");
			add(conditionLabel, new Float(20));
		}
		
		public void addCheckBox() {
			checkBox = SlickerFactory.instance().createCheckBox("with action condition?", false);
			checkBox.setSelected(true);

			checkBox.addActionListener(new ActionListener() {
			
			
				public void actionPerformed(ActionEvent e) {
					if(checkBox.isSelected()) {
						aggComboBox.enable();
						comparatorComboBox.enable();
						valueTextField.enable();
					}else {
						aggComboBox.disable();
						comparatorComboBox.disable();
						valueTextField.disable();
						condition=null;
					}
				}
			});

			checkBox.setOpaque(false);
			add(checkBox, new Float(20));
		}
		
		public void addAggComboBox() {
			String agg_list[] = { 
					"Select aggregator",
					"ratio",
					"count",
					"mean",
					"median" };
			aggComboBox = new ProMComboBox<String>(agg_list);
			aggComboBox.setSelectedIndex(0);
			aggComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cond_aggregator = aggComboBox.getSelectedItem().toString();
					condition = cond_panel.cond_aggregator 
							+ "," + cond_panel.cond_comparator 
							+ "," + cond_panel.cond_value;
				}
			});
			add(aggComboBox, new Float(20)); 
		}
		
		public void addComparatorComboBox() {
			String agg_list[] = { 
					"Select comparator",
					">",
					"=",
					"<"};
			comparatorComboBox = new ProMComboBox<String>(agg_list);
			comparatorComboBox.setSelectedIndex(0);
			comparatorComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cond_comparator = comparatorComboBox.getSelectedItem().toString();
					condition = cond_panel.cond_aggregator 
							+ "," + cond_panel.cond_comparator 
							+ "," + cond_panel.cond_value;
				}
			});
			add(comparatorComboBox, new Float(20));
		}
		
		public void addValueTextField() {
			valueTextField = new ProMTextField();
			valueTextField.setText("insert threshold");
			valueTextField.setEditable(true);
			valueTextField.getDocument().addDocumentListener(new DocumentListener() {
				  public void changedUpdate(DocumentEvent e) {
					    cond_value = valueTextField.getText();
					    condition = cond_panel.cond_aggregator 
								+ "," + cond_panel.cond_comparator 
								+ "," + cond_panel.cond_value;
					  }
					  public void removeUpdate(DocumentEvent e) {
						  cond_value = valueTextField.getText();
						  condition = cond_panel.cond_aggregator 
									+ "," + cond_panel.cond_comparator 
									+ "," + cond_panel.cond_value;
					  }
					  public void insertUpdate(DocumentEvent e) {
						  cond_value = valueTextField.getText();
						  condition = cond_panel.cond_aggregator 
									+ "," + cond_panel.cond_comparator 
									+ "," + cond_panel.cond_value;
					  }
			});
//			valueTextField.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					//System.out.println(c_condition.getSelectedItem());
//					cond_value = valueTextField.getText();
//					System.out.println(cond_value);
//				}
//			});
			add(valueTextField, new Float(20));
		}	
	} 
	
	class ActionPanel extends JPanel{
		PluginContext context;
		public String action = "";
		public ActionPanel(PluginContext ctx) {
			context = ctx;
			RelativeLayout rl = new RelativeLayout(RelativeLayout.X_AXIS);
			this.setLayout(rl);
//			this.addLabel();
			this.addActionComboBox();
		}
		
		public void addLabel() {
			JLabel constraintLabel = SlickerFactory.instance().createLabel("Action");
			add(constraintLabel, new Float(20));
		}
		
		public void addActionComboBox() {
			String action_list[] = {
					"Select action", 
					"a1: alert case manager", 
					"a2: alert operation director", 
					"a3: alert managing director",
					"a4: alert case manager with suggestion",
					"a5: alert operation director with suggestion",
					"a6: alert managing director with suggestion",
					"a7: add resource"
				};
			final ProMComboBox<String> actionComboBox = new ProMComboBox<String>(action_list);
			actionComboBox.setSelectedIndex(0);
			actionComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//System.out.println(c_action.getSelectedItem());
					action = actionComboBox.getSelectedItem().toString();
				}
			});
			add(actionComboBox, new Float(20));
		}
		
	}
}
