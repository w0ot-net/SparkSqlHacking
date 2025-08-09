package org.json4s.prefs;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class ExtractionNullStrategy$ implements Serializable {
   public static final ExtractionNullStrategy$ MODULE$ = new ExtractionNullStrategy$();

   private Object writeReplace() {
      return new ModuleSerializationProxy(ExtractionNullStrategy$.class);
   }

   private ExtractionNullStrategy$() {
   }
}
