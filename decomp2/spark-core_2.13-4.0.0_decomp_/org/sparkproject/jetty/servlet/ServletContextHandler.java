package org.sparkproject.jetty.servlet;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.ServletSecurityElement;
import jakarta.servlet.SessionCookieConfig;
import jakarta.servlet.SessionTrackingMode;
import jakarta.servlet.descriptor.JspConfigDescriptor;
import jakarta.servlet.descriptor.JspPropertyGroupDescriptor;
import jakarta.servlet.descriptor.TaglibDescriptor;
import jakarta.servlet.http.HttpSessionActivationListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingListener;
import jakarta.servlet.http.HttpSessionIdListener;
import jakarta.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.EventListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.security.ConstraintAware;
import org.sparkproject.jetty.security.ConstraintMapping;
import org.sparkproject.jetty.security.ConstraintSecurityHandler;
import org.sparkproject.jetty.security.SecurityHandler;
import org.sparkproject.jetty.server.Dispatcher;
import org.sparkproject.jetty.server.Handler;
import org.sparkproject.jetty.server.HandlerContainer;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.server.handler.ErrorHandler;
import org.sparkproject.jetty.server.handler.HandlerCollection;
import org.sparkproject.jetty.server.handler.HandlerWrapper;
import org.sparkproject.jetty.server.handler.gzip.GzipHandler;
import org.sparkproject.jetty.server.session.SessionHandler;
import org.sparkproject.jetty.util.DecoratedObjectFactory;
import org.sparkproject.jetty.util.DeprecationWarning;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.AbstractLifeCycle;
import org.sparkproject.jetty.util.component.ContainerLifeCycle;
import org.sparkproject.jetty.util.component.LifeCycle;

@ManagedObject("Servlet Context Handler")
public class ServletContextHandler extends ContextHandler {
   private static final Logger LOG = LoggerFactory.getLogger(ServletContextHandler.class);
   public static final int SESSIONS = 1;
   public static final int SECURITY = 2;
   public static final int NO_SESSIONS = 0;
   public static final int NO_SECURITY = 0;
   protected final DecoratedObjectFactory _objFactory;
   protected Class _defaultSecurityHandlerClass;
   protected SessionHandler _sessionHandler;
   protected SecurityHandler _securityHandler;
   protected ServletHandler _servletHandler;
   protected int _options;
   protected JspConfigDescriptor _jspConfig;
   private boolean _startListeners;

   public static ServletContextHandler getServletContextHandler(ServletContext servletContext, String purpose) {
      ContextHandler contextHandler = ContextHandler.getContextHandler(servletContext);
      if (contextHandler == null) {
         throw new IllegalStateException("No Jetty ContextHandler, " + purpose + " unavailable");
      } else if (!(contextHandler instanceof ServletContextHandler)) {
         throw new IllegalStateException("No Jetty ServletContextHandler, " + purpose + " unavailable");
      } else {
         return (ServletContextHandler)contextHandler;
      }
   }

   public static ServletContextHandler getServletContextHandler(ServletContext context) {
      ContextHandler handler = getContextHandler(context);
      if (handler == null) {
         return null;
      } else {
         return handler instanceof ServletContextHandler ? (ServletContextHandler)handler : null;
      }
   }

   public ServletContextHandler() {
      this((HandlerContainer)null, (SessionHandler)null, (SecurityHandler)null, (ServletHandler)null, (ErrorHandler)null);
   }

   public ServletContextHandler(int options) {
      this((HandlerContainer)null, (String)null, options);
   }

   public ServletContextHandler(HandlerContainer parent, String contextPath) {
      this(parent, contextPath, (SessionHandler)null, (SecurityHandler)null, (ServletHandler)null, (ErrorHandler)null);
   }

   public ServletContextHandler(HandlerContainer parent, String contextPath, int options) {
      this(parent, contextPath, (SessionHandler)null, (SecurityHandler)null, (ServletHandler)null, (ErrorHandler)null, options);
   }

   public ServletContextHandler(HandlerContainer parent, String contextPath, boolean sessions, boolean security) {
      this(parent, contextPath, (sessions ? 1 : 0) | (security ? 2 : 0));
   }

   public ServletContextHandler(HandlerContainer parent, SessionHandler sessionHandler, SecurityHandler securityHandler, ServletHandler servletHandler, ErrorHandler errorHandler) {
      this(parent, (String)null, sessionHandler, securityHandler, servletHandler, errorHandler);
   }

   public ServletContextHandler(HandlerContainer parent, String contextPath, SessionHandler sessionHandler, SecurityHandler securityHandler, ServletHandler servletHandler, ErrorHandler errorHandler) {
      this(parent, contextPath, sessionHandler, securityHandler, servletHandler, errorHandler, 0);
   }

