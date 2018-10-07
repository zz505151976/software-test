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
import Model.BeanServe;
import Model.BeanServeType;
import PetGO.PetUtil;
import Util.BaseException;
public class Frmcuslookserve extends JDialog implements ActionListener{
	//客户查看自己的预约
	DefaultTableModel tabModel=new DefaultTableModel();
	private JTable dataTable=new JTable(tabModel); 
	
	private Object tblAdvanTitle[]=BeanAdvanceorder.advantableTitles;
	private Object tblAdvanData[][];
	List<BeanAdvanceorder> allownadvan=null;
	
	private JPanel topjp=new JPanel();
	private JButton btncancel=new JButton("取消预约");
	
	private JPanel downjp=new JPanel();
	private JButton btnok=new JButton("确定");
	private void reloadallownadvanTable() {
		//查看自己的预约
		BeanCustomer customer=BeanCustomer.currentLogincustomer;
		try {
			allownadvan= PetUtil.advanceManage.showownadvance(customer);
		}catch (BaseException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblAdvanData=  new Object[allownadvan.size()][BeanAdvanceorder.advantableTitles.length];
		for(int i=0;i<allownadvan.size();i++){
			for(int j=0;j<BeanAdvanceorder.advantableTitles.length;j++)
				tblAdvanData[i][j]=allownadvan.get(i).getCell(j);
		}
		tabModel.setDataVector(tblAdvanData,tblAdvanTitle);
		this.dataTable.validate();
		this.dataTable.repaint();	
	} 
	public Frmcuslookserve(Frame f,String s,boolean b) {
		super(f,s,b);
		topjp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btncancel.addActionListener(this);
		topjp.add(btncancel);
		this.getContentPane().add(topjp,BorderLayout.NORTH);
		
		downjp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnok.addActionListener(this);
		downjp.add(btnok);
		this.getContentPane().add(downjp,BorderLayout.SOUTH);
		
		this.getContentPane().add(new JScrollPane(this.dataTable),BorderLayout.CENTER);
		this.reloadallownadvanTable();
		
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnok)
			this.setVisible(false);
		else if(e.getSource()==btncancel) {
			//取消预约
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择服务", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				PetUtil.advanceManage.canceladvance(allownadvan.get(i).getAdvance_id());
				this.reloadallownadvanTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
