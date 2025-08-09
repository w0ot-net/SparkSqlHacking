package org.apache.curator.framework.recipes.shared;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.Executor;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.shaded.com.google.common.annotations.VisibleForTesting;
import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.apache.curator.shaded.com.google.common.util.concurrent.MoreExecutors;

public class SharedCount implements Closeable, SharedCountReader, Listenable {
   private final Map listeners = Maps.newConcurrentMap();
   private final SharedValue sharedValue;

   public SharedCount(CuratorFramework client, String path, int seedValue) {
      this.sharedValue = new SharedValue(client, path, toBytes(seedValue));
   }

   protected SharedCount(CuratorFramework client, String path, SharedValue sv) {
      this.sharedValue = sv;
   }

   public int getCount() {
      return fromBytes(this.sharedValue.getValue());
   }

   public VersionedValue getVersionedValue() {
      VersionedValue<byte[]> localValue = this.sharedValue.getVersionedValue();
      return localValue.mapValue(SharedCount::fromBytes);
   }

   public void setCount(int newCount) throws Exception {
      this.sharedValue.setValue(toBytes(newCount));
   }

   /** @deprecated */
   @Deprecated
   public boolean trySetCount(int newCount) throws Exception {
      return this.sharedValue.trySetValue(toBytes(newCount));
   }

   public boolean trySetCount(VersionedValue previous, int newCount) throws Exception {
      VersionedValue<byte[]> previousCopy = previous.mapValue(SharedCount::toBytes);
      return this.sharedValue.trySetValue(previousCopy, toBytes(newCount));
   }

   public void addListener(SharedCountListener listener) {
      this.addListener(listener, MoreExecutors.directExecutor());
   }

   public void addListener(final SharedCountListener listener, Executor executor) {
      SharedValueListener valueListener = new SharedValueListener() {
         public void valueHasChanged(SharedValueReader sharedValue, byte[] newValue) throws Exception {
            listener.countHasChanged(SharedCount.this, SharedCount.fromBytes(newValue));
         }

         public void stateChanged(CuratorFramework client, ConnectionState newState) {
            listener.stateChanged(client, newState);
         }
      };
      this.sharedValue.getListenable().addListener(valueListener, executor);
      this.listeners.put(listener, valueListener);
   }

   public void removeListener(SharedCountListener listener) {
      SharedValueListener valueListener = (SharedValueListener)this.listeners.remove(listener);
      if (valueListener != null) {
         this.sharedValue.getListenable().removeListener(valueListener);
      }

   }

   public void start() throws Exception {
      this.sharedValue.start();
   }

   public void close() throws IOException {
      this.sharedValue.close();
   }

   @VisibleForTesting
   static byte[] toBytes(int value) {
      byte[] bytes = new byte[4];
      ByteBuffer.wrap(bytes).putInt(value);
      return bytes;
   }

   private static int fromBytes(byte[] bytes) {
      return ByteBuffer.wrap(bytes).getInt();
   }
}
