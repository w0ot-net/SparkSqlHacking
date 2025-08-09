package org.apache.spark.mllib.tree.configuration;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class Algo$ extends Enumeration {
   public static final Algo$ MODULE$ = new Algo$();
   private static final Enumeration.Value Classification;
   private static final Enumeration.Value Regression;

   static {
      Classification = MODULE$.Value();
      Regression = MODULE$.Value();
   }

   public Enumeration.Value Classification() {
      return Classification;
   }

   public Enumeration.Value Regression() {
      return Regression;
   }

   public Enumeration.Value fromString(final String name) {
      switch (name == null ? 0 : name.hashCode()) {
         case -880190367:
            if ("Regression".equals(name)) {
               return this.Regression();
            }
            break;
         case -619642874:
            if ("Classification".equals(name)) {
               return this.Classification();
            }
            break;
         case 382350310:
            if ("classification".equals(name)) {
               return this.Classification();
            }
            break;
         case 1421312065:
            if ("regression".equals(name)) {
               return this.Regression();
            }
      }

      throw new IllegalArgumentException("Did not recognize Algo name: " + name);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Algo$.class);
   }

   private Algo$() {
   }
}
