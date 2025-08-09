package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.CurrentOrigin$;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction4;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class InvokeInlineUserDefinedFunction$ extends AbstractFunction4 implements Serializable {
   public static final InvokeInlineUserDefinedFunction$ MODULE$ = new InvokeInlineUserDefinedFunction$();

   public boolean $lessinit$greater$default$3() {
      return false;
   }

   public Origin $lessinit$greater$default$4() {
      return CurrentOrigin$.MODULE$.get();
   }

   public final String toString() {
      return "InvokeInlineUserDefinedFunction";
   }

   public InvokeInlineUserDefinedFunction apply(final UserDefinedFunctionLike function, final Seq arguments, final boolean isDistinct, final Origin origin) {
      return new InvokeInlineUserDefinedFunction(function, arguments, isDistinct, origin);
   }

   public boolean apply$default$3() {
      return false;
   }

   public Origin apply$default$4() {
      return CurrentOrigin$.MODULE$.get();
   }

   public Option unapply(final InvokeInlineUserDefinedFunction x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.function(), x$0.arguments(), BoxesRunTime.boxToBoolean(x$0.isDistinct()), x$0.origin())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(InvokeInlineUserDefinedFunction$.class);
   }

   private InvokeInlineUserDefinedFunction$() {
   }
}
