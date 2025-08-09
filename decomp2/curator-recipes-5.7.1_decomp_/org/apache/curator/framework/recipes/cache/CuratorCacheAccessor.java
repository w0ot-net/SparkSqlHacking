package org.apache.curator.framework.recipes.cache;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.curator.utils.ZKPaths;

public interface CuratorCacheAccessor {
   Optional get(String var1);

   int size();

   Stream stream();

   static Predicate parentPathFilter(String parentPath) {
      return (d) -> {
         ZKPaths.PathAndNode pathAndNode = ZKPaths.getPathAndNode(d.getPath());
         return pathAndNode.getPath().equals(parentPath);
      };
   }
}
