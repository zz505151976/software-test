package ITF;
import Model.BeanServeType;
import java.util.List;
import Util.BaseException;
import Model.BeanServe;
public interface IServeManager {
	public void addserve(String serve_id,String serve_name,String serve_type_id,double serve_price) throws BaseException;
	public void addservetype(String servetype_id,String servetype_name,String servetype_descripe) throws BaseException;
	public void deleteserve(String serve_id) throws BaseException;
	public void deleteservetype(String servetype_id) throws BaseException;
	public void changeservename(BeanServe beanserve,String name) throws BaseException;
	public void changeserveprice(BeanServe beanserve,double serve_price) throws BaseException;
	public List<BeanServeType> showallservetype() throws BaseException;
	public List<BeanServe> showallserve() throws BaseException;
	public List<BeanServe> showservebytype(String servetype_id) throws BaseException;
	public List<BeanServe> showservename(String serve_name) throws BaseException;
}
