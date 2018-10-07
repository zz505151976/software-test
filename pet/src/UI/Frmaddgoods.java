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
public class Frmaddgoods extends JDialog implements ActionListener{
	String path="F:\\petserve\\barcode\\u=1354284822,1190638775&fm=26&gp=0.jpg";
	private JPanel topjp=new JPanel();
	private JLabel title = new JLabel("商品添加");
	
	private JPanel workjp=new JPanel();
	private JLabel goodsidlb=new JLabel("商品编号");
	private JTextField goodsidtf=new JTextField(20);
	private JLabel goodsnamelb=new JLabel("商品名称");
	private JTextField goodsnametf=new JTextField(20);
	private JLabel goodstypelb=new JLabel("商品类别");
	private JTextField goodstypetf=new JTextField(20);
	private JLabel goodsbrandlb=new JLabel("商品品牌");
	private JTextField goodsbrandtf=new JTextField(20);
	private JLabel goodspricelb=new JLabel("商品价格");
	private JTextField goodspricetf=new JTextField(20);
	private JLabel goodsbarcodelb = new JLabel("商品条码");
	private JButton btnchoose=new JButton("添加条码");
	
	private JPanel downjp = new JPanel();
	private JButton btnOk = new JButton("添加");
	private JButton btnno = new JButton("取消");
	
	List<BeanGoodsType> libgty;
	public Frmaddgoods(Frame f,String s,boolean b) {
		super(f, s, b);
		topjp.setLayout(new FlowLayout(FlowLayout.LEFT));
		topjp.add(title);
		this.getContentPane().add(topjp,BorderLayout.NORTH);
		
		
		
		workjp.setLayout(new GridLayout(6,2,5,5));
		workjp.add(goodsidlb);
		workjp.add(goodsidtf);
		workjp.add(goodsnamelb);
		workjp.add(goodsnametf);
		workjp.add(goodstypelb);
		workjp.add(goodstypetf);
		workjp.add(goodsbrandlb);
		workjp.add(goodsbrandtf);
		workjp.add(goodspricelb);
		workjp.add(goodspricetf);
		workjp.add(goodsbarcodelb);
		workjp.add(btnchoose);
		btnchoose.addActionListener(this);
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
		else if(e.getSource()==btnchoose) {
			JFileChooser jfc=new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			if(jfc.showDialog(new JLabel(),"选择")==JFileChooser.APPROVE_OPTION) {
				File file=jfc.getSelectedFile();
				path=file.getAbsolutePath();
			}
		}
		else if(e.getSource()==btnOk) {
			String goods_id=goodsidtf.getText();
			String goods_name=goodsnametf.getText();
			String gooos_type_id=goodstypetf.getText();
			String goods_brand=goodsbrandtf.getText();
			double goods_price=Double.parseDouble(goodspricetf.getText());
			System.out.print(goods_price);
			String goods_barcode=path;
			try {
				PetUtil.goodsManage.addgoods(goods_id, goods_name, gooos_type_id, goods_brand, goods_price, goods_barcode);
				this.setVisible(false);
			}catch(BaseException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
