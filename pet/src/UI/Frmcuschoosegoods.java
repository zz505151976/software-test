package UI;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Model.BeanAdvanceorder;
import Model.BeanCustomer;
import Model.BeanGoods;
import Model.BeanServe;
import Model.BeanServeType;
import Model.BeanGoodsType;
import PetGO.PetUtil;
import Util.BaseException;
public class Frmcuschoosegoods extends JDialog implements ActionListener{
	private JPanel topjp=new JPanel();
	private JLabel typesearchlb =new JLabel("类别");
	private JComboBox jcbox=new JComboBox();
	private JLabel namesearchlb =new JLabel("名称");
	private JTextField namesearchtf=new JTextField(20);
	private JButton typesearchbtn=new JButton("按类别查找");
	private JButton namesearchbtn=new JButton("按名称查找");
	private JPanel downjp=new JPanel();
	private JButton btnok=new JButton("购买");
	private JButton btncancel=new JButton("取消");
	private JLabel countlb=new JLabel("数量");
	private JTextField counttf=new JTextField(20);
	
	DefaultTableModel tabModel=new DefaultTableModel();
	private JTable dataTable=new JTable(tabModel); //JTabel
	
	List<BeanGoodsType> alltype=null;//存放类型
	
	private Object tblGoodsTitle[]=BeanGoods.goodstableTitles;
	private Object tblGoodsData[][];//商品信息
	List<BeanGoods> allgoods=null;
	
	int order_count;
	private void reloadGoodsbynameTable(String goods_name) {
		//按照名称查询
		try {
			allgoods= PetUtil.goodsManage.showgoodsbyname(goods_name);
		}catch (BaseException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblGoodsData=  new Object[allgoods.size()][BeanGoods.goodstableTitles.length];
		for(int i=0;i<allgoods.size();i++){
			for(int j=0;j<BeanGoods.goodstableTitles.length;j++)
				tblGoodsData[i][j]=allgoods.get(i).getCell(j);
		}
		tabModel.setDataVector(tblGoodsData,tblGoodsTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	
	private void reloadGoodsbytypeTable() {
		//按照类型查询商品
		int k=jcbox.getSelectedIndex();
		String gooos_type_id=alltype.get(k).getType_id();
		try {
			allgoods= PetUtil.goodsManage.showgoodsbytype(gooos_type_id);
		}catch (BaseException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblGoodsData=  new Object[allgoods.size()][BeanGoods.goodstableTitles.length];
		for(int i=0;i<allgoods.size();i++){
			for(int j=0;j<BeanGoods.goodstableTitles.length;j++)
				tblGoodsData[i][j]=allgoods.get(i).getCell(j);
		}
		tabModel.setDataVector(tblGoodsData,tblGoodsTitle);
		this.dataTable.validate();
		this.dataTable.repaint();
	}
	public Frmcuschoosegoods(Frame f,String s,boolean b) {
		super(f,s,b);
		
		try {
			alltype=PetUtil.goodsManage.showallgoodstype();
			for(BeanGoodsType bgtp:alltype) {
				String name=bgtp.getType_name();
				jcbox.addItem(name);
			}
		}catch(BaseException ex) {
			ex.printStackTrace();
		}
		topjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		topjp.add(typesearchlb);
		topjp.add(jcbox);
		topjp.add(namesearchlb);
		topjp.add(namesearchtf);
		typesearchbtn.addActionListener(this);
		topjp.add(typesearchbtn);
		namesearchbtn.addActionListener(this);
		topjp.add(namesearchbtn);
		this.getContentPane().add(topjp, BorderLayout.NORTH);
		
		this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);
		
		btnok.addActionListener(this);
		btncancel.addActionListener(this);
		downjp.add(countlb);
		downjp.add(counttf);
		downjp.add(btnok);
		downjp.add(btncancel);
		this.getContentPane().add(downjp, BorderLayout.SOUTH);
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btncancel)
			this.setVisible(false);
		else if(e.getSource()==typesearchbtn) {
			this.reloadGoodsbytypeTable();
		}
		else if(e.getSource()==namesearchbtn) {
			String goods_name=namesearchtf.getText();
			this.reloadGoodsbynameTable(goods_name);
		}
		else if(e.getSource()==btnok) {
			int i=this.dataTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			String goods_id=allgoods.get(i).getGoods_id();
			if(counttf.getText()==null||counttf.getText().equals(""))
				order_count=1;	
			else
				order_count=Integer.parseInt(counttf.getText());	
			try {
				PetUtil.orderManage.buygoods(BeanCustomer.currentLogincustomer, goods_id, order_count);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
