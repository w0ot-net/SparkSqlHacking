package spire.random.rng;

import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.runtime.AbstractFunction2;
import scala.runtime.ModuleSerializationProxy;

public final class PcgSeed64$ extends AbstractFunction2 implements Serializable {
   public static final PcgSeed64$ MODULE$ = new PcgSeed64$();

   public final String toString() {
      return "PcgSeed64";
   }

   public PcgSeed64 apply(final long initState, final long initSeq) {
      return new PcgSeed64(initState, initSeq);
   }

   public Option unapply(final PcgSeed64 x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2.mcJJ.sp(x$0.initState(), x$0.initSeq())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(PcgSeed64$.class);
   }

   private PcgSeed64$() {
   }
}
