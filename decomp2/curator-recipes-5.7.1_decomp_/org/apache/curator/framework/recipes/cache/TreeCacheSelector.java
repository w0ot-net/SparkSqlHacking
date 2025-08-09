package org.apache.curator.framework.recipes.cache;

public interface TreeCacheSelector {
   boolean traverseChildren(String var1);

   boolean acceptChild(String var1);
}
