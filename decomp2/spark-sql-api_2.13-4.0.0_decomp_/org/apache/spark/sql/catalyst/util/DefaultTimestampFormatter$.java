package org.apache.spark.sql.catalyst.util;

import java.io.Serializable;
import scala.Enumeration;
import scala.runtime.ModuleSerializationProxy;

public final class DefaultTimestampFormatter$ implements Serializable {
   public static final DefaultTimestampFormatter$ MODULE$ = new DefaultTimestampFormatter$();

   public Enumeration.Value $lessinit$greater$default$3() {
      return LegacyDateFormats$.MODULE$.LENIENT_SIMPLE_DATE_FORMAT();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DefaultTimestampFormatter$.class);
   }

   private DefaultTimestampFormatter$() {
   }
}
