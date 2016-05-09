/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.sql.*;

/**
 *
 * @author Avinash
 */
@ManagedBean
@SessionScoped
public class Register {

    /**
     * Creates a new instance of Register
     */
    String type;
    String loginID;
    String psw;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String LoginID) {
        this.loginID = LoginID;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
    
    public String Register()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;       
        try
        {
             final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/vadiyalas9388";
             connection = DriverManager.getConnection(DATABASE_URL, 
                    "vadiyalas9388", "1378113");   
            statement = connection.createStatement();
            
            resultSet=statement.executeQuery("Select * from account where id='"+loginID+"' ");
            
            if(resultSet.next())
            {
                 return("Login ID already Exists or Invalid");
            }
            else
            {
                int r=statement.executeUpdate("insert into account values('"+type+"','"+loginID+"','"+psw+"') ");
            }
            
            return "RegisterSuccess";      
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return("Internal Error.Please try again.");
        }
        finally
        {
            try{
            connection.close();
            statement.close();
            resultSet.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                
            }
        }
        
    }
           
}
