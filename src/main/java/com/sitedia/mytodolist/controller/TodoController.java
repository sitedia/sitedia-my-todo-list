package com.sitedia.mytodolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sitedia.common.rest.controller.AbstractCrudController;
import com.sitedia.common.rest.service.AbstractCrudService;
import com.sitedia.mytodolist.dto.TodoCreationDTO;
import com.sitedia.mytodolist.dto.TodoDTO;
import com.sitedia.mytodolist.dto.TodoUpdateDTO;
import com.sitedia.mytodolist.entity.TodoEntity;
import com.sitedia.mytodolist.service.TodoService;

/**
 * Todo controller
 * @author cedric
 *
 */
@RestController
@RequestMapping(path = { "/api/v1.0/todos" }, produces = "application/json;charset=UTF-8")
public class TodoController extends AbstractCrudController<TodoCreationDTO, TodoDTO, TodoUpdateDTO, TodoEntity, Long> {

    @Lazy
    @Autowired
    private TodoService todoService;

    @Override
    protected AbstractCrudService<TodoCreationDTO, TodoDTO, TodoUpdateDTO, TodoEntity, Long> getService() {
        return todoService;
    }

}
