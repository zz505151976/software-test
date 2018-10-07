package UI;
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;
import Model.*;
import PetGO.PetUtil;
import Util.BaseException;
public class FrmRegister extends JFrame implements ActionListener{//用户注册,登录界面打开
	private JLabel lbphone=new JLabel("手机号："); //使用手机号注册,标签
	private JLabel lbpwd=new JLabel("密码：");
	private JLabel lbacpwd=new JLabel("确认密码：");
	private JLabel lbname=new JLabel("姓名：");
	private JLabel lbmail=new JLabel("邮箱：");
	private JLabel lbother=new JLabel("其他联系方式：");
	private JLabel lbaddress=new JLabel("地址：");
	private JLabel lbex=new JLabel("(手机号将用作登录账号)");
	
	private JTextField txtphone=new JTextField(20);//手机号
	private JTextField txtname=new JTextField(20);//姓名
	private JTextField txtmail=new JTextField(20);//邮箱
	private JTextField txtother=new JTextField(20);//其他联系方式
	private JTextField txtaddress=new JTextField(20);//地址
	
	private JPasswordField txtpwd=new JPasswordField(20);//密码
	private JPasswordField txtacpwd=new JPasswordField(20);//确认密码
	
	private JRadioButton jrb=new JRadioButton("同意服务条款并注册");
	
	private JButton jbac=new JButton("注册");//按钮
	
	private JPanel jp=new JPanel();
	
	public FrmRegister() {
		jbac.addActionListener(this);
		lbphone.setBounds(30,30,60,25);
		txtphone.setBounds(95,30,120,25);
		lbpwd.setBounds(30,60,60,25);
		txtpwd.setBounds(95,60,120,25);
		lbacpwd.setBounds(30,90,60,25);
		txtacpwd.setBounds(95,90,120,25);
		lbname.setBounds(30, 120,60,120);
		txtname.setBounds(95,120,120,25);
		lbmail.setBounds(30,150,60,25);
		txtmail.setBounds(95,150,120,25);
		lbother.setBounds(30,180,60,25);
		txtother.setBounds(95,180,120,25);
		lbaddress.setBounds(30,210,60,25);
		txtaddress.setBounds(30,210,120,25);
		jrb.setBounds(60,250,40,25);
		jbac.setBounds(60,275,60,25);
		
		jp.add(lbphone);
		jp.add(txtphone);
		jp.add(lbpwd);
		jp.add(txtpwd);
		jp.add(lbacpwd);
		jp.add(txtacpwd);
		jp.add(lbname);
		jp.add(txtname);
		jp.add(lbmail);
		jp.add(txtmail);
		jp.add(lbother);
		jp.add(txtother);
		jp.add(lbaddress);
		jp.add(txtaddress);
		jp.add(jrb);
		jp.add(jbac);
		this.add(jp);
		this.setSize(260,400);
		this.setTitle("用户注册");
		this.setLocation(600,300);
		this.setResizable(false);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.jbac) {
			//用户注册确认的方法
			if(jrb.isSelected()) {
				//验证方法
				String phone=this.txtphone.getText();
				String pwd=new String(this.txtpwd.getPassword());
				String acpwd=new String(this.txtacpwd.getPassword());
				String name=this.txtname.getText();
				String mail=this.txtmail.getText();
				String address=this.txtaddress.getText();
				String other=this.txtother.getText();
				try {
					BeanCustomer bcs=PetUtil.customerManage.reg(phone, pwd, acpwd, name, mail, other, address);
					this.setVisible(false);
				}catch(BaseException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
			}
			else {
				JOptionPane.showMessageDialog(null,"请完整填写信息","提示",JOptionPane.ERROR_MESSAGE); 
			}
		}
	}
}
