package org.sparkproject.jetty.io;

import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;
import org.sparkproject.jetty.util.IncludeExcludeSet;

public class IncludeExcludeConnectionStatistics extends ConnectionStatistics {
   private final IncludeExcludeSet _set = new IncludeExcludeSet(ConnectionSet.class);

   public void include(String className) throws ClassNotFoundException {
      this._set.include((Object)this.connectionForName(className));
   }

   public void include(Class clazz) {
      this._set.include((Object)clazz);
   }

   public void exclude(String className) throws ClassNotFoundException {
      this._set.exclude((Object)this.connectionForName(className));
   }

   public void exclude(Class clazz) {
      this._set.exclude((Object)clazz);
   }

   private Class connectionForName(String className) throws ClassNotFoundException {
      Class<?> aClass = Class.forName(className);
      if (!Connection.class.isAssignableFrom(aClass)) {
         throw new IllegalArgumentException("Class is not a Connection");
      } else {
         return aClass;
      }
   }

   public void onOpened(Connection connection) {
      if (this._set.test(connection)) {
         super.onOpened(connection);
      }

   }

   public void onClosed(Connection connection) {
      if (this._set.test(connection)) {
         super.onClosed(connection);
      }

   }

   public static class ConnectionSet extends AbstractSet implements Predicate {
      private final Set set = new HashSet();

      public boolean add(Class aClass) {
         return this.set.add(aClass);
      }

      public boolean remove(Object o) {
         return this.set.remove(o);
      }

      public Iterator iterator() {
         return this.set.iterator();
      }

      public int size() {
         return this.set.size();
      }

      public boolean test(Connection connection) {
         return connection == null ? false : this.set.stream().anyMatch((c) -> c.isAssignableFrom(connection.getClass()));
      }
   }
}
