package com.sitedia.mytodolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitedia.common.rest.controller.AbstractCrudController;
import com.sitedia.common.rest.service.AbstractCrudService;
import com.sitedia.mytodolist.dto.UserCreationDTO;
import com.sitedia.mytodolist.dto.UserDTO;
import com.sitedia.mytodolist.dto.UserUpdateDTO;
import com.sitedia.mytodolist.entity.UserEntity;
import com.sitedia.mytodolist.service.UserService;

/**
 * User controller
 * @author cedric
 *
 */
@RestController
@RequestMapping(path = { "/api/v1.0/users" }, produces = "application/json;charset=UTF-8")
public class UserController extends AbstractCrudController<UserCreationDTO, UserDTO, UserUpdateDTO, UserEntity, Long> {

    @Lazy
    @Autowired
    private UserService userService;

    @Override
    protected AbstractCrudService<UserCreationDTO, UserDTO, UserUpdateDTO, UserEntity, Long> getService() {
        return userService;
    }

}
