package be.patrickhancke.springboot24async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CloseableExecutorService implements AutoCloseable {
    private static final Logger logger = LoggerFactory.getLogger(CloseableExecutorService.class);
    private final ExecutorService executor;
    private final Duration durationToAwaitTermination;

    public CloseableExecutorService(ExecutorService executor, Duration durationToAwaitTermination) {
        this.executor = executor;
        this.durationToAwaitTermination = durationToAwaitTermination;
    }

    @Override
    public void close() {
        logger.info("shutting down {}", this);
        try {
            executor.shutdown();
            terminate();
            logger.info("shut down {}", this);
        } catch (RuntimeException e) {
            logger.error("failed to shutdown {}. Reason: ", this, e);
        }
    }

    private void terminate() {
        try {
            if (!executor.awaitTermination(durationToAwaitTermination.getSeconds(), TimeUnit.SECONDS)) {
                logger.warn("{} failed to terminate properly after {}", this, durationToAwaitTermination);
                executor.shutdownNow();
                if (!executor.awaitTermination(durationToAwaitTermination.getSeconds(), TimeUnit.SECONDS)) {
                    logger.error("{} did not terminate", this);
                }
            }
        } catch (InterruptedException ie) {
            logger.warn("interrupted while waiting for termination", ie);
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public Future<?> submit(Runnable runnable) {
        return executor.submit(runnable);
    }

    public <V> Future<V> submit(Callable<V> callable) {
        return executor.submit(callable);
    }
}

