package ITF;
import java.util.List;
import Model.BeanCustomer;
import Model.BeanAdvanceorder;
import Util.BaseException;
import java.util.Date;
public interface IAdvanceorder {
	public void cuaddadvance(String advance_serve_id,int pet_id,String advance_time) throws BaseException;//客户预约
	public void opaddadvance(String customer_number,String advance_serve_id,int pet_id,Date advance_time);//现场操作员添加预约
	public void canceladvance(int advance_id) throws BaseException;//客户取消预约
	public void beginadvance(int advance_id) throws BaseException;//开始服务
	public void readyadvance(int advance_id) throws BaseException;//预约完成
	public List<BeanAdvanceorder> showownadvance(BeanCustomer customer) throws BaseException;
	public List<BeanAdvanceorder> showwhoadvance(int customer_id) throws BaseException;
}
