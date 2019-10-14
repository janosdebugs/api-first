package at.pasztor.backend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping(
            value = "/",
            produces = "application/json"
    )
    public TestResponse index() {
        return new TestResponse("Hello world!");
    }
}
