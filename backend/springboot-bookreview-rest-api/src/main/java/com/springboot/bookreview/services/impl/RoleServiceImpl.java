package com.springboot.bookreview.services.impl;

import com.springboot.bookreview.dto.roleDtos.RoleAddDto;
import com.springboot.bookreview.dto.roleDtos.RoleDto;
import com.springboot.bookreview.entities.Role;
import com.springboot.bookreview.exceptions.ResourceNotFoundException;
import com.springboot.bookreview.repositories.RoleRepository;
import com.springboot.bookreview.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDto> getAllRoles() {

        List<RoleDto> roleDtoList = new ArrayList<>();
        List<Role> roleList = roleRepository.findAll();

        for(Role role : roleList) {
            roleDtoList.add(mapEntityToDto(role));
        }

        return roleDtoList;

    }

    public RoleDto getRoleById(Long id) {

        Role roleFromDb = roleRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Role", "id", id));

        return  mapEntityToDto(roleFromDb);

    }

    @Override
    public RoleDto addRole(RoleAddDto roleDto) {

        //map dto to entity
        Role roleToAdd = mapAddDtoToEntity(roleDto);

        Role roleAdded = roleRepository.save(roleToAdd);

        //map entity to dto
        return mapEntityToDto(roleAdded);
    }

    @Override
    public void deleteRole(long roleId) {

        Role roleFromDb = roleRepository.findById(roleId).orElseThrow(()
                -> new ResourceNotFoundException("Role", "id", roleId));

        roleRepository.delete(roleFromDb);
    }

    private Role mapDtoToEntity(RoleDto roleDto) {

        return new Role(
                roleDto.getId(),
                roleDto.getName()
        );

    }

    private Role mapAddDtoToEntity(RoleAddDto roleAddDto) {

        return new Role(roleAddDto.getName());
    }

    private RoleDto mapEntityToDto(Role role) {

        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());

        return roleDto;

    }
}
