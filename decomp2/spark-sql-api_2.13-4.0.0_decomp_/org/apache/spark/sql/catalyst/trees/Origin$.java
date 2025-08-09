package org.apache.spark.sql.catalyst.trees;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple9;
import scala.None.;
import scala.runtime.AbstractFunction9;
import scala.runtime.ModuleSerializationProxy;

public final class Origin$ extends AbstractFunction9 implements Serializable {
   public static final Origin$ MODULE$ = new Origin$();

   public Option $lessinit$greater$default$1() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$2() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$3() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$4() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$5() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$6() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$7() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$8() {
      return .MODULE$;
   }

   public Option $lessinit$greater$default$9() {
      return .MODULE$;
   }

   public final String toString() {
      return "Origin";
   }

   public Origin apply(final Option line, final Option startPosition, final Option startIndex, final Option stopIndex, final Option sqlText, final Option objectType, final Option objectName, final Option stackTrace, final Option pysparkErrorContext) {
      return new Origin(line, startPosition, startIndex, stopIndex, sqlText, objectType, objectName, stackTrace, pysparkErrorContext);
   }

   public Option apply$default$1() {
      return .MODULE$;
   }

   public Option apply$default$2() {
      return .MODULE$;
   }

   public Option apply$default$3() {
      return .MODULE$;
   }

   public Option apply$default$4() {
      return .MODULE$;
   }

   public Option apply$default$5() {
      return .MODULE$;
   }

   public Option apply$default$6() {
      return .MODULE$;
   }

   public Option apply$default$7() {
      return .MODULE$;
   }

   public Option apply$default$8() {
      return .MODULE$;
   }

   public Option apply$default$9() {
      return .MODULE$;
   }

   public Option unapply(final Origin x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple9(x$0.line(), x$0.startPosition(), x$0.startIndex(), x$0.stopIndex(), x$0.sqlText(), x$0.objectType(), x$0.objectName(), x$0.stackTrace(), x$0.pysparkErrorContext())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Origin$.class);
   }

   private Origin$() {
   }
}
