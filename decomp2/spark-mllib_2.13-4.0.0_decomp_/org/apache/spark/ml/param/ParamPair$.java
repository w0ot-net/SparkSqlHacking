package org.apache.spark.ml.param;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class ParamPair$ implements Serializable {
   public static final ParamPair$ MODULE$ = new ParamPair$();

   public final String toString() {
      return "ParamPair";
   }

   public ParamPair apply(final Param param, final Object value) {
      return new ParamPair(param, value);
   }

   public Option unapply(final ParamPair x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.param(), x$0.value())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ParamPair$.class);
   }

   private ParamPair$() {
   }
}
