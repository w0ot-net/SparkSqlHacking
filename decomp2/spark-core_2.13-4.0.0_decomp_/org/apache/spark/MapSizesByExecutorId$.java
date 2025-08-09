package org.apache.spark;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.collection.Iterator;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class MapSizesByExecutorId$ extends AbstractFunction2 implements Serializable {
   public static final MapSizesByExecutorId$ MODULE$ = new MapSizesByExecutorId$();

   public final String toString() {
      return "MapSizesByExecutorId";
   }

   public MapSizesByExecutorId apply(final Iterator iter, final boolean enableBatchFetch) {
      return new MapSizesByExecutorId(iter, enableBatchFetch);
   }

   public Option unapply(final MapSizesByExecutorId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.iter(), BoxesRunTime.boxToBoolean(x$0.enableBatchFetch()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(MapSizesByExecutorId$.class);
   }

   private MapSizesByExecutorId$() {
   }
}
