package org.glassfish.jersey.servlet;

import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.RuntimeType;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.core.Response.Status;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.net.URI;
import java.security.AccessController;
import java.security.Principal;
import java.security.PrivilegedActionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.glassfish.jersey.innate.io.InputStreamWrapper;
import org.glassfish.jersey.internal.ServiceFinderBinder;
import org.glassfish.jersey.internal.inject.AbstractBinder;
import org.glassfish.jersey.internal.inject.Providers;
import org.glassfish.jersey.internal.inject.ReferencingFactory;
import org.glassfish.jersey.internal.inject.SupplierClassBinding;
import org.glassfish.jersey.internal.inject.SupplierInstanceBinding;
import org.glassfish.jersey.internal.util.ReflectionHelper;
import org.glassfish.jersey.internal.util.collection.Ref;
import org.glassfish.jersey.internal.util.collection.Value;
import org.glassfish.jersey.internal.util.collection.Values;
import org.glassfish.jersey.message.internal.HeaderValueException;
import org.glassfish.jersey.message.internal.MediaTypes;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ApplicationHandler;
import org.glassfish.jersey.server.BackgroundSchedulerLiteral;
import org.glassfish.jersey.server.ContainerRequest;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.internal.LocalizationMessages;
import org.glassfish.jersey.servlet.internal.PersistenceUnitBinder;
import org.glassfish.jersey.servlet.internal.ResponseWriter;
import org.glassfish.jersey.servlet.internal.ServletContainerProviderFactory;
import org.glassfish.jersey.servlet.internal.Utils;
import org.glassfish.jersey.servlet.internal.spi.ExtendedServletContainerProvider;
import org.glassfish.jersey.servlet.internal.spi.RequestContextProvider;
import org.glassfish.jersey.servlet.internal.spi.RequestScopedInitializerProvider;
import org.glassfish.jersey.servlet.internal.spi.ServletContainerProvider;
import org.glassfish.jersey.servlet.spi.AsyncContextDelegate;
import org.glassfish.jersey.servlet.spi.AsyncContextDelegateProvider;
import org.glassfish.jersey.servlet.spi.FilterUrlMappingsProvider;
import org.glassfish.jersey.uri.UriComponent;

public class WebComponent {
   private static final Logger LOGGER = Logger.getLogger(WebComponent.class.getName());
   private static final Type REQUEST_TYPE = (new GenericType() {
   }).getType();
   private static final Type RESPONSE_TYPE = (new GenericType() {
   }).getType();
   private static final AsyncContextDelegate DEFAULT_ASYNC_DELEGATE = new AsyncContextDelegate() {
      public void suspend() throws IllegalStateException {
         throw new UnsupportedOperationException(LocalizationMessages.ASYNC_PROCESSING_NOT_SUPPORTED());
      }

      public void complete() {
      }
   };
   private final RequestScopedInitializerProvider requestScopedInitializer;
   private final boolean requestResponseBindingExternalized;
   private static final RequestScopedInitializerProvider DEFAULT_REQUEST_SCOPE_INITIALIZER_PROVIDER = (context) -> (injectionManager) -> {
         ((Ref)injectionManager.getInstance(REQUEST_TYPE)).set(context.getHttpServletRequest());
         ((Ref)injectionManager.getInstance(RESPONSE_TYPE)).set(context.getHttpServletResponse());
      };
   final ApplicationHandler appHandler;
   final ScheduledExecutorService backgroundTaskScheduler;
   final WebConfig webConfig;
   final boolean forwardOn404;
   final boolean configSetStatusOverSendError;
   private final AsyncContextDelegateProvider asyncExtensionDelegate;
   private final boolean queryParamsAsFormParams;

   private AsyncContextDelegateProvider getAsyncExtensionDelegate() {
      Iterator<AsyncContextDelegateProvider> providers = Providers.getAllProviders(this.appHandler.getInjectionManager(), AsyncContextDelegateProvider.class).iterator();
      return providers.hasNext() ? (AsyncContextDelegateProvider)providers.next() : (request, response) -> DEFAULT_ASYNC_DELEGATE;
   }

