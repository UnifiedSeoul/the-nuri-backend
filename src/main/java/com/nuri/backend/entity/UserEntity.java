package com.nuri.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user_info")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;
    private String password;
    private String role;

    @OneToMany(mappedBy = "user")
    private List<UserJobInfo> userJobInfoList = new ArrayList<>();

}
