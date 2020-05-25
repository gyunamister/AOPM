package org.processmining.AOPM.dialogs;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.processmining.AOPM.models.AEConfig;
import org.processmining.AOPM.models.ActionFormula;
import org.processmining.AOPM.models.CMConfig;
import org.processmining.AOPM.models.ConstraintFormula;
import org.processmining.AOPM.models.ContextDescription;
import org.processmining.AOPM.models.TimeWindow;
import org.processmining.AOPM.parameters.YourParameters;
import org.processmining.AOPM.parser.ADLLexer;
import org.processmining.AOPM.parser.ADLListenerImpl;
import org.processmining.AOPM.parser.ADLParser;
import org.processmining.AOPM.parser.CDLLexer;
import org.processmining.AOPM.parser.CDLListenerImpl;
import org.processmining.AOPM.parser.CDLParser;
import org.processmining.contexts.uitopia.UIPluginContext;
import org.processmining.framework.util.ui.widgets.ProMComboBox;
import org.processmining.framework.util.ui.widgets.ProMTable;

import com.fluxicon.slickerbox.factory.SlickerFactory;

import info.clearthought.layout.TableLayout;
import info.clearthought.layout.TableLayoutConstants;

public class InputDialog extends JPanel {
	public int constrNumber=1;
	public int actionNumber=1;
	Map<String, Map<String, Object>> constraintMap = new LinkedHashMap<String, Map<String, Object>>();
	Map<String, ConstraintFormula> cfMap;
//	Map<String, Map<String, Object>> cmConfig = new LinkedHashMap<String, Map<String, Object>>();
	CMConfig cmConfig = new CMConfig(10000);
	Map<String, Map<String, Object>> actionMap = new LinkedHashMap<String, Map<String, Object>>();
	Map<String, ActionFormula> afMap;
//	Map<String, Map<String, Object>> aeConfig = new LinkedHashMap<String, Map<String, Object>>();
	AEConfig aeConfig = new AEConfig(10000);
	public String constraint;
	public String action;
	public ConstraintConfPanel constraintPanel;
	public ActionConfPanel actionPanel;
	public ProMTable constrTable;
	public ProMTable actionTable;
	public YourParameters parameters;

	private static final long serialVersionUID = -60087716353524468L;

	/**
	 * The JPanel that allows the user to set (a subset of) the parameters.
	 */

