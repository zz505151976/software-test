package Control;
import ITF.IAdvanceorder;
import Model.BeanAdvanceorder;
import Model.BeanCustomer;
import Model.BeanPet;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import org.hibernate.query.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import Util.BaseException;
import Util.HibernateUtil;

public class Advanceorder implements IAdvanceorder{
	public void cuaddadvance(String advance_serve_id,int pet_id,String advance_time) throws BaseException{
		//客户预约
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanPet where pet_id=:id";
		Query query=session.createQuery(hql);
		query.setInteger("id",pet_id);
		BeanPet one=(BeanPet)query.uniqueResult();
		BeanAdvanceorder ba=new BeanAdvanceorder();
		ba.setAdvance_order_describe("尚未开始");//预约状态
		ba.setAdvance_serve_id(advance_serve_id);
		try {
			SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd");
			Date startdate=simple.parse(advance_time);
			ba.setAdvance_time(startdate);
		}catch(Exception e) {
			e.printStackTrace();
		}
		ba.setPet_id(pet_id);
		ba.setCustomer_id(one.getOwner_id());
		session.save(ba);
		session.getTransaction().commit();
	}
	public void opaddadvance(String customer_number,String advance_serve_id,int pet_id,Date advance_time) {
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanCustomer where customer_number=:name";
		Query query=session.createQuery(hql);
		query.setString("name",customer_number);
		BeanCustomer one=(BeanCustomer)query.uniqueResult();
		BeanAdvanceorder ba=new BeanAdvanceorder();
		ba.setAdvance_order_describe("尚未开始");
		ba.setAdvance_serve_id(advance_serve_id);
		ba.setAdvance_time(advance_time);
		ba.setCustomer_id(one.getCustomer_id());
		ba.setPet_id(pet_id);
		session.save(ba);
		session.getTransaction().commit();
	}
	public void canceladvance(int advance_id) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		BeanAdvanceorder ba=(BeanAdvanceorder)session.get(BeanAdvanceorder.class,advance_id);
		if(ba.getAdvance_order_describe().equals("正在进行"))
			throw new BaseException("预约已经开始，无法取消");
		if(ba.getAdvance_order_describe().equals("已经结束"))
			throw new BaseException("预约已经结束");
		session.delete(ba);
		session.getTransaction().commit();
	}
	public void beginadvance(int advance_id) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		BeanAdvanceorder ba=(BeanAdvanceorder)session.get(BeanAdvanceorder.class,advance_id);
		if(ba.getAdvance_order_describe().equals("正在进行"))
			throw new BaseException("预约已经开始");
		if(ba.getAdvance_order_describe().equals("已经结束"))
			throw new BaseException("预约已经结束");
		ba.setAdvance_order_describe("正在进行");
		session.update(ba);
		session.getTransaction().commit();
	}
	public void readyadvance(int advance_id) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		BeanAdvanceorder ba=(BeanAdvanceorder)session.get(BeanAdvanceorder.class,advance_id);
		if(ba.getAdvance_order_describe().equals("尚未开始"))
			throw new BaseException("预约还没开始");
		if(ba.getAdvance_order_describe().equals("已经结束"))
			throw new BaseException("预约已经结束");
		ba.setAdvance_real_time(new java.sql.Timestamp(System.currentTimeMillis()));
		ba.setAdvance_order_describe("已经结束");
		session.update(ba);
		session.getTransaction().commit();
	}
	public List<BeanAdvanceorder> showownadvance(BeanCustomer customer) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanAdvanceorder where customer_id=:id";
		Query query=session.createQuery(hql);
		query.setInteger("id",customer.getCustomer_id());
		List<BeanAdvanceorder> liba=query.list();
		return liba;
	}
	public List<BeanAdvanceorder> showwhoadvance(int customer_id) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanAdvanceorder where customer_id=:number";
		Query query=session.createQuery(hql);
		query.setInteger("number", customer_id);
		List<BeanAdvanceorder> liba=query.list();
		return liba;
	}
}
