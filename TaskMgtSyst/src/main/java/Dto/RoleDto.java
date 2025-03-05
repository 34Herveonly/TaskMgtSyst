package Dto;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
@NotNull
public class RoleDto {
    private int roleId;
    private String roleName;
}
