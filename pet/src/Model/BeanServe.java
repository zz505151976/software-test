package Model;
import Util.BaseException;
import Util.HibernateUtil;
import org.hibernate.query.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class BeanServe {
	public static final String[] servetableTitles={"编号","名称","类别","价格"};
	private String serve_id;
	private String serve_name;
	private String serve_type_id;
	private double serve_price;
	public String getServe_id() {
		return serve_id;
	}
	public void setServe_id(String serve_id) {
		this.serve_id = serve_id;
	}
	public String getServe_name() {
		return serve_name;
	}
	public void setServe_name(String serve_name) {
		this.serve_name = serve_name;
	}
	public String getServe_type_id() {
		return serve_type_id;
	}
	public void setServe_type_id(String serve_type_id) {
		this.serve_type_id = serve_type_id;
	}
	public double getServe_price() {
		return serve_price;
	}
	public void setServe_price(double serve_price) {
		this.serve_price = serve_price;
	}
	public String getCell(int col) {
		if(col==0)
			return serve_id;
		else if(col==1)
			return serve_name;
		else if(col==2) {
			Session session=HibernateUtil.getSession();
			session.beginTransaction();
			BeanServeType bsty=(BeanServeType)session.get(BeanServeType.class,serve_type_id);
			return bsty.getServetype_name();
		}
		else if(col==3)
			return String.valueOf(serve_price);
		else return "";
	}
}
