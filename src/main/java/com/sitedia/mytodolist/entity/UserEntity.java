package com.sitedia.mytodolist.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.sitedia.mytodolist.utils.RoleType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = { "id" })
@Entity
@Table(name = "users")
@NamedNativeQuery(name = "user.getByMail", query = "SELECT * FROM users WHERE mail = :mail", resultClass = UserEntity.class)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mail;

    private String firstName;

    private String lastName;

    @DateTimeFormat
    private Date dateOfBirth;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

}