   public ServletContextHandler(HandlerContainer parent, String contextPath, SessionHandler sessionHandler, SecurityHandler securityHandler, ServletHandler servletHandler, ErrorHandler errorHandler, int options) {
      super((ContextHandler.Context)null, parent, contextPath);
      this._defaultSecurityHandlerClass = ConstraintSecurityHandler.class;
      this._options = options;
      this._scontext = new Context();
      this._sessionHandler = sessionHandler;
      this._securityHandler = securityHandler;
      this._servletHandler = servletHandler;
      this._objFactory = new DecoratedObjectFactory();
      this.relinkHandlers();
      if (errorHandler != null) {
         this.setErrorHandler(errorHandler);
      }

   }

   protected void setParent(HandlerContainer parent) {
      if (parent instanceof HandlerWrapper) {
         ((HandlerWrapper)parent).setHandler(this);
      } else if (parent instanceof HandlerCollection) {
         ((HandlerCollection)parent).addHandler(this);
      }

   }

   public boolean addEventListener(EventListener listener) {
      if (!super.addEventListener(listener)) {
         return false;
      } else {
         if ((listener instanceof HttpSessionActivationListener || listener instanceof HttpSessionAttributeListener || listener instanceof HttpSessionBindingListener || listener instanceof HttpSessionListener || listener instanceof HttpSessionIdListener) && this._sessionHandler != null) {
            this._sessionHandler.addEventListener(listener);
         }

         return true;
      }
   }

   public void setHandler(Handler handler) {
      if (handler instanceof SessionHandler) {
         this.setSessionHandler((SessionHandler)handler);
      } else if (handler instanceof SecurityHandler) {
         this.setSecurityHandler((SecurityHandler)handler);
      } else if (handler instanceof ServletHandler) {
         this.setServletHandler((ServletHandler)handler);
      } else if (handler instanceof GzipHandler) {
         this.setGzipHandler((GzipHandler)handler);
      } else {
         if (handler != null) {
            LOG.warn("ServletContextHandler.setHandler should not be called directly. Use insertHandler or setSessionHandler etc.");
         }

         super.setHandler(handler);
      }

   }

   private void doSetHandler(HandlerWrapper wrapper, Handler handler) {
      if (wrapper == this) {
         super.setHandler(handler);
      } else {
         wrapper.setHandler(handler);
      }

   }

   private void relinkHandlers() {
      HandlerWrapper handler = this;
      if (this.getSessionHandler() != null) {
         while(!(handler.getHandler() instanceof SessionHandler) && !(handler.getHandler() instanceof SecurityHandler) && !(handler.getHandler() instanceof ServletHandler) && handler.getHandler() instanceof HandlerWrapper) {
            handler = (HandlerWrapper)handler.getHandler();
         }

         if (handler.getHandler() != this._sessionHandler) {
            this.doSetHandler(handler, this._sessionHandler);
         }

         handler = this._sessionHandler;
      }

      if (this.getSecurityHandler() != null) {
         while(!(handler.getHandler() instanceof SecurityHandler) && !(handler.getHandler() instanceof ServletHandler) && handler.getHandler() instanceof HandlerWrapper) {
            handler = (HandlerWrapper)handler.getHandler();
         }

         if (handler.getHandler() != this._securityHandler) {
            this.doSetHandler(handler, this._securityHandler);
         }

         handler = this._securityHandler;
      }

      if (this.getServletHandler() != null) {
         while(!(handler.getHandler() instanceof ServletHandler) && handler.getHandler() instanceof HandlerWrapper) {
            handler = (HandlerWrapper)handler.getHandler();
         }

         if (handler.getHandler() != this._servletHandler) {
            this.doSetHandler(handler, this._servletHandler);
         }
      }

   }

   protected void doStart() throws Exception {
      this._objFactory.addDecorator(new DeprecationWarning());
      this.getServletContext().setAttribute(DecoratedObjectFactory.ATTR, this._objFactory);
      super.doStart();
   }

   protected void doStop() throws Exception {
      super.doStop();
      this._objFactory.clear();
      this.getServletContext().removeAttribute(DecoratedObjectFactory.ATTR);
   }

   public Class getDefaultSecurityHandlerClass() {
      return this._defaultSecurityHandlerClass;
   }

   public void setDefaultSecurityHandlerClass(Class defaultSecurityHandlerClass) {
      this._defaultSecurityHandlerClass = defaultSecurityHandlerClass;
   }

   protected SessionHandler newSessionHandler() {
      return new SessionHandler();
   }

   protected SecurityHandler newSecurityHandler() {
      try {
         return (SecurityHandler)this.getDefaultSecurityHandlerClass().getDeclaredConstructor().newInstance();
      } catch (Exception e) {
         throw new IllegalStateException(e);
      }
   }

   protected ServletHandler newServletHandler() {
      return new ServletHandler();
   }

