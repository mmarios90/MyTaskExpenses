package com.mytask.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mytask.controller.CRUDController;
import com.mytask.entity.Activity;
import com.mytask.entity.Labor;
import com.mytask.model.ActivityTableModel;
import com.mytask.model.LaborTableModel;
import com.mytask.util.HibernateUtil;
import com.mytask.util.TableCellListener;
import com.mytask.util.TemporaryValues;

public class MyWindow {

	public static void main(String[] args) {

		//////////////////////////////////////////////////////////

		final JFrame frame = new JFrame("Java Application");

		List<Activity> activities = new ArrayList<Activity>();

		final HibernateUtil hibernateUtil = new HibernateUtil();

		try {
			
			final SessionFactory sessionFactory = hibernateUtil.initializeSessionFactory();
			Session session = hibernateUtil.initializeSession(sessionFactory);

			final CRUDController crudController = new CRUDController();
			activities = crudController.fetchRowsActivity(session, "from Activity");

			hibernateUtil.commitTransaction();

			hibernateUtil.closeSession();

			ActivityTableModel activityTM = new ActivityTableModel(activities);
			JTable table = new JTable(activityTM);

			table.getColumnModel().getColumn(0).setMinWidth(0);
			table.getColumnModel().getColumn(0).setMaxWidth(0); // Hide ActivityId
			table.getColumnModel().getColumn(0).setResizable(false); // Avoid revealing it on resize

			table.setCellSelectionEnabled(true);
			ListSelectionModel cellSelectionModel = table.getSelectionModel();
			cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			frame.add(new JScrollPane(table));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);

			// Mouse listener for On click Labor Row retrieval
			table.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1) {

						JTable target = (JTable) e.getSource();
						int row = target.getSelectedRow();

						BigInteger activityIdColumn = (BigInteger) target.getValueAt(row, 0);
						System.out.println("Got :" + activityIdColumn.toString());

						Session session = hibernateUtil.initializeSession(sessionFactory);
						List<Labor> labors = crudController.fetchRowsLaborByActivityId(session,
								"select labor from Labor labor where labor.activityId = :activityIdColumn ",
								activityIdColumn);
						hibernateUtil.commitTransaction();
						System.out.println(labors);
						LaborTableModel laborTM = new LaborTableModel(labors);

						JTable table1 = new JTable(laborTM);

						table1.getColumnModel().getColumn(4).setMinWidth(0);
						table1.getColumnModel().getColumn(4).setMaxWidth(0); // Hide LaborId
						table1.getColumnModel().getColumn(4).setResizable(false); // Avoid revealing it on resize

						table1.getColumnModel().getColumn(0).setMinWidth(0);
						table1.getColumnModel().getColumn(0).setMaxWidth(0); // Hide ActivityId
						table1.getColumnModel().getColumn(0).setResizable(false); // Avoid revealing it on resize

						table1.setCellSelectionEnabled(true);
						ListSelectionModel cellSelectionModel = table1.getSelectionModel();
						cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						frame.add(new JScrollPane(table1), BorderLayout.PAGE_END);
						frame.pack();

						//Cell Listener for Labor Cost Modification
						Action action = new AbstractAction() {
							public void actionPerformed(ActionEvent e) {
								TableCellListener tcl = (TableCellListener) e.getSource();
								System.out.println("Row   : " + tcl.getRow());
								System.out.println("Column: " + tcl.getColumn());
								System.out.println("Old   : " + tcl.getOldValue());
								System.out.println("New   : " + tcl.getNewValue());
								
								Integer activityId = activityIdColumn.intValue();
								int laborId;
								int row1 = tcl.getRow();
								System.out.println(activityIdColumn);
								System.out.println(row1);
								
								if(activityId == 1) {laborId = row1 + 1;}
								else if(activityId == 2) {laborId = row1 + 4;} 
								else {laborId = row1 +7;}
							
								System.out.println(laborId);
								
								BigInteger thisLabor = BigInteger.valueOf(laborId);
								BigDecimal newValue = (BigDecimal) tcl.getNewValue();
								
								Session session = hibernateUtil.initializeSession(sessionFactory);
								
								crudController.SetNewLaborCost(session,
								"update Labor labor set labor.totalCost =:newValue where labor.laborId =:thisLabor",
								thisLabor, newValue);
								
								BigDecimal totalCostSum = crudController.getLaborCostSum(session,
								"select sum(totalCost) from Labor labor where labor.activityId =:activityIdColumn",
								activityIdColumn);
								
								System.out.println("activity's total cost" + totalCostSum);
								
								crudController.setActivityTotalCoast(session, 
								"update Activity activity set activity.totalCost =:totalCostSum where activity.activityId =:activityIdColumn",
								activityIdColumn, totalCostSum);
								
								hibernateUtil.commitTransaction();
								
								table.setModel(activityTM);	
								//frame.setVisible(false); frame.setVisible(true);
								//SwingUtilities.updateComponentTreeUI(frame);
							}
							

						};

						TableCellListener tcl = new TableCellListener(table1, action);
						

					}
				}
				
				
			}); 
			
		} catch (HibernateException e) {
			System.out.println("Hibernate Exception: " + e.getMessage());
		} finally {
			// hibernateUtil.closeSessionFactory();
		}

	}

}
