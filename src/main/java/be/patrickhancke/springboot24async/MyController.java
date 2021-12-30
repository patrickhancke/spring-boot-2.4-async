package be.patrickhancke.springboot24async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);
    private final AsyncService asyncService;

    public MyController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("job1")
    String launchJob1() throws InterruptedException {
        logger.info("triggering long running job 1");
        asyncService.longRunningJob1();
        logger.info("job 1 launched in the background");
        return "job 1 launched";
    }

    @GetMapping("job2")
    String launchJob2() throws InterruptedException {
        logger.info("triggering long running job 2");
        asyncService.longRunningJob2();
        logger.info("job 2 launched in the background");
        return "job 2 launched";
    }
}
