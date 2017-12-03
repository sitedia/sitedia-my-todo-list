package com.sitedia.mytodolist.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.sitedia.common.rest.exception.BusinessException;
import com.sitedia.common.rest.exception.TechnicalException;
import com.sitedia.common.rest.mapper.AbstractCrudMapper;
import com.sitedia.common.rest.service.AbstractCrudService;
import com.sitedia.common.rest.service.IUserService;
import com.sitedia.common.rest.utils.JsonUtils;
import com.sitedia.mytodolist.dto.UserCreationDTO;
import com.sitedia.mytodolist.dto.UserDTO;
import com.sitedia.mytodolist.dto.UserUpdateDTO;
import com.sitedia.mytodolist.entity.UserEntity;
import com.sitedia.mytodolist.mapper.UserMapper;

@Service
@Lazy
public class UserService extends AbstractCrudService<UserCreationDTO, UserDTO, UserUpdateDTO, UserEntity, Long> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private MessageSource messageSource;

    /**
     * Update
     * @param userUpdateDTO
     * @param mail
     * @return
     * @throws BusinessException
     * @throws TechnicalException
     */
    @Override
    @Transactional
    public UserDTO update(UserUpdateDTO userUpdateDTO, Long id) throws BusinessException, TechnicalException {

        // Check password
        String currentPassword = userUpdateDTO.getCurrentPassword() != null ? userUpdateDTO.getCurrentPassword() : "";
        String newPassword = userUpdateDTO.getNewPassword() != null ? userUpdateDTO.getNewPassword() : "";
        if (!currentPassword.equals(newPassword)) {
            String message = messageSource.getMessage("user.password.mismatch", null, LocaleContextHolder.getLocale());
            throw new BusinessException(message);
        }

        return super.update(userUpdateDTO, id);
    }

    public UserDTO getByMail(String mail) throws BusinessException, TechnicalException {
        Query query = entityManager.createNamedQuery("user.getByMail");
        query.setParameter("mail", mail);
        try {
            return userMapper.toDTO((UserEntity) query.getSingleResult());
        } catch (NoResultException e) {
            Logger.getLogger(getClass().getName()).log(Level.FINE, e.getMessage(), e);
            return null;
        }
    }

    @Override
    protected Long getId(UserCreationDTO creationDTO) {
        return null;
    }

    @Override
    public JsonNode getUserByName(String name) throws BusinessException, TechnicalException {
        UserDTO user = getByMail(name);
        return JsonUtils.toJsonNode(user);
    }

    @Override
    protected AbstractCrudMapper<UserCreationDTO, UserDTO, UserUpdateDTO, UserEntity, Long> getMapper() {
        return userMapper;
    }

    @Override
    protected Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }

}
