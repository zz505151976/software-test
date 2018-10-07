package UI;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Model.BeanGoods;
import Model.BeanGoodsType;
import PetGO.PetUtil;
import Util.BaseException;
public class Frmaddserve extends JDialog implements ActionListener{
	private JPanel topjp=new JPanel();
	private JLabel title = new JLabel("服务添加");
	
	private JPanel workjp=new JPanel();
	private JLabel serveidlb=new JLabel("服务编号");
	private JTextField serveidtf=new JTextField(20);
	private JLabel servenamelb=new JLabel("服务名称");
	private JTextField servenametf=new JTextField(20);
	private JLabel servetypelb=new JLabel("服务类别");
	private JTextField servetypetf=new JTextField(20);
	private JLabel servepricelb=new JLabel("服务价格");
	private JTextField servepricetf=new JTextField(20);
	
	private JPanel downjp = new JPanel();
	private JButton btnOk = new JButton("添加");
	private JButton btnno = new JButton("取消");
	
	public Frmaddserve(Frame f,String s,boolean b) {
		super(f, s, b);
		topjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		topjp.add(title);
		this.getContentPane().add(topjp,BorderLayout.NORTH);
		
		workjp.setLayout(new GridLayout(4,2,5,5));
		workjp.add(serveidlb);
		workjp.add(serveidtf);
		workjp.add(servenamelb);
		workjp.add(servenametf);
		workjp.add(servetypelb);
		workjp.add(servetypetf);
		workjp.add(servepricelb);
		workjp.add(servepricetf);
		this.getContentPane().add(workjp,BorderLayout.CENTER);
		
		downjp.add(btnOk);
		downjp.add(btnno);
		btnOk.addActionListener(this);
		btnno.addActionListener(this);
		this.getContentPane().add(downjp,BorderLayout.SOUTH);
		
		this.setLocation(600, 300);
		this.setSize(420,300);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnno) {
			this.setVisible(false);
		}
		else if(e.getSource()==btnOk) {
			String serve_id=serveidtf.getText();
			String serve_name=servenametf.getText();
			String serve_type_id=servetypetf.getText();
			double serve_price=Double.parseDouble(servepricetf.getText());
			try {
				PetUtil.serverManage.addserve(serve_id, serve_name, serve_type_id, serve_price);
				this.setVisible(false);
			}catch(BaseException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