   public WebComponent(WebConfig webConfig, ResourceConfig resourceConfig) throws ServletException {
      this.webConfig = webConfig;
      if (resourceConfig == null) {
         resourceConfig = createResourceConfig(webConfig);
      }

      ServletContainerProvider[] allServletContainerProviders = ServletContainerProviderFactory.getAllServletContainerProviders();
      this.configure(resourceConfig, allServletContainerProviders);
      boolean rrbExternalized = false;
      RequestScopedInitializerProvider rsiProvider = null;

      for(ServletContainerProvider servletContainerProvider : allServletContainerProviders) {
         if (servletContainerProvider instanceof ExtendedServletContainerProvider) {
            ExtendedServletContainerProvider extendedProvider = (ExtendedServletContainerProvider)servletContainerProvider;
            if (extendedProvider.bindsServletRequestResponse()) {
               rrbExternalized = true;
            }

            if (rsiProvider == null) {
               rsiProvider = extendedProvider.getRequestScopedInitializerProvider();
            }
         }
      }

      this.requestScopedInitializer = rsiProvider != null ? rsiProvider : DEFAULT_REQUEST_SCOPE_INITIALIZER_PROVIDER;
      this.requestResponseBindingExternalized = rrbExternalized;
      AbstractBinder webComponentBinder = new WebComponentBinder(resourceConfig.getProperties());
      resourceConfig.register(webComponentBinder);
      Object locator = webConfig.getServletContext().getAttribute("jersey.config.servlet.context.serviceLocator");
      this.appHandler = new ApplicationHandler(resourceConfig, webComponentBinder, locator);
      this.asyncExtensionDelegate = this.getAsyncExtensionDelegate();
      this.forwardOn404 = webConfig.getConfigType() == WebConfig.ConfigType.FilterConfig && resourceConfig.isProperty("jersey.config.servlet.filter.forwardOn404");
      this.queryParamsAsFormParams = !resourceConfig.isProperty("jersey.config.servlet.form.queryParams.disabled");
      this.configSetStatusOverSendError = (Boolean)ServerProperties.getValue(resourceConfig.getProperties(), "jersey.config.server.response.setStatusOverSendError", false, Boolean.class);
      this.backgroundTaskScheduler = (ScheduledExecutorService)this.appHandler.getInjectionManager().getInstance(ScheduledExecutorService.class, new Annotation[]{BackgroundSchedulerLiteral.INSTANCE});
   }

   public Value service(URI baseUri, URI requestUri, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
      final ResponseWriter responseWriter = this.serviceImpl(baseUri, requestUri, servletRequest, servletResponse);
      return Values.lazy(new Value() {
         public Integer get() {
            return responseWriter.responseContextResolved() ? responseWriter.getResponseStatus() : -1;
         }
      });
   }

   ResponseWriter serviceImpl(URI baseUri, URI requestUri, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {
      ResponseWriter responseWriter = new ResponseWriter(this.forwardOn404, this.configSetStatusOverSendError, servletResponse, this.asyncExtensionDelegate.createDelegate(servletRequest, servletResponse), this.backgroundTaskScheduler);

      try {
         ContainerRequest requestContext = new ContainerRequest(baseUri, requestUri, servletRequest.getMethod(), getSecurityContext(servletRequest), new ServletPropertiesDelegate(servletRequest), this.appHandler.getConfiguration());
         this.initContainerRequest(requestContext, servletRequest, servletResponse, responseWriter);
         this.appHandler.handle(requestContext);
      } catch (HeaderValueException hve) {
         if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.log(Level.FINE, LocalizationMessages.HEADER_VALUE_READ_FAILED(), hve);
         }

         Response.Status status = Status.BAD_REQUEST;
         if (this.configSetStatusOverSendError) {
            servletResponse.reset();
            ServletContainer.setStatus(servletResponse, status.getStatusCode(), status.getReasonPhrase());
         } else {
            servletResponse.sendError(status.getStatusCode(), status.getReasonPhrase());
         }
      } catch (Exception e) {
         throw new ServletException(e);
      }

      return responseWriter;
   }

   private void initContainerRequest(ContainerRequest requestContext, final HttpServletRequest servletRequest, final HttpServletResponse servletResponse, ResponseWriter responseWriter) throws IOException {
      try {
         requestContext.setEntityStream(new InputStreamWrapper() {
            protected InputStream getWrapped() {
               try {
                  return servletRequest.getInputStream();
               } catch (IOException e) {
                  throw new UncheckedIOException(e);
               }
            }
         });
      } catch (UncheckedIOException e) {
         throw e.getCause();
      }

      requestContext.setRequestScopedInitializer(this.requestScopedInitializer.get(new RequestContextProvider() {
         public HttpServletRequest getHttpServletRequest() {
            return servletRequest;
         }

         public HttpServletResponse getHttpServletResponse() {
            return servletResponse;
         }
      }));
      requestContext.setWriter(responseWriter);
      this.addRequestHeaders(servletRequest, requestContext);
      this.filterFormParameters(servletRequest, requestContext);
   }

