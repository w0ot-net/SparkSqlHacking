package org.apache.spark.mllib.tree.configuration;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class EnsembleCombiningStrategy$ extends Enumeration {
   public static final EnsembleCombiningStrategy$ MODULE$ = new EnsembleCombiningStrategy$();
   private static final Enumeration.Value Average;
   private static final Enumeration.Value Sum;
   private static final Enumeration.Value Vote;

   static {
      Average = MODULE$.Value();
      Sum = MODULE$.Value();
      Vote = MODULE$.Value();
   }

   public Enumeration.Value Average() {
      return Average;
   }

   public Enumeration.Value Sum() {
      return Sum;
   }

   public Enumeration.Value Vote() {
      return Vote;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(EnsembleCombiningStrategy$.class);
   }

   private EnsembleCombiningStrategy$() {
   }
}
