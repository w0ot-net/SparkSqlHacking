package org.sparkproject.jetty.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class HostMap extends HashMap {
   public HostMap() {
      super(11);
   }

   public HostMap(int capacity) {
      super(capacity);
   }

   public Object put(String host, Object object) throws IllegalArgumentException {
      return super.put(host, object);
   }

   public Object get(Object key) {
      return super.get(key);
   }

   public Object getLazyMatches(String host) {
      if (host == null) {
         return LazyList.getList(super.entrySet());
      } else {
         int idx = 0;
         String domain = host.trim();
         HashSet<String> domains = new HashSet();

         do {
            domains.add(domain);
            if ((idx = domain.indexOf(46)) > 0) {
               domain = domain.substring(idx + 1);
            }
         } while(idx > 0);

         Object entries = null;

         for(Map.Entry entry : super.entrySet()) {
            if (domains.contains(entry.getKey())) {
               entries = LazyList.add(entries, entry);
            }
         }

         return entries;
      }
   }
}
