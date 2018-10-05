package com.hamzajg.inventory.core.domain.model;

import com.google.common.collect.ImmutableList;
import com.hamzajg.inventory.core.domain.DomainEvent;
import com.hamzajg.inventory.core.domain.model.event.UserActivated;
import com.hamzajg.inventory.core.domain.model.event.UserDeactivated;
import com.hamzajg.inventory.core.domain.model.event.UserNicknameChanged;
import javaslang.API;
import javaslang.Predicates;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javaslang.API.Case;


public class User {
    private List<DomainEvent> events = new ArrayList<>();
    private String nickname;

    public static User recreateFrom(UUID uuid, List<DomainEvent> domainEvents) {
        return javaslang.collection.List.ofAll(domainEvents).foldLeft(new User(uuid), User::handleEvent);
    }

    private User handleEvent(DomainEvent domainEvent) {
        return API.Match(domainEvent).of(
                Case(Predicates.instanceOf(UserActivated.class), this::userActivated),
                Case(Predicates.instanceOf(UserDeactivated.class), this::userDeactivated),
                Case(Predicates.instanceOf(UserNicknameChanged.class), this::userNicknameChanged)
        );
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<DomainEvent> getEvents() {
        return ImmutableList.copyOf(events);
    }

    public void flushEvents(){
        events.clear();
    }
    enum UserState {
        INITIALIZED, ACTIVATED, DEACTIVATED
    }

    private final UUID uuid;
    private UserState state = UserState.INITIALIZED;

    public User(UUID uuid) {
        this.uuid = uuid;
    }

    void activate() {
        if (isActivated())
            throw new IllegalStateException();
        userActivated(new UserActivated(Instant.now()));
    }

    private User userActivated(UserActivated userActivated) {
        state = UserState.ACTIVATED;
        events.add(userActivated);
        return this;

    }

    void deactivate() {
        if (isDeactivated())
            throw new IllegalStateException();
        userDeactivated(new UserDeactivated(Instant.now()));
    }

    private User userDeactivated(UserDeactivated userDeactivated) {
        state = UserState.DEACTIVATED;
        events.add(userDeactivated);
        return this;
    }

    void changeNicknameTo(String newNickName) {
        if (isDeactivated())
            throw new IllegalStateException();
        userNicknameChanged(new UserNicknameChanged(newNickName, Instant.now()));
    }

    private User userNicknameChanged(UserNicknameChanged userNicknameChanged) {
        nickname = userNicknameChanged.getNewNickName();
        events.add(userNicknameChanged);
        return this;
    }

    boolean isActivated() {
        return state.equals(UserState.ACTIVATED);
    }

    boolean isDeactivated() {
        return state.equals(UserState.DEACTIVATED);
    }

    String getNickname() {
        return nickname;
    }
}
