package Control;
import Model.*;
import ITF.*;
import Util.*;
import java.util.List;
import org.hibernate.query.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CustomerManager implements ICustomerManager{
	public BeanCustomer reg(String phone,String pwd,String acpwd,String name,String mail,String other,String address) throws BaseException {
		if(pwd==null||pwd.length()==0)
			throw new BaseException("密码不能为空");
		if(!pwd.equals(acpwd))
			throw new BaseException("两次输入密码不一致");
		//判断输入的手机号是否合法
		if(phone.length()!=11) {
			throw new BaseException("手机号应该为11位");
		}
		else {
			 String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
			 Pattern p = Pattern.compile(regex);
			 Matcher m = p.matcher(phone);
			 boolean isMatch = m.matches();
			 if(!isMatch){
				 throw new BaseException("您的手机号格式错误");
	            }
		}
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanCustomer where customer_number=:cusphone";//账号不能重复
		Query query =session.createQuery(hql);
		query.setString("cusphone",phone);
		List<BeanCustomer>bc=query.list();
		if(bc.size()>0) 
			throw new BaseException("该手机号已经注册，如忘记密码请选择找回密码");//ps:找回密码
		//首次注册的手机号码，要加入数据库
		BeanCustomer bcs=new BeanCustomer();
		bcs.setCustomer_number(phone);//第一次注册的
		bcs.setPhone(phone);
		bcs.setCustomer_pwd(pwd);
		bcs.setCustomer_name(name);
		bcs.setMail(mail);
		bcs.setOther_contact(other);
		bcs.setAddress(address);
		bcs.setMoney(0);
		session.save(bcs);
		session.getTransaction().commit();	
		return bcs;
	}
	public BeanCustomer login(String phone,String pwd) throws BaseException{
		if(phone==null||phone.length()==0)
			throw new BaseException("账号不能为空");
		if(pwd==null||pwd.length()==0)
			throw new BaseException("密码不能为空");
		if(phone.length()!=11) {
			throw new BaseException("账号应该为11位");
		}
		else {
			 String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
			 Pattern p = Pattern.compile(regex);
			 Matcher m = p.matcher(phone);
			 boolean isMatch = m.matches();
			 if(!isMatch){
				 throw new BaseException("您的帐号格式错误");
	            }
		}
		Session session =HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanCustomer where customer_number=:cusphone";
		Query query =session.createQuery(hql);
		query.setString("cusphone",phone);
		query.setMaxResults(1);
		BeanCustomer customer=(BeanCustomer)query.uniqueResult();
		session.getTransaction().commit();
		if(customer==null)
			throw new BaseException("该账号不存在");
		if(!customer.getCustomer_pwd().equals(pwd))
			throw new BaseException("密码错误");
		return customer;
		
	}
	public void changePwd(BeanCustomer customer,String oldPwd,String newPwd,String acnewPwd) throws BaseException{
		//修改密码
		//输入旧密码
		if(!oldPwd.equals(customer.getCustomer_pwd()))
			throw new BaseException("原密码输入错误");
		if(newPwd==null||newPwd.length()==0)
			throw new BaseException("新密码不能为空");
		if(!acnewPwd.equals(newPwd))
			throw new BaseException("两次密码输入不一致");
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		customer.setCustomer_pwd(newPwd);
		session.update(customer);
		session.getTransaction().commit();
	}
	public void changephone(BeanCustomer customer,String newphone) throws BaseException{
		//修改绑定手机号，账号不变
		if(newphone.length()!=11) {
			throw new BaseException("手机号应该为11位");
		}
		else {
			 String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
			 Pattern p = Pattern.compile(regex);
			 Matcher m = p.matcher(newphone);
			 boolean isMatch = m.matches();
			 if(!isMatch){
				 throw new BaseException("您的手机号格式错误");
	            }
		}
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		customer.setPhone(newphone);
		session.update(customer);
		session.getTransaction().commit();
	}
	public void changeaddress(BeanCustomer customer,String newaddress) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		customer.setAddress(newaddress);
		session.update(customer);
		session.getTransaction().commit();
	}
	public void changeother(BeanCustomer customer,String newother) throws BaseException{
		//修改其他联系方式
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		customer.setOther_contact(newother);
		session.update(customer);
		session.getTransaction().commit();
	}
	public void changemail(BeanCustomer customer,String newmail) throws BaseException{
		//加入一个判断邮箱是否正规的方法
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		customer.setMail(newmail);
		session.update(customer);
		session.getTransaction().commit();
	}
	public List<BeanCustomer> searchcustomernumber(String number) throws BaseException{
		//通过用户账号来模糊查询
		if(number==null||number.length()==0)
			throw new BaseException("用户账号不能为空");
		Session session =HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanCustomer as b where b.customer_number like :num";
		Query query=session.createQuery(hql);
		query.setString("num","%"+number+"%");
		List<BeanCustomer> libc=query.list();
		if(libc.size()==0)
			throw new BaseException("抱歉，没有查询到相关用户");
		return libc;
	}
	public List<BeanCustomer> searchcustomerphone(String phone) throws BaseException{
		if(phone==null||phone.length()==0)
			throw new BaseException("用户手机号码不能为空");
		Session session =HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanCustomer as b where b.phone like :ph";
		Query query=session.createQuery(hql);
		query.setString("ph","%"+phone+"%");
		List<BeanCustomer> libc=query.list();
		if(libc.size()==0)
			throw new BaseException("抱歉，没有查询到相关用户");
		return libc;
	}
	public List<BeanCustomer> searchcustomername(String name) throws BaseException{
		//if(name==null||name.length()==0)
		//	throw new BaseException("用户姓名不能为空");
		Session session =HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanCustomer as b where b.customer_name like :cusname";
		Query query=session.createQuery(hql);
		query.setString("cusname","%"+name+"%");
		List<BeanCustomer> libc=query.list();
		if(libc.size()==0)
			throw new BaseException("抱歉，没有查询到相关用户");
		return libc;
	}
	public double searchmoney(BeanCustomer customer) throws BaseException{
		//查询余额
		return customer.getMoney();
	}
	public void addmoney(BeanCustomer customer,double money) throws BaseException{
		if(money==0)
			throw new BaseException("请输入正确的数字");
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		customer.setMoney(customer.getMoney()+money);
		session.update(customer);
		session.getTransaction().commit();
	}

}
