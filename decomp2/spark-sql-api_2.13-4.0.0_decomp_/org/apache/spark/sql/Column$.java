package org.apache.spark.sql;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.catalyst.trees.CurrentOrigin$;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.internal.ColumnNode;
import org.apache.spark.sql.internal.UnresolvedFunction;
import org.apache.spark.sql.internal.UnresolvedFunction$;
import scala.Function0;
import scala.MatchError;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.package.;

public final class Column$ {
   public static final Column$ MODULE$ = new Column$();

   public Column apply(final String colName) {
      return new Column(colName);
   }

   public Column apply(final Function0 node) {
      return (Column)CurrentOrigin$.MODULE$.withOrigin(() -> new Column((ColumnNode)node.apply()));
   }

   public Column fnWithOptions(final String name, final Iterator options, final Seq arguments) {
      Seq var10000;
      if (options.hasNext()) {
         Iterator flattenedKeyValueIterator = options.flatMap((x0$1) -> {
            if (x0$1 != null) {
               String k = (String)x0$1._1();
               String v = (String)x0$1._2();
               return .MODULE$.Iterator().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray(new Column[]{functions$.MODULE$.lit(k), functions$.MODULE$.lit(v)}));
            } else {
               throw new MatchError(x0$1);
            }
         });
         var10000 = (Seq)arguments.$colon$plus(functions$.MODULE$.map(flattenedKeyValueIterator.toSeq()));
      } else {
         var10000 = arguments;
      }

      Seq augmentedArguments = var10000;
      return this.fn(name, augmentedArguments);
   }

   public Column fn(final String name, final Seq inputs) {
      return this.fn(name, false, inputs);
   }

   public Column fn(final String name, final boolean isDistinct, final Seq inputs) {
      return this.fn(name, isDistinct, false, inputs);
   }

   public Column internalFn(final String name, final Seq inputs) {
      return this.fn(name, false, true, inputs);
   }

   private Column fn(final String name, final boolean isDistinct, final boolean isInternal, final Seq inputs) {
      return (Column)CurrentOrigin$.MODULE$.withOrigin(() -> MODULE$.apply((Function0)(() -> {
            Seq x$2 = (Seq)inputs.map((x$1) -> x$1.node());
            boolean x$5 = UnresolvedFunction$.MODULE$.apply$default$4();
            Origin x$6 = UnresolvedFunction$.MODULE$.apply$default$6();
            return new UnresolvedFunction(name, x$2, isDistinct, x$5, isInternal, x$6);
         })));
   }

   private Column$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
