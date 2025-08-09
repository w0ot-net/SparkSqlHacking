package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.AbstractFunction1;
import scala.runtime.ModuleSerializationProxy;

public final class HDFSCacheTaskLocation$ extends AbstractFunction1 implements Serializable {
   public static final HDFSCacheTaskLocation$ MODULE$ = new HDFSCacheTaskLocation$();

   public final String toString() {
      return "HDFSCacheTaskLocation";
   }

   public HDFSCacheTaskLocation apply(final String host) {
      return new HDFSCacheTaskLocation(host);
   }

   public Option unapply(final HDFSCacheTaskLocation x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.host()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(HDFSCacheTaskLocation$.class);
   }

   private HDFSCacheTaskLocation$() {
   }
}
