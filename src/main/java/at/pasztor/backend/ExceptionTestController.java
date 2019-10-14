package at.pasztor.backend;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionTestController {
    @RequestMapping("/exception")
    public void test() throws ApiException {
        throw new ApiException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ApiException.ErrorCode.TEST,
                "This is a test."
        );
    }
}
