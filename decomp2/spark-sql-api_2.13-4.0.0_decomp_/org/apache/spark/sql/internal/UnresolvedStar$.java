package org.apache.spark.sql.internal;

import java.io.Serializable;
import org.apache.spark.sql.catalyst.trees.CurrentOrigin$;
import org.apache.spark.sql.catalyst.trees.Origin;
import scala.Option;
import scala.Some;
import scala.Tuple3;
import scala.None.;
import scala.runtime.AbstractFunction3;
import scala.runtime.ModuleSerializationProxy;

public final class UnresolvedStar$ extends AbstractFunction3 implements Serializable {
   public static final UnresolvedStar$ MODULE$ = new UnresolvedStar$();

   public Option $lessinit$greater$default$2() {
      return .MODULE$;
   }

   public Origin $lessinit$greater$default$3() {
      return CurrentOrigin$.MODULE$.get();
   }

   public final String toString() {
      return "UnresolvedStar";
   }

   public UnresolvedStar apply(final Option unparsedTarget, final Option planId, final Origin origin) {
      return new UnresolvedStar(unparsedTarget, planId, origin);
   }

   public Option apply$default$2() {
      return .MODULE$;
   }

   public Origin apply$default$3() {
      return CurrentOrigin$.MODULE$.get();
   }

   public Option unapply(final UnresolvedStar x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.unparsedTarget(), x$0.planId(), x$0.origin())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UnresolvedStar$.class);
   }

   private UnresolvedStar$() {
   }
}
