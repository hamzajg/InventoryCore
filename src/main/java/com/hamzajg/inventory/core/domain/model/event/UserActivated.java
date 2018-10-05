package com.hamzajg.inventory.core.domain.model.event;

import com.hamzajg.inventory.core.domain.DomainEvent;

import java.time.Instant;

public class UserActivated implements DomainEvent {

    private final Instant when;

    public UserActivated(Instant when) {
        this.when = when;
    }

    @Override
    public Instant occuredAt() {
        return when;
    }
}
