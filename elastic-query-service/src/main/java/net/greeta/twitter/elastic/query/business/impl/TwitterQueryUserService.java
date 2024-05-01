package net.greeta.twitter.elastic.query.business.impl;

import net.greeta.twitter.elastic.query.business.QueryUserService;
import net.greeta.twitter.elastic.query.dataaccess.entity.UserPermission;
import net.greeta.twitter.elastic.query.dataaccess.repository.UserPermissionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TwitterQueryUserService implements QueryUserService {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterQueryUserService.class);


    private final UserPermissionRepository userPermissionRepository;

    public TwitterQueryUserService(UserPermissionRepository permissionRepository) {
        this.userPermissionRepository = permissionRepository;
    }

    @Override
    public Optional<List<UserPermission>> findAllPermissionsByUsername(String username) {
        LOG.info("Finding permissions by username {}", username);
        return userPermissionRepository.findPermissionsByUsername(username);
    }
}
