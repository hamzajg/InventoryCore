package com.hamzajg.inventory.core.domain;

import java.time.Instant;

public interface DomainEvent {
    Instant occuredAt();
}
