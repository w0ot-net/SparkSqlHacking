package org.apache.spark.mllib.stat.test;

import java.io.Serializable;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.Iterable;
import scala.collection.immutable.Map;
import scala.runtime.ModuleSerializationProxy;

public final class StreamingTestMethod$ implements Serializable {
   public static final StreamingTestMethod$ MODULE$ = new StreamingTestMethod$();
   private static final Map TEST_NAME_TO_OBJECT;

   static {
      TEST_NAME_TO_OBJECT = (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("welch"), WelchTTest$.MODULE$), scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("student"), StudentTTest$.MODULE$)})));
   }

   private final Map TEST_NAME_TO_OBJECT() {
      return TEST_NAME_TO_OBJECT;
   }

   public StreamingTestMethod getTestMethodFromName(final String method) {
      Option var3 = this.TEST_NAME_TO_OBJECT().get(method);
      if (var3 instanceof Some var4) {
         StreamingTestMethod test = (StreamingTestMethod)var4.value();
         return test;
      } else if (scala.None..MODULE$.equals(var3)) {
         Iterable var10002 = this.TEST_NAME_TO_OBJECT().keys();
         throw new IllegalArgumentException("Unrecognized method name. Supported streaming test methods: " + var10002.mkString(", "));
      } else {
         throw new MatchError(var3);
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(StreamingTestMethod$.class);
   }

   private StreamingTestMethod$() {
   }
}
