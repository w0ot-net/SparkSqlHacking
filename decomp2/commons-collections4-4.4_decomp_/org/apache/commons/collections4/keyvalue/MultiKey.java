package org.apache.commons.collections4.keyvalue;

import [Ljava.lang.Object;;
import java.io.Serializable;
import java.util.Arrays;

public class MultiKey implements Serializable {
   private static final long serialVersionUID = 4465448607415788805L;
   private final Object[] keys;
   private transient int hashCode;

   public MultiKey(Object key1, Object key2) {
      this(new Object[]{key1, key2}, false);
   }

   public MultiKey(Object key1, Object key2, Object key3) {
      this(new Object[]{key1, key2, key3}, false);
   }

   public MultiKey(Object key1, Object key2, Object key3, Object key4) {
      this(new Object[]{key1, key2, key3, key4}, false);
   }

   public MultiKey(Object key1, Object key2, Object key3, Object key4, Object key5) {
      this(new Object[]{key1, key2, key3, key4, key5}, false);
   }

   public MultiKey(Object[] keys) {
      this(keys, true);
   }

   public MultiKey(Object[] keys, boolean makeClone) {
      if (keys == null) {
         throw new IllegalArgumentException("The array of keys must not be null");
      } else {
         if (makeClone) {
            this.keys = ((Object;)keys).clone();
         } else {
            this.keys = keys;
         }

         this.calculateHashCode(keys);
      }
   }

   public Object[] getKeys() {
      return this.keys.clone();
   }

   public Object getKey(int index) {
      return this.keys[index];
   }

   public int size() {
      return this.keys.length;
   }

   public boolean equals(Object other) {
      if (other == this) {
         return true;
      } else if (other instanceof MultiKey) {
         MultiKey<?> otherMulti = (MultiKey)other;
         return Arrays.equals(this.keys, otherMulti.keys);
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.hashCode;
   }

   public String toString() {
      return "MultiKey" + Arrays.toString(this.keys);
   }

   private void calculateHashCode(Object[] keys) {
      int total = 0;

      for(Object key : keys) {
         if (key != null) {
            total ^= key.hashCode();
         }
      }

      this.hashCode = total;
   }

   protected Object readResolve() {
      this.calculateHashCode(this.keys);
      return this;
   }
}
