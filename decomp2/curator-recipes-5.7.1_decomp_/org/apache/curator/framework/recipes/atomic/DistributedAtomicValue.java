package org.apache.curator.framework.recipes.atomic;

import java.util.Arrays;
import org.apache.curator.RetryLoop;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundPathAndBytesable;
import org.apache.curator.framework.api.WatchPathable;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.utils.PathUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

public class DistributedAtomicValue {
   private final CuratorFramework client;
   private final String path;
   private final RetryPolicy retryPolicy;
   private final PromotedToLock promotedToLock;
   private final InterProcessMutex mutex;

   public DistributedAtomicValue(CuratorFramework client, String path, RetryPolicy retryPolicy) {
      this(client, path, retryPolicy, (PromotedToLock)null);
   }

   public DistributedAtomicValue(CuratorFramework client, String path, RetryPolicy retryPolicy, PromotedToLock promotedToLock) {
      this.client = client;
      this.path = PathUtils.validatePath(path);
      this.retryPolicy = retryPolicy;
      this.promotedToLock = promotedToLock;
      this.mutex = promotedToLock != null ? new InterProcessMutex(client, promotedToLock.getPath()) : null;
   }

   public AtomicValue get() throws Exception {
      MutableAtomicValue<byte[]> result = new MutableAtomicValue((Object)null, (Object)null, false);
      this.getCurrentValue(result, new Stat());
      result.postValue = result.preValue;
      result.succeeded = true;
      return result;
   }

   public void forceSet(byte[] newValue) throws Exception {
      try {
         this.client.setData().forPath(this.path, newValue);
      } catch (KeeperException.NoNodeException var5) {
         try {
            this.client.create().creatingParentContainersIfNeeded().forPath(this.path, newValue);
         } catch (KeeperException.NodeExistsException var4) {
            this.client.setData().forPath(this.path, newValue);
         }
      }

   }

   public AtomicValue compareAndSet(byte[] expectedValue, byte[] newValue) throws Exception {
      Stat stat = new Stat();
      MutableAtomicValue<byte[]> result = new MutableAtomicValue((Object)null, (Object)null, false);
      boolean createIt = this.getCurrentValue(result, stat);
      if (!createIt && Arrays.equals(expectedValue, (byte[])result.preValue)) {
         try {
            ((BackgroundPathAndBytesable)this.client.setData().withVersion(stat.getVersion())).forPath(this.path, newValue);
            result.succeeded = true;
            result.postValue = newValue;
         } catch (KeeperException.BadVersionException var7) {
            result.succeeded = false;
         } catch (KeeperException.NoNodeException var8) {
            result.succeeded = false;
         }
      } else {
         result.succeeded = false;
      }

      return result;
   }

   public AtomicValue trySet(final byte[] newValue) throws Exception {
      MutableAtomicValue<byte[]> result = new MutableAtomicValue((Object)null, (Object)null, false);
      MakeValue makeValue = new MakeValue() {
         public byte[] makeFrom(byte[] previous) {
            return newValue;
         }
      };
      this.tryOptimistic(result, makeValue);
      if (!result.succeeded() && this.mutex != null) {
         this.tryWithMutex(result, makeValue);
      }

      return result;
   }

   public boolean initialize(byte[] value) throws Exception {
      try {
         this.client.create().creatingParentContainersIfNeeded().forPath(this.path, value);
         return true;
      } catch (KeeperException.NodeExistsException var3) {
         return false;
      }
   }

   AtomicValue trySet(MakeValue makeValue) throws Exception {
      MutableAtomicValue<byte[]> result = new MutableAtomicValue((Object)null, (Object)null, false);
      this.tryOptimistic(result, makeValue);
      if (!result.succeeded() && this.mutex != null) {
         this.tryWithMutex(result, makeValue);
      }

      return result;
   }

   RuntimeException createCorruptionException(byte[] bytes) {
      StringBuilder str = new StringBuilder();
      str.append('[');
      boolean first = true;

      for(byte b : bytes) {
         if (first) {
            first = false;
         } else {
            str.append(", ");
         }

         str.append("0x").append(Integer.toHexString(b & 255));
      }

      str.append(']');
      return new RuntimeException(String.format("Corrupted data for node \"%s\": %s", this.path, str.toString()));
   }

   private boolean getCurrentValue(MutableAtomicValue result, Stat stat) throws Exception {
      boolean createIt = false;

      try {
         result.preValue = ((WatchPathable)this.client.getData().storingStatIn(stat)).forPath(this.path);
      } catch (KeeperException.NoNodeException var5) {
         result.preValue = null;
         createIt = true;
      }

      return createIt;
   }

   private void tryWithMutex(MutableAtomicValue result, MakeValue makeValue) throws Exception {
      long startMs = System.currentTimeMillis();
      int retryCount = 0;
      if (this.mutex.acquire(this.promotedToLock.getMaxLockTime(), this.promotedToLock.getMaxLockTimeUnit())) {
         try {
            boolean done = false;

            while(!done) {
               result.stats.incrementPromotedTries();
               if (this.tryOnce(result, makeValue)) {
                  result.succeeded = true;
                  done = true;
               } else if (!this.promotedToLock.getRetryPolicy().allowRetry(retryCount++, System.currentTimeMillis() - startMs, RetryLoop.getDefaultRetrySleeper())) {
                  done = true;
               }
            }
         } finally {
            this.mutex.release();
         }
      }

      result.stats.setPromotedTimeMs(System.currentTimeMillis() - startMs);
   }

   private void tryOptimistic(MutableAtomicValue result, MakeValue makeValue) throws Exception {
      long startMs = System.currentTimeMillis();
      int retryCount = 0;
      boolean done = false;

      while(!done) {
         result.stats.incrementOptimisticTries();
         if (this.tryOnce(result, makeValue)) {
            result.succeeded = true;
            done = true;
         } else if (!this.retryPolicy.allowRetry(retryCount++, System.currentTimeMillis() - startMs, RetryLoop.getDefaultRetrySleeper())) {
            done = true;
         }
      }

      result.stats.setOptimisticTimeMs(System.currentTimeMillis() - startMs);
   }

   private boolean tryOnce(MutableAtomicValue result, MakeValue makeValue) throws Exception {
      Stat stat = new Stat();
      boolean createIt = this.getCurrentValue(result, stat);
      boolean success = false;

      try {
         byte[] newValue = makeValue.makeFrom((byte[])result.preValue);
         if (createIt) {
            this.client.create().creatingParentContainersIfNeeded().forPath(this.path, newValue);
         } else {
            ((BackgroundPathAndBytesable)this.client.setData().withVersion(stat.getVersion())).forPath(this.path, newValue);
         }

         result.postValue = Arrays.copyOf(newValue, newValue.length);
         success = true;
      } catch (KeeperException.NodeExistsException var7) {
      } catch (KeeperException.BadVersionException var8) {
      } catch (KeeperException.NoNodeException var9) {
      }

      return success;
   }
}
