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
import com.sitedia.mytodolist.dto.TodoCreationDTO;
import com.sitedia.mytodolist.dto.TodoDTO;
import com.sitedia.mytodolist.dto.TodoUpdateDTO;
import com.sitedia.mytodolist.entity.TodoEntity;
import com.sitedia.mytodolist.service.TodoService;

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

	@Override
	protected AbstractCrudService<TodoCreationDTO, TodoDTO, TodoUpdateDTO, TodoEntity, Long> getService() {
		return todoService;
	}

	@Override
	public boolean hasListAccess(HttpServletRequest request) throws BusinessException, TechnicalException {
		return false;
	}

	@Override
	public boolean hasCreateAccess(TodoCreationDTO creationDTO, HttpServletRequest request)
			throws BusinessException, TechnicalException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasGetAccess(Long id, HttpServletRequest request) throws BusinessException, TechnicalException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasUpdateAccess(Long id, TodoUpdateDTO updateDTO, HttpServletRequest request)
			throws BusinessException, TechnicalException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasDeleteAccess(Long id, HttpServletRequest request) throws BusinessException, TechnicalException {
		// TODO Auto-generated method stub
		return false;
	}

}
