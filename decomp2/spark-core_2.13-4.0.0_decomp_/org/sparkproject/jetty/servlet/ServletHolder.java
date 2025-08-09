package org.sparkproject.jetty.servlet;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletSecurityElement;
import jakarta.servlet.SingleThreadModel;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparkproject.jetty.security.IdentityService;
import org.sparkproject.jetty.security.RunAsToken;
import org.sparkproject.jetty.server.Request;
import org.sparkproject.jetty.server.UserIdentity;
import org.sparkproject.jetty.server.handler.ContextHandler;
import org.sparkproject.jetty.util.Loader;
import org.sparkproject.jetty.util.NanoTime;
import org.sparkproject.jetty.util.StringUtil;
import org.sparkproject.jetty.util.annotation.ManagedAttribute;
import org.sparkproject.jetty.util.annotation.ManagedObject;
import org.sparkproject.jetty.util.component.Dumpable;
import org.sparkproject.jetty.util.component.DumpableCollection;
import org.sparkproject.jetty.util.thread.AutoLock;

@ManagedObject("Servlet Holder")
public class ServletHolder extends Holder implements UserIdentity.Scope, Comparable {
   private static final Logger LOG = LoggerFactory.getLogger(ServletHolder.class);
   private int _initOrder;
   private boolean _initOnStartup;
   private Map _roleMap;
   private String _forcedPath;
   private String _runAsRole;
   private ServletRegistration.Dynamic _registration;
   private JspContainer _jspContainer;
   private volatile Servlet _servlet;
   private Config _config;
   private boolean _enabled;
   public static final String APACHE_SENTINEL_CLASS = "org.apache.tomcat.InstanceManager";
   public static final String JSP_GENERATED_PACKAGE_NAME = "org.sparkproject.jetty.servlet.jspPackagePrefix";

   public ServletHolder() {
      this(Source.EMBEDDED);
   }

   public ServletHolder(Source creator) {
      super(creator);
      this._initOrder = -1;
      this._initOnStartup = false;
      this._enabled = true;
   }

   public ServletHolder(Servlet servlet) {
      this(Source.EMBEDDED);
      this.setServlet(servlet);
   }

   public ServletHolder(String name, Class servlet) {
      this(Source.EMBEDDED);
      this.setName(name);
      this.setHeldClass(servlet);
   }

   public ServletHolder(String name, Servlet servlet) {
      this(Source.EMBEDDED);
      this.setName(name);
      this.setServlet(servlet);
   }

   public ServletHolder(Class servlet) {
      this(Source.EMBEDDED);
      this.setHeldClass(servlet);
   }

   public UnavailableException getUnavailableException() {
      Servlet servlet = this._servlet;
      return servlet instanceof UnavailableServlet ? ((UnavailableServlet)servlet).getUnavailableException() : null;
   }

   public void setServlet(Servlet servlet) {
      if (servlet != null && !(servlet instanceof SingleThreadModel)) {
         this.setInstance(servlet);
      } else {
         throw new IllegalArgumentException(SingleThreadModel.class.getName() + " has been deprecated since Servlet API 2.4");
      }
   }

   @ManagedAttribute(
      value = "initialization order",
      readonly = true
   )
   public int getInitOrder() {
      return this._initOrder;
   }

   public void setInitOrder(int order) {
      this._initOnStartup = order >= 0;
      this._initOrder = order;
   }

   public int compareTo(ServletHolder sh) {
      if (sh == this) {
         return 0;
      } else if (sh._initOrder < this._initOrder) {
         return 1;
      } else if (sh._initOrder > this._initOrder) {
         return -1;
      } else {
         int c;
         if (this.getClassName() == null && sh.getClassName() == null) {
            c = 0;
         } else if (this.getClassName() == null) {
            c = -1;
         } else if (sh.getClassName() == null) {
            c = 1;
         } else {
            c = this.getClassName().compareTo(sh.getClassName());
         }

         if (c == 0) {
            c = this.getName().compareTo(sh.getName());
         }

         return c;
      }
   }

   public boolean equals(Object o) {
      return o instanceof ServletHolder && this.compareTo((ServletHolder)o) == 0;
   }

   public int hashCode() {
      return this.getName() == null ? System.identityHashCode(this) : this.getName().hashCode();
   }

