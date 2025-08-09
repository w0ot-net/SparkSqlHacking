package breeze.linalg;

import breeze.generic.UFunc;
import breeze.stats.distributions.Rand;
import breeze.stats.distributions.RandBasis;
import breeze.storage.Zero;
import scala.Tuple2;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u4qa\u0003\u0007\u0011\u0002\u0007\u0005\u0011\u0003C\u0003 \u0001\u0011\u0005\u0001\u0005C\u0003%\u0001\u0019EQ\u0005C\u0003?\u0001\u0019Eq\bC\u0004G\u0001\t\u0007i1C$\t\u000f9\u0003!\u0019!D\n\u001f\")a\u000b\u0001C\u0001/\")!\f\u0001C\u00027\")\u0001\u000e\u0001C\u0002S\")\u0011\u000f\u0001C\u0002e\")\u0011\u0010\u0001C\u0002u\n!\"+\u00198e_6<UM\\3sCR|'/\u0016$v]\u000eT!!\u0004\b\u0002\r1Lg.\u00197h\u0015\u0005y\u0011A\u00022sK\u0016TXm\u0001\u0001\u0016\u0005I\u00014c\u0001\u0001\u00143A\u0011AcF\u0007\u0002+)\ta#A\u0003tG\u0006d\u0017-\u0003\u0002\u0019+\t1\u0011I\\=SK\u001a\u0004\"AG\u000f\u000e\u0003mQ!\u0001\b\b\u0002\u000f\u001d,g.\u001a:jG&\u0011ad\u0007\u0002\u0006+\u001a+hnY\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003\u0005\u0002\"\u0001\u0006\u0012\n\u0005\r*\"\u0001B+oSR\f1aZ3o)\t1\u0013\bE\u0002(Y9j\u0011\u0001\u000b\u0006\u0003S)\nQ\u0002Z5tiJL'-\u001e;j_:\u001c(BA\u0016\u000f\u0003\u0015\u0019H/\u0019;t\u0013\ti\u0003F\u0001\u0003SC:$\u0007CA\u00181\u0019\u0001!Q!\r\u0001C\u0002I\u0012\u0011\u0001V\t\u0003gY\u0002\"\u0001\u0006\u001b\n\u0005U*\"a\u0002(pi\"Lgn\u001a\t\u0003)]J!\u0001O\u000b\u0003\u0007\u0005s\u0017\u0010C\u0003;\u0005\u0001\u000f1(A\u0003cCNL7\u000f\u0005\u0002(y%\u0011Q\b\u000b\u0002\n%\u0006tGMQ1tSN\f\u0001bZ3o%\u0006tw-\u001a\u000b\u0004\u0001\n#EC\u0001\u0014B\u0011\u0015Q4\u0001q\u0001<\u0011\u0015\u00195\u00011\u0001/\u0003\rawn\u001e\u0005\u0006\u000b\u000e\u0001\rAL\u0001\u0005Q&<\u0007.A\u0005`G2\f7o\u001d+bOV\t\u0001\nE\u0002J\u0019:j\u0011A\u0013\u0006\u0003\u0017V\tqA]3gY\u0016\u001cG/\u0003\u0002N\u0015\nA1\t\\1tgR\u000bw-A\u0003`u\u0016\u0014x.F\u0001Q!\r\tFKL\u0007\u0002%*\u00111KD\u0001\bgR|'/Y4f\u0013\t)&K\u0001\u0003[KJ|\u0017!B1qa2LH#\u0001-\u0015\u00059J\u0006\"\u0002\u001e\u0007\u0001\bY\u0014AD5na2\u0014\u0016M\u001c3p[R{\u0016\u0007\u0012\u000b\u00039\u001e\u0004B!\u00180aG6\t\u0001!\u0003\u0002`;\t!\u0011*\u001c9m!\t!\u0012-\u0003\u0002c+\t\u0019\u0011J\u001c;\u0011\u0007\u0011,g&D\u0001\r\u0013\t1GBA\u0006EK:\u001cXMV3di>\u0014\b\"\u0002\u001e\b\u0001\bY\u0014aE5na2\u0014\u0016M\u001c3p[R{\u0016\u0007\u0012*b]\u001e,GC\u00016q!\u0015i6\u000eY7d\u0013\taWDA\u0003J[Bd'\u0007\u0005\u0003\u0015]:r\u0013BA8\u0016\u0005\u0019!V\u000f\u001d7fe!)!\b\u0003a\u0002w\u0005q\u0011.\u001c9m%\u0006tGm\\7U?J\"ECA:y!\u0011if\f^;\u0011\tQq\u0007\r\u0019\t\u0004IZt\u0013BA<\r\u0005-!UM\\:f\u001b\u0006$(/\u001b=\t\u000biJ\u00019A\u001e\u0002'%l\u0007\u000f\u001c*b]\u0012|W\u000eV03\tJ\u000bgnZ3\u0015\u0005md\b#B/li6,\b\"\u0002\u001e\u000b\u0001\bY\u0004"
)
public interface RandomGeneratorUFunc extends UFunc {
   Rand gen(final RandBasis basis);

