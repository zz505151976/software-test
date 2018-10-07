package Control;
import java.util.List;
import ITF.IPetManager;
import Model.BeanPet;
import Model.BeanCustomer;
import Model.BeanOperator;
import Util.BaseException;
import Util.HibernateUtil;
import org.hibernate.query.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class PetManager implements IPetManager{
	public void addpet(String customer_number,String pet_name,String pet_love_name,String pet_image) throws BaseException{
		//管理员为用户账号增加宠物
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanCustomer where customer_number=:number";
		Query query=session.createQuery(hql);
		query.setString("number",customer_number);
		query.setMaxResults(1);
		BeanCustomer bc=(BeanCustomer)query.uniqueResult();
		if(bc==null)
			throw new BaseException("账号不存在");
		
		BeanPet bp=new BeanPet();
		bp.setOwner_id(bc.getCustomer_id());
		bp.setPet_image(pet_image);
		bp.setPet_name(pet_name);
		bp.setPet_love_name(pet_love_name);
		session.save(bp);
		session.getTransaction().commit();
		
	}
	public List<BeanPet> searchonly(BeanCustomer beancustomer) throws BaseException{
		//当前登录用户可以看到自己的宠物
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		int id;
		id=beancustomer.getCustomer_id();
		String hql="from BeanPet where owner_id=:ownerid";
		Query query=session.createQuery(hql);
		query.setInteger("ownerid",id);
		List<BeanPet> bpet=query.list();
		session.getTransaction().commit();
		return bpet;
	}
	public List<BeanPet> searchall_type(String type) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanPet where pet_type=:pettype";
		Query query=session.createQuery(hql);
		query.setString("pettype",type);
		List<BeanPet> bpet=query.list();
		session.getTransaction().commit();
		return bpet;
	}
	public List<BeanPet> searchall_customer(int id) throws BaseException{
		//已知用户的ID，查询宠物信息
		if(id==0)
			throw new BaseException("请选择用户");
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanPet where owner_id=:ownerid";
		Query query=session.createQuery(hql);
		query.setInteger("ownerid",id);
		List<BeanPet> libp=query.list();
		return libp;
	}
	
}
