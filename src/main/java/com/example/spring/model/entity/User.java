package com.example.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int userId;

    @NotNull
    @Column(name = "full_name", nullable = false, length = 100)
    protected String fullName;

    @Column(length = 50, nullable = false)
    @NotNull
    protected String username;

    @JsonIgnore
    @Column(length = 250, nullable = false)
    @NotNull
    protected String password;

    @NotNull
    @Column(length = 10, nullable = false)
    protected String role;

    @NotNull
    @Column(nullable = false)
    protected boolean status;

    @OneToMany(mappedBy = "user")
    protected List<Task> taskList;
}
