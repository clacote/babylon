package org.plug.babylon.web.login;

import java.io.IOException;
import java.rmi.ServerException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.brickred.socialauth.Permission;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;

/**
 * Login Servlet for OpenID authentication
 * @author Sryl <cyril.lacote@gmail.com>
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    /** Parameter name giving chosen OAuth provider */
    public static final String PARAM_PROVIDER = "provider";
    
    /** Session attribute name storing authentication manager */
    static final String SESSION_AUTHMANAGER = "authManager";
    
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//Create an instance of SocialAuthConfgi object
        SocialAuthConfig config = SocialAuthConfig.getDefault();

        String provider = request.getParameter(PARAM_PROVIDER);
        if (StringUtils.isBlank(provider)) {
            throw new ServletException("Parameter \"" + PARAM_PROVIDER + "\" must be provided");
        }
        
        try {
            //load configuration. By default load the configuration from oauth_consumer.properties. 
            //You can also pass input stream, properties object or properties file name.
            config.load();

            //Create an instance of SocialAuthManager and set config
            SocialAuthManager manager = new SocialAuthManager();
            manager.setSocialAuthConfig(config);

            // FIXME URL of OUR application which will be called after authentication
            final String successUrl = "http://192.168.1.114:8080/org.plug_babylon_war_0.0.1-SNAPSHOT" + SuccessfulAuthenticationServlet.URL;

            // FIXME AUTHENTICATE_ONLY as Workaround for bug http://code.google.com/p/socialauth/issues/detail?id=81
            final String url = manager.getAuthenticationUrl( provider, successUrl, Permission.AUTHENTICATE_ONLY);

            // Store manager in session
            request.getSession().setAttribute(SESSION_AUTHMANAGER, manager);
            
            response.sendRedirect(response.encodeRedirectURL(url));
        } catch (Exception ex) {
            throw new ServerException("Unable to initialize OAuth manager", ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Login servlet for OpenID authentication";
    }// </editor-fold>
}
