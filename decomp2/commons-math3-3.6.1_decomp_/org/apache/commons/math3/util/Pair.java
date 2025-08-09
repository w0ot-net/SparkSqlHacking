package org.apache.commons.math3.util;

public class Pair {
   private final Object key;
   private final Object value;

   public Pair(Object k, Object v) {
      this.key = k;
      this.value = v;
   }

   public Pair(Pair entry) {
      this(entry.getKey(), entry.getValue());
   }

   public Object getKey() {
      return this.key;
   }

   public Object getValue() {
      return this.value;
   }

   public Object getFirst() {
      return this.key;
   }

   public Object getSecond() {
      return this.value;
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (!(o instanceof Pair)) {
         return false;
      } else {
         boolean var10000;
         label43: {
            label29: {
               Pair<?, ?> oP = (Pair)o;
               if (this.key == null) {
                  if (oP.key != null) {
                     break label29;
                  }
               } else if (!this.key.equals(oP.key)) {
                  break label29;
               }

               if (this.value == null) {
                  if (oP.value == null) {
                     break label43;
                  }
               } else if (this.value.equals(oP.value)) {
                  break label43;
               }
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }
   }

   public int hashCode() {
      int result = this.key == null ? 0 : this.key.hashCode();
      int h = this.value == null ? 0 : this.value.hashCode();
      result = 37 * result + h ^ h >>> 16;
      return result;
   }

   public String toString() {
      return "[" + this.getKey() + ", " + this.getValue() + "]";
   }

   public static Pair create(Object k, Object v) {
      return new Pair(k, v);
   }
}
