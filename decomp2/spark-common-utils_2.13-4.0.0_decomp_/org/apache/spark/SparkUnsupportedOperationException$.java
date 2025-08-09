package org.apache.spark;

import java.io.Serializable;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.runtime.ModuleSerializationProxy;

public final class SparkUnsupportedOperationException$ implements Serializable {
   public static final SparkUnsupportedOperationException$ MODULE$ = new SparkUnsupportedOperationException$();

   public SparkUnsupportedOperationException apply() {
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      Map var10000;
      if (stackTrace.length >= 4) {
         StackTraceElement element = stackTrace[3];
         var10000 = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("className"), element.getClassName()), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("methodName"), element.getMethodName())})));
      } else {
         var10000 = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("className"), "?"), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("methodName"), "?")})));
      }

      Map messageParameters = var10000;
      return new SparkUnsupportedOperationException("UNSUPPORTED_CALL.WITHOUT_SUGGESTION", messageParameters);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkUnsupportedOperationException$.class);
   }

   private SparkUnsupportedOperationException$() {
   }
}
