package com.springboot.bookreview.services;

import com.springboot.bookreview.dto.roleDtos.RoleAddDto;
import com.springboot.bookreview.dto.roleDtos.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto getRoleById(Long id);
    List<RoleDto> getAllRoles();
    RoleDto addRole(RoleAddDto roleDto);
    void deleteRole(long roleId);

}