   Rand genRange(final Object low, final Object high, final RandBasis basis);

   ClassTag _classTag();

   Zero _zero();

   // $FF: synthetic method
   static Object apply$(final RandomGeneratorUFunc $this, final RandBasis basis) {
      return $this.apply(basis);
   }

   default Object apply(final RandBasis basis) {
      return this.gen(basis).draw();
   }

   // $FF: synthetic method
   static UFunc.UImpl implRandomT_1D$(final RandomGeneratorUFunc $this, final RandBasis basis) {
      return $this.implRandomT_1D(basis);
   }

   default UFunc.UImpl implRandomT_1D(final RandBasis basis) {
      return new UFunc.UImpl(basis) {
         // $FF: synthetic field
         private final RandomGeneratorUFunc $outer;
         private final RandBasis basis$1;

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

         public DenseVector apply(final int dimensions1) {
            return (DenseVector)DenseVector$.MODULE$.rand(dimensions1, this.$outer.gen(this.basis$1), this.$outer._classTag());
         }

         public {
            if (RandomGeneratorUFunc.this == null) {
               throw null;
            } else {
               this.$outer = RandomGeneratorUFunc.this;
               this.basis$1 = basis$1;
            }
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 implRandomT_1DRange$(final RandomGeneratorUFunc $this, final RandBasis basis) {
      return $this.implRandomT_1DRange(basis);
   }

   default UFunc.UImpl2 implRandomT_1DRange(final RandBasis basis) {
      return new UFunc.UImpl2(basis) {
         // $FF: synthetic field
         private final RandomGeneratorUFunc $outer;
         private final RandBasis basis$2;

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

         public DenseVector apply(final int dimensions1, final Tuple2 range) {
            return (DenseVector)DenseVector$.MODULE$.rand(dimensions1, this.$outer.genRange(range._1(), range._2(), this.basis$2), this.$outer._classTag());
         }

         public {
            if (RandomGeneratorUFunc.this == null) {
               throw null;
            } else {
               this.$outer = RandomGeneratorUFunc.this;
               this.basis$2 = basis$2;
            }
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl implRandomT_2D$(final RandomGeneratorUFunc $this, final RandBasis basis) {
      return $this.implRandomT_2D(basis);
   }

   default UFunc.UImpl implRandomT_2D(final RandBasis basis) {
      return new UFunc.UImpl(basis) {
         // $FF: synthetic field
         private final RandomGeneratorUFunc $outer;
         private final RandBasis basis$3;

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

         public DenseMatrix apply(final Tuple2 dimensions2) {
            return (DenseMatrix)DenseMatrix$.MODULE$.rand(dimensions2._1$mcI$sp(), dimensions2._2$mcI$sp(), this.$outer.gen(this.basis$3), this.$outer._classTag(), this.$outer._zero());
         }

         public {
            if (RandomGeneratorUFunc.this == null) {
               throw null;
            } else {
               this.$outer = RandomGeneratorUFunc.this;
               this.basis$3 = basis$3;
            }
         }
      };
   }

   // $FF: synthetic method
   static UFunc.UImpl2 implRandomT_2DRange$(final RandomGeneratorUFunc $this, final RandBasis basis) {
      return $this.implRandomT_2DRange(basis);
   }

   default UFunc.UImpl2 implRandomT_2DRange(final RandBasis basis) {
      return new UFunc.UImpl2(basis) {
         // $FF: synthetic field
         private final RandomGeneratorUFunc $outer;
         private final RandBasis basis$4;

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

         public DenseMatrix apply(final Tuple2 dimensions2, final Tuple2 range) {
            return (DenseMatrix)DenseMatrix$.MODULE$.rand(dimensions2._1$mcI$sp(), dimensions2._2$mcI$sp(), this.$outer.genRange(range._1(), range._2(), this.basis$4), this.$outer._classTag(), this.$outer._zero());
         }

         public {
            if (RandomGeneratorUFunc.this == null) {
               throw null;
            } else {
               this.$outer = RandomGeneratorUFunc.this;
               this.basis$4 = basis$4;
            }
         }
      };
   }

   static void $init$(final RandomGeneratorUFunc $this) {
   }
}
