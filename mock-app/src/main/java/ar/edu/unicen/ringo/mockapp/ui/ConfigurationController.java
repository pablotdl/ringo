package ar.edu.unicen.ringo.mockapp.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/config")
public class ConfigurationController {

    private static final Logger logger = LoggerFactory
            .getLogger(ConfigurationController.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public String configure(@RequestParam("id") String appId) {
        return "Configuration updated successfully";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public String get() {
        return "Application id: ";
    }
}
