package UI;
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;
import Model.*;
import PetGO.PetUtil;
import Util.BaseException;
public class Frmsearchmoney extends JDialog implements ActionListener{
	private JPanel topjp=new JPanel();
	private JLabel title=new JLabel();
	
	private JPanel workjp=new JPanel();
	private JLabel ownmoneylb=new JLabel("我的余额");
	
	
	private JPanel downjp=new JPanel();
	private JButton btnok=new JButton("确定");
	
	public Frmsearchmoney(Frame f,String s,boolean b) {
		super(f, s, b);
		JLabel ownmoneytf=new JLabel(String.valueOf(BeanCustomer.currentLogincustomer.getMoney()));
		
		topjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		topjp.add(title);
		this.getContentPane().add(topjp, BorderLayout.NORTH);
		
		workjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		workjp.add(ownmoneylb);
		workjp.add(ownmoneytf);
		this.getContentPane().add(workjp,BorderLayout.CENTER);
		
		downjp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		downjp.add(btnok);
		btnok.addActionListener(this);
		this.getContentPane().add(downjp, BorderLayout.SOUTH);
		
		this.setLocation(600, 300);
		this.setSize(350,180);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnok) {
			this.setVisible(false);
		}
	}
}
