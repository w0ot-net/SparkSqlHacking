package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.CurrentOrigin$;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Option;
import scala.Some;
import scala.Tuple6;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction6;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class UnresolvedFunction$ extends AbstractFunction6 implements Serializable {
   public static final UnresolvedFunction$ MODULE$ = new UnresolvedFunction$();

   public boolean $lessinit$greater$default$3() {
      return false;
   }

   public boolean $lessinit$greater$default$4() {
      return false;
   }

   public boolean $lessinit$greater$default$5() {
      return false;
   }

   public Origin $lessinit$greater$default$6() {
      return CurrentOrigin$.MODULE$.get();
   }

   public final String toString() {
      return "UnresolvedFunction";
   }

   public UnresolvedFunction apply(final String functionName, final Seq arguments, final boolean isDistinct, final boolean isUserDefinedFunction, final boolean isInternal, final Origin origin) {
      return new UnresolvedFunction(functionName, arguments, isDistinct, isUserDefinedFunction, isInternal, origin);
   }

   public boolean apply$default$3() {
      return false;
   }

   public boolean apply$default$4() {
      return false;
   }

   public boolean apply$default$5() {
      return false;
   }

   public Origin apply$default$6() {
      return CurrentOrigin$.MODULE$.get();
   }

   public Option unapply(final UnresolvedFunction x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple6(x$0.functionName(), x$0.arguments(), BoxesRunTime.boxToBoolean(x$0.isDistinct()), BoxesRunTime.boxToBoolean(x$0.isUserDefinedFunction()), BoxesRunTime.boxToBoolean(x$0.isInternal()), x$0.origin())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UnresolvedFunction$.class);
   }

   private UnresolvedFunction$() {
   }
}
