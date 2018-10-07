package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.BeanAdvanceorder;
import Model.BeanCustomer;
import Model.BeanOperator;
import Model.BeanOrder;
import Model.BeanPet;
import PetGO.PetUtil;
import Util.BaseException;

public class FrmOperatorMain extends JFrame implements ActionListener{
	private JMenuBar menubar=new JMenuBar();//菜单栏
	
	private JMenu menu_operatormanage=new JMenu("员工管理");//菜单
	private JMenuItem menuitem_operatorlook=new JMenuItem("查看员工");
	private JMenuItem menuitem_reg=new JMenuItem("员工注册");//菜单项
	private JMenuItem menuitem_del=new JMenuItem("员工删除");
	
	private JMenu menu_customermanage=new JMenu("用户管理");
	private JMenuItem menuitem_look=new JMenuItem("查看用户");//用户账号模糊查询
	private JPanel customerjp=new JPanel();
	private JLabel customernumberlb=new JLabel("用户姓名");
	private JTextField customernumbertf=new JTextField(20);
	private JButton customernumberbtn=new JButton("查找");
	private JMenuItem menuitem_delcus=new JMenuItem("删除用户");
	
	
	private JMenu menu_pet =new JMenu("宠物管理");
	private JMenuItem menuitem_petlook=new JMenuItem("查看宠物");
	private JMenuItem menuitem_petadd=new JMenuItem("添加宠物");
	private JMenuItem menuitem_petdelete=new JMenuItem("删除宠物");
	
	private JMenu menu_goods =new JMenu("商品管理");
	//private JMenuItem menuitem_goodsadd=new JMenuItem("添加商品");
	//private JMenuItem menuitem_gooddel=new JMenuItem("删除商品");
	//private JMenuItem menuitem_goodstypeadd=new JMenuItem("添加类别");
	//private JMenuItem menuitem_goodstypedel=new JMenuItem("删除类别");
	private JMenuItem menuitem_goodslook=new JMenuItem("商品安排");
	
	private JMenu menu_serve  =new JMenu("服务管理");
	//private JMenuItem menuitem_serveadd=new JMenuItem("添加服务");
	//private JMenuItem menuitem_servedel=new JMenuItem("删除服务");
	private JMenuItem menuitem_servelook=new JMenuItem("服务安排");
	
	private JMenu menu_order=new JMenu("订单管理");
	private JMenuItem menuitem_lookorder =new JMenuItem("查看订单");
	private JMenuItem menuitem_rendorder =new JMenuItem("订单发货");
	
	private JMenu menu_advanserve=new JMenu("预约管理");
	private JMenuItem menuitem_lookserve=new JMenuItem("查看预约");
	private JMenuItem menuitem_starserve=new JMenuItem("预约开始");
	private JMenuItem menuitem_okserve=new JMenuItem("预约完成");
	
	private JMenu menu_account=new JMenu("账号管理");
	private JMenuItem menuitem_changepwd=new JMenuItem("密码修改");
	
	private Object tblOpTitle[]=BeanOperator.optableTitles;
	private Object tblOpData[][];
	DefaultTableModel tabOpModel=new DefaultTableModel();
	private JTable dataTableOp=new JTable(tabOpModel);
	
	List<BeanOperator> alloperator=null;
	
	private Object tblPetTitle[]=BeanPet.pettableTitles;
	private Object tblPetData[][];
	DefaultTableModel tabPetModel=new DefaultTableModel();
	private JTable dataTablePet=new JTable(tabPetModel);
	
	private Object tblCusTitle[]=BeanCustomer.customertableTitles;
	private Object tblCusData[][];
	List<BeanCustomer> allcus=null;
	
	List<BeanPet> allPet=null;
	private JPanel looktop=new JPanel();
	private JPanel lookwork=new JPanel();
	private JLabel toplb=new JLabel("请输入用户账号：");
	private JTextField toptf=new JTextField(20);
	private JButton topbtn=new JButton("查找");
	
	private Object tblorderTitle[]=BeanOrder.ordertableTitles;
	private Object tblorderData[][];
	List<BeanOrder> allownorder=null;
	
	private Object tblAdvanTitle[]=BeanAdvanceorder.advantableTitles;
	private Object tblAdvanData[][];
	List<BeanAdvanceorder> allownadvan=null;
	
