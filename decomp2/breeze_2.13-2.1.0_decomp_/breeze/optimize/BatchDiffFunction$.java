package breeze.optimize;

import breeze.generic.UFunc;
import breeze.linalg.ImmutableNumericOps;
import breeze.linalg.NumericOps;
import breeze.linalg.support.CanCopy;
import breeze.linalg.support.CanSlice;
import breeze.linalg.support.CanSlice2;
import breeze.linalg.support.CanTranspose;
import breeze.util.Isomorphism;
import scala.Function1;
import scala.Function2;
import scala.Tuple2;
import scala.collection.immutable.IndexedSeq;
import scala.package.;

public final class BatchDiffFunction$ {
   public static final BatchDiffFunction$ MODULE$ = new BatchDiffFunction$();

   public BatchDiffFunction wrap(final DiffFunction f) {
      return new BatchDiffFunction(f) {
         private final DiffFunction f$1;

         public Tuple2 calculate(final Object x) {
            return BatchDiffFunction.calculate$(this, x);
         }

         public double valueAt(final Object x) {
            return BatchDiffFunction.valueAt$(this, x);
         }

         public Object gradientAt(final Object x) {
            return BatchDiffFunction.gradientAt$(this, x);
         }

         public double apply(final Object x, final IndexedSeq batch) {
            return BatchDiffFunction.apply$(this, x, batch);
         }

         public DiffFunction cached(final CanCopy copy) {
            return BatchDiffFunction.cached$(this, copy);
         }

         public StochasticDiffFunction withRandomBatches(final int size) {
            return BatchDiffFunction.withRandomBatches$(this, size);
         }

         public StochasticDiffFunction withScanningBatches(final int size) {
            return BatchDiffFunction.withScanningBatches$(this, size);
         }

         public BatchDiffFunction groupItems(final int groupSize) {
            return BatchDiffFunction.groupItems$(this, groupSize);
         }

         public BatchDiffFunction throughLens(final Isomorphism l) {
            return BatchDiffFunction.throughLens$(this, l);
         }

         public boolean apply$mcZDD$sp(final double v1, final double v2) {
            return Function2.apply$mcZDD$sp$(this, v1, v2);
         }

         public double apply$mcDDD$sp(final double v1, final double v2) {
            return Function2.apply$mcDDD$sp$(this, v1, v2);
         }

         public float apply$mcFDD$sp(final double v1, final double v2) {
            return Function2.apply$mcFDD$sp$(this, v1, v2);
         }

         public int apply$mcIDD$sp(final double v1, final double v2) {
            return Function2.apply$mcIDD$sp$(this, v1, v2);
         }

         public long apply$mcJDD$sp(final double v1, final double v2) {
            return Function2.apply$mcJDD$sp$(this, v1, v2);
         }

         public void apply$mcVDD$sp(final double v1, final double v2) {
            Function2.apply$mcVDD$sp$(this, v1, v2);
         }

         public boolean apply$mcZDI$sp(final double v1, final int v2) {
            return Function2.apply$mcZDI$sp$(this, v1, v2);
         }

         public double apply$mcDDI$sp(final double v1, final int v2) {
            return Function2.apply$mcDDI$sp$(this, v1, v2);
         }

         public float apply$mcFDI$sp(final double v1, final int v2) {
            return Function2.apply$mcFDI$sp$(this, v1, v2);
         }

         public int apply$mcIDI$sp(final double v1, final int v2) {
            return Function2.apply$mcIDI$sp$(this, v1, v2);
         }

         public long apply$mcJDI$sp(final double v1, final int v2) {
            return Function2.apply$mcJDI$sp$(this, v1, v2);
         }

         public void apply$mcVDI$sp(final double v1, final int v2) {
            Function2.apply$mcVDI$sp$(this, v1, v2);
         }

         public boolean apply$mcZDJ$sp(final double v1, final long v2) {
            return Function2.apply$mcZDJ$sp$(this, v1, v2);
         }

         public double apply$mcDDJ$sp(final double v1, final long v2) {
            return Function2.apply$mcDDJ$sp$(this, v1, v2);
         }

         public float apply$mcFDJ$sp(final double v1, final long v2) {
            return Function2.apply$mcFDJ$sp$(this, v1, v2);
         }

         public int apply$mcIDJ$sp(final double v1, final long v2) {
            return Function2.apply$mcIDJ$sp$(this, v1, v2);
         }

         public long apply$mcJDJ$sp(final double v1, final long v2) {
            return Function2.apply$mcJDJ$sp$(this, v1, v2);
         }

         public void apply$mcVDJ$sp(final double v1, final long v2) {
            Function2.apply$mcVDJ$sp$(this, v1, v2);
         }

         public boolean apply$mcZID$sp(final int v1, final double v2) {
            return Function2.apply$mcZID$sp$(this, v1, v2);
         }

         public double apply$mcDID$sp(final int v1, final double v2) {
            return Function2.apply$mcDID$sp$(this, v1, v2);
         }

         public float apply$mcFID$sp(final int v1, final double v2) {
            return Function2.apply$mcFID$sp$(this, v1, v2);
         }

         public int apply$mcIID$sp(final int v1, final double v2) {
            return Function2.apply$mcIID$sp$(this, v1, v2);
         }

         public long apply$mcJID$sp(final int v1, final double v2) {
            return Function2.apply$mcJID$sp$(this, v1, v2);
         }

         public void apply$mcVID$sp(final int v1, final double v2) {
            Function2.apply$mcVID$sp$(this, v1, v2);
         }

         public boolean apply$mcZII$sp(final int v1, final int v2) {
            return Function2.apply$mcZII$sp$(this, v1, v2);
         }

         public double apply$mcDII$sp(final int v1, final int v2) {
            return Function2.apply$mcDII$sp$(this, v1, v2);
         }

         public float apply$mcFII$sp(final int v1, final int v2) {
            return Function2.apply$mcFII$sp$(this, v1, v2);
         }

         public int apply$mcIII$sp(final int v1, final int v2) {
            return Function2.apply$mcIII$sp$(this, v1, v2);
         }

         public long apply$mcJII$sp(final int v1, final int v2) {
            return Function2.apply$mcJII$sp$(this, v1, v2);
         }

         public void apply$mcVII$sp(final int v1, final int v2) {
            Function2.apply$mcVII$sp$(this, v1, v2);
         }

         public boolean apply$mcZIJ$sp(final int v1, final long v2) {
            return Function2.apply$mcZIJ$sp$(this, v1, v2);
         }

         public double apply$mcDIJ$sp(final int v1, final long v2) {
            return Function2.apply$mcDIJ$sp$(this, v1, v2);
         }

         public float apply$mcFIJ$sp(final int v1, final long v2) {
            return Function2.apply$mcFIJ$sp$(this, v1, v2);
         }

         public int apply$mcIIJ$sp(final int v1, final long v2) {
            return Function2.apply$mcIIJ$sp$(this, v1, v2);
         }

         public long apply$mcJIJ$sp(final int v1, final long v2) {
            return Function2.apply$mcJIJ$sp$(this, v1, v2);
         }

         public void apply$mcVIJ$sp(final int v1, final long v2) {
            Function2.apply$mcVIJ$sp$(this, v1, v2);
         }

         public boolean apply$mcZJD$sp(final long v1, final double v2) {
            return Function2.apply$mcZJD$sp$(this, v1, v2);
         }

         public double apply$mcDJD$sp(final long v1, final double v2) {
            return Function2.apply$mcDJD$sp$(this, v1, v2);
         }

         public float apply$mcFJD$sp(final long v1, final double v2) {
            return Function2.apply$mcFJD$sp$(this, v1, v2);
         }

         public int apply$mcIJD$sp(final long v1, final double v2) {
            return Function2.apply$mcIJD$sp$(this, v1, v2);
         }

         public long apply$mcJJD$sp(final long v1, final double v2) {
            return Function2.apply$mcJJD$sp$(this, v1, v2);
         }

         public void apply$mcVJD$sp(final long v1, final double v2) {
            Function2.apply$mcVJD$sp$(this, v1, v2);
         }

         public boolean apply$mcZJI$sp(final long v1, final int v2) {
            return Function2.apply$mcZJI$sp$(this, v1, v2);
         }

         public double apply$mcDJI$sp(final long v1, final int v2) {
            return Function2.apply$mcDJI$sp$(this, v1, v2);
         }

         public float apply$mcFJI$sp(final long v1, final int v2) {
            return Function2.apply$mcFJI$sp$(this, v1, v2);
         }

         public int apply$mcIJI$sp(final long v1, final int v2) {
            return Function2.apply$mcIJI$sp$(this, v1, v2);
         }

         public long apply$mcJJI$sp(final long v1, final int v2) {
            return Function2.apply$mcJJI$sp$(this, v1, v2);
         }

         public void apply$mcVJI$sp(final long v1, final int v2) {
            Function2.apply$mcVJI$sp$(this, v1, v2);
         }

         public boolean apply$mcZJJ$sp(final long v1, final long v2) {
            return Function2.apply$mcZJJ$sp$(this, v1, v2);
         }

         public double apply$mcDJJ$sp(final long v1, final long v2) {
            return Function2.apply$mcDJJ$sp$(this, v1, v2);
         }

         public float apply$mcFJJ$sp(final long v1, final long v2) {
            return Function2.apply$mcFJJ$sp$(this, v1, v2);
         }

         public int apply$mcIJJ$sp(final long v1, final long v2) {
            return Function2.apply$mcIJJ$sp$(this, v1, v2);
         }

         public long apply$mcJJJ$sp(final long v1, final long v2) {
            return Function2.apply$mcJJJ$sp$(this, v1, v2);
         }

         public void apply$mcVJJ$sp(final long v1, final long v2) {
            Function2.apply$mcVJJ$sp$(this, v1, v2);
         }

         public Function1 curried() {
            return Function2.curried$(this);
         }

         public Function1 tupled() {
            return Function2.tupled$(this);
         }

         public String toString() {
            return Function2.toString$(this);
         }

         public DiffFunction repr() {
            return DiffFunction.repr$(this);
         }

         public final double apply(final Object x) {
            return StochasticDiffFunction.apply$(this, x);
         }

         public final Object $plus(final Object b, final UFunc.UImpl2 op) {
            return NumericOps.$plus$(this, b, op);
         }

         public final Object $colon$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$eq$(this, b, op);
         }

         public final Object $colon$plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$plus$eq$(this, b, op);
         }

