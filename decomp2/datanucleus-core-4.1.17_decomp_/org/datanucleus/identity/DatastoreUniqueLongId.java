package org.datanucleus.identity;

import java.io.Serializable;

public class DatastoreUniqueLongId implements Serializable, DatastoreId, Comparable {
   private static final long serialVersionUID = -8633190725867210874L;
   public final long key;

   public DatastoreUniqueLongId() {
      this.key = -1L;
   }

   public DatastoreUniqueLongId(long key) {
      this.key = key;
   }

   public DatastoreUniqueLongId(String str) throws IllegalArgumentException {
      this.key = Long.parseLong(str);
   }

   public Object getKeyAsObject() {
      return this.key;
   }

   public long getKey() {
      return this.key;
   }

   public String getTargetClassName() {
      throw new UnsupportedOperationException();
   }

   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      } else if (obj == null) {
         return false;
      } else if (!obj.getClass().equals(this.getClass())) {
         return false;
      } else {
         return this.key == ((DatastoreUniqueLongId)obj).key;
      }
   }

   public int compareTo(Object o) {
      if (o instanceof DatastoreUniqueLongId) {
         DatastoreUniqueLongId c = (DatastoreUniqueLongId)o;
         return (int)(this.key - c.key);
      } else if (o == null) {
         throw new ClassCastException("object is null");
      } else {
         throw new ClassCastException(this.getClass().getName() + " != " + o.getClass().getName());
      }
   }

   public int hashCode() {
      return (int)this.key;
   }

   public String toString() {
      return Long.toString(this.key);
   }
}