	public ParseTree readCDL()  {
		String content = "File not found";
		try {
			content = new Scanner(new File("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/cdlExample.cdl")).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println( "CDL File:\n" + content + "\n\n");

		ANTLRInputStream input = new ANTLRInputStream( content );

		CDLLexer lexer = new CDLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		CDLParser parser = new CDLParser(tokens);

		ParseTree tree = parser.constraint();
		return tree;
	}

	public ParseTree readADL()  {
		String content = "File not found";
		try {
			content = new Scanner(new File("/Users/GYUNAM/Documents/AOPM/src/org/processmining/AOPM/adlExample.adl")).useDelimiter("\\Z").next();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println( "ADL File:\n" + content + "\n\n");

		ANTLRInputStream input = new ANTLRInputStream( content );

		ADLLexer lexer = new ADLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		ADLParser parser = new ADLParser(tokens);

		ParseTree tree = parser.action();
		return tree;
	}

	public Map<String, ConstraintFormula> gencfMap(Map<String, Map<String, Object>> constraintMap) {
		Map<String, ConstraintFormula> cfMap = new LinkedHashMap<String, ConstraintFormula>();
		for(String cfName:constraintMap.keySet()) {
			Map<String, Object> constraint = constraintMap.get(cfName);
			Set<String> procSet = new HashSet<String>();
			Set<String> actSet = new HashSet<String>();
			Set<String> resSet = new HashSet<String>();
			Map<String,Set<String>> omap = new LinkedHashMap<String,Set<String>>();
			Map<String,Set<String>> vmap = new LinkedHashMap<String,Set<String>>();
			for(String key: constraint.keySet()) {
				if(key.equals("Proc")) {
					procSet.addAll((Set) constraint.get("Proc"));
				}else if(key.equals("Act")) {
					actSet.addAll((Set) constraint.get("Act"));
				}else if(key.equals("Res")) {
					resSet.addAll((Set) constraint.get("Res"));
				}else if(key.equals("omap")) {
					Map<String, Set<String>> objectMapping = (Map) constraint.get("omap");
					for(String ocName:  objectMapping.keySet()) {
						omap.put(ocName, objectMapping.get(ocName));
					}
				}else if(key.equals("vmap")) {
					Map<String, Set<String>> attrMapping = new LinkedHashMap<String, Set<String>>();
					for(String attrName:  attrMapping.keySet()) {
						vmap.put(attrName, attrMapping.get(attrName));
					}
				}
			}
			ContextDescription ctxdesc = new ContextDescription(procSet,actSet,resSet,omap,vmap);
			ConstraintFormula cf = new ConstraintFormula(cfName, ctxdesc, (String) constraint.get("filter"), (String) constraint.get("evaluation"));
			cfMap.put(cfName,cf);
		}
		return cfMap;
	}

	public Map<String, ActionFormula> genafMap(Map<String, Map<String, Object>> actionMap) {
		Map<String, ActionFormula> afMap = new LinkedHashMap<String, ActionFormula>();
		for(String afName:actionMap.keySet()) {
			Map<String, Object> action = actionMap.get(afName);
			Map<String, Set<String>> ccvDescription = new LinkedHashMap<String, Set<String>>();
			Map<String, String> pmap = new LinkedHashMap<String, String>();
			for(String key: action.keySet()) {
				if(key.equals("cf")) {
					Set<String> procSet = new HashSet<String>();
					procSet.addAll((Set) action.get(key));
					ccvDescription.put(key, procSet);
				}else if(key.equals("proc")) {
					Set<String> procSet = new HashSet<String>();
					procSet.addAll((Set) action.get(key));
					ccvDescription.put(key, procSet);
				}else if(key.equals("act")) {
					Set<String> actSet = new HashSet<String>();
					actSet.addAll((Set) action.get(key));
					ccvDescription.put(key, actSet);
				}else if(key.equals("res")) {
					Set<String> resSet = new HashSet<String>();
					resSet.addAll((Set) action.get(key));
					ccvDescription.put(key, resSet);
				}else if(key.contains("object@")) {
					Set<String> objSet = new HashSet<String>();
					objSet.addAll((Set) action.get(key));
					ccvDescription.put(key.split("@")[1], objSet);
				}else if(key.contains("parameter@")) {
					pmap.put(key.split("@")[1], (String) action.get(key));
				}
			}
			ActionFormula af = new ActionFormula(afName, ccvDescription, (String) action.get("assessment"), (String) action.get("operation"), pmap);
			afMap.put(afName,af);
		}
		return afMap;
	}

	public InputDialog(UIPluginContext context,
			final YourParameters parameters) {
		//read CDL
		this.parameters = parameters;
		ParseTree cdlTree = this.readCDL();
		CDLListenerImpl cdlListener = new CDLListenerImpl(this.constraintMap);
		ParseTreeWalker cdlWalker = new ParseTreeWalker();
		cdlWalker.walk(cdlListener, cdlTree);
		this.cfMap = this.gencfMap(this.constraintMap);
		System.out.println(this.cfMap);

		//read ADL
		ParseTree adlTree = this.readADL();
		ADLListenerImpl adlListener = new ADLListenerImpl(this.actionMap);
		ParseTreeWalker adlWalker = new ParseTreeWalker();
		adlWalker.walk(adlListener, adlTree);
		this.afMap = this.genafMap(this.actionMap);


		double size[][] = { { TableLayoutConstants.FILL }, { 30, 30, 30, 30, TableLayoutConstants.FILL, 30, 30, 30, 30, TableLayoutConstants.FILL } };
		setLayout(new TableLayout(size));

		this.constrTable = this.genConstraintTable();
		this.constrTable.setPreferredSize(new Dimension(50, 100));
		add(this.constrTable, "0, 4");

		JLabel cLabel = this.genLabel("Constraint Monitoring");
		cLabel.setPreferredSize(new Dimension(15, 100));
		add(cLabel,"0, 0");

		//Part1: Constraint selection
		ProMComboBox<String> constraintComboBox = this.genConstraintComboBox();
		add(constraintComboBox, "0, 1");

		//Part2: Constraint specification
		this.constraintPanel = new ConstraintConfPanel(context,constrTable);
		this.constraintPanel.setPreferredSize(new Dimension(15, 100));
		add(this.constraintPanel, "0, 2");

		JButton constrAddButton = this.genConstAddButton();
		add(constrAddButton,"0, 3");

		this.actionTable = this.genActionTable();
		this.actionTable.setPreferredSize(new Dimension(15, 100));
		add(this.actionTable, "0, 9");

		JLabel aLabel = this.genLabel("Action Engine");
		aLabel.setPreferredSize(new Dimension(15, 100));
		add(aLabel,"0, 5");

		//Part3: Action selection
		ProMComboBox<String> actionComboBox = this.genActionComboBox();
		add(actionComboBox, "0, 6");

		//Part4: Action specification
		this.actionPanel = new ActionConfPanel(context, actionTable);
		this.actionPanel.setPreferredSize(new Dimension(15, 100));
		add(this.actionPanel, "0, 7");

		JButton actionAddButton = this.genActionAddButton();
		add(actionAddButton,"0, 8");
	}

	public JLabel genLabel(String title) {
		JLabel label = SlickerFactory.instance().createLabel(title);
		label.setFont(new Font("Serif", Font.BOLD, 14));
		return label;
	}

	public ProMComboBox<String> genConstraintComboBox() {
		List<String> constraintList = new ArrayList<String>();
		constraintList.add("Select constraint");
		for(String constraintName:this.constraintMap.keySet()) {
			constraintList.add(constraintName);
		}

		final ProMComboBox<String> constraintComboBox = new ProMComboBox<String>(constraintList);
		constraintComboBox.setSelectedIndex(0);
		constraintComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				constraint = constraintComboBox.getSelectedItem().toString();
			}
		});
		constraintComboBox.setPreferredSize(new Dimension(15, 100));
		return constraintComboBox;
	}

	public ProMComboBox<String> genActionComboBox() {
		List<String> actionList = new ArrayList<String>();
		actionList.add("Select action");
		for(String actionName:actionMap.keySet()) {
			actionList.add(actionName);
		}

		final ProMComboBox<String> actionComboBox = new ProMComboBox<String>(actionList);
		actionComboBox.setSelectedIndex(0);
		actionComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action = actionComboBox.getSelectedItem().toString();
			}
		});
		actionComboBox.setPreferredSize(new Dimension(15, 100));
		return actionComboBox;
	}

	public ProMTable genActionTable() {
		TableModel actionTableModel = new DefaultTableModel() {
        	String[] column_names = {"Action ID", "Action Description", "Interval", "Offset", "Time"};

            @Override
            public int getColumnCount() {
                return column_names.length;
            }

            @Override
            public String getColumnName(int index) {
                return column_names[index];
            }
        };

		ProMTable actionTable = new ProMTable(actionTableModel);
		return actionTable;
	}

	public ProMTable genConstraintTable() {
		TableModel constrTableModel = new DefaultTableModel() {
        	String[] column_names = {"Constraint ID", "Constraint Description", "Interval", "Offset", "Time Window"};

            @Override
            public int getColumnCount() {
                return column_names.length;
            }

            @Override
            public String getColumnName(int index) {
                return column_names[index];
            }
        };

		ProMTable constrTable = new ProMTable(constrTableModel);
		return constrTable;
	}

	public JButton genConstAddButton() {
		JButton constraintAddButton = SlickerFactory.instance().createButton("Add");
		constraintAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) constrTable.getTable().getModel();
				String cfName = "cf" + constrNumber;

				List<String> row = new ArrayList<String>();
				row.add(cfName);
				row.add(constraint);
				row.add(Integer.toString(constraintPanel.timeInterval));
				row.add(Integer.toString(constraintPanel.timeOffset));
				row.add(constraintPanel.timeWindow);
				model.addRow(row.toArray());
