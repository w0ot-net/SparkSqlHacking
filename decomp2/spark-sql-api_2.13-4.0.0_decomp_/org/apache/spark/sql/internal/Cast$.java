package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.CurrentOrigin$;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.types.DataType;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class Cast$ implements Serializable {
   public static final Cast$ MODULE$ = new Cast$();

   public Option $lessinit$greater$default$3() {
      return .MODULE$;
   }

   public Origin $lessinit$greater$default$4() {
      return CurrentOrigin$.MODULE$.get();
   }

   public Cast apply(final ColumnNode child, final DataType dataType, final Option evalMode, final Origin origin) {
      return new Cast(child, dataType, evalMode, origin);
   }

   public Option apply$default$3() {
      return .MODULE$;
   }

   public Origin apply$default$4() {
      return CurrentOrigin$.MODULE$.get();
   }

   public Option unapply(final Cast x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.child(), x$0.dataType(), x$0.evalMode(), x$0.origin())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Cast$.class);
   }

   private Cast$() {
   }
}
