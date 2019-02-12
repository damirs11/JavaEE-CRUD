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
import models.SubdivisionEntity;
import service.SubdivisionServiceLocal;

@SuppressWarnings("Duplicates")
@Path("/subdivision")
public class SubdivisionRest implements RestOperations<SubdivisionEntity>{
    
    @EJB
    SubdivisionServiceLocal subdivisionService;
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    @Override
    public Response findAll() {
        try {
            List<SubdivisionEntity> sampleList = subdivisionService.findAll();
            
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
            SubdivisionEntity sampleList = subdivisionService.findById(id);

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
//    @Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
//    public Response findForPage(@PathParam("startNumber") Integer startNumber
//            , @PathParam("pageSize") Integer pageSize) {
//        
//        List<SubdivisionEntity> result;
//        
//        try {
//            result = subdivisionService.findForPage(startNumber, pageSize);
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
    public Response saveOrUpdate(SubdivisionEntity subdivisionEntity) {
    
        subdivisionService.saveOrUpdate(subdivisionEntity); //сделать проверку
        
        String result = "Subdivision saved : " + subdivisionEntity.toString();
        return Response.status(Response.Status.CREATED).entity(result).build();        
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
    @Override
    public Response edit(SubdivisionEntity subdivisionEntity){
        
        SubdivisionEntity _subdivisionEntity = subdivisionService.findById(subdivisionEntity.getId());
        
        if(_subdivisionEntity == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        _subdivisionEntity = subdivisionEntity;
        
        subdivisionService.edit(_subdivisionEntity);
        
        String result = "Subdivision edit : " + _subdivisionEntity.toString();
        return  Response.status(Response.Status.OK).entity(result).build();
    }
    
    @DELETE
    @Path("/{id:[0-9][0-9]*}")
    @Override
    public Response delete(@PathParam("id") Integer id){
        SubdivisionEntity subdivisionEntity = subdivisionService.findById(id);
        if(subdivisionEntity == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        subdivisionService.delete(subdivisionEntity);
        return Response.noContent().build();
    }
}
