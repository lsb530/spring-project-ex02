package org.codechobo.mapper;

import org.codechobo.domain.LoginUser;

import java.util.List;

public interface LoginUserMapper {
    List<LoginUser> getList();

    void insert(LoginUser user);

    LoginUser read(String id);

    int delete(String id);

    int update(LoginUser user);
}
