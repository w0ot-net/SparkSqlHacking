package org.sparkproject.jetty.server.handler;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.SessionCookieConfig;
import jakarta.servlet.SessionTrackingMode;
import jakarta.servlet.descriptor.JspConfigDescriptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionIdListener;
import jakarta.servlet.http.HttpSessionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.StackWalker.Option;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpHeaderValue;
import org.sparkproject.jetty.http.HttpURI;
import org.sparkproject.jetty.http.MimeTypes;
import org.sparkproject.jetty.server.ClassLoaderDump;
import org.sparkproject.jetty.server.Dispatcher;
import org.sparkproject.jetty.server.Handler;
import org.sparkproject.jetty.server.HandlerContainer;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.Server;
import org.sparkproject.jetty.server.SymlinkAllowedResourceAliasChecker;
import org.sparkproject.jetty.util.Attributes;
import org.sparkproject.jetty.util.AttributesMap;
import org.sparkproject.jetty.util.Index;
import org.sparkproject.jetty.util.Loader;
import org.sparkproject.jetty.util.MultiException;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.URIUtil;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.DumpableCollection;
import org.sparkproject.jetty.util.component.Graceful;
import org.sparkproject.jetty.util.component.LifeCycle;
import org.sparkproject.jetty.util.resource.Resource;
import org.sparkproject.jetty.util.security.SecurityUtils;

@ManagedObject("URI Context")
public class ContextHandler extends ScopedHandler implements Attributes, Graceful {
   public static final int SERVLET_MAJOR_VERSION = 5;
   public static final int SERVLET_MINOR_VERSION = 0;
   public static final Class[] SERVLET_LISTENER_TYPES = new Class[]{ServletContextListener.class, ServletContextAttributeListener.class, ServletRequestListener.class, ServletRequestAttributeListener.class, HttpSessionIdListener.class, HttpSessionListener.class, HttpSessionAttributeListener.class};
   public static final int DEFAULT_LISTENER_TYPE_INDEX = 1;
   public static final int EXTENDED_LISTENER_TYPE_INDEX = 0;
   private static final String UNIMPLEMENTED_USE_SERVLET_CONTEXT_HANDLER = "Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler";
   private static final Logger LOG = LoggerFactory.getLogger(ContextHandler.class);
   private static final ThreadLocal __context = new ThreadLocal();
   private static String __serverInfo = "jetty/" + Server.getVersion();
   public static final String MANAGED_ATTRIBUTES = "org.sparkproject.jetty.server.context.ManagedAttributes";
   public static final String MAX_FORM_KEYS_KEY = "org.sparkproject.jetty.server.Request.maxFormKeys";
   public static final String MAX_FORM_CONTENT_SIZE_KEY = "org.sparkproject.jetty.server.Request.maxFormContentSize";
   public static final int DEFAULT_MAX_FORM_KEYS = 1000;
   public static final int DEFAULT_MAX_FORM_CONTENT_SIZE = 200000;
   protected ContextStatus _contextStatus;
   protected Context _scontext;
   private final AttributesMap _attributes;
   private final Map _initParams;
   private ClassLoader _classLoader;
   private boolean _contextPathDefault;
   private String _defaultRequestCharacterEncoding;
   private String _defaultResponseCharacterEncoding;
   private String _contextPath;
   private String _contextPathEncoded;
   private String _displayName;
   private long _stopTimeout;
   private Resource _baseResource;
   private MimeTypes _mimeTypes;
   private Map _localeEncodingMap;
   private String[] _welcomeFiles;
   private ErrorHandler _errorHandler;
   private String[] _vhosts;
   private boolean[] _vhostswildcard;
   private String[] _vconnectors;
   private Logger _logger;
   private boolean _allowNullPathInfo;
   private int _maxFormKeys;
   private int _maxFormContentSize;
   private boolean _compactPath;
   private boolean _usingSecurityManager;
   private final List _programmaticListeners;
   private final List _servletContextListeners;
   private final List _destroyServletContextListeners;
   private final List _servletContextAttributeListeners;
   private final List _servletRequestListeners;
   private final List _servletRequestAttributeListeners;
   private final List _contextListeners;
   private final Set _durableListeners;
   private Index _protectedTargets;
   private final List _aliasChecks;
   private final AtomicReference _availability;

   public static Context getCurrentContext() {
      return (Context)__context.get();
   }

   public static ContextHandler getContextHandler(ServletContext context) {
      if (context instanceof Context) {
         return ((Context)context).getContextHandler();
      } else {
         Context c = getCurrentContext();
         return c != null ? c.getContextHandler() : null;
      }
   }

   public static String getServerInfo() {
      return __serverInfo;
   }

   public static void setServerInfo(String serverInfo) {
      __serverInfo = serverInfo;
   }

   public ContextHandler() {
      this((Context)null, (HandlerContainer)null, (String)null);
   }

   protected ContextHandler(Context context) {
      this(context, (HandlerContainer)null, (String)null);
   }

   public ContextHandler(String contextPath) {
      this((Context)null, (HandlerContainer)null, contextPath);
   }

   public ContextHandler(HandlerContainer parent, String contextPath) {
      this((Context)null, parent, contextPath);
   }

   protected ContextHandler(Context context, HandlerContainer parent, String contextPath) {
      this._contextStatus = ContextHandler.ContextStatus.NOTSET;
      this._contextPathDefault = true;
      this._contextPath = "/";
      this._contextPathEncoded = "/";
      this._maxFormKeys = Integer.getInteger("org.sparkproject.jetty.server.Request.maxFormKeys", 1000);
      this._maxFormContentSize = Integer.getInteger("org.sparkproject.jetty.server.Request.maxFormContentSize", 200000);
      this._compactPath = false;
      this._usingSecurityManager = getSecurityManager() != null;
      this._programmaticListeners = new CopyOnWriteArrayList();
      this._servletContextListeners = new CopyOnWriteArrayList();
      this._destroyServletContextListeners = new ArrayList();
      this._servletContextAttributeListeners = new CopyOnWriteArrayList();
      this._servletRequestListeners = new CopyOnWriteArrayList();
      this._servletRequestAttributeListeners = new CopyOnWriteArrayList();
      this._contextListeners = new CopyOnWriteArrayList();
      this._durableListeners = new HashSet();
      this._protectedTargets = Index.empty(false);
      this._aliasChecks = new CopyOnWriteArrayList();
      this._availability = new AtomicReference(ContextHandler.Availability.STOPPED);
      this._scontext = context == null ? new Context() : context;
      this._attributes = new AttributesMap();
      this._initParams = new HashMap();
      if (File.separatorChar == '/') {
         this.addAliasCheck(new SymlinkAllowedResourceAliasChecker(this));
      }

      if (contextPath != null) {
         this.setContextPath(contextPath);
      }

      if (parent instanceof HandlerWrapper) {
         ((HandlerWrapper)parent).setHandler(this);
      } else if (parent instanceof HandlerCollection) {
         ((HandlerCollection)parent).addHandler(this);
      }

   }

   public void dump(Appendable out, String indent) throws IOException {
      this.dumpObjects(out, indent, new Object[]{new ClassLoaderDump(this.getClassLoader()), new DumpableCollection("handler attributes " + String.valueOf(this), ((AttributesMap)this.getAttributes()).getAttributeEntrySet()), new DumpableCollection("context attributes " + String.valueOf(this), this.getServletContext().getAttributeEntrySet()), new DumpableCollection("initparams " + String.valueOf(this), this.getInitParams().entrySet())});
   }

   public Context getServletContext() {
      return this._scontext;
   }

   @ManagedAttribute("Checks if the /context is not redirected to /context/")
   public boolean getAllowNullPathInfo() {
      return this._allowNullPathInfo;
   }

   public void setAllowNullPathInfo(boolean allowNullPathInfo) {
      this._allowNullPathInfo = allowNullPathInfo;
   }

   public void setServer(Server server) {
      super.setServer(server);
      if (this._errorHandler != null) {
         this._errorHandler.setServer(server);
      }

   }

