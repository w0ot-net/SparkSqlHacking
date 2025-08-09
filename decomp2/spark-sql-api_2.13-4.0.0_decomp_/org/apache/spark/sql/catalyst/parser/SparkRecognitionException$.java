package org.apache.spark.sql.catalyst.parser;

import java.io.Serializable;
import scala.Option;
import scala.None.;
import scala.collection.immutable.Map;
import scala.runtime.ModuleSerializationProxy;

public final class SparkRecognitionException$ implements Serializable {
   public static final SparkRecognitionException$ MODULE$ = new SparkRecognitionException$();

   public Option $lessinit$greater$default$5() {
      return .MODULE$;
   }

   public Map $lessinit$greater$default$6() {
      return scala.Predef..MODULE$.Map().empty();
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkRecognitionException$.class);
   }

   private SparkRecognitionException$() {
   }
}
