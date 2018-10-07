package UI;
import Util.BaseException;
import Model.*;
import PetGO.PetUtil;
import ITF.*;
import Control.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class FrmLogin extends JFrame implements ActionListener{
	private JButton jb1=new JButton("登录");
	private JButton jb2=new JButton("注册");
	private JButton jb3=new JButton("退出");
	
	private JRadioButton jrb1=new JRadioButton("职员");
	private JRadioButton jrb2=new JRadioButton("客户");
	
	private JPanel jp1=new JPanel();	
	private JPanel jp2=new JPanel();
	private JPanel jp3=new JPanel();
	private JPanel jp4=new JPanel();
	
	private JTextField jtf1=new JTextField(15);
	
	private JLabel jlb1=new JLabel("账号：");
	private JLabel jlb2=new JLabel("密码：");
	private JLabel jlb3=new JLabel("身份：");
	
	private JPasswordField jpf1=new JPasswordField(15);
	
	private ButtonGroup bg=new ButtonGroup();
	public static void main(String [] args) {
		FrmLogin login=new FrmLogin();
	}
	public FrmLogin(){
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		bg.add(jrb1);
		bg.add(jrb2);
		jrb2.setSelected(true);//先显示在客户上
		
		jp1.add(jlb1);//账号
		jp1.add(jtf1);
		
		jp2.add(jlb2);//密码
		jp2.add(jpf1);
		
		jp3.add(jlb3);//身份
		jp3.add(jrb1);
		jp3.add(jrb2);
		
		jp4.add(jb1);//按钮
		jp4.add(jb2);
		jp4.add(jb3);
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		
		this.setLayout(new GridLayout(4,1));//4行1列
		this.setTitle("宠物服务系统");
		this.setSize(350,200);
		this.setLocation(600, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(true);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="登录") {
			if(jrb1.isSelected()) {//如果选中职员
				//职员认证,登录认证方法
				String opid=this.jtf1.getText();
				System.out.println(opid);
				String oppwd=new String(this.jpf1.getPassword());
				try {
					BeanOperator.currentLoginoperator=PetUtil.operatorManage.oplogin(opid, oppwd);
				}catch(BaseException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FrmOperatorMain frop=new FrmOperatorMain();
				frop.setVisible(true);
				this.setVisible(false);
			}else if(jrb2.isSelected()) {//如果选中客户
				//客户认证，登录认证方法
				String customerid=this.jtf1.getText();
				String customerpwd=new String(this.jpf1.getPassword());
				try {
					BeanCustomer.currentLogincustomer=PetUtil.customerManage.login(customerid,customerpwd);
				}catch(BaseException ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				FrmCustomerMain frcustom=new FrmCustomerMain();
				frcustom.setVisible(true);
				this.setVisible(false);
			}
		}else if (e.getActionCommand()=="注册") {
			//打开注册窗口
			FrmRegister fregister=new FrmRegister();
			fregister.setVisible(true);
		}else if(e.getActionCommand()=="退出") {
			System.exit(0);
		}
		
	}
	public void clear() {//清除输出框内容
		jtf1.setText("");
		jpf1.setText("");
	}
}
