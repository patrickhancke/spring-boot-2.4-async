package be.patrickhancke.springboot24async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AsyncService {
    private static final Logger logger = LoggerFactory.getLogger(AsyncService.class);

    @Async
    public void longRunningJob() throws InterruptedException {
        logger.info("starting long running job");
        TimeUnit.SECONDS.sleep(10L);
        logger.info("finished long running job");
    }
}
