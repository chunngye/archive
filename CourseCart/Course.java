package CourseCart;

import java.sql.*;

public class Course {
	private int course_id;
	private String name;
	private String dept_name;
	private String building;
	private String inst_name;
	
	public int getCid() {
		return this.course_id;
	}
	public String getName() {
		return this.name;
	}
	public String getDname() {
		return this.dept_name;
	}
	public String getBd() {
		return this.building;
	}
	public String getIname() {
		return this.inst_name;
	}
	public void setCid(int int1) {
		// TODO Auto-generated method stub
		this.course_id=int1;
	}
	public void setName(String string) {
		// TODO Auto-generated method stub
		this.name=string;
	}
	public void setDname(String string) {
		// TODO Auto-generated method stub
		this.dept_name=string;
	}
	public void setBd(String string) {
		// TODO Auto-generated method stub
		this.building=string;
	}
	public void setIname(String string) {
		// TODO Auto-generated method stub
		this.inst_name=string;
	}
}
