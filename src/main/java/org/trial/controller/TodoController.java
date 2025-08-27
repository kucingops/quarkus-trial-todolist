package org.trial.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.trial.dto.TodoResponse;
import org.trial.dto.TodoSaveRequest;
import org.trial.service.TodoService;

import java.util.List;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TodoController {

    @Inject
    TodoService service;

    // 1) save -> insert/update
    @POST
    @Path("/save")
    public Response save(TodoSaveRequest req) {
        return Response.ok(service.save(req)).build();
    }

    // 2) delete -> soft delete
    @DELETE
    @Path("/{id}/{actor}")
    public Response delete(@PathParam("id") Long id,
                           @PathParam("actor") String actor) {
        service.softDelete(id, actor);
        return Response.noContent().build();
    }

    // 3) get -> list non-deleted
    @GET
    public List<TodoResponse> getAll() {
        return service.listActive();
    }
}
