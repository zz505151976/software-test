package ITF;
import java.util.List;
import Model.BeanOrder;
import Model.BeanCustomer;
import Util.BaseException;
public interface IOrderManager {
	public void buygoods(BeanCustomer customer,String goods_id,int order_count) throws BaseException;
	public void readysend(int order_id) throws BaseException;//操作员已发货
	public void readyreceive(int order_id) throws BaseException;//用户已接受
	public List<BeanOrder> showallorderbycus(BeanCustomer beancustomer) throws BaseException;//用户查看自己的订单
	public List<BeanOrder> showorderbycus(int id) throws BaseException;//操作员查看顾客订单
	public void cancelorder(int order_id) throws BaseException;//未发货前用户可以取消订单
	
}