   public boolean isUsingSecurityManager() {
      return this._usingSecurityManager;
   }

   public void setUsingSecurityManager(boolean usingSecurityManager) {
      if (usingSecurityManager && getSecurityManager() == null) {
         throw new IllegalStateException("No security manager");
      } else {
         this._usingSecurityManager = usingSecurityManager;
      }
   }

   public void setVirtualHosts(String[] vhosts) {
      if (vhosts == null) {
         this._vhosts = vhosts;
      } else {
         boolean hostMatch = false;
         boolean connectorHostMatch = false;
         this._vhosts = new String[vhosts.length];
         this._vconnectors = new String[vhosts.length];
         this._vhostswildcard = new boolean[vhosts.length];
         ArrayList<Integer> connectorOnlyIndexes = null;

         for(int i = 0; i < vhosts.length; ++i) {
            boolean connectorMatch = false;
            this._vhosts[i] = vhosts[i];
            if (vhosts[i] != null) {
               int connectorIndex = this._vhosts[i].indexOf(64);
               if (connectorIndex >= 0) {
                  connectorMatch = true;
                  this._vconnectors[i] = this._vhosts[i].substring(connectorIndex + 1);
                  this._vhosts[i] = this._vhosts[i].substring(0, connectorIndex);
                  if (connectorIndex == 0) {
                     if (connectorOnlyIndexes == null) {
                        connectorOnlyIndexes = new ArrayList();
                     }

                     connectorOnlyIndexes.add(i);
                  }
               }

               if (this._vhosts[i].startsWith("*.")) {
                  this._vhosts[i] = this._vhosts[i].substring(1);
                  this._vhostswildcard[i] = true;
               }

               if (this._vhosts[i].isEmpty()) {
                  this._vhosts[i] = null;
               } else {
                  hostMatch = true;
                  connectorHostMatch = connectorHostMatch || connectorMatch;
               }

               this._vhosts[i] = this.normalizeHostname(this._vhosts[i]);
            }
         }

         if (connectorOnlyIndexes != null && hostMatch && !connectorHostMatch) {
            LOG.warn("ContextHandler {} has a connector only entry e.g. \"@connector\" and one or more host only entries. \nThe host entries will be ignored to match legacy behavior.  To clear this warning remove the host entries or update to us at least one host@connector syntax entry that will match a host for an specific connector", Arrays.asList(vhosts));
            String[] filteredHosts = new String[connectorOnlyIndexes.size()];

            for(int i = 0; i < connectorOnlyIndexes.size(); ++i) {
               filteredHosts[i] = vhosts[(Integer)connectorOnlyIndexes.get(i)];
            }

            this.setVirtualHosts(filteredHosts);
         }
      }

   }

   public void addVirtualHosts(String[] virtualHosts) {
      if (virtualHosts != null && virtualHosts.length != 0) {
         if (this._vhosts == null) {
            this.setVirtualHosts(virtualHosts);
         } else {
            Set<String> currentVirtualHosts = new HashSet(Arrays.asList(this.getVirtualHosts()));

            for(String vh : virtualHosts) {
               currentVirtualHosts.add(this.normalizeHostname(vh));
            }

            this.setVirtualHosts((String[])currentVirtualHosts.toArray(new String[0]));
         }

      }
   }

   public void removeVirtualHosts(String[] virtualHosts) {
      if (virtualHosts != null && virtualHosts.length != 0 && this._vhosts != null && this._vhosts.length != 0) {
         Set<String> existingVirtualHosts = new HashSet(Arrays.asList(this.getVirtualHosts()));

         for(String vh : virtualHosts) {
            existingVirtualHosts.remove(this.normalizeHostname(vh));
         }

         if (existingVirtualHosts.isEmpty()) {
            this.setVirtualHosts((String[])null);
         } else {
            this.setVirtualHosts((String[])existingVirtualHosts.toArray(new String[0]));
         }

      }
   }

   @ManagedAttribute(
      value = "Virtual hosts accepted by the context",
      readonly = true
   )
   public String[] getVirtualHosts() {
      if (this._vhosts == null) {
         return null;
      } else {
         String[] vhosts = new String[this._vhosts.length];

         for(int i = 0; i < this._vhosts.length; ++i) {
            StringBuilder sb = new StringBuilder();
            if (this._vhostswildcard[i]) {
               sb.append("*");
            }

            if (this._vhosts[i] != null) {
               sb.append(this._vhosts[i]);
            }

            if (this._vconnectors[i] != null) {
               sb.append("@").append(this._vconnectors[i]);
            }

            vhosts[i] = sb.toString();
         }

         return vhosts;
      }
   }

   public Object getAttribute(String name) {
      return this._attributes.getAttribute(name);
   }

   public Enumeration getAttributeNames() {
      return AttributesMap.getAttributeNamesCopy(this._attributes);
   }

   public Set getAttributeNameSet() {
      return this._attributes.getAttributeNameSet();
   }

   public Attributes getAttributes() {
      return this._attributes;
   }

   public ClassLoader getClassLoader() {
      return this._classLoader;
   }

   @ManagedAttribute("The file classpath")
   public String getClassPath() {
      if (this._classLoader != null && this._classLoader instanceof URLClassLoader) {
         URLClassLoader loader = (URLClassLoader)this._classLoader;
         URL[] urls = loader.getURLs();
         StringBuilder classpath = new StringBuilder();

         for(int i = 0; i < urls.length; ++i) {
            URL url = urls[i];

            try {
               Resource resource = this.newResource(url);
               File file = resource.getFile();
               if (file != null && file.exists()) {
                  if (classpath.length() > 0) {
                     classpath.append(File.pathSeparatorChar);
                  }

                  classpath.append(file.getAbsolutePath());
               }
            } catch (IOException e) {
               LOG.debug("Could not found resource: {}", url, e);
            }
         }

         return classpath.length() == 0 ? null : classpath.toString();
      } else {
         return null;
      }
   }

   @ManagedAttribute("True if URLs are compacted to replace the multiple '/'s with a single '/'")
   public String getContextPath() {
      return this._contextPath;
   }

   public String getContextPathEncoded() {
      return this._contextPathEncoded;
   }

   public String getRequestContextPath() {
      String contextPathEncoded = this.getContextPathEncoded();
      return "/".equals(contextPathEncoded) ? "" : contextPathEncoded;
   }

   public String getInitParameter(String name) {
      return (String)this._initParams.get(name);
   }

   public String setInitParameter(String name, String value) {
      return (String)this._initParams.put(name, value);
   }

   public Enumeration getInitParameterNames() {
      return Collections.enumeration(this._initParams.keySet());
   }

   @ManagedAttribute("Initial Parameter map for the context")
   public Map getInitParams() {
      return this._initParams;
   }

   @ManagedAttribute(
      value = "Display name of the Context",
      readonly = true
   )
   public String getDisplayName() {
      return this._displayName;
   }

