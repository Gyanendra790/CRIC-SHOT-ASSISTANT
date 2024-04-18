package com.cricshot.payloads;

import com.cricshot.entities.Role;

public class RoleDto {

	private int id;
	private String name;
	public RoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static RoleDto fromRole(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        return roleDto;
    }

    public Role toRole() {
        Role role = new Role();
        role.setId(this.getId());
        role.setName(this.getName());
        return role;
    }
	
	
}
