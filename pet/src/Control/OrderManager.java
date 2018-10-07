package Control;
import Model.*;
import Util.BaseException;
import Util.HibernateUtil;
import ITF.IOrderManager;
import org.hibernate.query.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
public class OrderManager implements IOrderManager{
	public void buygoods(BeanCustomer customer,String goods_id,int order_count) throws BaseException{
		//用户下订单购买商品
		if(order_count==0)
			throw new BaseException("数量不能为空");
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		BeanGoods bgs=(BeanGoods)session.get(BeanGoods.class,goods_id);
		if(bgs==null)
			throw new BaseException("该商品不存在");
		String typeid=bgs.getGooos_type_id();
		String goodsname=bgs.getGoods_name();
		double goodsprice=bgs.getGoods_price();
		BeanGoodsType bgtp=(BeanGoodsType)session.get(BeanGoodsType.class,typeid);
		if(bgtp==null)
			throw new BaseException("该商品类型不存在");
		if(customer.getMoney()<(goodsprice*order_count))
			throw new BaseException("余额不足");
		String typename=bgtp.getType_name();
		BeanOrder bo=new BeanOrder();
		bo.setCustomer_id(customer.getCustomer_id());
		bo.setGoods_id(goods_id);
		bo.setGoods_name(goodsname);
		bo.setGoods_type_name(typename);
		bo.setOrder_count(order_count);
		bo.setOrder_price(goodsprice);
		bo.setOrder_sum(goodsprice*order_count);
		bo.setOrder_state("等待商家发货");
		customer.setMoney(customer.getMoney()-goodsprice*order_count);
		session.save(bo);
		session.update(customer);
		session.getTransaction().commit();
	}
	public void readysend(int order_id) throws BaseException{
		//操作员调整订单状态
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		BeanOrder bo=(BeanOrder)session.get(BeanOrder.class,order_id);
		if(bo==null)
			throw new BaseException("订单不存在");
		bo.setOrder_state("商家已发货");
		session.update(bo);
		session.getTransaction().commit();
	}
	public void readyreceive(int order_id) throws BaseException{
		//客户已收货
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		BeanOrder bo=(BeanOrder)session.get(BeanOrder.class,order_id);
		if(bo==null)
			throw new BaseException("订单不存在");
		if(!bo.getOrder_state().equals("商家已发货"))
			throw new BaseException("商家未发货");
		bo.setOrder_state("已签收");
		session.update(bo);
		session.getTransaction().commit();
	}
	public void cancelorder(int order_id) throws BaseException{
		//客户取消订单
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		BeanOrder bo=(BeanOrder)session.get(BeanOrder.class,order_id);
		if(bo==null)
			throw new BaseException("订单不存在");
		if(bo.getOrder_state().equals("商家已发货"))
			throw new BaseException("商家已发货，不能取消订单");
		session.delete(bo);
		session.getTransaction().commit();
	}
	public List<BeanOrder> showallorderbycus(BeanCustomer beancustomer) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanOrder where customer_id=:id";
		Query query=session.createQuery(hql);
		query.setInteger("id",beancustomer.getCustomer_id());
		List<BeanOrder> libo=query.list();
		session.getTransaction().commit();
		return libo;
	}
	public List<BeanOrder> showorderbycus(int id) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanOrder where customer_id=:id";
		Query query=session.createQuery(hql);
		query.setInteger("id", id);
		List<BeanOrder> libo=query.list();
		session.getTransaction().commit();
		return libo;
	}
}
