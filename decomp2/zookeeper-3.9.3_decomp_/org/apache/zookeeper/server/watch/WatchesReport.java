package org.apache.zookeeper.server.watch;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WatchesReport {
   private final Map id2paths;

   WatchesReport(Map id2paths) {
      this.id2paths = Collections.unmodifiableMap(deepCopy(id2paths));
   }

   private static Map deepCopy(Map m) {
      Map<Long, Set<String>> m2 = new HashMap();

      for(Map.Entry e : m.entrySet()) {
         m2.put((Long)e.getKey(), new HashSet((Collection)e.getValue()));
      }

      return m2;
   }

   public boolean hasPaths(long sessionId) {
      return this.id2paths.containsKey(sessionId);
   }

   public Set getPaths(long sessionId) {
      Set<String> s = (Set)this.id2paths.get(sessionId);
      return s != null ? Collections.unmodifiableSet(s) : null;
   }

   public Map toMap() {
      return deepCopy(this.id2paths);
   }
}
