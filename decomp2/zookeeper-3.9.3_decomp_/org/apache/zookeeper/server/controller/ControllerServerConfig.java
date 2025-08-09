package org.apache.zookeeper.server.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.quorum.QuorumPeer;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;
import org.apache.zookeeper.server.quorum.flexible.QuorumMaj;

public class ControllerServerConfig extends QuorumPeerConfig {
   public static final String CONTROLLER_PORT_KEY = "zookeeper.controllerPort";
   public static final String CLIENT_PORT_KEY = "zookeeper.clientPortAddress";
   private InetSocketAddress controllerAddress;

   public InetSocketAddress getControllerAddress() {
      return this.controllerAddress;
   }

   public ControllerServerConfig(String configFile) throws QuorumPeerConfig.ConfigException {
      this.parse(configFile);
   }

   public ControllerServerConfig(InetAddress hostAddress, int controllerPort, int zkServerPort, String dataDirPath) {
      this.controllerAddress = new InetSocketAddress(hostAddress, controllerPort);
      this.clientPortAddress = new InetSocketAddress(hostAddress, zkServerPort);
      this.dataDir = new File(dataDirPath);
      this.dataLogDir = this.dataDir;
      this.serverId = 0L;
   }

   public ControllerServerConfig(int controllerPort, int zkServerPort, String dataDirPath) {
      this(InetAddress.getLoopbackAddress(), controllerPort, zkServerPort, dataDirPath);
   }

   public ServerConfig getZooKeeperServerConfig() {
      ServerConfig serverConfig = new ServerConfig();
      serverConfig.readFrom(this);
      return serverConfig;
   }

   public void parse(String configFile) throws QuorumPeerConfig.ConfigException {
      super.parse(configFile);

      for(String key : System.getProperties().stringPropertyNames()) {
         if ("zookeeper.controllerPort".equalsIgnoreCase(key)) {
            this.setControllerAddress(System.getProperty(key));
         }

         if ("zookeeper.clientPortAddress".equals(key)) {
            this.setClientAddress(System.getProperty(key));
         }
      }

      if (this.controllerAddress == null) {
         throw new QuorumPeerConfig.ConfigException("Missing required parameter zookeeper.controllerPort");
      } else if (this.clientPortAddress == null) {
         throw new QuorumPeerConfig.ConfigException("Missing required parameter zookeeper.clientPortAddress");
      }
   }

   private void setControllerAddress(String port) {
      try {
         this.controllerAddress = new InetSocketAddress(InetAddress.getLoopbackAddress(), Integer.parseInt(port));
      } catch (NumberFormatException ex) {
         throw new IllegalArgumentException("Invalid port", ex);
      }
   }

   private void setClientAddress(String port) {
      try {
         this.clientPortAddress = new InetSocketAddress(InetAddress.getLoopbackAddress(), Integer.parseInt(port));
      } catch (NumberFormatException ex) {
         throw new IllegalArgumentException("Invalid port", ex);
      }
   }

   public void ensureComplete() throws IOException {
      if (this.quorumVerifier == null || this.quorumVerifier.getAllMembers().size() <= 0) {
         ServerSocket randomSocket1 = new ServerSocket(0);
         int quorumPort = randomSocket1.getLocalPort();
         ServerSocket randomSocket2 = new ServerSocket(0);
         int electionPort = randomSocket2.getLocalPort();
         randomSocket1.close();
         randomSocket2.close();
         QuorumPeer.QuorumServer selfAsPeer = new QuorumPeer.QuorumServer(0L, new InetSocketAddress(quorumPort), new InetSocketAddress(electionPort), this.clientPortAddress);
         Map<Long, QuorumPeer.QuorumServer> peers = new HashMap();
         peers.put(selfAsPeer.id, selfAsPeer);
         this.quorumVerifier = new QuorumMaj(peers);
      }
   }
}
