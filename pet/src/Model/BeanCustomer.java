package Model;

public class BeanCustomer {
	public static BeanCustomer currentLogincustomer=null;
	public static final String[] customertableTitles={"编号","用户名","姓名","邮箱","手机号","其他联系方式","地址","余额"};
	private int customer_id;
	private String customer_number;//账号
	private String customer_name;
	private String mail;
	private String phone;//绑定手机号
	private String other_contact;
	private String customer_pwd;
	private String address;
	private double money;//余额
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOther_contact() {
		return other_contact;
	}
	public void setOther_contact(String other_contact) {
		this.other_contact = other_contact;
	}
	public String getCustomer_pwd() {
		return customer_pwd;
	}
	public void setCustomer_pwd(String customer_pwd) {
		this.customer_pwd = customer_pwd;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getCustomer_number() {
		return customer_number;
	}
	public void setCustomer_number(String customer_number) {
		this.customer_number = customer_number;
	}
	public String getCell(int col) {
		if(col==0) {
			return String.valueOf(customer_id);
		}
		else if(col==1)
			return customer_number;
		else if(col==2)
			return customer_name;
		else if(col==3)
			return mail;
		else if(col==4)
			return phone;
		else if(col==5)
			return other_contact;
		else if(col==6)
			return address;
		else if(col==7)
			return String.valueOf(money);
		else return "";
	}
	
}
