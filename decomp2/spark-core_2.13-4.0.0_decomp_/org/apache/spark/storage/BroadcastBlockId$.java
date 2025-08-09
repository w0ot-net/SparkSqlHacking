package org.apache.spark.storage;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class BroadcastBlockId$ extends AbstractFunction2 implements Serializable {
   public static final BroadcastBlockId$ MODULE$ = new BroadcastBlockId$();

   public String $lessinit$greater$default$2() {
      return "";
   }

   public final String toString() {
      return "BroadcastBlockId";
   }

   public BroadcastBlockId apply(final long broadcastId, final String field) {
      return new BroadcastBlockId(broadcastId, field);
   }

   public String apply$default$2() {
      return "";
   }

   public Option unapply(final BroadcastBlockId x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(BoxesRunTime.boxToLong(x$0.broadcastId()), x$0.field())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BroadcastBlockId$.class);
   }

   private BroadcastBlockId$() {
   }
}
