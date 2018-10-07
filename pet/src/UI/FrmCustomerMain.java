package UI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import Model.BeanCustomer;
import Model.BeanPet;
import PetGO.PetUtil;
import Util.BaseException;
public class FrmCustomerMain extends JFrame implements ActionListener{
	private JButton JBCancel=new JButton("退出");
	private JButton JBTrue=new JButton("确认");
	private JButton JBwork1;
	private JButton JBwork2;
	private JButton JBwork3;
	private JButton JBwork4;
	private JPanel workjpleft1=new JPanel();
	private JPanel topjp1=new JPanel();
	private JPanel workjp1=new JPanel();
	private JPanel jpleft=new JPanel();
	private JPanel jpright=new JPanel();
	private JPanel statujp=new JPanel();
	private JMenuBar menubar=new JMenuBar();//菜单栏
	
	private JMenu menu_money=new JMenu("我的钱包");//菜单
	private JMenu menu_pet=new JMenu("我的宠物");
	private JMenu menu_order=new JMenu("我的订单");
	private JMenu menu_goods=new JMenu("商品中心");
	//private JMenu menu_serve=new JMenu("宠物服务");
	private JMenu menu_system=new JMenu("账号中心");
	
	//private JMenuItem menuitem_remain=new JMenuItem("余额查询");//菜单项
	private JMenuItem menuitem_recharge=new JMenuItem("充值");
	private JMenuItem menuitem_lookorder=new JMenuItem("查看订单");
	private JMenuItem menuitem_subcribe=new JMenuItem("查看预约");
	private JMenuItem menuitem_look=new JMenuItem("查看宠物");
	private JMenuItem menuitem_buy=new JMenuItem("商品选购");
	private JMenuItem menuitem_chooseserve=new JMenuItem("服务预约");
	private JMenuItem menuitem_petserve=new JMenuItem("选择服务");
	private JMenuItem menuitem_management=new JMenuItem("账号管理");
	private JMenuItem menuitem_changepwd=new JMenuItem("修改密码");
	private JMenuItem menuitem_changephone=new JMenuItem("修改手机");
	private JMenuItem menuitem_changemail=new JMenuItem("修改邮箱");
	private JMenuItem menuitem_changeaddress=new JMenuItem("修改地址");
	
	private JLabel title1;
	private JLabel workjl1;
	private JLabel workjlshow1;
	private JLabel workjl2;
	private JLabel workjlshow2;
	private JLabel workjl3;
	private JLabel workjlshow3;
	private JLabel workjl4;
	private JLabel workjlshow4;
	
	private Object tblPetTitle[]=BeanPet.pettableTitles;
	private Object tblPetData[][];
	DefaultTableModel tabPetModel=new DefaultTableModel();
	private JTable dataTablePet=new JTable(tabPetModel);
	
