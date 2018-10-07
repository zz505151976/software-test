package UI;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Model.BeanAdvanceorder;
import Model.BeanCustomer;
import Model.BeanOrder;
import Model.BeanServe;
import Model.BeanServeType;
import PetGO.PetUtil;
import Util.BaseException;
public class Frmcusorder extends JDialog implements ActionListener{
	//顾客查看自己的订单
	DefaultTableModel tabModel=new DefaultTableModel();
	private JTable dataTable=new JTable(tabModel); 
	
	private Object tblorderTitle[]=BeanOrder.ordertableTitles;
	private Object tblorderData[][];
	List<BeanOrder> allownorder=null;
	
	private JPanel topjp=new JPanel();
	private JButton btncancel=new JButton("取消订单");
	private JButton btnready=new JButton("签收");
	
	private JPanel downjp=new JPanel();
	private JButton btnok=new JButton("确定");
	
	private void reloadallownorderTable() {
		//查看自己的订单
		BeanCustomer customer=BeanCustomer.currentLogincustomer;
		try {
			allownorder= PetUtil.orderManage.showallorderbycus(customer);
		}catch (BaseException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblorderData=  new Object[allownorder.size()][BeanOrder.ordertableTitles.length];
		for(int i=0;i<allownorder.size();i++){
			for(int j=0;j<BeanOrder.ordertableTitles.length;j++)
				tblorderData[i][j]=allownorder.get(i).getCell(j);
		}
		tabModel.setDataVector(tblorderData,tblorderTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	} 
	public Frmcusorder(Frame f,String s,boolean b) {
		super(f,s,b);
		btncancel.addActionListener(this);
		btnready.addActionListener(this);
		topjp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		topjp.add(btncancel);
		topjp.add(btnready);
		this.getContentPane().add(topjp, BorderLayout.NORTH);
		
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		this.reloadallownorderTable();
		downjp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnok.addActionListener(this);
		downjp.add(btnok);
		this.getContentPane().add(downjp, BorderLayout.SOUTH);
		
		
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnok)
			this.setVisible(false);
		else if(e.getSource()==btncancel) {
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择订单", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				PetUtil.orderManage.cancelorder(allownorder.get(i).getOrder_id());
				this.reloadallownorderTable();
			}catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==btnready) {
			//订单签收
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择订单", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				PetUtil.orderManage.readyreceive(allownorder.get(i).getOrder_id());
				this.reloadallownorderTable();
			}catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
