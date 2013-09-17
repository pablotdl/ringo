package ar.edu.unicen.ringo.mockapp.ui;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import ar.edu.unicen.ringo.mockapp.ConfigHolder;

@Controller
@RequestMapping("/config")
public class ConfigurationController {

    private static final Logger logger = LoggerFactory
            .getLogger(ConfigurationController.class);

    @Autowired
    private ConfigHolder config;

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public String configure(@RequestParam("id") String appId) {
        logger.info("Replacing previous app id of {} with new app id {}",
                this.config.getAppId(), appId);
        this.config.setAppId(appId);
        logger.info("Configuration successfully updated");
        return "Configuration updated successfully";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public String get() {
        String appId = this.config.getAppId();
        if (appId == null) {
            return "Application not configured yet";
        }
        return "Application id: " + appId;
    }
}
