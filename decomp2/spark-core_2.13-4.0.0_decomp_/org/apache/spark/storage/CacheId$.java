package org.apache.spark.storage;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class CacheId$ extends AbstractFunction2 implements Serializable {
   public static final CacheId$ MODULE$ = new CacheId$();

   public final String toString() {
      return "CacheId";
   }

   public CacheId apply(final String sessionUUID, final String hash) {
      return new CacheId(sessionUUID, hash);
   }

   public Option unapply(final CacheId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.sessionUUID(), x$0.hash())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CacheId$.class);
   }

   private CacheId$() {
   }
}
