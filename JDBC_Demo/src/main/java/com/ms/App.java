package com.ms;
import java.sql.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final String url="jdbc:mysql://localhost:3306/sarvesh";
	private static final String username="root";
	private static final String password="Sa@7505286528";
	
	
    public static void main( String[] args ){
    	try {
    		
        	Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e) {
        	//e.printStackTrace();
        	System.out.println(e.getMessage());
        }
    	// using Statements{
    	  //getData();
    	  //addData();
    	  //updateData();
    	  //DeleteData(); 
    	//}
    	
    	
    	// Using Prepared Statement
    	//PreparedInsert();
    	//PreparedFetchBy();
    	//preparedUpdate();
    	//preparedDelete();
    	
    	// using batch processing 
    	addBatchdata();
    	
    }
        
    public static void getData() { 	
        try {
        	Connection con=DriverManager.getConnection(url,username,password);
        	
        	Statement statement =con.createStatement();
        	
        	
        	String query="select * from Person";
        	
        	ResultSet rs=statement.executeQuery(query);
        	
        	while(rs.next()) {
        		int Id=rs.getInt("personId");
        		String FirstName=rs.getString("first_name");
        		String LastName=rs.getString("last_name");
        		int Age=rs.getInt("Age");
        		
        		System.out.println("ID: "+Id);
        		
        		System.out.println("NAME: " +FirstName+" "+LastName);
        		System.out.println("AGE: "+Age);
        				
        	}
        } catch(SQLException e){
        	System.out.println(e.getMessage());
        
        }
        	
    }
    public static void addData() {
    	try {
        	Connection con=DriverManager.getConnection(url,username,password);
        	
        	Statement statement =con.createStatement();
        	
        	
        	String query=String.format("INSERT INTO Person(first_name ,last_name ,Age) VALUES('%s','%s',%d)","Kamalesh","Maurya",20);
        	
        	int affectedRow=statement.executeUpdate(query);
        	if(affectedRow>0) {
        		System.out.println("Data inserted successfully!");
        	}else {
        		System.out.println("Data not inserted!!");
        	}
        } catch(SQLException e){
        	System.out.println(e.getMessage());
        
        }
    	
    }
    public static void updateData() {
    	try {
        	Connection con=DriverManager.getConnection(url,username,password);
        	
        	Statement statement =con.createStatement();
        	
        	
        	String query=String.format("UPDATE PERSON SET first_name='%s' WHERE PersonId=%d","Mani",4);
        	
        	int affectedRow=statement.executeUpdate(query);
        	if(affectedRow>0) {
        		System.out.println("Data updated successfully!");
        	}else {
        		System.out.println("Data not updated!!");
        	}
        } catch(SQLException e){
        	System.out.println(e.getMessage());
        
        }
    	
    }
    public static void DeleteData() {
    	try {
        	Connection con=DriverManager.getConnection(url,username,password);
        	
        	Statement statement =con.createStatement();
        	
        	
        	String query="Delete from Person where PersonId=4";
        	
        	int affectedRow=statement.executeUpdate(query);
        	if(affectedRow>0) {
        		System.out.println("Deleted successfully!");
        	}else {
        		System.out.println("Delete Failed!!");
        	}
        } catch(SQLException e){
        	System.out.println(e.getMessage());
        
        }
    	
    }
    public static void PreparedInsert() {
    	try {
        	Connection con=DriverManager.getConnection(url,username,password);
            
        	String query="INSERT INTO PERSON (first_name,last_name,Age) VALUES(?,?,?)";
        	// in prepared statement we dont do hardcode like statement 
        	
        	PreparedStatement preparedStatement=con.prepareStatement(query);
        	preparedStatement.setString(1, "Mukesh");
        	preparedStatement.setString(2, "Vish");
        	preparedStatement.setInt(3, 27);
        	
        	
        	int affectedRow=preparedStatement.executeUpdate();
        	if(affectedRow>0) {
        		System.out.println("Data inserted successfully!");
        	}else {
        		System.out.println("data insertion Failed!!");
        	}
        } catch(SQLException e){
        	System.out.println(e.getMessage());
        
        }
    }
    
    public static void PreparedFetchBy() {
    	try {
        	Connection con=DriverManager.getConnection(url,username,password);
            
        	String query="select first_name , last_name from Person where PersonId=?";
        	// in prepared statement we dont do hardcode like statement 
        	
        	PreparedStatement preparedStatement=con.prepareStatement(query);
        	preparedStatement.setInt(1, 2);
        	
        	ResultSet resultSet=preparedStatement.executeQuery();
        	if(resultSet.next()) {
        		String name=resultSet.getString("first_name") +" "+resultSet.getString("last_name");
        		System.out.println("NAME= "+name);
        	} else {
        		System.out.println("Name not found !!");
        	}
        } catch(SQLException e){
        	System.out.println(e.getMessage());
        
        }
    }
    public static void preparedUpdate() {
    	try {
        	Connection con=DriverManager.getConnection(url,username,password);
            
        	String query="update person set last_name=? where personId=?";
        	// in prepared statement we dont do hardcode like statement 
        	
        	PreparedStatement preparedStatement=con.prepareStatement(query);
        	preparedStatement.setString(1, "Bhagat");
        	preparedStatement.setInt(2, 5);
        	
        	int  rowaffected=preparedStatement.executeUpdate();
        	if(rowaffected>0) {
        		
        		System.out.println("Update successful !!");
        	} else {
        		System.out.println("Update failed !!");
        	}
        } catch(SQLException e){
        	System.out.println(e.getMessage());
        
        }
    	
    }
    public static void preparedDelete() {
    	try {
        	Connection con=DriverManager.getConnection(url,username,password);
            
        	String query="delete from Person where personId=?";
        	// in prepared statement we dont do hardcode like statement 
        	
        	PreparedStatement preparedStatement=con.prepareStatement(query);
        	preparedStatement.setInt(1, 5);
        	
        	int  rowaffected=preparedStatement.executeUpdate();
        	if(rowaffected>0) {
        		
        		System.out.println("Delete successful !!");
        	} else {
        		System.out.println("Delete failed !!");
        	}
        } catch(SQLException e){
        	System.out.println(e.getMessage());
        
        }
    }
    //------------------using batch processing and Prepared statements ----------------
    public static void addBatchdata() {
    	try {
    		Connection connection = DriverManager.getConnection(url,username,password);
    		String query="insert into person(first_name,last_name,age) values(?,?,?)";
    		PreparedStatement preparedStatement =connection.prepareStatement(query);
    		
    		Scanner sc= new Scanner(System.in);
    		
    		while(true) {
    			System.out.print("Enter First_name : ");
    			String fname=sc.next();
    			System.out.print("Enter last_name : ");
    			String lname=sc.next();
    			System.out.print("Enter Age : ");
    			int age=sc.nextInt();
    			System.out.println("Enter more data Y/N ");
    			String choice=sc.next();
    			
    			preparedStatement.setString(1, fname);
    			preparedStatement.setString(2, lname);
    			preparedStatement.setInt(3, age);
    			
    			preparedStatement.addBatch();
    			
    			if(choice.toUpperCase().equals("N")) {
    				break;
    			}
    		
    	} 
    		int [] arr=preparedStatement.executeBatch();
    		for(int i=0;i<arr.length;i++) {
    			if(arr[i]==0) {
    				System.out.println("Query "+i+"Not executed Successfully !!");
    			}
    		}
    	}
    		catch(SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
}
