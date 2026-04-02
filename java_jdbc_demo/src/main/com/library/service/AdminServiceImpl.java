package main.com.library.service;

import main.com.library.repository.AdminRepository;
import main.com.library.repository.AdminRepositoryImpl;
import main.com.library.dto.AdminDTO;

import java.util.List;

/***
 * This is service implementation class for admin
 */
public class AdminServiceImpl implements AdminService{

   private AdminRepository adminRepository;

   public AdminServiceImpl() {
       this.adminRepository = new AdminRepositoryImpl();
   }

    @Override
    public AdminDTO addAdmin(AdminDTO adminDTO) {
        return adminRepository.addAdmin(adminDTO);
    }

    @Override
    public AdminDTO updateAdmin(AdminDTO adminDTO) {
        return adminRepository.updateAdmin(adminDTO);
    }

    @Override
    public String deleteAdmin(int adminId) {
        return adminRepository.deleteAdmin(adminId);
    }

    @Override
    public List<AdminDTO> getAllAdmin() {
        return adminRepository.getAllAdmin();
    }

    @Override
    public AdminDTO getAdminById(int adminId) {
        return adminRepository.getAdminById(adminId);
    }

    @Override
    public AdminDTO getAdminByCode(String adminCode) {
        return adminRepository.getAdminByCode(adminCode);
    }

    @Override
    public List<AdminDTO> searchAdmin(String adminName, String adminCode, String adminAddress) {
        return adminRepository.searchAdmin(adminName,adminCode,adminAddress);
    }
}
