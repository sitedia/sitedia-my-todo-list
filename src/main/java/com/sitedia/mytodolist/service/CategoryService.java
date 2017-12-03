package com.sitedia.mytodolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.sitedia.common.rest.mapper.AbstractCrudMapper;
import com.sitedia.common.rest.service.AbstractCrudService;
import com.sitedia.mytodolist.dto.CategoryCreationDTO;
import com.sitedia.mytodolist.dto.CategoryDTO;
import com.sitedia.mytodolist.dto.CategoryUpdateDTO;
import com.sitedia.mytodolist.entity.CategoryEntity;
import com.sitedia.mytodolist.mapper.CategoryMapper;

@Service
@Lazy
public class CategoryService extends AbstractCrudService<CategoryCreationDTO, CategoryDTO, CategoryUpdateDTO, CategoryEntity, Long> {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    protected AbstractCrudMapper<CategoryCreationDTO, CategoryDTO, CategoryUpdateDTO, CategoryEntity, Long> getMapper() {
        return categoryMapper;
    }

    @Override
    protected Class<CategoryEntity> getEntityClass() {
        return CategoryEntity.class;
    }

    @Override
    protected Long getId(CategoryCreationDTO creationDTO) {
        return null;
    }

}