   protected void startContext() throws Exception {
      for(ServletContainerInitializerCaller sci : this.getBeans(ServletContainerInitializerCaller.class)) {
         if (sci.isStopped()) {
            sci.start();
            if (this.isAuto(sci)) {
               this.manage(sci);
            }
         }
      }

      if (this._servletHandler != null && this._servletHandler.getListeners() != null) {
         for(ListenerHolder holder : this._servletHandler.getListeners()) {
            holder.start();
         }
      }

      this._startListeners = true;
      super.startContext();
      if (this._servletHandler != null) {
         this._servletHandler.initialize();
      }

   }

   protected void stopContext() throws Exception {
      this._startListeners = false;
      super.stopContext();
   }

   @ManagedAttribute(
      value = "context security handler",
      readonly = true
   )
   public SecurityHandler getSecurityHandler() {
      if (this._securityHandler == null && (this._options & 2) != 0 && !this.isStarted()) {
         this._securityHandler = this.newSecurityHandler();
      }

      return this._securityHandler;
   }

   @ManagedAttribute(
      value = "context servlet handler",
      readonly = true
   )
   public ServletHandler getServletHandler() {
      if (this._servletHandler == null && !this.isStarted()) {
         this._servletHandler = this.newServletHandler();
      }

      return this._servletHandler;
   }

   @ManagedAttribute(
      value = "context session handler",
      readonly = true
   )
   public SessionHandler getSessionHandler() {
      if (this._sessionHandler == null && (this._options & 1) != 0 && !this.isStarted()) {
         this._sessionHandler = this.newSessionHandler();
      }

      return this._sessionHandler;
   }

   public ServletHolder addServlet(String className, String pathSpec) {
      return this.getServletHandler().addServletWithMapping(className, pathSpec);
   }

   public ServletHolder addServlet(Class servlet, String pathSpec) {
      return this.getServletHandler().addServletWithMapping(servlet, pathSpec);
   }

   public void addServlet(ServletHolder servlet, String pathSpec) {
      this.getServletHandler().addServletWithMapping(servlet, pathSpec);
   }

   public void addFilter(FilterHolder holder, String pathSpec, EnumSet dispatches) {
      this.getServletHandler().addFilterWithMapping(holder, pathSpec, dispatches);
   }

   public FilterHolder addFilter(Class filterClass, String pathSpec, EnumSet dispatches) {
      return this.getServletHandler().addFilterWithMapping(filterClass, pathSpec, dispatches);
   }

   public FilterHolder addFilter(String filterClass, String pathSpec, EnumSet dispatches) {
      return this.getServletHandler().addFilterWithMapping(filterClass, pathSpec, dispatches);
   }

   public ServletContainerInitializerHolder addServletContainerInitializer(ServletContainerInitializer sci) {
      if (!this.isStopped()) {
         throw new IllegalStateException("ServletContainerInitializers should be added before starting");
      } else {
         ServletContainerInitializerHolder holder = new ServletContainerInitializerHolder(sci, new Class[0]);
         this.addServletContainerInitializer(holder);
         return holder;
      }
   }

   public ServletContainerInitializerHolder addServletContainerInitializer(ServletContainerInitializer sci, Class... classes) {
      if (!this.isStopped()) {
         throw new IllegalStateException("ServletContainerInitializers should be added before starting");
      } else {
         ServletContainerInitializerHolder holder = new ServletContainerInitializerHolder(sci, classes);
         this.addServletContainerInitializer(holder);
         return holder;
      }
   }

   public void addServletContainerInitializer(ServletContainerInitializerHolder... sciHolders) {
      ServletContainerInitializerStarter starter = (ServletContainerInitializerStarter)this.getBean(ServletContainerInitializerStarter.class);
      if (starter == null) {
         starter = new ServletContainerInitializerStarter();
         this.addBean(starter, true);
      }

      starter.addServletContainerInitializerHolders(sciHolders);
   }

   protected ServletRegistration.Dynamic dynamicHolderAdded(ServletHolder holder) {
      return holder.getRegistration();
   }

   protected void addRoles(String... roleNames) {
      if (this._securityHandler != null && this._securityHandler instanceof ConstraintAware) {
         HashSet<String> union = new HashSet();
         Set<String> existing = ((ConstraintAware)this._securityHandler).getRoles();
         if (existing != null) {
            union.addAll(existing);
         }

         union.addAll(Arrays.asList(roleNames));
         ((ConstraintSecurityHandler)this._securityHandler).setRoles(union);
      }

   }

   public Set setServletSecurity(ServletRegistration.Dynamic registration, ServletSecurityElement servletSecurityElement) {
      Collection<String> pathSpecs = registration.getMappings();
      if (pathSpecs != null) {
         for(String pathSpec : pathSpecs) {
            for(ConstraintMapping m : ConstraintSecurityHandler.createConstraintsWithMappingsForPath(registration.getName(), pathSpec, servletSecurityElement)) {
               ((ConstraintAware)this.getSecurityHandler()).addConstraintMapping(m);
            }
         }
      }

      return Collections.emptySet();
   }

