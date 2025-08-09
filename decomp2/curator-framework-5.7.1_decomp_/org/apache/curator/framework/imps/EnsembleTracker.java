package org.apache.curator.framework.imps;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.ensemble.EnsembleProvider;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.WatcherRemoveCuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.BackgroundEnsembleable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorEventType;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.ErrorListenerEnsembleable;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.apache.curator.utils.Compatibility;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.server.quorum.QuorumPeer;
import org.apache.zookeeper.server.quorum.flexible.QuorumMaj;
import org.apache.zookeeper.server.quorum.flexible.QuorumVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@VisibleForTesting
public class EnsembleTracker implements Closeable, CuratorWatcher {
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private final WatcherRemoveCuratorFramework client;
   private final EnsembleProvider ensembleProvider;
   private final AtomicReference state;
   private final AtomicInteger outstanding;
   private final AtomicReference currentConfig;
   private final ConnectionStateListener connectionStateListener;

   EnsembleTracker(CuratorFramework client, EnsembleProvider ensembleProvider) {
      this.state = new AtomicReference(EnsembleTracker.State.LATENT);
      this.outstanding = new AtomicInteger(0);
      this.currentConfig = new AtomicReference(new QuorumMaj(Maps.newHashMap()));
      this.connectionStateListener = new ConnectionStateListener() {
         public void stateChanged(CuratorFramework client, ConnectionState newState) {
            if (newState == ConnectionState.CONNECTED || newState == ConnectionState.RECONNECTED) {
               try {
                  EnsembleTracker.this.reset();
               } catch (Exception e) {
                  EnsembleTracker.this.log.error("Trying to reset after reconnection", e);
               }
            }

         }

         public boolean doNotProxy() {
            return true;
         }
      };
      this.client = client.newWatcherRemoveCuratorFramework();
      this.ensembleProvider = ensembleProvider;
   }

   public void start() throws Exception {
      Preconditions.checkState(this.state.compareAndSet(EnsembleTracker.State.LATENT, EnsembleTracker.State.STARTED), "Cannot be started more than once");
      this.client.getConnectionStateListenable().addListener(this.connectionStateListener);
      this.reset();
   }

   public void close() {
      if (this.state.compareAndSet(EnsembleTracker.State.STARTED, EnsembleTracker.State.CLOSED)) {
         this.client.removeWatchers();
         this.client.getConnectionStateListenable().removeListener(this.connectionStateListener);
      }

   }

   public void process(WatchedEvent event) throws Exception {
      if (event.getType() == EventType.NodeDataChanged) {
         this.reset();
      }

   }

   public QuorumVerifier getCurrentConfig() {
      return (QuorumVerifier)this.currentConfig.get();
   }

   @VisibleForTesting
   public boolean hasOutstanding() {
      return this.outstanding.get() > 0;
   }

   private void reset() throws Exception {
      if (this.client.getState() == CuratorFrameworkState.STARTED && this.state.get() == EnsembleTracker.State.STARTED) {
         BackgroundCallback backgroundCallback = new BackgroundCallback() {
            public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
               EnsembleTracker.this.outstanding.decrementAndGet();
               if (event.getType() == CuratorEventType.GET_CONFIG && event.getResultCode() == Code.OK.intValue()) {
                  EnsembleTracker.this.processConfigData(event.getData());
               }

            }
         };
         this.outstanding.incrementAndGet();

         try {
            ((ErrorListenerEnsembleable)((BackgroundEnsembleable)this.client.getConfig().usingWatcher(this)).inBackground(backgroundCallback)).forEnsemble();
            this.outstanding.incrementAndGet();
         } finally {
            this.outstanding.decrementAndGet();
         }
      }

   }

   @VisibleForTesting
   public static String configToConnectionString(QuorumVerifier data) throws Exception {
      StringBuilder sb = new StringBuilder();
      List<QuorumPeer.QuorumServer> servers = new ArrayList(data.getAllMembers().values());
      Collections.sort(servers, (a, b) -> a.toString().compareTo(b.toString()));

      for(QuorumPeer.QuorumServer server : servers) {
         if (server.clientAddr != null) {
            if (sb.length() != 0) {
               sb.append(",");
            }

            sb.append(getHostString(server)).append(":").append(server.clientAddr.getPort());
         }
      }

      return sb.toString();
   }

   private static String getHostString(QuorumPeer.QuorumServer server) {
      InetSocketAddress clientAddr = server.clientAddr;
      InetAddress clientIpAddr = clientAddr.getAddress();
      return clientIpAddr != null && clientIpAddr.isAnyLocalAddress() ? Compatibility.getHostString(server) : clientAddr.getHostString();
   }

   private void processConfigData(byte[] data) throws Exception {
      Properties properties = new Properties();
      properties.load(new ByteArrayInputStream(data));
      this.log.info("New config event received: {}", properties);
      if (!properties.isEmpty()) {
         QuorumMaj newConfig = new QuorumMaj(properties);
         String connectionString = configToConnectionString(newConfig).trim();
         if (!connectionString.isEmpty()) {
            this.currentConfig.set(newConfig);
            String oldConnectionString = this.ensembleProvider.getConnectionString();
            int i = oldConnectionString.indexOf(47);
            if (i >= 0) {
               String chroot = oldConnectionString.substring(i);
               connectionString = connectionString + chroot;
            }

            this.ensembleProvider.setConnectionString(connectionString);
         } else {
            this.log.debug("Invalid config event received: {}", properties);
         }
      } else {
         this.log.debug("Ignoring new config as it is empty");
      }

   }

   private static enum State {
      LATENT,
      STARTED,
      CLOSED;
   }
}
