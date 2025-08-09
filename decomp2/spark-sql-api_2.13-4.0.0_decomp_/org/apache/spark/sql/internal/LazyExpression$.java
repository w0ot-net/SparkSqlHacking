package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.CurrentOrigin$;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class LazyExpression$ extends AbstractFunction2 implements Serializable {
   public static final LazyExpression$ MODULE$ = new LazyExpression$();

   public Origin $lessinit$greater$default$2() {
      return CurrentOrigin$.MODULE$.get();
   }

   public final String toString() {
      return "LazyExpression";
   }

   public LazyExpression apply(final ColumnNode child, final Origin origin) {
      return new LazyExpression(child, origin);
   }

   public Origin apply$default$2() {
      return CurrentOrigin$.MODULE$.get();
   }

   public Option unapply(final LazyExpression x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.child(), x$0.origin())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LazyExpression$.class);
   }

   private LazyExpression$() {
   }
}
