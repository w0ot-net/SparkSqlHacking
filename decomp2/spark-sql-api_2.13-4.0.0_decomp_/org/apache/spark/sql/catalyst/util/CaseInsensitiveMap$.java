package org.apache.spark.sql.catalyst.util;

import java.io.Serializable;
import scala.collection.immutable.Map;
import scala.runtime.ModuleSerializationProxy;

public final class CaseInsensitiveMap$ implements Serializable {
   public static final CaseInsensitiveMap$ MODULE$ = new CaseInsensitiveMap$();

   public CaseInsensitiveMap apply(final Map params) {
      if (params instanceof CaseInsensitiveMap var4) {
         return var4;
      } else {
         return new CaseInsensitiveMap(params);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CaseInsensitiveMap$.class);
   }

   private CaseInsensitiveMap$() {
   }
}