         public final Object $colon$times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$times$eq$(this, b, op);
         }

         public final Object $plus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$plus$eq$(this, b, op);
         }

         public final Object $times$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$times$eq$(this, b, op);
         }

         public final Object $colon$minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$minus$eq$(this, b, op);
         }

         public final Object $colon$percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$percent$eq$(this, b, op);
         }

         public final Object $percent$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$percent$eq$(this, b, op);
         }

         public final Object $minus$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$minus$eq$(this, b, op);
         }

         public final Object $colon$div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$div$eq$(this, b, op);
         }

         public final Object $colon$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$up$eq$(this, b, op);
         }

         public final Object $div$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$div$eq$(this, b, op);
         }

         public final Object $less$colon$less(final Object b, final UFunc.UImpl2 op) {
            return NumericOps.$less$colon$less$(this, b, op);
         }

         public final Object $less$colon$eq(final Object b, final UFunc.UImpl2 op) {
            return NumericOps.$less$colon$eq$(this, b, op);
         }

         public final Object $greater$colon$greater(final Object b, final UFunc.UImpl2 op) {
            return NumericOps.$greater$colon$greater$(this, b, op);
         }

         public final Object $greater$colon$eq(final Object b, final UFunc.UImpl2 op) {
            return NumericOps.$greater$colon$eq$(this, b, op);
         }

         public final Object $colon$amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$amp$eq$(this, b, op);
         }

         public final Object $colon$bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$bar$eq$(this, b, op);
         }

         public final Object $colon$up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$colon$up$up$eq$(this, b, op);
         }

         public final Object $amp$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$amp$eq$(this, b, op);
         }

         public final Object $bar$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$bar$eq$(this, b, op);
         }

         public final Object $up$up$eq(final Object b, final UFunc.InPlaceImpl2 op) {
            return NumericOps.$up$up$eq$(this, b, op);
         }

         public final Object $plus$colon$plus(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$plus$colon$plus$(this, b, op);
         }

         public final Object $times$colon$times(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$times$colon$times$(this, b, op);
         }

         public final Object $colon$eq$eq(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$colon$eq$eq$(this, b, op);
         }

         public final Object $colon$bang$eq(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$colon$bang$eq$(this, b, op);
         }

         public final Object unary_$minus(final UFunc.UImpl op) {
            return ImmutableNumericOps.unary_$minus$(this, op);
         }

         public final Object $minus$colon$minus(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$minus$colon$minus$(this, b, op);
         }

         public final Object $minus(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$minus$(this, b, op);
         }

         public final Object $percent$colon$percent(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$percent$colon$percent$(this, b, op);
         }

         public final Object $percent(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$percent$(this, b, op);
         }

         public final Object $div$colon$div(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$div$colon$div$(this, b, op);
         }

         public final Object $div(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$div$(this, b, op);
         }

         public final Object $up$colon$up(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$up$colon$up$(this, b, op);
         }

         public final Object dot(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.dot$(this, b, op);
         }

         public final Object unary_$bang(final UFunc.UImpl op) {
            return ImmutableNumericOps.unary_$bang$(this, op);
         }

         public final Object $amp$colon$amp(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$amp$colon$amp$(this, b, op);
         }

         public final Object $bar$colon$bar(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$bar$colon$bar$(this, b, op);
         }

         public final Object $up$up$colon$up$up(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$up$up$colon$up$up$(this, b, op);
         }

         public final Object $amp(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$amp$(this, b, op);
         }

         public final Object $bar(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$bar$(this, b, op);
         }

         public final Object $up$up(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$up$up$(this, b, op);
         }

         public final Object $times(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$times$(this, b, op);
         }

         public final Object t(final CanTranspose op) {
            return ImmutableNumericOps.t$(this, op);
         }

         public Object $bslash(final Object b, final UFunc.UImpl2 op) {
            return ImmutableNumericOps.$bslash$(this, b, op);
         }

         public final Object t(final Object a, final Object b, final CanTranspose op, final CanSlice2 canSlice) {
            return ImmutableNumericOps.t$(this, a, b, op, canSlice);
         }

         public final Object t(final Object a, final CanTranspose op, final CanSlice canSlice) {
            return ImmutableNumericOps.t$(this, a, op, canSlice);
         }

         public boolean apply$mcZD$sp(final double v1) {
            return Function1.apply$mcZD$sp$(this, v1);
         }

         public double apply$mcDD$sp(final double v1) {
            return Function1.apply$mcDD$sp$(this, v1);
         }

         public float apply$mcFD$sp(final double v1) {
            return Function1.apply$mcFD$sp$(this, v1);
         }

         public int apply$mcID$sp(final double v1) {
            return Function1.apply$mcID$sp$(this, v1);
         }

         public long apply$mcJD$sp(final double v1) {
            return Function1.apply$mcJD$sp$(this, v1);
         }

         public void apply$mcVD$sp(final double v1) {
            Function1.apply$mcVD$sp$(this, v1);
         }

         public boolean apply$mcZF$sp(final float v1) {
            return Function1.apply$mcZF$sp$(this, v1);
         }

         public double apply$mcDF$sp(final float v1) {
            return Function1.apply$mcDF$sp$(this, v1);
         }

         public float apply$mcFF$sp(final float v1) {
            return Function1.apply$mcFF$sp$(this, v1);
         }

         public int apply$mcIF$sp(final float v1) {
            return Function1.apply$mcIF$sp$(this, v1);
         }

         public long apply$mcJF$sp(final float v1) {
            return Function1.apply$mcJF$sp$(this, v1);
         }

         public void apply$mcVF$sp(final float v1) {
            Function1.apply$mcVF$sp$(this, v1);
         }

         public boolean apply$mcZI$sp(final int v1) {
            return Function1.apply$mcZI$sp$(this, v1);
         }

         public double apply$mcDI$sp(final int v1) {
            return Function1.apply$mcDI$sp$(this, v1);
         }

         public float apply$mcFI$sp(final int v1) {
            return Function1.apply$mcFI$sp$(this, v1);
         }

         public int apply$mcII$sp(final int v1) {
            return Function1.apply$mcII$sp$(this, v1);
         }

         public long apply$mcJI$sp(final int v1) {
            return Function1.apply$mcJI$sp$(this, v1);
         }

         public void apply$mcVI$sp(final int v1) {
            Function1.apply$mcVI$sp$(this, v1);
         }

         public boolean apply$mcZJ$sp(final long v1) {
            return Function1.apply$mcZJ$sp$(this, v1);
         }

         public double apply$mcDJ$sp(final long v1) {
            return Function1.apply$mcDJ$sp$(this, v1);
         }

         public float apply$mcFJ$sp(final long v1) {
            return Function1.apply$mcFJ$sp$(this, v1);
         }

         public int apply$mcIJ$sp(final long v1) {
            return Function1.apply$mcIJ$sp$(this, v1);
         }

         public long apply$mcJJ$sp(final long v1) {
            return Function1.apply$mcJJ$sp$(this, v1);
         }

         public void apply$mcVJ$sp(final long v1) {
            Function1.apply$mcVJ$sp$(this, v1);
         }

         public Function1 compose(final Function1 g) {
            return Function1.compose$(this, g);
         }

         public Function1 andThen(final Function1 g) {
            return Function1.andThen$(this, g);
         }

         public Tuple2 calculate(final Object x, final IndexedSeq batch) {
            return this.f$1.calculate(x);
         }

         public Object gradientAt(final Object x, final IndexedSeq batch) {
            return this.f$1.gradientAt(x);
         }

         public double valueAt(final Object x, final IndexedSeq batch) {
            return this.f$1.valueAt(x);
         }

         public IndexedSeq fullRange() {
            return (IndexedSeq).MODULE$.IndexedSeq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{0}));
         }

         public {
            this.f$1 = f$1;
            Function1.$init$(this);
            ImmutableNumericOps.$init$(this);
            NumericOps.$init$(this);
            StochasticDiffFunction.$init$(this);
            DiffFunction.$init$(this);
            Function2.$init$(this);
            BatchDiffFunction.$init$(this);
         }
      };
   }

   private BatchDiffFunction$() {
   }
}
