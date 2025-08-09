package breeze.linalg;

import breeze.collection.mutable.Beam;
import breeze.generic.UFunc;
import java.lang.invoke.SerializedLambda;
import scala.;
import scala.collection.immutable.IndexedSeq;
import scala.math.Ordering;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005A3\u0001b\u0001\u0003\u0011\u0002\u0007\u0005A\u0001\u0003\u0005\u0006\u001f\u0001!\t!\u0005\u0005\u0006+\u0001!\u0019A\u0006\u0002\u0013\u0019><\bK]5pe&$\u00180\u0011:h)>\u00048J\u0003\u0002\u0006\r\u00051A.\u001b8bY\u001eT\u0011aB\u0001\u0007EJ,WM_3\u0014\u0005\u0001I\u0001C\u0001\u0006\u000e\u001b\u0005Y!\"\u0001\u0007\u0002\u000bM\u001c\u0017\r\\1\n\u00059Y!AB!osJ+g-\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005\u0011\u0002C\u0001\u0006\u0014\u0013\t!2B\u0001\u0003V]&$\u0018!D1sOR|\u0007o[,ji\"\fF+\u0006\u0003\u0018KyJEc\u0001\rA\u0017B)\u0011$H\u0012/c9\u0011!dG\u0007\u0002\t%\u0011A\u0004B\u0001\bCJ<Go\u001c9l\u0013\tqrDA\u0003J[Bd''\u0003\u0002!C\t)QKR;oG*\u0011!EB\u0001\bO\u0016tWM]5d!\t!S\u0005\u0004\u0001\u0005\u000b\u0019\u0012!\u0019A\u0014\u0003\u0003E\u000b\"\u0001K\u0016\u0011\u0005)I\u0013B\u0001\u0016\f\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0003\u0017\n\u00055Z!aA!osB\u0011!bL\u0005\u0003a-\u00111!\u00138u!\r\u0011$(\u0010\b\u0003gar!\u0001N\u001c\u000e\u0003UR!A\u000e\t\u0002\rq\u0012xn\u001c;?\u0013\u0005a\u0011BA\u001d\f\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u000f\u001f\u0003\u0015%sG-\u001a=fIN+\u0017O\u0003\u0002:\u0017A\u0011AE\u0010\u0003\u0006\u007f\t\u0011\ra\n\u0002\u0002\u0013\")\u0011I\u0001a\u0002\u0005\u0006\u0011\u0011\u000f\u001e\t\u0005\u0015\r\u001bS)\u0003\u0002E\u0017\t\u0001B\u0005\\3tg\u0012\u001aw\u000e\\8oI1,7o\u001d\t\u00055\u0019k\u0004*\u0003\u0002H\t\tY\u0011+^1tSR+gn]8s!\t!\u0013\nB\u0003K\u0005\t\u0007qEA\u0001W\u0011\u0015a%\u0001q\u0001N\u0003\ry'\u000f\u001a\t\u0004e9C\u0015BA(=\u0005!y%\u000fZ3sS:<\u0007"
)
public interface LowPriorityArgTopK {
   // $FF: synthetic method
   static UFunc.UImpl2 argtopkWithQT$(final LowPriorityArgTopK $this, final .less.colon.less qt, final Ordering ord) {
      return $this.argtopkWithQT(qt, ord);
   }

   default UFunc.UImpl2 argtopkWithQT(final .less.colon.less qt, final Ordering ord) {
      return new UFunc.UImpl2(ord, qt) {
         private final Ordering ord$1;
         private final .less.colon.less qt$1;

         public double apply$mcDDD$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDD$sp$(this, v, v2);
         }

         public float apply$mcDDF$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDF$sp$(this, v, v2);
         }

         public int apply$mcDDI$sp(final double v, final double v2) {
            return UFunc.UImpl2.apply$mcDDI$sp$(this, v, v2);
         }

         public double apply$mcDFD$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFD$sp$(this, v, v2);
         }

         public float apply$mcDFF$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFF$sp$(this, v, v2);
         }

         public int apply$mcDFI$sp(final double v, final float v2) {
            return UFunc.UImpl2.apply$mcDFI$sp$(this, v, v2);
         }

         public double apply$mcDID$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDID$sp$(this, v, v2);
         }

         public float apply$mcDIF$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDIF$sp$(this, v, v2);
         }

         public int apply$mcDII$sp(final double v, final int v2) {
            return UFunc.UImpl2.apply$mcDII$sp$(this, v, v2);
         }

         public double apply$mcFDD$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDD$sp$(this, v, v2);
         }

         public float apply$mcFDF$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDF$sp$(this, v, v2);
         }

         public int apply$mcFDI$sp(final float v, final double v2) {
            return UFunc.UImpl2.apply$mcFDI$sp$(this, v, v2);
         }

         public double apply$mcFFD$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFD$sp$(this, v, v2);
         }

         public float apply$mcFFF$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFF$sp$(this, v, v2);
         }

         public int apply$mcFFI$sp(final float v, final float v2) {
            return UFunc.UImpl2.apply$mcFFI$sp$(this, v, v2);
         }

         public double apply$mcFID$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFID$sp$(this, v, v2);
         }

         public float apply$mcFIF$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFIF$sp$(this, v, v2);
         }

         public int apply$mcFII$sp(final float v, final int v2) {
            return UFunc.UImpl2.apply$mcFII$sp$(this, v, v2);
         }

         public double apply$mcIDD$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDD$sp$(this, v, v2);
         }

         public float apply$mcIDF$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDF$sp$(this, v, v2);
         }

         public int apply$mcIDI$sp(final int v, final double v2) {
            return UFunc.UImpl2.apply$mcIDI$sp$(this, v, v2);
         }

         public double apply$mcIFD$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFD$sp$(this, v, v2);
         }

         public float apply$mcIFF$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFF$sp$(this, v, v2);
         }

         public int apply$mcIFI$sp(final int v, final float v2) {
            return UFunc.UImpl2.apply$mcIFI$sp$(this, v, v2);
         }

         public double apply$mcIID$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIID$sp$(this, v, v2);
         }

         public float apply$mcIIF$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIIF$sp$(this, v, v2);
         }

         public int apply$mcIII$sp(final int v, final int v2) {
            return UFunc.UImpl2.apply$mcIII$sp$(this, v, v2);
         }

         public IndexedSeq apply(final Object q, final int k) {
            Ordering var10000 = this.ord$1;
            QuasiTensor var4 = (QuasiTensor)this.qt$1.apply(q);
            Ordering ordK = var10000.on((i) -> var4.apply(i));
            Beam queue = new Beam(k, ordK);
            queue.$plus$plus$eq(((QuasiTensor)this.qt$1.apply(q)).keysIterator());
            return (IndexedSeq)queue.toIndexedSeq().sorted(ordK.reverse());
         }

         public {
            this.ord$1 = ord$1;
            this.qt$1 = qt$1;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   static void $init$(final LowPriorityArgTopK $this) {
   }
}
