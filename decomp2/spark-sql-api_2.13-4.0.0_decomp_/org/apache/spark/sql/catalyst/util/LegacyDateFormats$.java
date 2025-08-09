package org.apache.spark.sql.catalyst.util;

import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class LegacyDateFormats$ extends Enumeration {
   public static final LegacyDateFormats$ MODULE$ = new LegacyDateFormats$();
   private static final Enumeration.Value FAST_DATE_FORMAT;
   private static final Enumeration.Value SIMPLE_DATE_FORMAT;
   private static final Enumeration.Value LENIENT_SIMPLE_DATE_FORMAT;

   static {
      FAST_DATE_FORMAT = MODULE$.Value();
      SIMPLE_DATE_FORMAT = MODULE$.Value();
      LENIENT_SIMPLE_DATE_FORMAT = MODULE$.Value();
   }

   public Enumeration.Value FAST_DATE_FORMAT() {
      return FAST_DATE_FORMAT;
   }

   public Enumeration.Value SIMPLE_DATE_FORMAT() {
      return SIMPLE_DATE_FORMAT;
   }

   public Enumeration.Value LENIENT_SIMPLE_DATE_FORMAT() {
      return LENIENT_SIMPLE_DATE_FORMAT;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LegacyDateFormats$.class);
   }

   private LegacyDateFormats$() {
   }
}
