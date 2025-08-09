package breeze.math;

import breeze.generic.UFunc;
import breeze.numerics.package$sqrt$sqrtDoubleImpl$;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003&\u0001\u0011\u0005a\u0005C\u0003+\u0001\u0019\u00051\u0006C\u00041\u0001\t\u0007I1A\u0019\t\u000b\u0005\u0003A1\u0001\"\u0003%5\u000bGO]5y\u0013:tWM\u001d)s_\u0012,8\r\u001e\u0006\u0003\u000f!\tA!\\1uQ*\t\u0011\"\u0001\u0004ce\u0016,'0Z\u0002\u0001+\ra\u0011dI\n\u0004\u00015\u0019\u0002C\u0001\b\u0012\u001b\u0005y!\"\u0001\t\u0002\u000bM\u001c\u0017\r\\1\n\u0005Iy!AB!osJ+g\r\u0005\u0003\u0015+]\u0011S\"\u0001\u0004\n\u0005Y1!aC'biJL\u0007PT8s[N\u0004\"\u0001G\r\r\u0001\u0011)!\u0004\u0001b\u00017\t\tQ*\u0005\u0002\u001d?A\u0011a\"H\u0005\u0003==\u0011qAT8uQ&tw\r\u0005\u0002\u000fA%\u0011\u0011e\u0004\u0002\u0004\u0003:L\bC\u0001\r$\t\u0015!\u0003A1\u0001\u001c\u0005\u0005\u0019\u0016A\u0002\u0013j]&$H\u0005F\u0001(!\tq\u0001&\u0003\u0002*\u001f\t!QK\\5u\u00031IgN\\3s!J|G-^2u)\r\u0011CF\f\u0005\u0006[\t\u0001\raF\u0001\u0003[FBQa\f\u0002A\u0002]\t!!\u001c\u001a\u0002\u001f\r\fg.\u00138oKJ\u0004&o\u001c3vGR,\u0012A\r\t\u0006gm:rC\t\b\u0003iej\u0011!\u000e\u0006\u0003m]\n\u0011b\u001c9fe\u0006$xN]:\u000b\u0005aB\u0011A\u00027j]\u0006dw-\u0003\u0002;k\u0005Qq\n]'vY&sg.\u001a:\n\u0005qj$!B%na2\u0014\u0014B\u0001 @\u0005\u0015)f)\u001e8d\u0015\t\u0001\u0005\"A\u0004hK:,'/[2\u00021\r\fg.\u00138oKJ\u0004&o\u001c3vGRtuN]7`%&tw\r\u0006\u0002D\u001bB!A\tS\fK\u001d\t)e)D\u00018\u0013\t9u'\u0001\u0003o_Jl\u0017BA%>\u0005\u0011IU\u000e\u001d7\u0011\u00059Y\u0015B\u0001'\u0010\u0005\u0019!u.\u001e2mK\")a\n\u0002a\u0002\u001f\u0006!!/\u001b8h!\r!\u0002KI\u0005\u0003#\u001a\u0011AAU5oO\u0002"
)
public interface MatrixInnerProduct extends MatrixNorms {
   void breeze$math$MatrixInnerProduct$_setter_$canInnerProduct_$eq(final UFunc.UImpl2 x$1);

   Object innerProduct(final Object m1, final Object m2);

   UFunc.UImpl2 canInnerProduct();

   // $FF: synthetic method
   static UFunc.UImpl canInnerProductNorm_Ring$(final MatrixInnerProduct $this, final Ring ring) {
      return $this.canInnerProductNorm_Ring(ring);
   }

   default UFunc.UImpl canInnerProductNorm_Ring(final Ring ring) {
      return new UFunc.UImpl(ring) {
         // $FF: synthetic field
         private final MatrixInnerProduct $outer;
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

         public double apply(final Object v) {
            return breeze.numerics.package.sqrt$.MODULE$.apply$mDDc$sp(((Ring).MODULE$.implicitly(this.ring$1)).sNorm(this.$outer.canInnerProduct().apply(v, v)), package$sqrt$sqrtDoubleImpl$.MODULE$);
         }

         public {
            if (MatrixInnerProduct.this == null) {
               throw null;
            } else {
               this.$outer = MatrixInnerProduct.this;
               this.ring$1 = ring$1;
            }
         }
      };
   }

   static void $init$(final MatrixInnerProduct $this) {
      $this.breeze$math$MatrixInnerProduct$_setter_$canInnerProduct_$eq(new UFunc.UImpl2() {
         // $FF: synthetic field
         private final MatrixInnerProduct $outer;

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

         public Object apply(final Object v, final Object v2) {
            return this.$outer.innerProduct(v, v2);
         }

         public {
            if (MatrixInnerProduct.this == null) {
               throw null;
            } else {
               this.$outer = MatrixInnerProduct.this;
            }
         }
      });
   }
}
