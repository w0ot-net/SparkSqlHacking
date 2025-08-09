package org.glassfish.jersey.servlet;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriBuilderException;
import jakarta.ws.rs.core.Response.Status;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.internal.util.ExtendedLogger;
import org.glassfish.jersey.internal.util.collection.Value;
import org.glassfish.jersey.server.ApplicationHandler;
import org.glassfish.jersey.server.ContainerException;
import org.glassfish.jersey.server.ContainerResponse;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.internal.ContainerUtils;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;
import org.glassfish.jersey.servlet.internal.LocalizationMessages;
import org.glassfish.jersey.servlet.internal.ResponseWriter;
import org.glassfish.jersey.servlet.spi.FilterUrlMappingsProvider;
import org.glassfish.jersey.uri.UriComponent;
import org.glassfish.jersey.uri.UriComponent.Type;

public class ServletContainer extends HttpServlet implements Filter, Container {
   private static final long serialVersionUID = 3932047066686065219L;
   private static final ExtendedLogger LOGGER;
   private transient FilterConfig filterConfig;
   private transient WebComponent webComponent;
   private transient ResourceConfig resourceConfig;
   private transient Pattern staticContentPattern;
   private transient String filterContextPath;
   private transient List filterUrlMappings;
   private transient volatile ContainerLifecycleListener containerListener;

   protected void init(WebConfig webConfig) throws ServletException {
      this.webComponent = new WebComponent(webConfig, this.resourceConfig);
      this.containerListener = this.webComponent.appHandler;
      this.containerListener.onStartup(this);
   }

   public ServletContainer() {
   }

   public ServletContainer(ResourceConfig resourceConfig) {
      this.resourceConfig = resourceConfig;
   }

