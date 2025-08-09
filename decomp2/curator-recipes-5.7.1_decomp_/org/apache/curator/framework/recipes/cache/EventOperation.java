package org.apache.curator.framework.recipes.cache;

class EventOperation implements Operation {
   private final PathChildrenCache cache;
   private final PathChildrenCacheEvent event;

   EventOperation(PathChildrenCache cache, PathChildrenCacheEvent event) {
      this.cache = cache;
      this.event = event;
   }

   public void invoke() {
      this.cache.callListeners(this.event);
   }

   public String toString() {
      return "EventOperation{event=" + this.event + '}';
   }
}
