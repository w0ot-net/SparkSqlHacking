package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.CurrentOrigin$;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.collection.immutable.Seq;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class CaseWhenOtherwise$ extends AbstractFunction3 implements Serializable {
   public static final CaseWhenOtherwise$ MODULE$ = new CaseWhenOtherwise$();

   public Option $lessinit$greater$default$2() {
      return .MODULE$;
   }

   public Origin $lessinit$greater$default$3() {
      return CurrentOrigin$.MODULE$.get();
   }

   public final String toString() {
      return "CaseWhenOtherwise";
   }

   public CaseWhenOtherwise apply(final Seq branches, final Option otherwise, final Origin origin) {
      return new CaseWhenOtherwise(branches, otherwise, origin);
   }

   public Option apply$default$2() {
      return .MODULE$;
   }

   public Origin apply$default$3() {
      return CurrentOrigin$.MODULE$.get();
   }

   public Option unapply(final CaseWhenOtherwise x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.branches(), x$0.otherwise(), x$0.origin())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(CaseWhenOtherwise$.class);
   }

   private CaseWhenOtherwise$() {
   }
}
