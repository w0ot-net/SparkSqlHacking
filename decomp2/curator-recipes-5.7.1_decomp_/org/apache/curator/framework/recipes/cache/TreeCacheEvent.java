package org.apache.curator.framework.recipes.cache;

public class TreeCacheEvent {
   private final Type type;
   private final ChildData data;
   private final ChildData oldData;

   public TreeCacheEvent(Type type, ChildData data) {
      this(type, data, (ChildData)null);
   }

   public TreeCacheEvent(Type type, ChildData data, ChildData oldData) {
      this.type = type;
      this.data = data;
      this.oldData = oldData;
   }

   public Type getType() {
      return this.type;
   }

   public ChildData getData() {
      return this.data;
   }

   public ChildData getOldData() {
      return this.oldData;
   }

   public String toString() {
      return TreeCacheEvent.class.getSimpleName() + "{type=" + this.type + ", data=" + this.data + '}';
   }

   public static enum Type {
      NODE_ADDED,
      NODE_UPDATED,
      NODE_REMOVED,
      CONNECTION_SUSPENDED,
      CONNECTION_RECONNECTED,
      CONNECTION_LOST,
      INITIALIZED;
   }
}
