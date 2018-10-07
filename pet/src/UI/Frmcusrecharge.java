package UI;

import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
public class Frmcusrecharge extends JDialog implements ActionListener{
	private JPanel topjp =new JPanel();
	private JLabel title=new JLabel("我要充值");
	
	private JPanel workjp=new JPanel();
	private JLabel moneycountlb=new JLabel("请输入充值余额");
	private JTextField moneycounttf=new JTextField(20);
	private JPanel downjp=new JPanel();
	private JButton btnok=new JButton("确定");
	private JButton btncancel=new JButton("取消");
	public Frmcusrecharge(Frame f,String s,boolean b) {
		super(f, s, b);
		topjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		topjp.add(title);
		topjp.setPreferredSize(new Dimension(0, 50));
		this.getContentPane().add(topjp,BorderLayout.NORTH);
		
		workjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		workjp.add(moneycountlb);
		workjp.add(moneycounttf);
		this.getContentPane().add(workjp, BorderLayout.CENTER);
		
		downjp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnok.addActionListener(this);
		btncancel.addActionListener(this);
		downjp.add(btnok);
		downjp.add(btncancel);
		this.getContentPane().add(downjp, BorderLayout.SOUTH);
		
		this.setLocation(600, 300);
		this.setSize(350,180);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btncancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnok) {
			double money=Double.parseDouble(moneycounttf.getText());
			try {
				PetUtil.customerManage.addmoney(BeanCustomer.currentLogincustomer,money);
				this.setVisible(false);
			}catch(BaseException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
