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
 * @author Avinash
 */
@ManagedBean
@SessionScoped
public class ApplicationForm implements Serializable  {
    
 
  private static int applicationID;
  private String applicantID;
  private static int jobID;
  private String name;
  private int age;
  private static int jid;
  private String gender;
  private String mobileNum;
  private String emailID;
  private String education;
  private double workExp;
  private String status;
  private static String objectId;
  
  
  private ArrayList<Status> abcd = new ArrayList<Status>();
  public ArrayList<String> efgh = new ArrayList<String>();
  private ArrayList<Status> list = new ArrayList<Status>();
  public static ArrayList<ApplicationForm> applicants = new ArrayList<ApplicationForm>();
  
  
  public ApplicationForm()
  {     
  }

    public ApplicationForm(int jobId,String applicantId, String nm, int ag, String gen, String mNum, String emlD, String edu, int wExp) {
        
        this.applicantID = applicantId ;
        this.name = nm;
        this.age = ag;
        this.gender = gen;
        this.mobileNum = mNum;
        this.emailID = emlD;
        this.education = edu;
        this.workExp = wExp;
        
    }
    
    
    public String formjobid()
    {
      FacesContext context = FacesContext.getCurrentInstance();
      objectId = (context.getExternalContext()
                .getRequestParameterMap().get("jobID"));
      
      return ("ApplyJob");
      
    }
    
    
    
    public void applicationView(String id)
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
        ResultSet resultSet1 = null;
        FacesContext context = FacesContext.getCurrentInstance();
        String appliID = (context.getExternalContext()
                .getRequestParameterMap().get("login"));
        appliID = id;
        
