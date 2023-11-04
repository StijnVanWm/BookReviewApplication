package com.springboot.bookreview.controllers;

import com.springboot.bookreview.dto.roleDtos.RoleAddDto;
import com.springboot.bookreview.dto.roleDtos.RoleDto;
import com.springboot.bookreview.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    //GET: api/roles
    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    //GET: api/roles/1
    @GetMapping("{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable(name = "id") Long roleId) {

        return new ResponseEntity<>(roleService.getRoleById(roleId), HttpStatus.OK);

    }

    //POST: api/roles
    @PostMapping
    public ResponseEntity<RoleDto> addRole(@RequestBody RoleAddDto roleAddDto) {

        RoleDto roleAdded = roleService.addRole(roleAddDto);

        if(roleAdded == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(roleAdded, HttpStatus.CREATED);

    }

    //DELETE: api/roles/5
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePost(@PathVariable(name = "id") Long roleId) {

        roleService.deleteRole(roleId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
