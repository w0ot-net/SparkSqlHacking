package org.apache.spark;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Array.;
import scala.collection.immutable.Map;
import scala.runtime.ModuleSerializationProxy;

public final class SparkException$ implements Serializable {
   public static final SparkException$ MODULE$ = new SparkException$();

   public QueryContext[] $lessinit$greater$default$5() {
      return (QueryContext[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(QueryContext.class));
   }

   public SparkException internalError(final String msg, final QueryContext[] context, final String summary) {
      return this.internalError(msg, context, summary, scala.None..MODULE$);
   }

   public SparkException internalError(final String msg, final QueryContext[] context, final String summary, final Option category) {
      return new SparkException("INTERNAL_ERROR" + category.map((x$1) -> "_" + x$1).getOrElse(() -> ""), (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("message"), msg)}))), (Throwable)null, context, summary);
   }

   public SparkException internalError(final String msg) {
      return this.internalError(msg, (QueryContext[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(QueryContext.class)), "", scala.None..MODULE$);
   }

   public SparkException internalError(final String msg, final String category) {
      return this.internalError(msg, (QueryContext[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(QueryContext.class)), "", new Some(category));
   }

   public SparkException internalError(final String msg, final Throwable cause) {
      return new SparkException("INTERNAL_ERROR", (Map)scala.Predef..MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc("message"), msg)}))), cause);
   }

   public void require(final boolean requirement, final String errorClass, final Function0 messageParameters) {
      if (!requirement) {
         throw new SparkIllegalArgumentException(errorClass, (Map)messageParameters.apply());
      }
   }

   public Map constructMessageParams(final java.util.Map messageParameters) {
      return scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(messageParameters).asScala().toMap(scala..less.colon.less..MODULE$.refl());
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SparkException$.class);
   }

   private SparkException$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
