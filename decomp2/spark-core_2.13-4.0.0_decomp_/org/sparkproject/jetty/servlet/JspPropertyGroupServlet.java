package org.sparkproject.jetty.servlet;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.util.URIUtil;
import org.sparkproject.jetty.util.resource.Resource;

public class JspPropertyGroupServlet extends GenericServlet {
   private static final long serialVersionUID = 3681783214726776945L;
   public static final String NAME = "__org.eclipse.jetty.servlet.JspPropertyGroupServlet__";
   private final ServletHandler _servletHandler;
   private final ContextHandler _contextHandler;
   private ServletHolder _dftServlet;
   private ServletHolder _jspServlet;
   private boolean _starJspMapped;

   public JspPropertyGroupServlet(ContextHandler context, ServletHandler servletHandler) {
      this._contextHandler = context;
      this._servletHandler = servletHandler;
   }

   public void init() throws ServletException {
      String jspName = "jsp";
      ServletMapping servletMapping = this._servletHandler.getServletMapping("*.jsp");
      if (servletMapping != null) {
         this._starJspMapped = true;
         ServletMapping[] mappings = this._servletHandler.getServletMappings();

         for(ServletMapping m : mappings) {
            String[] paths = m.getPathSpecs();
            if (paths != null) {
               for(String path : paths) {
                  if ("*.jsp".equals(path) && !"__org.eclipse.jetty.servlet.JspPropertyGroupServlet__".equals(m.getServletName())) {
                     servletMapping = m;
                  }
               }
            }
         }

         jspName = servletMapping.getServletName();
      }

      this._jspServlet = this._servletHandler.getServlet(jspName);
      String defaultName = "default";
      ServletMapping defaultMapping = this._servletHandler.getServletMapping("/");
      if (defaultMapping != null) {
         defaultName = defaultMapping.getServletName();
      }

      this._dftServlet = this._servletHandler.getServlet(defaultName);
   }

   public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
      HttpServletRequest request = null;
      if (req instanceof HttpServletRequest) {
         request = (HttpServletRequest)req;
         Object var4 = null;
         Object var5 = null;
         String var9;
         String var10;
         if (request.getAttribute("jakarta.servlet.include.request_uri") != null) {
            var9 = (String)request.getAttribute("jakarta.servlet.include.servlet_path");
            var10 = (String)request.getAttribute("jakarta.servlet.include.path_info");
            if (var9 == null) {
               var9 = request.getServletPath();
               var10 = request.getPathInfo();
            }
         } else {
            var9 = request.getServletPath();
            var10 = request.getPathInfo();
         }

         String pathInContext = URIUtil.addPaths(var9, var10);
         if (pathInContext.endsWith("/")) {
            this._dftServlet.getServlet().service(req, res);
         } else if (this._starJspMapped && pathInContext.toLowerCase(Locale.ENGLISH).endsWith(".jsp")) {
            this._jspServlet.getServlet().service(req, res);
         } else {
            Resource resource = this._contextHandler.getResource(pathInContext);
            if (resource != null && resource.isDirectory()) {
               this._dftServlet.getServlet().service(req, res);
            } else {
               this._jspServlet.getServlet().service(req, res);
            }
         }

      } else {
         throw new ServletException("Request not HttpServletRequest");
      }
   }
}
