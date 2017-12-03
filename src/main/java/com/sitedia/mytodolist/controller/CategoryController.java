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
import com.sitedia.mytodolist.dto.CategoryCreationDTO;
import com.sitedia.mytodolist.dto.CategoryDTO;
import com.sitedia.mytodolist.dto.CategoryUpdateDTO;
import com.sitedia.mytodolist.entity.CategoryEntity;
import com.sitedia.mytodolist.service.CategoryService;

/**
 * Category controller
 * @author cedric
 *
 */
@RestController
@RequestMapping(path = { "/api/v1.0/categories" }, produces = "application/json;charset=UTF-8")
public class CategoryController extends AbstractCrudController<CategoryCreationDTO, CategoryDTO, CategoryUpdateDTO, CategoryEntity, Long> {

    @Lazy
    @Autowired
    private CategoryService categoryService;

    @Override
    protected AbstractCrudService<CategoryCreationDTO, CategoryDTO, CategoryUpdateDTO, CategoryEntity, Long> getService() {
        return categoryService;
    }

	@Override
	public boolean hasCreateAccess(CategoryCreationDTO creationDTO, HttpServletRequest request)
			throws BusinessException, TechnicalException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasListAccess(HttpServletRequest request) throws BusinessException, TechnicalException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasGetAccess(Long id, HttpServletRequest request) throws BusinessException, TechnicalException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasUpdateAccess(Long id, CategoryUpdateDTO updateDTO, HttpServletRequest request)
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
