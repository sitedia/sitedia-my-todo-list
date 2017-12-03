package com.sitedia.mytodolist.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.sitedia.common.rest.exception.BusinessException;
import com.sitedia.common.rest.exception.TechnicalException;
import com.sitedia.common.rest.mapper.AbstractCrudMapper;
import com.sitedia.common.rest.utils.Sha512PasswordEncoder;
import com.sitedia.mytodolist.dto.UserCreationDTO;
import com.sitedia.mytodolist.dto.UserDTO;
import com.sitedia.mytodolist.dto.UserUpdateDTO;
import com.sitedia.mytodolist.entity.UserEntity;

@Lazy
@Component
public class UserMapper extends AbstractCrudMapper<UserCreationDTO, UserDTO, UserUpdateDTO, UserEntity, Long> {

    @Value("${security.salt}")
    private String salt;

    @Override
    public UserEntity fromCreationDTO(UserCreationDTO creationDTO) throws BusinessException, TechnicalException {
        UserEntity result = super.fromCreationDTO(creationDTO);

        // Encrypt password
        result.setPassword(new Sha512PasswordEncoder(salt).encode(creationDTO.getPassword()));

        return result;
    }

    @Override
    public UserEntity fromUpdateDTO(UserUpdateDTO updateDTO, Long id) throws BusinessException, TechnicalException {
        UserEntity result = super.fromUpdateDTO(updateDTO, id);

        // Encrypt new password
        if (updateDTO.getNewPassword() != null) {
            result.setPassword(new Sha512PasswordEncoder(salt).encode(updateDTO.getNewPassword()));
        }

        return result;
    }

    @Override
    protected Class<UserCreationDTO> getCreationDTOClass() {
        return UserCreationDTO.class;
    }

    @Override
    protected Class<UserDTO> getDTOClass() {
        return UserDTO.class;
    }

    @Override
    protected Class<UserUpdateDTO> getUpdateDTOClass() {
        return UserUpdateDTO.class;
    }

    @Override
    protected Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }

}
