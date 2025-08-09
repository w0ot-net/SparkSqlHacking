package org.apache.spark.sql.catalyst.util;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class LegacySimpleTimestampFormatter$ implements Serializable {
   public static final LegacySimpleTimestampFormatter$ MODULE$ = new LegacySimpleTimestampFormatter$();

   public boolean $lessinit$greater$default$4() {
      return true;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LegacySimpleTimestampFormatter$.class);
   }

   private LegacySimpleTimestampFormatter$() {
   }
}
