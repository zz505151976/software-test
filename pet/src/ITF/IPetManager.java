package ITF;
import java.util.List;
import Util.BaseException;
import Model.BeanPet;
import Model.BeanCustomer;
import Model.BeanOperator;
public interface IPetManager {
	//操作员添加宠物
	public void addpet(String customer_number,String pet_name,String pet_love_name,String pet_image) throws BaseException;
	//用户查看自己的的宠物，操作员查看所有的宠物
	public List<BeanPet> searchonly(BeanCustomer beancustomer) throws BaseException;
	public List<BeanPet> searchall_type(String type) throws BaseException;
	public List<BeanPet> searchall_customer(int id) throws BaseException;
	
}
