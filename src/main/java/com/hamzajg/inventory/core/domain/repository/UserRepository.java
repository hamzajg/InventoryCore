package com.hamzajg.inventory.core.domain.repository;

import com.hamzajg.inventory.core.domain.model.User;

import java.util.UUID;

public interface UserRepository {
    void save(User user);
    User find(UUID uuid);
}
