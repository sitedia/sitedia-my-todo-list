package com.sitedia.mytodolist.mapper;

import java.util.Map;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.sitedia.common.rest.mapper.AbstractCrudMapper;
import com.sitedia.mytodolist.dto.CategoryCreationDTO;
import com.sitedia.mytodolist.dto.CategoryDTO;
import com.sitedia.mytodolist.dto.CategoryUpdateDTO;
import com.sitedia.mytodolist.entity.CategoryEntity;

@Component
@Lazy
public class CategoryMapper extends AbstractCrudMapper<CategoryCreationDTO, CategoryDTO, CategoryUpdateDTO, CategoryEntity, Long> {

    @Override
    public Map<String, Object> convertParams(Map<String, Object> params) {

        // userId is a Long
        // TODO Cloner la map
        if (params.get("userId") != null) {
            params.put("userId", Long.parseLong(params.get("userId").toString()));
        }

        return params;
    }

    @Override
    protected Class<CategoryCreationDTO> getCreationDTOClass() {
        return CategoryCreationDTO.class;
    }

    @Override
    protected Class<CategoryDTO> getDTOClass() {
        return CategoryDTO.class;
    }

    @Override
    protected Class<CategoryUpdateDTO> getUpdateDTOClass() {
        return CategoryUpdateDTO.class;
    }

    @Override
    protected Class<CategoryEntity> getEntityClass() {
        return CategoryEntity.class;
    }

}
