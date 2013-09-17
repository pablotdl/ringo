package ar.edu.unicen.ringo.mockapp;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Component;

/**
 * Class that holds the configuration for the application.
 * @author psaavedra
 */
@Component
public class ConfigHolder {

    /**
     * The id of the application.
     */
    private AtomicReference<String> appId = new AtomicReference<String>(null);

    public String getAppId() {
        return appId.get();
    }

    public void setAppId(String appId) {
        this.appId.set(appId);
    }

}
