package breeze.linalg.operators;

import breeze.generic.UFunc;
import breeze.linalg.SparseVector;
import breeze.linalg.VectorBuilder;
import breeze.linalg.VectorBuilder$;
import breeze.math.Ring;
import breeze.math.Semiring;
import breeze.storage.Zero;
import breeze.storage.Zero$;
import breeze.util.ArrayUtil$;
import breeze.util.ReflectionUtil$;
import java.lang.invoke.SerializedLambda;
import java.util.Arrays;
import scala.Predef.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005efa\u0002\u0007\u000e!\u0003\r\t\u0001\u0006\u0005\u0006?\u0001!\t\u0001\t\u0005\u0006I\u0001!\u0019!\n\u0005\u0006\u0001\u0002!\u0019!\u0011\u0005\u0006\u001f\u0002!\u0019\u0001\u0015\u0005\u0006_\u0002!\u0019\u0001\u001d\u0005\u0006}\u0002!\u0019a \u0005\b\u00037\u0001A1AA\u000f\u0011\u001d\t)\u0005\u0001C\u0002\u0003\u000fBq!!\u001a\u0001\t\u0007\t9\u0007C\u0004\u0002z\u0001!\u0019!a\u001f\t\u000f\u0005u\u0005\u0001b\u0001\u0002 \n92\u000b]1sg\u00164Vm\u0019;pe~;UM\\3sS\u000e|\u0005o\u001d\u0006\u0003\u001d=\t\u0011b\u001c9fe\u0006$xN]:\u000b\u0005A\t\u0012A\u00027j]\u0006dwMC\u0001\u0013\u0003\u0019\u0011'/Z3{K\u000e\u00011c\u0001\u0001\u00167A\u0011a#G\u0007\u0002/)\t\u0001$A\u0003tG\u0006d\u0017-\u0003\u0002\u001b/\t1\u0011I\\=SK\u001a\u0004\"\u0001H\u000f\u000e\u00035I!AH\u0007\u0003\u0015\u001d+g.\u001a:jG>\u00038/\u0001\u0004%S:LG\u000f\n\u000b\u0002CA\u0011aCI\u0005\u0003G]\u0011A!\u00168ji\u0006\u0001\u0013.\u001c9m?>\u00038+\u001a;`\u0013:\u0004F.Y2f?N3vl\u0015,`\u000f\u0016tWM]5d+\t1s'F\u0001(!\u0011A3&M\u0019\u000f\u0005qI\u0013B\u0001\u0016\u000e\u0003\u0015y\u0005oU3u\u0013\taSF\u0001\u0007J]Bc\u0017mY3J[Bd''\u0003\u0002/_\t)QKR;oG*\u0011\u0001'E\u0001\bO\u0016tWM]5d!\r\u00114'N\u0007\u0002\u001f%\u0011Ag\u0004\u0002\r'B\f'o]3WK\u000e$xN\u001d\t\u0003m]b\u0001\u0001B\u00039\u0005\t\u0007\u0011HA\u0001U#\tQT\b\u0005\u0002\u0017w%\u0011Ah\u0006\u0002\b\u001d>$\b.\u001b8h!\t1b(\u0003\u0002@/\t\u0019\u0011I\\=\u0002?%l\u0007\u000f\\0PaN+GoX%o!2\f7-Z0T-~\u001bvlR3oKJL7-\u0006\u0002C\rR\u00111i\u0012\t\u0005Q-\"U\tE\u00023g\u0015\u0003\"A\u000e$\u0005\u000ba\u001a!\u0019A\u001d\t\u000f!\u001b\u0011\u0011!a\u0002\u0013\u0006QQM^5eK:\u001cW\r\n\u001b\u0011\u0007)kU)D\u0001L\u0015\ta\u0015#A\u0004ti>\u0014\u0018mZ3\n\u00059[%\u0001\u0002.fe>\f!$[7qY~{\u0005oX*W?N{V-]0T-~;UM\\3sS\u000e,2!U.d)\r\u0011F\r\u001c\t\u0007'^S\u0016MY1\u000f\u0005Q+V\"A\u0018\n\u0005Y{\u0013!B+Gk:\u001c\u0017B\u0001-Z\u0005\u0019)\u0016*\u001c9me)\u0011ak\f\t\u0003mm#Q\u0001\u0018\u0003C\u0002u\u0013!a\u00149\u0012\u0005ir\u0006C\u0001\u000f`\u0013\t\u0001WB\u0001\u0004PaRK\b/\u001a\t\u0004eM\u0012\u0007C\u0001\u001cd\t\u0015ADA1\u0001:\u0011\u001d)G!!AA\u0004\u0019\f!\"\u001a<jI\u0016t7-\u001a\u00136!\r9'NY\u0007\u0002Q*\u0011\u0011.E\u0001\u0005[\u0006$\b.\u0003\u0002lQ\nA1+Z7je&tw\rC\u0003n\t\u0001\u000fa.\u0001\u0002paB11k\u0016.cE\n\fQ$[7qY~{\u0005/\u00113e?N3vlU0fc~\u001bfkX$f]\u0016\u0014\u0018nY\u000b\u0003cj$\"A]>\u0011\u000bM4\b0\u001f=\u000f\u0005q!\u0018BA;\u000e\u0003\u0015y\u0005/\u00113e\u0013\t9XFA\u0003J[Bd'\u0007E\u00023ge\u0004\"A\u000e>\u0005\u000ba*!\u0019A\u001d\t\u000fq,\u0011\u0011!a\u0002{\u0006QQM^5eK:\u001cW\r\n\u001c\u0011\u0007\u001dT\u00170A\u000fj[Bdwl\u00149Tk\n|6KV0T?\u0016\fxl\u0015,`\u000f\u0016tWM]5d+\u0011\t\t!a\u0004\u0015\t\u0005\r\u0011\u0011\u0003\t\n\u0003\u000b1\u00181BA\u0007\u0003\u0017q1\u0001HA\u0004\u0013\r\tI!D\u0001\u0006\u001fB\u001cVO\u0019\t\u0005eM\ni\u0001E\u00027\u0003\u001f!Q\u0001\u000f\u0004C\u0002eB\u0011\"a\u0005\u0007\u0003\u0003\u0005\u001d!!\u0006\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$s\u0007E\u0003h\u0003/\ti!C\u0002\u0002\u001a!\u0014AAU5oO\u0006\u0019\u0013.\u001c9m?>\u0003X*\u001e7TG\u0006d\u0017M]0T-~\u001bv,Z9`'Z{v)\u001a8fe&\u001cW\u0003BA\u0010\u0003[!b!!\t\u00020\u0005U\u0002#CA\u0012m\u0006%\u00121FA\u0015\u001d\ra\u0012QE\u0005\u0004\u0003Oi\u0011aC(q\u001bVd7kY1mCJ\u0004BAM\u001a\u0002,A\u0019a'!\f\u0005\u000ba:!\u0019A\u001d\t\u0013\u0005Er!!AA\u0004\u0005M\u0012AC3wS\u0012,gnY3%qA!qM[A\u0016\u0011%\t9dBA\u0001\u0002\b\tI$\u0001\u0006fm&$WM\\2fIe\u0002b!a\u000f\u0002B\u0005-RBAA\u001f\u0015\r\tydF\u0001\be\u00164G.Z2u\u0013\u0011\t\u0019%!\u0010\u0003\u0011\rc\u0017m]:UC\u001e\fa%[7qY~{\u0005oX*W?N{V-]0T-~;UM\\3sS\u000e|v\n]'vY6\u000bGO]5y+\u0011\tI%a\u0016\u0015\r\u0005-\u0013\u0011LA0!%\tiE^A*\u0003+\n\u0019FD\u0002\u001d\u0003\u001fJ1!!\u0015\u000e\u0003-y\u0005/T;m\u001b\u0006$(/\u001b=\u0011\tI\u001a\u0014Q\u000b\t\u0004m\u0005]C!\u0002\u001d\t\u0005\u0004I\u0004\"CA.\u0011\u0005\u0005\t9AA/\u0003-)g/\u001b3f]\u000e,G%\r\u0019\u0011\t\u001dT\u0017Q\u000b\u0005\n\u0003CB\u0011\u0011!a\u0002\u0003G\n1\"\u001a<jI\u0016t7-\u001a\u00132cA1\u00111HA!\u0003+\nq%[7qY~{\u0005oX*W?N3v,Z9`'Z{v)\u001a8fe&\u001cwl\u00149Nk2\u001c6-\u00197beV!\u0011\u0011NA9)\u0011\tY'a\u001d\u0011\u0013\u0005\rb/!\u001c\u0002n\u00055\u0004\u0003\u0002\u001a4\u0003_\u00022ANA9\t\u0015A\u0014B1\u0001:\u0011\u001d\t)(\u0003a\u0002\u0003o\nAA]5oOB!qM[A8\u0003\u0015JW\u000e\u001d7`g\u000e\fG.Z!eI~\u001bfkX*`'Z{\u0016J\u001c)mC\u000e,wlR3oKJL7-\u0006\u0003\u0002~\u0005=ECBA@\u0003#\u000b9\n\u0005\u0006\u0002\u0002\u0006\u001d\u00151RAG\u0003\u0017s1AMAB\u0013\r\t)iD\u0001\tg\u000e\fG.Z!eI&\u0019\u0011\u0011R\u0017\u0003\u0019%s\u0007\u000b\\1dK&k\u0007\u000f\\\u001a\u0011\tI\u001a\u0014Q\u0012\t\u0004m\u0005=E!\u0002\u001d\u000b\u0005\u0004I\u0004\"CAJ\u0015\u0005\u0005\t9AAK\u0003-)g/\u001b3f]\u000e,G%\r\u001a\u0011\t\u001dT\u0017Q\u0012\u0005\n\u00033S\u0011\u0011!a\u0002\u00037\u000b1\"\u001a<jI\u0016t7-\u001a\u00132gA1\u00111HA!\u0003\u001b\u000b1$[7qY~{\u0005oX*W?N3v,Z9`'Z{v)\u001a8fe&\u001cWCBAQ\u0003[\u000b9\u000b\u0006\u0004\u0002$\u0006=\u00161\u0017\t\u000b'^\u000b)+!+\u0002*\u0006%\u0006c\u0001\u001c\u0002(\u0012)Al\u0003b\u0001;B!!gMAV!\r1\u0014Q\u0016\u0003\u0006q-\u0011\r!\u000f\u0005\u0007[.\u0001\u001d!!-\u0011\u0015M;\u0016QUAV\u0003W\u000bY\u000bC\u0004\u00026.\u0001\u001d!a.\u0002\u0011M,W.\u001b:j]\u001e\u0004Ba\u001a6\u0002,\u0002"
)
public interface SparseVector_GenericOps extends GenericOps {
   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_SV_SV_Generic$(final SparseVector_GenericOps $this) {
      return $this.impl_OpSet_InPlace_SV_SV_Generic();
   }

