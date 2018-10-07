package UI;
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;
import Model.*;
import PetGO.PetUtil;
import Util.BaseException;
public class Frmchangepwd extends JDialog implements ActionListener{
	private JLabel title = new JLabel("密码修改");
	private JLabel oldpwd = new JLabel("请输入原密码：");
	private JLabel newpwd = new JLabel("请输入新密码：");
	private JLabel newpwd2 = new JLabel("请再次确认	：");
	
	private JButton btnOk = new JButton("修改");
	private JButton btnCancel = new JButton("取消");
	
	private JPasswordField jpoldpwd = new JPasswordField(20);
	private JPasswordField jpnewpwd = new JPasswordField(20);
	private JPasswordField jpnewpwd2 = new JPasswordField(20);
	
	private JPanel topjp = new JPanel();
	private JPanel workjp = new JPanel();
	private JPanel downjp = new JPanel();
	
	public Frmchangepwd(Frame f, String s, boolean b) {
		super(f, s, b);
		Font font = new Font("黑体",Font.PLAIN,50);
		
		topjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		topjp.add(title);
		this.getContentPane().add(topjp,BorderLayout.NORTH);
		workjp.setLayout(new GridLayout(3,2,5,5));
		workjp.add(oldpwd);
		workjp.add(jpoldpwd);
		workjp.add(newpwd);
		workjp.add(jpnewpwd);
		workjp.add(newpwd2);
		workjp.add(jpnewpwd2);
		this.getContentPane().add(workjp,BorderLayout.CENTER);
		downjp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		downjp.add(btnOk);
		downjp.add(btnCancel);
		this.getContentPane().add(downjp,BorderLayout.SOUTH);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		this.setLocation(600, 300);
		this.setSize(300,160);
		this.setResizable(false);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnCancel) {
			System.out.println("Ok");
			this.setVisible(false);
		}
		else if(e.getSource()==btnOk) {
			try {
				String oldpwd=new String(this.jpoldpwd.getPassword());
				String newpwd=new String(this.jpnewpwd.getPassword());
				String newpwd2=new String(this.jpnewpwd2.getPassword());
				PetUtil.customerManage.changePwd(BeanCustomer.currentLogincustomer,oldpwd, newpwd,newpwd2);
				this.setVisible(false);
			}catch(BaseException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
	}
}
