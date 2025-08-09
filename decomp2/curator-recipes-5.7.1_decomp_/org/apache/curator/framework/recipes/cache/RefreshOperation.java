package org.apache.curator.framework.recipes.cache;

class RefreshOperation implements Operation {
   private final PathChildrenCache cache;
   private final PathChildrenCache.RefreshMode mode;

   RefreshOperation(PathChildrenCache cache, PathChildrenCache.RefreshMode mode) {
      this.cache = cache;
      this.mode = mode;
   }

   public void invoke() throws Exception {
      this.cache.refresh(this.mode);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         RefreshOperation that = (RefreshOperation)o;
         return this.mode == that.mode;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.mode.hashCode();
   }

   public String toString() {
      return "RefreshOperation(" + this.mode + "){}";
   }
}
