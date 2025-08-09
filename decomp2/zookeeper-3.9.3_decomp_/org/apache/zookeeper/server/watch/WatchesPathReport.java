package org.apache.zookeeper.server.watch;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WatchesPathReport {
   private final Map path2Ids;

   WatchesPathReport(Map path2Ids) {
      this.path2Ids = Collections.unmodifiableMap(deepCopy(path2Ids));
   }

   private static Map deepCopy(Map m) {
      Map<String, Set<Long>> m2 = new HashMap();

      for(Map.Entry e : m.entrySet()) {
         m2.put((String)e.getKey(), new HashSet((Collection)e.getValue()));
      }

      return m2;
   }

   public boolean hasSessions(String path) {
      return this.path2Ids.containsKey(path);
   }

   public Set getSessions(String path) {
      Set<Long> s = (Set)this.path2Ids.get(path);
      return s != null ? Collections.unmodifiableSet(s) : null;
   }

   public Map toMap() {
      return deepCopy(this.path2Ids);
   }
}
