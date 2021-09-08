package com.webank.wedatasphere.warehouse.restful;

import com.webank.wedatasphere.linkis.server.Message;
import com.webank.wedatasphere.warehouse.cqe.*;
import com.webank.wedatasphere.warehouse.exception.DwException;
import com.webank.wedatasphere.warehouse.service.DwDecorationService;
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
public class DwDecorationRestfulApi {

    private final DwDecorationService dwDecorationService;

    @Autowired
    public DwDecorationRestfulApi(DwDecorationService dwDecorationService) {
        this.dwDecorationService = dwDecorationService;
    }

    // query paged decorations
    @GET
    @Path("/decorations")
    public Response queryPagedDecorations(
            @Context HttpServletRequest request,
            @QueryParam("page") Integer page,
            @QueryParam("size") Integer size,
            @QueryParam("typeName") String typeName
    )throws DwException {
        final DwDecorationQueryCommand command = new DwDecorationQueryCommand();
        command.setTypeName(typeName);
        command.setPage(page);
        command.setSize(size);
        Message message = this.dwDecorationService.queryPage(request, command);
        return Message.messageToResponse(message);
    }

    // create dw decoration word
    @POST
    @Path("/decorations")
    public Response create(@Context HttpServletRequest request, DwDecorationCreateCommand command) throws DwException {
        Message message = this.dwDecorationService.create(request, command);
        return Message.messageToResponse(message);
    }

    // fetch one decoration details by id
    @GET
    @Path("/decorations/{id}")
    public Response getById(
            @Context HttpServletRequest request,
            @PathParam("id") Long id
    ) throws DwException {
        Message message = this.dwDecorationService.getById(request, id);
        return Message.messageToResponse(message);
    }

    // remove decoration logic
    @DELETE
    @Path("/decorations/{id}")
    public Response deleteById(
            @Context HttpServletRequest request,
            @PathParam("id") Long id
    ) throws DwException {
        Message message = this.dwDecorationService.deleteById(request, id);
        return Message.messageToResponse(message);
    }

    // update
    @PUT
    @Path("/decorations/{id}")
    public Response update(
            @Context HttpServletRequest request,
            @PathParam("id") Long id,
            DwDecorationUpdateCommand command
    ) throws DwException {
        command.setId(id);
        Message message = this.dwDecorationService.update(request, command);
        return Message.messageToResponse(message);
    }
}
