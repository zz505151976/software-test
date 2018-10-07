package Model;
import java.util.Date;
import Util.BaseException;
import Util.HibernateUtil;
import org.hibernate.query.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class BeanAdvanceorder {
	public static final String[] advantableTitles={"编号","服务","主人姓名","宠物昵称","预约时间","实际完成时间","完成情况"};
	private int advance_id;
	private String advance_serve_id;
	private int customer_id;
	private int pet_id;
	private Date advance_time;
	private Date advance_real_time;//实际完成时间
	private String advance_order_describe;//完成情况
	public int getAdvance_id() {
		return advance_id;
	}
	public void setAdvance_id(int advance_id) {
		this.advance_id = advance_id;
	}
	public String getAdvance_serve_id() {
		return advance_serve_id;
	}
	public void setAdvance_serve_id(String advance_serve_id) {
		this.advance_serve_id = advance_serve_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getPet_id() {
		return pet_id;
	}
	public void setPet_id(int pet_id) {
		this.pet_id = pet_id;
	}
	public Date getAdvance_time() {
		return advance_time;
	}
	public void setAdvance_time(Date advance_time) {
		this.advance_time = advance_time;
	}
	public Date getAdvance_real_time() {
		return advance_real_time;
	}
	public void setAdvance_real_time(Date advance_real_time) {
		this.advance_real_time = advance_real_time;
	}
	public String getAdvance_order_describe() {
		return advance_order_describe;
	}
	public void setAdvance_order_describe(String advance_order_describe) {
		this.advance_order_describe = advance_order_describe;
	}
	public String getCell(int col) {
		if(col==0)
			return String.valueOf(advance_id);
		else if(col==1) {
			Session session=HibernateUtil.getSession();
			session.beginTransaction();
			BeanServe bs=(BeanServe)session.get(BeanServe.class,advance_serve_id);
			return bs.getServe_name();
		}
		else if(col==2) {
			Session session=HibernateUtil.getSession();
			session.beginTransaction();
			BeanCustomer bc=(BeanCustomer)session.get(BeanCustomer.class, customer_id);
			return bc.getCustomer_name();
		}
		else if(col==3) {
			Session session=HibernateUtil.getSession();
			session.beginTransaction();
			BeanPet bp=(BeanPet)session.get(BeanPet.class, pet_id);
			return bp.getPet_name();
		}
		else if(col==4)
			return String.valueOf(advance_time);
		else if(col==5)
			return String.valueOf(advance_real_time);
		else if(col==6)
			return advance_order_describe;
		else return "";
	}
}