	List<BeanPet> allPet=null;
	private void reloadPetTable() {
		try {
			allPet= PetUtil.petManage.searchonly(BeanCustomer.currentLogincustomer);
		}catch (BaseException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblPetData=  new Object[allPet.size()][BeanPet.pettableTitles.length];
		for(int i=0;i<allPet.size();i++){
			for(int j=0;j<BeanPet.pettableTitles.length;j++)
				tblPetData[i][j]=allPet.get(i).getCell(j);
		}
		tabPetModel.setDataVector(tblPetData,tblPetTitle);
		this.dataTablePet.validate();
		this.dataTablePet.repaint();
	}
	public FrmCustomerMain() {
		
		this.menu_pet.add(menuitem_look);this.menuitem_look.addActionListener(this);
		//this.menu_serve.add(menuitem_petserve);this.menuitem_petserve.addActionListener(this);
		this.menu_order.add(menuitem_lookorder);this.menuitem_lookorder.addActionListener(this);
		this.menu_order.add(menuitem_subcribe);this.menuitem_subcribe.addActionListener(this);
		this.menu_goods.add(menuitem_buy);this.menuitem_buy.addActionListener(this);
		this.menu_goods.add(menuitem_chooseserve);this.menuitem_chooseserve.addActionListener(this);
		//this.menu_money.add(menuitem_remain);this.menuitem_remain.addActionListener(this);
		this.menu_money.add(menuitem_recharge);this.menuitem_recharge.addActionListener(this);
		this.menu_system.add(menuitem_management);this.menuitem_management.addActionListener(this);
		this.menu_system.addSeparator();
		this.menu_system.add(menuitem_changepwd);this.menuitem_changepwd.addActionListener(this);
		this.menu_system.addSeparator();
		this.menu_system.add(menuitem_changephone);this.menuitem_changephone.addActionListener(this);
		this.menu_system.addSeparator();
		this.menu_system.add(menuitem_changemail);this.menuitem_changemail.addActionListener(this);
		this.menu_system.addSeparator();
		this.menu_system.add(menuitem_changeaddress);this.menuitem_changeaddress.addActionListener(this);
	
		menubar.add(menu_pet);
		//menubar.add(menu_serve);
		menubar.add(menu_order);
		menubar.add(menu_goods);
		menubar.add(menu_money);
		menubar.add(menu_system);
		
		
		this.setJMenuBar(menubar);
		//底下界面
		JLabel tipslabel=new JLabel("欢迎回来！"+"  "+BeanCustomer.currentLogincustomer.getCustomer_name());
		statujp.setLayout(new FlowLayout(FlowLayout.RIGHT));
		statujp.add(tipslabel);
		this.getContentPane().add(statujp,BorderLayout.SOUTH);
		//表格
		this.getContentPane().add(new JScrollPane(this.dataTablePet), BorderLayout.CENTER);
		
		this.dataTablePet.addMouseListener(new MouseAdapter () {
			public void mouseClicked(MouseEvent e) {
				int k=dataTablePet.getSelectedRow();
				String path=allPet.get(k).getPet_image();
				if(k<0)
					return ;
				else {
					Frmpetpohot frmpetphoto=new Frmpetpohot(path);
					frmpetphoto.setVisible(true);
				}
			}
			
		});
		
		
		
		
		
		
		
		this.setTitle("神奇宠物在哪里");
		this.setSize(800,600);
		this.setLocationRelativeTo(null);
		//this.setExtendedState(Frame.MAXIMIZED_BOTH);
		//this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.menuitem_look) {
			//查看宠物
			this.reloadPetTable();
		}
		else if(e.getSource()==this.menuitem_changephone) {
			Frmchangephone frmchangephone=new Frmchangephone(this,"修改密码",true);
			frmchangephone.setVisible(true);
		}
		else if(e.getSource()==this.menuitem_changemail) {
			Frmchangemail frmchangemail=new Frmchangemail(this,"修改密码",true);
			frmchangemail.setVisible(true);
		}
		else if(e.getSource()==this.menuitem_changepwd) {
			Frmchangepwd frmchangepwd=new Frmchangepwd(this,"修改密码",true);
			frmchangepwd.setVisible(true);
			
		}
		else if(e.getSource()==this.menuitem_changeaddress) {
			Frmchangeaddress frmchangeaddress=new Frmchangeaddress(this,"修改地址",true);
			frmchangeaddress.setVisible(true);
		}
		else if(e.getSource()==this.menuitem_management) {
			//账号管理
			Frmaccount frmaccount=new Frmaccount (this,"账号管理",true);
			frmaccount.setVisible(true);
			
		}
		else if(e.getSource()==JBCancel) {
			System.exit(0);
		}
		else if(e.getSource()==menuitem_chooseserve) {
			//服务预约，打开服务窗口进行选择
			Frmservechoose frmchoose=new Frmservechoose(this,"服务预约",true);
			frmchoose.setVisible(true);
		}
		else if(e.getSource()==menuitem_subcribe) {
			Frmcuslookserve frmcuslook=new Frmcuslookserve(this,"查看预约",true);
			frmcuslook.setVisible(true);
		}
		else if(e.getSource()==menuitem_buy) {
			Frmcuschoosegoods frmchoosegoods=new Frmcuschoosegoods(this,"商品选购",true);
			frmchoosegoods.setVisible(true);
		}
		else if(e.getSource()==menuitem_recharge) {
			Frmcusrecharge frmcusrecharge=new Frmcusrecharge(this,"充值",true);
			frmcusrecharge.setVisible(true);
		}
		else if(e.getSource()==menuitem_lookorder) {
			Frmcusorder frmcusorder=new Frmcusorder(this,"我的订单",true);
			frmcusorder.setVisible(true);
		}
		
	}
}