   public void setUserRoleLink(String name, String link) {
      try (AutoLock l = this.lock()) {
         if (this._roleMap == null) {
            this._roleMap = new HashMap();
         }

         this._roleMap.put(name, link);
      }

   }

   public String getUserRoleLink(String name) {
      try (AutoLock l = this.lock()) {
         if (this._roleMap == null) {
            return name;
         } else {
            String link = (String)this._roleMap.get(name);
            return link == null ? name : link;
         }
      }
   }

   @ManagedAttribute(
      value = "forced servlet path",
      readonly = true
   )
   public String getForcedPath() {
      return this._forcedPath;
   }

   public void setForcedPath(String forcedPath) {
      this._forcedPath = forcedPath;
   }

   private void setClassFrom(ServletHolder holder) {
      if (this._servlet == null && this.getInstance() == null) {
         this.setClassName(holder.getClassName());
         this.setHeldClass(holder.getHeldClass());
      } else {
         throw new IllegalStateException();
      }
   }

   public boolean isEnabled() {
      return this._enabled;
   }

   public void setEnabled(boolean enabled) {
      this._enabled = enabled;
   }

   public void doStart() throws Exception {
      if (this._enabled) {
         if (this._forcedPath != null) {
            String precompiled = this.getClassNameForJsp(this._forcedPath);
            if (!StringUtil.isBlank(precompiled)) {
               if (LOG.isDebugEnabled()) {
                  LOG.debug("Checking for precompiled servlet {} for jsp {}", precompiled, this._forcedPath);
               }

               ServletHolder jsp = this.getServletHandler().getServlet(precompiled);
               if (jsp != null && jsp.getClassName() != null) {
                  if (LOG.isDebugEnabled()) {
                     LOG.debug("JSP file {} for {} mapped to Servlet {}", new Object[]{this._forcedPath, this.getName(), jsp.getClassName()});
                  }

                  this.setClassFrom(jsp);
               } else {
                  jsp = this.getServletHandler().getServlet("jsp");
                  if (jsp != null) {
                     if (LOG.isDebugEnabled()) {
                        LOG.debug("JSP file {} for {} mapped to JspServlet class {}", new Object[]{this._forcedPath, this.getName(), jsp.getClassName()});
                     }

                     this.setClassFrom(jsp);

                     for(Map.Entry entry : jsp.getInitParameters().entrySet()) {
                        if (!this.getInitParameters().containsKey(entry.getKey())) {
                           this.setInitParameter((String)entry.getKey(), (String)entry.getValue());
                        }
                     }

                     this.setInitParameter("jspFile", this._forcedPath);
                  }
               }
            } else {
               LOG.warn("Bad jsp-file {} conversion to classname in holder {}", this._forcedPath, this.getName());
            }
         }

         try {
            super.doStart();
         } catch (UnavailableException ex) {
            this.makeUnavailable(ex);
            if (this.getServletHandler().isStartWithUnavailable()) {
               LOG.trace("IGNORED", ex);
               return;
            }

            throw ex;
         }

         try {
            this.checkServletType();
         } catch (UnavailableException ex) {
            this.makeUnavailable(ex);
            if (this.getServletHandler().isStartWithUnavailable()) {
               LOG.trace("IGNORED", ex);
               return;
            }

            throw ex;
         }

         this.checkInitOnStartup();
         this._config = new Config();
      }
   }

   public void initialize() throws Exception {
      try (AutoLock l = this.lock()) {
         if (this._servlet == null && (this._initOnStartup || this.isInstance())) {
            super.initialize();
            this.initServlet();
         }
      }

   }

   public void doStop() throws Exception {
      try (AutoLock l = this.lock()) {
         Servlet servlet = this._servlet;
         if (servlet != null) {
            this._servlet = null;

            try {
               this.destroyInstance(servlet);
            } catch (Exception e) {
               LOG.warn("Unable to destroy servlet {}", servlet, e);
            }
         }

         this._config = null;
      }

   }

   public void destroyInstance(Object o) {
      if (o != null) {
         Servlet servlet = (Servlet)o;
         this.predestroyServlet(servlet);
         servlet.destroy();
      }
   }

