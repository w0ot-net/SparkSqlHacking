package org.apache.spark.rpc;

import scala.Function1;
import scala.concurrent.Future;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M3Qa\u0002\u0005\u0001\u0015AA\u0001\u0002\u0007\u0001\u0003\u0006\u0004%\tA\u0007\u0005\tY\u0001\u0011\t\u0011)A\u00057!AQ\u0006\u0001B\u0001B\u0003%a\u0006\u0003\u0005A\u0001\t\r\t\u0015a\u0003B\u0011\u00159\u0005\u0001\"\u0001I\u0011\u0015y\u0005\u0001\"\u0001Q\u0005I\t%m\u001c:uC\ndWM\u00159d\rV$XO]3\u000b\u0005%Q\u0011a\u0001:qG*\u00111\u0002D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001b9\ta!\u00199bG\",'\"A\b\u0002\u0007=\u0014x-\u0006\u0002\u0012GM\u0011\u0001A\u0005\t\u0003'Yi\u0011\u0001\u0006\u0006\u0002+\u0005)1oY1mC&\u0011q\u0003\u0006\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0019,H/\u001e:f\u0007\u0001)\u0012a\u0007\t\u00049}\tS\"A\u000f\u000b\u0005y!\u0012AC2p]\u000e,(O]3oi&\u0011\u0001%\b\u0002\u0007\rV$XO]3\u0011\u0005\t\u001aC\u0002\u0001\u0003\u0006I\u0001\u0011\r!\n\u0002\u0002)F\u0011a%\u000b\t\u0003'\u001dJ!\u0001\u000b\u000b\u0003\u000f9{G\u000f[5oOB\u00111CK\u0005\u0003WQ\u00111!\u00118z\u0003\u001d1W\u000f^;sK\u0002\nqa\u001c8BE>\u0014H\u000f\u0005\u0003\u0014_Ej\u0014B\u0001\u0019\u0015\u0005%1UO\\2uS>t\u0017\u0007\u0005\u00023u9\u00111\u0007\u000f\b\u0003i]j\u0011!\u000e\u0006\u0003me\ta\u0001\u0010:p_Rt\u0014\"A\u000b\n\u0005e\"\u0012a\u00029bG.\fw-Z\u0005\u0003wq\u0012\u0011\u0002\u00165s_^\f'\r\\3\u000b\u0005e\"\u0002CA\n?\u0013\tyDC\u0001\u0003V]&$\u0018AC3wS\u0012,gnY3%mA\u0019!)R\u0011\u000e\u0003\rS!\u0001\u0012\u000b\u0002\u000fI,g\r\\3di&\u0011ai\u0011\u0002\t\u00072\f7o\u001d+bO\u00061A(\u001b8jiz\"2!S'O)\tQE\nE\u0002L\u0001\u0005j\u0011\u0001\u0003\u0005\u0006\u0001\u0016\u0001\u001d!\u0011\u0005\u00061\u0015\u0001\ra\u0007\u0005\u0006[\u0015\u0001\rAL\u0001\u0006C\n|'\u000f\u001e\u000b\u0003{ECQA\u0015\u0004A\u0002E\n\u0011\u0001\u001e"
)
public class AbortableRpcFuture {
   private final Future future;
   private final Function1 onAbort;

   public Future future() {
      return this.future;
   }

   public void abort(final Throwable t) {
      this.onAbort.apply(t);
   }

   public AbortableRpcFuture(final Future future, final Function1 onAbort, final ClassTag evidence$6) {
      this.future = future;
      this.onAbort = onAbort;
   }
}
