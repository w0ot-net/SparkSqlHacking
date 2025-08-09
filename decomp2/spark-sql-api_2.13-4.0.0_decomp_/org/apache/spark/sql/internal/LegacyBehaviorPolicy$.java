package org.apache.spark.sql.internal;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class LegacyBehaviorPolicy$ extends Enumeration {
   public static final LegacyBehaviorPolicy$ MODULE$ = new LegacyBehaviorPolicy$();
   private static final Enumeration.Value EXCEPTION;
   private static final Enumeration.Value LEGACY;
   private static final Enumeration.Value CORRECTED;

   static {
      EXCEPTION = MODULE$.Value();
      LEGACY = MODULE$.Value();
      CORRECTED = MODULE$.Value();
   }

   public Enumeration.Value EXCEPTION() {
      return EXCEPTION;
   }

   public Enumeration.Value LEGACY() {
      return LEGACY;
   }

   public Enumeration.Value CORRECTED() {
      return CORRECTED;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LegacyBehaviorPolicy$.class);
   }

   private LegacyBehaviorPolicy$() {
   }
}