   public boolean addEventListener(EventListener listener) {
      if (super.addEventListener(listener)) {
         if (listener instanceof ContextScopeListener) {
            this._contextListeners.add((ContextScopeListener)listener);
            if (__context.get() != null) {
               ((ContextScopeListener)listener).enterScope((Context)__context.get(), (Request)null, "Listener registered");
            }
         }

         if (listener instanceof ServletContextListener) {
            if (this._contextStatus == ContextHandler.ContextStatus.INITIALIZED) {
               ServletContextListener scl = (ServletContextListener)listener;
               this._destroyServletContextListeners.add(scl);
               if (this.isStarting()) {
                  LOG.warn("ContextListener {} added whilst starting {}", scl, this);
                  this.callContextInitialized(scl, new ServletContextEvent(this._scontext));
               } else {
                  LOG.warn("ContextListener {} added after starting {}", scl, this);
               }
            }

            this._servletContextListeners.add((ServletContextListener)listener);
         }

         if (listener instanceof ServletContextAttributeListener) {
            this._servletContextAttributeListeners.add((ServletContextAttributeListener)listener);
         }

         if (listener instanceof ServletRequestListener) {
            this._servletRequestListeners.add((ServletRequestListener)listener);
         }

         if (listener instanceof ServletRequestAttributeListener) {
            this._servletRequestAttributeListeners.add((ServletRequestAttributeListener)listener);
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean removeEventListener(EventListener listener) {
      if (super.removeEventListener(listener)) {
         if (listener instanceof ContextScopeListener) {
            this._contextListeners.remove(listener);
         }

         if (listener instanceof ServletContextListener) {
            this._servletContextListeners.remove(listener);
            this._destroyServletContextListeners.remove(listener);
         }

         if (listener instanceof ServletContextAttributeListener) {
            this._servletContextAttributeListeners.remove(listener);
         }

         if (listener instanceof ServletRequestListener) {
            this._servletRequestListeners.remove(listener);
         }

         if (listener instanceof ServletRequestAttributeListener) {
            this._servletRequestAttributeListeners.remove(listener);
         }

         return true;
      } else {
         return false;
      }
   }

   protected void addProgrammaticListener(EventListener listener) {
      this._programmaticListeners.add(listener);
   }

   public boolean isProgrammaticListener(EventListener listener) {
      return this._programmaticListeners.contains(listener);
   }

   public boolean isDurableListener(EventListener listener) {
      return this.isStarted() ? this._durableListeners.contains(listener) : this.getEventListeners().contains(listener);
   }

   @ManagedAttribute("true for graceful shutdown, which allows existing requests to complete")
   public boolean isShutdown() {
      return this._availability.get() == ContextHandler.Availability.SHUTDOWN;
   }

   public CompletableFuture shutdown() {
      while(true) {
         Availability availability = (Availability)this._availability.get();
         switch (availability.ordinal()) {
            case 0:
               return CompletableFuture.failedFuture(new IllegalStateException(this.getState()));
            case 1:
            case 2:
            case 3:
               if (!this._availability.compareAndSet(availability, ContextHandler.Availability.SHUTDOWN)) {
                  break;
               }
            default:
               return CompletableFuture.completedFuture((Object)null);
         }
      }
   }

   public boolean isAvailable() {
      return this._availability.get() == ContextHandler.Availability.AVAILABLE;
   }

   public void setAvailable(boolean available) {
      if (available) {
         while(true) {
            Availability availability = (Availability)this._availability.get();
            switch (availability.ordinal()) {
               case 2:
                  return;
               case 3:
                  if (this._availability.compareAndSet(availability, ContextHandler.Availability.AVAILABLE)) {
                     return;
                  }
                  break;
               default:
                  throw new IllegalStateException(availability.toString());
            }
         }
      } else {
         while(true) {
            Availability availability = (Availability)this._availability.get();
            switch (availability.ordinal()) {
               case 1:
               case 2:
                  if (this._availability.compareAndSet(availability, ContextHandler.Availability.UNAVAILABLE)) {
                     return;
                  }
                  break;
               default:
                  return;
            }
         }
      }
   }

   public Logger getLogger() {
      return this._logger;
   }

   public void setLogger(Logger logger) {
      this._logger = logger;
   }

   protected void doStart() throws Exception {
      if (this._contextPath == null) {
         throw new IllegalStateException("Null contextPath");
      } else {
         if (this.getBaseResource() != null && this.getBaseResource().isAlias()) {
            this._baseResource = Resource.resolveAlias(this._baseResource);
            LOG.warn("BaseResource {} is aliased to {} in {}. May not be supported in future releases.", new Object[]{this.getBaseResource(), this.getBaseResource().getAlias(), this});
         }

         this._availability.set(ContextHandler.Availability.STARTING);
         if (this._logger == null) {
            String var10001 = ContextHandler.class.getName();
            this._logger = LoggerFactory.getLogger(var10001 + this.getLogNameSuffix());
         }

         ClassLoader oldClassloader = null;
         Thread currentThread = null;
         Context oldContext = null;
         this._attributes.setAttribute("org.sparkproject.jetty.server.Executor", this.getServer().getThreadPool());
         if (this._mimeTypes == null) {
            this._mimeTypes = new MimeTypes();
         }

         this._durableListeners.addAll(this.getEventListeners());

         try {
            if (this._classLoader != null) {
               currentThread = Thread.currentThread();
               oldClassloader = currentThread.getContextClassLoader();
               currentThread.setContextClassLoader(this._classLoader);
            }

            oldContext = (Context)__context.get();
            __context.set(this._scontext);
            this.enterScope((Request)null, this.getState());
            this.startContext();
            this.contextInitialized();
            this._availability.compareAndSet(ContextHandler.Availability.STARTING, ContextHandler.Availability.AVAILABLE);
            LOG.info("Started {}", this);
         } finally {
            this._availability.compareAndSet(ContextHandler.Availability.STARTING, ContextHandler.Availability.UNAVAILABLE);
            this.exitScope((Request)null);
            __context.set(oldContext);
            if (this._classLoader != null && currentThread != null) {
               currentThread.setContextClassLoader(oldClassloader);
            }

         }

      }
   }

   private String getLogNameSuffix() {
      String logName = this.getDisplayName();
      if (StringUtil.isBlank(logName)) {
         logName = this.getContextPath();
         if (logName != null && logName.startsWith("/")) {
            logName = logName.substring(1);
         }

         if (StringUtil.isBlank(logName)) {
            logName = "ROOT";
         }
      }

      return "." + logName.replaceAll("\\W", "_");
   }

   protected void startContext() throws Exception {
      String managedAttributes = (String)this._initParams.get("org.sparkproject.jetty.server.context.ManagedAttributes");
      if (managedAttributes != null) {
         this.addEventListener(new ManagedAttributeListener(this, StringUtil.csvSplit(managedAttributes)));
      }

      super.doStart();
   }

   public void contextInitialized() throws Exception {
      if (this._contextStatus == ContextHandler.ContextStatus.NOTSET) {
         this._contextStatus = ContextHandler.ContextStatus.INITIALIZED;
         this._destroyServletContextListeners.clear();
         if (!this._servletContextListeners.isEmpty()) {
            ServletContextEvent event = new ServletContextEvent(this._scontext);

            for(ServletContextListener listener : this._servletContextListeners) {
               this.callContextInitialized(listener, event);
               this._destroyServletContextListeners.add(listener);
            }
         }
      }

   }

   public void contextDestroyed() throws Exception {
      switch (this._contextStatus.ordinal()) {
         case 1:
            try {
               MultiException ex = new MultiException();
               ServletContextEvent event = new ServletContextEvent(this._scontext);
               Collections.reverse(this._destroyServletContextListeners);

               for(ServletContextListener listener : this._destroyServletContextListeners) {
                  try {
                     this.callContextDestroyed(listener, event);
                  } catch (Exception x) {
                     ex.add(x);
                  }
               }

               ex.ifExceptionThrow();
            } finally {
               this._contextStatus = ContextHandler.ContextStatus.DESTROYED;
            }
         default:
      }
   }

   protected void stopContext() throws Exception {
      super.doStop();
   }

   protected void callContextInitialized(ServletContextListener l, ServletContextEvent e) {
      if (!this.getServer().isDryRun()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("contextInitialized: {}->{}", e, l);
         }

         l.contextInitialized(e);
      }
   }

   protected void callContextDestroyed(ServletContextListener l, ServletContextEvent e) {
      if (!this.getServer().isDryRun()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("contextDestroyed: {}->{}", e, l);
         }

         l.contextDestroyed(e);
      }
   }

   protected void doStop() throws Exception {
      MultiException mex = null;
      this._availability.set(ContextHandler.Availability.STOPPED);
      ClassLoader oldClassloader = null;
      ClassLoader oldWebapploader = null;
      Thread currentThread = null;
      Context oldContext = (Context)__context.get();
      this.enterScope((Request)null, "doStop");
      __context.set(this._scontext);

      try {
         if (this._classLoader != null) {
            oldWebapploader = this._classLoader;
            currentThread = Thread.currentThread();
            oldClassloader = currentThread.getContextClassLoader();
            currentThread.setContextClassLoader(this._classLoader);
         }

         this.stopContext();
         this.contextDestroyed();
         this.setEventListeners(this._durableListeners);
         this._durableListeners.clear();
         if (this._errorHandler != null) {
            this._errorHandler.stop();
         }

         for(EventListener l : this._programmaticListeners) {
            this.removeEventListener(l);
            if (l instanceof ContextScopeListener) {
               try {
                  ((ContextScopeListener)l).exitScope(this._scontext, (Request)null);
               } catch (Throwable e) {
                  LOG.warn("Unable to exit scope", e);
               }
            }
         }

         this._programmaticListeners.clear();
      } catch (Throwable x) {
         if (mex == null) {
            mex = new MultiException();
         }

         mex.add(x);
      } finally {
         this._contextStatus = ContextHandler.ContextStatus.NOTSET;
         __context.set(oldContext);
         this.exitScope((Request)null);
         LOG.info("Stopped {}", this);
         if ((oldClassloader == null || oldClassloader != oldWebapploader) && currentThread != null) {
            currentThread.setContextClassLoader(oldClassloader);
         }

         this._scontext.clearAttributes();
      }

      if (mex != null) {
         mex.ifExceptionThrow();
      }

   }

   public boolean checkVirtualHost(Request baseRequest) {
      if (this._vhosts != null && this._vhosts.length != 0) {
         String vhost = this.normalizeHostname(baseRequest.getServerName());
         String connectorName = baseRequest.getHttpChannel().getConnector().getName();

         for(int i = 0; i < this._vhosts.length; ++i) {
            String contextVhost = this._vhosts[i];
            String contextVConnector = this._vconnectors[i];
            if (contextVConnector != null) {
               if (!contextVConnector.equalsIgnoreCase(connectorName)) {
                  continue;
               }

               if (contextVhost == null) {
                  return true;
               }
            }

            if (contextVhost != null) {
               if (this._vhostswildcard[i]) {
                  int index = vhost.indexOf(".");
                  if (index >= 0 && vhost.substring(index).equalsIgnoreCase(contextVhost)) {
                     return true;
                  }
               } else if (vhost.equalsIgnoreCase(contextVhost)) {
                  return true;
               }
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public boolean checkContextPath(String uri) {
      if (this._contextPath.length() > 1) {
         if (!uri.startsWith(this._contextPath)) {
            return false;
         }

         if (uri.length() > this._contextPath.length() && uri.charAt(this._contextPath.length()) != '/') {
            return false;
         }
      }

      return true;
   }

   public boolean checkContext(String target, Request baseRequest, HttpServletResponse response) throws IOException {
      DispatcherType dispatch = baseRequest.getDispatcherType();
      if (!this.checkVirtualHost(baseRequest)) {
         return false;
      } else if (!this.checkContextPath(target)) {
         return false;
      } else if (!this._allowNullPathInfo && this._contextPath.length() == target.length() && this._contextPath.length() > 1) {
         baseRequest.setHandled(true);
         String queryString = baseRequest.getQueryString();
         if (baseRequest.getContentType() != null || baseRequest.getContentLengthLong() > 0L) {
            response.setHeader(HttpHeader.CONNECTION.asString(), HttpHeaderValue.CLOSE.asString());
         }

         String var10001 = baseRequest.getRequestURI();
         response.sendRedirect(var10001 + (queryString == null ? "/" : "/?" + queryString));
         return false;
      } else {
         switch (((Availability)this._availability.get()).ordinal()) {
            case 0:
               return false;
            case 1:
            case 2:
            default:
               if (DispatcherType.REQUEST.equals(dispatch) && baseRequest.isHandled()) {
                  return false;
               }

               return true;
            case 3:
            case 4:
               baseRequest.setHandled(true);
               response.sendError(503);
               return false;
         }
      }
   }

   public void doScope(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      if (LOG.isDebugEnabled()) {
         LOG.debug("scope {}|{}|{} @ {}", new Object[]{baseRequest.getContextPath(), baseRequest.getServletPath(), baseRequest.getPathInfo(), this});
      }

      Thread currentThread = Thread.currentThread();
      ClassLoader oldClassloader = currentThread.getContextClassLoader();
      String oldPathInContext = baseRequest.getPathInContext();
      String pathInContext = target;
      DispatcherType dispatch = baseRequest.getDispatcherType();
      Context oldContext = baseRequest.getContext();
      if (oldContext != this._scontext && (DispatcherType.REQUEST.equals(dispatch) || DispatcherType.ASYNC.equals(dispatch))) {
         if (this.isCompactPath()) {
            target = URIUtil.compactPath(target);
         }

         if (!this.checkContext(target, baseRequest, response)) {
            return;
         }

         if (target.length() > this._contextPath.length()) {
            if (this._contextPath.length() > 1) {
               target = target.substring(this._contextPath.length());
            }

            pathInContext = target;
         } else if (this._contextPath.length() == 1) {
            target = "/";
            pathInContext = "/";
         } else {
            target = "/";
            pathInContext = null;
         }
      }

      if (this._classLoader != null) {
         currentThread.setContextClassLoader(this._classLoader);
      }

      try {
         baseRequest.setContext(this._scontext, !DispatcherType.INCLUDE.equals(dispatch) && target.startsWith("/") ? pathInContext : oldPathInContext);
         if (oldContext != this._scontext) {
            __context.set(this._scontext);
            this.enterScope(baseRequest, dispatch);
         }

         if (LOG.isDebugEnabled()) {
            LOG.debug("context={}|{}|{} @ {}", new Object[]{baseRequest.getContextPath(), baseRequest.getServletPath(), baseRequest.getPathInfo(), this});
         }

         this.nextScope(target, baseRequest, request, response);
      } finally {
         if (oldContext != this._scontext) {
            this.exitScope(baseRequest);
            if (this._classLoader != null) {
               currentThread.setContextClassLoader(oldClassloader);
            }

            __context.set(oldContext);
         }

         baseRequest.setContext(oldContext, oldPathInContext);
      }

   }

   protected void requestInitialized(Request baseRequest, HttpServletRequest request) {
      if (!this._servletRequestAttributeListeners.isEmpty()) {
         for(ServletRequestAttributeListener l : this._servletRequestAttributeListeners) {
            baseRequest.addEventListener(l);
         }
      }

      if (!this._servletRequestListeners.isEmpty()) {
         ServletRequestEvent sre = new ServletRequestEvent(this._scontext, request);

         for(ServletRequestListener l : this._servletRequestListeners) {
            l.requestInitialized(sre);
         }
      }

   }

   protected void requestDestroyed(Request baseRequest, HttpServletRequest request) {
      if (!this._servletRequestListeners.isEmpty()) {
         ServletRequestEvent sre = new ServletRequestEvent(this._scontext, request);
         int i = this._servletRequestListeners.size();

         while(i-- > 0) {
            ((ServletRequestListener)this._servletRequestListeners.get(i)).requestDestroyed(sre);
         }
      }

      if (!this._servletRequestAttributeListeners.isEmpty()) {
         int i = this._servletRequestAttributeListeners.size();

         while(i-- > 0) {
            baseRequest.removeEventListener((EventListener)this._servletRequestAttributeListeners.get(i));
         }
      }

   }

   public void doHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      DispatcherType dispatch = baseRequest.getDispatcherType();
      boolean new_context = baseRequest.takeNewContext();

      try {
         if (new_context) {
            this.requestInitialized(baseRequest, request);
         }

         if (dispatch != DispatcherType.REQUEST || !this.isProtectedTarget(target)) {
            this.nextHandle(target, baseRequest, request, response);
            return;
         }

         baseRequest.setHandled(true);
         response.sendError(404);
      } finally {
         if (new_context) {
            this.requestDestroyed(baseRequest, request);
         }

      }

   }

   protected void enterScope(Request request, Object reason) {
      if (!this._contextListeners.isEmpty()) {
         for(ContextScopeListener listener : this._contextListeners) {
            try {
               listener.enterScope(this._scontext, request, reason);
            } catch (Throwable e) {
               LOG.warn("Unable to enter scope", e);
            }
         }
      }

   }

   protected void exitScope(Request request) {
      if (!this._contextListeners.isEmpty()) {
         int i = this._contextListeners.size();

         while(i-- > 0) {
            try {
               ((ContextScopeListener)this._contextListeners.get(i)).exitScope(this._scontext, request);
            } catch (Throwable e) {
               LOG.warn("Unable to exit scope", e);
            }
         }
      }

   }

   public void handle(Request request, Runnable runnable) {
      ClassLoader oldClassloader = null;
      Thread currentThread = null;
      Context oldContext = (Context)__context.get();
      if (oldContext == this._scontext) {
         runnable.run();
      } else {
         try {
            __context.set(this._scontext);
            if (this._classLoader != null) {
               currentThread = Thread.currentThread();
               oldClassloader = currentThread.getContextClassLoader();
               currentThread.setContextClassLoader(this._classLoader);
            }

            this.enterScope(request, runnable);
            runnable.run();
         } finally {
            this.exitScope(request);
            __context.set(oldContext);
            if (oldClassloader != null) {
               currentThread.setContextClassLoader(oldClassloader);
            }

         }

      }
   }

   public void handle(Runnable runnable) {
      this.handle((Request)null, runnable);
   }

   public boolean isProtectedTarget(String target) {
      if (target != null && !this._protectedTargets.isEmpty()) {
         if (target.startsWith("//")) {
            target = URIUtil.compactPath(target);
         }

         ProtectedTargetType type = (ProtectedTargetType)this._protectedTargets.getBest(target);
         return type == ContextHandler.ProtectedTargetType.PREFIX || type == ContextHandler.ProtectedTargetType.EXACT && this._protectedTargets.get(target) == ContextHandler.ProtectedTargetType.EXACT;
      } else {
         return false;
      }
   }

   public void setProtectedTargets(String[] targets) {
      Index.Builder<ProtectedTargetType> builder = new Index.Builder();
      if (targets != null) {
         for(String t : targets) {
            if (!t.startsWith("/")) {
               throw new IllegalArgumentException("Bad protected target: " + t);
            }

            builder.with(t, ContextHandler.ProtectedTargetType.EXACT);
            builder.with(t + "/", ContextHandler.ProtectedTargetType.PREFIX);
            builder.with(t + "?", ContextHandler.ProtectedTargetType.PREFIX);
            builder.with(t + "#", ContextHandler.ProtectedTargetType.PREFIX);
            builder.with(t + ";", ContextHandler.ProtectedTargetType.PREFIX);
         }
      }

      this._protectedTargets = builder.caseSensitive(false).build();
   }

   public String[] getProtectedTargets() {
      return this._protectedTargets == null ? null : (String[])this._protectedTargets.keySet().stream().filter((s) -> this._protectedTargets.get(s) == ContextHandler.ProtectedTargetType.EXACT).toArray((x$0) -> new String[x$0]);
   }

   public void removeAttribute(String name) {
      this._attributes.removeAttribute(name);
   }

   public void setAttribute(String name, Object value) {
      this._attributes.setAttribute(name, value);
   }

   public void setAttributes(Attributes attributes) {
      this._attributes.clearAttributes();
      this._attributes.addAll(attributes);
   }

   public void clearAttributes() {
      this._attributes.clearAttributes();
   }

   public void setClassLoader(ClassLoader classLoader) {
      if (this.isStarted()) {
         throw new IllegalStateException(this.getState());
      } else {
         this._classLoader = classLoader;
      }
   }

   public void setDefaultContextPath(String contextPath) {
      this.setContextPath(contextPath);
      this._contextPathDefault = true;
   }

   public void setDefaultRequestCharacterEncoding(String encoding) {
      this._defaultRequestCharacterEncoding = encoding;
   }

   public String getDefaultRequestCharacterEncoding() {
      return this._defaultRequestCharacterEncoding;
   }

   public void setDefaultResponseCharacterEncoding(String encoding) {
      this._defaultResponseCharacterEncoding = encoding;
   }

   public String getDefaultResponseCharacterEncoding() {
      return this._defaultResponseCharacterEncoding;
   }

   public boolean isContextPathDefault() {
      return this._contextPathDefault;
   }

   public void setContextPath(String contextPath) {
      if (contextPath == null) {
         throw new IllegalArgumentException("null contextPath");
      } else {
         if (contextPath.endsWith("/*")) {
            LOG.warn("{} contextPath ends with /*", this);
            contextPath = contextPath.substring(0, contextPath.length() - 2);
         } else if (contextPath.length() > 1 && contextPath.endsWith("/")) {
            LOG.warn("{} contextPath ends with /", this);
            contextPath = contextPath.substring(0, contextPath.length() - 1);
         }

         if (contextPath.length() == 0) {
            LOG.warn("Empty contextPath");
            contextPath = "/";
         }

         this._contextPath = contextPath;
         this._contextPathEncoded = URIUtil.encodePath(contextPath);
         this._contextPathDefault = false;
         if (this.getServer() != null && (this.getServer().isStarting() || this.getServer().isStarted())) {
            Class<ContextHandlerCollection> handlerClass = ContextHandlerCollection.class;
            Handler[] contextCollections = this.getServer().getChildHandlersByClass(handlerClass);
            if (contextCollections != null) {
               for(Handler contextCollection : contextCollections) {
                  ((ContextHandlerCollection)handlerClass.cast(contextCollection)).mapContexts();
               }
            }
         }

      }
   }

   public void setDisplayName(String servletContextName) {
      this._displayName = servletContextName;
   }

   public Resource getBaseResource() {
      return this._baseResource == null ? null : this._baseResource;
   }

   @ManagedAttribute("document root for context")
   public String getResourceBase() {
      return this._baseResource == null ? null : this._baseResource.toString();
   }

   public void setBaseResource(Resource base) {
      if (this.isStarted()) {
         throw new IllegalStateException("Cannot call setBaseResource after starting");
      } else {
         this._baseResource = base;
      }
   }

   public void setResourceBase(String resourceBase) {
      try {
         this.setBaseResource(this.newResource(resourceBase));
      } catch (Exception e) {
         if (LOG.isDebugEnabled()) {
            LOG.warn("Unable to set baseResource: {}", resourceBase, e);
         } else {
            LOG.warn(e.toString());
         }

         throw new IllegalArgumentException(resourceBase);
      }
   }

   public MimeTypes getMimeTypes() {
      if (this._mimeTypes == null) {
         this._mimeTypes = new MimeTypes();
      }

      return this._mimeTypes;
   }

   public void setMimeTypes(MimeTypes mimeTypes) {
      this._mimeTypes = mimeTypes;
   }

   public void setWelcomeFiles(String[] files) {
      this._welcomeFiles = files;
   }

   @ManagedAttribute(
      value = "Partial URIs of directory welcome files",
      readonly = true
   )
   public String[] getWelcomeFiles() {
      return this._welcomeFiles;
   }

   @ManagedAttribute("The error handler to use for the context")
   public ErrorHandler getErrorHandler() {
      return this._errorHandler;
   }

   public void setErrorHandler(ErrorHandler errorHandler) {
      if (errorHandler != null) {
         errorHandler.setServer(this.getServer());
      }

      this.updateBean(this._errorHandler, errorHandler, true);
      this._errorHandler = errorHandler;
   }

   @ManagedAttribute("The maximum content size")
   public int getMaxFormContentSize() {
      return this._maxFormContentSize;
   }

   public void setMaxFormContentSize(int maxSize) {
      this._maxFormContentSize = maxSize;
   }

   public int getMaxFormKeys() {
      return this._maxFormKeys;
   }

   public void setMaxFormKeys(int max) {
      this._maxFormKeys = max;
   }

   /** @deprecated */
   @Deprecated
   public boolean isCompactPath() {
      return this._compactPath;
   }

   /** @deprecated */
   @Deprecated
   public void setCompactPath(boolean compactPath) {
      this._compactPath = compactPath;
   }

   public String toString() {
      String[] vhosts = this.getVirtualHosts();
      StringBuilder b = new StringBuilder();
      Package pkg = this.getClass().getPackage();
      if (pkg != null) {
         String p = pkg.getName();
         if (p != null && p.length() > 0) {
            String[] ss = p.split("\\.");

            for(String s : ss) {
               b.append(s.charAt(0)).append('.');
            }
         }
      }

      b.append(this.getClass().getSimpleName()).append('@').append(Integer.toString(this.hashCode(), 16));
      b.append('{');
      if (this.getDisplayName() != null) {
         b.append(this.getDisplayName()).append(',');
      }

      b.append(this.getContextPath()).append(',').append(this.getBaseResource()).append(',').append(this._availability.get());
      if (vhosts != null && vhosts.length > 0) {
         b.append(',').append(vhosts[0]);
      }

      b.append('}');
      return b.toString();
   }

   public Class loadClass(String className) throws ClassNotFoundException {
      if (className == null) {
         return null;
      } else {
         return this._classLoader == null ? Loader.loadClass(className) : this._classLoader.loadClass(className);
      }
   }

   public void addLocaleEncoding(String locale, String encoding) {
      if (this._localeEncodingMap == null) {
         this._localeEncodingMap = new HashMap();
      }

      this._localeEncodingMap.put(locale, encoding);
   }

   public String getLocaleEncoding(String locale) {
      if (this._localeEncodingMap == null) {
         return null;
      } else {
         String encoding = (String)this._localeEncodingMap.get(locale);
         return encoding;
      }
   }

   public String getLocaleEncoding(Locale locale) {
      if (this._localeEncodingMap == null) {
         return null;
      } else {
         String encoding = (String)this._localeEncodingMap.get(locale.toString());
         if (encoding == null) {
            encoding = (String)this._localeEncodingMap.get(locale.getLanguage());
         }

         return encoding;
      }
   }

   public Map getLocaleEncodings() {
      return this._localeEncodingMap == null ? null : Collections.unmodifiableMap(this._localeEncodingMap);
   }

   public Resource getResource(String pathInContext) throws MalformedURLException {
      if (pathInContext != null && pathInContext.startsWith("/")) {
         if (this._baseResource == null) {
            return null;
         } else {
            try {
               Resource resource = this._baseResource.addPath(pathInContext);
               return this.checkAlias(pathInContext, resource) ? resource : null;
            } catch (Exception e) {
               LOG.trace("IGNORED", e);
               return null;
            }
         }
      } else {
         throw new MalformedURLException(pathInContext);
      }
   }

   public boolean checkAlias(String path, Resource resource) {
      if (resource.isAlias()) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Aliased resource: {}~={}", resource, resource.getAlias());
         }

         for(AliasCheck check : this._aliasChecks) {
            if (check.check(path, resource)) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Aliased resource: {} approved by {}", resource, check);
               }

               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public Resource newResource(URL url) throws IOException {
      return Resource.newResource(url);
   }

   public Resource newResource(URI uri) throws IOException {
      return Resource.newResource(uri);
   }

   public Resource newResource(String urlOrPath) throws IOException {
      return Resource.newResource(urlOrPath);
   }

   public Set getResourcePaths(String path) {
      try {
         Resource resource = this.getResource(path);
         if (resource != null && resource.exists()) {
            if (!path.endsWith("/")) {
               path = path + "/";
            }

            String[] l = resource.list();
            if (l != null) {
               HashSet<String> set = new HashSet();

               for(int i = 0; i < l.length; ++i) {
                  set.add(path + l[i]);
               }

               return set;
            }
         }
      } catch (Exception e) {
         LOG.trace("IGNORED", e);
      }

      return Collections.emptySet();
   }

   private String normalizeHostname(String host) {
      if (host == null) {
         return null;
      } else {
         int connectorIndex = host.indexOf(64);
         String connector = null;
         if (connectorIndex > 0) {
            host = host.substring(0, connectorIndex);
            connector = host.substring(connectorIndex);
         }

         if (host.endsWith(".")) {
            host = host.substring(0, host.length() - 1);
         }

         if (connector != null) {
            host = host + connector;
         }

         return host;
      }
   }

   public void addAliasCheck(AliasCheck check) {
      this._aliasChecks.add(check);
      if (check instanceof LifeCycle) {
         this.addManaged((LifeCycle)check);
      } else {
         this.addBean(check);
      }

   }

   public List getAliasChecks() {
      return Collections.unmodifiableList(this._aliasChecks);
   }

   public void setAliasChecks(List checks) {
      this.clearAliasChecks();
      checks.forEach(this::addAliasCheck);
   }

   public void clearAliasChecks() {
      this._aliasChecks.forEach(this::removeBean);
      this._aliasChecks.clear();
   }

   private static Object getSecurityManager() {
      return SecurityUtils.getSecurityManager();
   }

   public static enum ContextStatus {
      NOTSET,
      INITIALIZED,
      DESTROYED;

      // $FF: synthetic method
      private static ContextStatus[] $values() {
         return new ContextStatus[]{NOTSET, INITIALIZED, DESTROYED};
      }
   }

   private static enum ProtectedTargetType {
      EXACT,
      PREFIX;

      // $FF: synthetic method
      private static ProtectedTargetType[] $values() {
         return new ProtectedTargetType[]{EXACT, PREFIX};
      }
   }

   public static enum Availability {
      STOPPED,
      STARTING,
      AVAILABLE,
      UNAVAILABLE,
      SHUTDOWN;

      // $FF: synthetic method
      private static Availability[] $values() {
         return new Availability[]{STOPPED, STARTING, AVAILABLE, UNAVAILABLE, SHUTDOWN};
      }
   }

   public class Context extends StaticContext {
      protected boolean _enabled = true;
      protected boolean _extendedListenerTypes = false;

      protected Context() {
      }

      public ContextHandler getContextHandler() {
         return ContextHandler.this;
      }

      public ServletContext getContext(String uripath) {
         List<ContextHandler> contexts = new ArrayList();
         Handler[] handlers = ContextHandler.this.getServer().getChildHandlersByClass(ContextHandler.class);
         String matchedPath = null;

         for(Handler handler : handlers) {
            if (handler != null) {
               ContextHandler ch = (ContextHandler)handler;
               String contextPath = ch.getContextPath();
               if (uripath.equals(contextPath) || uripath.startsWith(contextPath) && uripath.charAt(contextPath.length()) == '/' || "/".equals(contextPath)) {
                  if (ContextHandler.this.getVirtualHosts() != null && ContextHandler.this.getVirtualHosts().length > 0) {
                     if (ch.getVirtualHosts() != null && ch.getVirtualHosts().length > 0) {
                        for(String h1 : ContextHandler.this.getVirtualHosts()) {
                           for(String h2 : ch.getVirtualHosts()) {
                              if (h1.equals(h2)) {
                                 if (matchedPath == null || contextPath.length() > matchedPath.length()) {
                                    contexts.clear();
                                    matchedPath = contextPath;
                                 }

                                 if (matchedPath.equals(contextPath)) {
                                    contexts.add(ch);
                                 }
                              }
                           }
                        }
                     }
                  } else {
                     if (matchedPath == null || contextPath.length() > matchedPath.length()) {
                        contexts.clear();
                        matchedPath = contextPath;
                     }

                     if (matchedPath.equals(contextPath)) {
                        contexts.add(ch);
                     }
                  }
               }
            }
         }

         if (contexts.size() > 0) {
            return ((ContextHandler)contexts.get(0))._scontext;
         } else {
            matchedPath = null;

            for(Handler handler : handlers) {
               if (handler != null) {
                  ContextHandler ch = (ContextHandler)handler;
                  String contextPath = ch.getContextPath();
                  if (uripath.equals(contextPath) || uripath.startsWith(contextPath) && uripath.charAt(contextPath.length()) == '/' || "/".equals(contextPath)) {
                     if (matchedPath == null || contextPath.length() > matchedPath.length()) {
                        contexts.clear();
                        matchedPath = contextPath;
                     }

                     if (matchedPath.equals(contextPath)) {
                        contexts.add(ch);
                     }
                  }
               }
            }

            if (contexts.size() > 0) {
               return ((ContextHandler)contexts.get(0))._scontext;
            } else {
               return null;
            }
         }
      }

      public String getMimeType(String file) {
         return ContextHandler.this._mimeTypes == null ? null : ContextHandler.this._mimeTypes.getMimeByExtension(file);
      }

      public RequestDispatcher getRequestDispatcher(String uriInContext) {
         if (uriInContext == null) {
            return null;
         } else if (!uriInContext.startsWith("/")) {
            return null;
         } else {
            try {
               String contextPath = this.getContextPath();
               HttpURI.Mutable uri = HttpURI.build(uriInContext);
               String pathInfo = uri.getDecodedPath();
               if (StringUtil.isEmpty(pathInfo)) {
                  return null;
               } else {
                  if (!StringUtil.isEmpty(contextPath)) {
                     uri.path(URIUtil.addPaths(contextPath, uri.getPath()));
                     pathInfo = uri.getDecodedPath().substring(contextPath.length());
                  }

                  return new Dispatcher(ContextHandler.this, uri, pathInfo);
               }
            } catch (Exception e) {
               ContextHandler.LOG.trace("IGNORED", e);
               return null;
            }
         }
      }

      public String getRealPath(String path) {
         path = URIUtil.canonicalPath(path);
         if (path == null) {
            return null;
         } else {
            if (path.length() == 0) {
               path = "/";
            } else if (path.charAt(0) != '/') {
               path = "/" + path;
            }

            try {
               Resource resource = ContextHandler.this.getResource(path);
               if (resource != null) {
                  File file = resource.getFile();
                  if (file != null) {
                     return file.getCanonicalPath();
                  }
               }
            } catch (Exception e) {
               ContextHandler.LOG.trace("IGNORED", e);
            }

            return null;
         }
      }

      public URL getResource(String path) throws MalformedURLException {
         path = URIUtil.canonicalPath(path);
         if (path == null) {
            return null;
         } else {
            Resource resource = ContextHandler.this.getResource(path);
            return resource != null && resource.exists() ? resource.getURI().toURL() : null;
         }
      }

      public InputStream getResourceAsStream(String path) {
         try {
            URL url = this.getResource(path);
            if (url == null) {
               return null;
            } else {
               Resource r = Resource.newResource(url);
               return r.isDirectory() ? null : r.getInputStream();
            }
         } catch (Exception e) {
            ContextHandler.LOG.trace("IGNORED", e);
            return null;
         }
      }

      public Set getResourcePaths(String path) {
         path = URIUtil.canonicalPath(path);
         return path == null ? null : ContextHandler.this.getResourcePaths(path);
      }

      public void log(Exception exception, String msg) {
         ContextHandler.this._logger.warn(msg, exception);
      }

      public void log(String msg) {
         ContextHandler.this._logger.info(msg);
      }

      public void log(String message, Throwable throwable) {
         if (throwable == null) {
            ContextHandler.this._logger.warn(message);
         } else {
            ContextHandler.this._logger.warn(message, throwable);
         }

      }

      public String getInitParameter(String name) {
         return ContextHandler.this.getInitParameter(name);
      }

      public Enumeration getInitParameterNames() {
         return ContextHandler.this.getInitParameterNames();
      }

      public Object getAttribute(String name) {
         Object o = ContextHandler.this.getAttribute(name);
         if (o == null) {
            o = super.getAttribute(name);
         }

         return o;
      }

      public Enumeration getAttributeNames() {
         HashSet<String> set = new HashSet();
         Enumeration<String> e = super.getAttributeNames();

         while(e.hasMoreElements()) {
            set.add((String)e.nextElement());
         }

         e = ContextHandler.this.getAttributeNames();

         while(e.hasMoreElements()) {
            set.add((String)e.nextElement());
         }

         return Collections.enumeration(set);
      }

      public void setAttribute(String name, Object value) {
         Object oldValue = super.getAttribute(name);
         if (value == null) {
            super.removeAttribute(name);
         } else {
            super.setAttribute(name, value);
         }

         if (!ContextHandler.this._servletContextAttributeListeners.isEmpty()) {
            ServletContextAttributeEvent event = new ServletContextAttributeEvent(ContextHandler.this._scontext, name, oldValue == null ? value : oldValue);

            for(ServletContextAttributeListener listener : ContextHandler.this._servletContextAttributeListeners) {
               if (oldValue == null) {
                  listener.attributeAdded(event);
               } else if (value == null) {
                  listener.attributeRemoved(event);
               } else {
                  listener.attributeReplaced(event);
               }
            }
         }

      }

      public void removeAttribute(String name) {
         Object oldValue = super.getAttribute(name);
         super.removeAttribute(name);
         if (oldValue != null && !ContextHandler.this._servletContextAttributeListeners.isEmpty()) {
            ServletContextAttributeEvent event = new ServletContextAttributeEvent(ContextHandler.this._scontext, name, oldValue);

            for(ServletContextAttributeListener listener : ContextHandler.this._servletContextAttributeListeners) {
               listener.attributeRemoved(event);
            }
         }

      }

      public String getServletContextName() {
         String name = ContextHandler.this.getDisplayName();
         if (name == null) {
            name = ContextHandler.this.getContextPath();
         }

         return name;
      }

      public String getContextPath() {
         return ContextHandler.this.getRequestContextPath();
      }

      public String toString() {
         return "ServletContext@" + ContextHandler.this.toString();
      }

      public boolean setInitParameter(String name, String value) {
         if (ContextHandler.this.getInitParameter(name) != null) {
            return false;
         } else {
            ContextHandler.this.getInitParams().put(name, value);
            return true;
         }
      }

      public void addListener(String className) {
         if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            try {
               Class<? extends EventListener> clazz = ContextHandler.this._classLoader == null ? Loader.loadClass(className) : ContextHandler.this._classLoader.loadClass(className);
               this.addListener(clazz);
            } catch (ClassNotFoundException e) {
               throw new IllegalArgumentException(e);
            }
         }
      }

      public void addListener(EventListener t) {
         if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            this.checkListener(t.getClass());
            ContextHandler.this.addEventListener(t);
            ContextHandler.this.addProgrammaticListener(t);
         }
      }

      public void addListener(Class listenerClass) {
         if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else {
            try {
               EventListener e = this.createListener(listenerClass);
               this.addListener(e);
            } catch (ServletException e) {
               throw new IllegalArgumentException(e);
            }
         }
      }

      public void checkListener(Class listener) throws IllegalStateException {
         boolean ok = false;
         int startIndex = this.isExtendedListenerTypes() ? 0 : 1;

         for(int i = startIndex; i < ContextHandler.SERVLET_LISTENER_TYPES.length; ++i) {
            if (ContextHandler.SERVLET_LISTENER_TYPES[i].isAssignableFrom(listener)) {
               ok = true;
               break;
            }
         }

         if (!ok) {
            throw new IllegalArgumentException("Inappropriate listener class " + listener.getName());
         }
      }

      public void setExtendedListenerTypes(boolean extended) {
         this._extendedListenerTypes = extended;
      }

      public boolean isExtendedListenerTypes() {
         return this._extendedListenerTypes;
      }

      public ClassLoader getClassLoader() {
         if (!this._enabled) {
            throw new UnsupportedOperationException();
         } else if (!ContextHandler.this.isUsingSecurityManager()) {
            return ContextHandler.this._classLoader;
         } else {
            for(ClassLoader callerLoader = StackWalker.getInstance(Option.RETAIN_CLASS_REFERENCE).getCallerClass().getClassLoader(); callerLoader != null; callerLoader = callerLoader.getParent()) {
               if (callerLoader == ContextHandler.this._classLoader) {
                  return ContextHandler.this._classLoader;
               }
            }

            SecurityUtils.checkPermission(new RuntimePermission("getClassLoader"));
            return ContextHandler.this._classLoader;
         }
      }

      public JspConfigDescriptor getJspConfigDescriptor() {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "getJspConfigDescriptor()");
         return null;
      }

      public void setJspConfigDescriptor(JspConfigDescriptor d) {
      }

      public void declareRoles(String... roleNames) {
         if (!ContextHandler.this.isStarting()) {
            throw new IllegalStateException();
         } else if (!this._enabled) {
            throw new UnsupportedOperationException();
         }
      }

      public void setEnabled(boolean enabled) {
         this._enabled = enabled;
      }

      public boolean isEnabled() {
         return this._enabled;
      }

      public String getVirtualServerName() {
         String[] hosts = ContextHandler.this.getVirtualHosts();
         return hosts != null && hosts.length > 0 ? hosts[0] : null;
      }
   }

   public static class StaticContext extends AttributesMap implements ServletContext {
      private int _effectiveMajorVersion = 5;
      private int _effectiveMinorVersion = 0;

      public ServletContext getContext(String uripath) {
         return null;
      }

      public int getMajorVersion() {
         return 5;
      }

      public String getMimeType(String file) {
         return null;
      }

      public int getMinorVersion() {
         return 0;
      }

      public RequestDispatcher getNamedDispatcher(String name) {
         return null;
      }

      public RequestDispatcher getRequestDispatcher(String uriInContext) {
         return null;
      }

      public String getRealPath(String path) {
         return null;
      }

      public URL getResource(String path) throws MalformedURLException {
         return null;
      }

      public InputStream getResourceAsStream(String path) {
         return null;
      }

      public Set getResourcePaths(String path) {
         return null;
      }

      public String getServerInfo() {
         return ContextHandler.getServerInfo();
      }

      /** @deprecated */
      @Deprecated(
         since = "Servlet API 2.1"
      )
      public Servlet getServlet(String name) throws ServletException {
         return null;
      }

      /** @deprecated */
      @Deprecated(
         since = "Servlet API 2.1"
      )
      public Enumeration getServletNames() {
         return Collections.enumeration(Collections.EMPTY_LIST);
      }

      /** @deprecated */
      @Deprecated(
         since = "Servlet API 2.0"
      )
      public Enumeration getServlets() {
         return Collections.enumeration(Collections.EMPTY_LIST);
      }

      /** @deprecated */
      @Deprecated(
         since = "Servlet API 2.1"
      )
      public void log(Exception exception, String msg) {
         ContextHandler.LOG.warn(msg, exception);
      }

      public void log(String msg) {
         ContextHandler.LOG.info(msg);
      }

      public void log(String message, Throwable throwable) {
         ContextHandler.LOG.warn(message, throwable);
      }

      public String getInitParameter(String name) {
         return null;
      }

      public Enumeration getInitParameterNames() {
         return Collections.enumeration(Collections.EMPTY_LIST);
      }

      public String getServletContextName() {
         return "No Context";
      }

      public String getContextPath() {
         return null;
      }

      public boolean setInitParameter(String name, String value) {
         return false;
      }

      public FilterRegistration.Dynamic addFilter(String filterName, Class filterClass) {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "addFilter(String, Class)");
         return null;
      }

      public FilterRegistration.Dynamic addFilter(String filterName, Filter filter) {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "addFilter(String, Filter)");
         return null;
      }

