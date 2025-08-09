package org.apache.curator.framework.recipes.locks;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.shaded.com.google.common.base.Predicate;
import org.apache.curator.shaded.com.google.common.collect.ImmutableList;
import org.apache.curator.shaded.com.google.common.collect.Iterables;

public class InterProcessReadWriteLock {
   private final ReadLock readMutex;
   private final WriteLock writeMutex;
   private static final String READ_LOCK_NAME = "__READ__";
   private static final String WRITE_LOCK_NAME = "__WRIT__";

   public InterProcessReadWriteLock(CuratorFramework client, String basePath) {
      this(client, basePath, (byte[])null);
   }

   public InterProcessReadWriteLock(CuratorFramework client, String basePath, byte[] lockData) {
      this.writeMutex = new WriteLock(client, basePath, lockData);
      this.readMutex = new ReadLock(client, basePath, lockData, this.writeMutex);
   }

   protected InterProcessReadWriteLock(WriteLock writeLock, ReadLock readLock) {
      this.writeMutex = writeLock;
      this.readMutex = readLock;
   }

   public ReadLock readLock() {
      return this.readMutex;
   }

   public WriteLock writeLock() {
      return this.writeMutex;
   }

   private static class SortingLockInternalsDriver extends StandardLockInternalsDriver {
      private SortingLockInternalsDriver() {
      }

      public final String fixForSorting(String str, String lockName) {
         str = super.fixForSorting(str, "__READ__");
         str = super.fixForSorting(str, "__WRIT__");
         return str;
      }
   }

   private static class InternalInterProcessMutex extends InterProcessMutex {
      private final String lockName;
      private final byte[] lockData;

      InternalInterProcessMutex(CuratorFramework client, String path, String lockName, byte[] lockData, int maxLeases, LockInternalsDriver driver) {
         super(client, path, lockName, maxLeases, driver);
         this.lockName = lockName;
         this.lockData = lockData == null ? null : Arrays.copyOf(lockData, lockData.length);
      }

      public final Collection getParticipantNodes() throws Exception {
         return ImmutableList.copyOf(Iterables.filter(super.getParticipantNodes(), new Predicate() {
            public boolean apply(String node) {
               return node.contains(InternalInterProcessMutex.this.lockName);
            }
         }));
      }

      protected final byte[] getLockNodeBytes() {
         return this.lockData;
      }

      protected String getLockPath() {
         return super.getLockPath();
      }
   }

   public static class WriteLock extends InternalInterProcessMutex {
      public WriteLock(CuratorFramework client, String basePath, byte[] lockData) {
         super(client, basePath, "__WRIT__", lockData, 1, new SortingLockInternalsDriver());
      }

      public String getLockPath() {
         return super.getLockPath();
      }
   }

   public static class ReadLock extends InternalInterProcessMutex {
      public ReadLock(CuratorFramework client, String basePath, byte[] lockData, final WriteLock writeLock) {
         super(client, basePath, "__READ__", lockData, Integer.MAX_VALUE, new SortingLockInternalsDriver() {
            protected String getSortingSequence() {
               String writePath = writeLock.getLockPath();
               return writePath != null ? this.fixForSorting(writePath, "__WRIT__") : null;
            }

            public PredicateResults getsTheLock(CuratorFramework client, List children, String sequenceNodeName, int maxLeases) throws Exception {
               if (writeLock.isOwnedByCurrentThread()) {
                  return new PredicateResults((String)null, true);
               } else {
                  int index = 0;
                  int firstWriteIndex = Integer.MAX_VALUE;
                  int ourIndex = -1;

                  for(String node : children) {
                     if (node.contains("__WRIT__")) {
                        firstWriteIndex = Math.min(index, firstWriteIndex);
                     } else if (node.startsWith(sequenceNodeName)) {
                        ourIndex = index;
                        break;
                     }

                     ++index;
                  }

                  validateOurIndex(sequenceNodeName, ourIndex);
                  boolean getsTheLock = ourIndex < firstWriteIndex;
                  String pathToWatch = getsTheLock ? null : (String)children.get(firstWriteIndex);
                  return new PredicateResults(pathToWatch, getsTheLock);
               }
            }
         });
      }

      public String getLockPath() {
         return super.getLockPath();
      }
   }
}
