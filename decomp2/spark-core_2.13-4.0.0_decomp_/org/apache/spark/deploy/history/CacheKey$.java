package org.apache.spark.deploy.history;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class CacheKey$ extends AbstractFunction2 implements Serializable {
   public static final CacheKey$ MODULE$ = new CacheKey$();

   public final String toString() {
      return "CacheKey";
   }

   public CacheKey apply(final String appId, final Option attemptId) {
      return new CacheKey(appId, attemptId);
   }

   public Option unapply(final CacheKey x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.appId(), x$0.attemptId())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CacheKey$.class);
   }

   private CacheKey$() {
   }
}
