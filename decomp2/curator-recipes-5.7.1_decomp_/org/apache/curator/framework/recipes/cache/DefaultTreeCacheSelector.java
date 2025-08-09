package org.apache.curator.framework.recipes.cache;

public class DefaultTreeCacheSelector implements TreeCacheSelector {
   public boolean traverseChildren(String fullPath) {
      return true;
   }

   public boolean acceptChild(String fullPath) {
      return true;
   }
}
