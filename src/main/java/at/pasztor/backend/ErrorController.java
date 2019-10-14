package at.pasztor.backend;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@ControllerAdvice
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @RequestMapping(
            value = "/error",
            produces = "application/json"
    )
    public ResponseEntity<Void> errorJson(HttpServletRequest request) throws ApiException {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            switch (statusCode) {
                case 404:
                    throw new ApiException(
                            HttpStatus.NOT_FOUND,
                            ApiException.ErrorCode.NOT_FOUND,
                            "The requested API endpoint was not found."
                    );

            }
            return new ResponseEntity<>(HttpStatus.valueOf(statusCode));
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { ApiException.class })
    public ResponseEntity<ApiException> onException(ApiException apiException) {
        return new ResponseEntity<>(
            apiException,
            apiException.status
        );
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
