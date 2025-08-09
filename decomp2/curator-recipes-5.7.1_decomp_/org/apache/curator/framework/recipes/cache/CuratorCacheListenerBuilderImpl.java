package org.apache.curator.framework.recipes.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.apache.curator.framework.CuratorFramework;

class CuratorCacheListenerBuilderImpl implements CuratorCacheListenerBuilder {
   private final List listeners = new ArrayList();
   private boolean afterInitializedOnly = false;

   public CuratorCacheListenerBuilder forAll(CuratorCacheListener listener) {
      this.listeners.add(listener);
      return this;
   }

   public CuratorCacheListenerBuilder forCreates(Consumer listener) {
      this.listeners.add((CuratorCacheListener)(type, oldNode, node) -> {
         if (type == CuratorCacheListener.Type.NODE_CREATED) {
            listener.accept(node);
         }

      });
      return this;
   }

   public CuratorCacheListenerBuilder forChanges(CuratorCacheListenerBuilder.ChangeListener listener) {
      this.listeners.add((CuratorCacheListener)(type, oldNode, node) -> {
         if (type == CuratorCacheListener.Type.NODE_CHANGED) {
            listener.event(oldNode, node);
         }

      });
      return this;
   }

   public CuratorCacheListenerBuilder forCreatesAndChanges(CuratorCacheListenerBuilder.ChangeListener listener) {
      this.listeners.add((CuratorCacheListener)(type, oldNode, node) -> {
         if (type == CuratorCacheListener.Type.NODE_CHANGED || type == CuratorCacheListener.Type.NODE_CREATED) {
            listener.event(oldNode, node);
         }

      });
      return this;
   }

   public CuratorCacheListenerBuilder forDeletes(Consumer listener) {
      this.listeners.add((CuratorCacheListener)(type, oldNode, node) -> {
         if (type == CuratorCacheListener.Type.NODE_DELETED) {
            listener.accept(oldNode);
         }

      });
      return this;
   }

   public CuratorCacheListenerBuilder forInitialized(final Runnable listener) {
      CuratorCacheListener localListener = new CuratorCacheListener() {
         public void event(CuratorCacheListener.Type type, ChildData oldData, ChildData data) {
         }

         public void initialized() {
            listener.run();
         }
      };
      this.listeners.add(localListener);
      return this;
   }

   public CuratorCacheListenerBuilder forPathChildrenCache(String rootPath, CuratorFramework client, PathChildrenCacheListener listener) {
      this.listeners.add(new PathChildrenCacheListenerWrapper(rootPath, client, listener));
      return this;
   }

   public CuratorCacheListenerBuilder forTreeCache(CuratorFramework client, TreeCacheListener listener) {
      this.listeners.add(new TreeCacheListenerWrapper(client, listener));
      return this;
   }

   public CuratorCacheListenerBuilder forNodeCache(NodeCacheListener listener) {
      this.listeners.add(new NodeCacheListenerWrapper(listener));
      return this;
   }

   public CuratorCacheListenerBuilder afterInitialized() {
      this.afterInitializedOnly = true;
      return this;
   }

   public CuratorCacheListener build() {
      final List<CuratorCacheListener> copy = new ArrayList(this.listeners);
      return new CuratorCacheListener() {
         private volatile boolean isInitialized;

         {
            this.isInitialized = !CuratorCacheListenerBuilderImpl.this.afterInitializedOnly;
         }

         public void event(CuratorCacheListener.Type type, ChildData oldData, ChildData data) {
            if (this.isInitialized) {
               copy.forEach((l) -> l.event(type, oldData, data));
            }

         }

         public void initialized() {
            this.isInitialized = true;
            copy.forEach(CuratorCacheListener::initialized);
         }
      };
   }
}
