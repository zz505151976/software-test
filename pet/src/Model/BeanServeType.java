package Model;

public class BeanServeType {
	public static final String[] servetypetableTitles={"编号","名称","描述"};
	private String servetype_id;
	private String servetype_name;
	private String servetype_descripe;
	public String getServetype_id() {
		return servetype_id;
	}
	public void setServetype_id(String servetype_id) {
		this.servetype_id = servetype_id;
	}
	public String getServetype_name() {
		return servetype_name;
	}
	public void setServetype_name(String servetype_name) {
		this.servetype_name = servetype_name;
	}
	public String getServetype_descripe() {
		return servetype_descripe;
	}
	public void setServetype_descripe(String servetype_descripe) {
		this.servetype_descripe = servetype_descripe;
	}
	public String getCell(int col) {
		if(col==0)
			return servetype_id;
		else if(col==1)
			return servetype_name;
		else if(col==2)
			return servetype_descripe;
		else return "";
	}
	
}
