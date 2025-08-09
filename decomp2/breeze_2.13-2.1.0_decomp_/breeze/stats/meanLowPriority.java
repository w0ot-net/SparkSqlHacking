package breeze.stats;

import breeze.generic.UFunc;
import breeze.linalg.scaleAdd$;
import breeze.linalg.support.CanCreateZerosLike;
import breeze.linalg.support.CanTraverseValues;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.LazyRef;

@ScalaSignature(
   bytes = "\u0006\u0005\u00014\u0001b\u0001\u0003\u0011\u0002\u0007\u0005\u0012B\u0017\u0005\u0006!\u0001!\t!\u0005\u0005\u0006+\u0001!\u0019A\u0006\u0002\u0010[\u0016\fg\u000eT8x!JLwN]5us*\u0011QAB\u0001\u0006gR\fGo\u001d\u0006\u0002\u000f\u00051!M]3fu\u0016\u001c\u0001a\u0005\u0002\u0001\u0015A\u00111BD\u0007\u0002\u0019)\tQ\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0010\u0019\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001\n\u0011\u0005-\u0019\u0012B\u0001\u000b\r\u0005\u0011)f.\u001b;\u0002\u001d\r\fg.T3b]\u001e+g.\u001a:jGV\u0019qC\t\u0017\u0015\raq\u0003(\u0010%U!\u0011I\"\u0004I\u0016\u000e\u0003\u0001I!a\u0007\u000f\u0003\t%k\u0007\u000f\\\u0005\u0003;y\u0011Q!\u0016$v]\u000eT!a\b\u0004\u0002\u000f\u001d,g.\u001a:jGB\u0011\u0011E\t\u0007\u0001\t\u0015\u0019#A1\u0001%\u0005\u0005!\u0016CA\u0013)!\tYa%\u0003\u0002(\u0019\t9aj\u001c;iS:<\u0007CA\u0006*\u0013\tQCBA\u0002B]f\u0004\"!\t\u0017\u0005\u000b5\u0012!\u0019\u0001\u0013\u0003\u0003MCQa\f\u0002A\u0004A\nA!\u001b;feB!\u0011G\u000e\u0011,\u001b\u0005\u0011$BA\u001a5\u0003\u001d\u0019X\u000f\u001d9peRT!!\u000e\u0004\u0002\r1Lg.\u00197h\u0013\t9$GA\tDC:$&/\u0019<feN,g+\u00197vKNDQ!\u000f\u0002A\u0004i\n\u0011B_3s_Nd\u0015n[3\u0011\tEZ4fK\u0005\u0003yI\u0012!cQ1o\u0007J,\u0017\r^3[KJ|7\u000fT5lK\")aH\u0001a\u0002\u007f\u000591/\u001a;J]R|\u0007\u0003\u0002!GW-r!!\u0011#\u000e\u0003\tS!a\u0011\u001b\u0002\u0013=\u0004XM]1u_J\u001c\u0018BA#C\u0003\u0015y\u0005oU3u\u0013\t9ED\u0001\u0007J]Bc\u0017mY3J[Bd'\u0007C\u0003J\u0005\u0001\u000f!*\u0001\u0003bqBL\b#B&PWE[cB\u0001'N\u001b\u0005!\u0014B\u0001(5\u0003!\u00198-\u00197f\u0003\u0012$\u0017B\u0001)\u001d\u00051Ie\u000e\u00157bG\u0016LU\u000e\u001d74!\tY!+\u0003\u0002T\u0019\t1Ai\\;cY\u0016DQ!\u0016\u0002A\u0004Y\u000bAbY1o\u001bVd\u0017J\u001c;p-N\u0003Ba\u0016$,#:\u0011\u0011\tW\u0005\u00033\n\u000bQa\u00149ESZt!a\u0017/\u000e\u0003\u0011I!!\u0018\u0003\u0002\t5,\u0017M\\\u0015\u0003\u0001}S!!\u0018\u0003"
)
public interface meanLowPriority {
   // $FF: synthetic method
   static UFunc.UImpl canMeanGeneric$(final meanLowPriority $this, final CanTraverseValues iter, final CanCreateZerosLike zerosLike, final UFunc.InPlaceImpl2 setInto, final UFunc.InPlaceImpl3 axpy, final UFunc.InPlaceImpl2 canMulIntoVS) {
      return $this.canMeanGeneric(iter, zerosLike, setInto, axpy, canMulIntoVS);
   }

