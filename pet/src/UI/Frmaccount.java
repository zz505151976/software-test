package UI;

import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;
import Model.*;
import PetGO.PetUtil;
import Util.BaseException;
public class Frmaccount extends JDialog implements ActionListener{
	private JPanel workjp = new JPanel();
	private JPanel buttonPane = new JPanel();
	JButton okButton = new JButton("确认");
	public Frmaccount(Frame f, String s, boolean b) {
		super(f, s, b);
		//个人信息
		BeanCustomer bc=BeanCustomer.currentLogincustomer;
		this.setLayout(new BorderLayout());
		workjp.setLayout(new GridLayout(8,2));
		JLabel label1 = new JLabel("编号：");
		JLabel label1z = new JLabel(String.valueOf(bc.getCustomer_id()));
		workjp.add(label1);
		workjp.add(label1z);
		
		JLabel label2 = new JLabel("姓名：");
		JLabel label2z =new JLabel(bc.getCustomer_name());
		workjp.add(label2);
		workjp.add(label2z);
		
		JLabel label3 = new JLabel("邮箱：");
		JLabel label3z = new JLabel(bc.getMail());
		workjp.add(label3);
		workjp.add(label3z);
		
		JLabel label4 = new JLabel("地址：");
		JLabel label4z = new JLabel(bc.getAddress());
		workjp.add(label4);
		workjp.add(label4z);
		
		JLabel label5 = new JLabel("手机号：");
		JLabel label5z = new JLabel(bc.getPhone());
		workjp.add(label5);
		workjp.add(label5z);
		
		JLabel label6 = new JLabel("其他联系方式：");
		JLabel label6z = new JLabel(bc.getOther_contact());
		workjp.add(label6);
		workjp.add(label6z);
		
		JLabel label7=new JLabel("余额：");
		JLabel label7z=new JLabel(String.valueOf(bc.getMoney()));
		workjp.add(label7);
		workjp.add(label7z);
		
		this.add(workjp,BorderLayout.CENTER);
		
		this.okButton.addActionListener(this);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPane.add(this.okButton);
		this.add(buttonPane,BorderLayout.SOUTH);
		
		this.setTitle("个人信息");
		this.setLocation(600, 300);
		this.setSize(300,320);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.okButton) {
			this.setVisible(false);
		}
	}
}
