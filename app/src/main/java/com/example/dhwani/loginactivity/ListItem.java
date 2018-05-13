package com.example.dhwani.loginactivity;

/**
 * Created by Dhwani on 4/29/2018.
 */

public class ListItem {

    private String job_Name;
    private String company_Name;

    public ListItem(String job_name, String company_name) {
       this.job_Name = job_name;
       this.company_Name = company_name;
    }

    public String getJobName()
    {
        return job_Name;
    }

    public String getCompanyName() {
        return company_Name;
    }
}
