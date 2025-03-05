package Services;

import Dto.RoleDto;
import Entity.User;
import Entity.role;
import Repository.RolesRepository;
import Repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServices {

    @Autowired
    public RolesRepository rolesRepository;
    @Autowired
    public usersRepository userRepository;


    public role createRole(RoleDto roleDto) {

        role role = new role();
        role.setRolename(roleDto.getRoleName());

        return rolesRepository.save(role);
    }

    public List<role> getAllRoles() {
        return rolesRepository.findAll();
    }


    public role getRoleByName(String roleName){
        role role= rolesRepository.findByName(roleName)
                .orElseThrow(()-> new RuntimeException("role not found"));

        return rolesRepository.save(role);
    }

    public void assignRoles(long Userid,String roleName){

        User user=userRepository.findById(Userid)
                .orElseThrow(()->new RuntimeException("user at id:"+ Userid +" not found"));


        role role=rolesRepository.findByName(roleName)
                .orElseThrow(()->new RuntimeException("There is no role named "+roleName));

    }



}
