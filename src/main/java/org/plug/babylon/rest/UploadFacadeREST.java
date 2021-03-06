package org.plug.babylon.rest;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.plug.babylon.service.ImportException;
import org.plug.babylon.service.ImporterService;

@Path("/file")
@Stateless
public class UploadFacadeREST {

    private static final Log LOG = LogFactory.getLog(UploadFacadeREST.class); 
    
    @EJB ImporterService importerService;
    
    @POST @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String uploadFile(
            @DefaultValue("true") @FormDataParam("enabled") boolean enabled,
            @FormDataParam("file") InputStream file,
            @FormDataParam("file") FormDataContentDisposition fileInfo) {

        String response = "Something went wrong...";
        if (fileInfo != null) {
            
            LOG.info("Received file: "+fileInfo.getFileName()+" as " + file);
            LOG.info("File info : " + ReflectionToStringBuilder.toString(fileInfo)); 

            try {
                importerService.importFile( fileInfo.getFileName(), file);
            } catch (ImportException ex) {
                LOG.warn(ex.getLocalizedMessage(), ex);
                // FIXME 406 NOT ACCEPTABLE might be not the right response code to reply (supposed to deal with "accept" header.
                throw new WebApplicationException(ex, Response.Status.NOT_ACCEPTABLE);
            }
            
            response = "Thank you for uploading " + fileInfo.getFileName();
        } else {
            LOG.warn("Received null file!");
        }
        return response;
    }
}
