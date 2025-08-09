package org.apache.curator.framework.recipes.shared;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.WatcherRemoveCuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.BackgroundPathAndBytesable;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.api.ErrorListenerPathable;
import org.apache.curator.framework.api.Pathable;
import org.apache.curator.framework.api.WatchPathable;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.listen.StandardListenerManager;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.utils.PathUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SharedValue implements Closeable, SharedValueReader {
   private static final int NO_ZXID = -1;
   private static final int UNINITIALIZED_VERSION = -1;
   private final Logger log = LoggerFactory.getLogger(this.getClass());
   private final StandardListenerManager listeners = StandardListenerManager.standard();
   private final WatcherRemoveCuratorFramework client;
   private final String path;
   private final byte[] seedValue;
   private final AtomicReference state;
   private final AtomicReference currentValue;
   private final CuratorWatcher watcher;
   private final ConnectionStateListener connectionStateListener;
   private final BackgroundCallback upadateAndNotifyListenerCallback;

   public SharedValue(CuratorFramework client, String path, byte[] seedValue) {
      this.state = new AtomicReference(SharedValue.State.LATENT);
      this.connectionStateListener = new ConnectionStateListener() {
         public void stateChanged(CuratorFramework client, ConnectionState newState) {
            SharedValue.this.notifyListenerOfStateChanged(newState);
            if (newState.isConnected()) {
               try {
                  SharedValue.this.readValueAndNotifyListenersInBackground();
               } catch (Exception e) {
                  ThreadUtils.checkInterrupted(e);
                  SharedValue.this.log.error("Could not read value after reconnect", e);
               }
            }

         }
      };
      this.upadateAndNotifyListenerCallback = new BackgroundCallback() {
         public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
            if (event.getResultCode() == Code.OK.intValue()) {
               SharedValue.this.updateValue(event.getStat().getMzxid(), event.getStat().getVersion(), event.getData());
               SharedValue.this.notifyListeners();
            }

         }
      };
      this.client = client.newWatcherRemoveCuratorFramework();
      this.path = PathUtils.validatePath(path);
      this.seedValue = Arrays.copyOf(seedValue, seedValue.length);
      this.watcher = new SharedValueCuratorWatcher();
      this.currentValue = new AtomicReference(new VersionedValue(-1L, -1, Arrays.copyOf(seedValue, seedValue.length)));
   }

   @VisibleForTesting
   protected SharedValue(WatcherRemoveCuratorFramework client, String path, byte[] seedValue, CuratorWatcher watcher) {
      this.state = new AtomicReference(SharedValue.State.LATENT);
      this.connectionStateListener = new ConnectionStateListener() {
         public void stateChanged(CuratorFramework client, ConnectionState newState) {
            SharedValue.this.notifyListenerOfStateChanged(newState);
            if (newState.isConnected()) {
               try {
                  SharedValue.this.readValueAndNotifyListenersInBackground();
               } catch (Exception e) {
                  ThreadUtils.checkInterrupted(e);
                  SharedValue.this.log.error("Could not read value after reconnect", e);
               }
            }

         }
      };
      this.upadateAndNotifyListenerCallback = new BackgroundCallback() {
         public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
            if (event.getResultCode() == Code.OK.intValue()) {
               SharedValue.this.updateValue(event.getStat().getMzxid(), event.getStat().getVersion(), event.getData());
               SharedValue.this.notifyListeners();
            }

         }
      };
      this.client = client;
      this.path = PathUtils.validatePath(path);
      this.seedValue = Arrays.copyOf(seedValue, seedValue.length);
      this.watcher = watcher;
      this.currentValue = new AtomicReference(new VersionedValue(-1L, -1, Arrays.copyOf(seedValue, seedValue.length)));
   }

   public byte[] getValue() {
      VersionedValue<byte[]> localCopy = (VersionedValue)this.currentValue.get();
      return Arrays.copyOf((byte[])localCopy.getValue(), ((byte[])localCopy.getValue()).length);
   }

   public VersionedValue getVersionedValue() {
      VersionedValue<byte[]> localCopy = (VersionedValue)this.currentValue.get();
      return localCopy.mapValue((bytes) -> Arrays.copyOf(bytes, bytes.length));
   }

   public void setValue(byte[] newValue) throws Exception {
      Preconditions.checkState(this.state.get() == SharedValue.State.STARTED, "not started");
      Stat result = (Stat)this.client.setData().forPath(this.path, newValue);
      this.updateValue(result.getMzxid(), result.getVersion(), Arrays.copyOf(newValue, newValue.length));
   }

   /** @deprecated */
   @Deprecated
   public boolean trySetValue(byte[] newValue) throws Exception {
      return this.trySetValue((VersionedValue)this.currentValue.get(), newValue);
   }

   public boolean trySetValue(VersionedValue previous, byte[] newValue) throws Exception {
      Preconditions.checkState(this.state.get() == SharedValue.State.STARTED, "not started");
      VersionedValue<byte[]> current = (VersionedValue)this.currentValue.get();
      if (previous.getZxid() == current.getZxid() && Arrays.equals((byte[])previous.getValue(), (byte[])current.getValue())) {
         if (previous.getVersion() == -1) {
            throw new IllegalTrySetVersionException();
         } else {
            try {
               Stat result = (Stat)((BackgroundPathAndBytesable)this.client.setData().withVersion(previous.getVersion())).forPath(this.path, newValue);
               this.updateValue(result.getMzxid(), result.getVersion(), Arrays.copyOf(newValue, newValue.length));
               return true;
            } catch (KeeperException.BadVersionException var5) {
               this.readValue();
               return false;
            }
         }
      } else {
         return false;
      }
   }

   private void updateValue(long zxid, int version, byte[] bytes) {
      VersionedValue<byte[]> current;
      do {
         current = (VersionedValue)this.currentValue.get();
         if (current.getZxid() >= zxid) {
            return;
         }
      } while(!this.currentValue.compareAndSet(current, new VersionedValue(zxid, version, bytes)));

   }

   public Listenable getListenable() {
      return this.listeners;
   }

   public void start() throws Exception {
      Preconditions.checkState(this.state.compareAndSet(SharedValue.State.LATENT, SharedValue.State.STARTED), "Cannot be started more than once");
      this.client.getConnectionStateListenable().addListener(this.connectionStateListener);

      try {
         this.client.create().creatingParentContainersIfNeeded().forPath(this.path, this.seedValue);
      } catch (KeeperException.NodeExistsException var2) {
      }

      this.readValue();
   }

   public void close() throws IOException {
      this.state.set(SharedValue.State.CLOSED);
      this.client.removeWatchers();
      this.client.getConnectionStateListenable().removeListener(this.connectionStateListener);
      this.listeners.clear();
   }

   private void readValue() throws Exception {
      Stat localStat = new Stat();
      byte[] bytes = (byte[])((Pathable)((WatchPathable)this.client.getData().storingStatIn(localStat)).usingWatcher(this.watcher)).forPath(this.path);
      this.updateValue(localStat.getMzxid(), localStat.getVersion(), bytes);
   }

   private void readValueAndNotifyListenersInBackground() throws Exception {
      ((ErrorListenerPathable)((BackgroundPathable)this.client.getData().usingWatcher(this.watcher)).inBackground(this.upadateAndNotifyListenerCallback)).forPath(this.path);
   }

   private void notifyListeners() {
      byte[] localValue = this.getValue();
      this.listeners.forEach((listener) -> {
         try {
            listener.valueHasChanged(this, localValue);
         } catch (Exception e) {
            ThreadUtils.checkInterrupted(e);
            this.log.error("From SharedValue listener", e);
         }

      });
   }

   private void notifyListenerOfStateChanged(ConnectionState newState) {
      this.listeners.forEach((listener) -> listener.stateChanged(this.client, newState));
   }

   private class SharedValueCuratorWatcher implements CuratorWatcher {
      private SharedValueCuratorWatcher() {
      }

      public void process(WatchedEvent event) throws Exception {
         if (SharedValue.this.state.get() == SharedValue.State.STARTED && event.getType() != EventType.None) {
            SharedValue.this.readValueAndNotifyListenersInBackground();
         }

      }
   }

   private static enum State {
      LATENT,
      STARTED,
      CLOSED;
   }
}
