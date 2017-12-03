package com.sitedia.mytodolist.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.sitedia.common.rest.exception.BusinessException;
import com.sitedia.common.rest.mapper.AbstractCrudMapper;
import com.sitedia.common.rest.service.AbstractCrudService;
import com.sitedia.mytodolist.dto.TodoCreationDTO;
import com.sitedia.mytodolist.dto.TodoDTO;
import com.sitedia.mytodolist.dto.TodoUpdateDTO;
import com.sitedia.mytodolist.entity.TodoCategoryEntity;
import com.sitedia.mytodolist.entity.TodoEntity;
import com.sitedia.mytodolist.mapper.TodoMapper;

@Service
@Lazy
public class TodoService extends AbstractCrudService<TodoCreationDTO, TodoDTO, TodoUpdateDTO, TodoEntity, Long> {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TodoMapper todoMapper;

    @Override
    protected void internalPostCreate(TodoCreationDTO creationDTO, TodoEntity created) throws BusinessException {

        // Set categories in join table
        if (creationDTO.getCategories() != null) {
            for (Long categoryId : creationDTO.getCategories()) {
                daoManager.create(TodoCategoryEntity.class, new TodoCategoryEntity(created.getId(), categoryId), null, null);
            }
        }
    }

    @Override
    protected void internalPostUpdate(Long id, TodoUpdateDTO updateDTO, TodoEntity updated) throws BusinessException {

        // Case of no update
        if (updateDTO.getCategories() == null) {
            return;
        }

        // Delete all to-do categories
        Query query = entityManager.createNativeQuery("DELETE FROM todo_categories WHERE todo_id = :todoId");
        query.setParameter("todoId", id);
        query.executeUpdate();

        // Set categories in join table
        if (updateDTO.getCategories() != null) {
            for (Long categoryId : updateDTO.getCategories()) {
                daoManager.create(TodoCategoryEntity.class, new TodoCategoryEntity(id, categoryId), null, null);
            }
        }
    }

    @Override
    protected AbstractCrudMapper<TodoCreationDTO, TodoDTO, TodoUpdateDTO, TodoEntity, Long> getMapper() {
        return todoMapper;
    }

    @Override
    protected Class<TodoEntity> getEntityClass() {
        return TodoEntity.class;
    }

}