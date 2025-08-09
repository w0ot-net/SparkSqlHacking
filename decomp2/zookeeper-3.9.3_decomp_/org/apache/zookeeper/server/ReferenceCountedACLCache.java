package org.apache.zookeeper.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.jute.Index;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReferenceCountedACLCache {
   private static final Logger LOG = LoggerFactory.getLogger(ReferenceCountedACLCache.class);
   final Map longKeyMap = new HashMap();
   final Map aclKeyMap = new HashMap();
   final Map referenceCounter = new HashMap();
   private static final long OPEN_UNSAFE_ACL_ID = -1L;
   long aclIndex = 0L;

   public synchronized Long convertAcls(List acls) {
      if (acls == null) {
         return -1L;
      } else {
         Long ret = (Long)this.aclKeyMap.get(acls);
         if (ret == null) {
            ret = this.incrementIndex();
            this.longKeyMap.put(ret, acls);
            this.aclKeyMap.put(acls, ret);
         }

         this.addUsage(ret);
         return ret;
      }
   }

   public synchronized List convertLong(Long longVal) {
      if (longVal == null) {
         return null;
      } else if (longVal == -1L) {
         return ZooDefs.Ids.OPEN_ACL_UNSAFE;
      } else {
         List<ACL> acls = (List)this.longKeyMap.get(longVal);
         if (acls == null) {
            LOG.error("ERROR: ACL not available for long {}", longVal);
            throw new RuntimeException("Failed to fetch acls for " + longVal);
         } else {
            return acls;
         }
      }
   }

   private long incrementIndex() {
      return ++this.aclIndex;
   }

   public void deserialize(InputArchive ia) throws IOException {
      this.clear();
      int i = ia.readInt("map");

      LinkedHashMap<Long, List<ACL>> deserializedMap;
      for(deserializedMap = new LinkedHashMap(); i > 0; --i) {
         Long val = ia.readLong("long");
         List<ACL> aclList = new ArrayList();
         Index j = ia.startVector("acls");
         if (j == null) {
            throw new RuntimeException("Incorrent format of InputArchive when deserialize DataTree - missing acls");
         }

         while(!j.done()) {
            ACL acl = new ACL();
            acl.deserialize(ia, "acl");
            aclList.add(acl);
            j.incr();
         }

         deserializedMap.put(val, aclList);
      }

      synchronized(this) {
         for(Map.Entry entry : deserializedMap.entrySet()) {
            Long val = (Long)entry.getKey();
            List<ACL> aclList = (List)entry.getValue();
            if (this.aclIndex < val) {
               this.aclIndex = val;
            }

            this.longKeyMap.put(val, aclList);
            this.aclKeyMap.put(aclList, val);
            this.referenceCounter.put(val, new AtomicLongWithEquals(0L));
         }

      }
   }

   public void serialize(OutputArchive oa) throws IOException {
      Map<Long, List<ACL>> clonedLongKeyMap;
      synchronized(this) {
         clonedLongKeyMap = new HashMap(this.longKeyMap);
      }

      oa.writeInt(clonedLongKeyMap.size(), "map");

      for(Map.Entry val : clonedLongKeyMap.entrySet()) {
         oa.writeLong((Long)val.getKey(), "long");
         List<ACL> aclList = (List)val.getValue();
         oa.startVector(aclList, "acls");

         for(ACL acl : aclList) {
            acl.serialize(oa, "acl");
         }

         oa.endVector(aclList, "acls");
      }

   }

   public int size() {
      return this.aclKeyMap.size();
   }

   private void clear() {
      this.aclKeyMap.clear();
      this.longKeyMap.clear();
      this.referenceCounter.clear();
   }

   public synchronized void addUsage(Long acl) {
      if (acl != -1L) {
         if (!this.longKeyMap.containsKey(acl)) {
            LOG.info("Ignoring acl {} as it does not exist in the cache", acl);
         } else {
            AtomicLong count = (AtomicLong)this.referenceCounter.get(acl);
            if (count == null) {
               this.referenceCounter.put(acl, new AtomicLongWithEquals(1L));
            } else {
               count.incrementAndGet();
            }

         }
      }
   }

   public synchronized void removeUsage(Long acl) {
      if (acl != -1L) {
         if (!this.longKeyMap.containsKey(acl)) {
            LOG.info("Ignoring acl {} as it does not exist in the cache", acl);
         } else {
            long newCount = ((AtomicLongWithEquals)this.referenceCounter.get(acl)).decrementAndGet();
            if (newCount <= 0L) {
               this.referenceCounter.remove(acl);
               this.aclKeyMap.remove(this.longKeyMap.get(acl));
               this.longKeyMap.remove(acl);
            }

         }
      }
   }

   public synchronized void purgeUnused() {
      Iterator<Map.Entry<Long, AtomicLongWithEquals>> refCountIter = this.referenceCounter.entrySet().iterator();

      while(refCountIter.hasNext()) {
         Map.Entry<Long, AtomicLongWithEquals> entry = (Map.Entry)refCountIter.next();
         if (((AtomicLongWithEquals)entry.getValue()).get() <= 0L) {
            Long acl = (Long)entry.getKey();
            this.aclKeyMap.remove(this.longKeyMap.get(acl));
            this.longKeyMap.remove(acl);
            refCountIter.remove();
         }
      }

   }

   private static class AtomicLongWithEquals extends AtomicLong {
      private static final long serialVersionUID = 3355155896813725462L;

      public AtomicLongWithEquals(long i) {
         super(i);
      }

      public boolean equals(Object o) {
         if (this == o) {
            return true;
         } else {
            return o != null && this.getClass() == o.getClass() ? this.equals((AtomicLongWithEquals)o) : false;
         }
      }

      public boolean equals(AtomicLongWithEquals that) {
         return this.get() == that.get();
      }

      public int hashCode() {
         return 31 * Long.valueOf(this.get()).hashCode();
      }
   }
}
