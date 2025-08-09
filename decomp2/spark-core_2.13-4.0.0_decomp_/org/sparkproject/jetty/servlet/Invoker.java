package org.sparkproject.jetty.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.pathmap.MatchedResource;
import org.sparkproject.jetty.server.Handler;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.server.handler.HandlerWrapper;
import org.sparkproject.jetty.util.ArrayUtil;
import org.sparkproject.jetty.util.URIUtil;
import org.sparkproject.jetty.util.thread.AutoLock;

public class Invoker extends HttpServlet {
   private static final Logger LOG = LoggerFactory.getLogger(Invoker.class);
   private ContextHandler _contextHandler;
   private ServletHandler _servletHandler;
   private MatchedResource _invokerEntry;
   private Map _parameters;
   private boolean _nonContextServlets;
   private boolean _verbose;

   public void init() {
      ServletContext config = this.getServletContext();
      this._contextHandler = ((ContextHandler.Context)config).getContextHandler();

      Handler handler;
      for(handler = this._contextHandler.getHandler(); handler != null && !(handler instanceof ServletHandler) && handler instanceof HandlerWrapper; handler = ((HandlerWrapper)handler).getHandler()) {
      }

      this._servletHandler = (ServletHandler)handler;
      Enumeration<String> e = this.getInitParameterNames();

      while(e.hasMoreElements()) {
         String param = (String)e.nextElement();
         String value = this.getInitParameter(param);
         String lvalue = value.toLowerCase(Locale.ENGLISH);
         if ("nonContextServlets".equals(param)) {
            this._nonContextServlets = value.length() > 0 && lvalue.startsWith("t");
         }

         if (!"verbose".equals(param)) {
            if (this._parameters == null) {
               this._parameters = new HashMap();
            }

            this._parameters.put(param, value);
         } else {
            this._verbose = value.length() > 0 && lvalue.startsWith("t");
         }
      }

   }

   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      boolean included = false;
      String servletPath = (String)request.getAttribute("jakarta.servlet.include.servlet_path");
      if (servletPath == null) {
         servletPath = request.getServletPath();
      } else {
         included = true;
      }

      String pathInfo = (String)request.getAttribute("jakarta.servlet.include.path_info");
      if (pathInfo == null) {
         pathInfo = request.getPathInfo();
      }

      if (pathInfo != null && pathInfo.length() > 1) {
         int i0 = pathInfo.charAt(0) == '/' ? 1 : 0;
         int i1 = pathInfo.indexOf(47, i0);
         String servlet = i1 < 0 ? pathInfo.substring(i0) : pathInfo.substring(i0, i1);
         ServletHolder[] holders = this._servletHandler.getServlets();
         ServletHolder holder = this.getHolder(holders, servlet);
         if (holder != null) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Adding servlet mapping for named servlet: {}:{}/*", servlet, URIUtil.addPaths(servletPath, servlet));
            }

            ServletMapping mapping = new ServletMapping();
            mapping.setServletName(servlet);
            String var10001 = URIUtil.addPaths(servletPath, servlet);
            mapping.setPathSpec(var10001 + "/*");
            this._servletHandler.setServletMappings((ServletMapping[])ArrayUtil.addToArray(this._servletHandler.getServletMappings(), mapping, ServletMapping.class));
         } else {
            if (servlet.endsWith(".class")) {
               servlet = servlet.substring(0, servlet.length() - 6);
            }

            if (servlet == null || servlet.length() == 0) {
               response.sendError(404);
               return;
            }

            try (AutoLock l = this._servletHandler.lock()) {
               this._invokerEntry = this._servletHandler.getMatchedServlet(servletPath);
               String path = URIUtil.addPaths(servletPath, servlet);
               MatchedResource<ServletHandler.MappedServlet> entry = this._servletHandler.getMatchedServlet(path);
               if (entry != null && !((ServletHandler.MappedServlet)entry.getResource()).equals(this._invokerEntry.getResource())) {
                  holder = ((ServletHandler.MappedServlet)entry.getResource()).getServletHolder();
               } else {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("Making new servlet={}  with path={}/*", servlet, path);
                  }

                  holder = this._servletHandler.addServletWithMapping(servlet, path + "/*");
                  if (this._parameters != null) {
                     holder.setInitParameters(this._parameters);
                  }

                  try {
                     holder.start();
                  } catch (Exception e) {
                     LOG.debug("Unable to start {}", holder, e);
                     throw new UnavailableException(e.toString());
                  }

                  if (!this._nonContextServlets) {
                     Object s = holder.getServlet();
                     if (this._contextHandler.getClassLoader() != s.getClass().getClassLoader()) {
                        try {
                           holder.stop();
                        } catch (Exception e) {
                           LOG.trace("IGNORED", e);
                        }

                        LOG.warn("Dynamic servlet {} not loaded from context {}", s, request.getContextPath());
                        throw new UnavailableException("Not in context");
                     }
                  }

                  if (this._verbose && LOG.isDebugEnabled()) {
                     LOG.debug("Dynamic load '{}' at {}", servlet, path);
                  }
               }
            }
         }

         if (holder != null) {
            Request baseRequest = Request.getBaseRequest(request);
            holder.prepare(baseRequest, request, response);
            holder.handle(baseRequest, new InvokedRequest(request, included, servlet, servletPath, pathInfo), response);
         } else {
            LOG.info("Can't find holder for servlet: {}", servlet);
            response.sendError(404);
         }

      } else {
         response.sendError(404);
      }
   }

   private ServletHolder getHolder(ServletHolder[] holders, String servlet) {
      if (holders == null) {
         return null;
      } else {
         ServletHolder holder = null;

         for(int i = 0; holder == null && i < holders.length; ++i) {
            if (holders[i].getName().equals(servlet)) {
               holder = holders[i];
            }
         }

         return holder;
      }
   }

   class InvokedRequest extends HttpServletRequestWrapper {
      String _servletPath;
      String _pathInfo;
      boolean _included;

      InvokedRequest(HttpServletRequest request, boolean included, String name, String servletPath, String pathInfo) {
         super(request);
         this._included = included;
         this._servletPath = URIUtil.addPaths(servletPath, name);
         this._pathInfo = pathInfo.substring(name.length() + 1);
         if (this._pathInfo.length() == 0) {
            this._pathInfo = null;
         }

      }

      public String getServletPath() {
         return this._included ? super.getServletPath() : this._servletPath;
      }

      public String getPathInfo() {
         return this._included ? super.getPathInfo() : this._pathInfo;
      }

      public Object getAttribute(String name) {
         if (this._included) {
            if (name.equals("jakarta.servlet.include.request_uri")) {
               return URIUtil.addPaths(URIUtil.addPaths(this.getContextPath(), this._servletPath), this._pathInfo);
            }

            if (name.equals("jakarta.servlet.include.path_info")) {
               return this._pathInfo;
            }

            if (name.equals("jakarta.servlet.include.servlet_path")) {
               return this._servletPath;
            }
         }

         return super.getAttribute(name);
      }
   }
}