   default UFunc.InPlaceImpl2 impl_OpSet_InPlace_SV_SV_Generic() {
      return new UFunc.InPlaceImpl2() {
         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final SparseVector b) {
            SparseVector result = b.copy();
            a.use(result.index(), result.data(), result.activeSize());
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl2 impl_OpSet_InPlace_SV_S_Generic$(final SparseVector_GenericOps $this, final Zero evidence$4) {
      return $this.impl_OpSet_InPlace_SV_S_Generic(evidence$4);
   }

   default UFunc.InPlaceImpl2 impl_OpSet_InPlace_SV_S_Generic(final Zero evidence$4) {
      Object zero = ((Zero).MODULE$.implicitly(evidence$4)).zero();
      return new UFunc.InPlaceImpl2(zero) {
         private final Object zero$2;

         public void apply$mcD$sp(final Object v, final double v2) {
            UFunc.InPlaceImpl2.apply$mcD$sp$(this, v, v2);
         }

         public void apply$mcF$sp(final Object v, final float v2) {
            UFunc.InPlaceImpl2.apply$mcF$sp$(this, v, v2);
         }

         public void apply$mcI$sp(final Object v, final int v2) {
            UFunc.InPlaceImpl2.apply$mcI$sp$(this, v, v2);
         }

         public void apply(final SparseVector a, final Object b) {
            ClassTag ct = ReflectionUtil$.MODULE$.elemClassTagFromArray(a.data());
            if (BoxesRunTime.equals(b, this.zero$2)) {
               a.use(new int[2], ct.newArray(2), 0);
            } else {
               Object data = scala.Array..MODULE$.fill(a.length(), () -> b, ct);
               int[] index = scala.Array..MODULE$.range(0, a.length());
               a.use(index, data, a.length());
            }

         }

         public {
            this.zero$2 = zero$2;
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return var0.lambdaDeserialize<invokedynamic>(var0);
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Generic$(final SparseVector_GenericOps $this, final Semiring evidence$5, final UFunc.UImpl2 op) {
      return $this.impl_Op_SV_S_eq_SV_Generic(evidence$5, op);
   }

   default UFunc.UImpl2 impl_Op_SV_S_eq_SV_Generic(final Semiring evidence$5, final UFunc.UImpl2 op) {
      return (a, b) -> {
         ClassTag ct = ReflectionUtil$.MODULE$.elemClassTagFromArray(a.data());
         VectorBuilder result = new VectorBuilder(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), evidence$5, ct);
         Semiring f = (Semiring).MODULE$.implicitly(evidence$5);
         int index$macro$2 = 0;

         for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
            Object r = op.apply(a.apply(index$macro$2), b);
            if (!BoxesRunTime.equals(r, f.zero())) {
               result.add(index$macro$2, r);
            }
         }

         return result.toSparseVector(true, true);
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpAdd_SV_S_eq_SV_Generic$(final SparseVector_GenericOps $this, final Semiring evidence$6) {
      return $this.impl_OpAdd_SV_S_eq_SV_Generic(evidence$6);
   }

   default UFunc.UImpl2 impl_OpAdd_SV_S_eq_SV_Generic(final Semiring evidence$6) {
      return (a, b) -> {
         Semiring f = (Semiring).MODULE$.implicitly(evidence$6);
         SparseVector var10000;
         if (BoxesRunTime.equals(b, f.zero())) {
            var10000 = a.copy();
         } else {
            ClassTag ct = ReflectionUtil$.MODULE$.elemClassTagFromArray(a.data());
            VectorBuilder result = new VectorBuilder(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), evidence$6, ct);
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               Object r = f.$plus(a.apply(index$macro$2), b);
               if (!BoxesRunTime.equals(r, f.zero())) {
                  result.add(index$macro$2, r);
               }
            }

            var10000 = result.toSparseVector(true, true);
         }

         return var10000;
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpSub_SV_S_eq_SV_Generic$(final SparseVector_GenericOps $this, final Ring evidence$7) {
      return $this.impl_OpSub_SV_S_eq_SV_Generic(evidence$7);
   }

   default UFunc.UImpl2 impl_OpSub_SV_S_eq_SV_Generic(final Ring evidence$7) {
      return (a, b) -> {
         Ring f = (Ring).MODULE$.implicitly(evidence$7);
         SparseVector var10000;
         if (BoxesRunTime.equals(b, f.zero())) {
            var10000 = a.copy();
         } else {
            ClassTag ct = ReflectionUtil$.MODULE$.elemClassTagFromArray(a.data());
            VectorBuilder result = new VectorBuilder(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), evidence$7, ct);
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.length(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               Object r = f.$minus(a.apply(index$macro$2), b);
               if (!BoxesRunTime.equals(r, f.zero())) {
                  result.add(index$macro$2, r);
               }
            }

            var10000 = result.toSparseVector(true, true);
         }

         return var10000;
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_OpMulScalar_SV_S_eq_SV_Generic$(final SparseVector_GenericOps $this, final Semiring evidence$8, final ClassTag evidence$9) {
      return $this.impl_OpMulScalar_SV_S_eq_SV_Generic(evidence$8, evidence$9);
   }

   default UFunc.UImpl2 impl_OpMulScalar_SV_S_eq_SV_Generic(final Semiring evidence$8, final ClassTag evidence$9) {
      return (a, b) -> {
         Semiring f = (Semiring).MODULE$.implicitly(evidence$8);
         VectorBuilder result = new VectorBuilder(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), evidence$8, evidence$9);
         if (!BoxesRunTime.equals(b, f.zero())) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.activeSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               result.add(a.indexAt(index$macro$2), f.$times(a.valueAt(index$macro$2), b));
            }
         }

         return result.toSparseVector(true, true);
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_SV_S_eq_SV_Generic_OpMulMatrix$(final SparseVector_GenericOps $this, final Semiring evidence$10, final ClassTag evidence$11) {
      return $this.impl_Op_SV_S_eq_SV_Generic_OpMulMatrix(evidence$10, evidence$11);
   }

   default UFunc.UImpl2 impl_Op_SV_S_eq_SV_Generic_OpMulMatrix(final Semiring evidence$10, final ClassTag evidence$11) {
      return (a, b) -> {
         Semiring f = (Semiring).MODULE$.implicitly(evidence$10);
         VectorBuilder result = new VectorBuilder(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), evidence$10, evidence$11);
         if (!BoxesRunTime.equals(b, f.zero())) {
            int index$macro$2 = 0;

            for(int limit$macro$4 = a.activeSize(); index$macro$2 < limit$macro$4; ++index$macro$2) {
               result.add(a.indexAt(index$macro$2), f.$times(a.valueAt(index$macro$2), b));
            }
         }

         return result.toSparseVector(true, true);
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Generic_OpMulScalar$(final SparseVector_GenericOps $this, final Semiring ring) {
      return $this.impl_Op_SV_SV_eq_SV_Generic_OpMulScalar(ring);
   }

   default UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Generic_OpMulScalar(final Semiring ring) {
      return new UFunc.UImpl2(ring) {
         private final Semiring ring$1;

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

         public SparseVector apply(final SparseVector a, final SparseVector b) {
            while(true) {
               ClassTag ct = ReflectionUtil$.MODULE$.elemClassTagFromArray(a.data());
               if (b.activeSize() >= a.activeSize()) {
                  int left$macro$1 = b.length();
                  int right$macro$2 = a.length();
                  if (left$macro$1 != right$macro$2) {
                     throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
                  }

                  int asize = a.activeSize();
                  int bsize = b.activeSize();
                  int[] resultI = new int[scala.math.package..MODULE$.min(asize, bsize)];
                  Object resultV = ct.newArray(scala.math.package..MODULE$.min(asize, bsize));
                  int resultOff = 0;
                  int aoff = 0;
                  int boff = 0;

                  while(aoff < asize) {
                     int aind = a.indexAt(aoff);
                     boff = Arrays.binarySearch(b.index(), boff, scala.math.package..MODULE$.min(bsize, aind + 1), aind);
                     if (boff < 0) {
                        boff = ~boff;
                        if (boff == bsize) {
                           aoff = asize;
                        } else {
                           int bind = b.indexAt(boff);
                           int newAoff = Arrays.binarySearch(a.index(), aoff, scala.math.package..MODULE$.min(asize, bind + 1), bind);
                           if (newAoff < 0) {
                              newAoff = ~newAoff;
                              ++boff;
                           }

                           boolean cond$macro$3 = newAoff > aoff;
                           if (!cond$macro$3) {
                              throw new AssertionError((new StringBuilder(35)).append("assertion failed: ").append((new StringBuilder(6)).append(bind).append(" ").append(aoff).append(" ").append(newAoff).append(" ").append(a.index()[aoff]).append(" ").append(a.index()[newAoff]).append(" ").append(a).append(" ").append(b).toString()).append(": ").append("newAoff.>(aoff)").toString());
                           }

                           aoff = newAoff;
                        }
                     } else {
                        resultI[resultOff] = aind;
                        scala.runtime.ScalaRunTime..MODULE$.array_update(resultV, resultOff, this.ring$1.$times(a.valueAt(aoff), b.valueAt(boff)));
                        ++aoff;
                        ++boff;
                        ++resultOff;
                     }
                  }

                  return resultOff != resultI.length ? new SparseVector(Arrays.copyOf(resultI, resultOff), ArrayUtil$.MODULE$.copyOf(resultV, resultOff), resultOff, a.length(), Zero$.MODULE$.zeroFromSemiring(this.ring$1)) : new SparseVector(resultI, resultV, resultOff, a.length(), Zero$.MODULE$.zeroFromSemiring(this.ring$1));
               }

               SparseVector var10000 = b;
               b = a;
               a = var10000;
            }
         }

         public {
            this.ring$1 = ring$1;
         }
      };
   }

   // $FF: synthetic method
   static UFunc.InPlaceImpl3 impl_scaleAdd_SV_S_SV_InPlace_Generic$(final SparseVector_GenericOps $this, final Semiring evidence$12, final ClassTag evidence$13) {
      return $this.impl_scaleAdd_SV_S_SV_InPlace_Generic(evidence$12, evidence$13);
   }

   default UFunc.InPlaceImpl3 impl_scaleAdd_SV_S_SV_InPlace_Generic(final Semiring evidence$12, final ClassTag evidence$13) {
      return (dest, scale, source) -> {
         Semiring f = (Semiring).MODULE$.implicitly(evidence$12);
         int left$macro$1 = source.length();
         int right$macro$2 = dest.length();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(89)).append("requirement failed: Vectors must be the same length!: ").append("source.length == dest.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            int asize = dest.activeSize();
            int bsize = source.activeSize();
            if (!BoxesRunTime.equals(scale, f.zero()) && bsize != 0) {
               int[] resultI = new int[asize + bsize];
               Object resultV = evidence$13.newArray(asize + bsize);
               int resultOff = 0;
               int aoff = 0;

               int boff;
               for(boff = 0; aoff < asize; ++aoff) {
                  while(boff < bsize && source.indexAt(boff) < dest.indexAt(aoff)) {
                     resultI[resultOff] = source.indexAt(boff);
                     scala.runtime.ScalaRunTime..MODULE$.array_update(resultV, resultOff, f.$times(scale, source.valueAt(boff)));
                     ++resultOff;
                     ++boff;
                  }

                  Object var10000;
                  if (boff < bsize && source.indexAt(boff) == dest.indexAt(aoff)) {
                     Object bv = f.$times(scale, source.valueAt(boff));
                     ++boff;
                     var10000 = bv;
                  } else {
                     var10000 = f.zero();
                  }

                  Object bvalue = var10000;
                  resultI[resultOff] = dest.indexAt(aoff);
                  scala.runtime.ScalaRunTime..MODULE$.array_update(resultV, resultOff, f.$plus(dest.valueAt(aoff), bvalue));
                  ++resultOff;
               }

               while(boff < bsize) {
                  resultI[resultOff] = source.indexAt(boff);
                  scala.runtime.ScalaRunTime..MODULE$.array_update(resultV, resultOff, f.$times(scale, source.valueAt(boff)));
                  ++resultOff;
                  ++boff;
               }

               if (resultOff != resultI.length) {
                  dest.use(Arrays.copyOf(resultI, resultOff), ArrayUtil$.MODULE$.copyOf(resultV, resultOff), resultOff);
               } else {
                  dest.use(resultI, resultV, resultOff);
               }
            }

         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Generic$(final SparseVector_GenericOps $this, final UFunc.UImpl2 op, final Semiring semiring) {
      return $this.impl_Op_SV_SV_eq_SV_Generic(op, semiring);
   }

   default UFunc.UImpl2 impl_Op_SV_SV_eq_SV_Generic(final UFunc.UImpl2 op, final Semiring semiring) {
      return (a, b) -> {
         int left$macro$1 = b.length();
         int right$macro$2 = a.length();
         if (left$macro$1 != right$macro$2) {
            throw new IllegalArgumentException((new StringBuilder(81)).append("requirement failed: Vectors must be the same length!: ").append("b.length == a.length (").append(left$macro$1).append(" ").append("!=").append(" ").append(right$macro$2).append(")").toString());
         } else {
            ClassTag ct = ReflectionUtil$.MODULE$.elemClassTagFromArray(a.data());
            Object zero = ((Semiring).MODULE$.implicitly(semiring)).zero();
            VectorBuilder result = new VectorBuilder(a.length(), VectorBuilder$.MODULE$.$lessinit$greater$default$2(), semiring, ct);

            for(int i = 0; i < a.length(); ++i) {
               Object r = op.apply(a.apply(i), b.apply(i));
               if (!BoxesRunTime.equals(r, zero)) {
                  result.add(i, r);
               }
            }

            return result.toSparseVector(true, true);
         }
      };
   }

   static void $init$(final SparseVector_GenericOps $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
