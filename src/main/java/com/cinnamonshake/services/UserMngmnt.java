package com.cinnamonshake.services;

import com.cinnamonshake.models.*;
import java.sql.*;

public class UserMngmnt {

    private User currentUser;
	
    public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	private String fetchPassword(String username){
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/SmartBMSdb","root","Cinnamon_08");
			Statement stmt=con.createStatement();
			String sql="select password from users where username = '"+username+"';";
			ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) { 
                return rs.getString(1); 
            }
			con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
        return null;
    }
    private String fetchRealname(String username){
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/SmartBMSdb","root","Cinnamon_08");
			Statement stmt=con.createStatement();
			String sql="select realname from users where username = '"+username+"';";
			ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) { 
                return rs.getString(1); 
            }
			con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
        return null;
    }

    private String fetchEmail(String username){
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/SmartBMSdb","root","Cinnamon_08");
			Statement stmt=con.createStatement();
			String sql="select email from users where username = '"+username+"';";
			ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) { 
                return rs.getString(1); 
            }
			con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
        return null;
    }
    public long fetchBmsID(String username){
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/SmartBMSdb","root","Cinnamon_08");
			Statement stmt=con.createStatement();
			String sql="select bmsID from users where username = '"+username+"';";
			ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) { 
                return rs.getLong(1); 
            }
			con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return 0;
    }
	private Battery fetchBattery(long id){
		Battery battery = null;
		String[] arr = new String[5];
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/SmartBMSdb","root","Cinnamon_08");
			Statement stmt=con.createStatement();
			String sql = "select * from batteries where bmsID = "+id+";";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				arr[0] = rs.getString("bmsID");
				arr[1] = rs.getString("batteryCapacity");
				arr[2] = rs.getString("ratedCycles");
				arr[3] = rs.getString("batteryType");
				arr[4] = rs.getString("batteryCompany");
			}
			battery = new Battery(id, Double.parseDouble(arr[1]), Integer.parseInt(arr[2]),arr[3],arr[4]);
			con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return battery;
	}

    public boolean userExists(String username){
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/SmartBMSdb","root","Cinnamon_08");
			Statement stmt=con.createStatement();
			String sql="select exists (select 1 from users where username = '"+username+"');";
			ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) { 
                return rs.getBoolean(1); 
            }

			con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
        return false;
    }
	public boolean bmsExists(long id){
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/SmartBMSdb","root","Cinnamon_08");
			Statement stmt=con.createStatement();
			String sql="select exists (select 1 from users where bmsID = "+id+");";
			ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) { 
                return rs.getBoolean(1); 
            }

			con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
        return false;
    }

    public String registerUser(String username, String realname, String password, String email){

        User usr;
        if(userExists(username))
            return "Username already taken, please enter a new one and try again!";

        usr = new User(username,password,realname,email);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/SmartBMSdb","root","Cinnamon_08");
			Statement stmt=con.createStatement();

			String sql="Insert into users (username, realname, password, email) values('"+
					usr.getUsername()+"','"+usr.getRealname()+"','"+usr.getPassword()+"','"+usr.getEmailAddress()+"');";
			stmt.executeUpdate(sql);
			con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

        currentUser = usr;
        return username+" successfully registered!";
    }

    public String loginUser(String username, String password){

        if(!userExists(username))
            return "User "+username+ " does not exist! Please re-enter the correct username or register a new account.";

        if(!fetchPassword(username).equals(password))
            return "Password does not match! Please try again.";
        
        currentUser = new User(username, password, fetchRealname(username), fetchEmail(username));
		long bmsid = fetchBmsID(username);
		if(bmsid>0){
			Battery battery = fetchBattery(bmsid);
			BMS bms = new BMS(bmsid);
			bms.setBattery(battery);
			currentUser.setBms(bms);
		}

        return username +" successfully logged in!";
    }

    public String logoutUser(){
        String currentUsersName = currentUser.getUsername();
        currentUser = null;
        return currentUsersName + " successfully logged out!";
    }

    public String updateDetails(int x, String newDetail){
        String fieldName = null;
        switch (x){
            case 0:
                fieldName = "realname";
                currentUser.setRealname(newDetail);
                break; 
            case 1:
                fieldName = "email";
                currentUser.setEmailAddress(newDetail);
                break;
            default:
                fieldName = null;
        }

        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/SmartBMSdb","root","Cinnamon_08");
			Statement stmt=con.createStatement();
			String sql="update users set "+fieldName+" = '"+newDetail+"' where username = '"+currentUser.getUsername()+"';";
			stmt.executeUpdate(sql);
			con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

        return fieldName+" updated to "+newDetail+" successfully!";
    }

    public String passwordReset(String oldPassword, String newPassword){

        String oldPassFetched = fetchPassword(currentUser.getUsername());
        if(!oldPassword.equals(oldPassFetched)){
            return "Old Password entered is incorrect, please try again!";
        }

        currentUser.setPassword(newPassword);
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/SmartBMSdb","root","Cinnamon_08");
			Statement stmt=con.createStatement();
			String sql="update users set password = '"+newPassword+"' where username = '"+currentUser.getUsername()+"';";
			stmt.executeUpdate(sql);
			con.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

        return "Password updated successfully!";
    }
	public String BMSsetup(long id, double batteryCapacity, int ratedCycles, String batteryType, String batteryCompany){
		Battery battery = new Battery(id, batteryCapacity, ratedCycles, batteryType, batteryCompany);
		BMS bms = new BMS(id);
		bms.setBattery(battery);
		currentUser.setBms(bms);

		if(bmsExists(id)){
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection(  
						"jdbc:mysql://localhost:3306/SmartBMSdb","root","Cinnamon_08");
				Statement stmt=con.createStatement();
				String sql = "update batteries set batteryCapacity = "+batteryCapacity
					+", ratedCycles = "+ ratedCycles
					+", batteryType = '"+batteryType
					+"', batteryCompany = '"+batteryCompany
					+"' where bmsid = "+id+";";
				stmt.executeUpdate(sql);
				con.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			return "BMS has been updated!";
		} else{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection(  
						"jdbc:mysql://localhost:3306/SmartBMSdb","root","Cinnamon_08");
				Statement stmt=con.createStatement();
				String sql = "Update users set bmsID = "+id+" where username = '"+currentUser.getUsername()+"';";
				stmt.executeUpdate(sql);
				sql="Insert into bmsentries (bmsID, feedLocation) values("+id+", '"+currentUser.getBms().getFeedLocation()+"');";
				stmt.executeUpdate(sql);
				sql = "Insert into batteries (bmsID, batteryCapacity, ratedCycles, batteryType, batteryCompany) values("
					+id+","+batteryCapacity+","+ratedCycles+",'"+batteryType+"','"+batteryCompany+"');";
				stmt.executeUpdate(sql);
				con.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return "New BMS has been set up!";
		}

	}
}

 