   public void callContextInitialized(ServletContextListener l, ServletContextEvent e) {
      try {
         if (this.isProgrammaticListener(l)) {
            this.getServletContext().setEnabled(false);
         }

         super.callContextInitialized(l, e);
      } finally {
         this.getServletContext().setEnabled(true);
      }

   }

   public void callContextDestroyed(ServletContextListener l, ServletContextEvent e) {
      super.callContextDestroyed(l, e);
   }

   private void replaceHandler(HandlerWrapper handler, HandlerWrapper replacement) {
      if (this.isStarted()) {
         throw new IllegalStateException("STARTED");
      } else {
         Handler next = null;
         if (handler != null) {
            next = handler.getHandler();
            handler.setHandler((Handler)null);

            for(HandlerWrapper wrapper = this; wrapper != null; wrapper = wrapper.getHandler() instanceof HandlerWrapper ? (HandlerWrapper)wrapper.getHandler() : null) {
               if (wrapper.getHandler() == handler) {
                  this.doSetHandler(wrapper, replacement);
                  break;
               }
            }
         }

         if (next != null && replacement.getHandler() == null) {
            replacement.setHandler(next);
         }

      }
   }

   public void setSessionHandler(SessionHandler sessionHandler) {
      this.replaceHandler(this._sessionHandler, sessionHandler);
      this._sessionHandler = sessionHandler;
      this.relinkHandlers();
   }

   public void setSecurityHandler(SecurityHandler securityHandler) {
      this.replaceHandler(this._securityHandler, securityHandler);
      this._securityHandler = securityHandler;
      this.relinkHandlers();
   }

   public void setServletHandler(ServletHandler servletHandler) {
      this.replaceHandler(this._servletHandler, servletHandler);
      this._servletHandler = servletHandler;
      this.relinkHandlers();
   }

   /** @deprecated */
   @Deprecated
   public void setGzipHandler(GzipHandler gzipHandler) {
      this.insertHandler(gzipHandler);
      LOG.warn("ServletContextHandler.setGzipHandler(GzipHandler) is deprecated, use insertHandler(HandlerWrapper) instead.");
   }

   public void insertHandler(HandlerWrapper handler) {
      if (handler instanceof SessionHandler) {
         this.setSessionHandler((SessionHandler)handler);
      } else if (handler instanceof SecurityHandler) {
         this.setSecurityHandler((SecurityHandler)handler);
      } else if (handler instanceof ServletHandler) {
         this.setServletHandler((ServletHandler)handler);
      } else {
         HandlerWrapper tail;
         for(tail = handler; tail.getHandler() instanceof HandlerWrapper; tail = (HandlerWrapper)tail.getHandler()) {
         }

         if (tail.getHandler() != null) {
            throw new IllegalArgumentException("bad tail of inserted wrapper chain");
         }

         HandlerWrapper h;
         HandlerWrapper wrapper;
         for(h = this; h.getHandler() instanceof HandlerWrapper; h = wrapper) {
            wrapper = (HandlerWrapper)h.getHandler();
            if (wrapper instanceof SessionHandler || wrapper instanceof SecurityHandler || wrapper instanceof ServletHandler) {
               break;
            }
         }

         wrapper = h.getHandler();
         this.doSetHandler(h, handler);
         this.doSetHandler(tail, wrapper);
      }

      this.relinkHandlers();
   }

   public DecoratedObjectFactory getObjectFactory() {
      return this._objFactory;
   }

   void destroyServlet(Servlet servlet) {
      this._objFactory.destroy(servlet);
   }

   void destroyFilter(Filter filter) {
      this._objFactory.destroy(filter);
   }

   void destroyListener(EventListener listener) {
      this._objFactory.destroy(listener);
   }

   public static class JspPropertyGroup implements JspPropertyGroupDescriptor {
      private final List _urlPatterns = new ArrayList();
      private String _elIgnored;
      private String _pageEncoding;
      private String _scriptingInvalid;
      private String _isXml;
      private final List _includePreludes = new ArrayList();
      private final List _includeCodas = new ArrayList();
      private String _deferredSyntaxAllowedAsLiteral;
      private String _trimDirectiveWhitespaces;
      private String _defaultContentType;
      private String _buffer;
      private String _errorOnUndeclaredNamespace;

      public Collection getUrlPatterns() {
         return new ArrayList(this._urlPatterns);
      }

      public void addUrlPattern(String s) {
         if (!this._urlPatterns.contains(s)) {
            this._urlPatterns.add(s);
         }

      }

      public String getElIgnored() {
         return this._elIgnored;
      }

      public void setElIgnored(String s) {
         this._elIgnored = s;
      }

      public String getPageEncoding() {
         return this._pageEncoding;
      }

