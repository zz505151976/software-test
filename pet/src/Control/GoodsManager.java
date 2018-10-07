package Control;
import java.util.List;
import org.hibernate.query.*;
import ITF.IGoodsManager;
import Model.BeanGoods;
import Model.BeanGoodsType;
import Util.BaseException;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class GoodsManager implements IGoodsManager{
	public void	addgoodstype(String type_id,String type_name,String type_descripe) throws BaseException{
		//添加商品类型
		Session session =HibernateUtil.getSession();
		session.beginTransaction();
		//ID是否被占用
		BeanGoodsType bgt=(BeanGoodsType)session.get(BeanGoodsType.class,type_id);
		if(bgt!=null)
			throw new BaseException("该编号已存在");
		String hql="from BeanGoodsType where type_name=:name";
		Query query=session.createQuery(hql);
		query.setString("name",type_name);
		List<BeanGoodsType> libgt=query.list();
		if(libgt.size()!=0)
			throw new BaseException("该类型已存在");
		//检验完成
		BeanGoodsType bgtt=new BeanGoodsType();
		bgtt.setType_descripe(type_descripe);
		bgtt.setType_id(type_id);
		bgtt.setType_name(type_name);
		session.save(bgtt);
		session.getTransaction().commit();
	}
	public void deletegoodstype(String type_id) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		BeanGoodsType bgttp=(BeanGoodsType)session.get(BeanGoodsType.class,type_id);
		if(bgttp==null)
			throw new BaseException("该类型不存在");
		//该商品类型下是否还存在商品
		String hql="from BeanGoods where gooos_type_id=:id";
		Query query=session.createQuery(hql);
		query.setString("id",type_id);
		List<BeanGoods> libg=query.list();
		if(libg.size()>0)
			throw new BaseException("该商品类型下还存在商品，不能删除");
		session.delete(bgttp);
		session.getTransaction().commit();
	}
	public void addgoods(String goods_id,String goods_name,String gooos_type_id,String goods_brand,double goods_price,String goods_barcode) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		//商品类型是否存在
		BeanGoodsType bgttp=(BeanGoodsType)session.get(BeanGoodsType.class,gooos_type_id);
		if(bgttp==null)
			throw new BaseException("该类型不存在");
		//商品编号是否已存在
		BeanGoods bgs=(BeanGoods)session.get(BeanGoods.class,goods_id);
		if(bgs!=null)
			throw new BaseException("该商品编号已存在");
		//同个商品(名称相同)，品牌不同可以添加，同品牌不可添加,
		String hql="from BeanGoods where goods_name=:name";
		Query query=session.createQuery(hql);
		query.setString("name",goods_name);
		List<BeanGoods>libg=query.list();
		for(BeanGoods bg:libg) {
			if(bg.getGoods_brand().equals(goods_brand))
				throw new BaseException("相同品牌的商品已存在");
		}
		//重复检测完成
		BeanGoods bgg=new BeanGoods();
		bgg.setGoods_barcode(goods_barcode);
		bgg.setGoods_brand(goods_brand);
		bgg.setGoods_id(goods_id);
		bgg.setGoods_name(goods_name);
		bgg.setGoods_price(goods_price);
		bgg.setGooos_type_id(gooos_type_id);
		session.save(bgg);
		session.getTransaction().commit();
		
	}
	public void deletegoods(String goods_id) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		BeanGoods bg=(BeanGoods)session.get(BeanGoods.class,goods_id);
		if(bg==null)
			throw new BaseException("该商品不存在");
		session.delete(bg);
		session.getTransaction().commit();
	}
	public List<BeanGoodsType> showallgoodstype() throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanGoodsType";
		Query query=session.createQuery(hql);
		List<BeanGoodsType>libgt=query.list();
		session.getTransaction().commit();
		return libgt;
	}
	public List<BeanGoods> showgoodsbytype(String gooos_type_id) throws BaseException{
		//按照类别查找
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanGoods where gooos_type_id=:typeid";
		Query query=session.createQuery(hql);
		query.setString("typeid",gooos_type_id);
		List<BeanGoods>libg=query.list();
		session.getTransaction().commit();
		return libg;
	}
	public List<BeanGoods> showgoodsbyname(String goods_name) throws BaseException{
		Session session=HibernateUtil.getSession();
		session.beginTransaction();
		String hql="from BeanGoods where goods_name like :name";
		Query query=session.createQuery(hql);
		query.setString("name","%"+goods_name+"%");
		List<BeanGoods>libg=query.list();
		session.getTransaction().commit();
		return libg;
	}
}
