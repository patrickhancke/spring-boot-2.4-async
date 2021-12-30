package be.patrickhancke.springboot24async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AsyncService {
    private static final Logger logger = LoggerFactory.getLogger(AsyncService.class);
    private final CloseableExecutorService closeableExecutorService;

    public AsyncService(CloseableExecutorService closeableExecutorService) {
        this.closeableExecutorService = closeableExecutorService;
    }

    @Async("executor1")
    public void longRunningJob1() throws InterruptedException {
        logger.info("starting long running job 1");
        TimeUnit.SECONDS.sleep(10L);
        logger.info("finished long running job 1");
    }

    public void longRunningJob2() {
        closeableExecutorService.submit(() -> {
            try {
                logger.info("starting long running job 2");
                TimeUnit.SECONDS.sleep(10L);
                logger.info("finished long running job 2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
