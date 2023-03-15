package POJOS;

public class Logins {
    private String Applications;
    private String AppAccountName;
    private Boolean IsActive;
    private String JobTitle;
    private String Department;

    public Logins(
            String Applications,
            String AppAccountName,
            Boolean IsActive,
            String JobTitle,
            String Department
    ){
        this.Applications = Applications;
        this.AppAccountName = AppAccountName;
        this.IsActive = IsActive;
        this.JobTitle = JobTitle;
        this.Department = Department;
    }
    public String getApplications() {
        return Applications;
    }
    public String getAppAccountName() {
        return AppAccountName;
    }
    public Boolean getIsActive() {
        return IsActive;
    }
    public String getJobTitle() {
        return JobTitle;
    }
    public String getDepartment() {
        return Department;
    }
}
