package CourseCart;

import java.sql.*;

import java.util.Scanner;

public class CartSystem {
	//Field
	private Scanner scanner = new Scanner(System.in);
	private Connection conn;
	
	//Constructor
	public CartSystem() {
		try {
			//JDBC Driver ���
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//�����ϱ�
			conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/coursecart", 
				"java", 
				"mysql"
			);
		} catch(Exception e) {
			e.printStackTrace();
			//exit();
		}
	}
	
		
	public void list() {
		//���� ��� ����ϱ�
		System.out.println();
		System.out.println("[���� ���]");
		System.out.println("-----------------------------------------------------------");
		System.out.printf("%-8s%-8s\t%-12s\t%-5s\t%-3s\t\n", "�����ڵ�", "������", "�����а�", "�����ǹ�", "������");
		System.out.println("-----------------------------------------------------------");
		try {
			String sql = "" +
				"SELECT course_id, name, dept_name, building, inst_name " +
				"FROM course";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {		
				Course course = new Course();
				course.setCid(rs.getInt("course_id"));
				course.setName(rs.getString("name"));
				course.setDname(rs.getString("dept_name"));
				course.setBd(rs.getString("building"));
				course.setIname(rs.getString("inst_name"));
				System.out.printf("%-8s%-8s\t%-12s\t%-5s\t%-3s\t\n", 
						course.getCid(), 
						course.getName(),
						course.getDname(),
						course.getBd(),
						course.getIname());
			}
			rs.close();
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
			exit();
		}
		mainMenu();
	}

	public void mainMenu() {
		System.out.println();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("���θ޴�: 1.��ٱ��� ��� | 2.���� ���� ���� | 3.���� ���� ����ϱ� | 4.Exit");
		System.out.print("�޴�����: ");
		int menuNo = Integer.parseInt(scanner.nextLine());
		System.out.println();
		switch(menuNo) {
			case 1:
				create();
				break;
			case 2:
				read();
				break;
			case 3:
			    Cart cart=new Cart();
			    delete(cart);
				break;
			case 4:
				exit();
				break;
			default :
				System.out.println("�޴��� �ٽ� �Է��ϼ���.");
				mainMenu();
		}
	}	

	
	public void create(){
		//��ٱ��Ͽ� �߰��ϱ�
		//�й�, �̸��� �Է¹��� ��, �������� course���� ã�Ƽ� cart�� �� �� �߰��ϱ�	
		System.out.println("[��ٱ��� ���]");
		System.out.println("�й�: ");
		int ID = Integer.parseInt(scanner.nextLine());
		String name=null;
		String dept=null; //student ���̺��� ������ �а� ����
		int check;
		try {
		String sqlll=""+
		"SELECT ID, name, dept_name " +
		"FROM student " +
		"WHERE ID=?";
		
		PreparedStatement pstmttt=conn.prepareStatement(sqlll);
		pstmttt.setInt(1, ID);
		
		ResultSet rss=pstmttt.executeQuery();
		if(rss.next()) {
			name=rss.getString("name");
			dept=rss.getString("dept_name");
		}else {
			System.out.println("�й��� �ٽ� �Է��ϼ���.");
			create();
		}
		while(true) {
		System.out.println("*****�����ڵ带 �Է��ϼ���*****");
		System.out.println("�����ڵ�: ");
		int course_id = Integer.parseInt(scanner.nextLine());
		String c_name=null;
		String inst_name=null;
		String dept_name=null;
		String building=null;
		int cid;
		try {
			String sql = "" +
				"SELECT course_id, name, inst_name, dept_name, building " +
				"FROM course " +
				"WHERE course_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, course_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				c_name=rs.getString("name");
				inst_name=rs.getString("inst_name");
				dept_name=rs.getString("dept_name");
				building=rs.getString("building");
			}else {
				System.out.println("�����ڵ带 �ٽ� �Է��ϼ���.");
				continue;
			}
			rs.close();
			pstmt.close();
			if(!(dept_name.equals(dept))) {
				System.out.println("Ÿ�� ���� �����Դϴ�.");
				continue;
			}
			String sqllll=""+
					"SELECT course_id " +
					"FROM cart " +
					"WHERE ID=?";
					
			PreparedStatement pstmtttt=conn.prepareStatement(sqllll);
			pstmtttt.setInt(1, ID);
			ResultSet rsss=pstmtttt.executeQuery();
			while(rsss.next()) {
				cid=rsss.getInt("course_id");
				if(cid==course_id) {
					System.out.println("�̹� ��ҽ��ϴ�.");
					mainMenu();
				}
			}
			
			String sqll=""+
					"INSERT into cart (ID, name, course_id, c_name, inst_name, dept_name, building) "+
					"VALUE (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmtt = conn.prepareStatement(sqll);
			pstmtt.setInt(1, ID);
		    pstmtt.setString(2, name);
		    pstmtt.setInt(3, course_id);
		    pstmtt.setString(4, c_name);
		    pstmtt.setString(5, inst_name);
		    pstmtt.setString(6, dept_name);
		    pstmtt.setString(7, building);
		    pstmtt.execute();
		    }catch (Exception e) {
			e.printStackTrace();
			exit();	
		}
		System.out.println(c_name+"["+course_id+"]"+"�� ��ҽ��ϴ�.");
		System.out.println("��� �����ðڽ��ϱ�? Y: 1, N: 0");
		check=Integer.parseInt(scanner.nextLine());
		if(check==1)
			continue;
		else{
			break;
		}
	}
	rss.close();
	pstmttt.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		mainMenu();
	}
		
	public void read() {
		System.out.println("[���� ���� ����]");
		System.out.print("�й�: ");
		int ID = Integer.parseInt(scanner.nextLine());
		try {
			String sql = "" +
				"SELECT no, ID, name, course_id, c_name, inst_name, dept_name, building " +
				"FROM cart " +
				"WHERE ID=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ID);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("--------------------------------------------------------------------------------------------");
			System.out.printf("%-5s\t%-8s\t%-5s\t%-6s\t%-12s\t%-8s\t%-12s\t%-3s\t\n", "��Ϲ�ȣ", "�й�", "�̸�", "�����ڵ�", "���¸�", "������", "�����а�", "�ǹ�");
			System.out.println("--------------------------------------------------------------------------------------------");
			//������ �� n�� ��������
			while(rs.next()) {
				Cart cart = new Cart();
				cart.setNo(rs.getInt("no"));
				cart.setID(rs.getInt("ID"));
				cart.setName(rs.getString("name"));
				cart.setCid(rs.getInt("course_id"));
				cart.setCname(rs.getString("c_name"));
				cart.setIname(rs.getString("inst_name"));
			    cart.setDname(rs.getString("dept_name"));
			    cart.setBd(rs.getString("building"));
				
				System.out.printf("%-5s\t%-8s\t%-5s\t%-6s\t%-12s\t%-8s\t%-12s\t%-3s\t\n",
				cart.getNo(),
				cart.getID(),
				cart.getName(),
				cart.getCid(),
				cart.getCname(),
				cart.getIname(),
				cart.getDname(),
				cart.getBd());
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}
		mainMenu();
}


	public void delete(Cart cart) {
		//��ٱ��� ����
		int check;
		System.out.println("[���� ���� �����ϱ�]");
		while(true) {
		System.out.print("��Ϲ�ȣ: ");
		int no = Integer.parseInt(scanner.nextLine());
		try {
			String sql = "DELETE FROM cart WHERE no=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}
		System.out.println("�����Ǿ����ϴ�.");
		System.out.println("��� �Ͻðڽ��ϱ�? Y: 1, N: 0");
		check=Integer.parseInt(scanner.nextLine());
		if(check==1)
			continue;
		else{
			break;
		}
	}
	mainMenu();
}
	
	public void exit() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
		System.out.println("** ��ٱ��� ���� **");
		System.exit(0);
	}
	
	public static void main(String[] args) {
		CartSystem cartSystem = new CartSystem();
		cartSystem.list();
	}
}

