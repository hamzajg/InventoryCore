package com.hamzajg.inventory.core.domain.repository;

import com.hamzajg.inventory.core.domain.DomainEvent;
import com.hamzajg.inventory.core.domain.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class EventSourcedUserRepository implements UserRepository {

    private final Map<UUID, List<DomainEvent>> users = new ConcurrentHashMap<>();
    @Override
    public void save(User user) {
        List<DomainEvent> newEvents = user.getEvents();
        List<DomainEvent> currentChanges = users.getOrDefault(user.getUuid(), new ArrayList<>());
        currentChanges.addAll(newEvents);
        users.put(user.getUuid(), currentChanges);
        user.flushEvents();
    }

    @Override
    public User find(UUID uuid) {
        if(!users.containsKey(uuid))
            return null;
        return User.recreateFrom(uuid, users.get(uuid));

    }
}
