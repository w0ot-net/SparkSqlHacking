package scala.collection;

import java.lang.invoke.SerializedLambda;
import scala.Function0;
import scala.Function1;
import scala.collection.mutable.Builder;
import scala.math.Numeric;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005u3q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003-\u0001\u0011\u0005Q\u0006C\u00032\u0001\u0011\u0005#\u0007C\u0003C\u0001\u0011\u00053\tC\u0003P\u0001\u0011\u0005\u0003KA\rTiJL7\r^(qi&l\u0017N_3e'\u0016\fh)Y2u_JL(BA\u0004\t\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u0013\u0005)1oY1mC\u000e\u0001QC\u0001\u0007\u0018'\r\u0001Q\"\u0005\t\u0003\u001d=i\u0011\u0001C\u0005\u0003!!\u0011a!\u00118z%\u00164\u0007c\u0001\n\u0014+5\ta!\u0003\u0002\u0015\r\tQ1+Z9GC\u000e$xN]=\u0011\u0005Y9B\u0002\u0001\u0003\u00071\u0001!)\u0019A\r\u0003\u0005\r\u001bUC\u0001\u000e##\tYb\u0004\u0005\u0002\u000f9%\u0011Q\u0004\u0003\u0002\b\u001d>$\b.\u001b8h!\u0015\u0011r$\t\u0015,\u0013\t\u0001cA\u0001\u0004TKF|\u0005o\u001d\t\u0003-\t\"QaI\fC\u0002\u0011\u0012\u0011!Q\t\u00037\u0015\u0002\"A\u0004\u0014\n\u0005\u001dB!aA!osB\u0011!#K\u0005\u0003U\u0019\u00111aU3r!\r\u0011\u0012&I\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u00039\u0002\"AD\u0018\n\u0005AB!\u0001B+oSR\fAAZ5mYV\u00111g\u000e\u000b\u0003iu\"\"!\u000e\u001d\u0011\u0007Y9b\u0007\u0005\u0002\u0017o\u0011)1E\u0001b\u0001I!1\u0011H\u0001CA\u0002i\nA!\u001a7f[B\u0019ab\u000f\u001c\n\u0005qB!\u0001\u0003\u001fcs:\fW.\u001a \t\u000by\u0012\u0001\u0019A \u0002\u00039\u0004\"A\u0004!\n\u0005\u0005C!aA%oi\u0006AA/\u00192vY\u0006$X-\u0006\u0002E\u0011R\u0011QI\u0014\u000b\u0003\r&\u00032AF\fH!\t1\u0002\nB\u0003$\u0007\t\u0007A\u0005C\u0003K\u0007\u0001\u00071*A\u0001g!\u0011qAjP$\n\u00055C!!\u0003$v]\u000e$\u0018n\u001c82\u0011\u0015q4\u00011\u0001@\u0003\u0019\u0019wN\\2biV\u0011\u0011\u000b\u0016\u000b\u0003%V\u00032AF\fT!\t1B\u000bB\u0003$\t\t\u0007A\u0005C\u0003W\t\u0001\u0007q+A\u0002ygN\u00042A\u0004-[\u0013\tI\u0006B\u0001\u0006=e\u0016\u0004X-\u0019;fIz\u00022AE.T\u0013\tafA\u0001\u0005Ji\u0016\u0014\u0018M\u00197f\u0001"
)
public interface StrictOptimizedSeqFactory extends SeqFactory {
   // $FF: synthetic method
   static SeqOps fill$(final StrictOptimizedSeqFactory $this, final int n, final Function0 elem) {
      return $this.fill(n, elem);
   }

   default SeqOps fill(final int n, final Function0 elem) {
      Builder b = this.newBuilder();
      b.sizeHint(n);

      for(int i = 0; i < n; ++i) {
         Object $plus$eq_elem = elem.apply();
         b.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }

      return (SeqOps)b.result();
   }

   // $FF: synthetic method
   static SeqOps tabulate$(final StrictOptimizedSeqFactory $this, final int n, final Function1 f) {
      return $this.tabulate(n, f);
   }

   default SeqOps tabulate(final int n, final Function1 f) {
      Builder b = this.newBuilder();
      b.sizeHint(n);

      for(int i = 0; i < n; ++i) {
         Object $plus$eq_elem = f.apply(i);
         b.addOne($plus$eq_elem);
         $plus$eq_elem = null;
      }

      return (SeqOps)b.result();
   }

   // $FF: synthetic method
   static SeqOps concat$(final StrictOptimizedSeqFactory $this, final scala.collection.immutable.Seq xss) {
      return $this.concat(xss);
   }

   default SeqOps concat(final scala.collection.immutable.Seq xss) {
      Builder b = this.newBuilder();
      SeqView knownSizes = xss.view().map((x$13) -> BoxesRunTime.boxToInteger($anonfun$concat$2(x$13)));
      if (knownSizes.forall((x$14) -> x$14 >= 0)) {
         b.sizeHint(BoxesRunTime.unboxToInt(knownSizes.sum(Numeric.IntIsIntegral$.MODULE$)));
      }

      xss.foreach((xs) -> (Builder)b.$plus$plus$eq(xs));
      return (SeqOps)b.result();
   }

   // $FF: synthetic method
   static int $anonfun$concat$2(final Iterable x$13) {
      return x$13.knownSize();
   }

   static void $init$(final StrictOptimizedSeqFactory $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
