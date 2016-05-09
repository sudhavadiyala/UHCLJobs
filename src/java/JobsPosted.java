/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Avinash
 */
public class JobsPosted {
    
    int jobID;
    String jobtitle;
    String jobDesc;
    String skills;
    int positions;
    

    public JobsPosted(int jobID, String jobtitle, String jobDesc, String skills, int positions)
    {
        this.jobID = jobID;
        this.jobtitle = jobtitle;
        this.jobDesc = jobDesc;
        this.skills = skills;
        this.positions = positions;
    }

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

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getPositions() {
        return positions;
    }

    public void setPositions(int positions) {
        this.positions = positions;
    }
    
    
    
}
