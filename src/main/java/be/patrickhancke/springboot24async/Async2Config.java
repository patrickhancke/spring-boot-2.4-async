package be.patrickhancke.springboot24async;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Configuration
public class Async2Config {
    private static final Logger logger = LoggerFactory.getLogger(Async2Config.class);
    private static final Duration EXECUTOR_SHUTDOWN_DURATION = Duration.ofSeconds(10);

    @Bean
    public CloseableExecutorService asyncJobExecutor() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("executor2-%d")
                .setUncaughtExceptionHandler((thread, throwable) -> logger.error("Error while running async job thread {}", thread, throwable))
                .build();
        ExecutorService executorService = Executors.newCachedThreadPool(threadFactory);
        return new CloseableExecutorService(executorService, EXECUTOR_SHUTDOWN_DURATION);
    }
}
