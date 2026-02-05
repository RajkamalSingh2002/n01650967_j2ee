package com.example.employeerestapi.resources;

import com.example.employeerestapi.models.Employee;
import com.example.employeerestapi.services.EmployeeServices;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Collection;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    private EmployeeServices empService;

    @GET
    @RolesAllowed({"user", "admin"})
    public Collection<Employee> getAllEmployees() {
        return empService.getAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"user", "admin"})
    public Response getEmployee(@PathParam("id") int id) {
        Employee emp = empService.getById(id);
        if (emp == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(emp).build();
    }

    @POST
    @RolesAllowed("admin")
    public Response addEmployee(Employee _emp) {
        Employee emp = empService.create(_emp);
        return Response.status(Response.Status.CREATED).entity(emp).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response updateEmployee(@PathParam("id") int id, Employee _emp) {
        Employee updated = empService.update(id, _emp);
        if (updated == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response deleteEmployee(@PathParam("id") int id) {
        boolean deleted = empService.delete(id);
        if (!deleted)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.noContent().build();
    }
}