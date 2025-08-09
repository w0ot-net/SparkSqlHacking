package org.apache.curator.framework.recipes.locks;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** @deprecated */
@Deprecated
public class InterProcessSemaphore {
   private final Logger log;
   private final LockInternals internals;
   private static final String LOCK_NAME = "lock-";

   public InterProcessSemaphore(CuratorFramework client, String path, int maxLeases) {
      this(client, path, maxLeases, (SharedCountReader)null);
   }

   public InterProcessSemaphore(CuratorFramework client, String path, SharedCountReader count) {
      this(client, path, 0, count);
   }

   private InterProcessSemaphore(CuratorFramework client, String path, int maxLeases, SharedCountReader count) {
      this.log = LoggerFactory.getLogger(this.getClass());
      this.internals = new LockInternals(client, new StandardLockInternalsDriver(), path, "lock-", count != null ? count.getCount() : maxLeases);
      if (count != null) {
         count.addListener(new SharedCountListener() {
            public void countHasChanged(SharedCountReader sharedCount, int newCount) throws Exception {
               InterProcessSemaphore.this.internals.setMaxLeases(newCount);
            }

            public void stateChanged(CuratorFramework client, ConnectionState newState) {
            }
         });
      }

   }

   public void returnAll(Collection leases) {
      for(Lease l : leases) {
         CloseableUtils.closeQuietly(l);
      }

   }

   public void returnLease(Lease lease) {
      CloseableUtils.closeQuietly(lease);
   }

   public Lease acquire() throws Exception {
      String path = this.internals.attemptLock(-1L, (TimeUnit)null, (byte[])null);
      return this.makeLease(path);
   }

   public Collection acquire(int qty) throws Exception {
      Preconditions.checkArgument(qty > 0, "qty cannot be 0");
      ImmutableList.Builder<Lease> builder = ImmutableList.builder();

      try {
         while(qty-- > 0) {
            String path = this.internals.attemptLock(-1L, (TimeUnit)null, (byte[])null);
            builder.add(this.makeLease(path));
         }
      } catch (Exception e) {
         ThreadUtils.checkInterrupted(e);
         this.returnAll(builder.build());
         throw e;
      }

      return builder.build();
   }

   public Lease acquire(long time, TimeUnit unit) throws Exception {
      String path = this.internals.attemptLock(time, unit, (byte[])null);
      return path != null ? this.makeLease(path) : null;
   }

   public Collection acquire(int qty, long time, TimeUnit unit) throws Exception {
      long startMs = System.currentTimeMillis();
      long waitMs = TimeUnit.MILLISECONDS.convert(time, unit);
      Preconditions.checkArgument(qty > 0, "qty cannot be 0");
      ImmutableList.Builder<Lease> builder = ImmutableList.builder();

      try {
         while(qty-- > 0) {
            long elapsedMs = System.currentTimeMillis() - startMs;
            long thisWaitMs = waitMs - elapsedMs;
            String path = thisWaitMs > 0L ? this.internals.attemptLock(thisWaitMs, TimeUnit.MILLISECONDS, (byte[])null) : null;
            if (path == null) {
               this.returnAll(builder.build());
               return null;
            }

            builder.add(this.makeLease(path));
         }
      } catch (Exception e) {
         ThreadUtils.checkInterrupted(e);
         this.returnAll(builder.build());
         throw e;
      }

      return builder.build();
   }

   private Lease makeLease(final String path) {
      return new Lease() {
         public void close() throws IOException {
            try {
               InterProcessSemaphore.this.internals.releaseLock(path);
            } catch (KeeperException.NoNodeException e) {
               InterProcessSemaphore.this.log.warn("Lease already released", e);
            } catch (Exception e) {
               ThreadUtils.checkInterrupted(e);
               throw new IOException(e);
            }

         }

         public byte[] getData() throws Exception {
            return (byte[])InterProcessSemaphore.this.internals.getClient().getData().forPath(path);
         }

         public String getNodeName() {
            return ZKPaths.getNodeFromPath(path);
         }
      };
   }
}
