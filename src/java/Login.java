/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.*;

/**
 *
 * @author Avinash
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable {

    /**
     * Creates a new instance of Login
     */
   String loginID;
   String psw;
 
   

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }
   
    public String Login()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            return("Internal Error");
        }
        
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/vadiyalas9388";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try
        {
            connection = DriverManager.getConnection(DATABASE_URL,"vadiyalas9388", "1378113");   
            statement = connection.createStatement();  
            resultSet=statement.executeQuery("select * from account where id='"+loginID+"'");
            
            if(resultSet.next())
            {
                if(psw.equals(resultSet.getString(3)))
                {
                    if(resultSet.getString(1).equalsIgnoreCase("Applicant"))
                    {
                    return "ApplicantView"; 
                    }
                    else
                    {
                        return "HRView";
                    }
                }
                else
                {
                   loginID="";
                    psw="";
                    return("Internal Error");
                }
            }
            else
            {
                loginID="";
                psw="";
                return"Please enter correct login details";
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return("Internal Error..Please try again");
        }
        finally
        {
            try
            {
                connection.close();
                statement.close();
                resultSet.close();
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return("Internal Error");
            }
            
        }
           
         
        
    }
   
    
}
