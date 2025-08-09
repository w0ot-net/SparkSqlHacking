package breeze.linalg;

import breeze.generic.UFunc;
import java.lang.invoke.SerializedLambda;
import scala.Tuple2;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005U4qAB\u0004\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0003\u0019\u0001\u0011\r\u0011D\u0002\u0003>\u0001\u0001q\u0004\"B6\u0004\t\u0003a\u0007\"B8\u0004\t\u0003\u0001(AD*mS\u000e,W*\u0019;sSb|\u0005o\u001d\u0006\u0003\u0011%\ta\u0001\\5oC2<'\"\u0001\u0006\u0002\r\t\u0014X-\u001a>f\u0007\u0001\u0019\"\u0001A\u0007\u0011\u00059\tR\"A\b\u000b\u0003A\tQa]2bY\u0006L!AE\b\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\tQ\u0003\u0005\u0002\u000f-%\u0011qc\u0004\u0002\u0005+:LG/\u0001\u0007paN+G/\u00138QY\u0006\u001cW-\u0006\u0003\u001b]aZT#A\u000e\u0011\tq\u0011\u0003F\u000f\b\u0003;\u0001j\u0011A\b\u0006\u0003?\u001d\t\u0011b\u001c9fe\u0006$xN]:\n\u0005\u0005r\u0012!B(q'\u0016$\u0018BA\u0012%\u00051Ie\u000e\u00157bG\u0016LU\u000e\u001d73\u0013\t)cEA\u0003V\rVt7M\u0003\u0002(\u0013\u00059q-\u001a8fe&\u001c\u0007#B\u0015+Y]RT\"A\u0004\n\u0005-:!aC*mS\u000e,W*\u0019;sSb\u0004\"!\f\u0018\r\u0001\u0011)qF\u0001b\u0001a\t\u00111*M\t\u0003cQ\u0002\"A\u0004\u001a\n\u0005Mz!a\u0002(pi\"Lgn\u001a\t\u0003\u001dUJ!AN\b\u0003\u0007\u0005s\u0017\u0010\u0005\u0002.q\u0011)\u0011H\u0001b\u0001a\t\u00111J\r\t\u0003[m\"Q\u0001\u0010\u0002C\u0002A\u0012\u0011A\u0016\u0002\u000f'6{\u0005oU3u\u0013:\u0004F.Y2f+\u0011y4i\u0015-\u0014\u0007\ri\u0001\t\u0005\u0003\u001dE\u0005;\u0006#B\u0015+\u0005J;\u0006CA\u0017D\t%y3\u0001)A\u0001\u0002\u000b\u0007\u0001\u0007K\u0002D\u000b\"\u0003\"A\u0004$\n\u0005\u001d{!aC:qK\u000eL\u0017\r\\5{K\u0012\fTaI%K\u0019.s!A\u0004&\n\u0005-{\u0011aA%oiF\"A%T)\u0011\u001d\tq\u0015+D\u0001P\u0015\t\u00016\"\u0001\u0004=e>|GOP\u0005\u0002!A\u0011Qf\u0015\u0003\ns\r\u0001\u000b\u0011!AC\u0002AB3aU#Vc\u0015\u0019\u0013J\u0013,Lc\u0011!S*\u0015\t\u0011\u00055BF!\u0003\u001f\u0004A\u0003\u0005\tQ1\u00011Q\u0019AVIW0bMF*1e\u0017/_;:\u0011a\u0002X\u0005\u0003;>\ta\u0001R8vE2,\u0017\u0007\u0002\u0013N#B\tTaI%KA.\u000bD\u0001J'R!E*1EY2fI:\u0011abY\u0005\u0003I>\tQA\u00127pCR\fD\u0001J'R!E*1e\u001a5kS:\u0011a\u0002[\u0005\u0003S>\tA\u0001T8oOF\"A%T)\u0011\u0003\u0019a\u0014N\\5u}Q\tQ\u000eE\u0003o\u0007\t\u0013v+D\u0001\u0001\u0003\u0015\t\u0007\u000f\u001d7z)\r)\u0012o\u001d\u0005\u0006e\u0016\u0001\r!Q\u0001\u0002C\")A/\u0002a\u0001/\u0006\t!\r"
)
public interface SliceMatrixOps {
   // $FF: synthetic method
   static UFunc.InPlaceImpl2 opSetInPlace$(final SliceMatrixOps $this) {
      return $this.opSetInPlace();
   }

   default UFunc.InPlaceImpl2 opSetInPlace() {
      return new SMOpSetInPlace();
   }

   static void $init$(final SliceMatrixOps $this) {
   }

   public class SMOpSetInPlace implements UFunc.InPlaceImpl2 {
      // $FF: synthetic field
      public final SliceMatrixOps $outer;

      public void apply$mcD$sp(final Object v, final double v2) {
         UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
      }

      public void apply$mcF$sp(final Object v, final float v2) {
         UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
      }

      public void apply$mcI$sp(final Object v, final int v2) {
         UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
      }

      public void apply(final SliceMatrix a, final Object b) {
         a.keysIterator().foreach((k) -> {
            $anonfun$apply$4(a, b, k);
            return BoxedUnit.UNIT;
         });
      }

      public void apply$mcIID$sp(final SliceMatrix a, final double b) {
         this.apply((SliceMatrix)a, BoxesRunTime.boxToDouble(b));
      }

      public void apply$mcIIF$sp(final SliceMatrix a, final float b) {
         this.apply((SliceMatrix)a, BoxesRunTime.boxToFloat(b));
      }

      public void apply$mcIII$sp(final SliceMatrix a, final int b) {
         this.apply((SliceMatrix)a, BoxesRunTime.boxToInteger(b));
      }

      public void apply$mcIIJ$sp(final SliceMatrix a, final long b) {
         this.apply((SliceMatrix)a, BoxesRunTime.boxToLong(b));
      }

      // $FF: synthetic method
      public SliceMatrixOps breeze$linalg$SliceMatrixOps$SMOpSetInPlace$$$outer() {
         return this.$outer;
      }

      // $FF: synthetic method
      public static final void $anonfun$apply$4(final SliceMatrix a$1, final Object b$1, final Tuple2 k) {
         a$1.update(k, b$1);
      }

      public SMOpSetInPlace() {
         if (SliceMatrixOps.this == null) {
            throw null;
         } else {
            this.$outer = SliceMatrixOps.this;
            super();
         }
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
