package Control;

import java.util.List;
import Model.BeanAdvanceorder;
import Model.BeanPet;
import Model.BeanCustomer;
import Model.BeanOperator;
import ITF.IOperatorManager;
import Util.BaseException;
import Util.HibernateUtil;
import Util.HibernateUtil;
import org.hibernate.query.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class OperatorManager implements IOperatorManager{
	public BeanOperator reg(String operator_id,String operator_name,String operator_pwd,String level) throws BaseException{
		//管理员注册操作者
		if(operator_id==null||operator_id.length()==0)
			throw new BaseException("账号不能为空");
		if(operator_name==null||operator_name.length()==0)
			throw new BaseException("姓名不能为空");
		if(operator_pwd==null||operator_pwd.length()==0)
			throw new BaseException("密码不能为空");
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanOperator where operator_id=:opid";
		Query query =session.createQuery(hql);
		query.setString("opid",operator_id);
		List<BeanOperator> list=query.list();
		if(list.size()>0)
			throw new BaseException("账号已存在");
		BeanOperator bo=new BeanOperator();
		bo.setOperator_pwd(operator_pwd);
		bo.setOperator_id(operator_id);
		bo.setOperator_name(operator_name);
		bo.setLevel(level);
		session.save(bo);
		session.getTransaction().commit();
		return bo;
	}
	public BeanOperator oplogin(String operator_id,String operator_pwd) throws BaseException{
		if(operator_id==null||operator_id.length()==0)
			throw new BaseException("账号不能为空");
		if(operator_pwd==null||operator_pwd.length()==0)
			throw new BaseException("密码不能为空");
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		BeanOperator bo=(BeanOperator)session.get(BeanOperator.class,operator_id);
		if(bo==null)
			throw new BaseException("账号不存在");
		if(!bo.getOperator_pwd().equals(operator_pwd))
			throw new BaseException("密码错误");
		return bo;
		
	}

	public void changelevel(String operator_id,String level) throws BaseException{
		//管理员更改权限
		if(operator_id==null||operator_id.length()==0)
			throw new BaseException("账号不能为空");
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		BeanOperator bo=(BeanOperator)session.get(BeanOperator.class,operator_id);
		bo.setLevel(level);
		session.getTransaction().commit();
	}
	public List<BeanCustomer> showallcustomer() throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanCustomer";
		Query query=session.createQuery(hql);
		List<BeanCustomer> libc=query.list();
		session.getTransaction().commit();
		return libc;
	}
	public void deletepet(int id) throws BaseException{
		//将宠物删除
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		//如果宠物身上预约服务未完成，不允许删除
		String hql="from BeanAdvanceorder where pet_id=:petid";
		Query query=session.createQuery(hql);
		query.setInteger("petid",id);
		List<BeanAdvanceorder>liva=query.list();
		for(BeanAdvanceorder ba:liva) {
			if(!ba.getAdvance_order_describe().equals("已经结束"))
				throw new BaseException("还有未完成的预约不能删除");
		}
		BeanPet bp=(BeanPet)session.get(BeanPet.class,id);
		session.delete(bp);
		session.getTransaction().commit();
	}

	public void deletecustomer(int id) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanPet where owner_id=:ownerid";
		Query query=session.createQuery(hql);
		query.setInteger("ownerid",id);
		List<BeanPet> libp=query.list();
		if(libp.size()>0)
			throw new BaseException("该用户还有宠物存在，不可删除");
		BeanCustomer bp=(BeanCustomer)session.get(BeanCustomer.class,id);
		session.delete(bp);
		session.getTransaction().commit();
		//考虑将被删除用户后面的用户编号向前移动
	}
	public List<BeanOperator> showalloperator() throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanOperator";
		Query query=session.createQuery(hql);
		List<BeanOperator> liop=query.list();
		session.getTransaction().commit();
		return liop;
	}
	public void opchangepwd(BeanOperator operator,String oldpwd,String newpwd)throws BaseException{
		if(!oldpwd.equals(operator.getOperator_pwd()))
			throw new BaseException("原密码输入错误");
		if(newpwd==null||newpwd.length()==0)
			throw new BaseException("新密码不能为空");
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		operator.setOperator_pwd(newpwd);
		session.update(operator);
		session.getTransaction().commit();
	}
	public void deleteop(String operator_id)throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		BeanOperator bo=(BeanOperator)session.get(BeanOperator.class,operator_id);
		if(bo==null)
			throw new BaseException("职员不存在");
		if(bo.getLevel()=="管理员")
			throw new BaseException("不能删除管理员");
		if(bo.getOperator_id().equals(BeanOperator.currentLoginoperator.getOperator_id()))
			throw new BaseException("不能删除自己");
		session.delete(bo);
		session.getTransaction().commit();
	}
}