   public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
      if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
         HttpServletRequest request = (HttpServletRequest)req;
         HttpServletResponse response = (HttpServletResponse)res;
         this.service(request, response);
      } else {
         throw new ServletException("non-HTTP request or response");
      }
   }

   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String servletPath = request.getServletPath();
      StringBuffer requestUrl = request.getRequestURL();
      String requestURI = request.getRequestURI();

      UriBuilder absoluteUriBuilder;
      try {
         absoluteUriBuilder = UriBuilder.fromUri(requestUrl.toString());
      } catch (IllegalArgumentException iae) {
         this.setResponseForInvalidUri(response, iae);
         return;
      }

      String encodedBasePath = UriComponent.contextualEncode(request.getContextPath() + servletPath, Type.PATH) + "/";

      URI baseUri;
      URI requestUri;
      try {
         LOGGER.debugLog("ServletContainer.service(...) started");
         baseUri = absoluteUriBuilder.replacePath(encodedBasePath).build(new Object[0]);
         String queryParameters = ContainerUtils.encodeUnsafeCharacters(request.getQueryString());
         if (queryParameters == null) {
            queryParameters = "";
         }

         requestUri = absoluteUriBuilder.replacePath(requestURI).replaceQuery(queryParameters).build(new Object[0]);
      } catch (IllegalArgumentException | UriBuilderException ex) {
         this.setResponseForInvalidUri(response, ex);
         return;
      }

      this.service(baseUri, requestUri, request, response);
   }

   private void setResponseForInvalidUri(HttpServletResponse response, Throwable throwable) throws IOException {
      LOGGER.log(Level.FINER, "Error while processing request.", throwable);
      Response.Status badRequest = Status.BAD_REQUEST;
      if (this.webComponent.configSetStatusOverSendError) {
         response.reset();
         setStatus(response, badRequest.getStatusCode(), badRequest.getReasonPhrase());
      } else {
         response.sendError(badRequest.getStatusCode(), badRequest.getReasonPhrase());
      }

   }

   public static void setStatus(HttpServletResponse response, int statusCode, String reasonPhrase) {
      try {
         response.setStatus(statusCode, reasonPhrase);
      } catch (NoSuchMethodError var4) {
         response.setStatus(statusCode);
      }

   }

   public void destroy() {
      super.destroy();
      ContainerLifecycleListener listener = this.containerListener;
      if (listener != null) {
         listener.onShutdown(this);
      }

   }

   public void init() throws ServletException {
      this.init((WebConfig)(new WebServletConfig(this)));
   }

   public Value service(URI baseUri, URI requestUri, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      return this.webComponent.service(baseUri, requestUri, request, response);
   }

   private ResponseWriter serviceImpl(URI baseUri, URI requestUri, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      return this.webComponent.serviceImpl(baseUri, requestUri, request, response);
   }

   public void init(FilterConfig filterConfig) throws ServletException {
      this.filterConfig = filterConfig;
      this.init((WebConfig)(new WebFilterConfig(filterConfig)));
      String regex = (String)this.getConfiguration().getProperty("jersey.config.servlet.filter.staticContentRegex");
      if (regex != null && !regex.isEmpty()) {
         try {
            this.staticContentPattern = Pattern.compile(regex);
         } catch (PatternSyntaxException ex) {
            throw new ContainerException(LocalizationMessages.INIT_PARAM_REGEX_SYNTAX_INVALID(regex, "jersey.config.servlet.filter.staticContentRegex"), ex);
         }
      }

      this.filterContextPath = filterConfig.getInitParameter("jersey.config.servlet.filter.contextPath");
      if (this.filterContextPath != null) {
         if (this.filterContextPath.isEmpty()) {
            this.filterContextPath = null;
         } else {
            if (!this.filterContextPath.startsWith("/")) {
               this.filterContextPath = '/' + this.filterContextPath;
            }

            if (this.filterContextPath.endsWith("/")) {
               this.filterContextPath = this.filterContextPath.substring(0, this.filterContextPath.length() - 1);
            }
         }
      }

      FilterUrlMappingsProvider filterUrlMappingsProvider = this.getFilterUrlMappingsProvider();
      if (filterUrlMappingsProvider != null) {
         this.filterUrlMappings = filterUrlMappingsProvider.getFilterUrlMappings(filterConfig);
      }

      if (this.filterUrlMappings == null && this.filterContextPath == null) {
         LOGGER.warning(LocalizationMessages.FILTER_CONTEXT_PATH_MISSING());
      }

   }

   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      try {
         this.doFilter((HttpServletRequest)servletRequest, (HttpServletResponse)servletResponse, filterChain);
      } catch (ClassCastException e) {
         throw new ServletException("non-HTTP request or response", e);
      }
   }

   public ServletContext getServletContext() {
      return this.filterConfig != null ? this.filterConfig.getServletContext() : super.getServletContext();
   }

   public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
      if (request.getAttribute("jakarta.servlet.include.request_uri") != null) {
         String includeRequestURI = (String)request.getAttribute("jakarta.servlet.include.request_uri");
         if (!includeRequestURI.equals(request.getRequestURI())) {
            this.doFilter(request, response, chain, includeRequestURI, (String)request.getAttribute("jakarta.servlet.include.servlet_path"), (String)request.getAttribute("jakarta.servlet.include.query_string"));
            return;
         }
      }

      String servletPath = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
      this.doFilter(request, response, chain, request.getRequestURI(), servletPath, request.getQueryString());
   }

   private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain, String requestURI, String servletPath, String queryString) throws IOException, ServletException {
      Pattern p = this.getStaticContentPattern();
      if (p != null && p.matcher(servletPath).matches()) {
         chain.doFilter(request, response);
      } else if (this.filterContextPath != null && !servletPath.startsWith(this.filterContextPath)) {
         throw new ContainerException(LocalizationMessages.SERVLET_PATH_MISMATCH(servletPath, this.filterContextPath));
      } else {
         URI baseUri;
         URI requestUri;
         try {
            LOGGER.debugLog("ServletContainer.doFilter(...) started");
            UriBuilder absoluteUriBuilder = UriBuilder.fromUri(request.getRequestURL().toString());
            String pickedUrlMapping = this.pickUrlMapping(request.getRequestURL().toString(), this.filterUrlMappings);
            String replacingPath = pickedUrlMapping != null ? pickedUrlMapping : (this.filterContextPath != null ? this.filterContextPath : "");
            baseUri = absoluteUriBuilder.replacePath(request.getContextPath()).path(replacingPath).path("/").build(new Object[0]);
            requestUri = absoluteUriBuilder.replacePath(requestURI).replaceQuery(ContainerUtils.encodeUnsafeCharacters(queryString)).build(new Object[0]);
         } catch (IllegalArgumentException iae) {
            this.setResponseForInvalidUri(response, iae);
            return;
         }

         ResponseWriter responseWriter = this.serviceImpl(baseUri, requestUri, request, response);
         if (this.webComponent.forwardOn404 && !response.isCommitted()) {
            boolean hasEntity = false;
            Response.StatusType status = null;
            if (responseWriter.responseContextResolved()) {
               ContainerResponse responseContext = responseWriter.getResponseContext();
               hasEntity = responseContext.hasEntity();
               status = responseContext.getStatusInfo();
            }

            if (!hasEntity && status == Status.NOT_FOUND) {
               response.setStatus(200);
               chain.doFilter(request, response);
            }
         }

      }
   }

   private String pickUrlMapping(String requestUri, List filterUrlMappings) {
      if (filterUrlMappings != null && !filterUrlMappings.isEmpty()) {
         if (filterUrlMappings.size() == 1) {
            return (String)filterUrlMappings.get(0);
         } else {
            for(String pattern : filterUrlMappings) {
               if (requestUri.contains(pattern)) {
                  return pattern;
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   private FilterUrlMappingsProvider getFilterUrlMappingsProvider() {
      FilterUrlMappingsProvider filterUrlMappingsProvider = null;
      Iterator<FilterUrlMappingsProvider> providers = Providers.getAllProviders(this.getApplicationHandler().getInjectionManager(), FilterUrlMappingsProvider.class).iterator();
      if (providers.hasNext()) {
         filterUrlMappingsProvider = (FilterUrlMappingsProvider)providers.next();
      }

      return filterUrlMappingsProvider;
   }

   protected Pattern getStaticContentPattern() {
      return this.staticContentPattern;
   }

   public ResourceConfig getConfiguration() {
      return this.webComponent.appHandler.getConfiguration();
   }

   public void reload() {
      this.reload(new ResourceConfig(this.getConfiguration()));
   }

   public void reload(ResourceConfig configuration) {
      try {
         this.containerListener.onShutdown(this);
         this.webComponent = new WebComponent(this.webComponent.webConfig, configuration);
         this.containerListener = this.webComponent.appHandler;
         this.containerListener.onReload(this);
         this.containerListener.onStartup(this);
      } catch (ServletException ex) {
         LOGGER.log(Level.SEVERE, "Reload failed", ex);
      }

   }

   public ApplicationHandler getApplicationHandler() {
      return this.webComponent.appHandler;
   }

   public WebComponent getWebComponent() {
      return this.webComponent;
   }

   static {
      LOGGER = new ExtendedLogger(Logger.getLogger(ServletContainer.class.getName()), Level.FINEST);
   }
}
