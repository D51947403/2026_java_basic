package main.com.library.repository;

import main.com.library.dto.AdminDTO;

import java.util.List;

public interface AdminRepository {
    public AdminDTO addAdmin(AdminDTO adminDTO);
    public AdminDTO updateAdmin(AdminDTO adminDTO);
    public String deleteAdmin(int adminId);
    public List<AdminDTO> getAllAdmin();
    public AdminDTO getAdminById(int adminId);
    public AdminDTO getAdminByCode(String adminCode);
    public List<AdminDTO> searchAdmin(String adminName,String adminCode,String adminAddress);

}
