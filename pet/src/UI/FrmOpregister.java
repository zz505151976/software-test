package UI;
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;
import Model.*;
import PetGO.PetUtil;
import Util.BaseException;
public class FrmOpregister extends JDialog implements ActionListener{
	private JPanel topjp=new JPanel();
	private JLabel title = new JLabel("职员创建");
	private JPanel workjp=new JPanel();
	private JLabel opidlb=new JLabel("账号：");
	private JTextField opidtf=new JTextField(20);
	private JLabel opnamelb=new JLabel("姓名：");
	private JTextField opnametf=new JTextField(20);
	private JLabel oppwdlb=new JLabel("密码：");
	private JPasswordField oppwdtf=new JPasswordField(20);
	private JLabel oplevel=new JLabel("权限");
	private JComboBox comboBox=new JComboBox();
	
	private JPanel downjp=new JPanel();
	private JButton btnok =new JButton("创建");
	private JButton btncancel =new JButton("取消");
	
	public FrmOpregister(Frame f,String s,boolean b) {
		super(f,s,b);
		topjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		topjp.add(title);
		this.getContentPane().add(topjp,BorderLayout.NORTH);
		
		workjp.setLayout(new GridLayout(4,2));
		workjp.add(opidlb);
		workjp.add(opidtf);
		workjp.add(opnamelb);
		workjp.add(opnametf);
		workjp.add(oppwdlb);
		workjp.add(oppwdtf);
		workjp.add(oplevel);
		comboBox.addItem("管理员");//下拉框,选择level
		comboBox.addItem("操作员");
		workjp.add(comboBox);
		this.getContentPane().add(workjp,BorderLayout.CENTER);
		
		downjp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		btnok.addActionListener(this);
		btncancel.addActionListener(this);
		downjp.add(btnok);
		downjp.add(btncancel);
		this.getContentPane().add(downjp,BorderLayout.SOUTH);
		
		this.setSize(300,200);
		this.setLocation(600, 300);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnok) {
			String operator_id=this.opidtf.getText();
			String operator_name=this.opnametf.getText();
			String operator_pwd=new String(this.oppwdtf.getPassword());
			String level=comboBox.getSelectedItem().toString();
			try {
				BeanOperator bo=PetUtil.operatorManage.reg(operator_id, operator_name, operator_pwd, level);
				this.setVisible(false);
			}catch(BaseException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==btncancel) {
			this.setVisible(false);
		}
	}
}
