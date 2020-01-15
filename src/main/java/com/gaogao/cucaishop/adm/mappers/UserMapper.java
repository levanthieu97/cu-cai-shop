package com.gaogao.cucaishop.adm.mappers;

import com.gaogao.cucaishop.home.models.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    Users findByEmail(@Param("email") String email);

    int registerNewUser(Users users);

    int saveInfoBySocial(Users users);

//    Users checkUser(@Param("username") String username);

    int updateExistingUser(Users users);

    int insertUserRole(@Param("roleId") int roleId, @Param("userId") String userId);

    Users checkUser(@Param("email") String email);
}
