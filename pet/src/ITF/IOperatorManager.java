package ITF;
import java.util.List;
import Model.BeanCustomer;
import Model.BeanOperator;
import Util.BaseException;
public interface IOperatorManager {
	public BeanOperator reg(String operator_id,String operator_name,String operator_pwd,String level) throws BaseException;
	public BeanOperator oplogin(String operator_id,String operator_pwd) throws BaseException;
	public void changelevel(String operator_id,String level) throws BaseException;//待定
	public List<BeanCustomer> showallcustomer() throws BaseException;//显示所有用户
	public List<BeanOperator> showalloperator() throws BaseException;//显示所有操作员
	public void opchangepwd(BeanOperator operator,String oldpwd,String newpwd)throws BaseException;//修改密码
	public void deletepet(int id) throws BaseException;
	public void deletecustomer(int id) throws BaseException;
	public void deleteop(String operator_id)throws BaseException;
}
