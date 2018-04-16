package de.klerdy.codekata.tools;

import java.time.Duration;
import java.time.LocalTime;

public class StopWatchImpl implements StopWatch {

    /** Constants. **/
    private static final String WRONG_TIMER_STATE = "The StopWatch is currently not running. " +
            "Pls Start and Stop the Watch.";
    private static final String WRONG_TIMER_USAGE = "'start()' must be called before 'stop().'";
    private static final String WRONG_START_TIMER_USAGE = "The timer has already been started. Use 'reset()''";
    private static final String WRONG_STOP_TIMER_USAGE = "The timer has already been stopped. Use 'reset()'";

    private LocalTime start = null;
    private LocalTime stop = null;
    private Duration runtime = null;

    @Override
    public LocalTime start() {
        if(start != null){
            throw new IllegalStateException(WRONG_START_TIMER_USAGE);
        }
        start = LocalTime.now();
        return start;
    }

    @Override
    public LocalTime stop() {
        if(stop != null){
            throw new IllegalStateException(WRONG_STOP_TIMER_USAGE);
        }
        stop = LocalTime.now();
        return stop;
    }

    @Override
    public void reset() {
        start = null;
        stop = null;
        runtime = null;
    }

    @Override
    public double getRuntime(final Time timeUnit) {
        validateRightUsage();
        runtime = Duration.between(start, stop);
        return timeUnit.calculateDuration(runtime);
    }

    /**
     * Validate the timers before the service will calculate the runtime of the code section.
     */
    private void validateRightUsage(){
        if (start == null || stop == null) {
            throw new IllegalStateException(WRONG_TIMER_STATE);
        } else if(stop.isBefore(start)) {
            throw new IllegalStateException(WRONG_TIMER_USAGE);
        }
    }
}