   private void predestroyServlet(Servlet servlet) {
      this.getServletHandler().destroyServlet((Servlet)this.unwrap(servlet));
   }

   public Servlet getServlet() throws ServletException {
      Servlet servlet = this._servlet;
      if (servlet == null) {
         try (AutoLock l = this.lock()) {
            if (this._servlet == null && this.isRunning() && this.getHeldClass() != null) {
               this.initServlet();
            }

            servlet = this._servlet;
         }
      }

      return servlet;
   }

   public Servlet getServletInstance() {
      return this._servlet;
   }

   public void checkServletType() throws UnavailableException {
      if (this.getHeldClass() == null || !Servlet.class.isAssignableFrom(this.getHeldClass())) {
         throw new UnavailableException("Servlet " + String.valueOf(this.getHeldClass()) + " is not a jakarta.servlet.Servlet");
      }
   }

   public boolean isAvailable() {
      return this.isStarted() && !(this._servlet instanceof UnavailableServlet);
   }

   private void checkInitOnStartup() {
      if (this.getHeldClass() != null) {
         if (this.getHeldClass().getAnnotation(ServletSecurity.class) != null && !this._initOnStartup) {
            this.setInitOrder(Integer.MAX_VALUE);
         }

      }
   }

   private Servlet makeUnavailable(UnavailableException e) {
      try (AutoLock l = this.lock()) {
         if (this._servlet instanceof UnavailableServlet) {
            Throwable cause = ((UnavailableServlet)this._servlet).getUnavailableException();
            if (cause != e) {
               cause.addSuppressed(e);
            }
         } else {
            this._servlet = new UnavailableServlet(e, this._servlet);
         }

         return this._servlet;
      }
   }

   private void makeUnavailable(final Throwable e) {
      if (e instanceof UnavailableException) {
         this.makeUnavailable((UnavailableException)e);
      } else {
         ServletContext ctx = this.getServletHandler().getServletContext();
         if (ctx == null) {
            LOG.warn("unavailable", e);
         } else {
            ctx.log("unavailable", e);
         }

         UnavailableException unavailable = new UnavailableException(String.valueOf(e), -1) {
            {
               this.initCause(e);
            }
         };
         this.makeUnavailable(unavailable);
      }

   }

   private void initServlet() throws ServletException {
      if (!this.lockIsHeldByCurrentThread()) {
         throw new IllegalStateException("Lock not held");
      } else if (this._servlet != null) {
         throw new IllegalStateException("Servlet already initialised: " + String.valueOf(this._servlet));
      } else {
         Servlet servlet = null;

         try {
            Servlet var7 = (Servlet)this.getInstance();
            if (var7 == null) {
               var7 = this.newInstance();
            }

            if (var7 instanceof SingleThreadModel) {
               this.predestroyServlet((Servlet)var7);
               var7 = new SingleThreadedWrapper();
            }

            if (this._config == null) {
               this._config = new Config();
            }

            if (this._runAsRole != null) {
               IdentityService identityService = this.getServletHandler().getIdentityService();
               if (identityService != null) {
                  RunAsToken runAsToken = identityService.newRunAsToken(this._runAsRole);
                  var7 = new RunAs((Servlet)var7, identityService, runAsToken);
               }
            }

            if (!this.isAsyncSupported()) {
               var7 = new NotAsync((Servlet)var7);
            }

            if (this.isJspServlet()) {
               this.initJspServlet();
               this.detectJspContainer();
            } else if (this._forcedPath != null) {
               this.detectJspContainer();
            }

            Servlet var8 = (Servlet)this.wrap(var7, WrapFunction.class, WrapFunction::wrapServlet);
            if (LOG.isDebugEnabled()) {
               LOG.debug("Servlet.init {} for {}", this._servlet, this.getName());
            }

            try {
               var8.init(this._config);
               this._servlet = var8;
            } catch (UnavailableException e) {
               this._servlet = new UnavailableServlet(e, var8);
            }

         } catch (ServletException var5) {
            this.makeUnavailable((Throwable)(var5.getCause() == null ? var5 : var5.getCause()));
            this.predestroyServlet(servlet);
            throw var5;
         } catch (Exception e) {
            this.makeUnavailable((Throwable)e);
            this.predestroyServlet(servlet);
            throw new ServletException(this.toString(), e);
         }
      }
   }

