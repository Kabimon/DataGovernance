package com.webank.wedatasphere.warehouse.restful;

import com.webank.wedatasphere.linkis.server.Message;
import com.webank.wedatasphere.warehouse.cqe.*;
import com.webank.wedatasphere.warehouse.exception.DwException;
import com.webank.wedatasphere.warehouse.service.DwStatCycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/governance/warehouse")
public class DwStatCycleRestfulApi {

    private final DwStatCycleService dwStatCycleService;

    @Autowired
    public DwStatCycleRestfulApi(final DwStatCycleService dwStatCycleService) {
        this.dwStatCycleService = dwStatCycleService;
    }

    // query paged stat_cycles
    @GET
    @Path("/statcycles")
    public Response queryPagedDecorations(
            @Context HttpServletRequest request,
            @QueryParam("page") Integer page,
            @QueryParam("size") Integer size,
            @QueryParam("name") String name
    )throws DwException {
        final DwStatCycleQueryCommand command = new DwStatCycleQueryCommand();
        command.setName(name);
        command.setPage(page);
        command.setSize(size);
        Message message = this.dwStatCycleService.queryPage(request, command);
        return Message.messageToResponse(message);
    }

    // create dw stat_cycle
    @POST
    @Path("/statcycles")
    public Response create(@Context HttpServletRequest request, DwStatCycleCreateCommand command) throws DwException {
        Message message = this.dwStatCycleService.create(request, command);
        return Message.messageToResponse(message);
    }

    // fetch one stat_cycle details by id
    @GET
    @Path("/statcycles/{id}")
    public Response getById(
            @Context HttpServletRequest request,
            @PathParam("id") Long id
    ) throws DwException {
        Message message = this.dwStatCycleService.getById(request, id);
        return Message.messageToResponse(message);
    }

    // remove stat_cycle logic
    @DELETE
    @Path("/statcycles/{id}")
    public Response deleteById(
            @Context HttpServletRequest request,
            @PathParam("id") Long id
    ) throws DwException {
        Message message = this.dwStatCycleService.deleteById(request, id);
        return Message.messageToResponse(message);
    }

    // update stat_cycle
    @PUT
    @Path("/statcycles/{id}")
    public Response update(
            @Context HttpServletRequest request,
            @PathParam("id") Long id,
            DwStatCycleUpdateCommand command
    ) throws DwException {
        command.setId(id);
        Message message = this.dwStatCycleService.update(request, command);
        return Message.messageToResponse(message);
    }
}
