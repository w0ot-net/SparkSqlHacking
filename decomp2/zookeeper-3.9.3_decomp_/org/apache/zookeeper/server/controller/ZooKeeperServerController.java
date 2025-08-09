package org.apache.zookeeper.server.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.IOException;
import org.apache.zookeeper.server.ExitCode;
import org.apache.zookeeper.server.ServerCnxn;
import org.apache.zookeeper.server.ServerCnxnFactory;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.apache.zookeeper.server.quorum.QuorumPeer;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.apache.zookeeper.util.ServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressFBWarnings(
   value = {"IS2_INCONSISTENT_SYNC"},
   justification = "quorum peer is internally synchronized."
)
public class ZooKeeperServerController {
   private static final Logger LOG = LoggerFactory.getLogger(ZooKeeperServerController.class);
   private static final long DEFAULT_DELAY_MS = 1000L;
   private QuorumPeer quorumPeer;
   private ControllableConnectionFactory cnxnFactory;

   public ZooKeeperServerController(QuorumPeerConfig config) throws IOException {
      if (config == null) {
         throw new IllegalArgumentException("ZooKeeperServerController requires a valid config!");
      } else {
         this.cnxnFactory = new ControllableConnectionFactory();
         this.cnxnFactory.configure(config.getClientPortAddress(), config.getMaxClientCnxns(), config.getClientPortListenBacklog());
         this.quorumPeer = QuorumPeer.createFromConfig(config);
         this.quorumPeer.setCnxnFactory(this.cnxnFactory);
      }
   }

   public void run() {
      try {
         this.quorumPeer.start();
         this.quorumPeer.join();
      } catch (Exception ex) {
         LOG.error("Fatal error starting quorum peer", ex);
         ServiceUtils.requestSystemExit(ExitCode.UNEXPECTED_ERROR.getValue());
      }

   }

   protected ServerCnxnFactory getCnxnFactory() {
      return this.cnxnFactory;
   }

   public synchronized void shutdown() {
      if (this.cnxnFactory != null) {
         this.cnxnFactory.shutdown();
         this.cnxnFactory = null;
      }

      if (this.quorumPeer != null && this.quorumPeer.isRunning()) {
         this.quorumPeer.shutdown();
         this.quorumPeer = null;
      }

   }

   public synchronized boolean isReady() {
      return this.cnxnFactory != null && this.quorumPeer != null && this.quorumPeer.isRunning() && this.quorumPeer.getActiveServer() != null;
   }

   public void processCommand(ControlCommand command) {
      if (command == null) {
         throw new IllegalArgumentException("Invalid command parameter!");
      } else {
         LOG.info("processing command {}{}", command.getAction(), command.getParameter() == null ? "" : "[" + command.getParameter() + "]");
         if (!this.isReady()) {
            throw new IllegalStateException("Service is not ready. It has already been shutdown or is still initializing.");
         } else {
            switch (command.getAction()) {
               case PING:
               case REJECTCONNECTIONS:
                  break;
               case SHUTDOWN:
                  this.shutdown();
                  break;
               case CLOSECONNECTION:
                  if (command.getParameter() == null) {
                     this.cnxnFactory.closeAll(ServerCnxn.DisconnectReason.CLOSE_ALL_CONNECTIONS_FORCED);
                  } else {
                     this.cnxnFactory.closeSession(Long.decode(command.getParameter()), ServerCnxn.DisconnectReason.CONNECTION_CLOSE_FORCED);
                  }
                  break;
               case EXPIRESESSION:
                  if (command.getParameter() == null) {
                     this.expireAllSessions();
                  } else {
                     this.expireSession(Long.decode(command.getParameter()));
                  }
                  break;
               case ADDDELAY:
                  this.cnxnFactory.delayResponses(command.getParameter() == null ? 1000L : Long.decode(command.getParameter()));
                  break;
               case NORESPONSE:
                  if (command.getParameter() == null) {
                     this.cnxnFactory.holdAllFutureResponses();
                  } else {
                     this.cnxnFactory.holdFutureResponses(Long.decode(command.getParameter()));
                  }
                  break;
               case FAILREQUESTS:
                  if (command.getParameter() == null) {
                     this.cnxnFactory.failAllFutureRequests();
                  } else {
                     this.cnxnFactory.failFutureRequests(Long.decode(command.getParameter()));
                  }
                  break;
               case RESET:
                  this.cnxnFactory.resetBadBehavior();
                  break;
               case ELECTNEWLEADER:
                  this.quorumPeer.startLeaderElection();
                  break;
               default:
                  throw new IllegalArgumentException("Unknown command: " + command);
            }

         }
      }
   }

   private ZooKeeperServer getServer() {
      return this.quorumPeer.getActiveServer();
   }

   private void expireSession(long sessionId) {
      this.getServer().expire(sessionId);
   }

   private void expireAllSessions() {
      for(Long sessionId : this.getServer().getSessionTracker().localSessions()) {
         this.expireSession(sessionId);
      }

      for(Long sessionId : this.getServer().getSessionTracker().globalSessions()) {
         this.expireSession(sessionId);
      }

   }
}
