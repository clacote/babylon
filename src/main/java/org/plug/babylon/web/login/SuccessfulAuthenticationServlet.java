package org.plug.babylon.web.login;

import com.sun.xml.internal.ws.client.SenderException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;

/**
 * Servlet handling OAuth redirection after successful authentication
 * @author Sryl <cyril.lacote@gmail.com>
 */
@WebServlet(name = "SuccessfulAuthenticationServlet", urlPatterns = {SuccessfulAuthenticationServlet.URL})
public class SuccessfulAuthenticationServlet extends HttpServlet {

    public static final String URL = "/login/success";

    public static final String SESSION_PRINCIPAL = "openIdPrincipal";

    private static final Log LOG = LogFactory.getLog(SuccessfulAuthenticationServlet.class);
    
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get the social auth manager from session
        SocialAuthManager manager = (SocialAuthManager) request.getSession().getAttribute(LoginServlet.SESSION_AUTHMANAGER);

        try {
            // call connect method of manager which returns the provider object. 
            // Pass request parameter map while calling connect method. 
            AuthProvider provider = manager.connect(SocialAuthUtil.getRequestParametersMap(request));

            // get profile
            Profile p = provider.getUserProfile();

            LOG.info("Successful login for " + p);
            
            // Store authenticated user as principal in session
            request.getSession().setAttribute(SESSION_PRINCIPAL, new OpenIdPrincipal(p));
            
            // Redirect to home page
            response.sendRedirect(response.encodeRedirectURL("/")+getServletContext().getContextPath());
        } catch (Exception ex) {
            throw new ServletException("Unable to retrieve user profile", ex);
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
        return "Servlet managing successful OAuth redirection";
    }// </editor-fold>
}
