package org.apache.hive.service.cli.thrift;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.Shell;
import org.apache.hive.service.ServiceException;
import org.apache.hive.service.auth.HiveAuthFactory;
import org.apache.hive.service.cli.CLIService;
import org.apache.hive.service.rpc.thrift.TCLIService;
import org.apache.hive.service.server.ThreadFactoryWithGarbageCleanup;
import org.apache.spark.sql.hive.thriftserver.HiveThriftServer2$;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.sparkproject.jetty.io.ByteBufferPool;
import org.sparkproject.jetty.server.AbstractConnectionFactory;
import org.sparkproject.jetty.server.ConnectionFactory;
import org.sparkproject.jetty.server.HttpConnectionFactory;
import org.sparkproject.jetty.server.Server;
import org.sparkproject.jetty.server.ServerConnector;
import org.sparkproject.jetty.servlet.ServletContextHandler;
import org.sparkproject.jetty.servlet.ServletHolder;
import org.sparkproject.jetty.util.ssl.SslContextFactory;
import org.sparkproject.jetty.util.thread.ExecutorThreadPool;
import org.sparkproject.jetty.util.thread.ScheduledExecutorScheduler;

public class ThriftHttpCLIService extends ThriftCLIService {
   protected Server httpServer;

   public ThriftHttpCLIService(CLIService cliService) {
      super(cliService, ThriftHttpCLIService.class.getSimpleName());
   }

   protected void initializeServer() {
      try {
         String threadPoolName = "HiveServer2-HttpHandler-Pool";
         ThreadPoolExecutor executorService = new ThreadPoolExecutor(this.minWorkerThreads, this.maxWorkerThreads, this.workerKeepAliveTime, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactoryWithGarbageCleanup(threadPoolName));
         ExecutorThreadPool threadPool = new ExecutorThreadPool(executorService);
         this.httpServer = new Server(threadPool);
         boolean useSsl = this.hiveConf.getBoolVar(ConfVars.HIVE_SERVER2_USE_SSL);
         String schemeName = useSsl ? "https" : "http";
         ConnectionFactory[] connectionFactories;
         if (useSsl) {
            String keyStorePath = this.hiveConf.getVar(ConfVars.HIVE_SERVER2_SSL_KEYSTORE_PATH).trim();
            String keyStorePassword = ShimLoader.getHadoopShims().getPassword(this.hiveConf, ConfVars.HIVE_SERVER2_SSL_KEYSTORE_PASSWORD.varname);
            if (keyStorePath.isEmpty()) {
               throw new IllegalArgumentException(ConfVars.HIVE_SERVER2_SSL_KEYSTORE_PATH.varname + " Not configured for SSL connection");
            }

            SslContextFactory.Server sslContextFactoryServer = new SslContextFactory.Server();
            String[] excludedProtocols = this.hiveConf.getVar(ConfVars.HIVE_SSL_PROTOCOL_BLACKLIST).split(",");
            LOG.info("HTTP Server SSL: adding excluded protocols: " + Arrays.toString(excludedProtocols));
            sslContextFactoryServer.addExcludeProtocols(excludedProtocols);
            LOG.info("HTTP Server SSL: SslContextFactory.getExcludeProtocols = " + Arrays.toString(sslContextFactoryServer.getExcludeProtocols()));
            sslContextFactoryServer.setKeyStorePath(keyStorePath);
            sslContextFactoryServer.setKeyStorePassword(keyStorePassword);
            connectionFactories = AbstractConnectionFactory.getFactories(sslContextFactoryServer, new ConnectionFactory[]{new HttpConnectionFactory()});
         } else {
            connectionFactories = new ConnectionFactory[]{new HttpConnectionFactory()};
         }

         ServerConnector connector = new ServerConnector(this.httpServer, (Executor)null, new ScheduledExecutorScheduler("HiveServer2-HttpHandler-JettyScheduler", true), (ByteBufferPool)null, -1, -1, connectionFactories);
         connector.setPort(this.portNum);
         connector.setReuseAddress(!Shell.WINDOWS);
         int maxIdleTime = (int)this.hiveConf.getTimeVar(ConfVars.HIVE_SERVER2_THRIFT_HTTP_MAX_IDLE_TIME, TimeUnit.MILLISECONDS);
         connector.setIdleTimeout((long)maxIdleTime);
         this.httpServer.addConnector(connector);
         hiveAuthFactory = new HiveAuthFactory(this.hiveConf);
         TProcessor processor = new TCLIService.Processor(this);
         TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();
         UserGroupInformation serviceUGI = this.cliService.getServiceUGI();
         UserGroupInformation httpUGI = this.cliService.getHttpUGI();
         String authType = this.hiveConf.getVar(ConfVars.HIVE_SERVER2_AUTHENTICATION);
         TServlet thriftHttpServlet = new ThriftHttpServlet(processor, protocolFactory, authType, serviceUGI, httpUGI, hiveAuthFactory);
         ServletContextHandler context = new ServletContextHandler(1);
         context.setContextPath("/");
         String httpPath = this.getHttpPath(this.hiveConf.getVar(ConfVars.HIVE_SERVER2_THRIFT_HTTP_PATH));
         this.httpServer.setHandler(context);
         context.addServlet(new ServletHolder(thriftHttpServlet), httpPath);
         this.httpServer.start();
         this.portNum = connector.getLocalPort();
         String msg = "Started " + ThriftHttpCLIService.class.getSimpleName() + " in " + schemeName + " mode on port " + this.portNum + " path=" + httpPath + " with " + this.minWorkerThreads + "..." + this.maxWorkerThreads + " worker threads";
         LOG.info(msg);
      } catch (Exception t) {
         throw new ServiceException("Error initializing " + this.getName(), t);
      }
   }

   protected void stopServer() {
      if (this.httpServer != null && this.httpServer.isStarted()) {
         try {
            this.httpServer.stop();
            this.httpServer = null;
            LOG.info("Thrift HTTP server has been stopped");
         } catch (Exception e) {
            LOG.error("Error stopping HTTP server: ", e);
         }
      }

   }

   public void run() {
      try {
         this.httpServer.join();
      } catch (Throwable t) {
         if (t instanceof InterruptedException) {
            LOG.info("Caught " + t.getClass().getSimpleName() + ". Shutting down thrift server.");
         } else {
            LOG.error("Error starting HiveServer2: could not start " + ThriftHttpCLIService.class.getSimpleName(), t);
            if (!HiveThriftServer2$.MODULE$.systemExitOnError().get()) {
               throw new ServiceException(t);
            }

            System.exit(-1);
         }
      }

   }

   private String getHttpPath(String httpPath) {
      if (httpPath != null && !httpPath.equals("")) {
         if (!httpPath.startsWith("/")) {
            httpPath = "/" + httpPath;
         }

         if (httpPath.endsWith("/")) {
            httpPath = httpPath + "*";
         }

         if (!httpPath.endsWith("/*")) {
            httpPath = httpPath + "/*";
         }
      } else {
         httpPath = "/*";
      }

      return httpPath;
   }
}