   protected void initJspServlet() throws Exception {
      ContextHandler ch = ContextHandler.getContextHandler(this.getServletHandler().getServletContext());
      ch.setAttribute("org.apache.catalina.jsp_classpath", ch.getClassPath());
      if ("?".equals(this.getInitParameter("classpath"))) {
         String classpath = ch.getClassPath();
         if (LOG.isDebugEnabled()) {
            LOG.debug("classpath={}", classpath);
         }

         if (classpath != null) {
            this.setInitParameter("classpath", classpath);
         }
      }

      if (this.getInitParameter("scratchdir") == null) {
         File tmp = (File)this.getServletHandler().getServletContext().getAttribute("jakarta.servlet.context.tempdir");
         File scratch = new File(tmp, "jsp");
         this.setInitParameter("scratchdir", scratch.getAbsolutePath());
      }

      File scratch = new File(this.getInitParameter("scratchdir"));
      if (!scratch.exists() && !scratch.mkdir()) {
         throw new IllegalStateException("Could not create JSP scratch directory");
      }
   }

   public ContextHandler getContextHandler() {
      return ContextHandler.getContextHandler(this._config.getServletContext());
   }

   public String getContextPath() {
      return this._config.getServletContext().getContextPath();
   }

   public Map getRoleRefMap() {
      return this._roleMap;
   }

   @ManagedAttribute(
      value = "role to run servlet as",
      readonly = true
   )
   public String getRunAsRole() {
      return this._runAsRole;
   }

   public void setRunAsRole(String role) {
      this._runAsRole = role;
   }

   protected void prepare(Request baseRequest, ServletRequest request, ServletResponse response) throws ServletException, UnavailableException {
      this.getServlet();
      if (this._registration != null) {
         MultipartConfigElement mpce = ((Registration)this._registration).getMultipartConfig();
         if (mpce != null) {
            baseRequest.setAttribute("org.sparkproject.jetty.multipartConfig", mpce);
         }
      }

   }

   public void handle(Request baseRequest, ServletRequest request, ServletResponse response) throws ServletException, UnavailableException, IOException {
      try {
         Servlet servlet = this.getServletInstance();
         if (servlet == null) {
            throw new UnavailableException("Servlet Not Initialized");
         }

         servlet.service(request, response);
      } catch (UnavailableException e) {
         this.makeUnavailable(e).service(request, response);
      }

   }

   protected boolean isJspServlet() {
      Servlet servlet = this.getServletInstance();

      for(Class<?> c = servlet == null ? this.getHeldClass() : servlet.getClass(); c != null; c = c.getSuperclass()) {
         if (this.isJspServlet(c.getName())) {
            return true;
         }
      }

      return false;
   }

   protected boolean isJspServlet(String classname) {
      return classname == null ? false : "org.apache.jasper.servlet.JspServlet".equals(classname);
   }

