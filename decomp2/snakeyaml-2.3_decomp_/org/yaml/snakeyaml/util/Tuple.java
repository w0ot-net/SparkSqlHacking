package org.yaml.snakeyaml.util;

public final class Tuple {
   private final Object _1;
   private final Object _2;

   public Tuple(Object _1, Object _2) {
      this._1 = _1;
      this._2 = _2;
   }

   public Object _2() {
      return this._2;
   }

   public Object _1() {
      return this._1;
   }
}
