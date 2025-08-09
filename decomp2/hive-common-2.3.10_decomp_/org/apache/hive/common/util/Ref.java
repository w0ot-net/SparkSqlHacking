package org.apache.hive.common.util;

public final class Ref {
   public Object value;

   public Ref(Object value) {
      this.value = value;
   }

   public static Ref from(Object t) {
      return new Ref(t);
   }
}
