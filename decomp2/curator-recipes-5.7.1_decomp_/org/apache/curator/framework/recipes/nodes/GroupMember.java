package org.apache.curator.framework.recipes.nodes;

import java.io.Closeable;
import java.util.Iterator;
import java.util.Map;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheAccessor;
import org.apache.curator.framework.recipes.cache.CuratorCacheBridge;
import org.apache.curator.shaded.com.google.common.base.Preconditions;
import org.apache.curator.shaded.com.google.common.base.Throwables;
import org.apache.curator.shaded.com.google.common.collect.ImmutableMap;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.utils.ThreadUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;

public class GroupMember implements Closeable {
   private final PersistentNode pen;
   private final CuratorCacheBridge cache;
   private final String membershipPath;
   private final String thisId;

   public GroupMember(CuratorFramework client, String membershipPath, String thisId) {
      this(client, membershipPath, thisId, CuratorFrameworkFactory.getLocalAddress());
   }

   public GroupMember(CuratorFramework client, String membershipPath, String thisId, byte[] payload) {
      this.membershipPath = membershipPath;
      this.thisId = (String)Preconditions.checkNotNull(thisId, "thisId cannot be null");
      this.cache = CuratorCache.bridgeBuilder(client, membershipPath).build();
      this.pen = new PersistentNode(client, CreateMode.EPHEMERAL, false, ZKPaths.makePath(membershipPath, thisId), payload);
   }

   public void start() {
      this.pen.start();

      try {
         this.cache.start();
      } catch (Exception e) {
         ThreadUtils.checkInterrupted(e);
         Throwables.propagate(e);
      }

   }

   public void setThisData(byte[] data) {
      try {
         this.pen.setData(data);
      } catch (Exception e) {
         ThreadUtils.checkInterrupted(e);
         Throwables.propagate(e);
      }

   }

   public void close() {
      CloseableUtils.closeQuietly(this.cache);
      CloseableUtils.closeQuietly(this.pen);
   }

   public Map getCurrentMembers() {
      ImmutableMap.Builder<String, byte[]> builder = ImmutableMap.builder();
      boolean thisIdAdded = false;
      Iterator<ChildData> iterator = this.cache.stream().filter(CuratorCacheAccessor.parentPathFilter(this.membershipPath)).iterator();

      while(iterator.hasNext()) {
         ChildData data = (ChildData)iterator.next();
         String id = this.idFromPath(data.getPath());
         thisIdAdded = thisIdAdded || id.equals(this.thisId);
         builder.put(id, data.getData());
      }

      if (!thisIdAdded) {
         builder.put(this.thisId, this.pen.getData());
      }

      return builder.build();
   }

   public String idFromPath(String path) {
      return ZKPaths.getNodeFromPath(path);
   }
}
