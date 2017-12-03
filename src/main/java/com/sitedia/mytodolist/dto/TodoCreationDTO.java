package com.sitedia.mytodolist.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class TodoCreationDTO {

    @NotNull(message = "error.userId.required")
    private Long userId;

    @NotNull(message = "error.title.required")
    @Size(min = 2, max = 256, message = "error.title.invalid")
    @ApiModelProperty(notes = "Titre")
    private String title;

    @Size(min = 2, max = 256, message = "error.description.invalid")
    @ApiModelProperty(notes = "Description")
    private String description;

    @ApiModelProperty(notes = "Categories")
    private List<Long> categories = new ArrayList<>();

}
