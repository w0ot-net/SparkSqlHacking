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

public final class UnresolvedExtractValue$ extends AbstractFunction3 implements Serializable {
   public static final UnresolvedExtractValue$ MODULE$ = new UnresolvedExtractValue$();

   public Origin $lessinit$greater$default$3() {
      return CurrentOrigin$.MODULE$.get();
   }

   public final String toString() {
      return "UnresolvedExtractValue";
   }

   public UnresolvedExtractValue apply(final ColumnNode child, final ColumnNode extraction, final Origin origin) {
      return new UnresolvedExtractValue(child, extraction, origin);
   }

   public Origin apply$default$3() {
      return CurrentOrigin$.MODULE$.get();
   }

   public Option unapply(final UnresolvedExtractValue x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple3(x$0.child(), x$0.extraction(), x$0.origin())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(UnresolvedExtractValue$.class);
   }

   private UnresolvedExtractValue$() {
   }
}
