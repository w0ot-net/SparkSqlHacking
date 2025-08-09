package breeze.stats.mcmc;

import breeze.stats.distributions.Rand;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.None.;
import scala.reflect.ClassTag;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class ThreadedBufferedRand$ implements Serializable {
   public static final ThreadedBufferedRand$ MODULE$ = new ThreadedBufferedRand$();

   public int $lessinit$greater$default$2() {
      return 8192;
   }

   public final String toString() {
      return "ThreadedBufferedRand";
   }

   public ThreadedBufferedRand apply(final Rand wrapped, final int bufferSize, final ClassTag m) {
      return new ThreadedBufferedRand(wrapped, bufferSize, m);
   }

   public int apply$default$2() {
      return 8192;
   }

   public Option unapply(final ThreadedBufferedRand x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple2(x$0.wrapped(), BoxesRunTime.boxToInteger(x$0.bufferSize()))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(ThreadedBufferedRand$.class);
   }

   private ThreadedBufferedRand$() {
   }
}
