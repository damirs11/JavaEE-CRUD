package rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;
import models.CommissionEntity;
import models.EmployeeEntity;
import service.CommissionServiceLocal;


@SuppressWarnings("Duplicates")
@Path("/commission")
public class CommissionRest implements RestOperations<CommissionEntity>{
    
    @EJB
    CommissionServiceLocal commissionService;
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    @Override
    public Response findAll() {
        try {
            List<CommissionEntity> sampleList = commissionService.findAll();
            
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(sampleList);
                return Response.status(Response.Status.OK).entity(json).build();
            }catch(JsonProcessingException e)
            {
                printStackTrace(e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } catch (NoResultException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    @Override
    public Response findById(@PathParam("id") Integer id) {
        try {
            CommissionEntity sampleList = commissionService.findById(id);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(sampleList);
                return Response.status(Response.Status.OK).entity(json).build();
            }catch(JsonProcessingException e)
            {
                printStackTrace(e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } catch (NoResultException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    
//    @GET
//    @Path("/{startNumber:[0-9][0-9]*}&{pageSize[0-9][0-9]*}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response findForPage(@PathParam("startNumber") Integer startNumber
//            , @PathParam("pageSize") Integer pageSize) {
//        
//        List<CommissionEntity> result;
//        
//        try {
//            result = commissionService.findForPage(startNumber, pageSize);
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
    @Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    @Override
    public Response saveOrUpdate(CommissionEntity commissionEntity) {
    
        commissionService.saveOrUpdate(commissionEntity); //сделать проверку
        
        String result = "Commission saved : " + commissionEntity.toString();
        return Response.status(Response.Status.CREATED).entity(result).build();        
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    @Override
    public Response edit(CommissionEntity commissionEntity){
        
        CommissionEntity _commissionEntity = commissionService.findById(commissionEntity.getId());
        
        if(_commissionEntity == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        _commissionEntity = commissionEntity;
        
        commissionService.edit(_commissionEntity);
        
        String result = "Commission edit : " + _commissionEntity.toString();
        return  Response.status(Response.Status.OK).entity(result).build();
    }
    
    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    @Override
    public Response delete(@PathParam("id") Integer id){
        CommissionEntity commissionEntity = commissionService.findById(id);
        if(commissionEntity == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        commissionService.delete(commissionEntity);
        return Response.noContent().build();
    }
    
    @GET
    @Path("/mine/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response myCommissions(@PathParam("id") Integer id){
        try {
            List sampleList = commissionService.myCommissions(id);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(sampleList);
                return Response.status(Response.Status.OK).entity(json).build();
            }catch(JsonProcessingException e)
            {
                printStackTrace(e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } catch (NoResultException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/forMe/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    public Response commissionsForMe(@PathParam("id") Integer id){
        try {
            List sampleList = commissionService.commissionsForMe(id);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                String json = objectMapper.writeValueAsString(sampleList);
                return Response.status(Response.Status.OK).entity(json).build();
            }catch(JsonProcessingException e)
            {
                printStackTrace(e);
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } catch (NoResultException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
