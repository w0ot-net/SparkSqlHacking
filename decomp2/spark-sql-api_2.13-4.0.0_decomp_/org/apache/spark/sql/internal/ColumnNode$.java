package org.apache.spark.sql.internal;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.sql.catalyst.trees.Origin;
import org.apache.spark.sql.catalyst.trees.Origin$;
import scala.Option;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;

public final class ColumnNode$ {
   public static final ColumnNode$ MODULE$ = new ColumnNode$();
   private static final Origin NO_ORIGIN;

   static {
      NO_ORIGIN = new Origin(Origin$.MODULE$.apply$default$1(), Origin$.MODULE$.apply$default$2(), Origin$.MODULE$.apply$default$3(), Origin$.MODULE$.apply$default$4(), Origin$.MODULE$.apply$default$5(), Origin$.MODULE$.apply$default$6(), Origin$.MODULE$.apply$default$7(), Origin$.MODULE$.apply$default$8(), Origin$.MODULE$.apply$default$9());
   }

   public Origin NO_ORIGIN() {
      return NO_ORIGIN;
   }

   public Option normalize(final Option option) {
      return option.map((x$2) -> x$2.normalize());
   }

   public Seq normalize(final Seq nodes) {
      return (Seq)nodes.map((x$3) -> x$3.normalize());
   }

   public String argumentsToSql(final Seq nodes) {
      return this.textArgumentsToSql((Seq)nodes.map((x$4) -> x$4.sql()));
   }

   public String textArgumentsToSql(final Seq parts) {
      return parts.mkString("(", ", ", ")");
   }

   public String elementsToSql(final Seq elements, final String prefix) {
      return elements.nonEmpty() ? ((IterableOnceOps)elements.map((x$5) -> x$5.sql())).mkString(prefix, ", ", "") : "";
   }

   public String elementsToSql$default$2() {
      return "";
   }

   public String optionToSql(final Option option) {
      return (String)option.map((x$6) -> x$6.sql()).getOrElse(() -> "");
   }

   private ColumnNode$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
