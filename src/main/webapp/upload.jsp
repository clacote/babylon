<%-- 
    Document   : upload
    Created on : Aug 1, 2011, 11:48:34 PM
    Author     : Sryl <cyril.lacote@gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="uploadify/uploadify.css" type="text/css" rel="stylesheet" />
        <script type="text/javascript" src="uploadify/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="uploadify/swfobject.js"></script>
        <script type="text/javascript" src="uploadify/jquery.uploadify.v2.1.4.min.js"></script>
        <script type="text/javascript">
        $(document).ready(function() {
          $('#file_upload').uploadify({
            'uploader'  : 'uploadify/uploadify.swf',
        //  'script'    : 'uploadify/uploadify.php',
            'script'    : 'rest/file/upload',
            'cancelImg' : 'uploadify/cancel.png',
            'folder'    : 'uploads',
            'auto'      : false
          });
        });
        </script>
        <title>Test upload</title>
    </head>
    <body>
        <h1>Upload?</h1>
        
        <p>
        with uplodify :<br/>
            <input id="file_upload" name="file_upload" type="file" />
            <a href="javascript:$('#file_upload').uploadifyUpload();">Upload!</a>
        </p>
        
        <p>
        without uplodify :
        <form method="post" enctype="multipart/form-data" action="rest/file/upload">
            <input type="file" size="60" id="file" name="file" />
            <input type="submit" value="Upload!"/>
        </form>
        </p>
    </body>
</html>