   private static SecurityContext getSecurityContext(final HttpServletRequest request) {
      return new SecurityContext() {
         public Principal getUserPrincipal() {
            return request.getUserPrincipal();
         }

         public boolean isUserInRole(String role) {
            return request.isUserInRole(role);
         }

         public boolean isSecure() {
            return request.isSecure();
         }

         public String getAuthenticationScheme() {
            return request.getAuthType();
         }
      };
   }

   private static ResourceConfig createResourceConfig(WebConfig config) throws ServletException {
      ServletContext servletContext = config.getServletContext();
      ResourceConfig resourceConfig = Utils.retrieve(config.getServletContext(), config.getName());
      if (resourceConfig != null) {
         return resourceConfig;
      } else {
         Map<String, Object> initParams = getInitParams(config);
         Map<String, Object> contextParams = Utils.getContextParams(servletContext);
         String jaxrsApplicationClassName = config.getInitParameter("jakarta.ws.rs.Application");
         if (jaxrsApplicationClassName == null) {
            resourceConfig = (new ResourceConfig()).addProperties(initParams).addProperties(contextParams);
            String webApp = config.getInitParameter("jersey.config.servlet.provider.webapp");
            if (webApp != null && !"false".equals(webApp)) {
               resourceConfig.registerFinder(new WebAppResourcesScanner(servletContext));
            }

            return resourceConfig;
         } else {
            try {
               Class<? extends Application> jaxrsApplicationClass = (Class)AccessController.doPrivileged(ReflectionHelper.classForNameWithExceptionPEA(jaxrsApplicationClassName));
               if (Application.class.isAssignableFrom(jaxrsApplicationClass)) {
                  return ResourceConfig.forApplicationClass(jaxrsApplicationClass).addProperties(initParams).addProperties(contextParams);
               } else {
                  throw new ServletException(LocalizationMessages.RESOURCE_CONFIG_PARENT_CLASS_INVALID(jaxrsApplicationClassName, Application.class));
               }
            } catch (PrivilegedActionException e) {
               throw new ServletException(LocalizationMessages.RESOURCE_CONFIG_UNABLE_TO_LOAD(jaxrsApplicationClassName), e.getCause());
            } catch (ClassNotFoundException e) {
               throw new ServletException(LocalizationMessages.RESOURCE_CONFIG_UNABLE_TO_LOAD(jaxrsApplicationClassName), e);
            }
         }
      }
   }

   private void configure(ResourceConfig resourceConfig, ServletContainerProvider[] allServletContainerProviders) throws ServletException {
      for(ServletContainerProvider servletContainerProvider : allServletContainerProviders) {
         servletContainerProvider.configure(resourceConfig);
      }

   }

   private void addRequestHeaders(HttpServletRequest request, ContainerRequest requestContext) {
      Enumeration<String> names = request.getHeaderNames();

      while(names.hasMoreElements()) {
         String name = (String)names.nextElement();
         Enumeration<String> values = request.getHeaders(name);

         while(values.hasMoreElements()) {
            String value = (String)values.nextElement();
            if (value != null) {
               requestContext.header(name, value);
            }
         }
      }

   }

   private static Map getInitParams(WebConfig webConfig) {
      Map<String, Object> props = new HashMap();
      Enumeration names = webConfig.getInitParameterNames();

      while(names.hasMoreElements()) {
         String name = (String)names.nextElement();
         props.put(name, webConfig.getInitParameter(name));
      }

      return props;
   }

   private void filterFormParameters(HttpServletRequest servletRequest, ContainerRequest containerRequest) {
      if (MediaTypes.typeEqual(MediaType.APPLICATION_FORM_URLENCODED_TYPE, containerRequest.getMediaType()) && !containerRequest.hasEntity()) {
         Form form = new Form();
         Enumeration parameterNames = servletRequest.getParameterNames();
         String queryString = servletRequest.getQueryString();
         List<String> queryParams = queryString != null ? this.getDecodedQueryParamList(queryString) : Collections.emptyList();
         boolean keepQueryParams = this.queryParamsAsFormParams || queryParams.isEmpty();
         MultivaluedMap<String, String> formMap = form.asMap();

         while(parameterNames.hasMoreElements()) {
            String name = (String)parameterNames.nextElement();
            List<String> values = Arrays.asList(servletRequest.getParameterValues(name));
            List<String> filteredValues = keepQueryParams ? values : this.filterQueryParams(name, values, queryParams);
            if (!filteredValues.isEmpty()) {
               formMap.put(name, filteredValues);
            }
         }

         if (!formMap.isEmpty()) {
            containerRequest.setProperty("jersey.config.server.representation.decoded.form", form);
            if (LOGGER.isLoggable(Level.WARNING)) {
               LOGGER.log(Level.WARNING, LocalizationMessages.FORM_PARAM_CONSUMED(containerRequest.getRequestUri()));
            }
         }
      }

   }

