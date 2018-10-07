package Model;

public class BeanOperator {
	public static BeanOperator currentLoginoperator=null;
	public static final String[] optableTitles={"编号","姓名","密码","权限"};
	private String operator_id;
	private String operator_name;
	private String operator_pwd;  
	private String  level;
	public String getOperator_id() {
		return operator_id;
	}
	public void setOperator_id(String operator_id) {
		this.operator_id = operator_id;
	}
	public String getOperator_name() {
		return operator_name;
	}
	public void setOperator_name(String operator_name) {
		this.operator_name = operator_name;
	}
	public String getOperator_pwd() {
		return operator_pwd;
	}
	public void setOperator_pwd(String operator_pwd) {
		this.operator_pwd = operator_pwd;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getCell(int col) {
		if(col==0)
			return operator_id;
		else if(col==1)
			return operator_name;
		else if(col==2)
			return operator_pwd;
		else if(col==3)
			return level;
		else return "";
			
	}
	
	
}
