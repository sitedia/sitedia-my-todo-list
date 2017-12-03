package com.sitedia.mytodolist.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sitedia.common.rest.utils.SecureTextDeserializer;
import com.sitedia.mytodolist.utils.RoleType;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO to create a user
 * @author cedric
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    @ApiModelProperty(notes = "Identifier")
    private Long id;

    @JsonDeserialize(using = SecureTextDeserializer.class)
    @ApiModelProperty(notes = "Mail address")
    private String mail;

    @JsonDeserialize(using = SecureTextDeserializer.class)
    @ApiModelProperty(notes = "First name")
    private String firstName;

    @JsonDeserialize(using = SecureTextDeserializer.class)
    @ApiModelProperty(notes = "Last name")
    private String lastName;

    @ApiModelProperty(notes = "Date of birth")
    private Date dateOfBirth;

    @ApiModelProperty(notes = "Role")
    private RoleType role;

}
