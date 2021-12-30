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

    @GetMapping("job")
    String launchJob() throws InterruptedException {
        logger.info("triggering long running job");
        asyncService.longRunningJob();
        logger.info("job launched in the background");
        return "job launched";
    }
}
