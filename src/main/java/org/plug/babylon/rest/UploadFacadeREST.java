package org.plug.babylon.web;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.InputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

@Path("/file")
public class FileHandler {

    private static final Log LOG = LogFactory.getLog(FileHandler.class); 
    
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
            // your code here to copy file to destFile
            
            response = "Thank you for uploading " + fileInfo.getFileName();
        } else {
            LOG.warn("Received null file!");
        }
        return response;
    }
}
