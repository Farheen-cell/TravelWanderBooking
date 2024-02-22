package com.travelwanderbooking.repositories;

import com.travelwanderbooking.entites.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
}