        abcd.clear();
        list.clear();
        try {
            connection = DriverManager.getConnection(DATABASE_URL,"vadiyalas9388","1378113");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select jobid,status from application where applicantid='" + appliID  + "'");
            while(resultSet.next())
            {
               Status s = new Status();
               
               s.setJobID(resultSet.getInt(1));
               s.setStatus(resultSet.getString(2));
               s.getJobID();
               s.getStatus();   
               abcd.add(s);
            }
            
            statement1 = connection.createStatement();
            
            for(int i=0;i<abcd.size();i++)
            {
               resultSet1 = statement.executeQuery("select jobtitle from jobsuhcl where jobid='" + abcd.get(i).getJobID()  + "'");
               if(resultSet1.next())
               {
                   efgh.add(resultSet1.getString(1));      
               }      
            }
            for(int i=0;i<abcd.size();i++)
            {
               Status q = new Status();
               q.setJobID(abcd.get(i).getJobID());
               q.setStatus(abcd.get(i).getStatus());
               q.setJobtitle(efgh.get(i));
               list.add(q);
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

    
  public String ApplicationSubmit( String a) 
    {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();

        }
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/vadiyalas9388";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet1 = null;
        Statement statement1 = null;
        ResultSet resultSet = null;
        FacesContext context = FacesContext.getCurrentInstance();
        String appliID = (context.getExternalContext()
                .getRequestParameterMap().get("appid"));
        String out = "";
        

        try {
            connection = DriverManager.getConnection(DATABASE_URL,"vadiyalas9388","1378113");
            statement = connection.createStatement();
            statement1 = connection.createStatement();
            resultSet1 = statement.executeQuery("select * from application where applicantid='" + a  + "'" + "and jobid='" + objectId + "'");
            if (!resultSet1.next()) 
            {
            resultSet = statement1.executeQuery("select max(applicationID) from application");

            if (resultSet.next()) {
                applicationID =(resultSet.getInt(1) + 1);

            } else {
                applicationID = 10001;

            }
             String status = "Under Review";
             String message = "";
             
              int r = statement.executeUpdate("insert into application values"
                        + "('" + applicationID + "' , '" + a + "' , '"+ objectId +"' , '"+ name + "' , '" + age + "' , '" + gender + "' , '" + mobileNum + "' , '" + emailID + "' , '" + education + "' , '" + workExp + "' , '" + status + "' , '" +message + "')");
                out = "ApplyJobOk";
                
            } else {
                out = "ApplyJobNotOk";
            }
            
            applicationID = 0;
            applicantID = "";
            name = "";
            age = 0;
            gender = "";
            emailID = "";
            mobileNum = "";
            education = "";
            workExp = 0;     

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                connection.close();
                statement.close();
                resultSet1.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

      return out;
    }
  
  public void Withdrwapplctn()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();

        }
        FacesContext context = FacesContext.getCurrentInstance();
        String obj1 = context.getExternalContext().getRequestParameterMap().get("job_pr");
        String obj2 = context.getExternalContext().getRequestParameterMap().get("id_par");
        
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
                resultSet.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            
        }
        
       
    }
  
  public String viewapplicants(String jobid)
  {
      
      String s="selected";
      String w="Under Review";
     
      try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();

        }
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/vadiyalas9388";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        applicants.clear();
        try {
            connection = DriverManager.getConnection(DATABASE_URL,"vadiyalas9388","1378113");
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from application where jobid='"+jobid+"'");
            while(resultSet.next())
            {
                
               ApplicationForm a = new ApplicationForm();
               a.applicantID = resultSet.getString(2);
               a.gender = resultSet.getString(6);
               a.mobileNum = resultSet.getString(7);
               a.emailID = resultSet.getString(8);
               a.education = resultSet.getString(9);
               a.workExp = resultSet.getDouble(10);
               a.setJid(resultSet.getInt(3));
               applicants.add(a);
                
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
      return "ViewApplicants";
  }
  
  public String select()
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
        Statement statement2 = null;
        ResultSet resultSet = null;
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;
        String out="SelectedSuccess";
        FacesContext context = FacesContext.getCurrentInstance();
        String jobid = context.getExternalContext().getRequestParameterMap().get("i");
        String uid = context.getExternalContext().getRequestParameterMap().get("i1");
        try {
            connection = DriverManager.getConnection(DATABASE_URL,"vadiyalas9388","1378113");
            statement = connection.createStatement();
            statement1 = connection.createStatement();
            statement2 = connection.createStatement();
            resultSet = statement.executeQuery("select numofpos from jobsuhcl where jobid='"+jobid+"'");
            if(resultSet.next())
            {
                int positions = resultSet.getInt("numofpos");
                if(positions==0)
                {
                    out = "ErrorSelection";
                    String sta = "rejected";
                   // int r1 = statement2.executeUpdate("update application set status ='"+sta+"'"+"where jobid='"+jobid+"'"+"and applicantid='"+uid+"'");
                }
                if(positions==1)
                {
                    int pos = 0;
                    String sta = "selected";
                    int r = statement1.executeUpdate("update jobsuhcl set numofpos='"+pos+"'"+"where jobid='"+jobid+"'");
                    int r1 = statement2.executeUpdate("update application set status ='"+sta+"'"+"where jobid='"+jobid+"'"+"and applicantid='"+uid+"'");
                }
                if(positions==2)
                {
                   int pos =1;
                   String sta = "selected";
                   int r = statement1.executeUpdate("update jobsuhcl set numofpos='"+pos+"'"+"where jobid='"+jobid+"'");
                   int r1 = statement2.executeUpdate("update application set status ='"+sta+"'"+"where jobid='"+jobid+"'"+"and applicantid='"+uid+"'");
                }
                if(positions==3)
                {
                   int pos =2;
                   String sta = "selected";
                   int r = statement1.executeUpdate("update jobsuhcl set numofpos='"+pos+"'"+"where jobid='"+jobid+"'");
                   int r1 = statement2.executeUpdate("update application set status ='"+sta+"'"+"where jobid='"+jobid+"'"+"and applicantid='"+uid+"'");
                }
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
      return out;
  }
  
  public String reject()
  {
      try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            e.printStackTrace();

        }
        final String DATABASE_URL = "jdbc:mysql://mis-sql.uhcl.edu/vadiyalas9388";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        FacesContext context = FacesContext.getCurrentInstance();
        String jobid = context.getExternalContext().getRequestParameterMap().get("i2");
        String uid = context.getExternalContext().getRequestParameterMap().get("i3");
        try {
            connection = DriverManager.getConnection(DATABASE_URL,"vadiyalas9388","1378113");
            statement = connection.createStatement();
            String sta = "rejected";
            int r1 = statement.executeUpdate("update application set status ='"+sta+"'"+"where jobid='"+jobid+"'"+"and applicantid='"+uid+"'");     
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
      return "RejectedSuccess";
  }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @return the applicantID
     */
    public String getApplicantID() {
        return applicantID;
    }

    /**
     * @return the applicationID
     */
    public int getApplicationID() {
        return applicationID;
    }

    /**
     * @return the education
     */
    public String getEducation() {
        return education;
    }

    /**
     * @return the emailID
     */
    public String getEmailID() {
        return emailID;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return the jobID
     */
    public int getJobID() {
        return jobID;
    }

    /**
     * @return the mobileNum
     */
    public String getMobileNum() {
        return mobileNum;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the objectId
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @return the workExp
     */
    public double getWorkExp() {
        return workExp;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @param applicantID the applicantID to set
     */
    public void setApplicantID(String applicantID) {
        this.applicantID = applicantID;
    }

    /**
     * @param applicationID the applicationID to set
     */
    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    /**
     * @param education the education to set
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * @param emailID the emailID to set
     */
    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @param jobID the jobID to set
     */
    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    /**
     * @param mobileNum the mobileNum to set
     */
    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param objectId the objectId to set
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @param workExp the workExp to set
     */
    public void setWorkExp(double workExp) {
        this.workExp = workExp;
    }

    /**
     * @return the abcd
     */
    public ArrayList<Status> getAbcd() {
        return abcd;
    }

    /**
     * @param abcd the abcd to set
     */
    public void setAbcd(ArrayList<Status> abcd) {
        this.abcd = abcd;
    }

    /**
     * @return the efgh
     */
    public ArrayList<String> getEfgh() {
        return efgh;
    }

    /**
     * @param efgh the efgh to set
     */
    public void setEfgh(ArrayList<String> efgh) {
        this.efgh = efgh;
    }

    /**
     * @return the list
     */
    public ArrayList<Status> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(ArrayList<Status> list) {
        this.list = list;
    }

    /**
     * @return the applicants
     */
    public ArrayList<ApplicationForm> getApplicants() {
        return applicants;
    }

    /**
     * @param applicants the applicants to set
     */
    public void setApplicants(ArrayList<ApplicationForm> applicants) {
        this.applicants = applicants;
    }

    /**
     * @return the jid
     */
    public int getJid() {
        return jid;
    }

    /**
     * @param jid the jid to set
     */
    public void setJid(int jid) {
        this.jid = jid;
    }
  
  
}
