package UI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import Model.BeanGoodsType;
import Model.BeanOperator;
import Model.BeanCustomer;
import Model.BeanGoods;
import Model.BeanPet;
import PetGO.PetUtil;
import Util.BaseException;
public class FrmGoods extends JFrame implements ActionListener{
	private JMenuBar menubar=new JMenuBar();
	
	private JMenu menu_goodstype =new JMenu("类别管理");
	private JMenuItem menuiten_goodstypeshow=new JMenuItem("查看类别");
	private JMenuItem menuitem_goodstypeadd=new JMenuItem("添加类别");
	private JMenuItem menuitem_goodstypedel=new JMenuItem("删除类别");
	
	private JMenu menu_goods  =new JMenu("实物管理");
	private JMenuItem menuitem_goodsadd=new JMenuItem("添加商品");
	private JMenuItem menuitem_gooddel=new JMenuItem("删除商品");
	
	
	private JLabel typesearchlb =new JLabel("类别");
	private JComboBox jcbox=new JComboBox();
	private JLabel namesearchlb =new JLabel("名称");
	private JTextField namesearchtf=new JTextField(20);
	private JButton typesearchbtn=new JButton("按类别查找");
	private JButton namesearchbtn=new JButton("按名称查找");
	private JPanel workjp=new JPanel();
	List<BeanGoodsType> libgty;
	
	private Object tblGoodsTitle[]=BeanGoods.goodstableTitles;
	private Object tblGoodsData[][];
	DefaultTableModel tabGoodsModel=new DefaultTableModel();
	private JTable dataTableGoods=new JTable(tabGoodsModel);
	
	List<BeanGoods> allgoods=null;
	
	private Object tblGoodsTypeTitle[]=BeanGoodsType.goodstypetableTitles;
	private Object tblGoodsTypeData[][];
	//DefaultTableModel tabGoodsTypeModel=new DefaultTableModel();
	//private JTable dataTableGoodsType=new JTable(tabGoodsTypeModel);
	
	List<BeanGoodsType> allgoodstype=null;
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
		tabGoodsModel.setDataVector(tblGoodsData,tblGoodsTitle);
		this.dataTableGoods.validate();
		this.dataTableGoods.repaint();
	}
	private void reloadGoodsbytypeTable() {
		//按照类型查询商品
		int k=jcbox.getSelectedIndex();
		String gooos_type_id=libgty.get(k).getType_id();
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
		tabGoodsModel.setDataVector(tblGoodsData,tblGoodsTitle);
		this.dataTableGoods.validate();
		this.dataTableGoods.repaint();
	}
	
	private void reloadGoodstypeTable() {
		
		try {
			allgoodstype=PetUtil.goodsManage.showallgoodstype();
		}catch (BaseException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblGoodsTypeData=  new Object[allgoodstype.size()][BeanGoodsType.goodstypetableTitles.length];
		for(int i=0;i<allgoodstype.size();i++){
			for(int j=0;j<BeanGoodsType.goodstypetableTitles.length;j++)
				tblGoodsTypeData[i][j]=allgoodstype.get(i).getCell(j);
		}
		tabGoodsModel.setDataVector(tblGoodsTypeData,tblGoodsTypeTitle);
		this.dataTableGoods.validate();
		this.dataTableGoods.repaint();
	}
	public FrmGoods() {
		this.menu_goodstype.add(menuitem_goodstypeadd);this.menuitem_goodstypeadd.addActionListener(this);
		this.menu_goodstype.add(menuiten_goodstypeshow);this.menuiten_goodstypeshow.addActionListener(this);
		this.menu_goodstype.add(menuitem_goodstypedel);this.menuitem_goodstypedel.addActionListener(this);
		this.menu_goods.add(menuitem_goodsadd);this.menuitem_goodsadd.addActionListener(this);
		this.menu_goods.add(menuitem_gooddel);this.menuitem_gooddel.addActionListener(this);
		
		menubar.add(menu_goodstype);
		menubar.add(menu_goods);
		try {
			libgty=PetUtil.goodsManage.showallgoodstype();
			for(BeanGoodsType bg:libgty) {
				String name=bg.getType_name();
				jcbox.addItem(name);
			}
		}catch(BaseException ex) {
			ex.printStackTrace();
		}
		
		this.setJMenuBar(menubar);
		
		this.getContentPane().add(new JScrollPane(this.dataTableGoods), BorderLayout.CENTER);
		this.dataTableGoods.addMouseListener(new MouseAdapter () {
			public void mouseClicked(MouseEvent e) {
				int k=dataTableGoods.getSelectedRow();
				String path=allgoods.get(k).getGoods_barcode();
				if(k<0)
					return ;
				else {
					Frmpetpohot frmpetphoto=new Frmpetpohot(path);
					frmpetphoto.setVisible(true);
				}
			}
			
		});
		
		workjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		workjp.add(typesearchlb);
		workjp.add(jcbox);
		workjp.add(typesearchbtn);
		workjp.add(namesearchlb);
		workjp.add(namesearchtf);
		workjp.add(namesearchbtn);
		typesearchbtn.addActionListener(this);
		namesearchbtn.addActionListener(this);
		this.getContentPane().add(workjp,BorderLayout.NORTH);
		workjp.setVisible(true);
		this.setTitle("商品管理");
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==menuitem_goodstypeadd) {//添加类别
			Frmaddgoodstype frmaddgoodstype=new Frmaddgoodstype(this,"类别添加",true);
			frmaddgoodstype.setVisible(true);
			this.reloadGoodstypeTable();
		}
		else if(e.getSource()==menuiten_goodstypeshow) {//显示商品类别
			this.reloadGoodstypeTable();
		}
		else if(e.getSource()==menuitem_goodstypedel) {//删除类别
			int i=this.dataTableGoods.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择类别", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				PetUtil.goodsManage.deletegoodstype(allgoodstype.get(i).getType_id());
				this.reloadGoodstypeTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
		else if(e.getSource()==namesearchbtn) {//按名称查找
			String goods_name=namesearchtf.getText();
			this.reloadGoodsbynameTable(goods_name);
		}
		else if(e.getSource()==menuitem_goodsadd) {
			Frmaddgoods frmaddgoods=new Frmaddgoods(this,"商品添加",true);
			frmaddgoods.setVisible(true);
		}
		else if(e.getSource()==typesearchbtn) {
			this.reloadGoodsbytypeTable();//按类别查找
		}
		else if(e.getSource()==menuitem_gooddel) {
			//删除商品
			int i=this.dataTableGoods.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				PetUtil.goodsManage.deletegoods(allgoods.get(i).getGoods_id());		
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
