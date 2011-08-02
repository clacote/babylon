package org.plug.babylon.web;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/file")
public class FileHandler {

    // FIXME Receive null file with upload.jsp#uploadify and HTTP 400 BAD REQUEST with standard form input file.
    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public String uploadFile(
            @DefaultValue("true") @FormDataParam("enabled") boolean enabled,
            @FormDataParam("file") InputStream file,
            @FormDataParam("file") FormDataContentDisposition fileInfo) {

        if (fileInfo != null) {
            // your code here to copy file to destFile
            System.err.println("Received file: " + fileInfo.getFileName() + " as " + file);
        } else {
            System.err.println("Received null file!");
        }
        return "1";
    }
}
