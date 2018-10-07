package UI;
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;
import Model.*;
import PetGO.PetUtil;
import Util.BaseException;
public class Frmopchangepwd extends JDialog implements ActionListener{
	private JLabel title = new JLabel("密码修改");
	private JLabel oldpwd = new JLabel("请输入原密码：");
	private JLabel newpwd = new JLabel("请输入新密码：");
	
	private JButton btnOk = new JButton("修改");
	private JButton btnCancel = new JButton("取消");
	
	private JPasswordField jpoldpwd = new JPasswordField(20);
	private JPasswordField jpnewpwd = new JPasswordField(20);
	
	private JPanel topjp = new JPanel();
	private JPanel workjp = new JPanel();
	private JPanel downjp = new JPanel();
	
	public Frmopchangepwd(Frame f, String s, boolean b) {
		topjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		topjp.add(title);
		this.getContentPane().add(topjp,BorderLayout.NORTH);
		
		workjp.setLayout(new GridLayout(2,2,5,5));
		workjp.add(oldpwd);
		workjp.add(jpoldpwd);
		workjp.add(newpwd);
		workjp.add(jpnewpwd);
		this.getContentPane().add(workjp,BorderLayout.CENTER);
		
		downjp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		downjp.add(btnOk);
		downjp.add(btnCancel);
		this.getContentPane().add(downjp,BorderLayout.SOUTH);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
		this.setLocation(600, 300);
		this.setSize(300,160);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnCancel) {
			System.out.println("Ok");
			this.setVisible(false);
		}
		else if(e.getSource()==btnOk) {
			String oldpwd=new String(this.jpoldpwd.getPassword());
			String newpwd=new String(this.jpnewpwd.getPassword());
			try {
				PetUtil.operatorManage.opchangepwd(BeanOperator.currentLoginoperator, oldpwd, newpwd);
				this.setVisible(false);
			}catch(BaseException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		}
	}
}
