package Model;

public class BeanGoodsType {
	public static final String[] goodstypetableTitles={"编号","名称","描述"};
	private String type_id;
	private String type_name;
	private String type_descripe;
	
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getType_descripe() {
		return type_descripe;
	}
	public void setType_descripe(String type_descripe) {
		this.type_descripe = type_descripe;
	}
	public String getCell(int col) {
		if(col==0)
			return type_id;
		else if(col==1)
			return type_name;
		else if(col==2)
			return type_descripe;
		else return "";
	}
}