      public void setPageEncoding(String pageEncoding) {
         this._pageEncoding = pageEncoding;
      }

      public void setScriptingInvalid(String scriptingInvalid) {
         this._scriptingInvalid = scriptingInvalid;
      }

      public void setIsXml(String isXml) {
         this._isXml = isXml;
      }

      public void setDeferredSyntaxAllowedAsLiteral(String deferredSyntaxAllowedAsLiteral) {
         this._deferredSyntaxAllowedAsLiteral = deferredSyntaxAllowedAsLiteral;
      }

      public void setTrimDirectiveWhitespaces(String trimDirectiveWhitespaces) {
         this._trimDirectiveWhitespaces = trimDirectiveWhitespaces;
      }

      public void setDefaultContentType(String defaultContentType) {
         this._defaultContentType = defaultContentType;
      }

      public void setBuffer(String buffer) {
         this._buffer = buffer;
      }

      public void setErrorOnUndeclaredNamespace(String errorOnUndeclaredNamespace) {
         this._errorOnUndeclaredNamespace = errorOnUndeclaredNamespace;
      }

      public String getScriptingInvalid() {
         return this._scriptingInvalid;
      }

      public String getIsXml() {
         return this._isXml;
      }

      public Collection getIncludePreludes() {
         return new ArrayList(this._includePreludes);
      }

      public void addIncludePrelude(String prelude) {
         if (!this._includePreludes.contains(prelude)) {
            this._includePreludes.add(prelude);
         }

      }

      public Collection getIncludeCodas() {
         return new ArrayList(this._includeCodas);
      }

      public void addIncludeCoda(String coda) {
         if (!this._includeCodas.contains(coda)) {
            this._includeCodas.add(coda);
         }

      }

      public String getDeferredSyntaxAllowedAsLiteral() {
         return this._deferredSyntaxAllowedAsLiteral;
      }

      public String getTrimDirectiveWhitespaces() {
         return this._trimDirectiveWhitespaces;
      }

      public String getDefaultContentType() {
         return this._defaultContentType;
      }

      public String getBuffer() {
         return this._buffer;
      }

      public String getErrorOnUndeclaredNamespace() {
         return this._errorOnUndeclaredNamespace;
      }

      public String toString() {
         StringBuilder sb = new StringBuilder();
         sb.append("JspPropertyGroupDescriptor:");
         sb.append(" el-ignored=").append(this._elIgnored);
         sb.append(" is-xml=").append(this._isXml);
         sb.append(" page-encoding=").append(this._pageEncoding);
         sb.append(" scripting-invalid=").append(this._scriptingInvalid);
         sb.append(" deferred-syntax-allowed-as-literal=").append(this._deferredSyntaxAllowedAsLiteral);
         sb.append(" trim-directive-whitespaces").append(this._trimDirectiveWhitespaces);
         sb.append(" default-content-type=").append(this._defaultContentType);
         sb.append(" buffer=").append(this._buffer);
         sb.append(" error-on-undeclared-namespace=").append(this._errorOnUndeclaredNamespace);

         for(String prelude : this._includePreludes) {
            sb.append(" include-prelude=").append(prelude);
         }

         for(String coda : this._includeCodas) {
            sb.append(" include-coda=").append(coda);
         }

         return sb.toString();
      }
   }

   public static class TagLib implements TaglibDescriptor {
      private String _uri;
      private String _location;

      public String getTaglibURI() {
         return this._uri;
      }

      public void setTaglibURI(String uri) {
         this._uri = uri;
      }

      public String getTaglibLocation() {
         return this._location;
      }

      public void setTaglibLocation(String location) {
         this._location = location;
      }

      public String toString() {
         return "TagLibDescriptor: taglib-uri=" + this._uri + " location=" + this._location;
      }
   }

   public static class JspConfig implements JspConfigDescriptor {
      private final List _taglibs = new ArrayList();
      private final List _jspPropertyGroups = new ArrayList();

      public Collection getTaglibs() {
         return new ArrayList(this._taglibs);
      }

      public void addTaglibDescriptor(TaglibDescriptor d) {
         this._taglibs.add(d);
      }

      public Collection getJspPropertyGroups() {
         return new ArrayList(this._jspPropertyGroups);
      }

      public void addJspPropertyGroup(JspPropertyGroupDescriptor g) {
         this._jspPropertyGroups.add(g);
      }

      public String toString() {
         StringBuilder sb = new StringBuilder();
         sb.append("JspConfigDescriptor: \n");

         for(TaglibDescriptor taglib : this._taglibs) {
            sb.append(taglib).append("\n");
         }

         for(JspPropertyGroupDescriptor jpg : this._jspPropertyGroups) {
            sb.append(jpg).append("\n");
         }

         return sb.toString();
      }
   }

