package com.test.current.account.api.model;

import java.time.LocalDateTime;

public enum TimeUnit {
    DAY {
        @Override
        public LocalDateTime getLocalDateTime(Integer unitCount) {
            return LocalDateTime.now().minusDays(unitCount);
        }
    }, HOUR {
        @Override
        public LocalDateTime getLocalDateTime(Integer unitCount) {
            return LocalDateTime.now().minusHours(unitCount);
        }
    };

    abstract public LocalDateTime getLocalDateTime(Integer unitSize);
}