      public FilterRegistration.Dynamic addFilter(String filterName, String className) {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "addFilter(String, String)");
         return null;
      }

      public ServletRegistration.Dynamic addServlet(String servletName, Class servletClass) {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "addServlet(String, Class)");
         return null;
      }

      public ServletRegistration.Dynamic addServlet(String servletName, Servlet servlet) {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "addServlet(String, Servlet)");
         return null;
      }

      public ServletRegistration.Dynamic addServlet(String servletName, String className) {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "addServlet(String, String)");
         return null;
      }

      public ServletRegistration.Dynamic addJspFile(String servletName, String jspFile) {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "addJspFile(String, String)");
         return null;
      }

      public Set getDefaultSessionTrackingModes() {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "getDefaultSessionTrackingModes()");
         return null;
      }

      public Set getEffectiveSessionTrackingModes() {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "getEffectiveSessionTrackingModes()");
         return null;
      }

      public FilterRegistration getFilterRegistration(String filterName) {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "getFilterRegistration(String)");
         return null;
      }

      public Map getFilterRegistrations() {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "getFilterRegistrations()");
         return null;
      }

      public ServletRegistration getServletRegistration(String servletName) {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "getServletRegistration(String)");
         return null;
      }

      public Map getServletRegistrations() {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "getServletRegistrations()");
         return null;
      }

      public SessionCookieConfig getSessionCookieConfig() {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "getSessionCookieConfig()");
         return null;
      }

      public void setSessionTrackingModes(Set sessionTrackingModes) {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "setSessionTrackingModes(Set<SessionTrackingMode>)");
      }

      public void addListener(String className) {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "addListener(String)");
      }

      public void addListener(EventListener t) {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "addListener(T)");
      }

      public void addListener(Class listenerClass) {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "addListener(Class)");
      }

      public Object createInstance(Class clazz) throws ServletException {
         try {
            return clazz.getDeclaredConstructor().newInstance();
         } catch (Exception e) {
            throw new ServletException(e);
         }
      }

      public EventListener createListener(Class clazz) throws ServletException {
         return (EventListener)this.createInstance(clazz);
      }

      public Servlet createServlet(Class clazz) throws ServletException {
         return (Servlet)this.createInstance(clazz);
      }

      public Filter createFilter(Class clazz) throws ServletException {
         return (Filter)this.createInstance(clazz);
      }

      public ClassLoader getClassLoader() {
         return ContextHandler.class.getClassLoader();
      }

      public int getEffectiveMajorVersion() {
         return this._effectiveMajorVersion;
      }

      public int getEffectiveMinorVersion() {
         return this._effectiveMinorVersion;
      }

      public void setEffectiveMajorVersion(int v) {
         this._effectiveMajorVersion = v;
      }

      public void setEffectiveMinorVersion(int v) {
         this._effectiveMinorVersion = v;
      }

      public JspConfigDescriptor getJspConfigDescriptor() {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "getJspConfigDescriptor()");
         return null;
      }

      public void declareRoles(String... roleNames) {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "declareRoles(String...)");
      }

      public String getVirtualServerName() {
         return null;
      }

      public int getSessionTimeout() {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "getSessionTimeout()");
         return 0;
      }

      public void setSessionTimeout(int sessionTimeout) {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "setSessionTimeout(int)");
      }

      public String getRequestCharacterEncoding() {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "getRequestCharacterEncoding()");
         return null;
      }

      public void setRequestCharacterEncoding(String encoding) {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "setRequestCharacterEncoding(String)");
      }

      public String getResponseCharacterEncoding() {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "getResponseCharacterEncoding()");
         return null;
      }

      public void setResponseCharacterEncoding(String encoding) {
         ContextHandler.LOG.warn("Unimplemented {} - use org.eclipse.jetty.servlet.ServletContextHandler", "setResponseCharacterEncoding(String)");
      }
   }

   /** @deprecated */
   @Deprecated
   public static class ApproveAliases implements AliasCheck {
      public ApproveAliases() {
         ContextHandler.LOG.warn("ApproveAliases is deprecated");
      }

      public boolean check(String pathInContext, Resource resource) {
         return true;
      }
   }

   /** @deprecated */
   @Deprecated
   public static class ApproveNonExistentDirectoryAliases implements AliasCheck {
      public boolean check(String pathInContext, Resource resource) {
         if (resource.exists()) {
            return false;
         } else {
            String a = resource.getAlias().toString();
            String r = resource.getURI().toString();
            if (a.length() > r.length()) {
               return a.startsWith(r) && a.length() == r.length() + 1 && a.endsWith("/");
            } else if (a.length() >= r.length()) {
               return a.equals(r);
            } else {
               return r.startsWith(a) && r.length() == a.length() + 1 && r.endsWith("/");
            }
         }
      }
   }

   public interface AliasCheck {
      boolean check(String var1, Resource var2);
   }

   public interface ContextScopeListener extends EventListener {
      void enterScope(Context var1, Request var2, Object var3);

      void exitScope(Context var1, Request var2);
   }
}
