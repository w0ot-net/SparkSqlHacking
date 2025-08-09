package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.CurrentOrigin$;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class SortOrder$ implements Serializable {
   public static final SortOrder$ MODULE$ = new SortOrder$();

   public Origin $lessinit$greater$default$4() {
      return CurrentOrigin$.MODULE$.get();
   }

   public SortOrder apply(final ColumnNode child, final SortOrder.SortDirection sortDirection, final SortOrder.NullOrdering nullOrdering, final Origin origin) {
      return new SortOrder(child, sortDirection, nullOrdering, origin);
   }

   public Origin apply$default$4() {
      return CurrentOrigin$.MODULE$.get();
   }

   public Option unapply(final SortOrder x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.child(), x$0.sortDirection(), x$0.nullOrdering(), x$0.origin())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(SortOrder$.class);
   }

   private SortOrder$() {
   }
}
