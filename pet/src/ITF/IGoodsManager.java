package ITF;
import java.util.List;
import Util.BaseException;
import Model.BeanGoods;
import Model.BeanGoodsType;
public interface IGoodsManager {
	public void	addgoodstype(String type_id,String type_name,String type_descripe) throws BaseException;
	public void deletegoodstype(String type_id) throws BaseException;
	public void addgoods(String goods_id,String goods_name,String gooos_type_id,String goods_brand,double goods_price,String goods_barcode) throws BaseException;
	public void deletegoods(String goods_id) throws BaseException;
	public List<BeanGoodsType> showallgoodstype() throws BaseException;
	public List<BeanGoods> showgoodsbytype(String gooos_type_id) throws BaseException;
	public List<BeanGoods> showgoodsbyname(String goods_name) throws BaseException;
}
