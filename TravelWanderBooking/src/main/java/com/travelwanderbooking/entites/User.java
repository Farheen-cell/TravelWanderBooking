package com.travelwanderbooking.entites;

import com.travelwanderbooking.enums.PropertyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Size(min = 5, max = 20, message = "First Name must be between 5 and 30 characters long")
    @Pattern(regexp = "[a-zA-z]*$",message = "First Name must not contain numbers or spacific character")
    private String name;

    @Size(min = 5, max = 20, message = "First Name must be between 5 and 30 characters long")
    @Pattern(regexp = "[a-zA-z]*$",message = "First Name must not contain numbers or spacific character")
    private String lastName;

    private boolean hostStatus;

    private String Location;

    private PropertyType propertyType;

    @NotBlank(message = "About cannot be blank")
    private String about;

    private LocalDate date;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "password cannot be blank")
    private String password;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
}
