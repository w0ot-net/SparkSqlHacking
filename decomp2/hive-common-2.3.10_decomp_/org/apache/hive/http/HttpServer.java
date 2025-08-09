package org.apache.hive.http;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.math3.util.Pair;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.common.classification.InterfaceAudience;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.security.SecurityUtil;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.authentication.server.AuthenticationFilter;
import org.apache.hadoop.security.authorize.AccessControlList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AbstractOutputStreamAppender;
import org.apache.logging.log4j.core.appender.FileManager;
import org.apache.logging.log4j.core.appender.OutputStreamManager;
import org.eclipse.jetty.rewrite.handler.RewriteHandler;
import org.eclipse.jetty.rewrite.handler.RewriteRegexRule;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.server.ssl.SslSelectChannelConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer {
   private static final Logger LOG = LoggerFactory.getLogger(HttpServer.class);
   public static final String CONF_CONTEXT_ATTRIBUTE = "hive.conf";
   public static final String ADMINS_ACL = "admins.acl";
   private final String name;
   private final String appDir;
   private final WebAppContext webAppContext;
   private final Server webServer;

   private HttpServer(Builder b) throws IOException {
      this.name = b.name;
      this.webServer = new Server();
      this.appDir = this.getWebAppsPath(b.name);
      this.webAppContext = this.createWebAppContext(b);
      if (b.useSPNEGO) {
         this.setupSpnegoFilter(b);
      }

      this.initializeWebServer(b);
   }

   public void start() throws Exception {
      this.webServer.start();
      LOG.info("Started HttpServer[{}] on port {}", this.name, this.getPort());
   }

   public void stop() throws Exception {
      this.webServer.stop();
   }

   public int getPort() {
      return this.webServer.getConnectors()[0].getLocalPort();
   }

   @InterfaceAudience.LimitedPrivate({"hive"})
   public static boolean isInstrumentationAccessAllowed(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws IOException {
      Configuration conf = (Configuration)servletContext.getAttribute("hive.conf");
      boolean access = true;
      boolean adminAccess = conf.getBoolean("hadoop.security.instrumentation.requires.admin", false);
      if (adminAccess) {
         access = hasAdministratorAccess(servletContext, request, response);
      }

      return access;
   }

   static boolean hasAdministratorAccess(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws IOException {
      Configuration conf = (Configuration)servletContext.getAttribute("hive.conf");
      if (!conf.getBoolean("hadoop.security.authorization", false)) {
         return true;
      } else {
         String remoteUser = request.getRemoteUser();
         if (remoteUser == null) {
            response.sendError(401, "Unauthenticated users are not authorized to access this page.");
            return false;
         } else if (servletContext.getAttribute("admins.acl") != null && !userHasAdministratorAccess(servletContext, remoteUser)) {
            response.sendError(401, "User " + remoteUser + " is unauthorized to access this page.");
            return false;
         } else {
            return true;
         }
      }
   }

   static boolean userHasAdministratorAccess(ServletContext servletContext, String remoteUser) {
      AccessControlList adminsAcl = (AccessControlList)servletContext.getAttribute("admins.acl");
      UserGroupInformation remoteUserUGI = UserGroupInformation.createRemoteUser(remoteUser);
      return adminsAcl != null && adminsAcl.isUserAllowed(remoteUserUGI);
   }

   WebAppContext createWebAppContext(Builder b) {
      WebAppContext ctx = new WebAppContext();
      this.setContextAttributes(ctx.getServletContext(), b.contextAttrs);
      ctx.setDisplayName(b.name);
      ctx.setContextPath("/");
      ctx.setWar(this.appDir + "/" + b.name);
      return ctx;
   }

   void setupSpnegoFilter(Builder b) throws IOException {
      Map<String, String> params = new HashMap();
      params.put("kerberos.principal", SecurityUtil.getServerPrincipal(b.spnegoPrincipal, b.host));
      params.put("kerberos.keytab", b.spnegoKeytab);
      params.put("type", "kerberos");
      FilterHolder holder = new FilterHolder();
      holder.setClassName(AuthenticationFilter.class.getName());
      holder.setInitParameters(params);
      ServletHandler handler = this.webAppContext.getServletHandler();
      handler.addFilterWithMapping(holder, "/*", 31);
   }

   Connector createChannelConnector(int queueSize, Builder b) {
      SelectChannelConnector connector;
      if (!b.useSSL) {
         connector = new SelectChannelConnector();
      } else {
         SslContextFactory sslContextFactory = new SslContextFactory();
         sslContextFactory.setKeyStorePath(b.keyStorePath);
         Set<String> excludedSSLProtocols = Sets.newHashSet(Splitter.on(",").trimResults().omitEmptyStrings().split(Strings.nullToEmpty(b.conf.getVar(HiveConf.ConfVars.HIVE_SSL_PROTOCOL_BLACKLIST))));
         sslContextFactory.addExcludeProtocols((String[])excludedSSLProtocols.toArray(new String[excludedSSLProtocols.size()]));
         sslContextFactory.setKeyStorePassword(b.keyStorePassword);
         connector = new SslSelectChannelConnector(sslContextFactory);
      }

      connector.setLowResourcesMaxIdleTime(10000);
      connector.setAcceptQueueSize(queueSize);
      connector.setResolveNames(false);
      connector.setUseDirectBuffers(false);
      connector.setRequestHeaderSize(65536);
      connector.setReuseAddress(true);
      return connector;
   }

   void setContextAttributes(ContextHandler.Context ctx, Map contextAttrs) {
      for(Map.Entry e : contextAttrs.entrySet()) {
         ctx.setAttribute((String)e.getKey(), e.getValue());
      }

   }

   void initializeWebServer(Builder b) {
      QueuedThreadPool threadPool = new QueuedThreadPool();
      if (b.maxThreads > 0) {
         threadPool.setMaxThreads(b.maxThreads);
      }

      threadPool.setDaemon(true);
      threadPool.setName(b.name + "-web");
      this.webServer.setThreadPool(threadPool);
      Connector connector = this.createChannelConnector(threadPool.getMaxThreads(), b);
      connector.setHost(b.host);
      connector.setPort(b.port);
      this.webServer.addConnector(connector);
      RewriteHandler rwHandler = new RewriteHandler();
      rwHandler.setRewriteRequestURI(true);
      rwHandler.setRewritePathInfo(false);
      RewriteRegexRule rootRule = new RewriteRegexRule();
      rootRule.setRegex("^/$");
      rootRule.setReplacement(b.contextRootRewriteTarget);
      rootRule.setTerminating(true);
      rwHandler.addRule(rootRule);
      rwHandler.setHandler(this.webAppContext);
      ContextHandlerCollection contexts = new ContextHandlerCollection();
      contexts.addHandler(rwHandler);
      this.webServer.setHandler(contexts);
      this.addServlet("jmx", "/jmx", JMXJsonServlet.class);
      this.addServlet("conf", "/conf", ConfServlet.class);
      this.addServlet("stacks", "/stacks", StackServlet.class);

      for(Pair p : b.servlets) {
         this.addServlet((String)p.getFirst(), "/" + (String)p.getFirst(), (Class)p.getSecond());
      }

      ServletContextHandler staticCtx = new ServletContextHandler(contexts, "/static");
      staticCtx.setResourceBase(this.appDir + "/static");
      staticCtx.addServlet(DefaultServlet.class, "/*");
      staticCtx.setDisplayName("static");
      String logDir = this.getLogDir(b.conf);
      if (logDir != null) {
         ServletContextHandler logCtx = new ServletContextHandler(contexts, "/logs");
         this.setContextAttributes(logCtx.getServletContext(), b.contextAttrs);
         logCtx.addServlet(AdminAuthorizedServlet.class, "/*");
         logCtx.setResourceBase(logDir);
         logCtx.setDisplayName("logs");
      }

   }

   String getLogDir(Configuration conf) {
      String logDir = conf.get("hive.log.dir");
      if (logDir == null) {
         logDir = System.getProperty("hive.log.dir");
      }

      if (logDir != null) {
         return logDir;
      } else {
         LoggerContext context = (LoggerContext)LogManager.getContext(false);

         for(org.apache.logging.log4j.core.Logger logger : context.getLoggers()) {
            for(Appender appender : logger.getAppenders().values()) {
               if (appender instanceof AbstractOutputStreamAppender) {
                  OutputStreamManager manager = ((AbstractOutputStreamAppender)appender).getManager();
                  if (manager instanceof FileManager) {
                     String fileName = ((FileManager)manager).getFileName();
                     if (fileName != null) {
                        return fileName.substring(0, fileName.lastIndexOf(47));
                     }
                  }
               }
            }
         }

         return null;
      }
   }

   String getWebAppsPath(String appName) throws FileNotFoundException {
      String relativePath = "hive-webapps/" + appName;
      URL url = this.getClass().getClassLoader().getResource(relativePath);
      if (url == null) {
         throw new FileNotFoundException(relativePath + " not found in CLASSPATH");
      } else {
         String urlString = url.toString();
         return urlString.substring(0, urlString.lastIndexOf(47));
      }
   }

   public void addServlet(String name, String pathSpec, Class clazz) {
      ServletHolder holder = new ServletHolder(clazz);
      if (name != null) {
         holder.setName(name);
      }

      this.webAppContext.addServlet(holder, pathSpec);
   }

   public static class Builder {
      private final String name;
      private String host;
      private int port;
      private int maxThreads;
      private HiveConf conf;
      private final Map contextAttrs = new HashMap();
      private String keyStorePassword;
      private String keyStorePath;
      private String spnegoPrincipal;
      private String spnegoKeytab;
      private boolean useSPNEGO;
      private boolean useSSL;
      private String contextRootRewriteTarget = "/index.html";
      private final List servlets = new LinkedList();

      public Builder(String name) {
         Preconditions.checkArgument(name != null && !name.isEmpty(), "Name must be specified");
         this.name = name;
      }

      public HttpServer build() throws IOException {
         return new HttpServer(this);
      }

      public Builder setConf(HiveConf origConf) {
         this.conf = new HiveConf(origConf);
         origConf.stripHiddenConfigurations(this.conf);
         this.setContextAttribute("hive.conf", this.conf);
         return this;
      }

      public Builder setHost(String host) {
         this.host = host;
         return this;
      }

      public Builder setPort(int port) {
         this.port = port;
         return this;
      }

      public Builder setMaxThreads(int maxThreads) {
         this.maxThreads = maxThreads;
         return this;
      }

      public Builder setAdmins(String admins) {
         if (admins != null) {
            this.setContextAttribute("admins.acl", new AccessControlList(admins));
         }

         return this;
      }

      public Builder setKeyStorePassword(String keyStorePassword) {
         this.keyStorePassword = keyStorePassword;
         return this;
      }

      public Builder setKeyStorePath(String keyStorePath) {
         this.keyStorePath = keyStorePath;
         return this;
      }

      public Builder setUseSSL(boolean useSSL) {
         this.useSSL = useSSL;
         return this;
      }

      public Builder setUseSPNEGO(boolean useSPNEGO) {
         this.useSPNEGO = useSPNEGO;
         return this;
      }

      public Builder setSPNEGOPrincipal(String principal) {
         this.spnegoPrincipal = principal;
         return this;
      }

      public Builder setSPNEGOKeytab(String keytab) {
         this.spnegoKeytab = keytab;
         return this;
      }

      public Builder setContextAttribute(String name, Object value) {
         this.contextAttrs.put(name, value);
         return this;
      }

      public Builder setContextRootRewriteTarget(String contextRootRewriteTarget) {
         this.contextRootRewriteTarget = contextRootRewriteTarget;
         return this;
      }

      public Builder addServlet(String endpoint, Class servlet) {
         this.servlets.add(new Pair(endpoint, servlet));
         return this;
      }
   }
}
