package CourseCart;

import java.sql.*;

public class Cart {
	private int no;
	private int ID;
	private String name;
	private int course_id;
	private String c_name;
	private String inst_name;
	private String dept_name;
	private String building;
	
	public void setCid(int i) {
		// TODO Auto-generated method stub
		this.course_id=i;
	}
	public void setName(String nextLine) {
		// TODO Auto-generated method stub
		this.name=nextLine;
	}
	public void setID(int nextInt) {
		// TODO Auto-generated method stub
		this.ID=nextInt;
	}
	public void setDname(String nextLine) {
		// TODO Auto-generated method stub
		this.dept_name=nextLine;
	}
	public void setIname(String nextLine) {
		// TODO Auto-generated method stub
		this.inst_name=nextLine;
	}
	public int getCid() {
		// TODO Auto-generated method stub
		return this.course_id;
	}
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	public int getID() {
		// TODO Auto-generated method stub
		return this.ID;
	}
	public String getDname() {
		// TODO Auto-generated method stub
		return this.dept_name;
	}
	public String getIname() {
		// TODO Auto-generated method stub
		return this.inst_name;
	}
	public int getNo(){
		// TODO Auto-generated method stub
		return this.no;
	}
	public void setNo(int int1) {
		// TODO Auto-generated method stub
		this.no=int1;
	}
	public void setCname(String string) {
		// TODO Auto-generated method stub
		this.c_name=string;
	}
	public void setBd(String string) {
		// TODO Auto-generated method stub
		this.building=string;
	}
	public Object getCname() {
		// TODO Auto-generated method stub
		return this.c_name;
	}
	public Object getBd() {
		// TODO Auto-generated method stub
		return this.building;
	}
	
}