	private void reloadallownadvanTable(int customer_id) {
		//查看顾客的预约
		
		try {
			allownadvan= PetUtil.advanceManage.showwhoadvance(customer_id);
		}catch (BaseException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblAdvanData=  new Object[allownadvan.size()][BeanAdvanceorder.advantableTitles.length];
		for(int i=0;i<allownadvan.size();i++){
			for(int j=0;j<BeanAdvanceorder.advantableTitles.length;j++)
				tblAdvanData[i][j]=allownadvan.get(i).getCell(j);
		}
		tabOpModel.setDataVector(tblAdvanData,tblAdvanTitle);
		this.dataTableOp.validate();
		this.dataTableOp.repaint();	
	} 
	private void reloadallownorderTable(int id) {
		//查看顾客的订单
		
		try {
			allownorder= PetUtil.orderManage.showorderbycus(id);
		}catch (BaseException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblorderData=  new Object[allownorder.size()][BeanOrder.ordertableTitles.length];
		for(int i=0;i<allownorder.size();i++){
			for(int j=0;j<BeanOrder.ordertableTitles.length;j++)
				tblorderData[i][j]=allownorder.get(i).getCell(j);
		}
		tabOpModel.setDataVector(tblorderData,tblorderTitle);
		this.dataTableOp.validate();
		this.dataTableOp.repaint();
	} 
	private void reloadCusTable(String name) {
		//按姓名查找用户
		try {
			allcus=PetUtil.customerManage.searchcustomername(name);
		}catch(BaseException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCusData=  new Object[allcus.size()][BeanCustomer.customertableTitles.length];
		for(int i=0;i<allcus.size();i++){
			for(int j=0;j<BeanCustomer.customertableTitles.length;j++)
				tblCusData[i][j]=allcus.get(i).getCell(j);
		}
		tabOpModel.setDataVector(tblCusData,tblCusTitle);
		this.dataTableOp.validate();
		this.dataTableOp.repaint();
		
	}
	private void reloadPetTable(int id) {
		//查看宠物
		try {
			allPet= PetUtil.petManage.searchall_customer(id);
		}catch (BaseException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblPetData=  new Object[allPet.size()][BeanPet.pettableTitles.length];
		for(int i=0;i<allPet.size();i++){
			for(int j=0;j<BeanPet.pettableTitles.length;j++)
				tblPetData[i][j]=allPet.get(i).getCell(j);
		}
		tabOpModel.setDataVector(tblPetData,tblPetTitle);
		this.dataTableOp.validate();
		this.dataTableOp.repaint();
	}
	private void reloadOpTable() {
		//查看职员
		try {
			alloperator= PetUtil.operatorManage.showalloperator();
		}catch (BaseException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblOpData=  new Object[alloperator.size()][BeanOperator.optableTitles.length];
		for(int i=0;i<alloperator.size();i++){
			for(int j=0;j<BeanOperator.optableTitles.length;j++)
				tblOpData[i][j]=alloperator.get(i).getCell(j);
		}
		tabOpModel.setDataVector(tblOpData,tblOpTitle);
		this.dataTableOp.validate();
		this.dataTableOp.repaint();
	}
	public FrmOperatorMain () {
		this.menu_operatormanage.add(menuitem_operatorlook);this.menuitem_operatorlook.addActionListener(this);
		this.menu_operatormanage.add(menuitem_reg);this.menuitem_reg.addActionListener(this);
		this.menu_operatormanage.add(menuitem_del);this.menuitem_del.addActionListener(this);
		this.menu_pet.add(menuitem_petlook);this.menuitem_petlook.addActionListener(this);
		this.menu_pet.add(menuitem_petadd);this.menuitem_petadd.addActionListener(this);
		this.menu_pet.add(menuitem_petdelete);this.menuitem_petdelete.addActionListener(this);
		//this.menu_goods.add(menuitem_goodsadd);this.menuitem_goodsadd.addActionListener(this);
		//this.menu_goods.add(menuitem_gooddel);this.menuitem_gooddel.addActionListener(this);
		//this.menu_goods.add(menuitem_goodstypeadd);this.menuitem_goodstypeadd.addActionListener(this);
	//	this.menu_goods.add(menuitem_goodstypedel);this.menuitem_goodstypedel.addActionListener(this);
		this.menu_goods.add(menuitem_goodslook);this.menuitem_goodslook.addActionListener(this);
		//this.menu_serve.add(menuitem_serveadd);this.menuitem_serveadd.addActionListener(this);
		//this.menu_serve.add(menuitem_servedel);this.menuitem_servedel.addActionListener(this);
		this.menu_serve.add(menuitem_servelook);this.menuitem_servelook.addActionListener(this);
		this.menu_customermanage.add(menuitem_look);this.menuitem_look.addActionListener(this);
		this.menu_customermanage.add(menuitem_delcus);this.menuitem_delcus.addActionListener(this);
		this.menu_order.add(menuitem_lookorder);this.menuitem_lookorder.addActionListener(this);
		this.menu_order.add(menuitem_rendorder);this.menuitem_rendorder.addActionListener(this);
		this.menu_account.add(menuitem_changepwd);this.menuitem_changepwd.addActionListener(this);
		this.menu_advanserve.add(menuitem_lookserve);this.menuitem_lookserve.addActionListener(this);
		this.menu_advanserve.add(menuitem_starserve);this.menuitem_starserve.addActionListener(this);
		this.menu_advanserve.add(menuitem_okserve);this.menuitem_okserve.addActionListener(this);
		if(BeanOperator.currentLoginoperator.getLevel().equals("管理员"))
			menubar.add(menu_operatormanage);
		menubar.add(menu_pet);
		menubar.add(menu_goods);
		menubar.add(menu_serve);
		menubar.add(menu_customermanage);
		menubar.add(menu_order);
		menubar.add(menu_account);
		menubar.add(menu_advanserve);
		this.setJMenuBar(menubar);
		
		this.getContentPane().add(new JScrollPane(this.dataTableOp),BorderLayout.CENTER);//JTable添加
		
		customerjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		customerjp.add(customernumberlb);
		customerjp.add(customernumbertf);
		customerjp.add(customernumberbtn);
		customernumberbtn.addActionListener(this);
		this.getContentPane().add(customerjp, BorderLayout.NORTH);
		customerjp.setVisible(false);
		
		
		this.setTitle("神奇宠物在哪里--员工专用");
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.menuitem_petadd) {
			//添加宠物
			Frmaddpet fradd=new Frmaddpet(this,"添加宠物",true);
			fradd.setVisible(true);
		}
		else if(e.getSource()==menuitem_petlook) {
			//查看宠物
			int i=this.dataTableOp.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择用户", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.reloadPetTable(allcus.get(i).getCustomer_id());
		}
		else if(e.getSource()==menuitem_petdelete) {
			//宠物删除
			int i=this.dataTableOp.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择宠物", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				PetUtil.operatorManage.deletepet(allPet.get(i).getPet_id());
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==menuitem_reg) {
			//员工注册
			FrmOpregister frmopreg=new FrmOpregister(this,"员工注册",true);
			frmopreg.setVisible(true);
			this.reloadOpTable();
		}
		else if(e.getSource()==menuitem_operatorlook) {
			//显示操作员
			customerjp.setVisible(false);
			this.reloadOpTable();
		}
		else if(e.getSource()==menuitem_petlook) {
			
		}
		else if(e.getSource()==menuitem_goodslook) {
			//打开商品管理界面
			FrmGoods frmgoods=new FrmGoods();
			frmgoods.setVisible(true);
		}
		else if(e.getSource()==menuitem_servelook) {
			FrmServe frmserve=new FrmServe();
			frmserve.setVisible(true);
		}
		else if(e.getSource()==menuitem_look) {
			customerjp.setVisible(true);
		}
		else if(e.getSource()==customernumberbtn) {
			String name=customernumbertf.getText();
			this.reloadCusTable(name);
		}
		else if(e.getSource()==menuitem_delcus) {
			//删除用户
			int i=this.dataTableOp.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择服务", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				PetUtil.operatorManage.deletecustomer(allcus.get(i).getCustomer_id());
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==menuitem_lookorder) {
			//查看顾客订单
			int i=this.dataTableOp.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择用户", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.reloadallownorderTable(allcus.get(i).getCustomer_id());
		}
		else if(e.getSource()==menuitem_rendorder) {
			//订单发货
			int i=this.dataTableOp.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择订单", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				PetUtil.orderManage.readysend(allownorder.get(i).getOrder_id());
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==menuitem_lookserve) {
			//查看顾客预约
			int i=this.dataTableOp.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择用户", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.reloadallownadvanTable(allcus.get(i).getCustomer_id());
		}
		else if(e.getSource()==menuitem_starserve) {
			//预约开始
			int i=this.dataTableOp.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择预约", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				PetUtil.advanceManage.beginadvance(allownadvan.get(i).getAdvance_id());
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==menuitem_okserve) {
			int i=this.dataTableOp.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择预约", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				PetUtil.advanceManage.readyadvance(allownadvan.get(i).getAdvance_id());
			}catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		else if(e.getSource()==menuitem_changepwd) {
			Frmopchangepwd frmopchangepwd=new Frmopchangepwd(this,"密码修改",true);
			frmopchangepwd.setVisible(true);
		}
		else if(e.getSource()==menuitem_del) {
			int i=this.dataTableOp.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择预约", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				PetUtil.operatorManage.deleteop(alloperator.get(i).getOperator_id());
				this.reloadOpTable();
			}catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
