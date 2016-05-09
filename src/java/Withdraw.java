/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.SessionScoped;
import java.sql.*;
import javax.faces.context.FacesContext;

/**
 *
 * @author sudha
 */
@ManagedBean
@ViewScoped
public class Withdraw 
{
    public String Withdrwapplctn()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        String obj1 = context.getExternalContext().getRequestParameterMap().get("pa2");
        String obj2 = context.getExternalContext().getRequestParameterMap().get("pa1");
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();

        }
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/vadiyalas9388";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String st = "withdraw";
        try {
            connection = DriverManager.getConnection(DATABASE_URL,"vadiyalas9388","1378113");
            statement = connection.createStatement();
            int r = statement.executeUpdate("update application set status= '"+st+"'"+"where jobid='"+obj1+"'"+"and applicantid='"+obj2+"'");
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();     
        }
        finally{
            
            try{
                connection.close();
                statement.close();
                
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            
        }
        
        return "viewstatus";
    }
}
