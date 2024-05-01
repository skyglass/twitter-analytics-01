package net.greeta.twitter.elastic.query.business;

import net.greeta.twitter.elastic.query.dataaccess.entity.UserPermission;

import java.util.List;
import java.util.Optional;

public interface QueryUserService {

    Optional<List<UserPermission>> findAllPermissionsByUsername(String username);
}
