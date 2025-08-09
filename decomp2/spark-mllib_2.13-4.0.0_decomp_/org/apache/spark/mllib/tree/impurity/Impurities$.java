package org.apache.spark.mllib.tree.impurity;

public final class Impurities$ {
   public static final Impurities$ MODULE$ = new Impurities$();

   public Impurity fromString(final String name) {
      switch (name == null ? 0 : name.hashCode()) {
         case -1591567247:
            if ("entropy".equals(name)) {
               return Entropy$.MODULE$;
            }
            break;
         case -1249575311:
            if ("variance".equals(name)) {
               return Variance$.MODULE$;
            }
            break;
         case 3172893:
            if ("gini".equals(name)) {
               return Gini$.MODULE$;
            }
      }

      throw new IllegalArgumentException("Did not recognize Impurity name: " + name);
   }

   private Impurities$() {
   }
}
