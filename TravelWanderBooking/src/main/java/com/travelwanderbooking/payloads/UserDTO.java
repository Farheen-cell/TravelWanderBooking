package com.travelwanderbooking.payloads;

import com.travelwanderbooking.entites.Role;
import com.travelwanderbooking.enums.PropertyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long userId;
    private String name;
    private String lastName;
    private boolean hostStatus;
    private String Location;
    private PropertyType propertyType;
    private String about;
    private LocalDate date;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();
}