   public class Context extends ContextHandler.Context {
      public RequestDispatcher getNamedDispatcher(String name) {
         ContextHandler context = ServletContextHandler.this;
         if (ServletContextHandler.this._servletHandler == null) {
            return null;
         } else {
            ServletHolder holder = ServletContextHandler.this._servletHandler.getServlet(name);
            return holder != null && holder.isEnabled() ? new Dispatcher(context, name) : null;
         }
      }

      private void checkDynamic(String name) {
         if (ServletContextHandler.this.isStarted()) {
            throw new IllegalStateException();
         } else if (ServletContextHandler.this.getServletHandler().isInitialized()) {
            throw new IllegalStateException();
         } else if (StringUtil.isBlank(name)) {
            throw new IllegalArgumentException("Missing name");
         } else if (!this._enabled) {
            throw new UnsupportedOperationException();
         }
      }

      public FilterRegistration.Dynamic addFilter(String filterName, Class filterClass) {
         this.checkDynamic(filterName);
         ServletHandler handler = ServletContextHandler.this.getServletHandler();
         FilterHolder holder = handler.getFilter(filterName);
         if (holder == null) {
            holder = handler.newFilterHolder(Source.JAVAX_API);
            holder.setName(filterName);
            holder.setHeldClass(filterClass);
            handler.addFilter(holder);
            return holder.getRegistration();
         } else if (holder.getClassName() == null && holder.getHeldClass() == null) {
            holder.setHeldClass(filterClass);
            return holder.getRegistration();
         } else {
            return null;
         }
      }

      public FilterRegistration.Dynamic addFilter(String filterName, String className) {
         this.checkDynamic(filterName);
         ServletHandler handler = ServletContextHandler.this.getServletHandler();
         FilterHolder holder = handler.getFilter(filterName);
         if (holder == null) {
            holder = handler.newFilterHolder(Source.JAVAX_API);
            holder.setName(filterName);
            holder.setClassName(className);
            handler.addFilter(holder);
            return holder.getRegistration();
         } else if (holder.getClassName() == null && holder.getHeldClass() == null) {
            holder.setClassName(className);
            return holder.getRegistration();
         } else {
            return null;
         }
      }

      public FilterRegistration.Dynamic addFilter(String filterName, Filter filter) {
         this.checkDynamic(filterName);
         ServletHandler handler = ServletContextHandler.this.getServletHandler();
         FilterHolder holder = handler.getFilter(filterName);
         if (holder == null) {
            holder = handler.newFilterHolder(Source.JAVAX_API);
            holder.setName(filterName);
            holder.setFilter(filter);
            handler.addFilter(holder);
            return holder.getRegistration();
         } else if (holder.getClassName() == null && holder.getHeldClass() == null) {
            holder.setFilter(filter);
            return holder.getRegistration();
         } else {
            return null;
         }
      }

      public ServletRegistration.Dynamic addServlet(String servletName, Class servletClass) {
         this.checkDynamic(servletName);
         ServletHandler handler = ServletContextHandler.this.getServletHandler();
         ServletHolder holder = handler.getServlet(servletName);
         if (holder == null) {
            holder = handler.newServletHolder(Source.JAVAX_API);
            holder.setName(servletName);
            holder.setHeldClass(servletClass);
            handler.addServlet(holder);
            return ServletContextHandler.this.dynamicHolderAdded(holder);
         } else if (holder.getClassName() == null && holder.getHeldClass() == null) {
            holder.setHeldClass(servletClass);
            return holder.getRegistration();
         } else {
            return null;
         }
      }

      public ServletRegistration.Dynamic addServlet(String servletName, String className) {
         this.checkDynamic(servletName);
         ServletHandler handler = ServletContextHandler.this.getServletHandler();
         ServletHolder holder = handler.getServlet(servletName);
         if (holder == null) {
            holder = handler.newServletHolder(Source.JAVAX_API);
            holder.setName(servletName);
            holder.setClassName(className);
            handler.addServlet(holder);
            return ServletContextHandler.this.dynamicHolderAdded(holder);
         } else if (holder.getClassName() == null && holder.getHeldClass() == null) {
            holder.setClassName(className);
            return holder.getRegistration();
         } else {
            return null;
         }
      }

      public ServletRegistration.Dynamic addServlet(String servletName, Servlet servlet) {
         this.checkDynamic(servletName);
         ServletHandler handler = ServletContextHandler.this.getServletHandler();
         ServletHolder holder = handler.getServlet(servletName);
         if (holder == null) {
            holder = handler.newServletHolder(Source.JAVAX_API);
            holder.setName(servletName);
            holder.setServlet(servlet);
            handler.addServlet(holder);
            return ServletContextHandler.this.dynamicHolderAdded(holder);
         } else if (holder.getClassName() == null && holder.getHeldClass() == null) {
            holder.setServlet(servlet);
            return holder.getRegistration();
         } else {
            return null;
         }
      }

