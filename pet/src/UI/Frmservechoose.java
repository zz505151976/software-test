package UI;
import Model.BeanServeType;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import java.util.List;
import Model.BeanGoodsType;
import Model.BeanOperator;
import Model.BeanCustomer;
import Model.BeanGoods;
import Model.BeanPet;
import Model.BeanServe;
import PetGO.PetUtil;
import Util.BaseException;
public class Frmservechoose extends JDialog implements ActionListener {
	//为自己的宠物挑选服务
	
	private JPanel topjp =new JPanel();
	private JLabel title = new JLabel("服务预约");
	
	private JPanel workjp=new JPanel();
	private JLabel petidlb=new JLabel("请输入需要预约服务的宠物编号");
	private JTextField petidtf=new JTextField(20);//int
	private JLabel servetypelb=new JLabel("请选择服务类型");
	private JComboBox servetypebox=new JComboBox();//提供能选择的服务类型
	private JLabel servelb=new JLabel("请选择你需要的服务");
	private JComboBox servebox=new JComboBox();//服务类型下的服务
	private JLabel datelb=new JLabel("请输入您的预约时间");
	private JTextField datetf=new JTextField(20);
	private JLabel dateremind=new JLabel("预约时间格式");
	private JLabel dateremindr=new JLabel("2018-09-12");
	
	private JPanel downjp=new JPanel();
	private JButton btnok=new JButton("确定");
	private JButton btnno=new JButton("取消");
	
	List<BeanServeType> allservetype=null;//服务类型
	List<BeanServe>  bytypeserve=null;//具体服务
	public Frmservechoose(Frame f,String s,boolean b) {
		super(f, s, b);
		topjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		topjp.add(title);
		this.getContentPane().add(topjp,BorderLayout.NORTH);
		
		workjp.setLayout(new GridLayout(5,2,5,5));
		try {
			allservetype=PetUtil.serverManage.showallservetype();//获取所有类型
			for (BeanServeType bsty:allservetype) {
				String typename=bsty.getServetype_name();
				servetypebox.addItem(typename);
			}
		}catch(BaseException ex) {
			ex.printStackTrace();
		}
		//出BUG了！！！
		servetypebox.addActionListener(this);
		try {
			int i=servetypebox.getSelectedIndex();//当前服务类别索引
			String servetype_id=allservetype.get(i).getServetype_id();
			bytypeserve=PetUtil.serverManage.showservebytype(servetype_id);
			for(BeanServe bs:bytypeserve) {
				String servename=bs.getServe_name();
				servebox.addItem(servename);//获取服务
			}
		}catch(BaseException ex) {
			ex.printStackTrace();
		}
		
		workjp.add(petidlb);
		workjp.add(petidtf);
		workjp.add(servetypelb);
		workjp.add(servetypebox);
		workjp.add(servelb);
		workjp.add(servebox);
		workjp.add(datelb);
		workjp.add(datetf);
		workjp.add(dateremind);
		workjp.add(dateremindr);
		this.getContentPane().add(workjp,BorderLayout.CENTER);
		
		downjp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		downjp.add(btnok);
		downjp.add(btnno);
		btnok.addActionListener(this);
		btnno.addActionListener(this);
		this.getContentPane().add(downjp,BorderLayout.SOUTH);
		
		this.setLocation(600, 300);
		this.setSize(420,300);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnno) {
			this.setVisible(false);
		}
		else if(e.getSource()==btnok) {
			int i=servebox.getSelectedIndex();
			String advance_serve_id=bytypeserve.get(i).getServe_id();
			int pet_id=Integer.parseInt(petidtf.getText());
			String advance_time=datetf.getText();
			try {
				PetUtil.advanceManage.cuaddadvance(advance_serve_id, pet_id, advance_time);
				this.setVisible(false);
			}catch(BaseException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==servetypebox) {
			servebox.removeAllItems();
			try {
				int i=servetypebox.getSelectedIndex();//当前服务类别索引
				String servetype_id=allservetype.get(i).getServetype_id();
				bytypeserve=PetUtil.serverManage.showservebytype(servetype_id);
				for(BeanServe bs:bytypeserve) {
					String servename=bs.getServe_name();
					servebox.addItem(servename);//获取服务
				}
			}catch(BaseException ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
