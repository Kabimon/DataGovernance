package com.webank.wedatasphere.warehouse.restful;

import com.webank.wedatasphere.linkis.server.Message;
import com.webank.wedatasphere.warehouse.cqe.*;
import com.webank.wedatasphere.warehouse.exception.DwException;
import com.webank.wedatasphere.warehouse.service.DwLayerService;
import com.webank.wedatasphere.warehouse.service.DwSubjectDomainService;
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
public class DwSubjectDomainRestfulApi {

    private final DwSubjectDomainService dwSubjectDomainService;

    @Autowired
    public DwSubjectDomainRestfulApi(DwSubjectDomainService dwSubjectDomainService) {
        this.dwSubjectDomainService = dwSubjectDomainService;
    }

    // query paged subjectdomains
    @GET
    @Path("/subjectdomains")
    public Response queryPagedCustomLayers(
            @Context HttpServletRequest request,
            @QueryParam("page") Integer page,
            @QueryParam("size") Integer size,
            @QueryParam("name") String name
    )throws DwException {
        final DwSubjectDomainQueryCommand command = new DwSubjectDomainQueryCommand();
        command.setName(name);
        command.setPage(page);
        command.setSize(size);
        Message message = this.dwSubjectDomainService.queryPage(request, command);
        return Message.messageToResponse(message);
    }

    // create subjectdomain
    @POST
    @Path("/subjectdomains")
    public Response createDwCustomLayer(@Context HttpServletRequest request, DwSubjectDomainCreateCommand command) throws DwException {
        Message message = this.dwSubjectDomainService.create(request, command);
        return Message.messageToResponse(message);
    }

    // get subjectdomain by id
    @GET
    @Path("/subjectdomains/{id}")
    public Response getLayerById(
            @Context HttpServletRequest request,
            @PathParam("id") Long id
    ) throws DwException {
        Message message = this.dwSubjectDomainService.getById(request, id);
        return Message.messageToResponse(message);
    }

    // delete subjectdomain
    @DELETE
    @Path("/subjectdomains/{id}")
    public Response deleteById(
            @Context HttpServletRequest request,
            @PathParam("id") Long id
    ) throws DwException {
        Message message = this.dwSubjectDomainService.deleteById(request, id);
        return Message.messageToResponse(message);
    }

    // update subjectdomain
    @PUT
    @Path("/subjectdomains/{id}")
    public Response update(
            @Context HttpServletRequest request,
            @PathParam("id") Long id,
            DwSubjectDomainUpdateCommand command
    ) throws DwException {
        command.setId(id);
        Message message = this.dwSubjectDomainService.update(request, command);
        return Message.messageToResponse(message);
    }

    // enable subject domain
    @PUT
    @Path("/subjectdomains/{id}/enable")
    public Response enable(
            @Context HttpServletRequest request,
            @PathParam("id") Long id
    ) throws DwException {
        Message message = this.dwSubjectDomainService.enable(request, id);
        return Message.messageToResponse(message);
    }

    // disable subject domain
    @PUT
    @Path("/subjectdomains/{id}/disable")
    public Response disable(
            @Context HttpServletRequest request,
            @PathParam("id") Long id
    ) throws DwException {
        Message message = this.dwSubjectDomainService.disable(request, id);
        return Message.messageToResponse(message);
    }
}
