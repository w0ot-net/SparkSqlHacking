package org.apache.curator.framework.recipes.locks;

import java.util.List;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.ACLBackgroundPathAndBytesable;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandardLockInternalsDriver implements LockInternalsDriver {
   private static final Logger log = LoggerFactory.getLogger(StandardLockInternalsDriver.class);

   public PredicateResults getsTheLock(CuratorFramework client, List children, String sequenceNodeName, int maxLeases) throws Exception {
      int ourIndex = children.indexOf(sequenceNodeName);
      validateOurIndex(sequenceNodeName, ourIndex);
      boolean getsTheLock = ourIndex < maxLeases;
      String pathToWatch = getsTheLock ? null : (String)children.get(ourIndex - maxLeases);
      return new PredicateResults(pathToWatch, getsTheLock);
   }

   protected String getSortingSequence() {
      return null;
   }

   public String createsTheLock(CuratorFramework client, String path, byte[] lockNodeBytes) throws Exception {
      CreateMode createMode = CreateMode.EPHEMERAL_SEQUENTIAL;
      String sequence = this.getSortingSequence();
      if (sequence != null) {
         path = path + sequence;
         createMode = CreateMode.EPHEMERAL;
      }

      String ourPath;
      if (lockNodeBytes != null) {
         ourPath = (String)((ACLBackgroundPathAndBytesable)client.create().creatingParentContainersIfNeeded().withProtection().withMode(createMode)).forPath(path, lockNodeBytes);
      } else {
         ourPath = (String)((ACLBackgroundPathAndBytesable)client.create().creatingParentContainersIfNeeded().withProtection().withMode(createMode)).forPath(path);
      }

      return ourPath;
   }

   public String fixForSorting(String str, String lockName) {
      return standardFixForSorting(str, lockName);
   }

   public static String standardFixForSorting(String str, String lockName) {
      int index = str.lastIndexOf(lockName);
      if (index >= 0) {
         index += lockName.length();
         return index <= str.length() ? str.substring(index) : "";
      } else {
         return str;
      }
   }

   static void validateOurIndex(String sequenceNodeName, int ourIndex) throws KeeperException {
      if (ourIndex < 0) {
         throw new KeeperException.NoNodeException("Sequential path not found: " + sequenceNodeName);
      }
   }
}
