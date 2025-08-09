package breeze.stats;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ModeResult$ implements Serializable {
   public static final ModeResult$ MODULE$ = new ModeResult$();

   public final String toString() {
      return "ModeResult";
   }

   public ModeResult apply(final Object mode, final int frequency) {
      return new ModeResult(mode, frequency);
   }

   public Option unapply(final ModeResult x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.mode(), BoxesRunTime.boxToInteger(x$0.frequency()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ModeResult$.class);
   }

   private ModeResult$() {
   }
}
