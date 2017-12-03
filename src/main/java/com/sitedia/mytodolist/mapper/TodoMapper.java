package com.sitedia.mytodolist.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.sitedia.common.rest.exception.BusinessException;
import com.sitedia.common.rest.exception.TechnicalException;
import com.sitedia.common.rest.mapper.AbstractCrudMapper;
import com.sitedia.mytodolist.dto.TodoCreationDTO;
import com.sitedia.mytodolist.dto.TodoDTO;
import com.sitedia.mytodolist.dto.TodoUpdateDTO;
import com.sitedia.mytodolist.entity.TodoCategoryEntity;
import com.sitedia.mytodolist.entity.TodoEntity;

/**
 * To-do mapper
 * @author cedric
 *
 */
@Component
@Lazy
public class TodoMapper extends AbstractCrudMapper<TodoCreationDTO, TodoDTO, TodoUpdateDTO, TodoEntity, Long> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public TodoEntity fromCreationDTO(TodoCreationDTO creationDTO) throws BusinessException, TechnicalException {
        TodoEntity result = super.fromCreationDTO(creationDTO);

        // Set creation date
        result.setCreationDate(new Date());

        return result;
    }

    @Override
    public TodoDTO toDTO(TodoEntity entity) throws BusinessException, TechnicalException {
        TodoDTO result = super.toDTO(entity);

        // Add categories
        Map<String, Object> params = new HashMap<>();
        params.put("todoId", result.getId());
        List<TodoCategoryEntity> categories = daoManager.list(TodoCategoryEntity.class, params);
        for (TodoCategoryEntity category : categories) {
            result.getCategories().add(category.getCategoryId());
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> convertParams(Map<String, Object> params) {
        // TODO Cloner la map

        // userId is a Long
        if (params.get("userId") != null) {
            params.put("userId", Long.parseLong(params.get("userId").toString()));
        }

        // Extract categories
        if (params.get("categoryId") != null) {
            Long categoryId = Long.parseLong(params.get("categoryId").toString());
            Query query = entityManager.createNativeQuery("SELECT todo_id FROM todo_categories WHERE category_id = :categoryId");
            query.setParameter("categoryId", categoryId);
            List<Long> todoIds = query.getResultList();
            if (todoIds.isEmpty()) {
                todoIds.add(-1L);
            }
            params.remove("categoryId");
            params.put("id_in", todoIds);
        }

        return params;
    }

    @Override
    protected Class<TodoCreationDTO> getCreationDTOClass() {
        return TodoCreationDTO.class;
    }

    @Override
    protected Class<TodoDTO> getDTOClass() {
        return TodoDTO.class;
    }

    @Override
    protected Class<TodoUpdateDTO> getUpdateDTOClass() {
        return TodoUpdateDTO.class;
    }

    @Override
    protected Class<TodoEntity> getEntityClass() {
        return TodoEntity.class;
    }

}
