package org.apache.spark.mllib.tree.loss;

public final class Losses$ {
   public static final Losses$ MODULE$ = new Losses$();

   public Loss fromString(final String name) {
      switch (name == null ? 0 : name.hashCode()) {
         case -1866543685:
            if ("leastSquaresError".equals(name)) {
               return SquaredError$.MODULE$;
            }
            break;
         case -298452248:
            if ("leastAbsoluteError".equals(name)) {
               return AbsoluteError$.MODULE$;
            }
            break;
         case 341482631:
            if ("logLoss".equals(name)) {
               return LogLoss$.MODULE$;
            }
      }

      throw new IllegalArgumentException("Did not recognize Loss name: " + name);
   }

   private Losses$() {
   }
}
