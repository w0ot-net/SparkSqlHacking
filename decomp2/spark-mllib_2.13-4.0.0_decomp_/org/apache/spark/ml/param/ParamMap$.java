package org.apache.spark.ml.param;

import java.io.Serializable;
import scala.collection.immutable.Seq;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.ScalaRunTime.;

public final class ParamMap$ implements Serializable {
   public static final ParamMap$ MODULE$ = new ParamMap$();

   public ParamMap apply(final ParamPair... paramPairs) {
      return this.apply((Seq).MODULE$.wrapRefArray(paramPairs));
   }

   public ParamMap empty() {
      return new ParamMap();
   }

   public ParamMap apply(final Seq paramPairs) {
      return (new ParamMap()).put(paramPairs);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ParamMap$.class);
   }

   private ParamMap$() {
   }
}
