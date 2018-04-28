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
    private String emailId;
    private String applicantId;
    private int jobId;
    private String status;
    private int id;
    private String message;

    /**
     * @return the applicantid
     */
    public String getApplicantId() {
        return applicantId;
    }

    /**
     * @return the emailid
     */
    public String getEmailid() {
        return emailId;
    }

    /**
     * @return the jobid
     */
    public int getJobId() {
        return jobId;
    }

    /**
     * @param applicantid the applicantid to set
     */
    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    /**
     * @param emailid the emailid to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @param jobid the jobid to set
     */
    public void setJobId(int jobId) {
        this.jobId = jobId;
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
