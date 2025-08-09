package org.sparkproject.jetty.util;

import java.net.InetAddress;
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class InetAddressSet extends AbstractSet implements Set, Predicate {
   private Map _patterns = new HashMap();

   public boolean add(String pattern) {
      return this._patterns.put(pattern, InetAddressPattern.from(pattern)) == null;
   }

   public boolean remove(Object pattern) {
      return this._patterns.remove(pattern) != null;
   }

   public Iterator iterator() {
      return this._patterns.keySet().iterator();
   }

   public int size() {
      return this._patterns.size();
   }

   public boolean test(InetAddress address) {
      if (address == null) {
         return false;
      } else {
         for(InetAddressPattern pattern : this._patterns.values()) {
            if (pattern.test(address)) {
               return true;
            }
         }

         return false;
      }
   }
}
