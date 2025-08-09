package org.apache.spark.sql;

public final class Observation$ {
   public static final Observation$ MODULE$ = new Observation$();

   public Observation apply() {
      return new Observation();
   }

   public Observation apply(final String name) {
      return new Observation(name);
   }

   private Observation$() {
   }
}
