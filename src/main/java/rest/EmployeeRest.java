package rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import models.EmployeeEntity;
import models.OrganizationEntity;
import service.EmployeeServiceLocal;


@Path("/employee")
public class EmployeeRest implements RestOperations<EmployeeEntity>{
    
    @EJB
    EmployeeServiceLocal employeeService;
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response findAll() {
        try {
            List<EmployeeEntity> sampleList = employeeService.findAll();
            
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(sampleList);
                return Response.status(Response.Status.OK).entity(json).build();
            }catch(JsonProcessingException e)
            {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } catch (NoResultException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response findById(@PathParam("id") Integer id) {
        EmployeeEntity result;
        
        try {
            result = employeeService.findById(id);
        } catch (NoResultException e) {
            result = null;
        }

        if(result == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.ok(result).build();
    }
    
//    @GET
//    @Path("/{startNumber:[0-9][0-9]*}&{pageSize[0-9][0-9]*}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response findForPage(@PathParam("startNumber") Integer startNumber
//            , @PathParam("pageSize") Integer pageSize) {
//        
//        List<EmployeeEntity> result;
//        
//        try {
//            result = employeeService.findForPage(startNumber, pageSize);
//        } catch (NoResultException e) {
//            result = null;
//        }
//
//        if(result == null){
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }
//        
//        return Response.ok(result).build();
//    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response saveOrUpdate(EmployeeEntity employeeEntity) {
    
        employeeService.saveOrUpdate(employeeEntity); //сделать проверку
        
        String result = "Employee saved : " + employeeEntity.toString();
        return Response.status(Response.Status.CREATED).entity(result).build();        
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response edit(EmployeeEntity employeeEntity){
        
        EmployeeEntity _employeeEntity = employeeService.findById(employeeEntity.getId());
        
        if(_employeeEntity == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        _employeeEntity = employeeEntity;
        
        employeeService.edit(_employeeEntity);
        
        String result = "Employee edit : " + _employeeEntity.toString();
        return  Response.status(Response.Status.OK).entity(result).build();
    }
    
    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    @Override
    public Response delete(@PathParam("id") Integer id){
        EmployeeEntity employeeEntity = employeeService.findById(id);
        if(employeeEntity == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        employeeService.delete(employeeEntity);
        return Response.noContent().build();
    }
}
