package com.cricshot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cricshot.entities.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {

}
