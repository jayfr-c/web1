package project.start5;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorProperties.IncludeStacktrace;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.boot.web.servlet.server.AbstractServletWebServerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
 
@Controller 
public class ErrorMap implements ErrorController {

	public ErrorMap() {}
	
	private static final String PATH = "/error";

	@GetMapping("/error") 
	public String handleError(HttpServletRequest request) {
	 	Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

	 	if (status != null) {
	 		Integer statusCode = Integer.valueOf(status.toString());

	 		if(statusCode == HttpStatus.NOT_FOUND.value()) {
	 			return "error-404";
	 		}
	 		else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	 			return "error-500";
	 		}
	 	}
	 	return "error";
	}

	@Override
	public String getErrorPath() {
	 	return null;
	}

}