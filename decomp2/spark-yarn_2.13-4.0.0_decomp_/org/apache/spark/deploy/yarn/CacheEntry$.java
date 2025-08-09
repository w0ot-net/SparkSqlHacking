package org.apache.spark.deploy.yarn;

import java.io.Serializable;
import java.net.URI;
import org.apache.hadoop.yarn.api.records.LocalResourceType;
import org.apache.hadoop.yarn.api.records.LocalResourceVisibility;
import scala.Option;
import scala.Some;
import scala.Tuple5;
import scala.None.;
import scala.runtime.AbstractFunction5;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class CacheEntry$ extends AbstractFunction5 implements Serializable {
   public static final CacheEntry$ MODULE$ = new CacheEntry$();

   public final String toString() {
      return "CacheEntry";
   }

   public CacheEntry apply(final URI uri, final long size, final long modTime, final LocalResourceVisibility visibility, final LocalResourceType resType) {
      return new CacheEntry(uri, size, modTime, visibility, resType);
   }

   public Option unapply(final CacheEntry x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple5(x$0.uri(), BoxesRunTime.boxToLong(x$0.size()), BoxesRunTime.boxToLong(x$0.modTime()), x$0.visibility(), x$0.resType())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CacheEntry$.class);
   }

   private CacheEntry$() {
   }
}
