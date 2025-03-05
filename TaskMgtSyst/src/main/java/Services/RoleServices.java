package Services;

import Dto.RoleDto;
import Entity.roles;
import Repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
public class RoleServices {
    @Autowired
    public RolesRepository rolesRepository;

    public roles createRole(RoleDto roleDto) {

        roles role = new roles();
        role.setRolename(roleDto.getRoleName());

        return rolesRepository.save(role);
    }
}
