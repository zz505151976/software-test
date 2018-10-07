package UI;
import Model.BeanCustomer;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Model.BeanCustomer;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Frmcustomertest {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frmcustomertest window = new Frmcustomertest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frmcustomertest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 488, 351);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 472, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu menu = new JMenu("账号中心");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("账号管理");
		menu.add(menuItem);
		
		JMenu menu_1 = new JMenu("我的宠物");
		menuBar.add(menu_1);
		
		JMenu menu_2 = new JMenu("我的订单");
		menuBar.add(menu_2);
		
		JButton button = new JButton("密码修改");
		button.setBounds(37, 68, 93, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("邮箱修改");
		button_1.setBounds(37, 113, 93, 23);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("地址修改");
		button_2.setBounds(37, 159, 93, 23);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("号码修改");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_3.setBounds(37, 202, 93, 23);
		frame.getContentPane().add(button_3);
		
		JLabel label = new JLabel("个人中心");
		label.setBounds(174, 31, 54, 15);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("用户编号：");
		label_1.setBounds(225, 76, 65, 15);
		frame.getContentPane().add(label_1);
		
		JLabel lblNewLabel = new JLabel(String.valueOf(BeanCustomer.currentLogincustomer.getCustomer_id()));
		lblNewLabel.setBounds(300, 76, 54, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel label_2 = new JLabel("用户姓名：");
		label_2.setBounds(225, 113, 65, 15);
		frame.getContentPane().add(label_2);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(300, 113, 54, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("用户地址：");
		lblNewLabel_2.setBounds(225, 151, 65, 15);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(300, 151, 54, 15);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel label_3 = new JLabel("手机号码：");
		label_3.setBounds(225, 189, 65, 15);
		frame.getContentPane().add(label_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(300, 189, 54, 15);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("用户邮箱：");
		lblNewLabel_5.setBounds(225, 226, 65, 15);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBounds(300, 226, 54, 15);
		frame.getContentPane().add(lblNewLabel_6);
	}
}
