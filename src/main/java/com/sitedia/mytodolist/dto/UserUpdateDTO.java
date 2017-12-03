package com.sitedia.mytodolist.dto;

import java.util.Date;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
@JsonInclude(Include.NON_NULL)
public class UserUpdateDTO {

    @Size(min = 2, max = 50, message = "error.firstName.invalid")
    @ApiModelProperty(notes = "First name")
    private String firstName;

    @Size(min = 2, max = 50, message = "error.lastName.invalid")
    @ApiModelProperty(notes = "Last name")
    private String lastName;

    @ApiModelProperty(notes = "Date of birth")
    private Date dateOfBirth;

    @Size(min = 6, max = 40, message = "error.currentPassword.invalid")
    @ApiModelProperty(notes = "Current password")
    private String currentPassword;

    @Size(min = 6, max = 40, message = "error.newPassword.invalid")
    @ApiModelProperty(notes = "New password")
    private String newPassword;

    @ApiModelProperty(notes = "Role")
    private RoleType role;

}
