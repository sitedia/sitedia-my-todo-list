package com.sitedia.mytodolist.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sitedia.common.rest.utils.SecureTextDeserializer;

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
public class TodoDTO {

    @ApiModelProperty(notes = "Identifier")
    private Long id;

    @ApiModelProperty(notes = "User identifier")
    private Long userId;

    @JsonDeserialize(using = SecureTextDeserializer.class)
    @ApiModelProperty(notes = "Title")
    private String title;

    @JsonDeserialize(using = SecureTextDeserializer.class)
    @ApiModelProperty(notes = "Description")
    private String description;

    @ApiModelProperty(notes = "Categories")
    private List<Long> categories = new ArrayList<>();

}
