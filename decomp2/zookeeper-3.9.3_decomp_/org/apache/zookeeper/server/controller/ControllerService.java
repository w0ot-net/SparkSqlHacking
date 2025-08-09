package org.apache.zookeeper.server.controller;

import java.io.IOException;
import org.apache.zookeeper.server.ExitCode;
import org.apache.zookeeper.server.ServerCnxnFactory;
import org.apache.zookeeper.server.quorum.QuorumPeer;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.apache.zookeeper.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControllerService {
   private static final Logger LOG = LoggerFactory.getLogger(ControllerService.class);
   private ZooKeeperServerController controller;
   private CommandListener listener;
   protected QuorumPeerConfig config;
   private ServerCnxnFactory serverCnxnFactory = null;
   protected QuorumPeer quorumPeer = null;

   public static void main(String[] args) {
      try {
         if (args.length != 1) {
            throw new IllegalArgumentException("Require config file as cmd line argument");
         }

         ControllerServerConfig config = new ControllerServerConfig(args[0]);
         (new ControllerService()).start(config);
      } catch (Exception ex) {
         System.err.println(ex.getMessage());
         System.err.println("Usage: TestControllerMain controller-port configfile");
         ServiceUtils.requestSystemExit(ExitCode.UNEXPECTED_ERROR.getValue());
      }

   }

   public Thread start(ControllerServerConfig controllerConfig) {
      this.config = controllerConfig;
      Thread runner = new Thread(() -> {
         try {
            this.run();
         } catch (Exception var2) {
         }

      });
      runner.setDaemon(true);
      runner.start();
      return runner;
   }

   public synchronized void shutdown() {
      if (this.listener != null) {
         this.listener.close();
         this.listener = null;
      }

      if (this.controller != null) {
         this.controller.shutdown();
         this.controller = null;
      }

   }

   protected void initService() throws IOException {
      ControllerServerConfig controllerConfig = (ControllerServerConfig)this.config;
      controllerConfig.ensureComplete();
      this.controller = new ZooKeeperServerController(controllerConfig);
      this.listener = new CommandListener(this.controller, controllerConfig);
      this.serverCnxnFactory = this.controller.getCnxnFactory();
   }

   protected void runServices() {
      this.controller.run();
   }

   protected void cleanup() {
      if (this.listener != null) {
         this.listener.close();
         this.listener = null;
      }

   }

   public void initializeAndRun(String[] args) throws QuorumPeerConfig.ConfigException {
      this.initConfig(args);
      this.run();
   }

   protected void initConfig(String[] args) throws QuorumPeerConfig.ConfigException {
      if (args.length == 1) {
         this.config.parse(args[0]);
      }

   }

   public void runFromConfig(QuorumPeerConfig config) {
      LOG.info("Starting quorum peer from peer config");
      this.config = config;
      this.run();
   }

   protected void run() {
      try {
         this.initService();
      } catch (Exception ex) {
         LOG.error("Failed to start ControllerService.", ex);
         ServiceUtils.requestSystemExit(ExitCode.UNEXPECTED_ERROR.getValue());
      }

      this.runServices();
      this.cleanup();
   }

   public boolean isReady() {
      return this.controller != null && this.controller.isReady();
   }
}
