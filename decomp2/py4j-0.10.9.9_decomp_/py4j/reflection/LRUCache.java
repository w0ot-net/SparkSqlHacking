package py4j.reflection;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache extends LinkedHashMap {
   public static final int DEFAULT_CACHE_SIZE = 100;
   private static final long serialVersionUID = -3090703237387586885L;
   private int cacheSize;

   public LRUCache() {
      this(100);
   }

   public LRUCache(int cacheSize) {
      super(16, 0.75F, true);
      this.cacheSize = cacheSize;
   }

   protected boolean removeEldestEntry(Map.Entry eldest) {
      return this.size() > this.cacheSize;
   }
}
