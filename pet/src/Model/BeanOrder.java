package Model;
import Util.BaseException;
import Util.HibernateUtil;
import org.hibernate.query.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class BeanOrder {
	//客户购买商品
	public static final String[] ordertableTitles={"编号","商品名称","顾客姓名","单价","数量","总价","发货状态"};
	private int order_id;
	private String goods_id;
	private String goods_type_name;
	private String goods_name;
	private int customer_id;
	private int order_count;//数量
	private double order_price;//单价
	private double order_sum;//总价
	private String order_state;
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	
	public String getGoods_type_name() {
		return goods_type_name;
	}
	public void setGoods_type_name(String goods_type_name) {
		this.goods_type_name = goods_type_name;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getOrder_count() {
		return order_count;
	}
	public void setOrder_count(int order_count) {
		this.order_count = order_count;
	}
	public double getOrder_price() {
		return order_price;
	}
	public void setOrder_price(double order_price) {
		this.order_price = order_price;
	}
	public double getOrder_sum() {
		return order_sum;
	}
	public void setOrder_sum(double order_sum) {
		this.order_sum = order_sum;
	}
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	public String getCell(int col) {
		if(col==0)
			return String.valueOf(order_id);
		else if(col==1)
			return goods_name;
		else if(col==2) {
			Session session=HibernateUtil.getSession();
			session.beginTransaction();
			BeanCustomer bc=(BeanCustomer)session.get(BeanCustomer.class,customer_id);
			return bc.getCustomer_name();
		}
		else if(col==3) 
			return String.valueOf(order_price);
		else if(col==4)
			return String.valueOf(order_count);
		else if(col==5)
			return String.valueOf(order_sum);
		else if(col==6)
			return order_state;
		else return "";
		
	}
}
