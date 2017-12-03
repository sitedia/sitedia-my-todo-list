package com.sitedia.mytodolist.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

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
public class UserCreationDTO {

    @NotNull(message = "error.mail.required")
    @Email(message = "error.mail.invalid")
    @ApiModelProperty(notes = "Mail address")
    private String mail;

    @NotNull(message = "error.firstName.required")
    @Size(min = 2, max = 50, message = "error.firstName.invalid")
    @ApiModelProperty(notes = "First name")
    private String firstName;

    @NotNull(message = "error.lastName.required")
    @Size(min = 2, max = 50, message = "error.lastName.invalid")
    @ApiModelProperty(notes = "Last name")
    private String lastName;

    @NotNull(message = "error.dateOfBirth.required")
    @ApiModelProperty(notes = "Date of birth")
    private Date dateOfBirth;

    @NotNull(message = "error.password.required")
    @Size(min = 6, max = 40, message = "error.password.invalid")
    @ApiModelProperty(notes = "Password")
    private String password;

    @NotNull(message = "error.role.required")
    @ApiModelProperty(notes = "Role")
    private RoleType role;

}
