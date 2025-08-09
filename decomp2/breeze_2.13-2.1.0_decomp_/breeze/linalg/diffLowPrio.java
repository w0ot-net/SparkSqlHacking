package breeze.linalg;

import breeze.generic.UFunc;
import breeze.math.Ring;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005=3\u0001b\u0001\u0003\u0011\u0002\u0007\u0005\u0012B\u0013\u0005\u0006!\u0001!\t!\u0005\u0005\u0006+\u0001!\u0019A\u0006\u0002\fI&4g\rT8x!JLwN\u0003\u0002\u0006\r\u00051A.\u001b8bY\u001eT\u0011aB\u0001\u0007EJ,WM_3\u0004\u0001M\u0011\u0001A\u0003\t\u0003\u00179i\u0011\u0001\u0004\u0006\u0002\u001b\u0005)1oY1mC&\u0011q\u0002\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005\u0011\u0002CA\u0006\u0014\u0013\t!BB\u0001\u0003V]&$\u0018aB5na24VmY\u000b\u0004/A\u0012C\u0003\u0002\r3u\t\u0003B!\u0007\u000e!W5\t\u0001!\u0003\u0002\u001c9\t!\u0011*\u001c9m\u0013\tibDA\u0003V\rVt7M\u0003\u0002 \r\u00059q-\u001a8fe&\u001c\u0007CA\u0011#\u0019\u0001!Qa\t\u0002C\u0002\u0011\u00121AV3d#\t)\u0003\u0006\u0005\u0002\fM%\u0011q\u0005\u0004\u0002\b\u001d>$\b.\u001b8h!\tY\u0011&\u0003\u0002+\u0019\t\u0019\u0011I\\=\u0011\u00071js&D\u0001\u0005\u0013\tqCAA\u0006EK:\u001cXMV3di>\u0014\bCA\u00111\t\u0015\t$A1\u0001%\u0005\u0005!\u0006\"B\u001a\u0003\u0001\b!\u0014a\u0001<fGB!1\"\u000e\u00118\u0013\t1DB\u0001\t%Y\u0016\u001c8\u000fJ2pY>tG\u0005\\3tgB\u0019A\u0006O\u0018\n\u0005e\"!A\u0002,fGR|'\u000fC\u0003<\u0005\u0001\u000fA(\u0001\u0002diB\u0019Q\bQ\u0018\u000e\u0003yR!a\u0010\u0007\u0002\u000fI,g\r\\3di&\u0011\u0011I\u0010\u0002\t\u00072\f7o\u001d+bO\")1I\u0001a\u0002\t\u0006!!/\u001b8h!\r)\u0005jL\u0007\u0002\r*\u0011qIB\u0001\u0005[\u0006$\b.\u0003\u0002J\r\n!!+\u001b8h\u001d\ta3*\u0003\u0002M\t\u0005!A-\u001b4gS\t\u0001aJ\u0003\u0002M\t\u0001"
)
public interface diffLowPrio {
   // $FF: synthetic method
   static UFunc.UImpl implVec$(final diffLowPrio $this, final .less.colon.less vec, final ClassTag ct, final Ring ring) {
      return $this.implVec(vec, ct, ring);
   }

   default UFunc.UImpl implVec(final .less.colon.less vec, final ClassTag ct, final Ring ring) {
      return new UFunc.UImpl(vec, ct, ring) {
         private final .less.colon.less vec$1;
         private final ClassTag ct$1;
         private final Ring ring$1;

         public double apply$mcDD$sp(final double v) {
            return UFunc.UImpl.apply$mcDD$sp$(this, v);
         }

         public float apply$mcDF$sp(final double v) {
            return UFunc.UImpl.apply$mcDF$sp$(this, v);
         }

         public int apply$mcDI$sp(final double v) {
            return UFunc.UImpl.apply$mcDI$sp$(this, v);
         }

         public double apply$mcFD$sp(final float v) {
            return UFunc.UImpl.apply$mcFD$sp$(this, v);
         }

         public float apply$mcFF$sp(final float v) {
            return UFunc.UImpl.apply$mcFF$sp$(this, v);
         }

         public int apply$mcFI$sp(final float v) {
            return UFunc.UImpl.apply$mcFI$sp$(this, v);
         }

         public double apply$mcID$sp(final int v) {
            return UFunc.UImpl.apply$mcID$sp$(this, v);
         }

         public float apply$mcIF$sp(final int v) {
            return UFunc.UImpl.apply$mcIF$sp$(this, v);
         }

         public int apply$mcII$sp(final int v) {
            return UFunc.UImpl.apply$mcII$sp$(this, v);
         }

         public DenseVector apply(final Object v) {
            return ((Vector)this.vec$1.apply(v)).length() <= 1 ? (DenseVector)DenseVector$.MODULE$.apply(scala.collection.immutable.Nil..MODULE$, this.ct$1) : DenseVector$.MODULE$.tabulate(((Vector)this.vec$1.apply(v)).length() - 1, (index) -> $anonfun$apply$5(this, v, BoxesRunTime.unboxToInt(index)), this.ct$1);
         }

         // $FF: synthetic method
         public static final Object $anonfun$apply$5(final Object $this, final Object v$5, final int index) {
            return $this.ring$1.$minus(((TensorLike)$this.vec$1.apply(v$5)).apply(BoxesRunTime.boxToInteger(index + 1)), ((TensorLike)$this.vec$1.apply(v$5)).apply(BoxesRunTime.boxToInteger(index)));
         }

         public {
            this.vec$1 = vec$1;
            this.ct$1 = ct$1;
            this.ring$1 = ring$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final diffLowPrio $this) {
   }
}
