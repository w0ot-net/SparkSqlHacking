package org.glassfish.jersey.internal.util.collection;

import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;
import java.lang.reflect.Constructor;
import java.util.List;

public class MultivaluedStringMap extends MultivaluedHashMap {
   static final long serialVersionUID = -6052320403766368902L;

   public MultivaluedStringMap(MultivaluedMap map) {
      super(map);
   }

   public MultivaluedStringMap(int initialCapacity, float loadFactor) {
      super(initialCapacity, loadFactor);
   }

   public MultivaluedStringMap(int initialCapacity) {
      super(initialCapacity);
   }

   public MultivaluedStringMap() {
   }

   protected void addFirstNull(List values) {
      values.add("");
   }

   protected void addNull(List values) {
      values.add(0, "");
   }

   public final Object getFirst(String key, Class type) {
      String value = (String)this.getFirst(key);
      if (value == null) {
         return null;
      } else {
         Constructor<A> c = null;

         try {
            c = type.getConstructor(String.class);
         } catch (Exception ex) {
            throw new IllegalArgumentException(type.getName() + " has no String constructor", ex);
         }

         A retVal = (A)null;

         try {
            retVal = (A)c.newInstance(value);
         } catch (Exception var7) {
         }

         return retVal;
      }
   }

   public final Object getFirst(String key, Object defaultValue) {
      String value = (String)this.getFirst(key);
      if (value == null) {
         return defaultValue;
      } else {
         Class<A> type = defaultValue.getClass();
         Constructor<A> c = null;

         try {
            c = type.getConstructor(String.class);
         } catch (Exception ex) {
            throw new IllegalArgumentException(type.getName() + " has no String constructor", ex);
         }

         A retVal = defaultValue;

         try {
            retVal = (A)c.newInstance(value);
         } catch (Exception var8) {
         }

         return retVal;
      }
   }
}
