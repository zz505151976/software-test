package Control;

import java.util.List;
import ITF.IServeManager;
import Model.BeanServe;
import Model.BeanServeType;
import Util.BaseException;
import Util.HibernateUtil;
import org.hibernate.query.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class ServeManager implements IServeManager {
	public void addserve(String serve_id,String serve_name,String serve_type_id,double serve_price) throws BaseException{
		//增加服务
		if(serve_id==null||serve_id.length()==0)
			throw new BaseException("服务编号不能为空");
		if(serve_name==null||serve_name.length()==0)
			throw new BaseException("服务名称不能为空");
		if(serve_type_id==null||serve_type_id.length()==0)
			throw new BaseException("服务类型不能为空");
		if(serve_price==0)
			throw new BaseException("服务价格不能为空");
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		BeanServeType bstp=(BeanServeType)session.get(BeanServeType.class,serve_type_id);
		if(bstp==null)
			throw new BaseException("服务类型不存在");
		BeanServe bs=(BeanServe)session.get(BeanServe.class,serve_id);
		if(bs!=null)
			throw new BaseException("该编号已存在");
		String hql="from BeanServe where serve_name=:name";
		Query query =session.createQuery(hql);
		query.setString("name",serve_name);
		List<BeanServe> libs=query.list();
		if(libs.size()>0)
			throw new BaseException("该名称已存在");
		BeanServe bss=new BeanServe();
		bss.setServe_id(serve_id);
		bss.setServe_name(serve_name);
		bss.setServe_price(serve_price);
		bss.setServe_type_id(serve_type_id);
		session.save(bss);
		session.getTransaction().commit();
	}
	public void addservetype(String servetype_id,String servetype_name,String servetype_descripe) throws BaseException{
		//增加服务类别
		Session session =HibernateUtil.getSession();
		session.beginTransaction();
		//是否已存在id
		BeanServeType bsty=(BeanServeType)session.get(BeanServeType.class,servetype_id);
		if(bsty!=null)
			throw new BaseException("该编号已存在");
		//不允许重名存在
		String hql="from BeanServeType where servetype_name=:name";
		Query query=session.createQuery(hql);
		query.setString("name",servetype_name);
		List<BeanServeType> libs=query.list();
		if(libs.size()>0)
			throw new BaseException("该类型已存在");
		//重复校验完成
		BeanServeType bspe=new BeanServeType();
		bspe.setServetype_id(servetype_id);
		bspe.setServetype_name(servetype_name);
		bspe.setServetype_descripe(servetype_descripe);
		session.save(bspe);
		session.getTransaction().commit();
	}
	public void deleteserve(String serve_id) throws BaseException{
		//如果有订单未完成，该服务不能删除
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		BeanServe bs=(BeanServe)session.get(BeanServe.class,serve_id);
		if(bs==null)
			throw new BaseException("该服务不存在");
		session.delete(bs);
		session.getTransaction().commit();
	}
	public void changeservename(BeanServe beanserve,String name) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		BeanServe bs=(BeanServe)session.get(BeanServe.class,beanserve.getServe_id());
		bs.setServe_name(name);
		session.getTransaction().commit();
	}
	public void changeserveprice(BeanServe beanserve,double serve_price) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		BeanServe bs=(BeanServe)session.get(BeanServe.class,beanserve.getServe_id());
		bs.setServe_price(serve_price);
		session.getTransaction().commit();
	}
	public List<BeanServe> showservename(String serve_name) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanServe as b where b.serve_name like:name";
		Query query=session.createQuery(hql);
		query.setString("name","%"+serve_name+"%");
		List<BeanServe> libs=query.list();
		if(libs.size()==0)
			throw new BaseException("抱歉，您要查询的服务不存在");
		return libs;
	}
	public List<BeanServe> showallserve() throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanServe";
		Query query =session.createQuery(hql);
		List<BeanServe> libs=query.list();
		return libs;
	}
	public List<BeanServeType> showallservetype() throws BaseException{
		//返回所有服务类型
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanServeType";
		Query query =session.createQuery(hql);
		List<BeanServeType> libtp=query.list();
		session.getTransaction().commit();
		return libtp;
	}
	public List<BeanServe> showservebytype(String servetype_id) throws BaseException{
		//根据服务类型返回服务
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanServe where serve_type_id=:typeid";
		Query query=session.createQuery(hql);
		query.setString("typeid", servetype_id);
		List<BeanServe> libs=query.list();
		session.getTransaction().commit();
		return libs;
	}
	public void deleteservetype(String servetype_id) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		BeanServeType bsty=(BeanServeType)session.get(BeanServeType.class,servetype_id);
		if(bsty==null)
			throw new BaseException("该类型不存在");
		String hql="from BeanServe where serve_type_id=:typeid";
		Query query=session.createQuery(hql);
		query.setString("typeid", servetype_id);
		List<BeanServe> libs=query.list();
		if(libs.size()>0)
			throw new BaseException("该服务类型下还存在服务，不能删除");
		session.delete(bsty);
		session.getTransaction().commit();
	}
}
