package org.apache.zookeeper.server.admin;

import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.zookeeper.common.QuorumX509Util;
import org.apache.zookeeper.common.SecretUtils;
import org.apache.zookeeper.common.X509Util;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.apache.zookeeper.server.auth.IPAuthenticationProvider;
import org.eclipse.jetty.http.HttpHeader;
import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.security.Constraint;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JettyAdminServer implements AdminServer {
   static final Logger LOG = LoggerFactory.getLogger(JettyAdminServer.class);
   public static final int DEFAULT_PORT = 8080;
   public static final int DEFAULT_IDLE_TIMEOUT = 30000;
   public static final String DEFAULT_COMMAND_URL = "/commands";
   private static final String DEFAULT_ADDRESS = "0.0.0.0";
   public static final int DEFAULT_STS_MAX_AGE = 86400;
   public static final int DEFAULT_HTTP_VERSION = 11;
   private final Server server;
   private final String address;
   private final int port;
   private final int idleTimeout;
   private final String commandUrl;
   private ZooKeeperServer zkServer;

   public JettyAdminServer() throws AdminServer.AdminServerException, IOException, GeneralSecurityException {
      this(System.getProperty("zookeeper.admin.serverAddress", "0.0.0.0"), Integer.getInteger("zookeeper.admin.serverPort", 8080), Integer.getInteger("zookeeper.admin.idleTimeout", 30000), System.getProperty("zookeeper.admin.commandURL", "/commands"), Integer.getInteger("zookeeper.admin.httpVersion", 11), Boolean.getBoolean("zookeeper.admin.portUnification"), Boolean.getBoolean("zookeeper.admin.forceHttps"), Boolean.getBoolean("zookeeper.admin.needClientAuth"));
   }

   public JettyAdminServer(String address, int port, int timeout, String commandUrl, int httpVersion, boolean portUnification, boolean forceHttps, boolean needClientAuth) throws IOException, GeneralSecurityException {
      this.port = port;
      this.idleTimeout = timeout;
      this.commandUrl = commandUrl;
      this.address = address;
      this.server = new Server();
      ServerConnector connector = null;
      if (!portUnification && !forceHttps) {
         connector = new ServerConnector(this.server);
      } else {
         SecureRequestCustomizer customizer = new SecureRequestCustomizer();
         customizer.setStsMaxAge(86400L);
         customizer.setStsIncludeSubDomains(true);
         HttpConfiguration config = new HttpConfiguration();
         config.setSecureScheme("https");
         config.addCustomizer(customizer);
         QuorumX509Util x509Util = new QuorumX509Util();

         try {
            String privateKeyType = System.getProperty(x509Util.getSslKeystoreTypeProperty(), "");
            String privateKeyPath = System.getProperty(x509Util.getSslKeystoreLocationProperty(), "");
            String privateKeyPassword = this.getPasswordFromSystemPropertyOrFile(x509Util.getSslKeystorePasswdProperty(), x509Util.getSslKeystorePasswdPathProperty());
            String certAuthType = System.getProperty(x509Util.getSslTruststoreTypeProperty(), "");
            String certAuthPath = System.getProperty(x509Util.getSslTruststoreLocationProperty(), "");
            String certAuthPassword = this.getPasswordFromSystemPropertyOrFile(x509Util.getSslTruststorePasswdProperty(), x509Util.getSslTruststorePasswdPathProperty());
            KeyStore keyStore = null;
            KeyStore trustStore = null;

            try {
               keyStore = X509Util.loadKeyStore(privateKeyPath, privateKeyPassword, privateKeyType);
               trustStore = X509Util.loadTrustStore(certAuthPath, certAuthPassword, certAuthType);
               LOG.info("Successfully loaded private key from {}", privateKeyPath);
               LOG.info("Successfully loaded certificate authority from {}", certAuthPath);
            } catch (Exception e) {
               LOG.error("Failed to load authentication certificates for admin server.", e);
               throw e;
            }

            SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();
            sslContextFactory.setKeyStore(keyStore);
            sslContextFactory.setKeyStorePassword(privateKeyPassword);
            sslContextFactory.setTrustStore(trustStore);
            sslContextFactory.setTrustStorePassword(certAuthPassword);
            sslContextFactory.setNeedClientAuth(needClientAuth);
            if (forceHttps) {
               connector = new ServerConnector(this.server, new ConnectionFactory[]{new SslConnectionFactory(sslContextFactory, HttpVersion.fromVersion(httpVersion).asString()), new HttpConnectionFactory(config)});
            } else {
               connector = new ServerConnector(this.server, new ConnectionFactory[]{new UnifiedConnectionFactory(sslContextFactory, HttpVersion.fromVersion(httpVersion).asString()), new HttpConnectionFactory(config)});
            }
         } catch (Throwable var24) {
            try {
               x509Util.close();
            } catch (Throwable var22) {
               var24.addSuppressed(var22);
            }

            throw var24;
         }

         x509Util.close();
      }

      connector.setHost(address);
      connector.setPort(port);
      connector.setIdleTimeout((long)this.idleTimeout);
      this.server.addConnector(connector);
      ServletContextHandler context = new ServletContextHandler(1);
      context.setContextPath("/*");
      this.constrainTraceMethod(context);
      this.server.setHandler(context);
      context.addServlet(new ServletHolder(new CommandServlet()), commandUrl + "/*");
   }

   public void start() throws AdminServer.AdminServerException {
      try {
         this.server.start();
      } catch (Exception e) {
         String message = String.format("Problem starting AdminServer on address %s, port %d and command URL %s", this.address, this.port, this.commandUrl);
         throw new AdminServer.AdminServerException(message, e);
      }

      LOG.info("Started AdminServer on address {}, port {} and command URL {}", new Object[]{this.address, this.port, this.commandUrl});
   }

   public void shutdown() throws AdminServer.AdminServerException {
      try {
         this.server.stop();
      } catch (Exception e) {
         String message = String.format("Problem stopping AdminServer on address %s, port %d and command URL %s", this.address, this.port, this.commandUrl);
         throw new AdminServer.AdminServerException(message, e);
      }
   }

   public void setZooKeeperServer(ZooKeeperServer zkServer) {
      this.zkServer = zkServer;
   }

   private List commandLinks() {
      return (List)Commands.getPrimaryNames().stream().sorted().map((command) -> String.format("<a href=\"%s\">%s</a>", this.commandUrl + "/" + command, command)).collect(Collectors.toList());
   }

   private void constrainTraceMethod(ServletContextHandler ctxHandler) {
      Constraint c = new Constraint();
      c.setAuthenticate(true);
      ConstraintMapping cmt = new ConstraintMapping();
      cmt.setConstraint(c);
      cmt.setMethod("TRACE");
      cmt.setPathSpec("/*");
      ConstraintSecurityHandler securityHandler = new ConstraintSecurityHandler();
      securityHandler.setConstraintMappings(new ConstraintMapping[]{cmt});
      ctxHandler.setSecurityHandler(securityHandler);
   }

   private String getPasswordFromSystemPropertyOrFile(String propertyName, String pathPropertyName) {
      String value = System.getProperty(propertyName, "");
      String pathValue = System.getProperty(pathPropertyName, "");
      if (!pathValue.isEmpty()) {
         value = String.valueOf(SecretUtils.readSecret(pathValue));
      }

      return value;
   }

   private class CommandServlet extends HttpServlet {
      private static final long serialVersionUID = 1L;

      private CommandServlet() {
      }

      protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String cmd = request.getPathInfo();
         if (cmd != null && !cmd.equals("/")) {
            cmd = cmd.substring(1);
            Map<String, String[]> parameterMap = request.getParameterMap();
            Map<String, String> kwargs = new HashMap();

            for(Map.Entry entry : parameterMap.entrySet()) {
               kwargs.put((String)entry.getKey(), ((String[])entry.getValue())[0]);
            }

            String authInfo = request.getHeader(HttpHeader.AUTHORIZATION.asString());
            CommandResponse cmdResponse = Commands.runGetCommand(cmd, JettyAdminServer.this.zkServer, kwargs, authInfo, request);
            response.setStatus(cmdResponse.getStatusCode());
            Map<String, String> headers = cmdResponse.getHeaders();

            for(Map.Entry header : headers.entrySet()) {
               response.addHeader((String)header.getKey(), (String)header.getValue());
            }

            String clientIP = IPAuthenticationProvider.getClientIPAddress(request);
            if (cmdResponse.getInputStream() == null) {
               CommandOutputter outputter = new JsonOutputter(clientIP);
               response.setContentType(outputter.getContentType());
               outputter.output(cmdResponse, response.getWriter());
            } else {
               CommandOutputter outputter = new StreamOutputter(clientIP);
               response.setContentType(outputter.getContentType());
               outputter.output(cmdResponse, (OutputStream)response.getOutputStream());
            }

         } else {
            for(String link : JettyAdminServer.this.commandLinks()) {
               response.getWriter().println(link);
               response.getWriter().println("<br/>");
            }

         }
      }

      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String cmdName = this.extractCommandNameFromURL(request, response);
         if (cmdName != null) {
            String authInfo = request.getHeader(HttpHeader.AUTHORIZATION.asString());
            CommandResponse cmdResponse = Commands.runPostCommand(cmdName, JettyAdminServer.this.zkServer, request.getInputStream(), authInfo, request);
            String clientIP = IPAuthenticationProvider.getClientIPAddress(request);
            this.sendJSONResponse(response, cmdResponse, clientIP);
         }

      }

      private String extractCommandNameFromURL(HttpServletRequest request, HttpServletResponse response) throws IOException {
         String cmd = request.getPathInfo();
         if (cmd != null && !cmd.equals("/")) {
            return cmd.substring(1);
         } else {
            this.printCommandLinks(response);
            return null;
         }
      }

      private void printCommandLinks(HttpServletResponse response) throws IOException {
         for(String link : JettyAdminServer.this.commandLinks()) {
            response.getWriter().println(link);
            response.getWriter().println("<br/>");
         }

      }

      private void sendJSONResponse(HttpServletResponse response, CommandResponse cmdResponse, String clientIP) throws IOException {
         CommandOutputter outputter = new JsonOutputter(clientIP);
         response.setStatus(cmdResponse.getStatusCode());
         response.setContentType(outputter.getContentType());
         outputter.output(cmdResponse, response.getWriter());
      }
   }
}
