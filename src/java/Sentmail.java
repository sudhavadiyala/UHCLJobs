/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.SessionScoped;
import java.sql.*;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import javax.faces.context.ExternalContext;
/**
 *
 * @author sudha
 */
@ManagedBean
@SessionScoped
public class Sentmail 
{
    private ArrayList<Application> inbox = new ArrayList<Application>();
    public String notification(String j)
    {
       ArrayList<Application> emailids = new ArrayList<Application>();
       FacesContext context = FacesContext.getCurrentInstance();
       String jId = (context.getExternalContext()
                .getRequestParameterMap().get("jid")); 
       ApplicationForm b = new ApplicationForm();
       int jid  = b.getJid();
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();

        }
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/vadiyalas9388";
        Connection connection = null;
        Statement statement = null;
        Statement statement1 = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        String jobtitle="";
        int numoopos=-1;
        String ret="";
        
        try {
            connection = DriverManager.getConnection(DATABASE_URL,"vadiyalas9388","1378113");
            statement = connection.createStatement();
            statement1 = connection.createStatement();
            resultSet1 = statement.executeQuery("select jobtitle,numofpos from jobsuhcl where jobid='"+jid+"'");
            if(resultSet1.next())
            {
               jobtitle =  resultSet1.getString(1);
               numoopos =  resultSet1.getInt(2);
            }
            resultSet = statement.executeQuery("select emailid,status,applicantid from application where jobid='" + jid + "'");
            
            while(resultSet.next())
            {
              Application a = new Application();
              a.setEmailid(resultSet.getString(1));
              a.setStatus(resultSet.getString(2));
              a.setApplicantid(resultSet.getString(3));
              a.setJobid(jid);
              emailids.add(a);
            }
            
            
           
            if(numoopos==0)
            {
            for(int i=0;i<emailids.size();i++)
            {
                int k =0;
                String status = emailids.get(i).getStatus();
                if(status.equals("selected"))
                {
                    k++;
                }
                String appid = emailids.get(i).getApplicantid();
                String emailid = emailids.get(i).getEmailid();
                
                String message = "From noreply@uhcl.edu :" +"Your Application for "+jobtitle+" with job id"+jid+"  has been "+emailids.get(i).getStatus();
                int r = statement1.executeUpdate("insert into mails values"
                        + "('"+ appid +"' , '"+ emailid +"' , '"+ jid +"' , '"+ message +"')");
                ret = "Email";
            }
            }
            else
            {
                ret = "NoEmail";
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            
        }
        finally{
            
            try{
                connection.close();
                statement.close();
                statement1.close();
                resultSet.close();
                resultSet1.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            
        }
        
        return ret;
    }
    
    public void viewmessages(String id)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();

        }
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/vadiyalas9388";
        Connection connection = null;
        Statement statement = null;
        Statement statement1 = null;
        ResultSet resultSet = null;
        int i=0;
        try {
            connection = DriverManager.getConnection(DATABASE_URL,"vadiyalas9388","1378113");
            statement = connection.createStatement();
            statement1 = connection.createStatement();
            resultSet = statement.executeQuery("select message from mails where applicantid='" + id + "'");
            inbox.clear();
            while(resultSet.next())
            {
              Application a = new Application();
              a.setMessage(resultSet.getString(1));
              a.setId(i++);
              inbox.add(a);
            }
            
            
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            
        }
        finally{
            
            try{
                connection.close();
                statement.close();
                resultSet.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            
        }
    }

    /**
     * @return the inbox
     */
    public ArrayList<Application> getInbox() {
        return inbox;
    }

    /**
     * @param inbox the inbox to set
     */
    public void setInbox(ArrayList<Application> inbox) {
        this.inbox = inbox;
    }
}
