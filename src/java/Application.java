/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sudha
 */
public class Application 
{
    private String emailid;
    private String applicantid;
    private int jobid;
    private String status;
    private int id;
    private String message;

    /**
     * @return the applicantid
     */
    public String getApplicantid() {
        return applicantid;
    }

    /**
     * @return the emailid
     */
    public String getEmailid() {
        return emailid;
    }

    /**
     * @return the jobid
     */
    public int getJobid() {
        return jobid;
    }

    /**
     * @param applicantid the applicantid to set
     */
    public void setApplicantid(String applicantid) {
        this.applicantid = applicantid;
    }

    /**
     * @param emailid the emailid to set
     */
    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    /**
     * @param jobid the jobid to set
     */
    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
