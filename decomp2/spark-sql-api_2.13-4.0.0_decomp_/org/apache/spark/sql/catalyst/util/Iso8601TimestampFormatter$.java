package org.apache.spark.sql.catalyst.util;

import java.io.Serializable;
import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class Iso8601TimestampFormatter$ implements Serializable {
   public static final Iso8601TimestampFormatter$ MODULE$ = new Iso8601TimestampFormatter$();

   public Enumeration.Value $lessinit$greater$default$4() {
      return LegacyDateFormats$.MODULE$.LENIENT_SIMPLE_DATE_FORMAT();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Iso8601TimestampFormatter$.class);
   }

   private Iso8601TimestampFormatter$() {
   }
}
