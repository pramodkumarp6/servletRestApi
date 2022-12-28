package com.pramod.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pramod.utils.DBUtil;

public class DbOpertion {

	Connection connection = null;

	public List<Users> getData() {
		List<Users> listuser = new ArrayList<Users>();
		try {

			connection = DBUtil.getDataSource().getConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from users");
			while (rs.next()) {
				Users users = new Users();
				users.setId(rs.getInt("id"));
				users.setEmail(rs.getString("email"));
				users.setGender(rs.getString("gender"));
				users.setMobile(rs.getString("mobile"));
				users.setAddress(rs.getString("address"));

				listuser.add(users);

				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listuser;
	}
	
	
	public  Users  getfindById(int id) {
		
		Users users = new Users();
		
		try {
			connection = DBUtil.getDataSource().getConnection();
			
			PreparedStatement ps=connection.prepareStatement("select * FROM users WHERE id = ?");
			ps.setInt(1,id);
			   ResultSet rs=ps.executeQuery();  
			   System.out.println(id);
			   while (rs.next()) {
				
				users.setId(rs.getInt("id"));
				users.setEmail(rs.getString("email"));
				users.setGender(rs.getString("gender"));
				users.setMobile(rs.getString("mobile"));
				users.setAddress(rs.getString("address"));
				
				int empId = rs.getInt("id");
				String eName = rs.getString("mobile");
				String email = rs.getString("email");
				String salary = rs.getString("password");
				String bonus = rs.getString("address");

				System.out.println(empId + "\t" + eName + "\t" + salary + "\t" + email + "\t" + "\t" + bonus + "\t");
				
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	
	

	public int save(Users u) {
		int status = 0;
		System.out.println(u.toString());

		try {
			connection = DBUtil.getDataSource().getConnection();
			PreparedStatement ps = connection
					.prepareStatement("insert into users(email,password,gender,mobile,address) values (?,?,?,?,?)");
			ps.setString(1, u.getEmail());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getGender());
			ps.setString(4, u.getMobile());
			ps.setString(5, u.getAddress());
			
			status = ps.executeUpdate();
			
			connection.commit();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;

	}

	public int update(Users u) {
	
		int status = 0;
		try {
			connection = DBUtil.getDataSource().getConnection();
			PreparedStatement ps = connection
					.prepareStatement("update users set email=?,password=?,gender=?,mobile=?,address=? where id=?");
			ps.setString(1, u.getEmail());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getGender());
			ps.setString(4, u.getMobile());
			ps.setString(5, u.getAddress());
			ps.setInt(6, u.getId());
			status = ps.executeUpdate();

			connection.commit();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public Users deleteById(int id) {
		Users users = new Users();
		System.out.println("pramodkumar"+id);
		int status = 0;
		try {
			connection = DBUtil.getDataSource().getConnection();
			PreparedStatement ps = connection.prepareStatement("DELETE  FROM users WHERE id=?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
        System.out.println(status);
        connection.commit();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}


	

}
