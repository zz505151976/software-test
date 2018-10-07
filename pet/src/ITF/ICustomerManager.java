package ITF;
import java.util.List;
import Model.BeanCustomer;
import Util.BaseException;
public interface  ICustomerManager {
	public BeanCustomer reg(String phone,String pwd,String acpwd,String name,String mail,String other,String address) throws BaseException;
	public BeanCustomer login(String phone,String pwd) throws BaseException;
	public void changePwd(BeanCustomer customer,String oldPwd,String newPwd,String acnewPwd) throws BaseException;
	public void changephone(BeanCustomer customer,String newphone) throws BaseException;
	public void changeaddress(BeanCustomer customer,String newaddress) throws BaseException;
	public void changemail(BeanCustomer customer,String newmail) throws BaseException;
	public void changeother(BeanCustomer customer,String newother) throws BaseException;
	public double searchmoney(BeanCustomer customer) throws BaseException;
	public void addmoney(BeanCustomer customer,double money) throws BaseException;
	public List<BeanCustomer> searchcustomernumber(String number) throws BaseException;
	public List<BeanCustomer> searchcustomerphone(String phone) throws BaseException;
	public List<BeanCustomer> searchcustomername(String name) throws BaseException;
	
}