   private void detectJspContainer() {
      if (this._jspContainer == null) {
         try {
            Loader.loadClass("org.apache.tomcat.InstanceManager");
            if (LOG.isDebugEnabled()) {
               LOG.debug("Apache jasper detected");
            }

            this._jspContainer = ServletHolder.JspContainer.APACHE;
         } catch (ClassNotFoundException var2) {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Other jasper detected");
            }

            this._jspContainer = ServletHolder.JspContainer.OTHER;
         }
      }

   }

   public String getNameOfJspClass(String jsp) {
      if (StringUtil.isBlank(jsp)) {
         return "";
      } else {
         jsp = jsp.trim();
         if ("/".equals(jsp)) {
            return "";
         } else {
            int i = jsp.lastIndexOf(47);
            if (i == jsp.length() - 1) {
               return "";
            } else {
               jsp = jsp.substring(i + 1);

               try {
                  Class<?> jspUtil = Loader.loadClass("org.apache.jasper.compiler.JspUtil");
                  Method makeJavaIdentifier = jspUtil.getMethod("makeJavaIdentifier", String.class);
                  return (String)makeJavaIdentifier.invoke((Object)null, jsp);
               } catch (Exception e) {
                  String tmp = StringUtil.replace(jsp, '.', '_');
                  if (LOG.isDebugEnabled()) {
                     LOG.warn("JspUtil.makeJavaIdentifier failed for jsp {} using {} instead", new Object[]{jsp, tmp, e});
                  }

                  return tmp;
               }
            }
         }
      }
   }

   public String getPackageOfJspClass(String jsp) {
      if (jsp == null) {
         return "";
      } else {
         int i = jsp.lastIndexOf(47);
         if (i <= 0) {
            return "";
         } else {
            try {
               Class<?> jspUtil = Loader.loadClass("org.apache.jasper.compiler.JspUtil");
               Method makeJavaPackage = jspUtil.getMethod("makeJavaPackage", String.class);
               return (String)makeJavaPackage.invoke((Object)null, jsp.substring(0, i));
            } catch (Exception e) {
               int s = 0;
               if ('/' == jsp.charAt(0)) {
                  s = 1;
               }

               String makeJavaPackage = jsp.substring(s, i).trim();
               makeJavaPackage = StringUtil.replace(makeJavaPackage, '/', '.');
               makeJavaPackage = ".".equals(makeJavaPackage) ? "" : makeJavaPackage;
               if (LOG.isDebugEnabled()) {
                  LOG.warn("JspUtil.makeJavaPackage failed for {} using {} instead", new Object[]{jsp, makeJavaPackage, e});
               }

               return makeJavaPackage;
            }
         }
      }
   }

   public String getJspPackagePrefix() {
      String jspPackageName = null;
      if (this.getServletHandler() != null && this.getServletHandler().getServletContext() != null) {
         jspPackageName = this.getServletHandler().getServletContext().getInitParameter("org.sparkproject.jetty.servlet.jspPackagePrefix");
      }

      if (jspPackageName == null) {
         jspPackageName = "org.apache.jsp";
      }

      return jspPackageName;
   }

   public String getClassNameForJsp(String jsp) {
      if (jsp == null) {
         return null;
      } else {
         String name = this.getNameOfJspClass(jsp);
         if (StringUtil.isBlank(name)) {
            return null;
         } else {
            StringBuffer fullName = new StringBuffer();
            this.appendPath(fullName, this.getJspPackagePrefix());
            this.appendPath(fullName, this.getPackageOfJspClass(jsp));
            this.appendPath(fullName, name);
            return fullName.toString();
         }
      }
   }

   protected void appendPath(StringBuffer path, String element) {
      if (!StringUtil.isBlank(element)) {
         if (path.length() > 0) {
            path.append(".");
         }

         path.append(element);
      }
   }

   public ServletRegistration.Dynamic getRegistration() {
      if (this._registration == null) {
         this._registration = new Registration();
      }

      return this._registration;
   }

   protected Servlet newInstance() throws Exception {
      return this.createInstance();
   }

   protected Servlet createInstance() throws Exception {
      try (AutoLock l = this.lock()) {
         Servlet servlet = (Servlet)super.createInstance();
         if (servlet == null) {
            ServletContext ctx = this.getServletContext();
            if (ctx != null) {
               servlet = ctx.createServlet(this.getHeldClass());
            }
         }

         return servlet;
      }
   }

   public void dump(Appendable out, String indent) throws IOException {
      if (this.getInitParameters().isEmpty()) {
         Dumpable.dumpObjects(out, indent, this, this._servlet == null ? this.getHeldClass() : this._servlet);
      } else {
         Dumpable.dumpObjects(out, indent, this, this._servlet == null ? this.getHeldClass() : this._servlet, new DumpableCollection("initParams", this.getInitParameters().entrySet()));
      }

   }

   public String toString() {
      return String.format("%s==%s@%x{jsp=%s,order=%d,inst=%b,async=%b,src=%s,%s}", this.getName(), this.getClassName(), this.hashCode(), this._forcedPath, this._initOrder, this._servlet != null, this.isAsyncSupported(), this.getSource(), this.getState());
   }

   public static enum JspContainer {
      APACHE,
      OTHER;

      // $FF: synthetic method
      private static JspContainer[] $values() {
         return new JspContainer[]{APACHE, OTHER};
      }
   }

   protected class Config extends Holder.HolderConfig implements ServletConfig {
      public String getServletName() {
         return ServletHolder.this.getName();
      }
   }

   public class Registration extends Holder.HolderRegistration implements ServletRegistration.Dynamic {
      protected MultipartConfigElement _multipartConfig;

      public Set addMapping(String... urlPatterns) {
         ServletHolder.this.illegalStateIfContextStarted();
         Set<String> clash = null;

         for(String pattern : urlPatterns) {
            ServletMapping mapping = ServletHolder.this.getServletHandler().getServletMapping(pattern);
            if (mapping != null && !mapping.isFromDefaultDescriptor()) {
               if (clash == null) {
                  clash = new HashSet();
               }

               clash.add(pattern);
            }
         }

         if (clash != null) {
            return clash;
         } else {
            ServletMapping mapping = new ServletMapping(Source.JAVAX_API);
            mapping.setServletName(ServletHolder.this.getName());
            mapping.setPathSpecs(urlPatterns);
            ServletHolder.this.getServletHandler().addServletMapping(mapping);
            return Collections.emptySet();
         }
      }

      public Collection getMappings() {
         ServletMapping[] mappings = ServletHolder.this.getServletHandler().getServletMappings();
         List<String> patterns = new ArrayList();
         if (mappings != null) {
            for(ServletMapping mapping : mappings) {
               if (mapping.getServletName().equals(this.getName())) {
                  String[] specs = mapping.getPathSpecs();
                  if (specs != null && specs.length > 0) {
                     patterns.addAll(Arrays.asList(specs));
                  }
               }
            }
         }

         return patterns;
      }

      public String getRunAsRole() {
         return ServletHolder.this._runAsRole;
      }

      public void setLoadOnStartup(int loadOnStartup) {
         ServletHolder.this.illegalStateIfContextStarted();
         ServletHolder.this.setInitOrder(loadOnStartup);
      }

      public int getInitOrder() {
         return ServletHolder.this.getInitOrder();
      }

      public void setMultipartConfig(MultipartConfigElement element) {
         this._multipartConfig = element;
      }

      public MultipartConfigElement getMultipartConfig() {
         return this._multipartConfig;
      }

      public void setRunAsRole(String role) {
         ServletHolder.this._runAsRole = role;
      }

      public Set setServletSecurity(ServletSecurityElement securityElement) {
         return ServletHolder.this.getServletHandler().setServletSecurity(this, securityElement);
      }
   }

   private class SingleThreadedWrapper implements Servlet {
      Stack _stack = new Stack();

      public void destroy() {
         try (AutoLock l = ServletHolder.this.lock()) {
            while(this._stack.size() > 0) {
               Servlet servlet = (Servlet)this._stack.pop();

               try {
                  servlet.destroy();
               } catch (Exception e) {
                  ServletHolder.LOG.warn("Unable to destroy servlet {}", servlet, e);
               }
            }
         }

      }

      public ServletConfig getServletConfig() {
         return ServletHolder.this._config;
      }

      public String getServletInfo() {
         return null;
      }

      public void init(ServletConfig config) throws ServletException {
         try (AutoLock l = ServletHolder.this.lock()) {
            if (this._stack.size() == 0) {
               try {
                  Servlet s = ServletHolder.this.newInstance();
                  s.init(config);
                  this._stack.push(s);
               } catch (ServletException e) {
                  throw e;
               } catch (Exception e) {
                  throw new ServletException(e);
               }
            }
         }

      }

      public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
         Servlet s;
         try (AutoLock l = ServletHolder.this.lock()) {
            if (this._stack.size() > 0) {
               s = (Servlet)this._stack.pop();
            } else {
               try {
                  s = ServletHolder.this.newInstance();
                  s.init(ServletHolder.this._config);
               } catch (ServletException e) {
                  throw e;
               } catch (Exception e) {
                  throw new ServletException(e);
               }
            }
         }

         try {
            s.service(req, res);
         } finally {
            try (AutoLock var8 = ServletHolder.this.lock()) {
               this._stack.push(s);
            }

         }

      }
   }

   private class UnavailableServlet extends Wrapper {
      final UnavailableException _unavailableException;
      final AtomicLong _unavailableStart;

      public UnavailableServlet(UnavailableException unavailableException, Servlet servlet) {
         super((Servlet)(servlet != null ? servlet : new GenericServlet() {
            public void service(ServletRequest req, ServletResponse res) throws IOException {
               ((HttpServletResponse)res).sendError(404);
            }
         }));
         this._unavailableException = unavailableException;
         if (unavailableException.isPermanent()) {
            this._unavailableStart = null;
         } else {
            long start;
            for(start = NanoTime.now(); start == 0L; start = NanoTime.now()) {
            }

            this._unavailableStart = new AtomicLong(start);
         }

      }

      public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
         if (ServletHolder.LOG.isDebugEnabled()) {
            ServletHolder.LOG.debug("Unavailable {}", req, this._unavailableException);
         }

         if (this._unavailableStart == null) {
            ((HttpServletResponse)res).sendError(404);
         } else {
            long start = this._unavailableStart.get();
            if (start != 0L && NanoTime.secondsSince(start) >= (long)this._unavailableException.getUnavailableSeconds()) {
               if (this._unavailableStart.compareAndSet(start, 0L)) {
                  try (AutoLock l = ServletHolder.this.lock()) {
                     ServletHolder.this._servlet = this.getWrapped();
                  }

                  Request baseRequest = Request.getBaseRequest(req);
                  ServletHolder.this.prepare(baseRequest, req, res);
                  ServletHolder.this.handle(baseRequest, req, res);
               } else {
                  ((HttpServletResponse)res).sendError(503);
               }
            } else {
               ((HttpServletResponse)res).sendError(503);
            }
         }

      }

      public UnavailableException getUnavailableException() {
         return this._unavailableException;
      }
   }

   public static class Wrapper implements Servlet, BaseHolder.Wrapped {
      private final Servlet _wrappedServlet;

      public Wrapper(Servlet servlet) {
         this._wrappedServlet = (Servlet)Objects.requireNonNull(servlet, "Servlet cannot be null");
      }

      public Servlet getWrapped() {
         return this._wrappedServlet;
      }

      public void init(ServletConfig config) throws ServletException {
         this._wrappedServlet.init(config);
      }

      public ServletConfig getServletConfig() {
         return this._wrappedServlet.getServletConfig();
      }

      public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
         this._wrappedServlet.service(req, res);
      }

      public String getServletInfo() {
         return this._wrappedServlet.getServletInfo();
      }

      public void destroy() {
         this._wrappedServlet.destroy();
      }

      public String toString() {
         return String.format("%s:%s", this.getClass().getSimpleName(), this._wrappedServlet.toString());
      }
   }

   private static class RunAs extends Wrapper {
      final IdentityService _identityService;
      final RunAsToken _runAsToken;

      public RunAs(Servlet servlet, IdentityService identityService, RunAsToken runAsToken) {
         super(servlet);
         this._identityService = identityService;
         this._runAsToken = runAsToken;
      }

      public void init(ServletConfig config) throws ServletException {
         Object oldRunAs = this._identityService.setRunAs(this._identityService.getSystemUserIdentity(), this._runAsToken);

         try {
            this.getWrapped().init(config);
         } finally {
            this._identityService.unsetRunAs(oldRunAs);
         }

      }

      public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
         Object oldRunAs = this._identityService.setRunAs(this._identityService.getSystemUserIdentity(), this._runAsToken);

         try {
            this.getWrapped().service(req, res);
         } finally {
            this._identityService.unsetRunAs(oldRunAs);
         }

      }

      public void destroy() {
         Object oldRunAs = this._identityService.setRunAs(this._identityService.getSystemUserIdentity(), this._runAsToken);

         try {
            this.getWrapped().destroy();
         } finally {
            this._identityService.unsetRunAs(oldRunAs);
         }

      }
   }

   private static class NotAsync extends Wrapper {
      public NotAsync(Servlet servlet) {
         super(servlet);
      }

      public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
         if (req.isAsyncSupported()) {
            Request baseRequest = Request.getBaseRequest(req);

            try {
               baseRequest.setAsyncSupported(false, this.toString());
               this.getWrapped().service(req, res);
            } finally {
               baseRequest.setAsyncSupported(true, (Object)null);
            }
         } else {
            this.getWrapped().service(req, res);
         }

      }
   }

   public interface WrapFunction {
      Servlet wrapServlet(Servlet var1);
   }
}
