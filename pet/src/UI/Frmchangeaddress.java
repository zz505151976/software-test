package UI;
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;
import Model.*;
import PetGO.PetUtil;
import Util.BaseException;
public class Frmchangeaddress extends JDialog implements ActionListener{
	//修改收货地址
	private JLabel title = new JLabel("地址修改");
	private JLabel newaddresslb = new JLabel("请输入新地址：");
	
	private JButton btnOk = new JButton("修改");
	private JButton btnCancel = new JButton("取消");
	
	private JTextField newaddresstf =new JTextField(20);
	
	private JPanel topjp=new JPanel();
	private JPanel workjp=new JPanel();
	private JPanel downjp=new JPanel();
	
	public Frmchangeaddress(Frame f,String s,boolean b) {
		super(f, s, b);
		topjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		topjp.add(title);
		topjp.setPreferredSize(new Dimension(0, 50));
		this.getContentPane().add(topjp,BorderLayout.NORTH);
		
		workjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		workjp.add(newaddresslb);
		workjp.add(newaddresstf);
		this.getContentPane().add(workjp,BorderLayout.CENTER);
		
		downjp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		downjp.add(btnOk);
		downjp.add(btnCancel);
		this.getContentPane().add(downjp,BorderLayout.SOUTH);
		
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		this.setLocation(600, 300);
		this.setSize(350,180);
		this.setResizable(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
		}
		else if(e.getSource()==this.btnOk) {
			try {
				String newaddress=this.newaddresstf.getText();
				PetUtil.customerManage.changeaddress(BeanCustomer.currentLogincustomer, newaddress);
				this.setVisible(false);
			}catch(BaseException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
