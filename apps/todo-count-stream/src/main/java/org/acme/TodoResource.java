package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;

import io.smallrye.mutiny.Multi;

@Path("/todo-count")
public class TodoResource {

    @Channel("todo-count")
    Multi<String> todoCounts;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS) 
    public Multi<String> stream() {
        return todoCounts; 
    }
}