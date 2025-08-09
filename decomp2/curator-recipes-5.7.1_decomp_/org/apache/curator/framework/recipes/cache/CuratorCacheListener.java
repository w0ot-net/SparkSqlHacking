package org.apache.curator.framework.recipes.cache;

@FunctionalInterface
public interface CuratorCacheListener {
   void event(Type var1, ChildData var2, ChildData var3);

   default void initialized() {
   }

   static CuratorCacheListenerBuilder builder() {
      return new CuratorCacheListenerBuilderImpl();
   }

   public static enum Type {
      NODE_CREATED,
      NODE_CHANGED,
      NODE_DELETED;
   }
}
