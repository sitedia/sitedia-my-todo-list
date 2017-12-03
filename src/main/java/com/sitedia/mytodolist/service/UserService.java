package com.sitedia.mytodolist.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.sitedia.common.rest.exception.BusinessException;
import com.sitedia.common.rest.exception.TechnicalException;
import com.sitedia.common.rest.mapper.AbstractCrudMapper;
import com.sitedia.common.rest.service.AbstractCrudService;
import com.sitedia.mytodolist.dto.UserCreationDTO;
import com.sitedia.mytodolist.dto.UserDTO;
import com.sitedia.mytodolist.dto.UserUpdateDTO;
import com.sitedia.mytodolist.entity.UserEntity;
import com.sitedia.mytodolist.mapper.UserMapper;

@Service
@Lazy
public class UserService extends AbstractCrudService<UserCreationDTO, UserDTO, UserUpdateDTO, UserEntity, Long> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EntityManager entityManager;

    /**
     * Create the user in database, not activated
     * @param userCreationDTO
     * @return
     * @throws BusinessException
     * @throws TechnicalException
     */
    @Override
    @Transactional
    public UserDTO create(UserCreationDTO userCreationDTO) throws BusinessException, TechnicalException {
        UserDTO result = super.create(userCreationDTO);

        // Send mail

        return result;
    }

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

        return super.update(userUpdateDTO, id);
    }

    @Override
    protected AbstractCrudMapper<UserCreationDTO, UserDTO, UserUpdateDTO, UserEntity, Long> getMapper() {
        return userMapper;
    }

    @Override
    protected Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }

    public UserDTO getByMail(String mail) throws BusinessException, TechnicalException {
        Query query = entityManager.createNamedQuery("user.getByMail");
        query.setParameter("mail", mail);
        try {
            return userMapper.toDTO((UserEntity) query.getSingleResult());
        } catch (NoResultException e) {
            // TODO Log exception
            return null;
        }
    }
    
    @Override
    protected Long getId(UserCreationDTO creationDTO) {
    	return null;
    }
    

}