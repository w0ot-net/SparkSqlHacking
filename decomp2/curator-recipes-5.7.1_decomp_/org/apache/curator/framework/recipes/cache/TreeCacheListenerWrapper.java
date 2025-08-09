package org.apache.curator.framework.recipes.cache;

import org.apache.curator.framework.CuratorFramework;

class TreeCacheListenerWrapper implements CuratorCacheListener {
   private final CuratorFramework client;
   private final TreeCacheListener listener;

   TreeCacheListenerWrapper(CuratorFramework client, TreeCacheListener listener) {
      this.client = client;
      this.listener = listener;
   }

   public void event(CuratorCacheListener.Type type, ChildData oldData, ChildData data) {
      switch (type) {
         case NODE_CREATED:
            this.sendEvent(data, (ChildData)null, TreeCacheEvent.Type.NODE_ADDED);
            break;
         case NODE_CHANGED:
            this.sendEvent(data, oldData, TreeCacheEvent.Type.NODE_UPDATED);
            break;
         case NODE_DELETED:
            this.sendEvent(oldData, (ChildData)null, TreeCacheEvent.Type.NODE_REMOVED);
      }

   }

   public void initialized() {
      this.sendEvent((ChildData)null, (ChildData)null, TreeCacheEvent.Type.INITIALIZED);
   }

   private void sendEvent(ChildData node, ChildData oldNode, TreeCacheEvent.Type type) {
      TreeCacheEvent event = new TreeCacheEvent(type, node, oldNode);

      try {
         this.listener.childEvent(this.client, event);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
}
