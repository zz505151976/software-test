package PetGO;
import Control.*;
import ITF.*;
public class PetUtil {
	public static ICustomerManager customerManage=new CustomerManager();
	public static IOperatorManager operatorManage=new OperatorManager();
	public static IPetManager petManage=new PetManager();
	public static IGoodsManager goodsManage=new GoodsManager();
	public static IServeManager serverManage=new ServeManager();
	public static IAdvanceorder advanceManage=new Advanceorder();
	public static IOrderManager orderManage=new OrderManager();
}