   default UFunc.UImpl canMeanGeneric(final CanTraverseValues iter, final CanCreateZerosLike zerosLike, final UFunc.InPlaceImpl2 setInto, final UFunc.InPlaceImpl3 axpy, final UFunc.InPlaceImpl2 canMulIntoVS) {
      return new UFunc.UImpl(setInto, axpy, zerosLike, canMulIntoVS, iter) {
         public final UFunc.InPlaceImpl2 setInto$1;
         public final UFunc.InPlaceImpl3 axpy$1;
         public final CanCreateZerosLike zerosLike$1;
         public final UFunc.InPlaceImpl2 canMulIntoVS$1;
         private final CanTraverseValues iter$4;

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
            LazyRef visit$module = new LazyRef();
            this.iter$4.traverse(v, this.visit$8(visit$module));
            if (this.visit$8(visit$module).count() == 0L) {
               throw new IllegalArgumentException("empty collection");
            } else {
               return this.visit$8(visit$module).runningMean();
            }
         }

         // $FF: synthetic method
         private final visit$7$ visit$lzycompute$4(final LazyRef visit$module$4) {
            synchronized(visit$module$4){}

            visit$7$ var3;
            try {
               class visit$7$ implements CanTraverseValues.ValuesVisitor {
                  private Object runningMean;
                  private Object scratch;
                  private long count;
                  // $FF: synthetic field
                  private final <undefinedtype> $outer;

                  public void visit$mcZ$sp(final boolean a) {
                     CanTraverseValues.ValuesVisitor.visit$mcZ$sp$(this, a);
                  }

                  public void visit$mcB$sp(final byte a) {
                     CanTraverseValues.ValuesVisitor.visit$mcB$sp$(this, a);
                  }

                  public void visit$mcC$sp(final char a) {
                     CanTraverseValues.ValuesVisitor.visit$mcC$sp$(this, a);
                  }

                  public void visit$mcD$sp(final double a) {
                     CanTraverseValues.ValuesVisitor.visit$mcD$sp$(this, a);
                  }

                  public void visit$mcF$sp(final float a) {
                     CanTraverseValues.ValuesVisitor.visit$mcF$sp$(this, a);
                  }

                  public void visit$mcI$sp(final int a) {
                     CanTraverseValues.ValuesVisitor.visit$mcI$sp$(this, a);
                  }

                  public void visit$mcJ$sp(final long a) {
                     CanTraverseValues.ValuesVisitor.visit$mcJ$sp$(this, a);
                  }

                  public void visit$mcS$sp(final short a) {
                     CanTraverseValues.ValuesVisitor.visit$mcS$sp$(this, a);
                  }

                  public void visit$mcV$sp(final BoxedUnit a) {
                     CanTraverseValues.ValuesVisitor.visit$mcV$sp$(this, a);
                  }

                  public void visitArray(final Object arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$(this, arr);
                  }

                  public void visitArray$mcZ$sp(final boolean[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr);
                  }

                  public void visitArray$mcB$sp(final byte[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr);
                  }

                  public void visitArray$mcC$sp(final char[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr);
                  }

                  public void visitArray$mcD$sp(final double[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr);
                  }

                  public void visitArray$mcF$sp(final float[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr);
                  }

                  public void visitArray$mcI$sp(final int[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr);
                  }

                  public void visitArray$mcJ$sp(final long[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr);
                  }

                  public void visitArray$mcS$sp(final short[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr);
                  }

                  public void visitArray$mcV$sp(final BoxedUnit[] arr) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr);
                  }

                  public void visitArray(final Object arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcZ$sp(final boolean[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcZ$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcB$sp(final byte[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcB$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcC$sp(final char[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcC$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcD$sp(final double[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcD$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcF$sp(final float[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcF$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcI$sp(final int[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcI$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcJ$sp(final long[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcJ$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcS$sp(final short[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcS$sp$(this, arr, offset, length, stride);
                  }

                  public void visitArray$mcV$sp(final BoxedUnit[] arr, final int offset, final int length, final int stride) {
                     CanTraverseValues.ValuesVisitor.visitArray$mcV$sp$(this, arr, offset, length, stride);
                  }

                  public void zeros$mcZ$sp(final int numZero, final boolean zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcZ$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcB$sp(final int numZero, final byte zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcB$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcC$sp(final int numZero, final char zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcC$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcD$sp(final int numZero, final double zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcD$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcF$sp(final int numZero, final float zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcF$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcI$sp(final int numZero, final int zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcI$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcJ$sp(final int numZero, final long zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcJ$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcS$sp(final int numZero, final short zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcS$sp$(this, numZero, zeroValue);
                  }

                  public void zeros$mcV$sp(final int numZero, final BoxedUnit zeroValue) {
                     CanTraverseValues.ValuesVisitor.zeros$mcV$sp$(this, numZero, zeroValue);
                  }

                  public Object runningMean() {
                     return this.runningMean;
                  }

                  public void runningMean_$eq(final Object x$1) {
                     this.runningMean = x$1;
                  }

                  public Object scratch() {
                     return this.scratch;
                  }

                  public void scratch_$eq(final Object x$1) {
                     this.scratch = x$1;
                  }

                  public long count() {
                     return this.count;
                  }

                  public void count_$eq(final long x$1) {
                     this.count = x$1;
                  }

                  public void visit(final Object y) {
                     if (this.count() == 0L) {
                        this.init(y);
                        this.count_$eq(this.count() + 1L);
                     } else {
                        this.count_$eq(this.count() + 1L);
                        this.$outer.setInto$1.apply(this.scratch(), y);
                        scaleAdd$.MODULE$.inPlace(this.scratch(), BoxesRunTime.boxToDouble((double)-1.0F), this.runningMean(), this.$outer.axpy$1);
                        scaleAdd$.MODULE$.inPlace(this.runningMean(), BoxesRunTime.boxToDouble((double)1.0F / (double)this.count()), this.scratch(), this.$outer.axpy$1);
                     }

                  }

                  private void init(final Object y) {
                     this.runningMean_$eq(this.$outer.zerosLike$1.apply(y));
                     this.$outer.setInto$1.apply(this.runningMean(), y);
                     this.scratch_$eq(this.$outer.zerosLike$1.apply(y));
                  }

                  public void zeros(final int numZero, final Object zeroValue) {
                     if (this.count() == 0L) {
                        this.init(zeroValue);
                     } else if (numZero != 0) {
                        this.$outer.canMulIntoVS$1.apply$mcD$sp(this.runningMean(), (double)(this.count() / (this.count() + (long)numZero)));
                     }

                     this.count_$eq(this.count() + (long)numZero);
                  }

                  public visit$7$() {
                     if (<VAR_NAMELESS_ENCLOSURE> == null) {
                        throw null;
                     } else {
                        this.$outer = <VAR_NAMELESS_ENCLOSURE>;
                        super();
                        CanTraverseValues.ValuesVisitor.$init$(this);
                        this.count = 0L;
                     }
                  }
               }

               var3 = visit$module$4.initialized() ? (visit$7$)visit$module$4.value() : (visit$7$)visit$module$4.initialize(new visit$7$());
            } catch (Throwable var5) {
               throw var5;
            }

            return var3;
         }

         private final visit$7$ visit$8(final LazyRef visit$module$4) {
            return visit$module$4.initialized() ? (visit$7$)visit$module$4.value() : this.visit$lzycompute$4(visit$module$4);
         }

         public {
            this.setInto$1 = setInto$1;
            this.axpy$1 = axpy$1;
            this.zerosLike$1 = zerosLike$1;
            this.canMulIntoVS$1 = canMulIntoVS$1;
            this.iter$4 = iter$4;
         }
      };
   }

   static void $init$(final meanLowPriority $this) {
   }
}
