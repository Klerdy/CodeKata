package de.klerdy.codekata.tools;

import java.time.LocalTime;

/**
 * This interface measures the runtime of a code section.
 * @author Norman Nusse
 */
public interface StopWatch {
    /** Starts the timer. */
    LocalTime start();
    /** Stops the timer. */
    LocalTime stop();
    /** Resets the timer. */
    void reset();
    /** returns the runtime of a code section. */
    double getRuntime(final Time timeUnit);
}
