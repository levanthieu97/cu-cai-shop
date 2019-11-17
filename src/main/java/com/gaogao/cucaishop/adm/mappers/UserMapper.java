package com.gaogao.cucaishop.adm.mappers;

import com.gaogao.cucaishop.home.models.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    Users findByEmail(@Param("email") String email);

    Users registerNewUser(Users users);

    Users saveInfoBySocial(Users users);

//    Users checkUser(@Param("username") String username);

    Users updateExistingUser(Users users);
}
