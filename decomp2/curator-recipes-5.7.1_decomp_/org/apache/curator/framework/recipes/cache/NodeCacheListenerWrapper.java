package org.apache.curator.framework.recipes.cache;

class NodeCacheListenerWrapper implements CuratorCacheListener {
   private final NodeCacheListener listener;

   NodeCacheListenerWrapper(NodeCacheListener listener) {
      this.listener = listener;
   }

   public void event(CuratorCacheListener.Type type, ChildData oldData, ChildData data) {
      try {
         this.listener.nodeChanged();
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
}
