package org.apache.curator.framework.recipes.cache;

import java.util.List;

public class PathChildrenCacheEvent {
   private final Type type;
   private final ChildData data;

   public PathChildrenCacheEvent(Type type, ChildData data) {
      this.type = type;
      this.data = data;
   }

   public Type getType() {
      return this.type;
   }

   public ChildData getData() {
      return this.data;
   }

   public List getInitialData() {
      return null;
   }

   public String toString() {
      return "PathChildrenCacheEvent{type=" + this.type + ", data=" + this.data + '}';
   }

   public static enum Type {
      CHILD_ADDED,
      CHILD_UPDATED,
      CHILD_REMOVED,
      CONNECTION_SUSPENDED,
      CONNECTION_RECONNECTED,
      CONNECTION_LOST,
      INITIALIZED;
   }
}
