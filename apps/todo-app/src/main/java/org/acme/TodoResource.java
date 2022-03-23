package org.acme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/api")
public class TodoResource {

    private Map<Long, Todo> todos = new HashMap<>();

    @OPTIONS
    public Response opt() {
        return Response.ok().build();
    }

    @GET
    public List<Todo> getAll() {
        return new ArrayList<>(todos.values());
    }

    @GET
    @Path("/{id}")
    public Todo getOne(@PathParam("id") Long id) {
        Todo entity = todos.get(id);
        if (entity == null) {
            throw new WebApplicationException("Todo with id of " + id + " does not exist.", Status.NOT_FOUND);
        }
        return entity;
    }

    @POST
    public Response create(Todo item) {
        todos.put(item.id, item);
        return Response.status(Status.CREATED).entity(item).build();
    }

    @PATCH
    @Path("/{id}")
    public Response update(Todo todo, @PathParam("id") Long id) {
        Todo entity = todos.get(id);
        entity.id = id;
        entity.completed = todo.completed;
        entity.order = todo.order;
        entity.title = todo.title;
        entity.url = todo.url;
        return Response.ok(entity).build();
    }

    @DELETE
    public Response deleteCompleted() {
        todos.entrySet().removeIf(e -> e.getValue().completed);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOne(@PathParam("id") Long id) {
        Todo entity = todos.get(id);
        if (entity == null) {
            throw new WebApplicationException("Todo with id of " + id + " does not exist.", Status.NOT_FOUND);
        }
        todos.remove(id);
        return Response.noContent().build();
    }
}
