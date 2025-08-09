package breeze.linalg;

import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.ScalarOf$;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class BroadcastedRows$ implements Serializable {
   public static final BroadcastedRows$ MODULE$ = new BroadcastedRows$();

   public ScalarOf scalarOf_BRows() {
      return ScalarOf$.MODULE$.dummy();
   }

   public BroadcastedRows.BroadcastRowsDMToIndexedSeq BroadcastRowsDMToIndexedSeq(final BroadcastedRows bc) {
      return new BroadcastedRows.BroadcastRowsDMToIndexedSeq(bc);
   }

   public BroadcastedRows apply(final Object underlying) {
      return new BroadcastedRows(underlying);
   }

   public Option unapply(final BroadcastedRows x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.underlying()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BroadcastedRows$.class);
   }

   private BroadcastedRows$() {
   }
}
