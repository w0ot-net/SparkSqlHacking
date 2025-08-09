package org.apache.spark.sql.streaming;

import java.io.Serializable;
import java.util.Map;
import scala.jdk.CollectionConverters.;
import scala.runtime.ModuleSerializationProxy;

public final class SourceProgress$ implements Serializable {
   public static final SourceProgress$ MODULE$ = new SourceProgress$();

   public Map $lessinit$greater$default$8() {
      return .MODULE$.MapHasAsJava((scala.collection.Map)scala.Predef..MODULE$.Map().apply(scala.collection.immutable.Nil..MODULE$)).asJava();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SourceProgress$.class);
   }

   private SourceProgress$() {
   }
}
