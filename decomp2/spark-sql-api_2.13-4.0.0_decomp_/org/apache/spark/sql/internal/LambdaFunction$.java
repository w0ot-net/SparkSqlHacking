package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.CurrentOrigin$;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.ModuleSerializationProxy;

public final class LambdaFunction$ implements Serializable {
   public static final LambdaFunction$ MODULE$ = new LambdaFunction$();

   public LambdaFunction apply(final ColumnNode function, final Seq arguments) {
      return new LambdaFunction(function, arguments, CurrentOrigin$.MODULE$.get());
   }

   public LambdaFunction apply(final ColumnNode function, final Seq arguments, final Origin origin) {
      return new LambdaFunction(function, arguments, origin);
   }

   public Option unapply(final LambdaFunction x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.function(), x$0.arguments(), x$0.origin())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LambdaFunction$.class);
   }

   private LambdaFunction$() {
   }
}
