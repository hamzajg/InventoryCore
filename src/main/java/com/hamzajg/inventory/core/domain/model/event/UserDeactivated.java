package com.hamzajg.inventory.core.domain.model.event;

import com.hamzajg.inventory.core.domain.DomainEvent;

import java.time.Instant;

public class UserDeactivated implements DomainEvent {

    private final Instant when;

    public UserDeactivated(Instant when) {
        this.when = when;
    }

    @Override
    public Instant occuredAt() {
        return when;
    }
}
