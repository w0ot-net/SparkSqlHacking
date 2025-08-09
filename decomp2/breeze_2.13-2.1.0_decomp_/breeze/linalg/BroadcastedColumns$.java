package breeze.linalg;

import breeze.linalg.support.ScalarOf;
import breeze.linalg.support.ScalarOf$;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.runtime.ModuleSerializationProxy;

public final class BroadcastedColumns$ implements Serializable {
   public static final BroadcastedColumns$ MODULE$ = new BroadcastedColumns$();

   public ScalarOf scalarOf() {
      return ScalarOf$.MODULE$.dummy();
   }

   public BroadcastedColumns.BroadcastColumnsDMToIndexedSeq BroadcastColumnsDMToIndexedSeq(final BroadcastedColumns bc) {
      return new BroadcastedColumns.BroadcastColumnsDMToIndexedSeq(bc);
   }

   public BroadcastedColumns apply(final Object underlying) {
      return new BroadcastedColumns(underlying);
   }

   public Option unapply(final BroadcastedColumns x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(x$0.underlying()));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BroadcastedColumns$.class);
   }

   private BroadcastedColumns$() {
   }
}
