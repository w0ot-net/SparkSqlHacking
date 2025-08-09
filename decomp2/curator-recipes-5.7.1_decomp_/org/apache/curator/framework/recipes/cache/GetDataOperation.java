package org.apache.curator.framework.recipes.cache;

import org.apache.curator.utils.PathUtils;

class GetDataOperation implements Operation {
   private final PathChildrenCache cache;
   private final String fullPath;

   GetDataOperation(PathChildrenCache cache, String fullPath) {
      this.cache = cache;
      this.fullPath = PathUtils.validatePath(fullPath);
   }

   public void invoke() throws Exception {
      this.cache.getDataAndStat(this.fullPath);
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         GetDataOperation that = (GetDataOperation)o;
         return this.fullPath.equals(that.fullPath);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.fullPath.hashCode();
   }

   public String toString() {
      return "GetDataOperation{fullPath='" + this.fullPath + '\'' + '}';
   }
}
