package com.hamzajg.inventory.core.domain.model.event;

import com.hamzajg.inventory.core.domain.DomainEvent;

import java.time.Instant;

public class UserNicknameChanged implements DomainEvent {
    private final String newNickName;
    private final Instant when;
    private String newNickname = "";

    public UserNicknameChanged(String newNickName, Instant when) {

        this.newNickName = newNickName;
        this.when = when;
    }

    public String getNewNickName() {
        return newNickname;
    }

    @Override
    public Instant occuredAt() {
        return when;
    }
}