      public ServletRegistration.Dynamic addJspFile(String servletName, String jspFile) {
         this.checkDynamic(servletName);
         ServletHandler handler = ServletContextHandler.this.getServletHandler();
         ServletHolder holder = handler.getServlet(servletName);
         if (holder == null) {
            holder = handler.newServletHolder(Source.JAVAX_API);
            holder.setName(servletName);
            holder.setForcedPath(jspFile);
            handler.addServlet(holder);
            return ServletContextHandler.this.dynamicHolderAdded(holder);
         } else if (holder.getClassName() == null && holder.getHeldClass() == null && holder.getForcedPath() == null) {
            holder.setForcedPath(jspFile);
            return holder.getRegistration();
         } else {
            return null;
         }
      }

      public String getInitParameter(String name) {
         Objects.requireNonNull(name);
         return super.getInitParameter(name);
      }

      public boolean setInitParameter(String name, String value) {
         Objects.requireNonNull(name);
         if (!ServletContextHandler.this.isStarting()) {
            throw new IllegalStateException();
         } else if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            return super.setInitParameter(name, value);
         }
      }

      public Object createInstance(Class clazz) throws ServletException {
         return ServletContextHandler.this._objFactory.decorate(super.createInstance(clazz));
      }

      public Object createInstance(BaseHolder holder) throws ServletException {
         Object var2;
         try {
            DecoratedObjectFactory.associateInfo(holder);
            var2 = this.createInstance(holder.getHeldClass());
         } finally {
            DecoratedObjectFactory.disassociateInfo();
         }

         return var2;
      }

      public void destroyFilter(Filter f) {
         ServletContextHandler.this._objFactory.destroy(f);
      }

      public void destroyServlet(Servlet s) {
         ServletContextHandler.this._objFactory.destroy(s);
      }