   private List getDecodedQueryParamList(String queryString) {
      List<String> params = new ArrayList();

      for(String param : queryString.split("&")) {
         params.add(UriComponent.decode(param, org.glassfish.jersey.uri.UriComponent.Type.QUERY_PARAM));
      }

      return params;
   }

   private List filterQueryParams(String name, List values, Collection params) {
      return (List)values.stream().filter((s) -> !params.remove(name + "=" + s) && !params.remove(name + "[]=" + s)).collect(Collectors.toList());
   }

   public ApplicationHandler getAppHandler() {
      return this.appHandler;
   }

   private static class HttpServletRequestReferencingFactory extends ReferencingFactory {
      @Inject
      public HttpServletRequestReferencingFactory(Provider referenceFactory) {
         super(referenceFactory);
      }
   }

   private static class HttpServletResponseReferencingFactory extends ReferencingFactory {
      @Inject
      public HttpServletResponseReferencingFactory(Provider referenceFactory) {
         super(referenceFactory);
      }
   }

   private final class WebComponentBinder extends AbstractBinder {
      private final Map applicationProperties;

      private WebComponentBinder(Map applicationProperties) {
         this.applicationProperties = applicationProperties;
      }

      protected void configure() {
         if (!WebComponent.this.requestResponseBindingExternalized) {
            ((SupplierClassBinding)((SupplierClassBinding)((SupplierClassBinding)this.bindFactory(HttpServletRequestReferencingFactory.class).to(HttpServletRequest.class)).proxy(true)).proxyForSameScope(false)).in(RequestScoped.class);
            ((SupplierInstanceBinding)this.bindFactory(ReferencingFactory.referenceFactory()).to(new GenericType() {
            })).in(RequestScoped.class);
            ((SupplierClassBinding)((SupplierClassBinding)((SupplierClassBinding)this.bindFactory(HttpServletResponseReferencingFactory.class).to(HttpServletResponse.class)).proxy(true)).proxyForSameScope(false)).in(RequestScoped.class);
            ((SupplierInstanceBinding)this.bindFactory(ReferencingFactory.referenceFactory()).to(new GenericType() {
            })).in(RequestScoped.class);
         }

         WebConfig var10001 = WebComponent.this.webConfig;
         var10001.getClass();
         ((SupplierInstanceBinding)this.bindFactory(var10001::getServletContext).to(ServletContext.class)).in(Singleton.class);
         ServletConfig servletConfig = WebComponent.this.webConfig.getServletConfig();
         if (WebComponent.this.webConfig.getConfigType() == WebConfig.ConfigType.ServletConfig) {
            ((SupplierInstanceBinding)this.bindFactory(() -> servletConfig).to(ServletConfig.class)).in(Singleton.class);
            Enumeration initParams = servletConfig.getInitParameterNames();

            while(initParams.hasMoreElements()) {
               String initParamName = (String)initParams.nextElement();
               if (initParamName.startsWith("unit:")) {
                  this.install(new AbstractBinder[]{new PersistenceUnitBinder(servletConfig)});
                  break;
               }
            }
         } else {
            var10001 = WebComponent.this.webConfig;
            var10001.getClass();
            ((SupplierInstanceBinding)this.bindFactory(var10001::getFilterConfig).to(FilterConfig.class)).in(Singleton.class);
         }

         ((SupplierInstanceBinding)this.bindFactory(() -> WebComponent.this.webConfig).to(WebConfig.class)).in(Singleton.class);
         this.install(new AbstractBinder[]{new ServiceFinderBinder(AsyncContextDelegateProvider.class, this.applicationProperties, RuntimeType.SERVER)});
         this.install(new AbstractBinder[]{new ServiceFinderBinder(FilterUrlMappingsProvider.class, this.applicationProperties, RuntimeType.SERVER)});
      }
   }
}
