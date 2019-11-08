package com.gaogao.cucaishop.adm.mappers;

import com.gaogao.cucaishop.home.models.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface UserMapper {
    Optional<Users> findByUserName(@Param("username") String username);

    Users registerNewUser(Users users);

    Users checkUser(@Param("username") String username);

    Users updateExistingUser(Users users);
}
