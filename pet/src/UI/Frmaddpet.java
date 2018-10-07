package UI;
import java.io.File;
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;
import Model.*;
import PetGO.PetUtil;
import Util.BaseException;

public class Frmaddpet extends JDialog implements ActionListener{
	String path="F:\\petserve\\image\\u=1354284822,1190638775&fm=26&gp=0.jpg";
	private JPanel topjp=new JPanel();
	private JLabel title = new JLabel("宠物添加");
	
	private JPanel workjp=new JPanel();
	private JLabel cusnumberlb = new JLabel("用户账号：");
	private JTextField cusnumbertf =new JTextField(20);
	private JLabel petnamelb = new JLabel("宠物昵称：");
	private JTextField petnametf =new JTextField(20);
	private JLabel petlovenamelb = new JLabel("宠物别名：");
	private JTextField petlovenametf =new JTextField(20);
	private JLabel petimagelb = new JLabel("宠物照片：");
	//private JTextField petimagetf=new  JTextField(20);
	
	private JPanel downjp = new JPanel();
	private JButton btnOk = new JButton("添加");
	private JButton btnno = new JButton("取消");
	//private JPanel eastjp=new JPanel();
	private JButton btnchoose=new JButton("选择图片");
	public Frmaddpet(Frame f,String s,boolean b) {
		super(f, s, b);
		topjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		topjp.add(title);
		this.getContentPane().add(topjp,BorderLayout.NORTH);
		
		workjp.setLayout(new GridLayout(4,2,5,5));
		workjp.add(cusnumberlb);
		workjp.add(cusnumbertf);
		workjp.add(petnamelb);
		workjp.add(petnametf);
		workjp.add(petlovenamelb);
		workjp.add(petlovenametf);
		workjp.add(petimagelb);
		workjp.add(btnchoose);
		this.getContentPane().add(workjp,BorderLayout.CENTER);
		
		downjp.add(btnOk);
		downjp.add(btnno);
		btnOk.addActionListener(this);
		btnno.addActionListener(this);
		this.getContentPane().add(downjp,BorderLayout.SOUTH);
		
		btnchoose.addActionListener(this);
		//eastjp.add(btnchoose);
		//this.getContentPane().add(eastjp,BorderLayout.EAST);
		this.setLocation(600, 300);
		this.setSize(420,300);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnno) {
			this.setVisible(false);
		}
		else if(e.getSource()==btnchoose) {
			JFileChooser jfc=new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			if(jfc.showDialog(new JLabel(),"选择")==JFileChooser.APPROVE_OPTION) {
				File file=jfc.getSelectedFile();
				path=file.getAbsolutePath();
			}
			
		}
		else if(e.getSource()==btnOk) {
			String cusnumber=cusnumbertf.getText();
			String petname=petnametf.getText();
			String petlovename=petlovenametf.getText();
			String petimage=path;
			try{
				PetUtil.petManage.addpet(cusnumber, petname, petlovename, petimage);
				this.setVisible(false);
			}catch(BaseException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
