package org.apache.hive.service.cli.thrift;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.hive.common.auth.HiveAuthUtils;
import org.apache.hadoop.hive.conf.HiveConf.ConfVars;
import org.apache.hadoop.hive.shims.ShimLoader;
import org.apache.hive.service.ServiceException;
import org.apache.hive.service.auth.HiveAuthFactory;
import org.apache.hive.service.cli.CLIService;
import org.apache.hive.service.cli.HiveSQLException;
import org.apache.hive.service.rpc.thrift.TGetQueryIdReq;
import org.apache.hive.service.rpc.thrift.TGetQueryIdResp;
import org.apache.hive.service.server.ThreadFactoryWithGarbageCleanup;
import org.apache.spark.sql.hive.thriftserver.HiveThriftServer2$;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportFactory;

public class ThriftBinaryCLIService extends ThriftCLIService {
   protected TServer server;

   public ThriftBinaryCLIService(CLIService cliService) {
      super(cliService, ThriftBinaryCLIService.class.getSimpleName());
   }

   protected void initializeServer() {
      try {
         String threadPoolName = "HiveServer2-Handler-Pool";
         ExecutorService executorService = new ThreadPoolExecutor(this.minWorkerThreads, this.maxWorkerThreads, this.workerKeepAliveTime, TimeUnit.SECONDS, new SynchronousQueue(), new ThreadFactoryWithGarbageCleanup(threadPoolName));
         hiveAuthFactory = new HiveAuthFactory(this.hiveConf);
         TTransportFactory transportFactory = hiveAuthFactory.getAuthTransFactory();
         TProcessorFactory processorFactory = hiveAuthFactory.getAuthProcFactory(this);
         TServerSocket serverSocket = null;
         List<String> sslVersionBlacklist = new ArrayList();

         for(String sslVersion : this.hiveConf.getVar(ConfVars.HIVE_SSL_PROTOCOL_BLACKLIST).split(",")) {
            sslVersionBlacklist.add(sslVersion);
         }

         if (!this.hiveConf.getBoolVar(ConfVars.HIVE_SERVER2_USE_SSL)) {
            serverSocket = HiveAuthUtils.getServerSocket(this.hiveHost, this.portNum);
         } else {
            String keyStorePath = this.hiveConf.getVar(ConfVars.HIVE_SERVER2_SSL_KEYSTORE_PATH).trim();
            if (keyStorePath.isEmpty()) {
               throw new IllegalArgumentException(ConfVars.HIVE_SERVER2_SSL_KEYSTORE_PATH.varname + " Not configured for SSL connection");
            }

            String keyStorePassword = ShimLoader.getHadoopShims().getPassword(this.hiveConf, ConfVars.HIVE_SERVER2_SSL_KEYSTORE_PASSWORD.varname);
            serverSocket = HiveAuthUtils.getServerSSLSocket(this.hiveHost, this.portNum, keyStorePath, keyStorePassword, sslVersionBlacklist);
         }

         this.portNum = serverSocket.getServerSocket().getLocalPort();
         int maxMessageSize = this.hiveConf.getIntVar(ConfVars.HIVE_SERVER2_THRIFT_MAX_MESSAGE_SIZE);
         TThreadPoolServer.Args sargs = ((TThreadPoolServer.Args)((TThreadPoolServer.Args)((TThreadPoolServer.Args)((TThreadPoolServer.Args)(new TThreadPoolServer.Args(serverSocket)).processorFactory(processorFactory)).transportFactory(transportFactory)).protocolFactory(new TBinaryProtocol.Factory())).inputProtocolFactory(new TBinaryProtocol.Factory(true, true, (long)maxMessageSize, (long)maxMessageSize))).executorService(executorService);
         this.server = new TThreadPoolServer(sargs);
         this.server.setServerEventHandler(this.serverEventHandler);
         String var10000 = ThriftBinaryCLIService.class.getSimpleName();
         String msg = "Starting " + var10000 + " on port " + this.portNum + " with " + this.minWorkerThreads + "..." + this.maxWorkerThreads + " worker threads";
         LOG.info(msg);
      } catch (Exception t) {
         throw new ServiceException("Error initializing " + this.getName(), t);
      }
   }

   protected void stopServer() {
      this.server.stop();
      this.server = null;
      LOG.info("Thrift server has stopped");
   }

   public TGetQueryIdResp GetQueryId(TGetQueryIdReq req) throws TException {
      try {
         return new TGetQueryIdResp(this.cliService.getQueryId(req.getOperationHandle()));
      } catch (HiveSQLException e) {
         throw new TException(e);
      }
   }

   public void run() {
      try {
         this.server.serve();
      } catch (Throwable t) {
         LOG.error("Error starting HiveServer2: could not start " + ThriftBinaryCLIService.class.getSimpleName(), t);
         if (!HiveThriftServer2$.MODULE$.systemExitOnError().get()) {
            throw new ServiceException(t);
         }

         System.exit(-1);
      }

   }
}
