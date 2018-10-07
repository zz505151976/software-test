package UI;

import java.util.List;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import Model.BeanAdvanceorder;
import Model.BeanCustomer;
import Model.BeanServe;
import Model.BeanServeType;
import PetGO.PetUtil;
import Util.BaseException;
public class Frmpetpohot extends JDialog {
	
	public Frmpetpohot(String path) {
		Icon icon=new ImageIcon(path);
		JLabel label=null;
		label=new JLabel(icon,JLabel.CENTER);
		label.setForeground(Color.RED);
		label.setBackground(Color.YELLOW);
		this.getContentPane().add(label,BorderLayout.CENTER);
		this.setSize(1200,600);
		this.setTitle("图片展示");
		this.setLocation(300, 200);
		
	}
}
