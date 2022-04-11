package org.acme;

import io.smallrye.health.checks.UrlHealthCheck;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.HttpMethod;

@ApplicationScoped
public class CustomHealthCheck {

    @ConfigProperty(name = "quarkus.rest-client.\"org.acme.ActivityService\".url")
    String externalURL;

    @Readiness 
    HealthCheck checkURL() {
        return new UrlHealthCheck(externalURL) 
                .name("external-url-check").requestMethod(HttpMethod.GET).statusCode(200);
    }

}