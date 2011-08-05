package org.plug.babylon.web.login;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import org.plug.babylon.Role;

/**
 * OpenID filter, exposing authenticated user to JEE security
 * @author Sryl <cyril.lacote@gmail.com>
 */
@WebFilter(
    filterName = "OpenIDFilter",
    urlPatterns = {"/*"},
    initParams = {@WebInitParam(name="defaultRoles",value="TestRole")})
public class OpenIDFilter implements Filter {
    
    private static final boolean debug = false;

    private FilterConfig filterConfig = null;

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        if (debug) {
            log("OpenIDFilter:doFilter()");
        }

        // Retrieving existing Principal in session
        OpenIdPrincipal principal = retrievePrincipalInSession(request);
        
        // Wrap request to expose existing principal to Java Security
        ServletRequest wrappedRequest = new RequestWrapper((HttpServletRequest) request, principal);

        chain.doFilter(wrappedRequest, response);
    }

    protected OpenIdPrincipal retrievePrincipalInSession(ServletRequest request) {
        OpenIdPrincipal oidPrincipal = null;
        if (request instanceof HttpServletRequest) {
            HttpSession session = ((HttpServletRequest) request).getSession(false);
            if (session != null) {
                oidPrincipal = (OpenIdPrincipal) session.getAttribute(SuccessfulAuthenticationServlet.SESSION_PRINCIPAL); 
            }
        }
        return oidPrincipal;
    }
    
    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter 
     */
    @Override
    public void destroy() {        
    }

    /**
     * Init method for this filter 
     */
    @Override
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("OpenIDFilter: Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("OpenIDFilter()");
        }
        StringBuilder sb = new StringBuilder("OpenIDFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
        
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }

    class RequestWrapper extends HttpServletRequestWrapper {
        
        private OpenIdPrincipal principal;
        
        public static final String AUTH_TYPE = "OpenID";
        
        public RequestWrapper(HttpServletRequest request, OpenIdPrincipal principal) {
            super(request);
            this.principal = principal;
        }

        @Override
        public String getAuthType() {
            return AUTH_TYPE;
        }

        @Override
        public String getRemoteUser() {
            return (this.principal != null) ? this.principal.getName() : null;
        }

        @Override
        public Principal getUserPrincipal() {
            return this.principal;
        }

        @Override
        public boolean isUserInRole(String role) {
            // FIXME Default "OpenID" role for test
            if (role.equals(Role.OPENID)) return true;
            return super.isUserInRole(role);
        }
    }
}
