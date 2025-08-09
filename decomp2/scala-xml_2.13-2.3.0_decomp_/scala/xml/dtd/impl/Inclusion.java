package scala.xml.dtd.impl;

import java.lang.invoke.SerializedLambda;
import scala.collection.Seq;
import scala.reflect.ScalaSignature;
import scala.runtime.BooleanRef;
import scala.runtime.BoxedUnit;
import scala.runtime.IntRef;

/** @deprecated */
@ScalaSignature(
   bytes = "\u0006\u0005\u00013\u0001\u0002B\u0003\u0011\u0002\u0007\u0005q!\u0004\u0005\u0006'\u0001!\t!\u0006\u0005\b3\u0001\u0011\rQ\"\u0001\u001b\u0011\u0015I\u0003\u0001\"\u0001+\u0005%Ien\u00197vg&|gN\u0003\u0002\u0007\u000f\u0005!\u0011.\u001c9m\u0015\tA\u0011\"A\u0002ei\u0012T!AC\u0006\u0002\u0007alGNC\u0001\r\u0003\u0015\u00198-\u00197b+\tq1e\u0005\u0002\u0001\u001fA\u0011\u0001#E\u0007\u0002\u0017%\u0011!c\u0003\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012A\u0006\t\u0003!]I!\u0001G\u0006\u0003\tUs\u0017\u000e^\u0001\u0007Y\u0006\u0014W\r\\:\u0016\u0003m\u00012\u0001H\u0010\"\u001b\u0005i\"B\u0001\u0010\f\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0003Au\u00111aU3r!\t\u00113\u0005\u0004\u0001\u0005\u000b\u0011\u0002!\u0019A\u0013\u0003\u0003\u0005\u000b\"AJ\b\u0011\u0005A9\u0013B\u0001\u0015\f\u0005\u001dqu\u000e\u001e5j]\u001e\f\u0011\"\u001b8dYV\u001c\u0018n\u001c8\u0015\u0007-rC\u0007\u0005\u0002\u0011Y%\u0011Qf\u0003\u0002\b\u0005>|G.Z1o\u0011\u0015y3\u00011\u00011\u0003\u0011!g-Y\u0019\u0011\u0007E\u0012\u0014%D\u0001\u0006\u0013\t\u0019TA\u0001\u0007EKR<vN\u001d3BkR|W\u000eC\u00036\u0007\u0001\u0007\u0001'\u0001\u0003eM\u0006\u0014\u0004F\u0002\u00018umjd\b\u0005\u0002\u0011q%\u0011\u0011h\u0003\u0002\u000bI\u0016\u0004(/Z2bi\u0016$\u0017aB7fgN\fw-Z\u0011\u0002y\u0005QB\u000b[5tA\rd\u0017m]:!o&dG\u000e\t2fAI,Wn\u001c<fI\u0006)1/\u001b8dK\u0006\nq(\u0001\u00043]E\u0002d\u0006\r"
)
public interface Inclusion {
   Seq labels();

   // $FF: synthetic method
   static boolean inclusion$(final Inclusion $this, final DetWordAutom dfa1, final DetWordAutom dfa2) {
      return $this.inclusion(dfa1, dfa2);
   }

   default boolean inclusion(final DetWordAutom dfa1, final DetWordAutom dfa2) {
      IntRef q1 = IntRef.create(0);
      IntRef q2 = IntRef.create(0);
      int max = 1 + dfa1.nstates() * dfa2.nstates();
      int[] mark = new int[max];
      BooleanRef result = BooleanRef.create(true);
      int current = encode$1(q1.elem, q2.elem, dfa1);
      IntRef last = IntRef.create(current);
      mark[last.elem] = max;

      while(current != 0 && result.elem) {
         this.labels().foreach((letter) -> {
            $anonfun$inclusion$1(dfa1, q1, dfa2, q2, result, mark, last, max, letter);
            return BoxedUnit.UNIT;
         });
         int ncurrent = mark[current];
         if (ncurrent != max) {
            q1.elem = decode1$1(ncurrent, dfa1);
            q2.elem = decode2$1(ncurrent, dfa1);
            current = ncurrent;
         } else {
            current = 0;
         }
      }

      return result.elem;
   }

   private static int encode$1(final int q1, final int q2, final DetWordAutom dfa1$1) {
      return 1 + q1 + q2 * dfa1$1.nstates();
   }

   private static int decode2$1(final int c, final DetWordAutom dfa1$1) {
      return (c - 1) / dfa1$1.nstates();
   }

   private static int decode1$1(final int c, final DetWordAutom dfa1$1) {
      return (c - 1) % dfa1$1.nstates();
   }

   // $FF: synthetic method
   static void $anonfun$inclusion$1(final DetWordAutom dfa1$1, final IntRef q1$1, final DetWordAutom dfa2$1, final IntRef q2$1, final BooleanRef result$1, final int[] mark$1, final IntRef last$1, final int max$1, final Object letter) {
      int r1 = dfa1$1.next(q1$1.elem, letter);
      int r2 = dfa2$1.next(q2$1.elem, letter);
      if (dfa1$1.isFinal(r1) && !dfa2$1.isFinal(r2)) {
         result$1.elem = false;
      }

      int test = encode$1(r1, r2, dfa1$1);
      if (mark$1[test] == 0) {
         mark$1[last$1.elem] = test;
         mark$1[test] = max$1;
         last$1.elem = test;
      }
   }

   static void $init$(final Inclusion $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