      public Set getDefaultSessionTrackingModes() {
         if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            return ServletContextHandler.this._sessionHandler != null ? ServletContextHandler.this._sessionHandler.getDefaultSessionTrackingModes() : null;
         }
      }

      public Set getEffectiveSessionTrackingModes() {
         if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            return ServletContextHandler.this._sessionHandler != null ? ServletContextHandler.this._sessionHandler.getEffectiveSessionTrackingModes() : null;
         }
      }

      public FilterRegistration getFilterRegistration(String filterName) {
         if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            FilterHolder holder = ServletContextHandler.this.getServletHandler().getFilter(filterName);
            return holder == null ? null : holder.getRegistration();
         }
      }

      public Map getFilterRegistrations() {
         if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            HashMap<String, FilterRegistration> registrations = new HashMap();
            ServletHandler handler = ServletContextHandler.this.getServletHandler();
            FilterHolder[] holders = handler.getFilters();
            if (holders != null) {
               for(FilterHolder holder : holders) {
                  registrations.put(holder.getName(), holder.getRegistration());
               }
            }

            return registrations;
         }
      }

      public ServletRegistration getServletRegistration(String servletName) {
         if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            ServletHolder holder = ServletContextHandler.this.getServletHandler().getServlet(servletName);
            return holder == null ? null : holder.getRegistration();
         }
      }

      public Map getServletRegistrations() {
         if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            HashMap<String, ServletRegistration> registrations = new HashMap();
            ServletHandler handler = ServletContextHandler.this.getServletHandler();
            ServletHolder[] holders = handler.getServlets();
            if (holders != null) {
               for(ServletHolder holder : holders) {
                  registrations.put(holder.getName(), holder.getRegistration());
               }
            }

            return registrations;
         }
      }

      public SessionCookieConfig getSessionCookieConfig() {
         if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            return ServletContextHandler.this._sessionHandler != null ? ServletContextHandler.this._sessionHandler.getSessionCookieConfig() : null;
         }
      }

      public void setSessionTrackingModes(Set sessionTrackingModes) {
         if (!ServletContextHandler.this.isStarting()) {
            throw new IllegalStateException();
         } else if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            if (ServletContextHandler.this._sessionHandler != null) {
               ServletContextHandler.this._sessionHandler.setSessionTrackingModes(sessionTrackingModes);
            }

         }
      }

      public int getSessionTimeout() {
         if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            int timeout = -1;
            if (ServletContextHandler.this._sessionHandler != null) {
               timeout = ServletContextHandler.this._sessionHandler.getMaxInactiveInterval();
            }

            return (int)TimeUnit.SECONDS.toMinutes((long)timeout);
         }
      }

      public void setSessionTimeout(int sessionTimeout) {
         if (!ServletContextHandler.this.isStarting()) {
            throw new IllegalStateException();
         } else if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            if (ServletContextHandler.this._sessionHandler != null) {
               long tmp = TimeUnit.MINUTES.toSeconds((long)sessionTimeout);
               if (tmp > 2147483647L) {
                  tmp = 2147483647L;
               }

               if (tmp < -2147483648L) {
                  tmp = -2147483648L;
               }

               ServletContextHandler.this._sessionHandler.setMaxInactiveInterval((int)tmp);
            }

         }
      }

      public Servlet createServlet(Class clazz) throws ServletException {
         if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            return super.createServlet(clazz);
         }
      }

      public Filter createFilter(Class clazz) throws ServletException {
         if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            return super.createFilter(clazz);
         }
      }

      public EventListener createListener(Class clazz) throws ServletException {
         if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            try {
               this.checkListener(clazz);
            } catch (IllegalArgumentException e) {
               if (!ServletContextListener.class.isAssignableFrom(clazz)) {
                  throw e;
               }
            }

            return super.createListener(clazz);
         }
      }

      public void addListener(String className) {
         if (!ServletContextHandler.this.isStarting()) {
            throw new IllegalStateException();
         } else if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            super.addListener(className);
         }
      }

      public void addListener(EventListener t) {
         if (!ServletContextHandler.this.isStarting()) {
            throw new IllegalStateException();
         } else if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            this.checkListener(t.getClass());
            ListenerHolder holder = ServletContextHandler.this.getServletHandler().newListenerHolder(Source.JAVAX_API);
            holder.setListener(t);
            ServletContextHandler.this.addProgrammaticListener(t);
            ServletContextHandler.this.getServletHandler().addListener(holder);
            if (ServletContextHandler.this._startListeners) {
               try {
                  holder.start();
               } catch (Exception e) {
                  throw new IllegalStateException(e);
               }
            }

         }
      }

      public void addListener(Class listenerClass) {
         if (!ServletContextHandler.this.isStarting()) {
            throw new IllegalStateException();
         } else if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            super.addListener(listenerClass);
         }
      }

      public JspConfigDescriptor getJspConfigDescriptor() {
         return ServletContextHandler.this._jspConfig;
      }

      public void setJspConfigDescriptor(JspConfigDescriptor d) {
         ServletContextHandler.this._jspConfig = d;
      }

      public void declareRoles(String... roleNames) {
         if (!ServletContextHandler.this.isStarting()) {
            throw new IllegalStateException();
         } else if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            ServletContextHandler.this.addRoles(roleNames);
         }
      }

      public String getRequestCharacterEncoding() {
         return ServletContextHandler.this.getDefaultRequestCharacterEncoding();
      }

      public void setRequestCharacterEncoding(String encoding) {
         if (!ServletContextHandler.this.isStarting()) {
            throw new IllegalStateException();
         } else {
            ServletContextHandler.this.setDefaultRequestCharacterEncoding(encoding);
         }
      }

      public String getResponseCharacterEncoding() {
         return ServletContextHandler.this.getDefaultResponseCharacterEncoding();
      }

      public void setResponseCharacterEncoding(String encoding) {
         if (!ServletContextHandler.this.isStarting()) {
            throw new IllegalStateException();
         } else {
            ServletContextHandler.this.setDefaultResponseCharacterEncoding(encoding);
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public static class Initializer extends AbstractLifeCycle implements ServletContainerInitializerCaller {
      private final ServletContextHandler _context;
      private final ServletContainerInitializer _sci;
      private final Set _classes;

      public Initializer(ServletContextHandler context, ServletContainerInitializer sci, Set classes) {
         this._context = context;
         this._sci = sci;
         this._classes = classes;
      }

      public Initializer(ServletContextHandler context, ServletContainerInitializer sci) {
         this(context, sci, Collections.emptySet());
      }

      protected void doStart() throws Exception {
         boolean oldExtended = this._context.getServletContext().isExtendedListenerTypes();

         try {
            this._context.getServletContext().setExtendedListenerTypes(true);
            this._sci.onStartup(this._classes, this._context.getServletContext());
         } finally {
            this._context.getServletContext().setExtendedListenerTypes(oldExtended);
         }

      }
   }

   public static class ServletContainerInitializerStarter extends ContainerLifeCycle implements ServletContainerInitializerCaller {
      public void addServletContainerInitializerHolders(ServletContainerInitializerHolder... holders) {
         for(ServletContainerInitializerHolder holder : holders) {
            this.addBean(holder, true);
         }

      }

      public Collection getServletContainerInitializerHolders() {
         return this.getContainedBeans(ServletContainerInitializerHolder.class);
      }

      protected void doStart() throws Exception {
         if (ServletContextHandler.LOG.isDebugEnabled()) {
            ServletContextHandler.LOG.debug("Starting SCIs");
         }

         super.doStart();
      }

      protected void doStop() throws Exception {
         for(ServletContainerInitializerHolder h : this.getServletContainerInitializerHolders()) {
            if (h.getSource().getOrigin() != Source.Origin.EMBEDDED) {
               this.removeBean(h);
            }
         }

         super.doStop();
      }
   }

   public interface ServletContainerInitializerCaller extends LifeCycle {
   }
}
