package org.apache.ws.commons.schema.utils;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class CollectionFactory {
   private static final String PROTECT_READ_ONLY_COLLECTIONS_PROP = "org.apache.ws.commons.schema.protectReadOnlyCollections";
   private static final boolean DEFAULT_PROTECT_READ_ONLY_COLLECTIONS = (Boolean)AccessController.doPrivileged(new PrivilegedAction() {
      public Boolean run() {
         return Boolean.parseBoolean(System.getProperty("org.apache.ws.commons.schema.protectReadOnlyCollections"));
      }
   });
   private static final ThreadLocal PROTECT_READ_ONLY_COLLECTIONS = new ThreadLocal();

   private CollectionFactory() {
   }

   public static List getList(Class type) {
      return Collections.synchronizedList(new ArrayList());
   }

   public static Set getSet(Class type) {
      return Collections.synchronizedSet(new HashSet());
   }

   private static boolean isProtected() {
      Boolean b = (Boolean)PROTECT_READ_ONLY_COLLECTIONS.get();
      return b == null ? DEFAULT_PROTECT_READ_ONLY_COLLECTIONS : b;
   }

   public static void setProtected(boolean b) {
      PROTECT_READ_ONLY_COLLECTIONS.set(b);
   }

   public static void clearProtection() {
      PROTECT_READ_ONLY_COLLECTIONS.remove();
   }

   public static List getProtectedList(List list) {
      return isProtected() ? Collections.unmodifiableList(list) : list;
   }

   public static Map getProtectedMap(Map map) {
      return isProtected() ? Collections.unmodifiableMap(map) : map;
   }

   public static void withSchemaModifiable(Runnable action) {
      Boolean saved = (Boolean)PROTECT_READ_ONLY_COLLECTIONS.get();

      try {
         PROTECT_READ_ONLY_COLLECTIONS.set(Boolean.FALSE);
         action.run();
      } finally {
         if (saved == null) {
            PROTECT_READ_ONLY_COLLECTIONS.remove();
         } else {
            PROTECT_READ_ONLY_COLLECTIONS.set(saved);
         }

      }

   }
}
