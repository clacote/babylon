package org.plug.babylon.web;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

@Path("/file")
public class FileHandler {

    private static final Logger LOG = Logger.getLogger(FileHandler.class.getName()); 
    
    @POST @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String uploadFile(
            @DefaultValue("true") @FormDataParam("enabled") boolean enabled,
            @FormDataParam("file") InputStream file,
            @FormDataParam("file") FormDataContentDisposition fileInfo) {

        String response = "Something went wrong...";
        if (fileInfo != null) {
            
            LOG.log(Level.INFO, "Received file: {0} as {1}", new Object[]{fileInfo.getFileName(), file});
            LOG.log(Level.INFO, "File info : {0}", ReflectionToStringBuilder.toString(fileInfo)); 
            // your code here to copy file to destFile
            
            response = "Thank you for uploading " + fileInfo.getFileName();
        } else {
            LOG.warning("Received null file!");
        }
        return response;
    }
}
