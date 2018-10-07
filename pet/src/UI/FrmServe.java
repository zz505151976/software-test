package UI;
import Model.BeanServeType;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import Model.BeanGoodsType;
import Model.BeanOperator;
import Model.BeanCustomer;
import Model.BeanGoods;
import Model.BeanPet;
import Model.BeanServe;
import PetGO.PetUtil;
import Util.BaseException;
public class FrmServe extends JFrame implements ActionListener{
	private JMenuBar menubar=new JMenuBar();
	
	private JMenu menu_servetype =new JMenu("服务类别");
	private JMenuItem menuitem_looktype=new JMenuItem("类别查看");
	private JMenuItem menuitem_addservetype=new JMenuItem("添加类别");
	private JMenuItem menuitem_delservetype=new JMenuItem("删除类别");
	
	private JMenu menu_servemanage=new JMenu("服务操作");
	private JMenuItem menuitem_serveadd=new JMenuItem("增加服务");
	private JMenuItem menuitem_servedel=new JMenuItem("删除服务");
	//private JMenuItem menuitem_servechangename =new JMenuItem("修改名称");
//	private JMenuItem menuitem_servecjamgeprice = new JMenuItem("修改价格");
	
	DefaultTableModel tabModel=new DefaultTableModel();
	private JTable dataTable=new JTable(tabModel); 
	
	private Object tblServeTitle[]=BeanServe.servetableTitles;
	private Object tblServeData[][];
	List<BeanServe> allserve=null;
	
	private Object tblServetypeTitle[]=BeanServeType.servetypetableTitles;
	private Object tblServetypeData[][];
	List<BeanServeType> alltypes=null;
	
	private JPanel topjp=new JPanel();
	//private JButton allservebtn=new JButton("查看所有服务");
	private JButton searchbytype=new JButton("按类别查找");
	private JButton searchbyname=new JButton("按名称查找");
	private JLabel namesearchlb =new JLabel("名称");
	private JTextField namesearchtf=new JTextField(20);
	private JLabel typesearchlb=new JLabel("类别");
	private JComboBox jcbox=new JComboBox();//所有类别
	
	List<BeanServe> libs=null;
	List<BeanServeType> libstp;
	private void reloadallservetypeTable() {
		//查看类别	
		try {
			alltypes= PetUtil.serverManage.showallservetype();
		}catch (BaseException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblServetypeData=  new Object[alltypes.size()][BeanServeType.servetypetableTitles.length];
		for(int i=0;i<alltypes.size();i++){
			for(int j=0;j<BeanServeType.servetypetableTitles.length;j++)
				tblServetypeData[i][j]=alltypes.get(i).getCell(j);
		}
		tabModel.setDataVector(tblServetypeData,tblServetypeTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	private void reloadservebytypeTable() {
		//按类别查找
		int k=jcbox.getSelectedIndex();
		String servetype_id=libstp.get(k).getServetype_id();
		try {
			allserve= PetUtil.serverManage.showservebytype(servetype_id);
		}catch (BaseException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblServeData=  new Object[allserve.size()][BeanServe.servetableTitles.length];
		for(int i=0;i<allserve.size();i++){
			for(int j=0;j<BeanServe.servetableTitles.length;j++)
				tblServeData[i][j]=allserve.get(i).getCell(j);
		}
		tabModel.setDataVector(tblServeData,tblServeTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	private void reloadservebynameTable(String serve_name) {
		//按名称模糊查找
		try {
			allserve= PetUtil.serverManage.showservename(serve_name);
		}catch (BaseException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblServeData=  new Object[allserve.size()][BeanServe.servetableTitles.length];
		for(int i=0;i<allserve.size();i++){
			for(int j=0;j<BeanServe.servetableTitles.length;j++)
				tblServeData[i][j]=allserve.get(i).getCell(j);
		}
		tabModel.setDataVector(tblServeData,tblServeTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	public FrmServe() {
		this.menu_servetype.add(menuitem_looktype);this.menuitem_looktype.addActionListener(this);
		this.menu_servetype.add(menuitem_addservetype);this.menuitem_addservetype.addActionListener(this);
		this.menu_servetype.add(menuitem_delservetype);this.menuitem_delservetype.addActionListener(this);
		this.menu_servemanage.add(menuitem_serveadd);this.menuitem_serveadd.addActionListener(this);
		this.menu_servemanage.add(menuitem_servedel);this.menuitem_servedel.addActionListener(this);
		//this.menu_servemanage.add(menuitem_servechangename);this.menuitem_servechangename.addActionListener(this);
		//this.menu_servemanage.add(menuitem_servecjamgeprice);this.menuitem_servecjamgeprice.addActionListener(this);
		
		menubar.add(menu_servetype);
		menubar.add(menu_servemanage);
		this.setJMenuBar(menubar);
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		
		try {//下拉栏循环添加类别
			libstp=PetUtil.serverManage.showallservetype();
			for(BeanServeType bs:libstp) {
				String tyname=bs.getServetype_name();
				jcbox.addItem(tyname);
			}
		}catch(BaseException ex) {
			ex.printStackTrace();
		}
		topjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		topjp.add(typesearchlb);
		topjp.add(jcbox);
		topjp.add(searchbytype);
		topjp.add(namesearchlb);
		topjp.add(namesearchtf);
		topjp.add(searchbyname);
		//topjp.add(allservebtn);
		//allservebtn.addActionListener(this);
		searchbytype.addActionListener(this);
		searchbyname.addActionListener(this);
		this.getContentPane().add(topjp, BorderLayout.NORTH);
		topjp.setVisible(true);
		this.setTitle("服务管理");
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==searchbyname) {
			//按名称查找服务
			String	serve_name=namesearchtf.getText();
			this.reloadservebynameTable(serve_name);
		}
		else if(e.getSource()==searchbytype) {
			//按类型查找服务
			this.reloadservebytypeTable();
		}
		else if(e.getSource()==menuitem_addservetype) {
			//类别添加
			Frmaddservetype frmaddservetype=new Frmaddservetype(this,"类别添加",true);
			frmaddservetype.setVisible(true);
			this.reloadallservetypeTable();
		}
		else if(e.getSource()==menuitem_looktype) {
			//查看所有类别
			this.reloadallservetypeTable();
		}
		else if(e.getSource()==menuitem_delservetype) {
			//删除类别
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择服务", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
					PetUtil.serverManage.deleteservetype(alltypes.get(i).getServetype_id());
					this.reloadallservetypeTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==menuitem_serveadd) {
			//添加服务
			Frmaddserve frmaddserve=new Frmaddserve(this,"添加服务",true);
			frmaddserve.setVisible(true);
		}
		else if(e.getSource()==menuitem_servedel) {
			//删除服务
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择服务", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				PetUtil.serverManage.deleteserve(allserve.get(i).getServe_id());
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
	}
}
