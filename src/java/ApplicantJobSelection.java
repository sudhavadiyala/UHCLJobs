/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.*;
import java.util.ArrayList;
import javax.faces.bean.SessionScoped;


/**
 *
 * @author Avinash
 */
@ManagedBean
@SessionScoped
public class ApplicantJobSelection implements Serializable{

    /**
     * Creates a new instance of ApplicantJobSelection
     */
    
    public ArrayList<JobsPosted> jobs=new ArrayList<JobsPosted>();

    public ArrayList<JobsPosted> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<JobsPosted> jobs) {
        this.jobs = jobs;
    }
    
    public void ApplicantJobSelect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
            final String DATABASE_URL="jdbc:mysql://mis-sql.uhcl.edu/vadiyalas9388";
            Connection connection=null;
            Statement statement=null;
            ResultSet resultSet=null;
         
        try
        {
            connection=DriverManager.getConnection(DATABASE_URL,"vadiyalas9388","1378113");
            statement=connection.createStatement();
            jobs.clear();
            resultSet=statement.executeQuery("select * from jobsuhcl");
            while(resultSet.next())
            {
            JobsPosted j=new JobsPosted(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5));
            jobs.add(j);
            }
              
        }
          
        catch(Exception e)
        {
            e.printStackTrace();
            
          
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
            }
        }
             
       
        
          
    }
    
}
