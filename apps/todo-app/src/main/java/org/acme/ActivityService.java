package org.acme;

import org.eclipse.microprofile.faulttolerance.ExecutionContext;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.FallbackHandler;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@RegisterRestClient
@Path("/api/activity")
@Singleton
public interface ActivityService {

    @GET
    Todo getActivityByType(@QueryParam("type") String type);

    @GET
    @Retry(maxRetries = 3, delay = 2000)
    @Fallback(TodoFallback.class)
    Todo getActivity();

    public static class TodoFallback implements FallbackHandler<Todo> {

        private static final Todo EMPTY_TODO = new Todo();

        @Override
        public Todo handle(ExecutionContext context) {
            return EMPTY_TODO;
        }

    }

}