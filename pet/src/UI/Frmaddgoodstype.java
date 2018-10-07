package UI;

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
import javax.swing.JTextField;

import PetGO.PetUtil;
import Util.BaseException;
public class Frmaddgoodstype extends JDialog implements ActionListener{
	private JPanel topjp=new JPanel();
	private JLabel title = new JLabel("类别添加");
	
	private JPanel workjp=new JPanel();
	private JLabel typeidlb=new JLabel("类别编号");
	private JTextField typeidtf=new JTextField(20);
	private JLabel typenamelb=new JLabel("类别名称");
	private JTextField typenametf=new JTextField(20);
	private JLabel typedeslb=new JLabel("类别描述");
	private JTextField typedestf=new JTextField(200);
	
	private JPanel downjp = new JPanel();
	private JButton btnOk = new JButton("添加");
	private JButton btnno = new JButton("取消");
	public Frmaddgoodstype(Frame f,String s,boolean b)  {
		super(f,s,b);
		topjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		topjp.add(title);
		this.getContentPane().add(topjp,BorderLayout.NORTH);
		
		workjp.setLayout(new GridLayout(3,2,5,5));
		workjp.add(typeidlb);
		workjp.add(typeidtf);
		workjp.add(typenamelb);
		workjp.add(typenametf);
		workjp.add(typedeslb);
		workjp.add(typedestf);
		this.getContentPane().add(workjp,BorderLayout.CENTER);
		
		downjp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		downjp.add(btnOk);
		downjp.add(btnno);
		btnOk.addActionListener(this);
		btnno.addActionListener(this);
		this.getContentPane().add(downjp,BorderLayout.SOUTH);
		
		this.setLocation(600, 300);
		this.setSize(400,220);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnno) {
			this.setVisible(false);
		}
		else if(e.getSource()==btnOk) {
			String id=typeidtf.getText();
			String name=typenametf.getText();
			String des=typedestf.getText();
			try {
				PetUtil.goodsManage.addgoodstype(id,name,des);
				this.setVisible(false);
			}catch(BaseException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
