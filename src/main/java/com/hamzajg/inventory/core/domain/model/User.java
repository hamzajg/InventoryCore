package com.hamzajg.inventory.core.domain.model;

import java.util.UUID;

public class User {

    private String nickname;

    public UUID getUuid() {
        return uuid;
    }

    enum UserState{
        INITIALIZED, ACTIVATED, DEACTIVATED
    }
    private final UUID uuid;
    private UserState state = UserState.INITIALIZED;
    public User(UUID uuid) {
        this.uuid = uuid;
    }

    void activate() {
        if(isActivated())
            throw new IllegalStateException();
        state = UserState.ACTIVATED;
    }

    void deactivate() {
        if(isDeactivated())
            throw new IllegalStateException();
        state = UserState.DEACTIVATED;
    }

    void changeNicknameTo(String newNickName) {
        if(isDeactivated())
            throw new IllegalStateException();
        nickname = newNickName;
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
