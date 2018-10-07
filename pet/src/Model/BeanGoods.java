package Model;
import Util.BaseException;
import Util.HibernateUtil;
import org.hibernate.query.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class BeanGoods {
	public static final String[] goodstableTitles={"编号","名称","类别","品牌","价格"};
	private String goods_id;
	private String goods_name;
	private String gooos_type_id;
	private String goods_brand;//品牌
	private double goods_price;
	private String goods_barcode;//条码
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGooos_type_id() {
		return gooos_type_id;
	}
	public void setGooos_type_id(String gooos_type_id) {
		this.gooos_type_id = gooos_type_id;
	}
	public String getGoods_brand() {
		return goods_brand;
	}
	public void setGoods_brand(String goods_brand) {
		this.goods_brand = goods_brand;
	}
	
	public double getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}
	public String getGoods_barcode() {
		return goods_barcode;
	}
	public void setGoods_barcode(String goods_barcode) {
		this.goods_barcode = goods_barcode;
	}
	public String getCell(int col) {
		if(col==0)
			return goods_id;
		else if(col==1)
			return goods_name;
		else if(col==2) {
			Session session=HibernateUtil.getSession();
			session.beginTransaction();
			BeanGoodsType bgty=(BeanGoodsType)session.get(BeanGoodsType.class,gooos_type_id);
			return bgty.getType_name();
		}
		else if(col==3)
			return goods_brand;
		else if(col==4)
			return String.valueOf(goods_price);
		else return "";
	}
	
}