//				Map<String,Object> configuration = new LinkedHashMap<String,Object>();
//				configuration.put("monitoringInterval", constraintPanel.timeInterval);
//				configuration.put("monitoringOffset", constraintPanel.timeOffset);
				TimeWindow tw = new TimeWindow(Integer.parseInt(constraintPanel.timeWindow.split(",")[0]), Integer.parseInt(constraintPanel.timeWindow.split(",")[1]));
//				configuration.put("monitoringTimeWindow", tw);
				cmConfig.addConstraint(cfMap.get(constraint), constraintPanel.timeOffset, constraintPanel.timeInterval, tw);
				constrNumber+=1;
				System.out.println(cmConfig);
				parameters.updateCMConf(cmConfig);
			}
		});
		return constraintAddButton;
	}

	public JButton genActionAddButton() {
		JButton actionAddButton = SlickerFactory.instance().createButton("Add");
		actionAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) actionTable.getTable().getModel();
				String afName = "af" + actionNumber;

				List<String> row = new ArrayList<String>();
				row.add(afName);
				row.add(action);
				row.add(Integer.toString(actionPanel.timeInterval));
				row.add(Integer.toString(actionPanel.timeOffset));
				row.add(actionPanel.timeWindow);
				model.addRow(row.toArray());
//				actionComboBox.setSelectedIndex(0);
//				aeConfig.put(action, afSet.get(action));
//				Map<String,Object> configuration = new LinkedHashMap<String,Object>();
//				configuration.put("monitoringInterval", actionPanel.timeInterval);
//				configuration.put("monitoringOffset", actionPanel.timeOffset);
				TimeWindow tw = new TimeWindow(Integer.parseInt(actionPanel.timeWindow.split(",")[0]), Integer.parseInt(actionPanel.timeWindow.split(",")[1]));
//				configuration.put("monitoringTimeWindow", tw);
//				aeConfig.get(action).put("configuration", configuration);
				aeConfig.addAction(afMap.get(action), actionPanel.timeOffset, actionPanel.timeInterval, tw);
				actionNumber+=1;
				System.out.println(aeConfig);
				parameters.updateAEConf(aeConfig);
			}
		});
		return actionAddButton;
	}

	public void setActiveAction() {

	}

}
