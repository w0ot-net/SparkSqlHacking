package breeze.linalg;

import breeze.generic.UFunc;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q3\u0001\u0002B\u0003\u0011\u0002\u0007\u0005\"B\u0016\u0005\u0006#\u0001!\tA\u0005\u0005\u0006-\u0001!\u0019a\u0006\u0005\u0006\u0017\u0002!\u0019\u0001\u0014\u0002\u000bgVlGj\\<Qe&|'B\u0001\u0004\b\u0003\u0019a\u0017N\\1mO*\t\u0001\"\u0001\u0004ce\u0016,'0Z\u0002\u0001'\t\u00011\u0002\u0005\u0002\r\u001f5\tQBC\u0001\u000f\u0003\u0015\u00198-\u00197b\u0013\t\u0001RB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003M\u0001\"\u0001\u0004\u000b\n\u0005Ui!\u0001B+oSR\f\u0011c];n'VlW.\u00192mKRC\u0017N\\4t+\rA2%\f\u000b\u00043=\u0002\u0005\u0003\u0002\u000e\u001cC1j\u0011\u0001A\u0005\u00039u\u0011A!S7qY&\u0011ad\b\u0002\u0006+\u001a+hn\u0019\u0006\u0003A\u001d\tqaZ3oKJL7\r\u0005\u0002#G1\u0001A!\u0002\u0013\u0003\u0005\u0004)#AA\"D#\t1\u0013\u0006\u0005\u0002\rO%\u0011\u0001&\u0004\u0002\b\u001d>$\b.\u001b8h!\ta!&\u0003\u0002,\u001b\t\u0019\u0011I\\=\u0011\u0005\tjC!\u0002\u0018\u0003\u0005\u0004)#!\u0001+\t\u000bA\u0012\u00019A\u0019\u0002\tYLWm\u001e\t\u0005\u0019I\nC'\u0003\u00024\u001b\t\u0001B\u0005\\3tg\u0012\u001aw\u000e\\8oI1,7o\u001d\t\u0004kubcB\u0001\u001c<\u001d\t9$(D\u00019\u0015\tI\u0014\"\u0001\u0004=e>|GOP\u0005\u0002\u001d%\u0011A(D\u0001\ba\u0006\u001c7.Y4f\u0013\tqtH\u0001\u0005Ji\u0016\u0014\u0018M\u00197f\u0015\taT\u0002C\u0003B\u0005\u0001\u000f!)\u0001\u0003u'Vl\u0007#B\"JY1bcB\u0001#H\u001b\u0005)%B\u0001$\u0006\u0003%y\u0007/\u001a:bi>\u00148/\u0003\u0002I\u000b\u0006)q\n]!eI&\u0011!*\b\u0002\u0006\u00136\u0004HNM\u0001\fgVl\u0017\n^3sCR|'/\u0006\u0002N'R\u0011a\n\u0016\t\u00055my%\u000bE\u00026!JK!!U \u0003\u0011%#XM]1u_J\u0004\"AI*\u0005\u000b9\u001a!\u0019A\u0013\t\u000b\u0005\u001b\u00019A+\u0011\u000b\rK%K\u0015*\u000f\u0005]CV\"A\u0003\n\u0005e+\u0011aA:v[&\u0012\u0001a\u0017\u0006\u00033\u0016\u0001"
)
public interface sumLowPrio {
   // $FF: synthetic method
   static UFunc.UImpl sumSummableThings$(final sumLowPrio $this, final .less.colon.less view, final UFunc.UImpl2 tSum) {
      return $this.sumSummableThings(view, tSum);
   }

   default UFunc.UImpl sumSummableThings(final .less.colon.less view, final UFunc.UImpl2 tSum) {
      return new UFunc.UImpl(view, tSum) {
         private final .less.colon.less view$1;
         private final UFunc.UImpl2 tSum$1;

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

         public Object apply(final Object v) {
            return ((IterableOnceOps)this.view$1.apply(v)).reduceLeft((x$1, x$2) -> this.tSum$1.apply(x$1, x$2));
         }

         public {
            this.view$1 = view$1;
            this.tSum$1 = tSum$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl sumIterator$(final sumLowPrio $this, final UFunc.UImpl2 tSum) {
      return $this.sumIterator(tSum);
   }

   default UFunc.UImpl sumIterator(final UFunc.UImpl2 tSum) {
      return new UFunc.UImpl(tSum) {
         private final UFunc.UImpl2 tSum$2;

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

         public Object apply(final Iterator v) {
            return v.reduce((x$3, x$4) -> this.tSum$2.apply(x$3, x$4));
         }

         public {
            this.tSum$2 = tSum$2;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final sumLowPrio $this) {
   }
}
