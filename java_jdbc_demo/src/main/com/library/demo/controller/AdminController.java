package main.com.library.demo.controller;

import main.com.library.dto.AdminDTO;
import main.com.library.service.AdminService;
import main.com.library.service.AdminServiceImpl;

import java.util.List;

/**
 * This is request handler class for admin
 */
public class AdminController {

    public static void main(String[] args) {
        System.out.println("Admin Controller");
        AdminController adminController = new AdminController();
        //getBy adminID
        AdminDTO adminDTO = adminController.getAdminById(4000);
        System.out.println("Get By ID");
        System.out.println(adminDTO);
        //getBy All admin list
        List<AdminDTO> adminList = adminController.getAllAdmin();
        System.out.println("Get By All");
        System.out.println(adminList);
        // getBy adminCode
        AdminDTO adminDTO1 = adminController.getAdminByCode("ADM009");
        System.out.println("Get By Code");
        System.out.println(adminDTO1);
        // search
        List<AdminDTO> adminList1 = adminController.searchAdmin("", "ADM005", "");
        System.out.println("Search Admin");
        System.out.println(adminList1);

       // add new admin
        AdminDTO newAdmin=new AdminDTO();
        newAdmin.setAdminName("DEVENDRA SINGRAUL");
        newAdmin.setAdminCode("ADM011");
        newAdmin.setAdminAddress("Satna");
        newAdmin.setAdminEmail("singrauld@yahoo.com");
        newAdmin.setAdminPhone("7509032801");
        newAdmin.setAdminSalary(20000);

        //add admin
        AdminDTO adminDTO2=adminController.addAdmin(newAdmin);
        System.out.println("Add New Admin");
        System.out.println(adminDTO2);

        // UPDATE existing admin
        AdminDTO oldAdmin=new AdminDTO();
        oldAdmin.setAdminId(4004);
        oldAdmin.setAdminName("Rivank SINGRAUL");
        oldAdmin.setAdminCode("ADM013");
        oldAdmin.setAdminAddress("Satna");
        oldAdmin.setAdminEmail("rivank1234@yahoo.com");
        oldAdmin.setAdminPhone("7509032801");
        oldAdmin.setAdminSalary(40000);

        AdminDTO adminDTO3=adminController.updateAdmin(oldAdmin);
        System.out.println("Update Admin");
        System.out.println(adminDTO3);

        String message=adminController.deleteAdmin(4000);
        System.out.println("Delete Admin");
        System.out.println(message);
    }
    // tightly coupled dependency injection
    private AdminService adminService;

    public AdminController() {
        this.adminService = new AdminServiceImpl();
    }

    public AdminDTO addAdmin(AdminDTO adminDTO) {
        return adminService.addAdmin(adminDTO);
    }


    public AdminDTO updateAdmin(AdminDTO adminDTO) {
        return adminService.updateAdmin(adminDTO);
    }


    public String deleteAdmin(int adminId) {
        return adminService.deleteAdmin(adminId);
    }


    public List<AdminDTO> getAllAdmin() {
        return adminService.getAllAdmin();
    }


    public AdminDTO getAdminById(int adminId) {
        return adminService.getAdminById(adminId);
    }


    public AdminDTO getAdminByCode(String adminCode) {
        return adminService.getAdminByCode(adminCode);
    }

    public List<AdminDTO> searchAdmin(String adminName, String adminCode, String adminAddress) {
        return adminService.searchAdmin(adminName,adminCode,adminAddress);
    }
}
