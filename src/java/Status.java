/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sudha
 */
public class Status 
{
  private int JobID;
  private  String status;
  private  String jobtitle;

    /**
     * @return the JobID
     */
//  public Status(int Id,String a , String b)
//  {
//      this.JobID = Id;
//      this.status = a;
//      this.jobtitle = b;
//  }
  
    public int getJobID() {
        return JobID;
    }

    /**
     * @return the jobtitle
     */
    public String getJobtitle() {
        return jobtitle;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param JobID the JobID to set
     */
    public void setJobID(int JobID) {
        this.JobID = JobID;
    }

    /**
     * @param jobtitle the jobtitle to set
     */
    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
   
   
   
}
