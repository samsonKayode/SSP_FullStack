package com.ssp.backend.service;

import com.ssp.backend.dto.UserDto;
import com.ssp.backend.entity.UserEntity;
import com.ssp.backend.enums.RoleTypes;
import com.ssp.backend.mapper.UserMapper;
import com.ssp.backend.repository.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDAO;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    private final UserMapper userMapper;

    @Override
    public UserEntity saveUser(UserDto userDTO) {
        UserEntity userEntity = userMapper.toUserEntity(userDTO);

        if(userDAO.existsByUserName(userEntity.getUserName())) {
            log.error("duplicate user");
            throw new DuplicateUserException();
        }

        if(userEntity.getRoles().isEmpty() || userEntity.getRoles() == null) {
            userEntity.getRoles().add(RoleTypes.USER);
        }
        userEntity.setPassword(bcryptEncoder.encode(userEntity.getPassword()));
        userEntity.setEnabled(true);

        return userDAO.save(userEntity);
    }

    @Override
    public UserEntity updateUser(UserDto userDTO, Long id) {
        Optional<UserEntity> oldEntity = userDAO.findById(id);

        if(!oldEntity.isPresent()) {
            log.error("Invalid user id provided");
            throw new InvalidDataSubmittedException();
        }

        UserEntity userEntity = userMapper.toUserEntity(userDTO);
        userEntity.setId(findByUsername(userDTO.getUserName()).getId());

        return userDAO.save(userEntity);
    }

    @Override
    public UserDto findByUsername(String userName) {
        return userMapper.toUserDto(userDAO.findByUserName(userName));
    }

    @Override
    public Page<UserEntity> getAllUsers(int pageNo, int pageSize, String sortField, String sortDirection) {

        final Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        final Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);

        final Page<UserEntity> userEntityPage = userDAO.findAll(pageable);

        return userEntityPage;
    }
}
