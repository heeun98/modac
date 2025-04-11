package com.project.modac.domain;


import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
@Builder
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username; //로그인시 사용자 id , 고유값

    @Column
    private String password; // 로그인시 사용자 password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String nickname;

    private String email;

    @Enumerated(EnumType.STRING)
    private HairType hairType;

    private String hairLossLevel;

    @Embedded
    private Address address;

    private int transplantAmount;






}
