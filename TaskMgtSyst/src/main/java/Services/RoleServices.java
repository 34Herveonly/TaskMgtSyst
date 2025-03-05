package Services;

import Dto.RoleDto;
import Entity.roles;
import Repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServices {

    @Autowired
    public RolesRepository rolesRepository;


    public roles createRole(RoleDto roleDto) {

        roles role = new roles();
        role.setRolename(roleDto.getRoleName());

        return rolesRepository.save(role);
    }

    public List<roles> getAllRoles() {
        return rolesRepository.findAll();
    }


    public roles getRoleByName(String roleName){
        roles role= rolesRepository.findByName(roleName)
                .orElseThrow(()-> new RuntimeException("role not found"));

        return rolesRepository.save(role);
    }

    public void assignRoles(long userId,String roleName){

    }


}
