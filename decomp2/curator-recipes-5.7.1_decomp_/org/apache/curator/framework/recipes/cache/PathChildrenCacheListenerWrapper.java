package org.apache.curator.framework.recipes.cache;

import java.util.Objects;
import org.apache.curator.framework.CuratorFramework;

class PathChildrenCacheListenerWrapper implements CuratorCacheListener {
   private final PathChildrenCacheListener listener;
   private final CuratorFramework client;
   private final String rootPath;

   PathChildrenCacheListenerWrapper(String rootPath, CuratorFramework client, PathChildrenCacheListener listener) {
      Objects.requireNonNull(rootPath, "rootPath cannot be null");
      this.rootPath = rootPath;
      this.listener = listener;
      this.client = client;
   }

   public void event(CuratorCacheListener.Type type, ChildData oldData, ChildData data) {
      switch (type) {
         case NODE_CREATED:
            if (this.rootPath.equals(data.getPath())) {
               return;
            }

            this.sendEvent(data, PathChildrenCacheEvent.Type.CHILD_ADDED);
            break;
         case NODE_CHANGED:
            if (this.rootPath.equals(data.getPath())) {
               return;
            }

            this.sendEvent(data, PathChildrenCacheEvent.Type.CHILD_UPDATED);
            break;
         case NODE_DELETED:
            if (this.rootPath.equals(oldData.getPath())) {
               return;
            }

            this.sendEvent(oldData, PathChildrenCacheEvent.Type.CHILD_REMOVED);
      }

   }

   public void initialized() {
      this.sendEvent((ChildData)null, PathChildrenCacheEvent.Type.INITIALIZED);
   }

   private void sendEvent(ChildData node, PathChildrenCacheEvent.Type type) {
      PathChildrenCacheEvent event = new PathChildrenCacheEvent(type, node);

      try {
         this.listener.childEvent(this.client, event);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
}
