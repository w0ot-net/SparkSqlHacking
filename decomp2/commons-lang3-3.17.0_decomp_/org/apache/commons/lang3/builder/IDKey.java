package org.apache.commons.lang3.builder;

final class IDKey {
   private final Object value;
   private final int id;

   IDKey(Object value) {
      this.id = System.identityHashCode(value);
      this.value = value;
   }

   public boolean equals(Object other) {
      if (!(other instanceof IDKey)) {
         return false;
      } else {
         IDKey idKey = (IDKey)other;
         if (this.id != idKey.id) {
            return false;
         } else {
            return this.value == idKey.value;
         }
      }
   }

   public int hashCode() {
      return this.id;
   }
}
