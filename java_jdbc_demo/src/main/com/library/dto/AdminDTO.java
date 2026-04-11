package main.com.library.dto;

public class AdminDTO {
    private int adminId;
    private String adminName;
    private String adminCode;
    private String adminAddress;
    private String adminEmail;
    private String adminPhone;
    private int adminSalary;
    private String isDeleted;
    
    public AdminDTO() {}
    
    public AdminDTO(int adminId, String adminName, String adminCode, String adminAddress, 
                   String adminEmail, String adminPhone, int adminSalary, String isDeleted) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminCode = adminCode;
        this.adminAddress = adminAddress;
        this.adminEmail = adminEmail;
        this.adminPhone = adminPhone;
        this.adminSalary = adminSalary;
        this.isDeleted = isDeleted;
    }
    
    public int getAdminId() {
        return adminId;
    }
    
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    
    public String getAdminName() {
        return adminName;
    }
    
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    
    public String getAdminCode() {
        return adminCode;
    }
    
    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }
    
    public String getAdminAddress() {
        return adminAddress;
    }
    
    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }
    
    public String getAdminEmail() {
        return adminEmail;
    }
    
    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
    
    public String getAdminPhone() {
        return adminPhone;
    }
    
    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }
    
    public int getAdminSalary() {
        return adminSalary;
    }
    
    public void setAdminSalary(int adminSalary) {
        this.adminSalary = adminSalary;
    }
    
    public String getIsDeleted() {
        return isDeleted;
    }
    
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    @Override
    public String toString() {
        return "AdminDTO{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", adminCode='" + adminCode + '\'' +
                ", adminAddress='" + adminAddress + '\'' +
                ", adminEmail='" + adminEmail + '\'' +
                ", adminPhone='" + adminPhone + '\'' +
                ", adminSalary=" + adminSalary +
                ", isDeleted='" + isDeleted + '\'' +
                '}';
    }
}
