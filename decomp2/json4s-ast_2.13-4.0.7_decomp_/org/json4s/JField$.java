package org.json4s;

import scala.Tuple2;

public final class JField$ {
   public static final JField$ MODULE$ = new JField$();

   public Tuple2 apply(final String name, final JValue value) {
      return new Tuple2(name, value);
   }

   public Tuple2 unapply(final Tuple2 f) {
      return f;
   }

   private JField$() {
   }
}
