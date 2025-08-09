package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.CurrentOrigin$;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.runtime.AbstractFunction4;
import scala.runtime.ModuleSerializationProxy;

public final class UpdateFields$ extends AbstractFunction4 implements Serializable {
   public static final UpdateFields$ MODULE$ = new UpdateFields$();

   public Option $lessinit$greater$default$3() {
      return .MODULE$;
   }

   public Origin $lessinit$greater$default$4() {
      return CurrentOrigin$.MODULE$.get();
   }

   public final String toString() {
      return "UpdateFields";
   }

   public UpdateFields apply(final ColumnNode structExpression, final String fieldName, final Option valueExpression, final Origin origin) {
      return new UpdateFields(structExpression, fieldName, valueExpression, origin);
   }

   public Option apply$default$3() {
      return .MODULE$;
   }

   public Origin apply$default$4() {
      return CurrentOrigin$.MODULE$.get();
   }

   public Option unapply(final UpdateFields x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.structExpression(), x$0.fieldName(), x$0.valueExpression(), x$0.origin())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UpdateFields$.class);
   }

   private UpdateFields$() {
   }
}
