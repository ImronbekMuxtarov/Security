package com.example.securitychains.repository;

import com.example.securitychains.RoleName;
import com.example.securitychains.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, RoleName> {
}
