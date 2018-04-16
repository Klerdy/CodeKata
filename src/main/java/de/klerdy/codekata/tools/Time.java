package de.klerdy.codekata.tools;

import java.time.Duration;

/**
 * This enum specifies the possible time units that the {@link StopWatch} supports.
 * It converts the runtime duration for each unit.
 */
 public enum Time {
    MILLI {
        @Override
        public double calculateDuration(Duration runtimeDuration) {
            return runtimeDuration.toMillis();
        }
    },
    SECONDS {
        @Override
        public double calculateDuration(Duration runtimeDuration) {
            return runtimeDuration.toMillis()/1000.0;
        }
    },
    MINUTES {
        @Override
        public double calculateDuration(Duration runtimeDuration) {
            return runtimeDuration.toMinutes();
        }
    };

    public abstract double calculateDuration(Duration runtimeDuration);
}
