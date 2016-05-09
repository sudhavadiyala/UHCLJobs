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
public class JobPosting implements Serializable{

    /**
     * Creates a new instance of JobPosting
     */
    int jobID;
    String jobtitle;
    String jobdesc;
    String skills;
    String positions;

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }
    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getJobdesc() {
        return jobdesc;
    }

    public void setJobdesc(String jobdesc) {
        this.jobdesc = jobdesc;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }
    
    public String JobPost()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "Internal Error";
        }
        
        final String DATABASE_URL="jdbc:mysql://mis-sql.uhcl.edu/vadiyalas9388";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try
        {
            connection= DriverManager.getConnection(DATABASE_URL,"vadiyalas9388","1378113");
            statement=connection.createStatement();
            resultSet=statement.executeQuery("select max(jobid) from jobsuhcl");
            if(resultSet.next())
            {
                jobID=resultSet.getInt(1)+1;
            }
            else
            {
            jobID=100;
           int r=statement.executeUpdate("insert into jobsuhcl values"
                    + "('"+jobID+"','"+jobtitle+"','"+jobdesc+"','"+skills+"','"+positions+"')");
            
            }
           
            int s=statement.executeUpdate("insert into jobsuhcl values"
                    + "('"+jobID+"','"+jobtitle+"','"+jobdesc+"','"+skills+"','"+positions+"')");
           
          
            return "JobPostOk";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "Internal Problem";
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
                //return "Error..Please try again";
            }
        }
               
               
    }
    
}
