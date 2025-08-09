package org.sparkproject.jetty.server.handler;

import java.net.InetAddress;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;
import org.sparkproject.jetty.http.pathmap.PathSpec;
import org.sparkproject.jetty.http.pathmap.ServletPathSpec;
import org.sparkproject.jetty.util.InetAddressPattern;
import org.sparkproject.jetty.util.StringUtil;

public class InetAccessSet extends AbstractSet implements Set, Predicate {
   private final ArrayList tuples = new ArrayList();

   public boolean add(PatternTuple storageTuple) {
      return this.tuples.add(storageTuple);
   }

   public boolean remove(Object o) {
      return this.tuples.remove(o);
   }

   public Iterator iterator() {
      return this.tuples.iterator();
   }

   public int size() {
      return this.tuples.size();
   }

   public boolean test(AccessTuple entry) {
      if (entry == null) {
         return false;
      } else {
         for(PatternTuple tuple : this.tuples) {
            if (tuple.test(entry)) {
               return true;
            }
         }

         return false;
      }
   }

   public static class PatternTuple implements Predicate {
      private final String connector;
      private final InetAddressPattern address;
      private final PathSpec pathSpec;

      public static PatternTuple from(String pattern) {
         String path = null;
         int pathIndex = pattern.indexOf(124);
         if (pathIndex >= 0) {
            path = pattern.substring(pathIndex + 1);
         }

         String connector = null;
         int connectorIndex = pattern.indexOf(64);
         if (connectorIndex >= 0) {
            connector = pattern.substring(0, connectorIndex);
         }

         String addr = null;
         int addrStart = connectorIndex < 0 ? 0 : connectorIndex + 1;
         int addrEnd = pathIndex < 0 ? pattern.length() : pathIndex;
         if (addrStart != addrEnd) {
            addr = pattern.substring(addrStart, addrEnd);
         }

         return new PatternTuple(connector, InetAddressPattern.from(addr), StringUtil.isEmpty(path) ? null : new ServletPathSpec(path));
      }

      public PatternTuple(String connector, InetAddressPattern address, PathSpec pathSpec) {
         this.connector = connector;
         this.address = address;
         this.pathSpec = pathSpec;
      }

      public boolean test(AccessTuple entry) {
         if (this.connector != null && !this.connector.equals(entry.getConnector())) {
            return false;
         } else if (this.pathSpec != null && !this.pathSpec.matches(entry.getPath())) {
            return false;
         } else {
            return this.address == null || this.address.test(entry.getAddress());
         }
      }

      public String toString() {
         return String.format("%s@%x{connector=%s, addressPattern=%s, pathSpec=%s}", this.getClass().getSimpleName(), this.hashCode(), this.connector, this.address, this.pathSpec);
      }
   }

   public static class AccessTuple {
      private final String connector;
      private final InetAddress address;
      private final String path;

      public AccessTuple(String connector, InetAddress address, String path) {
         this.connector = connector;
         this.address = address;
         this.path = path;
      }

      public String getConnector() {
         return this.connector;
      }

      public InetAddress getAddress() {
         return this.address;
      }

      public String getPath() {
         return this.path;
      }
   }
}
