package com.ibm.icu.impl;

import java.util.Map;

public class ImmutableEntry implements Map.Entry {
   final Object k;
   final Object v;

   ImmutableEntry(Object key, Object value) {
      this.k = key;
      this.v = value;
   }

   public Object getKey() {
      return this.k;
   }

   public Object getValue() {
      return this.v;
   }

   public Object setValue(Object value) {
      throw new UnsupportedOperationException();
   }

   public boolean equals(Object o) {
      try {
         Map.Entry e = (Map.Entry)o;
         return UnicodeMap.areEqual(e.getKey(), this.k) && UnicodeMap.areEqual(e.getValue(), this.v);
      } catch (ClassCastException var3) {
         return false;
      }
   }

   public int hashCode() {
      return (this.k == null ? 0 : this.k.hashCode()) ^ (this.v == null ? 0 : this.v.hashCode());
   }

   public String toString() {
      return this.k + "=" + this.v;
   }
}
