package Model;
import Util.BaseException;
import Util.HibernateUtil;
import org.hibernate.query.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class BeanPet {
	public static final String[] pettableTitles={"编号","昵称","别名","主人"};
	private int pet_id;
	private String pet_name;
	private String pet_love_name;
	private String pet_image;//暂定
 	private int owner_id;
	public int getPet_id() {
		return pet_id;
	}
	public void setPet_id(int pet_id) {
		this.pet_id = pet_id;
	}
	public String getPet_name() {
		return pet_name;
	}
	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}
	public String getPet_love_name() {
		return pet_love_name;
	}
	public void setPet_love_name(String pet_love_name) {
		this.pet_love_name = pet_love_name;
	}
	public int getOwner_id() {
		return owner_id;
	}
	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}
	public String getPet_image() {
		return pet_image;
	}
	public void setPet_image(String pet_image) {
		this.pet_image = pet_image;
	}
	
	public String getCell(int col){
		if(col==0)
			return String.valueOf(pet_id);
		else if(col==1) return pet_name;
		else if(col==2) return pet_love_name;
		else if(col==3) {
			Session session=HibernateUtil.getSession();
			session.beginTransaction();
			BeanCustomer bc=(BeanCustomer)session.get(BeanCustomer.class, owner_id);
			return bc.getCustomer_name();
		}
		else return "";
	}
	
}
