package com.sitedia.mytodolist.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitedia.common.rest.controller.AbstractCrudController;
import com.sitedia.common.rest.exception.BusinessException;
import com.sitedia.common.rest.exception.TechnicalException;
import com.sitedia.common.rest.service.AbstractCrudService;
import com.sitedia.common.rest.utils.SecurityUtils;
import com.sitedia.mytodolist.dto.TodoCreationDTO;
import com.sitedia.mytodolist.dto.TodoDTO;
import com.sitedia.mytodolist.dto.TodoUpdateDTO;
import com.sitedia.mytodolist.dto.UserDTO;
import com.sitedia.mytodolist.entity.TodoEntity;
import com.sitedia.mytodolist.service.TodoService;
import com.sitedia.mytodolist.service.UserService;

/**
 * Todo controller
 * 
 * @author cedric
 *
 */
@RestController
@RequestMapping(path = { "/api/v1.0/todos" }, produces = "application/json;charset=UTF-8")
public class TodoController extends AbstractCrudController<TodoCreationDTO, TodoDTO, TodoUpdateDTO, TodoEntity, Long> {

    @Lazy
    @Autowired
    private TodoService todoService;

    @Lazy
    @Autowired
    private UserService userService;

    /**
     * Checks if the connected user is the owner of the to-do
     * @param ownerId
     * @return
     * @throws BusinessException
     * @throws TechnicalException
     */
    public boolean isOwner(Long ownerId) throws BusinessException, TechnicalException {
        UserDTO connectedUser = userService.getByMail(SecurityUtils.getUsername());
        return connectedUser != null && ownerId.equals(connectedUser.getId());
    }

    @Override
    public boolean hasListAccess(HttpServletRequest request) throws BusinessException, TechnicalException {
        Long userId = request.getParameter("userId") != null ? Long.parseLong(request.getParameter("userId")) : null;
        return userId != null ? isOwner(userId) : false;
    }

    @Override
    public boolean hasCreateAccess(TodoCreationDTO creationDTO, HttpServletRequest request) throws BusinessException, TechnicalException {
        return isOwner(creationDTO.getUserId());
    }

    @Override
    public boolean hasGetAccess(Long id, HttpServletRequest request) throws BusinessException, TechnicalException {
        TodoDTO todo = todoService.get(id);
        return todo != null ? isOwner(todo.getUserId()) : false;
    }

    @Override
    public boolean hasUpdateAccess(Long id, TodoUpdateDTO updateDTO, HttpServletRequest request) throws BusinessException, TechnicalException {
        TodoDTO todo = todoService.get(id);
        return todo != null ? isOwner(todo.getUserId()) : false;
    }

    @Override
    public boolean hasDeleteAccess(Long id, HttpServletRequest request) throws BusinessException, TechnicalException {
        TodoDTO todo = todoService.get(id);
        return todo != null ? isOwner(todo.getUserId()) : false;
    }

    @Override
    protected AbstractCrudService<TodoCreationDTO, TodoDTO, TodoUpdateDTO, TodoEntity, Long> getService() {
        return todoService;
    }

}
